// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.intellij.psi.AbstractElementManipulator;

public class OCLiteralExpressionManipulator extends AbstractElementManipulator<OCLiteralExpression>
{
    public OCLiteralExpression handleContentChange(@NotNull final OCLiteralExpression ocLiteralExpression, @NotNull final TextRange textRange, final String s) throws IncorrectOperationException {
        try {
            if (ocLiteralExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionManipulator", "handleContentChange"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionManipulator", "handleContentChange"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final ASTNode childByType = ocLiteralExpression.getNode().findChildByType((IElementType)OCTokenTypes.STRING_LITERAL);
        try {
            if (childByType == null) {
                throw new IncorrectOperationException("Content change of non-string literals is not supported");
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final String text = childByType.getText();
        ocLiteralExpression.getNode().addLeaf((IElementType)OCTokenTypes.STRING_LITERAL, (CharSequence)(text.substring(0, textRange.getStartOffset() - 1) + s + text.substring(textRange.getEndOffset() - 1)), (ASTNode)null);
        ocLiteralExpression.getNode().removeChild(childByType);
        return ocLiteralExpression;
    }
    
    @NotNull
    public TextRange getRangeInElement(@NotNull final OCLiteralExpression ocLiteralExpression) {
        try {
            if (ocLiteralExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionManipulator", "getRangeInElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final ASTNode childByType = ocLiteralExpression.getNode().findChildByType((IElementType)OCTokenTypes.STRING_LITERAL);
        TextRange textRange2 = null;
        Label_0156: {
            if (childByType != null) {
                final int startOffsetInParent = childByType.getPsi().getStartOffsetInParent();
                final int n = startOffsetInParent + 1;
                final int n2 = startOffsetInParent + childByType.getTextLength() - 1;
                TextRange textRange = null;
                Label_0121: {
                    try {
                        if (n > n2) {
                            break Label_0156;
                        }
                        final int n3 = n;
                        final int n4 = n2;
                        textRange = new TextRange(n3, n4);
                        if (textRange == null) {
                            break Label_0121;
                        }
                        return textRange;
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final int n3 = n;
                        final int n4 = n2;
                        textRange = new TextRange(n3, n4);
                        if (textRange == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionManipulator", "getRangeInElement"));
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                }
                return textRange;
            }
            try {
                textRange2 = ocLiteralExpression.getTextRange();
                if (textRange2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionManipulator", "getRangeInElement"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return textRange2;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
