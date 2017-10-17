// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.openapi.progress.EmptyProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import java.util.regex.Matcher;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.Set;
import com.jetbrains.cidr.CidrLog;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.util.Function;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import org.jetbrains.annotations.NotNull;

public abstract class CidrCompilerBase extends CidrExecutableTool implements CidrCompiler
{
    protected static final int COMPILER_TIMEOUT = 30000;
    private static final CompilerRunner DEFAULT_RUNNER;
    @NotNull
    protected static volatile CompilerRunner outCompilerRunner;
    @NotNull
    protected final File myWorkingDirectory;
    
    public static void setCompilerRunnerInTests(@Nullable final CompilerRunner compilerRunner) {
        CompilerRunner default_RUNNER = null;
        Label_0015: {
            try {
                if (compilerRunner == null) {
                    default_RUNNER = CidrCompilerBase.DEFAULT_RUNNER;
                    break Label_0015;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            default_RUNNER = compilerRunner;
        }
        CidrCompilerBase.outCompilerRunner = default_RUNNER;
    }
    
    public CidrCompilerBase(@NotNull final File file, @NotNull final File myWorkingDirectory) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "executable", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "<init>"));
        }
        if (myWorkingDirectory == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "workingDirectory", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "<init>"));
        }
        super(file);
        this.myWorkingDirectory = myWorkingDirectory;
    }
    
    @Nullable
    protected String doReadVersion(@NotNull final List<String> list, final Function<ProcessOutput, String> function) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "doReadVersion"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        final GeneralCommandLine generalCommandLine = new GeneralCommandLine();
        generalCommandLine.setExePath(this.getExecutablePath());
        generalCommandLine.addParameters((List)list);
        ProcessOutput run;
        try {
            run = CidrCompilerBase.outCompilerRunner.run(generalCommandLine);
            try {
                if (run.isTimeout()) {
                    throw new ExecutionException("process timed out");
                }
            }
            catch (ExecutionException ex2) {
                throw b((Exception)ex2);
            }
        }
        catch (ExecutionException ex3) {
            CidrLog.LOG.info("Cannot read compiler version: " + generalCommandLine.getCommandLineString(), (Throwable)ex3);
            return null;
        }
        final String s = (String)function.fun((Object)run);
        try {
            if (s == null) {
                CidrLog.LOG.info("Cannot read compiler version: " + generalCommandLine.getCommandLineString() + "\n" + run.getStderr() + "\n" + run.getStdout());
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
        return s;
    }
    
    protected static void checkCompilerOutput(@NotNull final ProcessOutput processOutput, @NotNull final String s) throws ExecutionException {
        try {
            if (processOutput == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "output", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "checkCompilerOutput"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "userFriendlyCommandLine", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "checkCompilerOutput"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (processOutput.isTimeout()) {
                throw throwCompilerTimeout(s);
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (processOutput.getExitCode() != 0) {
                throw throwCompilerError(processOutput, s);
            }
        }
        catch (ExecutionException ex4) {
            throw b((Exception)ex4);
        }
    }
    
    protected static ExecutionException throwCompilerTimeout(@NotNull final String s) throws ExecutionException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "userFriendlyCommandLine", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "throwCompilerTimeout"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        throw new ExecutionException("Compiler command timed out: " + s);
    }
    
    protected static ExecutionException throwCompilerError(@NotNull final ProcessOutput processOutput, @NotNull final String s) throws ExecutionException {
        try {
            if (processOutput == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "output", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "throwCompilerError"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "userFriendlyCommandLine", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "throwCompilerError"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        throw new ExecutionException("Compiler exited with error code " + processOutput.getExitCode() + ": " + s + "\n" + processOutput.getStderr() + "\n");
    }
    
    protected static boolean collectOptionsToSkip(@NotNull final List<String> list, @NotNull final Set<String> set, @NotNull final List<String> list2, final Pattern... array) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lines", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "collectOptionsToSkip"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "skipOptions", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "collectOptionsToSkip"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "warnLog", "com/jetbrains/cidr/toolchains/CidrCompilerBase", "collectOptionsToSkip"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        boolean b = false;
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String trim = iterator.next().trim();
            for (int length = array.length, i = 0; i < length; ++i) {
                final Matcher matcher = array[i].matcher(trim);
                if (matcher.matches()) {
                    b |= set.add(matcher.group(1));
                    list2.add(matcher.group(0));
                    break;
                }
            }
        }
        return b;
    }
    
    static {
        DEFAULT_RUNNER = new CompilerRunner();
        CidrCompilerBase.outCompilerRunner = CidrCompilerBase.DEFAULT_RUNNER;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
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
}
