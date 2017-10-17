// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.cpp.cmake.completion.contributors.CMakeVariableCompletionContributor;
import com.jetbrains.cidr.cpp.cmake.completion.contributors.CMakeCommandCompletionContributor;
import com.jetbrains.cidr.cpp.cmake.completion.contributors.CMakeKeywordCompletionContributor;
import com.intellij.codeInsight.completion.CompletionContributor;

public class CMakeCompletionContributor extends CompletionContributor
{
    public CMakeCompletionContributor() {
        new CMakeKeywordCompletionContributor().extendContributor(this);
        new CMakeCommandCompletionContributor().extendContributor(this);
        new CMakeVariableCompletionContributor().extendContributor(this);
    }
    
    public boolean invokeAutoPopup(@NotNull final PsiElement psiElement, final char c) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/cpp/cmake/completion/CMakeCompletionContributor", "invokeAutoPopup"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return CMakeVariableCompletionContributor.shouldAutoPopup(psiElement, c);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
