// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class OCSendMessageToObjectIntentionAction extends OCPsiElementQuickFix<PsiElement>
{
    private String myMessage;
    
    public OCSendMessageToObjectIntentionAction(final PsiElement psiElement, final String myMessage) {
        super(psiElement);
        this.myMessage = myMessage;
    }
    
    @Override
    protected String getTextInternal() {
        return "Send '" + this.myMessage + "' message";
    }
    
    @NotNull
    public String getFamilyName() {
        String textInternal;
        try {
            textInternal = this.getTextInternal();
            if (textInternal == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSendMessageToObjectIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return textInternal;
    }
    
    @Override
    protected void invoke(final PsiFile psiFile, @NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "object", "com/jetbrains/cidr/lang/quickfixes/OCSendMessageToObjectIntentionAction", "invoke"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)OCElementFactory.expressionFromText("[x " + this.myMessage + "]", (PsiElement)psiFile);
        ocSendMessageExpression.getReceiverExpression().replace(psiElement);
        OCChangeUtil.replaceHandlingMacros(psiElement, (PsiElement)ocSendMessageExpression);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
