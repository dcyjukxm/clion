// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface BackendConsoleInjectionHelper
{
    public static final ExtensionPointName<BackendConsoleInjectionHelper> EP_NAME = ExtensionPointName.create("cidr.debugger.backendConsoleInjectionHelper");
    
    void subscribeToInjection(@NotNull final XDebugSession p0);
}
