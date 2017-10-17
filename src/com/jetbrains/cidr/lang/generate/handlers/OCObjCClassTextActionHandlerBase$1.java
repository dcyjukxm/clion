// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import java.util.Iterator;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.psi.PsiElement;
import com.intellij.featureStatistics.FeatureUsageTracker;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import java.util.List;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.command.WriteCommandAction;

class OCObjCClassTextActionHandlerBase$1 extends WriteCommandAction {
    final /* synthetic */ Editor val$editor;
    final /* synthetic */ PsiFile val$file;
    final /* synthetic */ OCObjCActionContext val$actionContext;
    final /* synthetic */ Project val$project;
    final /* synthetic */ List val$chosenCandidates;
    
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
                if (this.val$editor != null) {
                    offset = this.val$editor.getCaretModel().getOffset();
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
        if (this.val$editor != null) {
            psiElement = this.val$file.findElementAt(n);
            if (psiElement == null) {
                psiElement = this.val$file.getLastChild();
            }
        }
        final Pair access$000 = OCObjCClassTextActionHandlerBase.access$000(OCObjCClassTextActionHandlerBase.this, this.val$actionContext);
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
                        CommandProcessor.getInstance().addAffectedFiles(this.val$project, new VirtualFile[] { virtualFile });
                    }
                }
                catch (Throwable t4) {
                    throw a(t4);
                }
                try {
                    if (i == n2) {
                        OCObjCClassTextActionHandlerBase.this.performAction(this.val$project, psiElement3, n, psiElement, this.val$chosenCandidates, this.val$actionContext);
                        continue;
                    }
                }
                catch (Throwable t5) {
                    throw a(t5);
                }
                OCObjCClassTextActionHandlerBase.this.performAction(this.val$project, psiElement3, -1, null, this.val$chosenCandidates, this.val$actionContext);
            }
        }
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}