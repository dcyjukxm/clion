// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertionContext;

private static class OCClassLookupElementInsertHandler extends LookupElementInsertHandler
{
    private OCClassLookupElementInsertHandler() {
        super(' ');
    }
    
    @Override
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        final CharSequence charsSequence = insertionContext.getDocument().getCharsSequence();
        if (insertionContext.getStartOffset() == 0 || (charsSequence.length() > 0 && charsSequence.charAt(insertionContext.getStartOffset() - 1) != '[')) {
            insertionContext.getDocument().insertString(insertionContext.getStartOffset(), (CharSequence)String.valueOf('['));
        }
        super.handleInsert(insertionContext, lookupElement);
        LookupElementInsertHandler.reInvokeCompletion(insertionContext);
    }
}
