// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;

public interface OCForeachStatement extends OCLoopStatement, OCLocalScopeable
{
    boolean isCpp11Foreach();
    
    @Nullable
    OCElement getVariableExpression();
    
    @Nullable
    OCElement getVariableDeclaration();
    
    @Nullable
    OCExpression getCollectionExpression();
}
