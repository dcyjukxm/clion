// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;

enum OCCompilerFeatures$Feature$1
{
    @NotNull
    @Override
    public Boolean compute(@NotNull final OCImmutableInclusionContext ocImmutableInclusionContext) {
        try {
            if (ocImmutableInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Feature$1", "compute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Boolean value;
        try {
            value = "1".equals(OCCompilerFeatures.access$000(ocImmutableInclusionContext, "_MSC_EXTENSIONS"));
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Feature$1", "compute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return value;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}