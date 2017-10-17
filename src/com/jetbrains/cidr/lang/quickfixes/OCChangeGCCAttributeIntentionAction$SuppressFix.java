// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.codeInspection.SuppressIntentionAction;

public static class SuppressFix extends SuppressIntentionAction
{
    private OCChangeGCCAttributeIntentionAction myAction;
    
    public SuppressFix(final OCSymbol ocSymbol, final String s, final String s2, final boolean b) {
        this.myAction = new OCChangeGCCAttributeIntentionAction(ocSymbol, s, s2, b) {
            @Override
            protected boolean isAvailable(final OCSymbol ocSymbol) {
                return super.isAvailable(ocSymbol) && SuppressFix.this.isAvailable(ocSymbol);
            }
        };
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        this.myAction.invoke(project, editor, psiElement.getContainingFile());
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return this.myAction.isAvailable(project, editor, psiElement.getContainingFile());
    }
    
    protected boolean isAvailable(final OCSymbol ocSymbol) {
        return true;
    }
    
    @NotNull
    public String getFamilyName() {
        String familyName;
        try {
            familyName = this.myAction.getFamilyName();
            if (familyName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return familyName;
    }
    
    @NotNull
    public String getText() {
        String text;
        try {
            text = this.myAction.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeGCCAttributeIntentionAction$SuppressFix", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean startInWriteAction() {
        return this.myAction.startInWriteAction();
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
