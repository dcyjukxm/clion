// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.remote;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.Installer;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.jetbrains.cidr.execution.TrivialInstaller;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import kotlin.Metadata;
import com.jetbrains.cidr.execution.RunParameters;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t" }, d2 = { "com/jetbrains/cidr/execution/debugger/remote/CidrRemoteGDBDebugProcessKt$createParams$1", "Lcom/jetbrains/cidr/execution/RunParameters;", "(Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriverConfiguration;)V", "getArchitectureId", "", "getDebuggerDriverConfiguration", "Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriverConfiguration;", "getInstaller", "Lcom/jetbrains/cidr/execution/TrivialInstaller;", "cidr-debugger" })
public static final class CidrRemoteGDBDebugProcessKt$createParams$1 extends RunParameters {
    @NotNull
    @Override
    public DebuggerDriverConfiguration getDebuggerDriverConfiguration() {
        return this.$driverConfiguration;
    }
    
    @NotNull
    @Override
    public TrivialInstaller getInstaller() {
        return new TrivialInstaller(new GeneralCommandLine());
    }
    
    @Nullable
    public Void getArchitectureId() {
        return null;
    }
}