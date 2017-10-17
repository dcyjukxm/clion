// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.parser.OCElementType;
import org.jetbrains.annotations.Nullable;

public interface OCUnaryExpression extends OCExpression
{
    @Nullable
    OCExpression getOperand();
    
    @NotNull
    OCElementType getOperationSign();
    
    @NotNull
    ASTNode getOperationSignNode();
    
    @NotNull
    String getOperationName();
    
    boolean isGetAddress();
}
