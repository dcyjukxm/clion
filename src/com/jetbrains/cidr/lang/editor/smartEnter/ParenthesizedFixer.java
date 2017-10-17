// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class ParenthesizedFixer extends OCFixer
{
    @Override
    public void apply(@NotNull final Editor editor, @NotNull final OCSmartEnterProcessor ocSmartEnterProcessor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/ParenthesizedFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocSmartEnterProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/editor/smartEnter/ParenthesizedFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/ParenthesizedFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        Label_0153: {
            try {
                if (psiElement instanceof OCParenthesizedExpression) {
                    break Label_0153;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCArgumentList;
                if (b) {
                    break Label_0153;
                }
                return;
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = psiElement2 instanceof OCArgumentList;
                if (!b) {
                    return;
                }
                if (OCElementUtil.getElementType(psiElement.getLastChild()) == OCTokenTypes.RPAR) {
                    return;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
        }
        final int completionPointAfterLPAR = OCFixer.getCompletionPointAfterLPAR(editor, psiElement);
        try {
            if (completionPointAfterLPAR >= 0) {
                editor.getDocument().insertString(completionPointAfterLPAR, (CharSequence)")");
            }
        }
        catch (IncorrectOperationException ex6) {
            throw a(ex6);
        }
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
