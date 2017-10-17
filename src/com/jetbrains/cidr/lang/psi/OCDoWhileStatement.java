// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.Nullable;

public interface OCDoWhileStatement extends OCLoopStatement
{
    @Nullable
    OCExpression getCondition();
    
    @Nullable
    ASTNode getWhileKeyword();
}
