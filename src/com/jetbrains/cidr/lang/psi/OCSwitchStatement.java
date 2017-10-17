// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface OCSwitchStatement extends OCStatementWithExpression
{
    @Nullable
    OCDeclarationOrExpression getExpression();
    
    @Nullable
    OCStatement getBody();
    
    @NotNull
    PsiElement getSwitchToken();
}
