// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

class OCVariableInplaceIntroducer$1 implements Rebuilder {
    @Override
    public OCDeclarator rebuild(final OCDeclarator ocDeclarator) {
        return ((OCDeclarationStatement)OCChangeUtil.replaceHandlingMacros(ocDeclarator.getParent().getParent(), (PsiElement)OCVariableInplaceIntroducer.access$000(OCVariableInplaceIntroducer.this, ocDeclarator.getName(), ocDeclarator.getInitializer(), (PsiElement)ocDeclarator))).getDeclaration().getDeclarators().get(0);
    }
}