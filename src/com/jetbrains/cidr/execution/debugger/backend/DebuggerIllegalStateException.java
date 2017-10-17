// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.ExecutionException;

public class DebuggerIllegalStateException extends ExecutionException
{
    public DebuggerIllegalStateException(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/execution/debugger/backend/DebuggerIllegalStateException", "<init>"));
        }
        super(s);
    }
}
