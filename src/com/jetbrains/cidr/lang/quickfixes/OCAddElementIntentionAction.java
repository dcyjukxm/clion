// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.psi.PsiElement;

public class OCAddElementIntentionAction extends OCPsiElementQuickFix<PsiElement>
{
    private String myCaption;
    private SmartPsiElementPointer myNewElementPtr;
    private String myFamilyName;
    
    public OCAddElementIntentionAction(final String s, final PsiElement psiElement, final PsiElement psiElement2) {
        this(s, s, psiElement, psiElement2);
    }
    
    public OCAddElementIntentionAction(final String myCaption, final String myFamilyName, final PsiElement psiElement, final PsiElement psiElement2) {
        super(psiElement);
        this.myNewElementPtr = OCElementUtil.createPsiElementPointer(psiElement2);
        this.myCaption = myCaption;
        this.myFamilyName = myFamilyName;
    }
    
    @Override
    protected String getTextInternal() {
        return this.myCaption;
    }
    
    @NotNull
    public String getFamilyName() {
        String myFamilyName;
        try {
            myFamilyName = this.myFamilyName;
            if (myFamilyName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCAddElementIntentionAction", "getFamilyName"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/quickfixes/OCAddElementIntentionAction", "invoke"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCChangeUtil.addBefore(psiElement, (PsiElement)OCElementUtil.getPsiElementByPointer((com.intellij.psi.SmartPsiElementPointer<T>)this.myNewElementPtr), null);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
