// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;

private abstract static class ConsoleCommand<T> extends EvaluationCommand<T>
{
    public ConsoleCommand(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$ConsoleCommand", "<init>"));
        }
        super(s);
    }
}
