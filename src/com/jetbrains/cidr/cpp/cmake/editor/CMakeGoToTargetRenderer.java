// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.editor;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgumentImplMixin;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeRoutine;
import com.intellij.psi.PsiElement;
import com.intellij.ide.util.DefaultPsiElementCellRenderer;

public class CMakeGoToTargetRenderer extends DefaultPsiElementCellRenderer
{
    @Override
    public String getElementText(final PsiElement psiElement) {
        if (psiElement instanceof CMakeRoutine) {
            final CMakeArgument firstArgument = ((CMakeRoutine)psiElement).getFirstArgument();
            if (firstArgument instanceof CMakeArgumentImplMixin) {
                return ((CMakeArgumentImplMixin)firstArgument).getPresentation().getPresentableText();
            }
        }
        return super.getElementText(psiElement);
    }
}
