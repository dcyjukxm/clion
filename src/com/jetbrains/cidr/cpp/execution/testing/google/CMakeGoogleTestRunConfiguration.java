// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.testing.google;

import com.intellij.execution.configurations.RunConfiguration;
import com.jetbrains.cidr.execution.CidrCommandLineState;
import com.jetbrains.cidr.execution.testing.CidrTestScope;
import com.intellij.execution.Executor;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties;
import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestConsoleProperties;
import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestCommandLineState;
import com.intellij.execution.ui.ConsoleView;
import com.jetbrains.cidr.execution.CidrConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilder;
import java.io.File;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.jetbrains.cidr.execution.testing.CidrTestCommandLineState;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import com.intellij.execution.configurations.CommandLineState;
import com.jetbrains.cidr.cpp.execution.CMakeAppRunConfiguration;
import com.jetbrains.cidr.cpp.execution.CMakeLauncher;
import com.jetbrains.cidr.execution.testing.CidrLauncher;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.testing.CidrTestRunConfigurationData;
import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestRunConfigurationData;
import com.intellij.execution.configurations.ConfigurationFactory;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.cpp.execution.testing.CMakeTestRunConfiguration;

public class CMakeGoogleTestRunConfiguration extends CMakeTestRunConfiguration
{
    public CMakeGoogleTestRunConfiguration(@NotNull final Project project, @NotNull final ConfigurationFactory configurationFactory, @NotNull final String s) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration", "<init>"));
        }
        if (configurationFactory == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "factory", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration", "<init>"));
        }
        super(project, configurationFactory, s, new CidrGoogleTestRunConfigurationData(project));
    }
    
    @Nullable
    @Override
    public String getTestingFrameworkName() {
        return "GoogleTest";
    }
    
    @NotNull
    @Override
    public CidrLauncher createLauncher() {
        CMakeLauncher cMakeLauncher;
        try {
            cMakeLauncher = new CMakeLauncher(this) {
                @NotNull
                @Override
                protected GeneralCommandLine createCommandLine(@NotNull final CommandLineState commandLineState, @NotNull final BuildAndRunConfigurations buildAndRunConfigurations, @NotNull final CMakeEnvironment cMakeEnvironment, final boolean b) throws ExecutionException {
                    try {
                        if (commandLineState == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration$1", "createCommandLine"));
                        }
                    }
                    catch (ExecutionException ex) {
                        throw d((Exception)ex);
                    }
                    try {
                        if (buildAndRunConfigurations == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildAndRunConfigurations", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration$1", "createCommandLine"));
                        }
                    }
                    catch (ExecutionException ex2) {
                        throw d((Exception)ex2);
                    }
                    try {
                        if (cMakeEnvironment == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration$1", "createCommandLine"));
                        }
                    }
                    catch (ExecutionException ex3) {
                        throw d((Exception)ex3);
                    }
                    final GeneralCommandLine commandLine = super.createCommandLine(commandLineState, buildAndRunConfigurations, cMakeEnvironment, b);
                    GeneralCommandLine generalCommandLine;
                    try {
                        commandLine.addParameters(new String[] { "--gtest_filter=" + ((CidrTestCommandLineState)commandLineState).testScope(), "--gtest_color=no" });
                        generalCommandLine = commandLine;
                        if (generalCommandLine == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration$1", "createCommandLine"));
                        }
                    }
                    catch (ExecutionException ex4) {
                        throw d((Exception)ex4);
                    }
                    return generalCommandLine;
                }
                
                @Override
                public boolean usePty() {
                    return false;
                }
                
                @NotNull
                @Override
                protected TextConsoleBuilder createConsoleBuilder(@NotNull final CommandLineState commandLineState, @NotNull final CidrToolEnvironment cidrToolEnvironment, @Nullable final File file) {
                    try {
                        if (commandLineState == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration$1", "createConsoleBuilder"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw d(ex);
                    }
                    try {
                        if (cidrToolEnvironment == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration$1", "createConsoleBuilder"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw d(ex2);
                    }
                    CidrConsoleBuilder cidrConsoleBuilder;
                    try {
                        cidrConsoleBuilder = new CidrConsoleBuilder(this.getProject(), cidrToolEnvironment, file) {
                            @NotNull
                            @Override
                            protected ConsoleView createConsole() {
                                final CidrGoogleTestCommandLineState cidrGoogleTestCommandLineState = (CidrGoogleTestCommandLineState)commandLineState;
                                ConsoleView console;
                                try {
                                    console = this.createConsole(cidrGoogleTestCommandLineState.getConfiguration().getType(), new CidrGoogleTestConsoleProperties(cidrGoogleTestCommandLineState.getConfiguration(), cidrGoogleTestCommandLineState.getExecutor(), cidrGoogleTestCommandLineState.getExecutionTarget()));
                                    if (console == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration$1$1", "createConsole"));
                                    }
                                }
                                catch (IllegalStateException ex) {
                                    throw a(ex);
                                }
                                return console;
                            }
                            
                            private static IllegalStateException a(final IllegalStateException ex) {
                                return ex;
                            }
                        };
                        if (cidrConsoleBuilder == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration$1", "createConsoleBuilder"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw d(ex3);
                    }
                    return cidrConsoleBuilder;
                }
                
                private static Exception d(final Exception ex) {
                    return ex;
                }
            };
            if (cMakeLauncher == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration", "createLauncher"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return cMakeLauncher;
    }
    
    @NotNull
    @Override
    public CidrCommandLineState createState(@NotNull final ExecutionEnvironment executionEnvironment, @NotNull final Executor executor, @Nullable final CidrTestScope cidrTestScope) {
        try {
            if (executionEnvironment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "env", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration", "createState"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (executor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration", "createState"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        CidrGoogleTestCommandLineState cidrGoogleTestCommandLineState;
        try {
            cidrGoogleTestCommandLineState = new CidrGoogleTestCommandLineState((RunConfiguration)this, this.createLauncher(), cidrTestScope, executionEnvironment, executor);
            if (cidrGoogleTestCommandLineState == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/testing/google/CMakeGoogleTestRunConfiguration", "createState"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return cidrGoogleTestCommandLineState;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
