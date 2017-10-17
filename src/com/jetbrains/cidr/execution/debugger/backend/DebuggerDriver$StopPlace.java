// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.NotNull;

public static class StopPlace
{
    @NotNull
    public final LLThread thread;
    @NotNull
    public final LLFrame frame;
    
    public StopPlace(@NotNull final LLThread thread, @NotNull final LLFrame frame) {
        if (thread == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "thread", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StopPlace", "<init>"));
        }
        if (frame == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$StopPlace", "<init>"));
        }
        this.thread = thread;
        this.frame = frame;
    }
    
    @Override
    public String toString() {
        return this.thread + ": " + this.frame;
    }
}
