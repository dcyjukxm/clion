// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.AddSpaceInsertHandler;

static final class OCKeywordCompletionContributor$1 implements LookupDecorator {
    private final AddSpaceInsertHandler myHandler = new AddSpaceInsertHandler(false);
    
    @NotNull
    @Override
    public LookupElementBuilder decorate(@NotNull final OCCompletionParameters ocCompletionParameters, @NotNull final LookupElementBuilder lookupElementBuilder) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$1", "decorate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (lookupElementBuilder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$1", "decorate"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        LookupElementBuilder withInsertHandler;
        try {
            withInsertHandler = lookupElementBuilder.withInsertHandler((InsertHandler)this.myHandler);
            if (withInsertHandler == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$1", "decorate"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return withInsertHandler;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}