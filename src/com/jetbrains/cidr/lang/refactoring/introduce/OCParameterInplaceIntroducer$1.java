// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

class OCParameterInplaceIntroducer$1 implements Rebuilder {
    @Override
    public OCDeclarator rebuild(final OCDeclarator ocDeclarator) {
        return ((OCDeclaration)OCChangeUtil.replaceHandlingMacros(ocDeclarator.getParent(), (PsiElement)OCElementFactory.paramDeclarationByNameAndType(ocDeclarator.getName(), OCParameterInplaceIntroducer.this.getType((PsiElement)ocDeclarator.getInitializer(), false, OCParameterInplaceIntroducer.access$000(OCParameterInplaceIntroducer.this) != null && OCParameterInplaceIntroducer.access$000(OCParameterInplaceIntroducer.this).isSelected()), (PsiElement)ocDeclarator))).getDeclarators().get(0);
    }
}