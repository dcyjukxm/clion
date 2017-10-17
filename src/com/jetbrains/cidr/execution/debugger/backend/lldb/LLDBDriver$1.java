// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import org.jetbrains.annotations.NotNull;
import java.io.File;
import com.jetbrains.cidr.execution.GLogOutputReaders;

class LLDBDriver$1 extends GLogOutputReaders {
    @Override
    protected void onTextAvailable(@NotNull final String s, @NotNull final LogType logType) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$1", "onTextAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (logType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$1", "onTextAvailable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (LLDBDriver.LOG.isTraceEnabled()) {
                LLDBDriver.LOG.trace(s.trim());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}