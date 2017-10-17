// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.types.ARCAttribute;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCVisibility;

public interface OCInstanceVariableSymbol extends OCMemberSymbol
{
    @NotNull
    OCVisibility getVisibility();
    
    void updateVisibility(@NotNull final OCVisibility p0);
    
    @NotNull
    ARCAttribute getARCAttribute(final PsiElement p0);
    
    String getGeneratedFromProperty();
    
    boolean processSynthesizes(final Processor<OCSynthesizeSymbol> p0);
    
    @Nullable
    OCPropertySymbol getAssociatedProperty();
    
    boolean isClang4ImplicitIvar(@Nullable final PsiFile p0);
    
    boolean isClang4ImplicitIvar();
    
    boolean isForbiddenClang4ImplicitIvar();
    
    boolean isForbiddenClang4ImplicitIvar(@Nullable final PsiFile p0);
}
