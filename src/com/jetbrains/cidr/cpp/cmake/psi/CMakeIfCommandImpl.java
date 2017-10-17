// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.List;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class CMakeIfCommandImpl extends CMakeCommandImpl implements CMakeIfCommand
{
    public CMakeIfCommandImpl(final ASTNode astNode) {
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final CMakeVisitor cMakeVisitor) {
        try {
            if (cMakeVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeIfCommandImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        cMakeVisitor.visitCMakeIfCommand(this);
    }
    
    @Override
    public void accept(@NotNull final PsiElementVisitor psiElementVisitor) {
        try {
            if (psiElementVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/cpp/cmake/psi/CMakeIfCommandImpl", "accept"));
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
    public List<CMakeBodyBlock> getCMakeBodyBlockList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)CMakeBodyBlock.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeIfCommandImpl", "getCMakeBodyBlockList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return (List<CMakeBodyBlock>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<CMakeCommand> getCMakeCommandList() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)CMakeCommand.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeIfCommandImpl", "getCMakeCommandList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return (List<CMakeCommand>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public CMakeIfCommandCall getCMakeIfCommandCall() {
        CMakeIfCommandCall cMakeIfCommandCall;
        try {
            cMakeIfCommandCall = this.findNotNullChildByClass(CMakeIfCommandCall.class);
            if (cMakeIfCommandCall == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeIfCommandImpl", "getCMakeIfCommandCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return cMakeIfCommandCall;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
