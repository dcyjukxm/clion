// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.jetbrains.cidr.lang.refactoring.changeSignature.OCParameterInfo;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;

public class OCSelectorExpressionUsage extends OCUsageInfo<OCSelectorExpression>
{
    public OCSelectorExpressionUsage(@NotNull final PsiReference psiReference) {
        if (psiReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCSelectorExpressionUsage", "<init>"));
        }
        super(psiReference);
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo ocChangeInfo, @NotNull final OCSelectorExpression ocSelectorExpression, @NotNull final Project project) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeInfo", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCSelectorExpressionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocSelectorExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCSelectorExpressionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCSelectorExpressionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!ocChangeInfo.willBeMethod()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final StringBuilder sb = new StringBuilder("@selector(");
        for (final OCParameterInfo ocParameterInfo : ocChangeInfo.getNewParameters()) {
            try {
                sb.append(ocParameterInfo.getSelector());
                if (!ocParameterInfo.getName().isEmpty()) {
                    sb.append(':');
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        sb.append(')');
        OCChangeUtil.replaceHandlingMacros((PsiElement)ocSelectorExpression, (PsiElement)OCElementFactory.expressionFromText(sb.toString(), (PsiElement)ocSelectorExpression));
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
