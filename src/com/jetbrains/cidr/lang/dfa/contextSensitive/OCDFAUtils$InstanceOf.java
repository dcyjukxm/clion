// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;

public static class InstanceOf
{
    OCReferenceExpression variable;
    OCSymbol symbol;
    OCType type;
    
    public InstanceOf(final OCReferenceExpression variable, final OCSymbol symbol, final OCType type) {
        this.variable = variable;
        this.symbol = symbol;
        this.type = type;
    }
}
