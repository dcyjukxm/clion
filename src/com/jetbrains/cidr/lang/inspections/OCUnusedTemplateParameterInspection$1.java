// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;

class OCUnusedTemplateParameterInspection$1 extends UnusedVisitor {
    @Override
    public void visitTypeParameterDeclaration(final OCTypeParameterDeclaration ocTypeParameterDeclaration) {
        if (!OCUnusedTemplateParameterInspection.isTraitTemplateParameter((OCTemplateParameterList)ocTypeParameterDeclaration.getParent())) {
            this.checkSymbolUsed((PsiElement)ocTypeParameterDeclaration, this.getSymbol(ocTypeParameterDeclaration));
        }
    }
}