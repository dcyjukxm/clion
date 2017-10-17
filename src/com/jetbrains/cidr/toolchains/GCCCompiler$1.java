// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessOutput;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.GeneralCommandLine;

static final class GCCCompiler$1 extends CompilerRunner {
    @NotNull
    @Override
    public ProcessOutput run(@NotNull final GeneralCommandLine generalCommandLine) throws ExecutionException {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/toolchains/GCCCompiler$1", "run"));
            }
        }
        catch (ExecutionException ex) {
            throw b(ex);
        }
        final ProcessOutput processOutput = new ProcessOutput(1);
        ProcessOutput processOutput2;
        try {
            processOutput.appendStderr("Emulated GCC error");
            processOutput2 = processOutput;
            if (processOutput2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/GCCCompiler$1", "run"));
            }
        }
        catch (ExecutionException ex2) {
            throw b(ex2);
        }
        return processOutput2;
    }
    
    private static ExecutionException b(final ExecutionException ex) {
        return ex;
    }
}