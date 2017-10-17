// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.intellij.openapi.util.TextRange;
import java.util.Iterator;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureUsageProcessor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;

public class OCMethodCallUsage extends OCUsageInfo<OCSendMessageExpression>
{
    public OCMethodCallUsage(@NotNull final PsiReference psiReference) {
        if (psiReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodCallUsage", "<init>"));
        }
        super(psiReference);
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo ocChangeInfo, @NotNull final OCSendMessageExpression ocSendMessageExpression, @NotNull final Project project) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeInfo", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodCallUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocSendMessageExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "call", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodCallUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodCallUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        if (ocChangeInfo.isParameterSetOrOrderChanged()) {
            OCImportSymbolFix.fixAllSymbolsRecursively(OCChangeUtil.replaceHandlingMacros((PsiElement)ocSendMessageExpression, OCChangeSignatureUsageProcessor.generateCall(ocSendMessageExpression.getReceiverExpression(), ocSendMessageExpression.getArgumentExpressions(), ocChangeInfo)));
        }
        else if (ocChangeInfo.isNameChanged()) {
            int n = 0;
            final Iterator<OCMessageArgument> iterator = ocSendMessageExpression.getArguments().iterator();
            while (iterator.hasNext()) {
                final PsiElement selectorIdentifier = iterator.next().getArgumentSelector().getSelectorIdentifier();
                if (selectorIdentifier != null) {
                    final String selector = ocChangeInfo.getNewParameters()[n++].getSelector();
                    try {
                        if (selector.isEmpty()) {
                            OCChangeUtil.delete(selectorIdentifier);
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    OCElementUtil.replaceWithIdentifier(selectorIdentifier, selector, (PsiElement)ocSendMessageExpression);
                }
            }
            final TextRange rangeWithMacros = ocSendMessageExpression.getRangeWithMacros();
            CodeStyleManager.getInstance(project).reformatRange((PsiElement)ocSendMessageExpression.getContainingFile(), rangeWithMacros.getStartOffset(), rangeWithMacros.getEndOffset(), true);
        }
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
