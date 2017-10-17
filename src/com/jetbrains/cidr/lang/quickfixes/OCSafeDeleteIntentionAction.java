// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.refactoring.safeDelete.SafeDeleteHandler;
import com.intellij.util.Processor;
import com.intellij.psi.PsiFile;
import java.util.Collection;
import java.util.Collections;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.intention.LowPriorityAction;

public class OCSafeDeleteIntentionAction extends OCRemoveElementsIntentionAction implements LowPriorityAction
{
    public OCSafeDeleteIntentionAction(final PsiElement psiElement, final String s) {
        super(Collections.singletonList(psiElement), "Safe delete " + s, "Safe delete");
    }
    
    @Override
    protected void invoke(final PsiFile psiFile) {
        this.processElements((Processor<PsiElement>)(psiElement -> {
            SafeDeleteHandler.invoke(psiElement.getProject(), new PsiElement[] { psiElement }, true);
            return true;
        }));
    }
    
    public boolean startInWriteAction() {
        return false;
    }
}
