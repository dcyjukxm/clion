// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.openapi.progress.EmptyProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessOutput;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.GeneralCommandLine;

public static class CompilerRunner
{
    @NotNull
    public ProcessOutput run(@NotNull final GeneralCommandLine generalCommandLine) throws ExecutionException {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/toolchains/CidrCompilerBase$CompilerRunner", "run"));
            }
        }
        catch (ExecutionException ex) {
            throw a(ex);
        }
        Object progressIndicator = ProgressManager.getInstance().getProgressIndicator();
        if (progressIndicator == null) {
            progressIndicator = new EmptyProgressIndicator();
        }
        ProcessOutput runProcessWithProgressIndicator;
        try {
            runProcessWithProgressIndicator = new CapturingProcessHandler(generalCommandLine).runProcessWithProgressIndicator((ProgressIndicator)progressIndicator, 30000, true);
            if (runProcessWithProgressIndicator == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/CidrCompilerBase$CompilerRunner", "run"));
            }
        }
        catch (ExecutionException ex2) {
            throw a(ex2);
        }
        return runProcessWithProgressIndicator;
    }
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
}
