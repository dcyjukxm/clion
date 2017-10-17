// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.NotNull;

public static class PathMapping
{
    @NotNull
    public final String from;
    @NotNull
    public final String to;
    
    public PathMapping(@NotNull final String from, @NotNull final String to) {
        if (from == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "from", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$PathMapping", "<init>"));
        }
        if (to == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "to", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$PathMapping", "<init>"));
        }
        this.from = from;
        this.to = to;
    }
}
