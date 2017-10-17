// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.parser.OCElementType;
import org.jetbrains.annotations.Nullable;

public interface OCBinaryExpression extends OCExpression
{
    @Nullable
    OCExpression getLeft();
    
    @Nullable
    OCExpression getRight();
    
    @NotNull
    OCElementType getOperationSign();
    
    @NotNull
    ASTNode getOperationSignNode();
}
