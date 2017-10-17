// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCStatement;

public class Converter
{
    private OCStatement myThenStatement;
    private OCStatement myElseStatement;
    private OCStatement myOriginalElseStatement;
    private OCExpression myThenExpression;
    private OCExpression myElseExpression;
    private OCExpression myCondition;
    private OCIfStatement myStatement;
    
    public Converter(final PsiElement psiElement) {
        final OCIfStatement myStatement = (OCIfStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCIfStatement.class, false);
        if (myStatement == null) {
            return;
        }
        this.myStatement = myStatement;
        final OCDeclarationOrExpression condition = myStatement.getCondition();
        if (condition == null || condition.getExpression() == null) {
            return;
        }
        this.myCondition = condition.getExpression();
        this.myThenStatement = myStatement.getThenBranch();
        this.myElseStatement = myStatement.getElseBranch();
        if (this.myElseStatement == null) {
            final PsiElement skipSiblingsForward = PsiTreeUtil.skipSiblingsForward((PsiElement)myStatement, new Class[] { PsiWhiteSpace.class, PsiComment.class });
            if (skipSiblingsForward instanceof OCReturnStatement) {
                this.myElseStatement = (OCStatement)skipSiblingsForward;
            }
        }
        this.myOriginalElseStatement = this.myElseStatement;
        while (this.myThenStatement instanceof OCBlockStatement && ((OCBlockStatement)this.myThenStatement).getStatements().size() == 1) {
            this.myThenStatement = ((OCBlockStatement)this.myThenStatement).getStatements().get(0);
        }
        while (this.myElseStatement instanceof OCBlockStatement && ((OCBlockStatement)this.myElseStatement).getStatements().size() == 1) {
            this.myElseStatement = ((OCBlockStatement)this.myElseStatement).getStatements().get(0);
        }
        if (this.myThenStatement == null || this.myElseStatement == null) {
            return;
        }
        final Pair<PsiElement, PsiElement> elementsDiff = OCElementUtil.getElementsDiff((PsiElement)this.myThenStatement, (PsiElement)this.myElseStatement);
        if (elementsDiff == null) {
            return;
        }
        PsiElement parent;
        for (parent = (PsiElement)elementsDiff.first; parent != null && !(parent instanceof OCExpression) && parent != this.myThenStatement; parent = parent.getParent()) {}
        PsiElement parent2;
        for (parent2 = (PsiElement)elementsDiff.second; parent2 != null && !(parent2 instanceof OCExpression) && parent2 != this.myElseStatement; parent2 = parent2.getParent()) {}
        if (parent instanceof OCExpression && parent2 instanceof OCExpression) {
            this.myThenExpression = (OCExpression)parent;
            this.myElseExpression = (OCExpression)parent2;
        }
    }
    
    public OCExpression getCondition() {
        return this.myCondition;
    }
    
    public OCExpression getThenExpression() {
        return this.myThenExpression;
    }
    
    public OCExpression getElseExpression() {
        return this.myElseExpression;
    }
    
    public boolean isAvailable() {
        if (this.myThenExpression == null || this.myElseExpression == null) {
            return false;
        }
        if (!OCCodeInsightUtil.isValid((PsiElement)this.myThenExpression)) {
            return false;
        }
        if (!OCCodeInsightUtil.isValid((PsiElement)this.myThenStatement)) {
            return false;
        }
        if (!OCCodeInsightUtil.isValid((PsiElement)this.myElseExpression)) {
            return false;
        }
        if (!OCCodeInsightUtil.isValid((PsiElement)this.myElseStatement)) {
            return false;
        }
        if (!OCCodeInsightUtil.isValid((PsiElement)this.myCondition)) {
            return false;
        }
        final OCType leastCommonType = this.myThenExpression.getResolvedType().getGuessedType().getLeastCommonType(this.myElseExpression.getResolvedType().getGuessedType(), (PsiElement)this.myThenExpression);
        return !leastCommonType.isUnknown() && !leastCommonType.isVoid();
    }
    
    public void invoke() {
        OCParenthesesUtils.replaceExpressionAndRemoveAppendParentheses(this.myThenExpression, OCConvertIfToTernaryIntentionAction.this.getNewExpression(this));
        OCChangeUtil.replaceHandlingMacros((PsiElement)this.myStatement, (PsiElement)this.myThenStatement);
        OCChangeUtil.delete((PsiElement)this.myOriginalElseStatement);
    }
}
