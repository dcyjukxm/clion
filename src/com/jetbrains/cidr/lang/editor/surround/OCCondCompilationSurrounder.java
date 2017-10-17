// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.openapi.editor.Document;
import com.intellij.util.text.CharArrayUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class OCCondCompilationSurrounder extends OCSurrounder
{
    public String getTemplateDescription() {
        return "#ifdef";
    }
    
    public TextRange surroundElements(@NotNull final Project project, @NotNull final Editor editor, @NotNull final PsiElement[] array) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/editor/surround/OCCondCompilationSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/surround/OCCondCompilationSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/editor/surround/OCCondCompilationSurrounder", "surroundElements"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final int startOffset = array[0].getTextRange().getStartOffset();
        final int endOffset = array[array.length - 1].getTextRange().getEndOffset();
        final Document document = editor.getDocument();
        final int n = CharArrayUtil.shiftBackward(document.getCharsSequence(), startOffset - 1, " \t") + 1;
        document.insertString(endOffset, (CharSequence)"\n#endif");
        document.insertString(n, (CharSequence)"#ifdef \n");
        final int n2 = n + "#ifdef ".length();
        return TextRange.create(n2, n2);
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
