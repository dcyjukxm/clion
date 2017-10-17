// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import javax.swing.Icon;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeFileLocationUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.navigation.ItemPresentation;

class CMakeRoutineCommandImplMixin$1 implements ItemPresentation {
    @Nullable
    public String getPresentableText() {
        final CMakeArgument firstArgument = CMakeRoutineCommandImplMixin.this.getFirstArgument();
        if (firstArgument instanceof CMakeArgumentImplMixin) {
            return ((CMakeArgumentImplMixin)firstArgument).getPresentation().getPresentableText();
        }
        return CMakeRoutineCommandImplMixin.this.getText();
    }
    
    @Nullable
    public String getLocationString() {
        return CMakeFileLocationUtil.getLocationInFile((PsiElement)CMakeRoutineCommandImplMixin.this, false);
    }
    
    @Nullable
    public Icon getIcon(final boolean b) {
        return CMakeRoutineCommandImplMixin.this.getIcon(0);
    }
}