// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.EventListener;

public interface Handler extends EventListener
{
    default void handleRunning() {
    }
    
    default void handleModulesLoaded(@NotNull final List<String> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modules", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleModulesLoaded"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    default void handleInterrupted(@NotNull final StopPlace stopPlace) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleInterrupted"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    default void handleSignal(@NotNull final StopPlace stopPlace, @NotNull final String s, @NotNull final String s2) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleSignal"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "signal", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleSignal"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "meaning", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleSignal"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    default void handleException(@NotNull final StopPlace stopPlace, @NotNull final String s) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleException"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "description", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleException"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    default void handleBreakpoint(@NotNull final StopPlace stopPlace, final int n) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleBreakpoint"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    default void handleWatchpoint(@NotNull final StopPlace stopPlace, final int n) {
        try {
            if (stopPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stopPlace", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleWatchpoint"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    default void handleWatchpointScope(final int n) {
    }
    
    default void handleTargetOutput(@NotNull final String s, @NotNull final Key key) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleTargetOutput"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleTargetOutput"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    default void handleGDBOutput(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleGDBOutput"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    default void handlePrompt(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prompt", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handlePrompt"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    default void handleTargetFinished(final int n, @Nullable final String s) {
    }
    
    default void handleTargetTerminated() {
    }
    
    default void handleExited(final int n) {
    }
    
    default void handleAttached(final int n) {
    }
    
    default void handleDetached() {
    }
    
    default void handleConnected(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "connection", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$Handler", "handleConnected"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    default void handleDisconnected() {
    }
    
    default IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
