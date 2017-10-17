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
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;

public class OCLegacyObjCLiteralInspection extends OCInspections.GeneralObjC
{
    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        String s;
        try {
            s = "Modern syntax can be used";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCLegacyObjCLiteralInspection", "getDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return s;
    }
    
    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder problemsHolder, final boolean b) {
        try {
            if (problemsHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/inspections/OCLegacyObjCLiteralInspection", "buildVisitor"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        OCVisitor ocVisitor;
        try {
            ocVisitor = new OCVisitor() {
                @Override
                public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
                    final OCConvertObjCLiteralIntentionAction.Converter converter = OCConvertObjCLiteralIntentionAction.findConverter(ocSendMessageExpression);
                    if (converter != null) {
                        final OCConvertObjCLiteralIntentionAction ocConvertObjCLiteralIntentionAction = new OCConvertObjCLiteralIntentionAction(ocSendMessageExpression, converter);
                        if (ocConvertObjCLiteralIntentionAction.isAvailable(ocSendMessageExpression)) {
                            OCLegacyObjCLiteralInspection.this.registerProblem(problemsHolder, null, null, b, (PsiElement)ocSendMessageExpression, "Modern syntax can be used", "CIDR", ProblemHighlightType.GENERIC_ERROR_OR_WARNING, ocConvertObjCLiteralIntentionAction);
                        }
                    }
                }
            };
            if (ocVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCLegacyObjCLiteralInspection", "buildVisitor"));
            }
        }
        catch (IllegalStateException ex2) {
            throw d(ex2);
        }
        return ocVisitor;
    }
    
    private static IllegalStateException d(final IllegalStateException ex) {
        return ex;
    }
}
