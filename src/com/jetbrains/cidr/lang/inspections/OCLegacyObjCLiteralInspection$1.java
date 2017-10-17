// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.psi.PsiElement;
import com.intellij.codeInspection.GlobalInspectionContext;
import com.intellij.codeInspection.ProblemDescriptionsProcessor;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.quickfixes.OCConvertObjCLiteralIntentionAction;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.codeInspection.ProblemsHolder;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

class OCLegacyObjCLiteralInspection$1 extends OCVisitor {
    final /* synthetic */ ProblemsHolder val$holder;
    final /* synthetic */ boolean val$isOnTheFly;
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        final OCConvertObjCLiteralIntentionAction.Converter converter = OCConvertObjCLiteralIntentionAction.findConverter(ocSendMessageExpression);
        if (converter != null) {
            final OCConvertObjCLiteralIntentionAction ocConvertObjCLiteralIntentionAction = new OCConvertObjCLiteralIntentionAction(ocSendMessageExpression, converter);
            if (ocConvertObjCLiteralIntentionAction.isAvailable(ocSendMessageExpression)) {
                OCLegacyObjCLiteralInspection.this.registerProblem(this.val$holder, null, null, this.val$isOnTheFly, (PsiElement)ocSendMessageExpression, "Modern syntax can be used", "CIDR", ProblemHighlightType.GENERIC_ERROR_OR_WARNING, ocConvertObjCLiteralIntentionAction);
            }
        }
    }
}