// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class IfConditionFixer extends OCFixer
{
    @Override
    public void apply(@NotNull final Editor editor, @NotNull final OCSmartEnterProcessor ocSmartEnterProcessor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/IfConditionFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocSmartEnterProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/editor/smartEnter/IfConditionFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/IfConditionFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        Label_0161: {
            try {
                if (!(psiElement instanceof OCIfStatement)) {
                    return;
                }
                final PsiElement psiElement2 = psiElement;
                final OCElementType ocElementType = OCTokenTypes.IF_KEYWORD;
                final boolean b = OCFixer.hasMacroBasedStatement(psiElement2, ocElementType);
                if (b) {
                    return;
                }
                break Label_0161;
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final OCElementType ocElementType = OCTokenTypes.IF_KEYWORD;
                final boolean b = OCFixer.hasMacroBasedStatement(psiElement2, ocElementType);
                if (b) {
                    return;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
        }
        final Document document = editor.getDocument();
        final OCIfStatement ocIfStatement = (OCIfStatement)psiElement;
        final ASTNode rParenth = ocIfStatement.getRParenth();
        final ASTNode lParenth = ocIfStatement.getLParenth();
        final OCDeclarationOrExpression condition = ocIfStatement.getCondition();
        if (lParenth == null) {
            int n = document.getLineEndOffset(document.getLineNumber(OCFixer.getRangeWithMacros((PsiElement)ocIfStatement).getStartOffset()));
            final OCStatement thenBranch = ocIfStatement.getThenBranch();
            if (thenBranch != null) {
                n = Math.min(n, OCFixer.getRangeWithMacros((PsiElement)thenBranch).getStartOffset());
            }
            document.replaceString(OCFixer.getRangeWithMacros((PsiElement)ocIfStatement).getStartOffset(), Math.min(n, OCFixer.getRangeWithMacros((PsiElement)ocIfStatement).getEndOffset()), (CharSequence)"if ()");
            ocSmartEnterProcessor.registerUnresolvedError(OCFixer.getRangeWithMacros((PsiElement)ocIfStatement).getStartOffset() + "if (".length());
        }
        else {
            OCFixer.fixConditionIfNeed(editor, ocSmartEnterProcessor, lParenth, (PsiElement)condition, rParenth);
        }
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
