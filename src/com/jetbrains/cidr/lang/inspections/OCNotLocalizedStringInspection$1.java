// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.codeInspection.GlobalInspectionContext;
import com.intellij.codeInspection.ProblemDescriptionsProcessor;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.intellij.codeInspection.ProblemsHolder;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

class OCNotLocalizedStringInspection$1 extends OCVisitor {
    final /* synthetic */ boolean val$isOnTheFly;
    final /* synthetic */ ProblemsHolder val$holder;
    
    @Override
    public void visitLiteralExpression(final OCLiteralExpression ocLiteralExpression) {
        if ((!this.val$isOnTheFly || (ApplicationManager.getApplication().isUnitTestMode() && ocLiteralExpression.getUnescapedLiteralText().equals("NLS"))) && ocLiteralExpression.getReferences().length == 0 && !OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocLiteralExpression) && ocLiteralExpression.getResolvedType().isPointerToStringCompatible()) {
            final OCMacroCall ocMacroCall = (OCMacroCall)PsiTreeUtil.getParentOfType((PsiElement)ocLiteralExpression, (Class)OCMacroCall.class);
            if ((ocMacroCall == null || ocMacroCall.getMacroReferenceElement() == null || !ocMacroCall.getMacroReferenceElement().getName().startsWith("NSLocalizedString")) && !ocLiteralExpression.getUnescapedLiteralText().isEmpty()) {
                OCNotLocalizedStringInspection.this.registerProblem(this.val$holder, null, null, this.val$isOnTheFly, (PsiElement)ocLiteralExpression, "Non-localized string: " + ocLiteralExpression.getRawLiteralText(), "CIDR", ProblemHighlightType.INFORMATION, new IntentionAction[0]);
            }
        }
    }
}