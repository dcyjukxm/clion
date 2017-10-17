// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.jetbrains.cidr.cpp.cmake.resolve.CMakeRenameUtils;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class CMakeCommandNameImplMixin extends CMakeElementBase implements CMakeCommandNameMixin
{
    public CMakeCommandNameImplMixin(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandNameImplMixin", "<init>"));
        }
        super(astNode);
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandNameImplMixin", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        CMakeRenameUtils.renameCommandCall(this.getProject(), s, this.getNode());
        return (PsiElement)this;
    }
    
    @NotNull
    @Override
    public String getName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandNameImplMixin", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
