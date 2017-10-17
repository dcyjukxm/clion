// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCDoWhileStatement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class DoWhileConditionFixer extends OCFixer
{
    @Override
    public void apply(@NotNull final Editor editor, @NotNull final OCSmartEnterProcessor ocSmartEnterProcessor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/DoWhileConditionFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocSmartEnterProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/editor/smartEnter/DoWhileConditionFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/DoWhileConditionFixer", "apply"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        try {
            if (!applicable(psiElement)) {
                return;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final OCDoWhileStatement ocDoWhileStatement = (OCDoWhileStatement)psiElement;
        final OCStatement body = ocDoWhileStatement.getBody();
        try {
            if (OCFixer.fixBlockIfNeed(editor, (PsiElement)body, false)) {
                return;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        final Document document = editor.getDocument();
        final ASTNode whileKeyword = ocDoWhileStatement.getWhileKeyword();
        Label_0253: {
            Label_0208: {
                try {
                    if (whileKeyword != null) {
                        break Label_0253;
                    }
                    final OCStatement ocStatement = body;
                    if (ocStatement != null) {
                        break Label_0208;
                    }
                    break Label_0208;
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
                try {
                    final OCStatement ocStatement = body;
                    if (ocStatement != null) {
                        if (body instanceof OCBlockStatement) {
                            break Label_0253;
                        }
                    }
                }
                catch (IncorrectOperationException ex7) {
                    throw a(ex7);
                }
            }
            final int startOffset = OCFixer.getRangeWithMacros((PsiElement)ocDoWhileStatement).getStartOffset();
            document.replaceString(startOffset, startOffset + "do".length(), (CharSequence)"do {} while()");
            return;
        }
        if (body != null) {
            final ASTNode lParenth = ocDoWhileStatement.getLParenth();
            final ASTNode rParenth = ocDoWhileStatement.getRParenth();
            Label_0415: {
                Label_0393: {
                    Label_0320: {
                        try {
                            if (!a(ocDoWhileStatement)) {
                                break Label_0393;
                            }
                            if (whileKeyword != null) {
                                break Label_0320;
                            }
                        }
                        catch (IncorrectOperationException ex8) {
                            throw a(ex8);
                        }
                        document.insertString(OCFixer.getRangeWithMacros((PsiElement)ocDoWhileStatement).getEndOffset(), (CharSequence)"while()");
                        return;
                        try {
                            if (lParenth == null) {
                                document.insertString(OCFixer.getRangeWithMacros(whileKeyword).getEndOffset(), (CharSequence)"()");
                                return;
                            }
                        }
                        catch (IncorrectOperationException ex9) {
                            throw a(ex9);
                        }
                    }
                    try {
                        if (rParenth == null) {
                            document.insertString(OCFixer.getRangeWithMacros(lParenth).getEndOffset(), (CharSequence)")");
                            return;
                        }
                    }
                    catch (IncorrectOperationException ex10) {
                        throw a(ex10);
                    }
                    ocSmartEnterProcessor.registerUnresolvedError(OCFixer.getRangeWithMacros(lParenth).getEndOffset());
                    return;
                    try {
                        if (ocDoWhileStatement.getCondition() == null) {
                            return;
                        }
                        final ASTNode astNode = rParenth;
                        if (astNode == null) {
                            break Label_0415;
                        }
                        return;
                    }
                    catch (IncorrectOperationException ex11) {
                        throw a(ex11);
                    }
                }
                try {
                    final ASTNode astNode = rParenth;
                    if (astNode == null) {
                        document.insertString(OCFixer.getRangeWithMacros((PsiElement)ocDoWhileStatement.getCondition()).getEndOffset(), (CharSequence)")");
                    }
                }
                catch (IncorrectOperationException ex12) {
                    throw a(ex12);
                }
            }
        }
    }
    
    public static boolean applicable(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/DoWhileConditionFixer", "applicable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0068: {
            try {
                if (!(psiElement instanceof OCDoWhileStatement)) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                final OCElementType ocElementType = OCTokenTypes.DO_KEYWORD;
                final boolean b = OCFixer.hasMacroBasedStatement(psiElement2, ocElementType);
                if (!b) {
                    break Label_0068;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final OCElementType ocElementType = OCTokenTypes.DO_KEYWORD;
                final boolean b = OCFixer.hasMacroBasedStatement(psiElement2, ocElementType);
                if (!b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static boolean a(@NotNull final OCDoWhileStatement ocDoWhileStatement) {
        try {
            if (ocDoWhileStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stmt", "com/jetbrains/cidr/lang/editor/smartEnter/DoWhileConditionFixer", "hasDoEmptyCondition"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocDoWhileStatement.getCondition() != null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        Label_0097: {
            try {
                if (ocDoWhileStatement.getLParenth() == null) {
                    break Label_0097;
                }
                final OCDoWhileStatement ocDoWhileStatement2 = ocDoWhileStatement;
                final ASTNode astNode = ocDoWhileStatement2.getLParenth();
                final ASTNode astNode2 = astNode.getTreeNext();
                final PsiElement psiElement = astNode2.getPsi();
                final boolean b = OCElementUtil.isIncompleteExpressionError(psiElement);
                if (b) {
                    break Label_0097;
                }
                return false;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final OCDoWhileStatement ocDoWhileStatement2 = ocDoWhileStatement;
                final ASTNode astNode = ocDoWhileStatement2.getLParenth();
                final ASTNode astNode2 = astNode.getTreeNext();
                final PsiElement psiElement = astNode2.getPsi();
                final boolean b = OCElementUtil.isIncompleteExpressionError(psiElement);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
