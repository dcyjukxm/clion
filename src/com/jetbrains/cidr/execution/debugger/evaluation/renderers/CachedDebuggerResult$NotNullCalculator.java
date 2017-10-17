// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.execution.ExecutionException;

public interface NotNullCalculator<T> extends Calculator<T>
{
    @NotNull
    T calculate() throws ExecutionException, DebuggerCommandException;
}
