// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.resolve;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandName;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeLiteral;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;
import com.intellij.codeInsight.TargetElementEvaluatorEx2;

public class CMakeElementEvaluator extends TargetElementEvaluatorEx2
{
    @Nullable
    @Override
    public PsiElement getElementByReference(@NotNull final PsiReference psiReference, final int n) {
        try {
            if (psiReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeElementEvaluator", "getElementByReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Override
    public boolean isAcceptableNamedParent(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeElementEvaluator", "isAcceptableNamedParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0131: {
            CMakeArgument argument = null;
            Label_0097: {
                Label_0092: {
                    Label_0065: {
                        try {
                            if (psiElement instanceof CMakeLiteral) {
                                break Label_0065;
                            }
                            final PsiElement psiElement2 = psiElement;
                            final boolean b = psiElement2 instanceof CMakeArgument;
                            if (b) {
                                break Label_0065;
                            }
                            break Label_0131;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final PsiElement psiElement2 = psiElement;
                            final boolean b = psiElement2 instanceof CMakeArgument;
                            if (!b) {
                                break Label_0131;
                            }
                            if (!(psiElement instanceof CMakeLiteral)) {
                                break Label_0092;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    argument = ((CMakeLiteral)psiElement).getArgument();
                    break Label_0097;
                }
                argument = (CMakeArgument)psiElement;
                try {
                    if (argument.isCommandDefinitionName()) {
                        return true;
                    }
                    final CMakeArgument cMakeArgument = argument;
                    final boolean b2 = cMakeArgument.isEndCommandDefinitionName();
                    if (b2) {
                        return true;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            try {
                final CMakeArgument cMakeArgument = argument;
                final boolean b2 = cMakeArgument.isEndCommandDefinitionName();
                if (b2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            return false;
            try {
                if (psiElement instanceof CMakeCommandName) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
