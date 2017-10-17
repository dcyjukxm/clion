// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class OCChangeTextIntentionAction extends OCPsiElementQuickFix<PsiFile>
{
    private int myOffset;
    private int myLength;
    private String mySubstitution;
    private String myIntentionName;
    private String myFamilyName;
    
    public OCChangeTextIntentionAction(final PsiFile psiFile, final int n, final int n2, final String s, final String s2) {
        this(psiFile, n, n2, s, s2, s2);
    }
    
    public OCChangeTextIntentionAction(final PsiFile psiFile, final int myOffset, final int myLength, final String mySubstitution, final String myIntentionName, final String myFamilyName) {
        super((PsiElement)psiFile);
        this.myOffset = myOffset;
        this.myLength = myLength;
        this.mySubstitution = mySubstitution;
        this.myIntentionName = myIntentionName;
        this.myFamilyName = myFamilyName;
    }
    
    @Override
    protected String getTextInternal() {
        return this.myIntentionName;
    }
    
    @NotNull
    public String getFamilyName() {
        String myFamilyName;
        try {
            myFamilyName = this.myFamilyName;
            if (myFamilyName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myFamilyName;
    }
    
    @Override
    protected boolean isAvailable(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.mySubstitution != null) {
                return true;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @Override
    protected void invoke(final PsiFile psiFile, @NotNull final PsiFile psiFile2) {
        try {
            if (psiFile2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction", "invoke"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCChangeUtil.changeText(psiFile2.getProject(), psiFile2, this.myOffset, this.myLength, this.mySubstitution, false);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
