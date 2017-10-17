// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

static final class OCIncludeMap$1 implements OCIncludeMap {
    @Override
    public boolean isEmpty() {
        return true;
    }
    
    @Nullable
    @Override
    public VirtualFile get(@NotNull final String s, @NotNull final OCResolveRootAndConfiguration ocResolveRootAndConfiguration) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "include", "com/jetbrains/cidr/lang/workspace/OCIncludeMap$1", "get"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveRootAndConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rootAndConfiguration", "com/jetbrains/cidr/lang/workspace/OCIncludeMap$1", "get"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}