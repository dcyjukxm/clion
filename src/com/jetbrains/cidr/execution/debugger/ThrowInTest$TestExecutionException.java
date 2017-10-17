// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.execution.ExecutionException;

public static class TestExecutionException extends ExecutionException
{
    public TestExecutionException() {
        super("execution exception");
    }
}
