// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;

protected interface VoidCommand extends Command<Void>
{
    @Nullable
    default Void call() throws ExecutionException, DebuggerCommandException {
        this.run();
        return null;
    }
    
    void run() throws ExecutionException, DebuggerCommandException;
}
