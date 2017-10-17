// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.xdebugger.XDebugProcessStarter;
import com.intellij.xdebugger.impl.ui.XDebugSessionData;
import com.intellij.execution.ExecutionException;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.impl.XDebugProcessConfiguratorStarter;
import com.intellij.xdebugger.XDebuggerManager;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebugSessionListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.internal.statistic.UsageTrigger;
import com.intellij.internal.statistic.beans.ConvertUsagesUtil;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfiguration;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.configurations.RunConfigurationWithSuppressedDefaultDebugAction;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.RunConfigurationWithSuppressedDefaultRunAction;
import com.intellij.execution.configurations.RunProfile;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.runners.DefaultProgramRunner;

public abstract class CidrRunner extends DefaultProgramRunner
{
    public boolean canRun(@NotNull final String s, @NotNull final RunProfile runProfile) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executorId", "com/jetbrains/cidr/execution/CidrRunner", "canRun"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (runProfile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "profile", "com/jetbrains/cidr/execution/CidrRunner", "canRun"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0118: {
            try {
                if (!(runProfile instanceof RunConfigurationWithSuppressedDefaultRunAction)) {
                    break Label_0118;
                }
                final String s2 = DefaultRunExecutor.EXECUTOR_ID;
                final String s3 = s;
                final boolean b = s2.equals(s3);
                if (b) {
                    return false;
                }
                break Label_0118;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final String s2 = DefaultRunExecutor.EXECUTOR_ID;
                final String s3 = s;
                final boolean b = s2.equals(s3);
                if (b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (!(runProfile instanceof RunConfigurationWithSuppressedDefaultDebugAction)) {
                    return runProfile instanceof CidrRunProfile;
                }
                final String s4 = DefaultDebugExecutor.EXECUTOR_ID;
                final String s5 = s;
                final boolean b2 = s4.equals(s5);
                if (b2) {
                    return false;
                }
                return runProfile instanceof CidrRunProfile;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        try {
            final String s4 = DefaultDebugExecutor.EXECUTOR_ID;
            final String s5 = s;
            final boolean b2 = s4.equals(s5);
            if (b2) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return runProfile instanceof CidrRunProfile;
    }
    
    protected static void triggerUsage(@Nullable final RunnerAndConfigurationSettings runnerAndConfigurationSettings) {
        if (runnerAndConfigurationSettings != null) {
            final RunConfiguration configuration = runnerAndConfigurationSettings.getConfiguration();
            if (configuration instanceof CidrTestRunConfiguration) {
                final String testingFrameworkName = ((CidrTestRunConfiguration)configuration).getTestingFrameworkName();
                try {
                    if (testingFrameworkName != null) {
                        UsageTrigger.trigger("execute.testingFramework." + ConvertUsagesUtil.ensureProperKey(testingFrameworkName));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
        }
    }
    
    public XDebugSession startDebugSession(final CidrCommandLineState cidrCommandLineState, final ExecutionEnvironment executionEnvironment, final boolean b, final XDebugSessionListener... array) throws ExecutionException {
        return XDebuggerManager.getInstance(executionEnvironment.getProject()).startSession(executionEnvironment, (XDebugProcessStarter)new XDebugProcessConfiguratorStarter() {
            @NotNull
            public XDebugProcess start(@NotNull final XDebugSession xDebugSession) throws ExecutionException {
                try {
                    if (xDebugSession == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "session", "com/jetbrains/cidr/execution/CidrRunner$1", "start"));
                    }
                }
                catch (ExecutionException ex) {
                    throw b((Exception)ex);
                }
                final XDebugSessionListener[] val$listeners = array;
                for (int length = val$listeners.length, i = 0; i < length; ++i) {
                    xDebugSession.addSessionListener(val$listeners[i]);
                }
                XDebugProcess startDebugProcess;
                try {
                    startDebugProcess = cidrCommandLineState.startDebugProcess(xDebugSession);
                    if (startDebugProcess == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrRunner$1", "start"));
                    }
                }
                catch (ExecutionException ex2) {
                    throw b((Exception)ex2);
                }
                return startDebugProcess;
            }
            
            @Override
            public void configure(final XDebugSessionData xDebugSessionData) {
                try {
                    if (b) {
                        xDebugSessionData.setBreakpointsMuted(true);
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
