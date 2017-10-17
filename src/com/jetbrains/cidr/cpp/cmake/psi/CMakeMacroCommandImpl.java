// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class CMakeMacroCommandImpl extends CMakeRoutineImpl implements CMakeMacroCommand
{
    public CMakeMacroCommandImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final CMakeVisitor cMakeVisitor) {
        try {
            if (cMakeVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeMacroCommandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        cMakeVisitor.visitCMakeMacroCommand(this);
    }
    
    @Override
    public void accept(@NotNull final PsiElementVisitor psiElementVisitor) {
        try {
            if (psiElementVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeMacroCommandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElementVisitor instanceof CMakeVisitor) {
                this.accept((CMakeVisitor)psiElementVisitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
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
    public CMakeCommandArguments getCMakeCommandArguments() {
        return this.findChildByClass(CMakeCommandArguments.class);
    }
    
    @Nullable
    @Override
    public CMakeEndMacroCommand getCMakeEndMacroCommand() {
        return this.findChildByClass(CMakeEndMacroCommand.class);
    }
    
    @NotNull
    @Override
    public CMakeMacroCommandCall getCMakeMacroCommandCall() {
        CMakeMacroCommandCall cMakeMacroCommandCall;
        try {
            cMakeMacroCommandCall = this.findNotNullChildByClass(CMakeMacroCommandCall.class);
            if (cMakeMacroCommandCall == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeMacroCommandImpl", "getCMakeMacroCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cMakeMacroCommandCall;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
