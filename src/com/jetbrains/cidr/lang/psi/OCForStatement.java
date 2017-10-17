// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface OCForStatement extends OCLoopStatement, OCLocalScopeable
{
    @Nullable
    OCStatement getInitializer();
    
    @Nullable
    OCDeclarationOrExpression getCondition();
    
    @Nullable
    OCStatement getIncrement();
}
