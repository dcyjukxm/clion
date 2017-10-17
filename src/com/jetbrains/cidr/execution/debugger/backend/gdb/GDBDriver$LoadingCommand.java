// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

protected interface LoadingCommand extends VoidCommand
{
    default long getTimeout() {
        return GDBDriver.access$1400();
    }
}
