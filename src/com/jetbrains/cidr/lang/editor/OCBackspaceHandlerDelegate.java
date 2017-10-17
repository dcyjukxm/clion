// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.ex.EditorEx;
import com.jetbrains.cidr.lang.OCFileType;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.editorActions.BackspaceHandlerDelegate;

public class OCBackspaceHandlerDelegate extends BackspaceHandlerDelegate
{
    public void beforeCharDeleted(final char c, final PsiFile psiFile, final Editor editor) {
    }
    
    public boolean charDeleted(final char c, final PsiFile psiFile, final Editor editor) {
        if (psiFile.getFileType() != OCFileType.INSTANCE) {
            return false;
        }
        final Document document = editor.getDocument();
        final CharSequence charsSequence = document.getCharsSequence();
        final int offset = editor.getCaretModel().getOffset();
        if (c == '{' && offset + 1 < charsSequence.length() && charsSequence.charAt(offset) == '}' && charsSequence.charAt(offset + 1) == ';' && OCTypedHandlerDelegate.needSemicolonAtBlockEnd(((EditorEx)editor).getHighlighter().createIterator(offset - 1))) {
            document.deleteString(offset, offset + 2);
            return true;
        }
        return false;
    }
}
