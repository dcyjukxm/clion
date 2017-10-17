// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.util.Function;

private static class StringLookupElementFunction implements Function<String, LookupElement>
{
    @Nullable
    private String prefix;
    @Nullable
    private String suffix;
    
    private StringLookupElementFunction() {
        this(null, null);
    }
    
    private StringLookupElementFunction(@Nullable final String prefix, @Nullable final String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }
    
    public LookupElement fun(final String s) {
        return (LookupElement)LookupElementBuilder.create(((this.prefix == null) ? "" : this.prefix) + s + ((this.suffix == null) ? "" : this.suffix)).withInsertHandler((InsertHandler)new InsertHandler<LookupElement>() {
            public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
                insertionContext.getEditor().getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)" ");
                insertionContext.getEditor().getCaretModel().moveToOffset(insertionContext.getTailOffset());
            }
        });
    }
}
