// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiElement;

enum OCDeclarationKind$3
{
    @Override
    public boolean isKindOf(final PsiElement psiElement) {
        if (!(psiElement instanceof OCDeclaration)) {
            return false;
        }
        final List<OCDeclarator> declarators = ((OCDeclaration)psiElement).getDeclarators();
        final OCSymbol ocSymbol = declarators.isEmpty() ? null : declarators.get(0).getSymbol();
        return ocSymbol instanceof OCDeclaratorSymbol && ((OCDeclaratorSymbol)ocSymbol).isConst();
    }
}