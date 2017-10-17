// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.hint.DefaultImplementationTextSelectioner;

public class OCImplementationTextSelectioner extends DefaultImplementationTextSelectioner
{
    public int getTextStartOffset(@NotNull PsiElement parent) {
        try {
            if (parent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/OCImplementationTextSelectioner", "getTextStartOffset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (parent instanceof OCDeclarator) {
            parent = ((PsiElement)parent).getParent();
        }
        else if (parent instanceof OCStruct) {
            final OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getContextOfType((PsiElement)parent, false, new Class[] { OCDeclaration.class });
            if (ocDeclaration != null) {
                parent = ocDeclaration;
            }
        }
        return super.getTextStartOffset((PsiElement)parent);
    }
    
    public int getTextEndOffset(@NotNull PsiElement parent) {
        try {
            if (parent == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/OCImplementationTextSelectioner", "getTextEndOffset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (parent.getParent() instanceof OCFunctionDeclaration) {
            parent = parent.getParent();
        }
        return parent.getTextRange().getEndOffset();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
