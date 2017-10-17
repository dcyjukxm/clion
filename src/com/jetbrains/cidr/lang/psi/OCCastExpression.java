// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;

public interface OCCastExpression extends OCExpression
{
    @NotNull
    OCType getCastType();
    
    @NotNull
    OCCastKind getCastKind();
    
    @Nullable
    PsiElement getBridgeCastModifier();
    
    @Nullable
    OCExpression getOperand();
    
    @Nullable
    OCArgumentList getArgumentList();
    
    @Nullable
    OCTypeElement getTypeElement();
}
