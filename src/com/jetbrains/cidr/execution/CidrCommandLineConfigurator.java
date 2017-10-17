// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.openapi.util.SystemInfo;
import java.util.Map;
import com.intellij.openapi.vfs.encoding.EncodingProjectManager;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.text.StringUtil;
import java.io.File;
import com.intellij.execution.configurations.SimpleProgramParameters;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class CidrCommandLineConfigurator
{
    @NotNull
    private final Project myProject;
    @NotNull
    private final SimpleProgramParameters myAppParameters;
    
    public CidrCommandLineConfigurator(@NotNull final Project myProject, @NotNull final SimpleProgramParameters myAppParameters) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/CidrCommandLineConfigurator", "<init>"));
        }
        if (myAppParameters == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "appParameters", "com/jetbrains/cidr/execution/CidrCommandLineConfigurator", "<init>"));
        }
        this.myProject = myProject;
        this.myAppParameters = myAppParameters;
    }
    
    @Nullable
    public File getAppWorkingDir() {
        final String workingDirectory = this.myAppParameters.getWorkingDirectory();
        try {
            if (StringUtil.isEmptyOrSpaces(workingDirectory)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return new File(workingDirectory);
    }
    
    public void configureCommandLine(@NotNull final GeneralCommandLine generalCommandLine) throws ExecutionException {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/execution/CidrCommandLineConfigurator", "configureCommandLine"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        generalCommandLine.setCharset(EncodingProjectManager.getInstance(this.myProject).getDefaultCharset());
        generalCommandLine.setWorkDirectory(this.getAppWorkingDir());
        generalCommandLine.getParametersList().addAll(this.myAppParameters.getProgramParametersList().getList());
        final Map environment = generalCommandLine.getEnvironment();
        try {
            environment.putAll(this.myAppParameters.getEnv());
            if (SystemInfo.isMac) {
                CidrExecUtil.setIfAbsent(environment, "NSUnbufferedIO", "YES");
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        GeneralCommandLine.ParentEnvironmentType parentEnvironmentType = null;
        Label_0148: {
            try {
                if (this.myAppParameters.isPassParentEnvs()) {
                    parentEnvironmentType = GeneralCommandLine.ParentEnvironmentType.CONSOLE;
                    break Label_0148;
                }
            }
            catch (ExecutionException ex3) {
                throw b((Exception)ex3);
            }
            parentEnvironmentType = GeneralCommandLine.ParentEnvironmentType.NONE;
        }
        generalCommandLine.withParentEnvironmentType(parentEnvironmentType);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
