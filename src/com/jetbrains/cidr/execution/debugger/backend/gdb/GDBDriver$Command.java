// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;

protected interface Command<T>
{
    @Nullable
    T call() throws ExecutionException, DebuggerCommandException;
    
    default long getTimeout() {
        return GDBDriver.access$1300();
    }
}
