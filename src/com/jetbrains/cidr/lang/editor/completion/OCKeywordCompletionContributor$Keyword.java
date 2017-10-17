// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

private static class Keyword
{
    @NotNull
    final String lookUpString;
    @NotNull
    final OCCompletionPriority priority;
    @Nullable
    final LookupDecorator decorator;
    
    public Keyword(@NotNull final String lookUpString, @NotNull final OCCompletionPriority priority, @Nullable final LookupDecorator decorator) {
        if (lookUpString == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lookUpString", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$Keyword", "<init>"));
        }
        if (priority == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "priority", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$Keyword", "<init>"));
        }
        this.lookUpString = lookUpString;
        this.priority = priority;
        this.decorator = decorator;
    }
}
