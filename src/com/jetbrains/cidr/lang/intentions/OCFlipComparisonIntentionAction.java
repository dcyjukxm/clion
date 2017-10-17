// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCFlipComparisonIntentionAction extends PsiElementBaseIntentionAction
{
    public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCFlipComparisonIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCFlipComparisonIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        OCBinaryExpression ocBinaryExpression = (OCBinaryExpression)PsiTreeUtil.getParentOfType(psiElement, (Class)OCBinaryExpression.class, false);
        while (ocBinaryExpression != null) {
            final OCElementType flippedOperator = OCCodeInsightUtil.getFlippedOperator(ocBinaryExpression.getOperationSign());
            Label_0149: {
                Block_5: {
                    Label_0285: {
                        try {
                            if (flippedOperator == null) {
                                break Label_0285;
                            }
                            final OCBinaryExpression ocBinaryExpression2 = ocBinaryExpression;
                            final OCExpression ocExpression = ocBinaryExpression2.getRight();
                            if (ocExpression == null) {
                                return false;
                            }
                            break Label_0149;
                        }
                        catch (IncorrectOperationException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final OCBinaryExpression ocBinaryExpression2 = ocBinaryExpression;
                            final OCExpression ocExpression = ocBinaryExpression2.getRight();
                            if (ocExpression == null) {
                                return false;
                            }
                        }
                        catch (IncorrectOperationException ex4) {
                            throw a(ex4);
                        }
                        break Block_5;
                    }
                    ocBinaryExpression = (OCBinaryExpression)PsiTreeUtil.getParentOfType((PsiElement)ocBinaryExpression, (Class)OCBinaryExpression.class);
                    continue;
                }
                try {
                    if (!OCCodeInsightUtil.isValid((PsiElement)ocBinaryExpression.getLeft())) {
                        return false;
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
            }
            try {
                if (!OCCodeInsightUtil.isValid((PsiElement)ocBinaryExpression.getRight())) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
            final String text = ocBinaryExpression.getOperationSignNode().getText();
            final String name = flippedOperator.getName();
            try {
                if (name.equals(text)) {
                    this.setText("Flip " + text);
                    return true;
                }
            }
            catch (IncorrectOperationException ex7) {
                throw a(ex7);
            }
            this.setText("Flip " + text + " to " + name);
            return true;
        }
        return false;
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Flip Binary Operation";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCFlipComparisonIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCFlipComparisonIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCFlipComparisonIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        for (OCBinaryExpression ocBinaryExpression = OCElementUtil.getAdjacentParentOfType(psiElement, (Class<? extends OCBinaryExpression>)OCBinaryExpression.class); ocBinaryExpression != null; ocBinaryExpression = (OCBinaryExpression)PsiTreeUtil.getParentOfType((PsiElement)ocBinaryExpression, (Class)OCBinaryExpression.class)) {
            final OCElementType flippedOperator = OCCodeInsightUtil.getFlippedOperator(ocBinaryExpression.getOperationSign());
            if (flippedOperator != null) {
                final OCExpression right = ocBinaryExpression.getRight();
                try {
                    if (right == null) {
                        return;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                final OCBinaryExpression ocBinaryExpression2 = (OCBinaryExpression)OCElementFactory.expressionFromText("1" + flippedOperator.getName() + "2", psiElement);
                OCChangeUtil.replaceHandlingMacros((PsiElement)ocBinaryExpression2.getLeft(), (PsiElement)ocBinaryExpression.getRight());
                OCChangeUtil.replaceHandlingMacros((PsiElement)ocBinaryExpression2.getRight(), (PsiElement)ocBinaryExpression.getLeft());
                ocBinaryExpression.replace((PsiElement)ocBinaryExpression2);
                return;
            }
        }
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
