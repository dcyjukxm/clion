// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;

public static class TestDebuggerException extends DebuggerCommandException
{
    public TestDebuggerException() {
        super("user exception");
    }
}
