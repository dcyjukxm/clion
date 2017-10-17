// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCStructLike;

class OCUnusedStructInspection$1 extends UnusedVisitor {
    @Override
    public void visitStructLike(final OCStructLike ocStructLike) {
        final OCSymbol symbol = this.getSymbol(ocStructLike);
        if (symbol != null && !symbol.isPredeclaration()) {
            this.checkSymbolUsed((PsiElement)ocStructLike, symbol);
        }
    }
}