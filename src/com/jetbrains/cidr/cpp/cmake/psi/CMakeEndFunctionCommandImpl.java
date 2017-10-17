// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class CMakeEndFunctionCommandImpl extends CMakeCommandImpl implements CMakeEndFunctionCommand
{
    public CMakeEndFunctionCommandImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final CMakeVisitor cMakeVisitor) {
        try {
            if (cMakeVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeEndFunctionCommandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        cMakeVisitor.visitCMakeEndFunctionCommand(this);
    }
    
    @Override
    public void accept(@NotNull final PsiElementVisitor psiElementVisitor) {
        try {
            if (psiElementVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeEndFunctionCommandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (psiElementVisitor instanceof CMakeVisitor) {
                this.accept((CMakeVisitor)psiElementVisitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        super.accept(psiElementVisitor);
    }
    
    @NotNull
    @Override
    public CMakeEndFunctionCommandCall getCMakeEndFunctionCommandCall() {
        CMakeEndFunctionCommandCall cMakeEndFunctionCommandCall;
        try {
            cMakeEndFunctionCommandCall = this.findNotNullChildByClass(CMakeEndFunctionCommandCall.class);
            if (cMakeEndFunctionCommandCall == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeEndFunctionCommandImpl", "getCMakeEndFunctionCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return cMakeEndFunctionCommandCall;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
