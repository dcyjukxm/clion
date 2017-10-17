// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiPolyVariantReference;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.interpreter.CMakeScope;
import java.util.List;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;

interface CMakeArgumentMixin extends CMakeElement, PsiNamedElement
{
    @NotNull
    PsiElement getLiteralNotNull();
    
    boolean isBracketArgument();
    
    boolean isQuotedArgument();
    
    @NotNull
    String getValue();
    
    @NotNull
    TextRange getContentsRange();
    
    void expandIntoArgumentList(@NotNull final List<String> p0, @NotNull final CMakeScope p1);
    
    boolean isFunctionName();
    
    boolean isEndFunctionName();
    
    boolean isMacroName();
    
    boolean isEndMacroName();
    
    boolean isCommandDefinitionName();
    
    boolean isEndCommandDefinitionName();
    
    boolean isEndCommandDefinitionName(@Nullable final String p0);
    
    @Nullable
    CMakeArgument getCommandDefinitionName();
    
    @Nullable
    CMakeArgument getNextArgument();
    
    @Nullable
    CMakeArgument getPreviousArgument();
    
    @NotNull
    CMakeCommandArguments getParentCommandArguments();
    
    @Nullable
    PsiPolyVariantReference getReference();
}
