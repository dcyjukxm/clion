// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class CaseColonFixer extends OCFixer
{
    @Override
    public void apply(@NotNull final Editor editor, @NotNull final OCSmartEnterProcessor ocSmartEnterProcessor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/CaseColonFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocSmartEnterProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/editor/smartEnter/CaseColonFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/CaseColonFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        if (psiElement instanceof OCCaseStatement) {
            final int offset = editor.getCaretModel().getOffset();
            final Document document = editor.getDocument();
            final TextRange rangeWithMacros = OCFixer.getRangeWithMacros(psiElement);
            try {
                if (document.getLineNumber(rangeWithMacros.getStartOffset()) != document.getLineNumber(offset)) {
                    if (!rangeWithMacros.contains(offset)) {
                        return;
                    }
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            Object o = ((OCCaseStatement)psiElement).getExpression();
            if (o == null) {
                o = psiElement.getFirstChild();
                if (OCElementUtil.getElementType((PsiElement)o) != OCTokenTypes.DEFAULT_KEYWORD) {
                    o = null;
                }
            }
            if (o != null) {
                final ASTNode firstColonInCase = findFirstColonInCase(psiElement);
                int n;
                if (firstColonInCase != null) {
                    n = OCFixer.getRangeWithMacros(firstColonInCase).getEndOffset();
                }
                else {
                    n = OCFixer.getRangeWithMacros((PsiElement)o).getEndOffset();
                    document.insertString(n, (CharSequence)":");
                    ++n;
                }
                editor.getCaretModel().moveToOffset(n);
            }
        }
    }
    
    @Nullable
    public static ASTNode findFirstColonInCase(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/CaseColonFixer", "findFirstColonInCase"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return psiElement.getNode().findChildByType((IElementType)OCTokenTypes.COLON);
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
