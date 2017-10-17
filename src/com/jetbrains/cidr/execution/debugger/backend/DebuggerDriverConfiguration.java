// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import com.intellij.openapi.util.Expirable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.jetbrains.cidr.execution.Installer;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.UserDataHolderBase;

public abstract class DebuggerDriverConfiguration extends UserDataHolderBase
{
    @NotNull
    public abstract String getDriverName();
    
    public boolean isAttachSupported() {
        return true;
    }
    
    @NotNull
    public abstract DebuggerDriver createDriver(@NotNull final DebuggerDriver.Handler p0);
    
    @NotNull
    public abstract GeneralCommandLine createDriverCommandLine(@NotNull final DebuggerDriver p0, @NotNull final Installer p1) throws ExecutionException;
    
    protected static void setupCommonParameters(@NotNull final GeneralCommandLine generalCommandLine) {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/debugger/backend/DebuggerDriverConfiguration", "setupCommonParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        generalCommandLine.setCharset(CharsetToolkit.UTF8_CHARSET);
    }
    
    @Contract("null -> null")
    public String convertToLocalPath(@Nullable final String s) {
        return s;
    }
    
    @Contract("null -> null")
    public String convertToEnvPath(@Nullable final String s) {
        return s;
    }
    
    @NotNull
    public abstract EvaluationContext createEvaluationContext(@NotNull final DebuggerDriver p0, @Nullable final Expirable p1, @NotNull final CidrStackFrame p2);
    
    @Deprecated
    public boolean supportsArrayEvaluation() {
        return false;
    }
    
    @Deprecated
    public boolean isCodeFragmentEvaluationSupported() {
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
