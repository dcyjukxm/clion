// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class CMakeWhileCommandImpl extends CMakeLoopCommandImpl implements CMakeWhileCommand
{
    public CMakeWhileCommandImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final CMakeVisitor cMakeVisitor) {
        try {
            if (cMakeVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeWhileCommandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        cMakeVisitor.visitCMakeWhileCommand(this);
    }
    
    @Override
    public void accept(@NotNull final PsiElementVisitor psiElementVisitor) {
        try {
            if (psiElementVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeWhileCommandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (psiElementVisitor instanceof CMakeVisitor) {
                this.accept((CMakeVisitor)psiElementVisitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        super.accept(psiElementVisitor);
    }
    
    @Nullable
    @Override
    public CMakeBodyBlock getCMakeBodyBlock() {
        return this.findChildByClass(CMakeBodyBlock.class);
    }
    
    @Nullable
    @Override
    public CMakeEndWhileCommand getCMakeEndWhileCommand() {
        return this.findChildByClass(CMakeEndWhileCommand.class);
    }
    
    @NotNull
    @Override
    public CMakeWhileCommandCall getCMakeWhileCommandCall() {
        CMakeWhileCommandCall cMakeWhileCommandCall;
        try {
            cMakeWhileCommandCall = this.findNotNullChildByClass(CMakeWhileCommandCall.class);
            if (cMakeWhileCommandCall == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeWhileCommandImpl", "getCMakeWhileCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return cMakeWhileCommandCall;
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
