// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.parser.OCElementType;

public interface OCStatementWithExpression extends OCStatement
{
    @NotNull
    OCElementType getKeywordType();
    
    @Nullable
    OCStatement getBody();
    
    @Nullable
    PsiElement getExpression();
    
    @Nullable
    ASTNode getRParenth();
    
    @Nullable
    ASTNode getLParenth();
}
