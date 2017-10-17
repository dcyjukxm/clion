// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class CMakeCommandImpl extends CMakeCommandImplMixin implements CMakeCommand
{
    public CMakeCommandImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    public void accept(@NotNull final CMakeVisitor cMakeVisitor) {
        try {
            if (cMakeVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        cMakeVisitor.visitCMakeCommand(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (visitor instanceof CMakeVisitor) {
                this.accept((CMakeVisitor)visitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        super.accept(visitor);
    }
    
    @Nullable
    @Override
    public CMakeCommandArguments getCMakeCommandArguments() {
        return this.findChildByClass(CMakeCommandArguments.class);
    }
    
    @NotNull
    @Override
    public CMakeCommandName getCMakeCommandName() {
        CMakeCommandName cMakeCommandName;
        try {
            cMakeCommandName = this.findNotNullChildByClass(CMakeCommandName.class);
            if (cMakeCommandName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandImpl", "getCMakeCommandName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cMakeCommandName;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
