// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.TailTypeDecorator;

class OCKeywordCompletionContributor$8$1 extends TailTypeDecorator<LookupElement> {
    final /* synthetic */ boolean val$isVoid;
    
    @Nullable
    @Override
    protected TailType computeTailType(final InsertionContext insertionContext) {
        final char completionChar = insertionContext.getCompletionChar();
        if (this.val$isVoid) {
            return (completionChar == ' ') ? TailType.HUMBLE_SPACE_BEFORE_WORD : TailType.SEMICOLON;
        }
        return (completionChar == ';') ? TailType.SEMICOLON : TailType.HUMBLE_SPACE_BEFORE_WORD;
    }
}