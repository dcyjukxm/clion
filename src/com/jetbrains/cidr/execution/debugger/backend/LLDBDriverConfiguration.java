// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.execution.debugger.backend.lldb.LLDBEvaluationContext;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import com.intellij.openapi.util.Expirable;
import java.util.Map;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import com.jetbrains.cidr.execution.Installer;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerPathManager;
import com.intellij.openapi.util.SystemInfo;
import java.io.File;
import com.jetbrains.cidr.execution.debugger.backend.lldb.LLDBDriver;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.execution.configurations.GeneralCommandLine;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.util.ExecUtil;
import org.jetbrains.annotations.NotNull;

public class LLDBDriverConfiguration extends DebuggerDriverConfiguration
{
    @NotNull
    @Override
    public String getDriverName() {
        String s;
        try {
            s = "LLDB";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "getDriverName"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Nullable
    public String readVersion() throws ExecutionException {
        final GeneralCommandLine a = this.a();
        a.addParameter("--version");
        final ProcessOutput execAndGetOutput = ExecUtil.execAndGetOutput(a);
        try {
            if (execAndGetOutput.getExitCode() != 0) {
                throw new ExecutionException(execAndGetOutput.getStderr());
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        return (String)ContainerUtil.getFirstItem((Collection)execAndGetOutput.getStdoutLines(), (Object)"");
    }
    
    @Override
    public boolean isAttachSupported() {
        return true;
    }
    
    @NotNull
    @Override
    public DebuggerDriver createDriver(@NotNull final DebuggerDriver.Handler handler) {
        try {
            if (handler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "createDriver"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        LLDBDriver lldbDriver;
        try {
            lldbDriver = new LLDBDriver(handler, this);
            if (lldbDriver == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "createDriver"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return lldbDriver;
    }
    
    @NotNull
    protected File getLLDBFrameworkFile() {
        File lldbBinFile = null;
        Label_0033: {
            try {
                if (SystemInfo.isWindows) {
                    final String s = "liblldb.dll";
                    break Label_0033;
                }
            }
            catch (IllegalStateException ex) {
                throw b(ex);
            }
            try {
                if (SystemInfo.isLinux) {
                    final String s = "lib/liblldb.so";
                    break Label_0033;
                }
            }
            catch (IllegalStateException ex2) {
                throw b(ex2);
            }
            final String s = "LLDB.framework";
            try {
                lldbBinFile = this.getLLDBBinFile(s);
                if (lldbBinFile == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "getLLDBFrameworkFile"));
                }
            }
            catch (IllegalStateException ex3) {
                throw b(ex3);
            }
        }
        return lldbBinFile;
    }
    
    @NotNull
    protected File getLLDBBinFile(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "relativePath", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "getLLDBBinFile"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        File lldbBinFile;
        try {
            lldbBinFile = CidrDebuggerPathManager.getLLDBBinFile(s);
            if (lldbBinFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "getLLDBBinFile"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return lldbBinFile;
    }
    
    @NotNull
    @Override
    public GeneralCommandLine createDriverCommandLine(@NotNull final DebuggerDriver debuggerDriver, @NotNull final Installer installer) throws ExecutionException {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "createDriverCommandLine"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (installer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "installer", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "createDriverCommandLine"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        final GeneralCommandLine a = this.a();
        GeneralCommandLine generalCommandLine;
        try {
            a.addParameter(String.valueOf(((LLDBDriver)debuggerDriver).getPort()));
            this.configureDriverCommandLine(a);
            generalCommandLine = a;
            if (generalCommandLine == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "createDriverCommandLine"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        return generalCommandLine;
    }
    
    @NotNull
    private GeneralCommandLine a() throws ExecutionException {
        final File lldbFrameworkFile = this.getLLDBFrameworkFile();
        try {
            if (!lldbFrameworkFile.exists()) {
                throw new ExecutionException(lldbFrameworkFile + " not found");
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        String s = null;
        Label_0061: {
            try {
                if (SystemInfo.isWindows) {
                    s = "LLDBFrontend.exe";
                    break Label_0061;
                }
            }
            catch (ExecutionException ex2) {
                throw b((Exception)ex2);
            }
            s = "LLDBFrontend";
        }
        final File lldbBinFile = this.getLLDBBinFile(s);
        try {
            if (!lldbBinFile.exists()) {
                throw new ExecutionException(lldbBinFile.getAbsolutePath() + " not found");
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        final GeneralCommandLine generalCommandLine = new GeneralCommandLine();
        generalCommandLine.setExePath(lldbBinFile.getAbsolutePath());
        final Map environment = generalCommandLine.getEnvironment();
        Label_0360: {
            try {
                if (SystemInfo.isLinux) {
                    environment.put("LD_LIBRARY_PATH", lldbFrameworkFile.getParent());
                    break Label_0360;
                }
            }
            catch (ExecutionException ex4) {
                throw b((Exception)ex4);
            }
            try {
                if (SystemInfo.isWindows) {
                    environment.put("PATH", lldbFrameworkFile.getParent() + ";" + lldbBinFile.getParent());
                    break Label_0360;
                }
            }
            catch (ExecutionException ex5) {
                throw b((Exception)ex5);
            }
            if (SystemInfo.isMac) {
                environment.put("DYLD_FRAMEWORK_PATH", lldbFrameworkFile.getParent());
                final File file = new File(lldbFrameworkFile, "Resources/debugserver");
                try {
                    if (file.exists()) {
                        environment.put("LLDB_DEBUGSERVER_PATH", file.getAbsolutePath());
                        environment.put("LLDB_DEBUGSERVER_EXTRA_ARG_1", "--reverse-connect");
                    }
                }
                catch (ExecutionException ex6) {
                    throw b((Exception)ex6);
                }
                environment.put("NSUnbufferedIO", "YES");
                final File osxSystemPythonBinDir = CidrDebuggerPathManager.getOSXSystemPythonBinDir();
                Label_0333: {
                    try {
                        if (osxSystemPythonBinDir != null) {
                            environment.put("PATH", osxSystemPythonBinDir.getAbsolutePath());
                            break Label_0333;
                        }
                    }
                    catch (ExecutionException ex7) {
                        throw b((Exception)ex7);
                    }
                    CidrDebuggerLog.LOG.warn("Python bin dir not found");
                }
                generalCommandLine.putUserData((Key)LLDBDriver.ENABLE_STL_RENDERERS, (Object)true);
                environment.put("PYTHONPATH", CidrDebuggerPathManager.getBundledLLDBSTLPrettyPrinters().getAbsolutePath());
            }
            try {
                if (this.disableASLR()) {
                    environment.put("LLDB_LAUNCH_FLAG_DISABLE_ASLR", "1");
                }
            }
            catch (ExecutionException ex8) {
                throw b((Exception)ex8);
            }
        }
        GeneralCommandLine generalCommandLine2;
        try {
            DebuggerDriverConfiguration.setupCommonParameters(generalCommandLine);
            generalCommandLine2 = generalCommandLine;
            if (generalCommandLine2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "createLLDBFrontendCommandLine"));
            }
        }
        catch (ExecutionException ex9) {
            throw b((Exception)ex9);
        }
        return generalCommandLine2;
    }
    
    protected void configureDriverCommandLine(@NotNull final GeneralCommandLine generalCommandLine) {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "configureDriverCommandLine"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
    }
    
    @NotNull
    @Override
    public EvaluationContext createEvaluationContext(@NotNull final DebuggerDriver debuggerDriver, @Nullable final Expirable expirable, @NotNull final CidrStackFrame cidrStackFrame) {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "createEvaluationContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (cidrStackFrame == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "createEvaluationContext"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        LLDBEvaluationContext lldbEvaluationContext;
        try {
            lldbEvaluationContext = new LLDBEvaluationContext(debuggerDriver, expirable, cidrStackFrame);
            if (lldbEvaluationContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLDBDriverConfiguration", "createEvaluationContext"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        return lldbEvaluationContext;
    }
    
    @Override
    public boolean supportsArrayEvaluation() {
        return true;
    }
    
    @Override
    public boolean isCodeFragmentEvaluationSupported() {
        return true;
    }
    
    public boolean isStaticVarsLoadingEnabled() {
        return false;
    }
    
    public boolean disableASLR() {
        return false;
    }
    
    @Contract("null -> null")
    public String convertToProjectModelPath(@Nullable final String s) {
        return s;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
