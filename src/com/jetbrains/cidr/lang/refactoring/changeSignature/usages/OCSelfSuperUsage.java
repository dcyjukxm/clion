// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;

public class OCSelfSuperUsage extends OCUsageInfo<OCReferenceExpression>
{
    public OCSelfSuperUsage(@NotNull final OCReferenceExpression ocReferenceExpression) {
        if (ocReferenceExpression == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCSelfSuperUsage", "<init>"));
        }
        super((PsiElement)ocReferenceExpression);
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo ocChangeInfo, @NotNull final OCReferenceExpression ocReferenceExpression, @NotNull final Project project) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeInfo", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCSelfSuperUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocReferenceExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCSelfSuperUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCSelfSuperUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (ocReferenceExpression == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        if (ocChangeInfo.getSelfParameterName() != null) {
            final OCReferenceElement referenceElement = ocReferenceExpression.getReferenceElement();
            try {
                if (referenceElement != null) {
                    referenceElement.setNameOfIdentifier(ocChangeInfo.getSelfParameterName());
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
