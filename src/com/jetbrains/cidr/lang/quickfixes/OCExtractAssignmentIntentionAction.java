// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCLoopStatement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SmartPsiElementPointer;
import com.jetbrains.cidr.lang.psi.OCExpression;

public class OCExtractAssignmentIntentionAction extends OCPsiElementQuickFix<OCExpression>
{
    private SmartPsiElementPointer myLeftOperandPtr;
    
    public OCExtractAssignmentIntentionAction(final OCExpression ocExpression, final OCExpression ocExpression2) {
        super((PsiElement)ocExpression);
        this.myLeftOperandPtr = OCElementUtil.createPsiElementPointer(ocExpression2);
    }
    
    @Override
    protected String getTextInternal() {
        return "Extract assignment";
    }
    
    @NotNull
    public String getFamilyName() {
        String textInternal;
        try {
            textInternal = this.getTextInternal();
            if (textInternal == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCExtractAssignmentIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return textInternal;
    }
    
    @Override
    protected boolean isAvailable(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/quickfixes/OCExtractAssignmentIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType((PsiElement)ocExpression, (Class)OCStatement.class);
        Label_0076: {
            try {
                if (!(ocStatement instanceof OCLoopStatement)) {
                    break Label_0076;
                }
                final OCExtractAssignmentIntentionAction ocExtractAssignmentIntentionAction = this;
                final OCExpression ocExpression2 = ocExpression;
                final OCBlockStatement ocBlockStatement = ocExtractAssignmentIntentionAction.a(ocExpression2);
                if (ocBlockStatement != null) {
                    break Label_0076;
                }
                return false;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCExtractAssignmentIntentionAction ocExtractAssignmentIntentionAction = this;
                final OCExpression ocExpression2 = ocExpression;
                final OCBlockStatement ocBlockStatement = ocExtractAssignmentIntentionAction.a(ocExpression2);
                if (ocBlockStatement != null) {
                    return true;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @Override
    protected void invoke(final PsiFile psiFile, @NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/quickfixes/OCExtractAssignmentIntentionAction", "invoke"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType((PsiElement)ocExpression, (Class)OCStatement.class);
        try {
            if (ocStatement == null) {
                return;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCBlockStatement a = this.a(ocExpression);
        final OCExpressionStatement ocExpressionStatement = (OCExpressionStatement)OCElementFactory.statementFromText("1;", (PsiElement)psiFile);
        try {
            ocExpressionStatement.getExpression().replace((PsiElement)ocExpression);
            ocExpression.replace(OCElementUtil.getPsiElementByPointer((com.intellij.psi.SmartPsiElementPointer<PsiElement>)this.myLeftOperandPtr));
            if (a != null) {
                OCChangeUtil.add((PsiElement)a, ocExpressionStatement.copy());
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final OCStatement ensureParentIsBlockStatement = OCChangeUtil.ensureParentIsBlockStatement(ocStatement);
        OCChangeUtil.addBefore(ensureParentIsBlockStatement.getParent(), ocExpressionStatement, (PsiElement)ensureParentIsBlockStatement);
    }
    
    @Nullable
    private OCBlockStatement a(final OCExpression ocExpression) {
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType((PsiElement)ocExpression, (Class)OCStatement.class);
        if (ocStatement instanceof OCLoopStatement) {
            final OCStatement body = ((OCLoopStatement)ocStatement).getBody();
            try {
                if (body instanceof OCBlockStatement) {
                    return (OCBlockStatement)body;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return null;
        }
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
