// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.openapi.util.Segment;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.ArrayList;
import java.util.Iterator;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import java.util.Collection;
import com.intellij.psi.util.PsiUtilCore;
import java.util.LinkedList;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;

public abstract class OCObjCClassTextActionHandlerBase<M extends OCMemberSymbol, C extends OCObjCActionContext<M>> extends OCClassActionHandlerBase<OCClassSymbol, M, C>
{
    protected abstract int getInsertPosition(final PsiElement p0, final int p1, final PsiElement p2, final List<M> p3, final C p4);
    
    @NotNull
    protected abstract String getInsertText(@NotNull final PsiElement p0, @Nullable final PsiElement p1, @NotNull final List<M> p2, @NotNull final C p3);
    
    protected boolean shouldSelectResult(@NotNull final OCBlockStatement ocBlockStatement) {
        try {
            if (ocBlockStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "body", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase", "shouldSelectResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @Override
    protected Class<? extends OCSymbolDeclarator> getParentClass() {
        return OCClassDeclaration.class;
    }
    
    @Override
    protected void performAction(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile, @NotNull final C c, @NotNull final List<M> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase", "performAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase", "performAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase", "performAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenCandidates", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase", "performAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final LinkedList<PsiFile> list2 = new LinkedList<PsiFile>();
        final Iterator<PsiElement> iterator = ((List)this.a(c).getFirst()).iterator();
        while (iterator.hasNext()) {
            list2.add(iterator.next().getContainingFile());
        }
        new WriteCommandAction(project, this.getActionTitle(), PsiUtilCore.toPsiFileArray((Collection)list2)) {
            protected void run(@NotNull final Result result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                int offset = 0;
                Label_0081: {
                    try {
                        FeatureUsageTracker.getInstance().triggerFeatureUsed("codeassists.altInsert");
                        if (editor != null) {
                            offset = editor.getCaretModel().getOffset();
                            break Label_0081;
                        }
                    }
                    catch (Throwable t2) {
                        throw a(t2);
                    }
                    offset = -1;
                }
                final int n = offset;
                PsiElement psiElement = null;
                if (editor != null) {
                    psiElement = psiFile.findElementAt(n);
                    if (psiElement == null) {
                        psiElement = psiFile.getLastChild();
                    }
                }
                final Pair access$000 = OCObjCClassTextActionHandlerBase.this.a(c);
                final List list = (List)access$000.getFirst();
                final Integer n2 = (Integer)access$000.getSecond();
                for (final PsiElement psiElement2 : list) {
                    try {
                        if (!FileModificationService.getInstance().prepareFileForWrite(psiElement2.getContainingFile())) {
                            return;
                        }
                        continue;
                    }
                    catch (Throwable t3) {
                        throw a(t3);
                    }
                }
                for (int i = 0; i < list.size(); ++i) {
                    final PsiElement psiElement3 = list.get(i);
                    if (psiElement3.isValid()) {
                        final VirtualFile virtualFile = psiElement3.getContainingFile().getVirtualFile();
                        try {
                            if (virtualFile != null) {
                                CommandProcessor.getInstance().addAffectedFiles(project, new VirtualFile[] { virtualFile });
                            }
                        }
                        catch (Throwable t4) {
                            throw a(t4);
                        }
                        try {
                            if (i == n2) {
                                OCObjCClassTextActionHandlerBase.this.performAction(project, psiElement3, n, psiElement, list, c);
                                continue;
                            }
                        }
                        catch (Throwable t5) {
                            throw a(t5);
                        }
                        OCObjCClassTextActionHandlerBase.this.performAction(project, psiElement3, -1, null, list, c);
                    }
                }
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
    }
    
    private Pair<List<PsiElement>, Integer> a(@NotNull final C c) {
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase", "getElementsToModify"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        int n = -1;
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        final List<? extends OCSymbol> symbolsToModify = c.getSymbolsToModify();
        for (int i = 0; i < symbolsToModify.size(); ++i) {
            final OCSymbol<PsiElement> ocSymbol = (OCSymbol<PsiElement>)symbolsToModify.get(i);
            if (ocSymbol != null) {
                final PsiElement locateDefinition = ocSymbol.locateDefinition();
                try {
                    if (locateDefinition == null) {
                        continue;
                    }
                    list.add(locateDefinition);
                    if (!ocSymbol.equals(((OCActionContext<Object, M>)c).getParent())) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                n = i;
            }
        }
        return (Pair<List<PsiElement>, Integer>)new Pair((Object)list, (Object)n);
    }
    
    protected void performAction(@NotNull final Project project, @NotNull final PsiElement psiElement, final int n, @Nullable final PsiElement psiElement2, @NotNull final List<M> list, @NotNull final C c) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase", "performAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase", "performAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase", "performAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCObjCClassTextActionHandlerBase", "performAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final PsiFile containingFile = psiElement.getContainingFile();
        final Document document = PsiDocumentManager.getInstance(project).getDocument(containingFile);
        try {
            if (document == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument(document);
        final String insertText = this.getInsertText(psiElement, psiElement2, list, c);
        try {
            if (insertText.isEmpty()) {
                return;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        final int insertPosition = this.getInsertPosition(psiElement, n, psiElement2, list, c);
        try {
            if (insertPosition == -1) {
                return;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        final RangeMarker rangeMarker = document.createRangeMarker(insertPosition, insertPosition);
        rangeMarker.setGreedyToRight(true);
        document.insertString(insertPosition, (CharSequence)insertText);
        PsiDocumentManager.getInstance(project).commitDocument(document);
        OCChangeUtil.reformatTextIfNotInjected(containingFile, rangeMarker.getStartOffset(), rangeMarker.getEndOffset());
        OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)containingFile, TextRange.create((Segment)rangeMarker));
        OCCodeInsightUtil.showCallableInEditorAndSelectBody(containingFile, (Segment)rangeMarker, (Condition<OCBlockStatement>)this::shouldSelectResult);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
