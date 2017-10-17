// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors;

import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeTokenTypes;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeCompletionContributor;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.PsiElementPattern;

public abstract class AbstractCMakeCompletionContributor
{
    public static final PsiElementPattern.Capture<PsiElement> ID_CAPTURE;
    
    public abstract void extendContributor(final CMakeCompletionContributor p0);
    
    static {
        ID_CAPTURE = PlatformPatterns.psiElement(CMakeTokenTypes.ID);
    }
}
