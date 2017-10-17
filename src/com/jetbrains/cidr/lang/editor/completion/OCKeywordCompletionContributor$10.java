// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;

static final class OCKeywordCompletionContributor$10 extends CompletionProvider<CompletionParameters> {
    final /* synthetic */ String[] val$keywords;
    final /* synthetic */ OCCompletionPriority val$priority;
    
    protected void addCompletions(@NotNull final CompletionParameters completionParameters, final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
        try {
            if (completionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$10", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$10", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final CompletionResultSet withOCPrefixMather = OCKeywordCompletionContributor.withOCPrefixMather(set, completionParameters.getPosition(), completionParameters.getOffset());
        final String[] val$keywords = this.val$keywords;
        for (int length = val$keywords.length, i = 0; i < length; ++i) {
            withOCPrefixMather.addElement(OCCompletionPriority.keywordWithPriority((LookupElement)TemplateInsertHandler.lookup(val$keywords[i]), this.val$priority));
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}