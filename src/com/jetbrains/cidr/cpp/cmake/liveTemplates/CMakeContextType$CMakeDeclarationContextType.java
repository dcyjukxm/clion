// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.liveTemplates;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandName;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.template.TemplateContextType;

public static class CMakeDeclarationContextType extends CMakeContextType
{
    public CMakeDeclarationContextType() {
        super("CMAKE_COMMAND_CONTEXT", "Command", CMakeParentContextType.class);
    }
    
    public boolean isInContext(@NotNull final PsiFile psiFile, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/liveTemplates/CMakeContextType$CMakeDeclarationContextType", "isInContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(psiFile instanceof CMakeFile)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement element = psiFile.findElementAt(n);
        boolean b = false;
        Label_0078: {
            try {
                if (element != null) {
                    b = true;
                    break Label_0078;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            b = false;
        }
        final boolean b2 = b;
        try {
            if (!b2) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final PsiElement parent = element.getParent();
        Label_0119: {
            try {
                if (parent == null) {
                    return false;
                }
                final PsiElement psiElement = parent;
                final boolean b3 = psiElement instanceof CMakeCommandName;
                if (b3) {
                    break Label_0119;
                }
                return false;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                final PsiElement psiElement = parent;
                final boolean b3 = psiElement instanceof CMakeCommandName;
                if (b3) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
