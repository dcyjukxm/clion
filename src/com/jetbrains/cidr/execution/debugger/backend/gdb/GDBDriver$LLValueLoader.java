// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;

private interface LLValueLoader
{
    void loadValue(@NotNull final LLValue p0) throws ExecutionException, DebuggerCommandException;
}
