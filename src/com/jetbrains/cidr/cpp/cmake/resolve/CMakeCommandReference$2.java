// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.resolve;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeLiteral;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import com.intellij.psi.PsiElementResolveResult;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeRoutine;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.intellij.psi.search.PsiElementProcessor;

class CMakeCommandReference$2 implements PsiElementProcessor {
    final /* synthetic */ boolean val$checkForTextEquality;
    final /* synthetic */ List val$commands;
    final /* synthetic */ CMakeCommandReference this$0;
    
    public boolean execute(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReference$2", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (psiElement instanceof CMakeRoutine) {
            final CMakeArgument firstArgument = ((CMakeRoutine)psiElement).getFirstArgument();
            boolean b = false;
            Label_0076: {
                try {
                    if (firstArgument != null) {
                        b = true;
                        break Label_0076;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                b = false;
            }
            final boolean b2 = b;
            boolean b4 = false;
            Label_0108: {
                Label_0099: {
                    try {
                        if (!b2) {
                            break Label_0099;
                        }
                        final CMakeArgument cMakeArgument = firstArgument;
                        final boolean b3 = cMakeArgument.isCommandDefinitionName();
                        if (b3) {
                            break Label_0099;
                        }
                        break Label_0099;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final CMakeArgument cMakeArgument = firstArgument;
                        final boolean b3 = cMakeArgument.isCommandDefinitionName();
                        if (b3) {
                            b4 = true;
                            break Label_0108;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                b4 = false;
            }
            final boolean b5 = b4;
            Label_0217: {
                try {
                    if (!b5) {
                        return true;
                    }
                    if (!this.val$checkForTextEquality) {
                        break Label_0217;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                final CMakeLiteral cMakeLiteral = firstArgument.getCMakeLiteral();
                boolean b7 = false;
                Label_0183: {
                    Label_0174: {
                        try {
                            if (cMakeLiteral == null) {
                                break Label_0174;
                            }
                            final CMakeLiteral cMakeLiteral2 = cMakeLiteral;
                            final String s = cMakeLiteral2.getText();
                            final PsiElementProcessor psiElementProcessor = (PsiElementProcessor)this;
                            final CMakeCommandReference cMakeCommandReference = psiElementProcessor.this$0;
                            final PsiElement psiElement2 = CMakeCommandReference.access$100(cMakeCommandReference);
                            final String s2 = psiElement2.getText();
                            final boolean b6 = s.equalsIgnoreCase(s2);
                            if (b6) {
                                break Label_0174;
                            }
                            break Label_0174;
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        try {
                            final CMakeLiteral cMakeLiteral2 = cMakeLiteral;
                            final String s = cMakeLiteral2.getText();
                            final PsiElementProcessor psiElementProcessor = (PsiElementProcessor)this;
                            final CMakeCommandReference cMakeCommandReference = psiElementProcessor.this$0;
                            final PsiElement psiElement2 = CMakeCommandReference.access$100(cMakeCommandReference);
                            final String s2 = psiElement2.getText();
                            final boolean b6 = s.equalsIgnoreCase(s2);
                            if (b6) {
                                b7 = true;
                                break Label_0183;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                    }
                    b7 = false;
                }
                final boolean b8 = b7;
                try {
                    if (b8) {
                        this.val$commands.add(new PsiElementResolveResult((PsiElement)firstArgument));
                        return true;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                return true;
            }
            this.val$commands.add(new PsiElementResolveResult((PsiElement)firstArgument));
            return true;
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}