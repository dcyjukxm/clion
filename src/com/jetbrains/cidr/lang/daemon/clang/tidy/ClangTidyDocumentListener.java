// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import java.util.List;
import java.util.Iterator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.Document;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.util.Processor;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.codeInsight.daemon.impl.DaemonCodeAnalyzerEx;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.util.SmartList;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.util.DocumentUtil;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;

public class ClangTidyDocumentListener implements DocumentListener
{
    public void beforeDocumentChange(final DocumentEvent documentEvent) {
        final Document document = documentEvent.getDocument();
        final int lineStartOffset = DocumentUtil.getLineStartOffset(documentEvent.getOffset(), document);
        final int lineEndOffset = DocumentUtil.getLineEndOffset(lineStartOffset + documentEvent.getOldLength(), document);
        final Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
        final SmartList list = new SmartList();
        for (final Project project : openProjects) {
            if (project.isInitialized()) {
                if (!project.isDisposed()) {
                    if (PsiDocumentManager.getInstance(project).getCachedPsiFile(document) != null) {
                        DaemonCodeAnalyzerEx.processHighlights(document, project, null, lineStartOffset, lineEndOffset, (Processor<HighlightInfo>)(highlightInfo -> {
                            if (highlightInfo.getProblemGroup() instanceof ClangTidyProblemGroup) {
                                ((List<HighlightInfo>)list).add(highlightInfo);
                            }
                            return true;
                        }));
                    }
                }
            }
        }
        final Iterator<HighlightInfo> iterator = ((List<HighlightInfo>)list).iterator();
        while (iterator.hasNext()) {
            iterator.next().getHighlighter().dispose();
        }
    }
    
    public void documentChanged(final DocumentEvent documentEvent) {
    }
}
