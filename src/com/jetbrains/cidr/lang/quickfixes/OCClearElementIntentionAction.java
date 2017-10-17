// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class OCClearElementIntentionAction extends OCPsiElementQuickFix<PsiElement>
{
    private String myQuickFixName;
    private String myFamilyName;
    
    public OCClearElementIntentionAction(final PsiElement psiElement, final String s) {
        this(psiElement, s, s);
    }
    
    public OCClearElementIntentionAction(final PsiElement psiElement, final String myQuickFixName, final String myFamilyName) {
        super(psiElement);
        this.myQuickFixName = myQuickFixName;
        this.myFamilyName = myFamilyName;
    }
    
    @Override
    protected String getTextInternal() {
        return this.myQuickFixName;
    }
    
    @NotNull
    public String getFamilyName() {
        String myFamilyName;
        try {
            myFamilyName = this.myFamilyName;
            if (myFamilyName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCClearElementIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myFamilyName;
    }
    
    @Override
    protected void invoke(final PsiFile psiFile, @NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCClearElementIntentionAction", "invoke"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiElement firstChild = psiElement.getFirstChild();
        final PsiElement lastChild = psiElement.getLastChild();
        Label_0075: {
            try {
                if (firstChild == null) {
                    return;
                }
                final PsiElement psiElement2 = lastChild;
                if (psiElement2 != null) {
                    break Label_0075;
                }
                return;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement2 = lastChild;
                if (psiElement2 != null) {
                    psiElement.deleteChildRange(firstChild, lastChild);
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
