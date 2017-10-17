// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCStructLike;

public class OCUnusedStructInspection extends OCUnusedCodeInspection
{
    @NotNull
    @Override
    public UnusedVisitor buildVisitor() {
        UnusedVisitor unusedVisitor;
        try {
            unusedVisitor = new UnusedVisitor() {
                @Override
                public void visitStructLike(final OCStructLike ocStructLike) {
                    final OCSymbol symbol = this.getSymbol(ocStructLike);
                    if (symbol != null && !symbol.isPredeclaration()) {
                        this.checkSymbolUsed((PsiElement)ocStructLike, symbol);
                    }
                }
            };
            if (unusedVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCUnusedStructInspection", "buildVisitor"));
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
