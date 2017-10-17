// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.Executor;
import com.intellij.execution.process.ProcessHandler;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.execution.ExecutionException;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.testing.CidrLauncher;
import com.intellij.execution.configurations.CommandLineState;

public class CidrCommandLineState extends CommandLineState
{
    @NotNull
    private final CidrLauncher myLauncher;
    
    public CidrCommandLineState(final ExecutionEnvironment executionEnvironment, @NotNull final CidrLauncher myLauncher) {
        if (myLauncher == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "launcher", "com/jetbrains/cidr/execution/CidrCommandLineState", "<init>"));
        }
        super(executionEnvironment);
        this.myLauncher = myLauncher;
    }
    
    @NotNull
    public CidrLauncher getLauncher() {
        CidrLauncher myLauncher;
        try {
            myLauncher = this.myLauncher;
            if (myLauncher == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrCommandLineState", "getLauncher"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myLauncher;
    }
    
    @NotNull
    public XDebugProcess startDebugProcess(@NotNull final XDebugSession xDebugSession) throws ExecutionException {
        try {
            if (xDebugSession == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/execution/CidrCommandLineState", "startDebugProcess"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        CidrDebugProcess startDebugProcess;
        try {
            startDebugProcess = this.myLauncher.startDebugProcess(this, xDebugSession);
            if (startDebugProcess == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrCommandLineState", "startDebugProcess"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return startDebugProcess;
    }
    
    @NotNull
    protected ProcessHandler startProcess() throws ExecutionException {
        ProcessHandler startProcess;
        try {
            startProcess = this.myLauncher.startProcess(this);
            if (startProcess == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrCommandLineState", "startProcess"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return startProcess;
    }
    
    @NotNull
    public ExecutionResult execute(@NotNull final Executor executor, @NotNull final ProgramRunner programRunner) throws ExecutionException {
        try {
            if (executor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/execution/CidrCommandLineState", "execute"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (programRunner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runner", "com/jetbrains/cidr/execution/CidrCommandLineState", "execute"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        final DefaultExecutionResult defaultExecutionResult = (DefaultExecutionResult)super.execute(executor, programRunner);
        DefaultExecutionResult defaultExecutionResult2;
        try {
            this.myLauncher.configureExecutionResult(this, defaultExecutionResult);
            defaultExecutionResult2 = defaultExecutionResult;
            if (defaultExecutionResult2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrCommandLineState", "execute"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        return (ExecutionResult)defaultExecutionResult2;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
