// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

class CMakeSetAndUnsetPlainVariableProvider$StringLookupElementFunction$1 implements InsertHandler<LookupElement> {
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        insertionContext.getEditor().getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)" ");
        insertionContext.getEditor().getCaretModel().moveToOffset(insertionContext.getTailOffset());
    }
}