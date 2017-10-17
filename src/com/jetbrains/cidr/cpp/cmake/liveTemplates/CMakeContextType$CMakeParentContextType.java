// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.liveTemplates;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.codeInsight.template.EverywhereContextType;

public static class CMakeParentContextType extends CMakeContextType
{
    protected CMakeParentContextType() {
        super("CMAKE", "CMake", (Class<? extends TemplateContextType>)EverywhereContextType.class);
    }
    
    public boolean isInContext(@NotNull final PsiFile psiFile, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/liveTemplates/CMakeContextType$CMakeParentContextType", "isInContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return psiFile instanceof CMakeFile;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
