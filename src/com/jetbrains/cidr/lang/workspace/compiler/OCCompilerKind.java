// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

import org.jetbrains.annotations.NotNull;

public enum OCCompilerKind
{
    GCC("GCC"), 
    CLANG("Clang"), 
    MSVC("MSVC"), 
    UNKNOWN("Unknown");
    
    @NotNull
    private final String myDisplayName;
    
    private OCCompilerKind(final String myDisplayName) {
        if (myDisplayName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "displayName", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerKind", "<init>"));
        }
        super(s, n);
        this.myDisplayName = myDisplayName;
    }
    
    @Override
    public String toString() {
        return this.myDisplayName;
    }
}
