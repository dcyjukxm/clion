// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.SmartPsiElementPointer;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import java.util.List;
import com.intellij.psi.PsiElement;

public class OCChangeElementIntentionAction extends OCPsiElementsQuickFix
{
    private String myIntentionName;
    private String myFamilyName;
    
    public OCChangeElementIntentionAction(final PsiElement psiElement, final PsiElement psiElement2, final String s) {
        this(psiElement, psiElement2, s, s);
    }
    
    public OCChangeElementIntentionAction(final PsiElement psiElement, final PsiElement psiElement2, final String myIntentionName, final String myFamilyName) {
        super(new PsiElement[] { psiElement, psiElement2 });
        this.myIntentionName = myIntentionName;
        this.myFamilyName = myFamilyName;
    }
    
    public OCChangeElementIntentionAction(final PsiElement psiElement, final List<? extends PsiElement> list, final String s) {
        super(ContainerUtil.concat((List)Collections.singletonList(psiElement), (List)list));
        this.myIntentionName = s;
        this.myFamilyName = s;
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeElementIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myFamilyName;
    }
    
    @Override
    public boolean isAvailable() {
        return OCCodeInsightUtil.isValid(this.myElementPointers.get(0).getElement());
    }
    
    @Override
    protected void invoke(final PsiFile psiFile) {
        final List<? extends PsiElement> elements = this.getElements();
        final PsiElement psiElement = (PsiElement)elements.get(0);
        if (elements.size() == 2) {
            final PsiElement psiElement2 = (T)elements.get(1);
            Label_0060: {
                try {
                    if (!(psiElement instanceof OCExpression)) {
                        break Label_0060;
                    }
                    final PsiElement psiElement3 = psiElement2;
                    final boolean b = psiElement3 instanceof OCExpression;
                    if (b) {
                        break Label_0060;
                    }
                    break Label_0060;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement3 = psiElement2;
                    final boolean b = psiElement3 instanceof OCExpression;
                    if (b) {
                        OCParenthesesUtils.replaceExpressionAndAppendParentheses((OCExpression)psiElement, (OCExpression)psiElement2);
                        return;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            OCChangeUtil.replaceHandlingMacros(psiElement, psiElement2);
        }
        else {
            PsiElement addAfter = psiElement;
            for (int i = 1; i < elements.size(); ++i) {
                addAfter = OCChangeUtil.addAfter(addAfter.getParent(), (PsiElement)elements.get(i), addAfter);
            }
            OCChangeUtil.delete(psiElement);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
