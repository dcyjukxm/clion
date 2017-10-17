// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import java.util.Iterator;
import java.util.List;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.ide.util.EditorHelper;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureUsageProcessor;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class OCGeneratedCallUsage extends OCUsageWithContext
{
    public OCGeneratedCallUsage(@NotNull final PsiElement psiElement) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedCallUsage", "<init>"));
        }
        super(psiElement);
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo ocChangeInfo, @NotNull final PsiElement psiElement, @NotNull final Project project) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeInfo", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedCallUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedCallUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCGeneratedCallUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final String callString = ocChangeInfo.getGenerated().getCallString();
        Object o;
        if (callString.endsWith(";")) {
            o = OCElementFactory.statementFromText(callString, ocChangeInfo.getContext(), true);
        }
        else {
            o = OCElementFactory.expressionFromText(callString, ocChangeInfo.getContext(), true);
        }
        final List<PsiElement> beforeCallStatements = ocChangeInfo.getGenerated().getBeforeCallStatements();
        final List<PsiElement> afterCallStatements = ocChangeInfo.getGenerated().getAfterCallStatements();
        Label_0264: {
            try {
                if (this.getParent() instanceof OCBlockStatement || beforeCallStatements.size() + afterCallStatements.size() <= 0) {
                    break Label_0264;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            this.setElement(((OCBlockStatement)this.addElement((PsiElement)OCElementFactory.statementFromText("{}", ocChangeInfo.getContext()))).getOpeningBrace());
        }
        final Iterator<PsiElement> iterator = beforeCallStatements.iterator();
        while (iterator.hasNext()) {
            this.addElement(iterator.next());
        }
        Object addElement = this.addElement((PsiElement)o);
        final Iterator<PsiElement> iterator2 = afterCallStatements.iterator();
        while (iterator2.hasNext()) {
            this.addElement(iterator2.next());
        }
        final OCCallExpression a = a((PsiElement)addElement);
        final PsiElement replaceHandlingMacros = OCChangeUtil.replaceHandlingMacros((PsiElement)a, OCChangeSignatureUsageProcessor.generateCall(OCElementFactory.expressionFromText("self", ocChangeInfo.getContext()), a.getArguments(), ocChangeInfo));
        if (addElement == a) {
            addElement = replaceHandlingMacros;
        }
        Label_0469: {
            try {
                if (!(replaceHandlingMacros instanceof OCCallExpression) || !(((OCCallExpression)replaceHandlingMacros).getFunctionReferenceExpression() instanceof OCBlockExpression)) {
                    break Label_0469;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            final OCBlockExpression newMethod = (OCBlockExpression)((OCCallExpression)replaceHandlingMacros).getFunctionReferenceExpression();
            ocChangeInfo.setNewMethod(newMethod);
            OCBindUtil.decodeContextInfo((PsiElement)newMethod);
            OCBindUtil.removeRedundantQualifiers((PsiElement)newMethod);
        }
        if (!ocChangeInfo.getGenerated().isSelectMethod()) {
            OCCodeInsightUtil.selectRange(EditorHelper.openInEditor((PsiElement)addElement), ((PsiElement)addElement).getTextOffset(), ((PsiElement)addElement).getTextOffset() + ((PsiElement)addElement).getTextLength());
        }
        return true;
    }
    
    @Nullable
    private static OCCallExpression a(final PsiElement psiElement) {
        try {
            if (psiElement instanceof OCCallExpression) {
                return (OCCallExpression)psiElement;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (psiElement instanceof OCExpressionStatement) {
            final OCExpression expression = ((OCExpressionStatement)psiElement).getExpression();
            try {
                if (expression instanceof OCCallExpression) {
                    return (OCCallExpression)expression;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                if (expression instanceof OCAssignmentExpression) {
                    return (OCCallExpression)((OCAssignmentExpression)expression).getSourceExpression();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                assert false;
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        else {
            try {
                if (psiElement instanceof OCReturnStatement) {
                    return (OCCallExpression)((OCReturnStatement)psiElement).getExpression();
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            if (psiElement instanceof OCDeclarationStatement) {
                return (OCCallExpression)((OCDeclarationStatement)psiElement).getDeclaration().getDeclarators().get(0).getInitializer();
            }
            try {
                assert false;
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
        }
        return null;
    }
    
    @Override
    public int getUsageRank() {
        return -2;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCGeneratedCallUsage.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
