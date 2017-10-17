// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import java.util.Iterator;
import java.util.List;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.inspections.OCSimplifyInspection;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public abstract class OCConvertToIfIntentionActionBase extends PsiElementBaseIntentionAction
{
    protected void invoke(final OCExpression ocExpression, OCExpression diveIntoParentheses, OCExpression diveIntoParentheses2, OCExpression diveIntoParentheses3) {
        diveIntoParentheses = OCParenthesesUtils.diveIntoParentheses(diveIntoParentheses);
        diveIntoParentheses2 = OCParenthesesUtils.diveIntoParentheses(diveIntoParentheses2);
        diveIntoParentheses3 = OCParenthesesUtils.diveIntoParentheses(diveIntoParentheses3);
        final PsiElement parentOfType = PsiTreeUtil.getParentOfType((PsiElement)ocExpression, new Class[] { OCStatement.class, OCDeclarator.class });
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType(parentOfType, (Class)OCStatement.class, false);
        if (ocExpression == null || ocStatement == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder("if (");
        final OCExpression topmostParenthesized = OCParenthesesUtils.topmostParenthesized(ocExpression);
        if (diveIntoParentheses != null) {
            sb.append(diveIntoParentheses.getTextWithMacros());
        }
        sb.append(") {\n");
        if (parentOfType instanceof OCDeclarator) {
            final OCDeclarator ocDeclarator = (OCDeclarator)parentOfType;
            final OCExpression initializer = ocDeclarator.getInitializer();
            sb.append(ocDeclarator.getName()).append("=");
            a(sb, (PsiElement)initializer, topmostParenthesized, diveIntoParentheses2);
            sb.append(";\n}\nelse {\n").append(ocDeclarator.getName()).append("=");
            a(sb, (PsiElement)initializer, topmostParenthesized, diveIntoParentheses3);
            sb.append(";\n}");
            OCChangeUtil.delete((PsiElement)initializer);
        }
        else {
            a(sb, (PsiElement)ocStatement, topmostParenthesized, diveIntoParentheses2);
            sb.append("}\nelse {\n");
            a(sb, (PsiElement)ocStatement, topmostParenthesized, diveIntoParentheses3);
            sb.append("\n}");
        }
        final OCIfStatement ocIfStatement = (OCIfStatement)OCElementFactory.statementFromText(sb.toString(), (PsiElement)ocExpression, true);
        final PsiElement psiElement = (parentOfType instanceof OCDeclarator) ? OCChangeUtil.addAfter(ocStatement.getParent(), (PsiElement)ocIfStatement, (PsiElement)ocStatement) : OCChangeUtil.replaceHandlingMacros((PsiElement)ocStatement, (PsiElement)ocIfStatement);
        if (psiElement instanceof OCIfStatement) {
            final PsiDocumentManager instance = PsiDocumentManager.getInstance(psiElement.getProject());
            final Document document = instance.getDocument(psiElement.getContainingFile());
            if (document != null) {
                instance.doPostponedOperationsAndUnblockDocument(document);
                OCSimplifyInspection.simplify((PsiElement)((OCIfStatement)psiElement).getThenBranch());
                OCSimplifyInspection.simplify((PsiElement)((OCIfStatement)psiElement).getElseBranch());
            }
        }
    }
    
    private static void a(final StringBuilder sb, final PsiElement psiElement, final OCExpression ocExpression, final OCExpression ocExpression2) {
        if (psiElement == ocExpression) {
            if (OCParenthesesUtils.isParenthesesNeededInReplacing(ocExpression, ocExpression2)) {
                sb.append('(').append(OCElementUtil.getTextWithMacros((PsiElement)ocExpression2)).append(')');
            }
            else {
                sb.append(OCElementUtil.getTextWithMacros((PsiElement)ocExpression2));
            }
        }
        else {
            final List<PsiElement> allChildren = OCElementUtil.getAllChildren(psiElement);
            if (allChildren.size() > 0) {
                final Iterator<PsiElement> iterator = allChildren.iterator();
                while (iterator.hasNext()) {
                    a(sb, iterator.next(), ocExpression, ocExpression2);
                }
            }
            else if (!OCElementUtil.isPartOfMacroSubstitution(psiElement)) {
                sb.append(OCElementUtil.getTextWithMacros(psiElement));
            }
        }
    }
}
