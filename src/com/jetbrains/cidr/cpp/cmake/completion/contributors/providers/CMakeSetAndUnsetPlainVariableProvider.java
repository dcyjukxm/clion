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
import com.intellij.util.containers.ContainerUtil;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionParameters;

public class CMakeSetAndUnsetPlainVariableProvider extends CMakeVariableProvider
{
    @Override
    protected void addCompletions(@NotNull final CompletionParameters completionParameters, final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
        try {
            if (completionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeSetAndUnsetPlainVariableProvider", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/completion/contributors/providers/CMakeSetAndUnsetPlainVariableProvider", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            set.addAllElements((Iterable)ContainerUtil.map((Object[])CMakeSetAndUnsetPlainVariableProvider.ALL_COMPLETION_VARIABLES, (Function)new StringLookupElementFunction()));
            set.addAllElements((Iterable)ContainerUtil.map((Object[])CMakeEnvironmentVariableProvider.getCompletionVariables(completionParameters), (Function)new StringLookupElementFunction("ENV{", "}")));
            if (CMakeVariableProviderUtils.shouldCreatePolicyCompletions(completionParameters)) {
                set.addAllElements((Iterable)ContainerUtil.map((Object[])CMakeSetAndUnsetPlainVariableProvider.CMAKE_POLICY_DEFAULT_HARDCODED, (Function)new StringLookupElementFunction()));
                set.addAllElements((Iterable)ContainerUtil.map((Object[])CMakeSetAndUnsetPlainVariableProvider.CMAKE_POLICY_WARNING_HARDCODED, (Function)new StringLookupElementFunction()));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        set.stopHere();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
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
}
