// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureUsageProcessor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;

public class OCBlockDefinitionUsage extends OCCallableDefinitionUsage<OCBlockExpression>
{
    public OCBlockDefinitionUsage(@NotNull final OCBlockExpression ocBlockExpression) {
        if (ocBlockExpression == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCBlockDefinitionUsage", "<init>"));
        }
        super(ocBlockExpression, false);
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo ocChangeInfo, @NotNull final OCBlockExpression ocBlockExpression, @NotNull final Project project) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeInfo", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCBlockDefinitionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (ocBlockExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "block", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCBlockDefinitionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCBlockDefinitionUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (ocChangeInfo.isParameterSetOrOrderChanged()) {
                return super.processUsage(ocChangeInfo, ocBlockExpression, project);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        Label_0172: {
            try {
                if (ocChangeInfo.isNameChanged()) {
                    break Label_0172;
                }
                final OCChangeInfo ocChangeInfo2 = ocChangeInfo;
                final boolean b = ocChangeInfo2.isParameterTypesChanged();
                if (b) {
                    break Label_0172;
                }
                break Label_0172;
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
            try {
                final OCChangeInfo ocChangeInfo2 = ocChangeInfo;
                final boolean b = ocChangeInfo2.isParameterTypesChanged();
                if (b) {
                    OCChangeSignatureUsageProcessor.processParameters(ocChangeInfo, ocBlockExpression, ocBlockExpression.getParameterList(), project, false);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw c(ex6);
            }
        }
        if (ocChangeInfo.isReturnTypeChanged()) {
            final OCTypeElement returnTypeElement = ocBlockExpression.getReturnTypeElement();
            try {
                if (returnTypeElement != null) {
                    OCChangeUtil.replaceHandlingMacros((PsiElement)returnTypeElement, (PsiElement)OCElementFactory.typeElementFromText(ocChangeInfo.getNewReturnType(), (PsiElement)ocBlockExpression));
                    return true;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw c(ex7);
            }
            final PsiElement replaceHandlingMacros = OCChangeUtil.replaceHandlingMacros((PsiElement)ocBlockExpression, (PsiElement)OCChangeSignatureUsageProcessor.generateMethodDefinition(ocChangeInfo, ocBlockExpression, true, true, false));
            ocChangeInfo.setNewMethod((OCCallable)replaceHandlingMacros);
            ocChangeInfo.addNewMethod((OCCallable)replaceHandlingMacros);
        }
        return true;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
