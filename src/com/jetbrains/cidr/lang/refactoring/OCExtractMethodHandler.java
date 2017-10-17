// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.intellij.refactoring.RefactoringBundle;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.util.text.CharArrayUtil;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.RefactoringActionHandler;

public class OCExtractMethodHandler implements RefactoringActionHandler
{
    public static final String REFACTORING_NAME;
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/OCExtractMethodHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        editor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
        final SelectionModel selectionModel = editor.getSelectionModel();
        try {
            if (!selectionModel.hasSelection()) {
                selectionModel.selectLineAtCaret();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int selectionStart = selectionModel.getSelectionStart();
        final int selectionEnd = selectionModel.getSelectionEnd();
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        final OCExpression expressionAtRange = OCCodeInsightUtil.findExpressionAtRange(psiFile, selectionStart, selectionEnd);
        try {
            if (expressionAtRange != null) {
                a(project, editor, dataContext, expressionAtRange);
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final PsiElement[] statementsAtRange = OCCodeInsightUtil.findStatementsAtRange(psiFile, selectionStart, selectionEnd, true);
        Label_0180: {
            try {
                if (statementsAtRange == null) {
                    break Label_0180;
                }
                final PsiElement[] array = statementsAtRange;
                final int n = array.length;
                if (n > 0) {
                    break Label_0180;
                }
                break Label_0180;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final PsiElement[] array = statementsAtRange;
                final int n = array.length;
                if (n > 0) {
                    new OCExtractMethodProcessor(statementsAtRange, project, new TextRange(selectionStart, selectionEnd), dataContext).invoke(editor);
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        final OCExpression expressionAtRange2 = OCCodeInsightUtil.findExpressionAtRange(psiFile, selectionStart, CharArrayUtil.shiftBackward(editor.getDocument().getCharsSequence(), selectionEnd - 1, " \t\n;") + 1);
        try {
            if (expressionAtRange2 != null) {
                a(project, editor, dataContext, expressionAtRange2);
                return;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        CommonRefactoringUtil.showErrorHint(project, editor, "Selected block should represent a set of statements or an expression", OCExtractMethodHandler.REFACTORING_NAME, (String)null);
    }
    
    private static void a(final Project project, final Editor editor, final DataContext dataContext, final OCExpression ocExpression) {
        Label_0034: {
            try {
                if (ocExpression.getParent() instanceof OCExpressionStatement) {
                    break Label_0034;
                }
                final OCExpression ocExpression2 = ocExpression;
                final OCType ocType = ocExpression2.getResolvedType();
                final OCType ocType2 = ocType.getGuessedType();
                final OCUnknownType ocUnknownType = OCUnknownType.INSTANCE;
                if (ocType2 == ocUnknownType) {
                    break Label_0034;
                }
                break Label_0034;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCExpression ocExpression2 = ocExpression;
                final OCType ocType = ocExpression2.getResolvedType();
                final OCType ocType2 = ocType.getGuessedType();
                final OCUnknownType ocUnknownType = OCUnknownType.INSTANCE;
                if (ocType2 == ocUnknownType) {
                    CommonRefactoringUtil.showErrorHint(project, editor, "Cannot determine type of the selected expression", OCExtractMethodHandler.REFACTORING_NAME, (String)null);
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        new OCExtractMethodProcessor((PsiElement[])new OCExpression[] { ocExpression }, project, ocExpression.getRangeWithMacros(), dataContext).invoke(editor);
    }
    
    public void invoke(@NotNull final Project project, @NotNull final PsiElement[] array, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/OCExtractMethodHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/refactoring/OCExtractMethodHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    static {
        REFACTORING_NAME = RefactoringBundle.message("extract.method.title");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
