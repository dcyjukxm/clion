// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.editor;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeRoutine;
import com.intellij.ide.util.PsiElementListCellRenderer;
import com.intellij.codeInsight.navigation.GotoTargetHandler;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.navigation.GotoTargetRendererProvider;

public class CMakeTargetRendererProvider implements GotoTargetRendererProvider
{
    @Override
    public PsiElementListCellRenderer getRenderer(@NotNull final PsiElement psiElement, @NotNull final GotoTargetHandler.GotoData gotoData) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/editor/CMakeTargetRendererProvider", "getRenderer"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (gotoData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "gotoData", "com/jetbrains/cidr/cpp/cmake/editor/CMakeTargetRendererProvider", "getRenderer"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement instanceof CMakeRoutine) {
                return new CMakeGoToTargetRenderer();
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
