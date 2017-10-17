// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.impl.OCDefineDirectiveImpl;

public class OCUnusedMacroInspection extends OCUnusedCodeInspection
{
    @NotNull
    @Override
    public UnusedVisitor buildVisitor() {
        UnusedVisitor unusedVisitor;
        try {
            unusedVisitor = new UnusedVisitor() {
                @Override
                public void visitDefineDirective(final OCDefineDirectiveImpl ocDefineDirectiveImpl) {
                    final OCSymbol symbol = this.getSymbol(ocDefineDirectiveImpl);
                    if (symbol instanceof OCMacroSymbol) {
                        this.checkSymbolUsed((PsiElement)ocDefineDirectiveImpl, symbol);
                    }
                }
            };
            if (unusedVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCUnusedMacroInspection", "buildVisitor"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return unusedVisitor;
    }
    
    private static IllegalStateException c(final IllegalStateException ex) {
        return ex;
    }
}
