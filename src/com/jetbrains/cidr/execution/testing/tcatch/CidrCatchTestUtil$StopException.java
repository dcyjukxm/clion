// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

private static class StopException extends RuntimeException
{
    public StopException() {
        super("catch stop iteration", null, false, false);
    }
}
