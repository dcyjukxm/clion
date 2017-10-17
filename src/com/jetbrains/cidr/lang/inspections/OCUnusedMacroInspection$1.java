// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.impl.OCDefineDirectiveImpl;

class OCUnusedMacroInspection$1 extends UnusedVisitor {
    @Override
    public void visitDefineDirective(final OCDefineDirectiveImpl ocDefineDirectiveImpl) {
        final OCSymbol symbol = this.getSymbol(ocDefineDirectiveImpl);
        if (symbol instanceof OCMacroSymbol) {
            this.checkSymbolUsed((PsiElement)ocDefineDirectiveImpl, symbol);
        }
    }
}