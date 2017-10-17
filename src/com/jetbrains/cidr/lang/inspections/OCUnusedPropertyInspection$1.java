// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCProperty;

class OCUnusedPropertyInspection$1 extends UnusedVisitor {
    @Override
    public void visitProperty(final OCProperty ocProperty) {
        final OCDeclaration declaration = ocProperty.getDeclaration();
        try {
            if (declaration == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        for (final OCDeclarator ocDeclarator : declaration.getDeclarators()) {
            this.checkSymbolUsed((PsiElement)ocDeclarator, ocDeclarator.getSymbol());
        }
    }
    
    @Override
    public void checkFromBatchMode(@NotNull final OCSymbol ocSymbol, @NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/inspections/OCUnusedPropertyInspection$1", "checkFromBatchMode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/inspections/OCUnusedPropertyInspection$1", "checkFromBatchMode"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocSymbol.getKind() == OCSymbolKind.PROPERTY) {
                this.checkSymbolUsed(psiElement, ocSymbol, b);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}