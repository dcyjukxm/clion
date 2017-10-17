// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface OCParameterList extends OCElement, OCLocalScopeable
{
    @NotNull
    List<OCParameterDeclaration> getParameterDeclarations();
    
    @NotNull
    List<OCDeclarator> getParameters();
    
    @Nullable
    ASTNode getLParenth();
    
    @Nullable
    ASTNode getRParenth();
    
    @Nullable
    PsiElement getLastElement();
}
