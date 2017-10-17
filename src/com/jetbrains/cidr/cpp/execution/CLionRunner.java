// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.intellij.xdebugger.XDebugSessionListener;
import com.jetbrains.cidr.execution.CidrCommandLineState;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.configurations.RunProfile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.CidrRunner;

public class CLionRunner extends CidrRunner
{
    @NotNull
    public String getRunnerId() {
        String s;
        try {
            s = "CPPAppRunner";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CLionRunner", "getRunnerId"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Override
    public boolean canRun(@NotNull final String s, @NotNull final RunProfile runProfile) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executorId", "com/jetbrains/cidr/cpp/execution/CLionRunner", "canRun"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (runProfile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "profile", "com/jetbrains/cidr/cpp/execution/CLionRunner", "canRun"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        try {
            if (DefaultRunExecutor.EXECUTOR_ID.equals(s)) {
                return super.canRun(s, runProfile);
            }
            final String s2 = DefaultDebugExecutor.EXECUTOR_ID;
            final String s3 = s;
            final boolean b = s2.equals(s3);
            if (!b) {
                return false;
            }
            return super.canRun(s, runProfile);
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        try {
            final String s2 = DefaultDebugExecutor.EXECUTOR_ID;
            final String s3 = s;
            final boolean b = s2.equals(s3);
            if (!b) {
                return false;
            }
        }
        catch (IllegalStateException ex4) {
            throw b(ex4);
        }
        return super.canRun(s, runProfile);
    }
    
    @Override
    protected RunContentDescriptor doExecute(@NotNull final RunProfileState runProfileState, @NotNull final ExecutionEnvironment executionEnvironment) throws ExecutionException {
        try {
            if (runProfileState == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/execution/CLionRunner", "doExecute"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (executionEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/cpp/execution/CLionRunner", "doExecute"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            CidrRunner.triggerUsage(executionEnvironment.getRunnerAndConfigurationSettings());
            if (DefaultDebugExecutor.EXECUTOR_ID.equals(executionEnvironment.getExecutor().getId())) {
                return this.startDebugSession((CidrCommandLineState)runProfileState, executionEnvironment, false, new XDebugSessionListener[0]).getRunContentDescriptor();
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        return super.doExecute(runProfileState, executionEnvironment);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
