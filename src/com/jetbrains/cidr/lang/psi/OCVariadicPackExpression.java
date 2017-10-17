// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;

public interface OCVariadicPackExpression extends OCExpression
{
    @NotNull
    OCExpression getOperand();
}
