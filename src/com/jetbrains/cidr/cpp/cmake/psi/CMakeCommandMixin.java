// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiPolyVariantReference;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiNamedElement;

interface CMakeCommandMixin extends CMakeElement, PsiNamedElement
{
    @NotNull
    String getName();
    
    @NotNull
    PsiElement getNameElement();
    
    boolean namesEqual(@Nullable final String p0);
    
    @Nullable
    CMakeArgument getFirstArgument();
    
    @Nullable
    String getFirstArgumentValue();
    
    @NotNull
    List<CMakeArgument> getTailArguments();
    
    @Nullable
    List<CMakeArgument> getCMakeArgumentList();
    
    @NotNull
    List<String> getTailArgumentsValues();
    
    boolean canAppendArguments();
    
    void appendArgument(@NotNull final String p0);
    
    @Nullable
    PsiPolyVariantReference getReference();
}
