// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.cpp.cmake.psi.builtin.CMakeEndIfCommandCallImplMixin;

public class CMakeEndIfCommandCallImpl extends CMakeEndIfCommandCallImplMixin implements CMakeEndIfCommandCall
{
    public CMakeEndIfCommandCallImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    public void accept(@NotNull final CMakeVisitor cMakeVisitor) {
        try {
            if (cMakeVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeEndIfCommandCallImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        cMakeVisitor.visitCMakeEndIfCommandCall(this);
    }
    
    public void accept(@NotNull final PsiElementVisitor visitor) {
        try {
            if (visitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeEndIfCommandCallImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (visitor instanceof CMakeVisitor) {
                this.accept((CMakeVisitor)visitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        super.accept(visitor);
    }
    
    @NotNull
    @Override
    public CMakeEndIfCommandCallName getCMakeEndIfCommandCallName() {
        CMakeEndIfCommandCallName cMakeEndIfCommandCallName;
        try {
            cMakeEndIfCommandCallName = this.findNotNullChildByClass(CMakeEndIfCommandCallName.class);
            if (cMakeEndIfCommandCallName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeEndIfCommandCallImpl", "getCMakeEndIfCommandCallName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return cMakeEndIfCommandCallName;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
