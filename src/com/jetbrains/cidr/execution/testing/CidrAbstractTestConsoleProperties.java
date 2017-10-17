// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import java.io.File;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import java.io.IOException;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.regex.Matcher;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.pom.Navigatable;
import com.intellij.openapi.project.Project;
import java.util.regex.Pattern;
import com.intellij.execution.Executor;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.ExecutionTarget;
import com.intellij.execution.testframework.sm.SMStacktraceParser;
import com.intellij.execution.testframework.sm.SMCustomMessagesParsing;
import com.intellij.execution.testframework.sm.runner.SMTRunnerConsoleProperties;

public abstract class CidrAbstractTestConsoleProperties extends SMTRunnerConsoleProperties implements SMCustomMessagesParsing, SMStacktraceParser
{
    private final ExecutionTarget myTarget;
    
    protected CidrAbstractTestConsoleProperties(@NotNull final RunConfiguration runConfiguration, @NotNull final String s, @NotNull final Executor executor, @NotNull final ExecutionTarget myTarget) {
        if (runConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/execution/testing/CidrAbstractTestConsoleProperties", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testFrameworkName", "com/jetbrains/cidr/execution/testing/CidrAbstractTestConsoleProperties", "<init>"));
        }
        if (executor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executor", "com/jetbrains/cidr/execution/testing/CidrAbstractTestConsoleProperties", "<init>"));
        }
        if (myTarget == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/execution/testing/CidrAbstractTestConsoleProperties", "<init>"));
        }
        super(runConfiguration, s, executor);
        this.myTarget = myTarget;
    }
    
    @NotNull
    @Override
    public ExecutionTarget getExecutionTarget() {
        ExecutionTarget myTarget;
        try {
            myTarget = this.myTarget;
            if (myTarget == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrAbstractTestConsoleProperties", "getExecutionTarget"));
            }
        }
        catch (NumberFormatException ex) {
            throw b((Exception)ex);
        }
        return myTarget;
    }
    
    @NotNull
    protected abstract Pattern getAssertionPattern();
    
    @Nullable
    @Override
    public Navigatable getErrorNavigatable(@NotNull final Project project, @NotNull final String s) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/CidrAbstractTestConsoleProperties", "getErrorNavigatable"));
            }
        }
        catch (NumberFormatException ex) {
            throw b((Exception)ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "stacktrace", "com/jetbrains/cidr/execution/testing/CidrAbstractTestConsoleProperties", "getErrorNavigatable"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b((Exception)ex2);
        }
        final Matcher matcher = this.getAssertionPattern().matcher(s);
        if (matcher.find()) {
            final String group = matcher.group(1);
            final String group2 = matcher.group(2);
            final VirtualFile fileByPath = LocalFileSystem.getInstance().findFileByPath(normalizePath(group));
            try {
                if (fileByPath == null) {
                    return null;
                }
            }
            catch (NumberFormatException ex3) {
                throw b((Exception)ex3);
            }
            int n;
            try {
                n = Integer.valueOf(group2) - 1;
            }
            catch (NumberFormatException ex4) {
                return null;
            }
            return this.findSuitableNavigatableForLine(project, fileByPath, n);
        }
        return null;
    }
    
    @NotNull
    public static String normalizePath(@NotNull String canonicalPath) {
        try {
            if (canonicalPath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileName", "com/jetbrains/cidr/execution/testing/CidrAbstractTestConsoleProperties", "normalizePath"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        String s = null;
        Label_0085: {
            try {
                if (!ApplicationManagerEx.getApplicationEx().isUnitTestMode() || !FileUtil.isAbsolute(canonicalPath)) {
                    break Label_0085;
                }
            }
            catch (IOException ex2) {
                throw b(ex2);
            }
            try {
                canonicalPath = new File(canonicalPath).getCanonicalPath();
            }
            catch (IOException ex4) {}
            try {
                s = canonicalPath;
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrAbstractTestConsoleProperties", "normalizePath"));
                }
            }
            catch (IOException ex3) {
                throw b(ex3);
            }
        }
        return s;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
