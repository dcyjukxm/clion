// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi.builtin;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandNameImplMixin;

public abstract class CMakeEndMacroCommandCallNameImplMixin extends CMakeCommandNameImplMixin
{
    public CMakeEndMacroCommandCallNameImplMixin(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/cpp/cmake/psi/builtin/CMakeEndMacroCommandCallNameImplMixin", "<init>"));
        }
        super(astNode);
    }
}
