// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCRemoveInitializerIntentionAction extends OCPsiElementQuickFix<OCDeclarator>
{
    public OCRemoveInitializerIntentionAction(final OCDeclarator ocDeclarator) {
        super((PsiElement)ocDeclarator);
    }
    
    @Override
    protected String getTextInternal() {
        return "Remove initializer";
    }
    
    @NotNull
    public String getFamilyName() {
        String textInternal;
        try {
            textInternal = this.getTextInternal();
            if (textInternal == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveInitializerIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return textInternal;
    }
    
    @Override
    protected boolean isAvailable(@NotNull final OCDeclarator ocDeclarator) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/quickfixes/OCRemoveInitializerIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocDeclarator.getInitializer() != null) {
                return true;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @Override
    protected void invoke(final PsiFile psiFile, @NotNull final OCDeclarator ocDeclarator) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/quickfixes/OCRemoveInitializerIntentionAction", "invoke"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCChangeUtil.delete((PsiElement)ocDeclarator.getInitializer());
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
