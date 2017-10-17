// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;

public interface OCCaseStatement extends OCStatement
{
    @Nullable
    OCExpression getExpression();
    
    boolean isRange();
    
    boolean isDefault();
    
    @Nullable
    OCExpression getRangeFirst();
    
    @Nullable
    OCExpression getRangeSecond();
    
    @Nullable
    OCStatement getStatement();
}
