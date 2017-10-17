// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.NotNull;

public class DebuggerCommandException extends Exception
{
    public DebuggerCommandException(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "s", "com/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException", "<init>"));
        }
        super(s);
    }
    
    public DebuggerCommandException(@NotNull final String s, final Throwable t) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "s", "com/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException", "<init>"));
        }
        super(s, t);
    }
    
    public DebuggerCommandException(final Throwable t) {
        this(t.getMessage(), t);
    }
}
