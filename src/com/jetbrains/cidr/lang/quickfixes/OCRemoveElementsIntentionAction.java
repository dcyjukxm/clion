// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.util.Processor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public class OCRemoveElementsIntentionAction extends OCPsiElementsQuickFix
{
    private String myIntentionName;
    private String myFamilyName;
    
    public OCRemoveElementsIntentionAction(@Nullable final PsiElement psiElement, final String s) {
        this(psiElement, s, s);
    }
    
    public OCRemoveElementsIntentionAction(final PsiElement psiElement, final String s, final String s2) {
        this(Collections.singletonList(psiElement), s, s2);
    }
    
    public OCRemoveElementsIntentionAction(final Collection<? extends PsiElement> collection, final String myIntentionName, final String myFamilyName) {
        super(collection);
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myFamilyName;
    }
    
    @Override
    protected void invoke(final PsiFile psiFile) {
        this.processElements((Processor<PsiElement>)(psiElement -> {
            try {
                if (psiElement instanceof OCStatement) {
                    OCChangeUtil.safeDeleteStatement((OCStatement)psiElement);
                    return true;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            OCChangeUtil.delete(psiElement);
            return true;
        }));
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
