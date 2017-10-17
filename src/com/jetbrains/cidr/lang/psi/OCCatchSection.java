// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;

public interface OCCatchSection extends OCStatementWithExpression
{
    @Nullable
    OCBlockStatement getBody();
    
    @Nullable
    OCParameterList getParameters();
    
    boolean isCppStatement();
}
