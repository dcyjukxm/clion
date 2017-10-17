// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.remote;

import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.xdebugger.XDebugSession;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import java.io.File;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.gdb.GDBDriver;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0015J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0015J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0014" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteGDBDebugProcess;", "Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;", "driverConfiguration", "Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriverConfiguration;", "parameters", "Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteDebugParameters;", "session", "Lcom/intellij/xdebugger/XDebugSession;", "consoleBuilder", "Lcom/intellij/execution/filters/TextConsoleBuilder;", "(Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriverConfiguration;Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteDebugParameters;Lcom/intellij/xdebugger/XDebugSession;Lcom/intellij/execution/filters/TextConsoleBuilder;)V", "getParameters", "()Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteDebugParameters;", "doLaunchTarget", "", "driver", "Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriver;", "doStart", "isDetachDefault", "", "cidr-debugger" })
public final class CidrRemoteGDBDebugProcess extends CidrDebugProcess
{
    @NotNull
    private final CidrRemoteDebugParameters parameters;
    
    @Override
    public boolean isDetachDefault() {
        return true;
    }
    
    @Override
    protected void doStart(@NotNull final DebuggerDriver debuggerDriver) throws ExecutionException {
        Intrinsics.checkParameterIsNotNull((Object)debuggerDriver, "driver");
        final GDBDriver gdbDriver = (GDBDriver)debuggerDriver;
        final String s = this.parameters.getSymbolFile();
        final GDBDriver gdbDriver2 = gdbDriver;
        boolean b = false;
        Label_0040: {
            try {
                if (s.length() == 0) {
                    b = true;
                    break Label_0040;
                }
            }
            catch (ExecutionException ex) {
                throw a(ex);
            }
            b = false;
        }
        final boolean b2 = b;
        GDBDriver gdbDriver3 = null;
        File file = null;
        Label_0070: {
            try {
                gdbDriver3 = gdbDriver2;
                if (b2) {
                    file = null;
                    break Label_0070;
                }
            }
            catch (ExecutionException ex2) {
                throw a(ex2);
            }
            file = new File(this.parameters.getSymbolFile());
        }
        final String s2 = this.parameters.getSysroot();
        final File file2 = file;
        final GDBDriver gdbDriver4 = gdbDriver3;
        boolean b3 = false;
        Label_0102: {
            try {
                if (s2.length() == 0) {
                    b3 = true;
                    break Label_0102;
                }
            }
            catch (ExecutionException ex3) {
                throw a(ex3);
            }
            b3 = false;
        }
        final boolean b4 = b3;
        GDBDriver gdbDriver5 = null;
        File file3 = null;
        File file4 = null;
        Label_0134: {
            try {
                gdbDriver5 = gdbDriver4;
                file3 = file2;
                if (b4) {
                    file4 = null;
                    break Label_0134;
                }
            }
            catch (ExecutionException ex4) {
                throw a(ex4);
            }
            file4 = new File(this.parameters.getSysroot());
        }
        gdbDriver5.loadForRemote(file3, file4, this.parameters.driverPathMapping());
    }
    
    @Override
    protected void doLaunchTarget(@NotNull final DebuggerDriver debuggerDriver) throws ExecutionException {
        Intrinsics.checkParameterIsNotNull((Object)debuggerDriver, "driver");
        ((GDBDriver)debuggerDriver).connectTo(this.parameters.getRemoteCommand());
    }
    
    @NotNull
    public final CidrRemoteDebugParameters getParameters() {
        return this.parameters;
    }
    
    public CidrRemoteGDBDebugProcess(@NotNull final DebuggerDriverConfiguration debuggerDriverConfiguration, @NotNull final CidrRemoteDebugParameters parameters, @NotNull final XDebugSession xDebugSession, @NotNull final TextConsoleBuilder textConsoleBuilder) throws ExecutionException {
        Intrinsics.checkParameterIsNotNull((Object)debuggerDriverConfiguration, "driverConfiguration");
        Intrinsics.checkParameterIsNotNull((Object)parameters, "parameters");
        Intrinsics.checkParameterIsNotNull((Object)xDebugSession, "session");
        Intrinsics.checkParameterIsNotNull((Object)textConsoleBuilder, "consoleBuilder");
        super(CidrRemoteGDBDebugProcessKt.createParams(debuggerDriverConfiguration), xDebugSession, textConsoleBuilder);
        this.parameters = parameters;
    }
    
    private static ExecutionException a(final ExecutionException ex) {
        return ex;
    }
}
