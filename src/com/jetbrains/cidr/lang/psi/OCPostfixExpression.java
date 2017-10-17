// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCElementType;
import org.jetbrains.annotations.NotNull;

public interface OCPostfixExpression extends OCExpression
{
    @NotNull
    OCExpression getOperand();
    
    @NotNull
    OCElementType getOperationSign();
    
    @NotNull
    ASTNode getOperationSignNode();
}
