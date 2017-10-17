// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import com.intellij.execution.ExecutionException;

public class DebuggerFatalException extends ExecutionException
{
    public DebuggerFatalException(final String s) {
        super(s);
    }
    
    public DebuggerFatalException(final Throwable t) {
        super(t);
    }
    
    public DebuggerFatalException(final String s, final Throwable t) {
        super(s, t);
    }
}
