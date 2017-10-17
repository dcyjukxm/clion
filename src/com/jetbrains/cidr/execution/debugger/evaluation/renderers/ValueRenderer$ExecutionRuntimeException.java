// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation.renderers;

import com.intellij.execution.ExecutionException;

static class ExecutionRuntimeException extends RuntimeException
{
    ExecutionException cause;
    
    ExecutionRuntimeException(final ExecutionException cause) {
        this.cause = cause;
    }
}
