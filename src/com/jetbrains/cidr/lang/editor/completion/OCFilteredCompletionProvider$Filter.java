// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

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
