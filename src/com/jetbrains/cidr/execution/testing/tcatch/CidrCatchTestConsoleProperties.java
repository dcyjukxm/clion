// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.intellij.execution.testframework.sm.runner.OutputToGeneralTestEventsConverter;
import com.intellij.execution.testframework.TestConsoleProperties;
import com.intellij.execution.testframework.sm.runner.SMTestLocator;
import com.intellij.execution.ExecutionTarget;
import com.intellij.execution.Executor;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.RunConfiguration;
import java.util.regex.Pattern;
import com.jetbrains.cidr.execution.testing.CidrAbstractTestConsoleProperties;

public class CidrCatchTestConsoleProperties extends CidrAbstractTestConsoleProperties
{
    private static final Pattern FAILURE_PATTERN;
    
    public CidrCatchTestConsoleProperties(@NotNull final RunConfiguration runConfiguration, @NotNull final Executor executor, @NotNull final ExecutionTarget executionTarget) {
        if (runConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestConsoleProperties", "<init>"));
        }
        if (executor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestConsoleProperties", "<init>"));
        }
        if (executionTarget == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestConsoleProperties", "<init>"));
        }
        super(runConfiguration, "Catch", executor, executionTarget);
        this.setIdBasedTestTree(true);
    }
    
    @Override
    public SMTestLocator getTestLocator() {
        return CidrCatchTestLocationProvider.INSTANCE;
    }
    
    @Override
    public OutputToGeneralTestEventsConverter createTestEventsConverter(@NotNull final String s, @NotNull final TestConsoleProperties testConsoleProperties) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testFrameworkName", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestConsoleProperties", "createTestEventsConverter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (testConsoleProperties == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consoleProperties", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestConsoleProperties", "createTestEventsConverter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return new CidrCatchOutputToGeneralTestEventsConverter(s, testConsoleProperties, this.getConsole());
    }
    
    @NotNull
    @Override
    protected Pattern getAssertionPattern() {
        Pattern failure_PATTERN;
        try {
            failure_PATTERN = CidrCatchTestConsoleProperties.FAILURE_PATTERN;
            if (failure_PATTERN == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestConsoleProperties", "getAssertionPattern"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return failure_PATTERN;
    }
    
    static {
        FAILURE_PATTERN = Pattern.compile("(.+?):(\\d+): Failure:[^\\n]*");
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
