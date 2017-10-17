// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import org.jetbrains.annotations.NotNull;

public abstract class RunParameters
{
    @NotNull
    public abstract Installer getInstaller();
    
    @NotNull
    public abstract DebuggerDriverConfiguration getDebuggerDriverConfiguration();
    
    @Nullable
    public abstract String getArchitectureId();
}
