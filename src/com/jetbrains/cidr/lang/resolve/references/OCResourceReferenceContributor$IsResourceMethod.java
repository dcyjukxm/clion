// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.intellij.patterns.PatternCondition;

private static class IsResourceMethod extends PatternCondition<OCMessageArgument>
{
    private OCResourceCompletionProvider myProvider;
    
    public IsResourceMethod(final OCResourceCompletionProvider myProvider) {
        super((String)null);
        this.myProvider = myProvider;
    }
    
    public boolean accepts(@NotNull final OCMessageArgument ocMessageArgument, final ProcessingContext processingContext) {
        try {
            if (ocMessageArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/resolve/references/OCResourceReferenceContributor$IsResourceMethod", "accepts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocMessageArgument.getArgumentSelector().getSelectorName().equals(this.myProvider.getArgumentSelector());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
