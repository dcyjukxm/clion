// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import com.jetbrains.cidr.execution.Installer;
import com.jetbrains.cidr.execution.RunParameters;

static final class CidrLocalAttachedDebugProcess$1 extends RunParameters {
    final /* synthetic */ Installer val$installer;
    final /* synthetic */ DebuggerDriverConfiguration val$driverConfiguration;
    
    @NotNull
    @Override
    public Installer getInstaller() {
        Installer val$installer;
        try {
            val$installer = this.val$installer;
            if (val$installer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachedDebugProcess$1", "getInstaller"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return val$installer;
    }
    
    @NotNull
    @Override
    public DebuggerDriverConfiguration getDebuggerDriverConfiguration() {
        DebuggerDriverConfiguration val$driverConfiguration;
        try {
            val$driverConfiguration = this.val$driverConfiguration;
            if (val$driverConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachedDebugProcess$1", "getDebuggerDriverConfiguration"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return val$driverConfiguration;
    }
    
    @Nullable
    @Override
    public String getArchitectureId() {
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}