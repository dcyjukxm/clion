// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.template.TemplateContextType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;

public static class DeclarationBase extends OCCodeContextType
{
    public DeclarationBase(@NotNull final OCLanguageKind ocLanguageKind) {
        if (ocLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "needKind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$DeclarationBase", "<init>"));
        }
        super(ocLanguageKind, "OC_DECLARATION" + LanguageInfo.getSuffix(ocLanguageKind), "Declaration", LanguageInfo.getBaseContext(ocLanguageKind));
    }
    
    @Override
    protected boolean isInContext(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$DeclarationBase", "isInContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (PsiTreeUtil.getParentOfType(psiElement, (Class)OCBlockStatement.class) == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
