// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.execution.ui.ConsoleView;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.console.CMakeConsoleMessageType;
import com.jetbrains.cidr.cpp.cmake.console.CMakeConsoleViewImpl;

class CMakeOutputConsole$2 implements CMakeConsoleViewImpl.MessageListener {
    final /* synthetic */ CMakeConsoleViewImpl val$console;
    
    @Override
    public void messageReported(@NotNull final CMakeConsoleMessageType cMakeConsoleMessageType) {
        try {
            if (cMakeConsoleMessageType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole$2", "messageReported"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CMakeOutputConsole.access$000(CMakeOutputConsole.this, (ConsoleView)this.val$console, cMakeConsoleMessageType);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}