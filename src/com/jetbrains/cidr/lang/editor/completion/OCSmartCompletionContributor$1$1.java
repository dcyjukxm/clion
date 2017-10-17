// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.Document;
import com.intellij.util.text.CharArrayUtil;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

class OCSmartCompletionContributor$1$1 implements InsertHandler<LookupElement> {
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        final Document document = insertionContext.getDocument();
        final Editor editor = insertionContext.getEditor();
        final CharSequence charsSequence = document.getCharsSequence();
        final int tailOffset = insertionContext.getTailOffset();
        if (charsSequence.charAt(CharArrayUtil.shiftForward(charsSequence, tailOffset, " \t")) != ')') {
            document.insertString(tailOffset, (CharSequence)")");
        }
        editor.getCaretModel().moveToOffset(CharArrayUtil.shiftForward(charsSequence, tailOffset + 1, " \t"));
    }
}