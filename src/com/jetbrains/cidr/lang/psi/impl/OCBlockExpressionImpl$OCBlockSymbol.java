// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public static class OCBlockSymbol extends OCSymbolImpl
{
    public OCBlockSymbol() {
    }
    
    public OCBlockSymbol(final OCBlockExpression ocBlockExpression) {
        super(ocBlockExpression.getProject(), ocBlockExpression.getContainingFile().getVirtualFile(), ocBlockExpression.getTextOffset(), (ocBlockExpression.getReturnTypeElement() != null) ? ("^" + ocBlockExpression.getReturnTypeElement().getText() + "{...}") : "^{...}", Collections.emptyList());
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind block;
        try {
            block = OCSymbolKind.BLOCK;
            if (block == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCBlockExpressionImpl$OCBlockSymbol", "getKind"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return block;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
