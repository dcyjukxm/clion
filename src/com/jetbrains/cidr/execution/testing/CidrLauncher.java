// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import java.util.List;
import java.util.ArrayList;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.execution.DefaultExecutionResult;
import com.intellij.openapi.project.Project;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.openapi.util.Key;
import com.intellij.execution.ui.RunContentManagerImpl;
import com.intellij.execution.ui.ExecutionConsole;
import com.intellij.xdebugger.impl.XDebugSessionImpl;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessHandler;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.CommandLineState;

public abstract class CidrLauncher
{
    public ProcessHandler startProcess(@NotNull final CommandLineState commandLineState) throws ExecutionException {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/testing/CidrLauncher", "startProcess"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        final ProcessHandler process = this.createProcess(commandLineState);
        this.configProcessHandler(commandLineState, process, false, true);
        return process;
    }
    
    protected abstract ProcessHandler createProcess(@NotNull final CommandLineState p0) throws ExecutionException;
    
    @NotNull
    public CidrDebugProcess startDebugProcess(@NotNull final CommandLineState commandLineState, @NotNull final XDebugSession xDebugSession) throws ExecutionException {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/testing/CidrLauncher", "startDebugProcess"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (xDebugSession == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/execution/testing/CidrLauncher", "startDebugProcess"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        final CidrDebugProcess debugProcess = this.createDebugProcess(commandLineState, xDebugSession);
        CidrDebugProcess cidrDebugProcess;
        try {
            ((XDebugSessionImpl)xDebugSession).addRestartActions(this.a(commandLineState, debugProcess.getProcessHandler(), (ExecutionConsole)debugProcess.getConsole()));
            this.configProcessHandler(commandLineState, debugProcess.getProcessHandler(), debugProcess.isDetachDefault(), true);
            debugProcess.start();
            cidrDebugProcess = debugProcess;
            if (cidrDebugProcess == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrLauncher", "startDebugProcess"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        return cidrDebugProcess;
    }
    
    @NotNull
    protected abstract CidrDebugProcess createDebugProcess(@NotNull final CommandLineState p0, @NotNull final XDebugSession p1) throws ExecutionException;
    
    protected void configProcessHandler(@NotNull final CommandLineState commandLineState, @NotNull final ProcessHandler processHandler, final boolean b, final boolean b2) {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/testing/CidrLauncher", "configProcessHandler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (processHandler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/execution/testing/CidrLauncher", "configProcessHandler"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (!b) {
                processHandler.putUserData((Key)RunContentManagerImpl.ALWAYS_USE_DEFAULT_STOPPING_BEHAVIOUR_KEY, (Object)Boolean.TRUE);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (b2) {
                ProcessTerminatedListener.attach(processHandler, this.getProject());
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
    }
    
    @NotNull
    protected abstract Project getProject();
    
    public void configureExecutionResult(@NotNull final CommandLineState commandLineState, @NotNull final DefaultExecutionResult defaultExecutionResult) throws ExecutionException {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/testing/CidrLauncher", "configureExecutionResult"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (defaultExecutionResult == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executionResult", "com/jetbrains/cidr/execution/testing/CidrLauncher", "configureExecutionResult"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        defaultExecutionResult.setRestartActions(this.a(commandLineState, defaultExecutionResult.getProcessHandler(), defaultExecutionResult.getExecutionConsole()));
    }
    
    @NotNull
    private AnAction[] a(@NotNull final CommandLineState commandLineState, final ProcessHandler processHandler, final ExecutionConsole executionConsole) throws ExecutionException {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/testing/CidrLauncher", "getAdditionalActions"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        final ArrayList<AnAction> list = new ArrayList<AnAction>();
        AnAction[] array;
        try {
            this.collectAdditionalActions(commandLineState, processHandler, executionConsole, list);
            array = list.toArray(new AnAction[list.size()]);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrLauncher", "getAdditionalActions"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        return array;
    }
    
    protected void collectAdditionalActions(@NotNull final CommandLineState commandLineState, @NotNull final ProcessHandler processHandler, @NotNull final ExecutionConsole executionConsole, @NotNull final List<AnAction> list) throws ExecutionException {
        try {
            if (commandLineState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/testing/CidrLauncher", "collectAdditionalActions"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (processHandler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processHandler", "com/jetbrains/cidr/execution/testing/CidrLauncher", "collectAdditionalActions"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (executionConsole == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "console", "com/jetbrains/cidr/execution/testing/CidrLauncher", "collectAdditionalActions"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actions", "com/jetbrains/cidr/execution/testing/CidrLauncher", "collectAdditionalActions"));
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
        try {
            if (commandLineState instanceof CidrTestCommandLineState) {
                list.add(((CidrTestCommandLineState)commandLineState).createRerunFailedTestsAction(executionConsole));
            }
        }
        catch (ExecutionException ex5) {
            throw b((Exception)ex5);
        }
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
