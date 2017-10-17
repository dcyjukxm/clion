// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.text.CharArrayUtil;
import com.jetbrains.cidr.lang.editor.OCEnterInBlockCommentHandler;
import com.intellij.codeInsight.editorActions.smartEnter.SmartEnterProcessor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiComment;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.intellij.lang.SmartEnterProcessorWithFixers;

public class CommentBreakerEnterProcessor extends SmartEnterProcessorWithFixers.FixEnterProcessor
{
    @Override
    public boolean doEnter(final PsiElement psiElement, final PsiFile psiFile, @NotNull final Editor editor, final boolean b) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/CommentBreakerEnterProcessor", "doEnter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (b) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final PsiComment psiComment = (PsiComment)PsiTreeUtil.getParentOfType(psiElement, (Class)PsiComment.class, false);
        Label_0097: {
            try {
                if (psiComment == null) {
                    return false;
                }
                final CommentBreakerEnterProcessor commentBreakerEnterProcessor = this;
                final Editor editor2 = editor;
                commentBreakerEnterProcessor.plainEnter(editor2);
                final PsiComment psiComment2 = psiComment;
                final IElementType elementType = psiComment2.getTokenType();
                final OCElementType ocElementType = OCTokenTypes.EOL_COMMENT;
                if (elementType == ocElementType) {
                    break Label_0097;
                }
                break Label_0097;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final CommentBreakerEnterProcessor commentBreakerEnterProcessor = this;
                final Editor editor2 = editor;
                commentBreakerEnterProcessor.plainEnter(editor2);
                final PsiComment psiComment2 = psiComment;
                final IElementType elementType = psiComment2.getTokenType();
                final OCElementType ocElementType = OCTokenTypes.EOL_COMMENT;
                if (elementType == ocElementType) {
                    EditorModificationUtil.insertStringAtCaret(editor, "// ");
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        SmartEnterProcessor.commitDocument(editor);
        final int offset = editor.getCaretModel().getOffset();
        final PsiComment psiComment3 = (PsiComment)PsiTreeUtil.getParentOfType(psiFile.findElementAt(offset), (Class)PsiComment.class, false);
        try {
            if (psiComment3 == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        if (!psiComment3.getText().endsWith("*/")) {
            final CharSequence charsSequence = editor.getDocument().getCharsSequence();
            String s = OCEnterInBlockCommentHandler.getFirstCommentLineIndentFromContext((PsiElement)psiComment3);
            int shiftBackward = CharArrayUtil.shiftBackward(charsSequence, offset - 1, " \t");
            Label_0317: {
                Label_0295: {
                    try {
                        if (shiftBackward >= offset || charsSequence.charAt(shiftBackward) != '*') {
                            break Label_0295;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                    --shiftBackward;
                    final int shiftBackward2 = CharArrayUtil.shiftBackward(charsSequence, shiftBackward, " \t");
                    Label_0292: {
                        try {
                            if (shiftBackward2 != 0) {
                                if (charsSequence.charAt(shiftBackward2) != '\n') {
                                    break Label_0292;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw b(ex7);
                        }
                        s = charsSequence.subSequence(shiftBackward2, shiftBackward + 1).toString();
                    }
                    break Label_0317;
                }
                s = "\n" + s;
            }
            EditorModificationUtil.insertStringAtCaret(editor, s + "*/");
            editor.getCaretModel().moveToOffset(offset);
        }
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
