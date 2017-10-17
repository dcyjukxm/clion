// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCCatchSection;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class ParameterListFixer extends OCFixer
{
    @Override
    public void apply(@NotNull final Editor editor, @NotNull final OCSmartEnterProcessor ocSmartEnterProcessor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/ParameterListFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocSmartEnterProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/editor/smartEnter/ParameterListFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/ParameterListFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        try {
            if (!(psiElement instanceof OCParameterList) || psiElement.getParent() instanceof OCCatchSection) {
                return;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final OCParameterList list = (OCParameterList)psiElement;
        if (OCElementUtil.getElementType(list.getLastChild()) != OCTokenTypes.RPAR) {
            int n = OCFixer.getCompletionPointAfterLPAR(editor, psiElement);
            if (n >= 0) {
                final PsiElement lastElement = list.getLastElement();
                if (lastElement != null) {
                    n = Math.min(n, OCFixer.getRangeWithMacros(lastElement).getEndOffset());
                }
                editor.getDocument().insertString(n, (CharSequence)")");
            }
        }
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
