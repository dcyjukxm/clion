// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.OCLoopStatement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.quickfixes.OCChangeElementIntentionAction;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.types.OCIntType;
import java.util.ArrayList;
import com.intellij.codeInsight.intention.IntentionAction;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCFile;

static final class OCDFAInspection$4 implements ElementFix {
    final /* synthetic */ OCFile val$file;
    final /* synthetic */ boolean val$constantCondition;
    
    @Override
    public List<IntentionAction> getFixes(final PsiElement psiElement) {
        final ArrayList<OCChangeElementIntentionAction> list = (ArrayList<OCChangeElementIntentionAction>)new ArrayList<IntentionAction>();
        final String value = OCIntType.getAppropriateBool(this.val$file).getValue(this.val$constantCondition, psiElement);
        list.add((IntentionAction)new OCChangeElementIntentionAction(psiElement, (PsiElement)OCElementFactory.expressionFromText(value, psiElement, false), "Simplify condition to '" + value + "'"));
        final OCElement ocElement = (OCElement)PsiTreeUtil.getParentOfType(psiElement, new Class[] { OCIfStatement.class, OCLoopStatement.class, OCBinaryExpression.class });
        Object o = null;
        if (ocElement instanceof OCStatement) {
            o = OCSimplifyInspection.simplifyStatementWithConstCondition(this.val$constantCondition, (OCStatement)ocElement);
        }
        else if (ocElement instanceof OCBinaryExpression) {
            o = OCSimplifyInspection.simplifyLogicExpression(this.val$constantCondition, (OCBinaryExpression)ocElement, psiElement);
        }
        if (o != null) {
            list.add(OCSimplifyInspection.getSimplifyFix((PsiElement)ocElement, (PsiElement)o));
        }
        return (List<IntentionAction>)list;
    }
}