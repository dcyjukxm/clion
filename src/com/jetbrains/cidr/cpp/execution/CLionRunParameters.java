// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.Installer;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import com.jetbrains.cidr.execution.RunParameters;

public class CLionRunParameters extends RunParameters
{
    @NotNull
    private final DebuggerDriverConfiguration myDriverConfiguration;
    @NotNull
    private final Installer myInstaller;
    
    public CLionRunParameters(@NotNull final DebuggerDriverConfiguration myDriverConfiguration, @NotNull final Installer myInstaller) {
        if (myDriverConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driverConfiguration", "com/jetbrains/cidr/cpp/execution/CLionRunParameters", "<init>"));
        }
        if (myInstaller == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "installer", "com/jetbrains/cidr/cpp/execution/CLionRunParameters", "<init>"));
        }
        this.myDriverConfiguration = myDriverConfiguration;
        this.myInstaller = myInstaller;
    }
    
    @NotNull
    @Override
    public Installer getInstaller() {
        Installer myInstaller;
        try {
            myInstaller = this.myInstaller;
            if (myInstaller == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CLionRunParameters", "getInstaller"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myInstaller;
    }
    
    @NotNull
    @Override
    public DebuggerDriverConfiguration getDebuggerDriverConfiguration() {
        DebuggerDriverConfiguration myDriverConfiguration;
        try {
            myDriverConfiguration = this.myDriverConfiguration;
            if (myDriverConfiguration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CLionRunParameters", "getDebuggerDriverConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myDriverConfiguration;
    }
    
    @Nullable
    @Override
    public String getArchitectureId() {
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
