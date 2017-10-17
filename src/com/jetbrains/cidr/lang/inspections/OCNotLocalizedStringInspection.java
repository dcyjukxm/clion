// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.quickfixes.OCSuppressClangDiagnosticIntentionAction;
import com.jetbrains.cidr.lang.daemon.OCSuppressionGroup;
import com.intellij.codeInspection.SuppressQuickFix;
import org.jetbrains.annotations.Nullable;
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
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nls;

public class OCNotLocalizedStringInspection extends OCInspections.GeneralObjC
{
    @Nls
    @NotNull
    @Override
    public String getDisplayName() {
        String s;
        try {
            s = "Non-localized string";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCNotLocalizedStringInspection", "getDisplayName"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/inspections/OCNotLocalizedStringInspection", "buildVisitor"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        OCVisitor ocVisitor;
        try {
            ocVisitor = new OCVisitor() {
                @Override
                public void visitLiteralExpression(final OCLiteralExpression ocLiteralExpression) {
                    if ((!b || (ApplicationManager.getApplication().isUnitTestMode() && ocLiteralExpression.getUnescapedLiteralText().equals("NLS"))) && ocLiteralExpression.getReferences().length == 0 && !OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocLiteralExpression) && ocLiteralExpression.getResolvedType().isPointerToStringCompatible()) {
                        final OCMacroCall ocMacroCall = (OCMacroCall)PsiTreeUtil.getParentOfType((PsiElement)ocLiteralExpression, (Class)OCMacroCall.class);
                        if ((ocMacroCall == null || ocMacroCall.getMacroReferenceElement() == null || !ocMacroCall.getMacroReferenceElement().getName().startsWith("NSLocalizedString")) && !ocLiteralExpression.getUnescapedLiteralText().isEmpty()) {
                            OCNotLocalizedStringInspection.this.registerProblem(problemsHolder, null, null, b, (PsiElement)ocLiteralExpression, "Non-localized string: " + ocLiteralExpression.getRawLiteralText(), "CIDR", ProblemHighlightType.INFORMATION, new IntentionAction[0]);
                        }
                    }
                }
            };
            if (ocVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCNotLocalizedStringInspection", "buildVisitor"));
            }
        }
        catch (IllegalStateException ex2) {
            throw d(ex2);
        }
        return ocVisitor;
    }
    
    public boolean isSuppressedFor(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/inspections/OCNotLocalizedStringInspection", "isSuppressedFor"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return false;
    }
    
    @NotNull
    public SuppressQuickFix[] getBatchSuppressActions(@Nullable final PsiElement psiElement) {
        final OCSuppressionGroup ocSuppressionGroup = new OCSuppressionGroup("ide", this.getClass().getSimpleName());
        SuppressQuickFix[] array;
        try {
            array = new SuppressQuickFix[] { new OCSuppressClangDiagnosticIntentionAction.SuppressFixForStatement(ocSuppressionGroup), new OCSuppressClangDiagnosticIntentionAction.SuppressFixForCallable(ocSuppressionGroup), new OCSuppressClangDiagnosticIntentionAction.SuppressFixForFile(ocSuppressionGroup) };
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCNotLocalizedStringInspection", "getBatchSuppressActions"));
            }
        }
        catch (IllegalStateException ex) {
            throw d(ex);
        }
        return array;
    }
    
    private static IllegalStateException d(final IllegalStateException ex) {
        return ex;
    }
}
