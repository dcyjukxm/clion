// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.preprocessor.OCImmutableInclusionContext;
import org.jetbrains.annotations.NotNull;

public interface Type<T>
{
    @NotNull
    T getDefault();
    
    @Nullable
    default T compute(@NotNull final OCImmutableInclusionContext ocImmutableInclusionContext) {
        try {
            if (ocImmutableInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures$Type", "compute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    default IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
