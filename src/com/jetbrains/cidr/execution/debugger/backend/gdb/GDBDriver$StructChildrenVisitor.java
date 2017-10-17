// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.execution.ExecutionException;
import org.jetbrains.annotations.NotNull;

private static class StructChildrenVisitor
{
    boolean visitRealChild(@NotNull final GDBTuple gdbTuple) throws ExecutionException {
        try {
            if (gdbTuple == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$StructChildrenVisitor", "visitRealChild"));
            }
        }
        catch (ExecutionException ex) {
            throw a(ex);
        }
        return true;
    }
    
    boolean visitFakeChild(@NotNull final LLValue llValue, final int n) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fakeChild", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBDriver$StructChildrenVisitor", "visitFakeChild"));
            }
        }
        catch (ExecutionException ex) {
            throw a(ex);
        }
        return true;
    }
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
}
