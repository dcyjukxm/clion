// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import com.intellij.execution.filters.TextConsoleBuilder;
import com.jetbrains.cidr.execution.debugger.remote.CidrRemoteGDBDebugProcess;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.configurations.CommandLineState;
import com.jetbrains.cidr.execution.debugger.remote.CidrRemoteDebugParameters;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;
import com.jetbrains.cidr.execution.testing.CidrLauncher;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0014J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u001d\u001a\u00020\u0003H\u0014R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001e" }, d2 = { "Lcom/jetbrains/cidr/cpp/execution/remote/RemoteGDBLauncher;", "Lcom/jetbrains/cidr/execution/testing/CidrLauncher;", "myProject", "Lcom/intellij/openapi/project/Project;", "myDebugger", "", "myParams", "Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteDebugParameters;", "(Lcom/intellij/openapi/project/Project;Ljava/lang/String;Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteDebugParameters;)V", "getMyDebugger", "()Ljava/lang/String;", "getMyParams", "()Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteDebugParameters;", "getMyProject", "()Lcom/intellij/openapi/project/Project;", "configProcessHandler", "", "state", "Lcom/intellij/execution/configurations/CommandLineState;", "handler", "Lcom/intellij/execution/process/ProcessHandler;", "detachSupported", "", "reportExitCode", "createDebugProcess", "Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;", "session", "Lcom/intellij/xdebugger/XDebugSession;", "createProcess", "getProject", "clion" })
public final class RemoteGDBLauncher extends CidrLauncher
{
    @NotNull
    private final Project myProject;
    @Nullable
    private final String myDebugger;
    @NotNull
    private final CidrRemoteDebugParameters myParams;
    
    @NotNull
    @Override
    protected Project getProject() {
        return this.myProject;
    }
    
    @Nullable
    @Override
    protected ProcessHandler createProcess(@NotNull final CommandLineState commandLineState) {
        Intrinsics.checkParameterIsNotNull((Object)commandLineState, "state");
        throw new UnsupportedOperationException();
    }
    
    @NotNull
    @Override
    protected CidrDebugProcess createDebugProcess(@NotNull final CommandLineState commandLineState, @NotNull final XDebugSession xDebugSession) {
        Intrinsics.checkParameterIsNotNull((Object)commandLineState, "state");
        Intrinsics.checkParameterIsNotNull((Object)xDebugSession, "session");
        final DebuggerDriverConfiguration debuggerDriverConfiguration = (DebuggerDriverConfiguration)new RemoteGDBLauncher$createDebugProcess$configuration.RemoteGDBLauncher$createDebugProcess$configuration$1(this);
        final CidrRemoteDebugParameters myParams = this.myParams;
        final TextConsoleBuilder consoleBuilder = commandLineState.getConsoleBuilder();
        Intrinsics.checkExpressionValueIsNotNull((Object)consoleBuilder, "state.consoleBuilder");
        return new CidrRemoteGDBDebugProcess(debuggerDriverConfiguration, myParams, xDebugSession, consoleBuilder);
    }
    
    @Override
    protected void configProcessHandler(@NotNull final CommandLineState commandLineState, @NotNull final ProcessHandler processHandler, final boolean b, final boolean b2) {
        Intrinsics.checkParameterIsNotNull((Object)commandLineState, "state");
        Intrinsics.checkParameterIsNotNull((Object)processHandler, "handler");
        super.configProcessHandler(commandLineState, processHandler, b, false);
    }
    
    @NotNull
    public final Project getMyProject() {
        return this.myProject;
    }
    
    @Nullable
    public final String getMyDebugger() {
        return this.myDebugger;
    }
    
    @NotNull
    public final CidrRemoteDebugParameters getMyParams() {
        return this.myParams;
    }
    
    public RemoteGDBLauncher(@NotNull final Project myProject, @Nullable final String myDebugger, @NotNull final CidrRemoteDebugParameters myParams) {
        Intrinsics.checkParameterIsNotNull((Object)myProject, "myProject");
        Intrinsics.checkParameterIsNotNull((Object)myParams, "myParams");
        this.myProject = myProject;
        this.myDebugger = myDebugger;
        this.myParams = myParams;
    }
}
