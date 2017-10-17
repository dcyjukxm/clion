// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import java.util.List;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface CLionCustomDebuggerProvider
{
    public static final ExtensionPointName<CLionCustomDebuggerProvider> EP_NAME = ExtensionPointName.create("clion.debugger.customDebuggerProvider");
    
    @NotNull
    List<DebuggerDriverConfiguration> getDebuggerConfigurations();
}
