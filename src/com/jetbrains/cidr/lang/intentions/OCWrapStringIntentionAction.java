// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCWrapStringIntentionAction extends PsiElementBaseIntentionAction
{
    public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCWrapStringIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCWrapStringIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCLiteralExpression a = a(editor, psiElement.getContainingFile());
        Label_0123: {
            try {
                if (!OCCodeInsightUtil.isValid((PsiElement)a)) {
                    return false;
                }
                final Object o = a;
                final String s = OCElementUtil.getStringLiteral((PsiElement)o);
                if (s != null) {
                    break Label_0123;
                }
                return false;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final Object o = a;
                final String s = OCElementUtil.getStringLiteral((PsiElement)o);
                if (s != null) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCWrapStringIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Wrap with stringWithFormat";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCWrapStringIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Nullable
    private static OCLiteralExpression a(final Editor editor, final PsiFile psiFile) {
        return OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), (Class<? extends OCLiteralExpression>)OCLiteralExpression.class);
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCWrapStringIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCWrapStringIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final PsiFile containingFile = psiElement.getContainingFile();
        final OCLiteralExpression a = a(editor, containingFile);
        try {
            if (a == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)OCElementFactory.expressionFromText("[NSString stringWithFormat: 0]", (PsiElement)containingFile);
        ocSendMessageExpression.getArguments().get(0).getArgumentExpression().replace((PsiElement)a);
        final OCSendMessageExpression ocSendMessageExpression2 = (OCSendMessageExpression)a.replace((PsiElement)ocSendMessageExpression);
        try {
            if (editor != null) {
                editor.getCaretModel().moveToOffset(ocSendMessageExpression2.getTextRange().getEndOffset());
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
