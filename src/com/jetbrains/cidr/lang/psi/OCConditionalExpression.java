// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public interface OCConditionalExpression extends OCExpression
{
    @NotNull
    OCExpression getCondition();
    
    @Nullable
    OCExpression getPositiveExpression(final boolean p0);
    
    @Nullable
    OCExpression getNegativeExpression();
}
