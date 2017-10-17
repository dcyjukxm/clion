// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.CodeInsightUtilCore;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCStatement;

abstract class OCStatementSurrounder<T extends OCStatement> extends OCSurrounder
{
    public TextRange surroundElements(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiElement[] array) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/editor/surround/OCStatementSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/surround/OCStatementSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/editor/surround/OCStatementSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return this.doSurroundElements(project, array, null);
    }
    
    public TextRange doSurroundElements(final Project project, PsiElement[] moveDeclarationsOut, @Nullable final Object o) {
        final PsiElement parent = moveDeclarationsOut[0].getParent();
        moveDeclarationsOut = OCSurroundUtil.moveDeclarationsOut(parent, moveDeclarationsOut, true);
        try {
            if (moveDeclarationsOut.length == 0) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCStatement a = this.a((OCStatement)parent.addBefore(this.a(OCElementFactory.statementFromText(this.getStatementTemplate(project, moveDeclarationsOut[0]), parent)), moveDeclarationsOut[0]));
        final CodeStyleManager instance = CodeStyleManager.getInstance(project);
        final OCStatement a2 = this.a((OCStatement)instance.reformat((PsiElement)a));
        this.insertOldElements(moveDeclarationsOut, parent, (T)a2, o);
        final OCStatement ocStatement = CodeInsightUtilCore.forcePsiPostprocessAndRestoreElement(a2);
        final PsiDocumentManager instance2 = PsiDocumentManager.getInstance(project);
        final Document document = instance2.getDocument(ocStatement.getContainingFile());
        try {
            if (document != null) {
                instance2.commitDocument(document);
                instance.adjustLineIndent((PsiFile)ocStatement.getContainingOCFile(), ocStatement.getRangeWithMacros());
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return this.getSelectionRange((T)ocStatement);
    }
    
    protected void insertOldElements(final PsiElement[] array, final PsiElement psiElement, final T t, @Nullable final Object o) {
        final PsiElement insertionPlace = this.getInsertionPlace(t);
        final PsiElement psiElement2 = array[0];
        final PsiElement psiElement3 = array[array.length - 1];
        try {
            insertionPlace.addRangeBefore(psiElement2, psiElement3, insertionPlace.getLastChild());
            if (OCElementUtil.getElementType(psiElement3) == OCTokenTypes.EOL_COMMENT) {
                insertionPlace.addBefore(OCElementFactory.newlineFromText(psiElement3), insertionPlace.getLastChild());
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        psiElement.deleteChildRange(psiElement2, psiElement3);
    }
    
    private T a(final OCStatement ocStatement) {
        return (T)ocStatement;
    }
    
    @NonNls
    protected abstract String getStatementTemplate(final Project p0, final PsiElement p1);
    
    protected abstract PsiElement getInsertionPlace(final T p0);
    
    protected abstract TextRange getSelectionRange(final T p0);
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
