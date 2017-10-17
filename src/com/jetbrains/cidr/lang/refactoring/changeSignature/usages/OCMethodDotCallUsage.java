// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import java.util.List;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureUsageProcessor;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;

public class OCMethodDotCallUsage extends OCUsageInfo<OCQualifiedExpression>
{
    public OCMethodDotCallUsage(@NotNull final PsiReference psiReference) {
        if (psiReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodDotCallUsage", "<init>"));
        }
        super(psiReference);
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo ocChangeInfo, @NotNull final OCQualifiedExpression ocQualifiedExpression, @NotNull final Project project) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeInfo", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodDotCallUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocQualifiedExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodDotCallUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCMethodDotCallUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        if (ocChangeInfo.isParameterSetOrOrderChanged()) {
            Object o = ocQualifiedExpression;
            final OCExpression topmostParenthesized = OCParenthesesUtils.topmostParenthesized(ocQualifiedExpression);
            final PsiElement parent = topmostParenthesized.getParent();
            List<OCExpression> list = null;
            Label_0217: {
                Label_0212: {
                    try {
                        if (!(parent instanceof OCAssignmentExpression) || ((OCAssignmentExpression)parent).getReceiverExpression() != topmostParenthesized) {
                            break Label_0212;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    list = Collections.singletonList(((OCAssignmentExpression)parent).getSourceExpression());
                    o = parent;
                    break Label_0217;
                }
                list = Collections.emptyList();
            }
            OCImportSymbolFix.fixAllSymbolsRecursively(OCChangeUtil.replaceHandlingMacros((PsiElement)o, OCChangeSignatureUsageProcessor.generateCall(ocQualifiedExpression.getQualifier(), list, ocChangeInfo)));
        }
        else if (ocChangeInfo.isNameChanged()) {
            final String newName = ocChangeInfo.getNewName();
            final String objCGetterFromSetter = OCNameSuggester.getObjCGetterFromSetter(newName);
            String name = null;
            Label_0285: {
                try {
                    if (objCGetterFromSetter != null) {
                        name = objCGetterFromSetter;
                        break Label_0285;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                name = newName;
            }
            ocQualifiedExpression.setName(name);
        }
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
