// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class CMakeElementBase extends ASTWrapperPsiElement implements CMakeElement
{
    public CMakeElementBase(@NotNull final ASTNode node) {
        if (node == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/cpp/cmake/psi/CMakeElementBase", "<init>"));
        }
        super(node);
    }
}
