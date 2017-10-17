// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.console;

import org.jetbrains.annotations.NotNull;
import java.util.EventListener;

public interface MessageListener extends EventListener
{
    void messageReported(@NotNull final CMakeConsoleMessageType p0);
}
