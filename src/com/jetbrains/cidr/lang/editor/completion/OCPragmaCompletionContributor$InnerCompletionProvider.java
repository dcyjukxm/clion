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

private static class InnerCompletionProvider extends CompletionProvider<CompletionParameters>
{
    protected void addCompletions(@NotNull final CompletionParameters completionParameters, final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
        try {
            if (completionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ps", "com/jetbrains/cidr/lang/editor/completion/OCPragmaCompletionContributor$InnerCompletionProvider", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "r", "com/jetbrains/cidr/lang/editor/completion/OCPragmaCompletionContributor$InnerCompletionProvider", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String[] access$100 = OCPragmaCompletionContributor.access$100();
        for (int length = access$100.length, i = 0; i < length; ++i) {
            set.addElement(OCCompletionPriority.keywordWithPriority((LookupElement)TemplateInsertHandler.lookup(access$100[i]), OCCompletionPriority.NORMAL_KEYWORDS_PRIORITY));
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
