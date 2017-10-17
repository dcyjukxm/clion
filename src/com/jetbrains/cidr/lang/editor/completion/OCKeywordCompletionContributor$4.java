// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.util.Iterator;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.AddSpaceInsertHandler;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import java.util.List;

class OCKeywordCompletionContributor$4 extends OCCompletionProvider {
    final /* synthetic */ List val$keywords;
    
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$4", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final Keyword keyword : this.val$keywords) {
            set.addElement(OCCompletionPriority.keywordWithPriority((LookupElement)LookupElementBuilder.create(keyword.lookUpString).withInsertHandler((InsertHandler)AddSpaceInsertHandler.INSTANCE), keyword.priority));
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}