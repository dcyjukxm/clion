// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.handlers;

import com.intellij.codeInsight.template.TemplateEditingListener;
import com.intellij.openapi.editor.RangeMarker;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Segment;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateEditingAdapter;
import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.ide.util.EditorHelper;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.codeInsight.FileModificationService;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import java.util.List;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.command.WriteCommandAction;

class OCAbstractGenerateTestHandler$1 extends WriteCommandAction {
    final /* synthetic */ Editor val$editor;
    final /* synthetic */ PsiFile val$file;
    final /* synthetic */ PsiFile val$elementFile;
    final /* synthetic */ PsiElement val$element;
    final /* synthetic */ Project val$project;
    final /* synthetic */ List val$chosenCandidates;
    final /* synthetic */ OCActionContext val$actionContext;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        int offset = 0;
        Label_0073: {
            try {
                if (this.val$editor != null) {
                    offset = this.val$editor.getCaretModel().getOffset();
                    break Label_0073;
                }
            }
            catch (Throwable t2) {
                throw a(t2);
            }
            offset = -1;
        }
        final int n = offset;
        PsiElement psiElement = null;
        if (this.val$editor != null) {
            psiElement = this.val$file.findElementAt(n);
            if (psiElement == null) {
                psiElement = this.val$file.getLastChild();
            }
        }
        try {
            if (!FileModificationService.getInstance().prepareFileForWrite(this.val$elementFile)) {
                return;
            }
        }
        catch (Throwable t3) {
            throw a(t3);
        }
        if (this.val$element.isValid()) {
            final VirtualFile virtualFile = this.val$elementFile.getVirtualFile();
            try {
                if (virtualFile != null) {
                    CommandProcessor.getInstance().addAffectedFiles(this.val$project, new VirtualFile[] { virtualFile });
                }
            }
            catch (Throwable t4) {
                throw a(t4);
            }
            final Editor openInEditor = EditorHelper.openInEditor((PsiElement)this.val$elementFile);
            final int insertPos = OCAbstractGenerateTestHandler.this.getInsertPos(this.val$element, n, psiElement, this.val$chosenCandidates, this.val$actionContext);
            Label_0230: {
                try {
                    if (openInEditor == null) {
                        break Label_0230;
                    }
                    final int n2 = insertPos;
                    final int n3 = -1;
                    if (n2 == n3) {
                        break Label_0230;
                    }
                    break Label_0230;
                }
                catch (Throwable t5) {
                    throw a(t5);
                }
                try {
                    final int n2 = insertPos;
                    final int n3 = -1;
                    if (n2 == n3) {
                        CommonRefactoringUtil.showErrorHint(this.val$project, openInEditor, "Action is invalid for the current caret position", OCAbstractGenerateTestHandler.this.getActionTitle(), (String)null);
                        return;
                    }
                }
                catch (Throwable t6) {
                    throw a(t6);
                }
            }
            openInEditor.getCaretModel().moveToOffset(insertPos);
            TemplateManager.getInstance(this.val$project).startTemplate(openInEditor, OCAbstractGenerateTestHandler.access$100(OCAbstractGenerateTestHandler.this, new FileTemplateDescriptor(OCAbstractGenerateTestHandler.access$000(OCAbstractGenerateTestHandler.this)), this.val$project), new TemplateEditingAdapter() {
                @Override
                public void templateFinished(final Template template, final boolean b) {
                    PsiElement psiElement = WriteCommandAction.this.val$elementFile.findElementAt(insertPos);
                    if (psiElement instanceof PsiWhiteSpace) {
                        psiElement = psiElement.getNextSibling();
                    }
                    if (psiElement != null) {
                        final RangeMarker rangeMarker = openInEditor.getDocument().createRangeMarker(psiElement.getTextRange());
                        OCAbstractGenerateTestHandler.this.onTemplateFinished(WriteCommandAction.this.val$elementFile, rangeMarker);
                        OCCodeInsightUtil.showCallableInEditorAndSelectBody(WriteCommandAction.this.val$elementFile, (Segment)rangeMarker, (Condition<OCBlockStatement>)OCAbstractGenerateTestHandler.this::shouldSelectResult);
                    }
                }
            });
        }
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}