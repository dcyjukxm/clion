// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCParameterInfo;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCAddParametersToInitWithIntentionAction$3 extends OCRecursiveVisitor {
    final /* synthetic */ OCCallable val$callable;
    final /* synthetic */ int val$finalIndex;
    final /* synthetic */ OCParameterInfo val$newParameter;
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        if (ocSendMessageExpression.getMessageSelector().equals(((OCMethod)this.val$callable).getSelector())) {
            final List<OCExpression> argumentExpressions = ocSendMessageExpression.getArgumentExpressions();
            if (argumentExpressions.size() > this.val$finalIndex) {
                OCChangeUtil.replaceHandlingMacros((PsiElement)argumentExpressions.get(this.val$finalIndex), (PsiElement)OCElementFactory.expressionFromText(this.val$newParameter.getName(), (PsiElement)ocSendMessageExpression));
            }
        }
    }
}