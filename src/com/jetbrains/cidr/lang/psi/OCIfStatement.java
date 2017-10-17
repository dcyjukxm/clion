// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.Nullable;

public interface OCIfStatement extends OCStatement, OCLocalScopeable
{
    @Nullable
    OCDeclarationOrExpression getCondition();
    
    @Nullable
    OCStatement getThenBranch();
    
    @Nullable
    OCStatement getElseBranch();
    
    void setCondition(final OCDeclarationOrExpression p0);
    
    void setElse(final OCStatement p0);
    
    void setThen(final OCStatement p0);
    
    @Nullable
    ASTNode getRParenth();
    
    @Nullable
    ASTNode getLParenth();
    
    @Nullable
    ASTNode getElseKeyword();
}
