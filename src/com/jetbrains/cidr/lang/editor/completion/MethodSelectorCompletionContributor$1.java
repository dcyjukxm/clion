// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.openapi.editor.Document;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

static final class MethodSelectorCompletionContributor$1 implements InsertHandler<LookupElement> {
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        final Document document = insertionContext.getDocument();
        final int offset = insertionContext.getEditor().getCaretModel().getOffset();
        if (document.getCharsSequence().charAt(offset) == ':') {
            document.deleteString(offset, offset + 1);
        }
    }
}