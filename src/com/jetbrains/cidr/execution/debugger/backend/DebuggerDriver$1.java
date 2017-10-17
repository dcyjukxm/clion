// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.ProcessOutputReaders;

class DebuggerDriver$1 extends ProcessOutputReaders {
    @Override
    protected void onTextAvailable(@NotNull final String s, @NotNull final Key key) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$1", "onTextAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (key == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriver$1", "onTextAvailable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        DebuggerDriver.this.handleTargetOutput(s, key);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}