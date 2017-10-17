// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.debugger.backend;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import com.jetbrains.cidr.execution.debugger.evaluation.EvaluationContext;
import com.jetbrains.cidr.execution.debugger.CidrStackFrame;
import com.intellij.openapi.util.Expirable;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerSettings;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerPathManager;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.jetbrains.cidr.toolchains.EnvironmentProblems;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.jetbrains.cidr.execution.Installer;
import com.jetbrains.cidr.execution.debugger.backend.gdb.GDBDriver;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.openapi.util.SystemInfo;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.toolchains.GDBEnvironment;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;

public class GDBDriverConfiguration extends DebuggerDriverConfiguration
{
    private volatile GDBEnvironment myGDBEnvironment;
    
    @NotNull
    @Override
    public String getDriverName() {
        String s;
        try {
            s = "GDB";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration", "getDriverName"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Override
    public boolean isAttachSupported() {
        try {
            if (!SystemInfo.isMac) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @NotNull
    @Override
    public DebuggerDriver createDriver(@NotNull final DebuggerDriver.Handler handler) {
        try {
            if (handler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration", "createDriver"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        GDBDriver gdbDriver;
        try {
            gdbDriver = new GDBDriver(handler, this);
            if (gdbDriver == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration", "createDriver"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        return gdbDriver;
    }
    
    @NotNull
    @Override
    public GeneralCommandLine createDriverCommandLine(@NotNull final DebuggerDriver debuggerDriver, @NotNull final Installer installer) throws ExecutionException {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration", "createDriverCommandLine"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (installer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "installer", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration", "createDriverCommandLine"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        final EnvironmentProblems environmentProblems = new EnvironmentProblems();
        this.myGDBEnvironment = this.createGDBEnvironment(environmentProblems);
        environmentProblems.throwAsExecutionException();
        final GeneralCommandLine generalCommandLine = new GeneralCommandLine();
        this.myGDBEnvironment.prepare(generalCommandLine, CidrToolEnvironment.PrepareFor.RUN);
        final GeneralCommandLine withWorkDirectory = generalCommandLine.withWorkDirectory(installer.getAppWorkingDir());
        GeneralCommandLine generalCommandLine2;
        try {
            DebuggerDriverConfiguration.setupCommonParameters(withWorkDirectory);
            withWorkDirectory.addParameters(new String[] { "-interpreter=mi2" });
            withWorkDirectory.putUserData((Key)GDBDriver.PRETTY_PRINTERS_PATH, (Object)FileUtil.toSystemIndependentName(CidrDebuggerPathManager.getBundledGDBSTLPrettyPrinters().getAbsolutePath()));
            withWorkDirectory.putUserData((Key)GDBDriver.ENABLE_STL_PRETTY_PRINTERS, (Object)CidrDebuggerSettings.getInstance().STL_RENDERERS_ENABLED);
            generalCommandLine2 = withWorkDirectory;
            if (generalCommandLine2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration", "createDriverCommandLine"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
        return generalCommandLine2;
    }
    
    @Nullable
    protected GDBEnvironment createGDBEnvironment(@NotNull final EnvironmentProblems environmentProblems) {
        try {
            if (environmentProblems == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problems", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration", "createGDBEnvironment"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return GDBEnvironment.create(environmentProblems);
    }
    
    @Override
    public String convertToLocalPath(@Nullable final String s) {
        return this.myGDBEnvironment.toLocalPath(s);
    }
    
    @Override
    public String convertToEnvPath(@Nullable final String s) {
        return this.myGDBEnvironment.toEnvPath(s);
    }
    
    @NotNull
    @Override
    public EvaluationContext createEvaluationContext(@NotNull final DebuggerDriver debuggerDriver, @Nullable final Expirable expirable, @NotNull final CidrStackFrame cidrStackFrame) {
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration", "createEvaluationContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        try {
            if (cidrStackFrame == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration", "createEvaluationContext"));
            }
        }
        catch (IllegalStateException ex2) {
            throw b(ex2);
        }
        EvaluationContext evaluationContext;
        try {
            evaluationContext = new EvaluationContext(debuggerDriver, expirable, cidrStackFrame) {
                @NotNull
                @Override
                public String convertToRValue(@NotNull final LLValueData llValueData, @NotNull final Pair<LLValue, String> pair) throws DebuggerCommandException {
                    try {
                        if (llValueData == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rValue", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration$1", "convertToRValue"));
                        }
                    }
                    catch (DebuggerCommandException ex) {
                        throw b(ex);
                    }
                    try {
                        if (pair == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "valuePair", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration$1", "convertToRValue"));
                        }
                    }
                    catch (DebuggerCommandException ex2) {
                        throw b(ex2);
                    }
                    String cast;
                    try {
                        cast = EvaluationContext.cast((String)pair.getSecond(), ((LLValue)pair.getFirst()).getType());
                        if (cast == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration$1", "convertToRValue"));
                        }
                    }
                    catch (DebuggerCommandException ex3) {
                        throw b(ex3);
                    }
                    return cast;
                }
                
                private static DebuggerCommandException b(final DebuggerCommandException ex) {
                    return ex;
                }
            };
            if (evaluationContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration", "createEvaluationContext"));
            }
        }
        catch (IllegalStateException ex3) {
            throw b(ex3);
        }
        return evaluationContext;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
