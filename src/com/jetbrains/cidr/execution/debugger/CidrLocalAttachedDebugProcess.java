// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.Installer;
import com.jetbrains.cidr.execution.TrivialInstaller;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.jetbrains.cidr.execution.RunParameters;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.execution.process.ProcessInfo;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;

public class CidrLocalAttachedDebugProcess extends CidrAttachedDebugProcess
{
    private final int myPid;
    
    public CidrLocalAttachedDebugProcess(@NotNull final DebuggerDriverConfiguration debuggerDriverConfiguration, @NotNull final ProcessInfo processInfo, @NotNull final XDebugSession xDebugSession, @NotNull final TextConsoleBuilder textConsoleBuilder) throws ExecutionException {
        if (debuggerDriverConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driverConfiguration", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachedDebugProcess", "<init>"));
        }
        if (processInfo == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachedDebugProcess", "<init>"));
        }
        if (xDebugSession == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachedDebugProcess", "<init>"));
        }
        if (textConsoleBuilder == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachedDebugProcess", "<init>"));
        }
        super(a(debuggerDriverConfiguration), xDebugSession, textConsoleBuilder);
        this.myPid = processInfo.getPid();
    }
    
    @NotNull
    private static RunParameters a(@NotNull final DebuggerDriverConfiguration debuggerDriverConfiguration) {
        try {
            if (debuggerDriverConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driverConfiguration", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachedDebugProcess", "createRunParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final TrivialInstaller trivialInstaller = new TrivialInstaller(new GeneralCommandLine());
        RunParameters runParameters;
        try {
            runParameters = new RunParameters() {
                @NotNull
                @Override
                public Installer getInstaller() {
                    Installer val$installer;
                    try {
                        val$installer = trivialInstaller;
                        if (val$installer == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachedDebugProcess$1", "getInstaller"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return val$installer;
                }
                
                @NotNull
                @Override
                public DebuggerDriverConfiguration getDebuggerDriverConfiguration() {
                    DebuggerDriverConfiguration val$driverConfiguration;
                    try {
                        val$driverConfiguration = debuggerDriverConfiguration;
                        if (val$driverConfiguration == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachedDebugProcess$1", "getDebuggerDriverConfiguration"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return val$driverConfiguration;
                }
                
                @Nullable
                @Override
                public String getArchitectureId() {
                    return null;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (runParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrLocalAttachedDebugProcess", "createRunParameters"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return runParameters;
    }
    
    @Override
    protected int getPidToAttachTo() throws ExecutionException {
        return this.myPid;
    }
    
    @Override
    public boolean isDetachDefault() {
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
