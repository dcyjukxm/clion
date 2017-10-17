// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.NotNull;

class OCFilteredCompletionProvider extends OCCompletionProvider
{
    @NotNull
    private OCCompletionProvider myDelegate;
    @NotNull
    private Condition<Filter> myCondition;
    
    public OCFilteredCompletionProvider(@NotNull final OCCompletionProvider myDelegate, @NotNull final Condition<Filter> myCondition) {
        if (myDelegate == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "delegate", "com/jetbrains/cidr/lang/editor/completion/OCFilteredCompletionProvider", "<init>"));
        }
        if (myCondition == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/editor/completion/OCFilteredCompletionProvider", "<init>"));
        }
        this.myDelegate = myDelegate;
        this.myCondition = myCondition;
    }
    
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCFilteredCompletionProvider", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myCondition.value((Object)new Filter(s, ocCompletionParameters, processingContext))) {
                this.myDelegate.addCompletions(s, ocCompletionParameters, processingContext, set);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class Filter
    {
        public final String myPrefix;
        @NotNull
        public final OCCompletionParameters myParameters;
        public final ProcessingContext myProcessingContext;
        
        public Filter(final String myPrefix, @NotNull final OCCompletionParameters myParameters, final ProcessingContext myProcessingContext) {
            if (myParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCFilteredCompletionProvider$Filter", "<init>"));
            }
            this.myPrefix = myPrefix;
            this.myParameters = myParameters;
            this.myProcessingContext = myProcessingContext;
        }
    }
}
