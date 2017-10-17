// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi.builtin;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandArguments;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandNameImpl;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandName;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandImplMixin;

public abstract class CMakeBuiltInCommandCallImplMixin extends CMakeCommandImplMixin
{
    public CMakeBuiltInCommandCallImplMixin(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/cpp/cmake/psi/builtin/CMakeBuiltInCommandCallImplMixin", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public CMakeCommandName getCMakeCommandName() {
        CMakeCommandNameImpl cMakeCommandNameImpl;
        try {
            cMakeCommandNameImpl = new CMakeCommandNameImpl(this.getNode());
            if (cMakeCommandNameImpl == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/builtin/CMakeBuiltInCommandCallImplMixin", "getCMakeCommandName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cMakeCommandNameImpl;
    }
    
    @Nullable
    @Override
    public CMakeCommandArguments getCMakeCommandArguments() {
        return (CMakeCommandArguments)this.getFirstChild();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
