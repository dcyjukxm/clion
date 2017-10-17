// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.remote;

import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.execution.RunParameters;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 2, d1 = { "\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004" }, d2 = { "createParams", "Lcom/jetbrains/cidr/execution/RunParameters;", "driverConfiguration", "Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerDriverConfiguration;", "cidr-debugger" })
public final class CidrRemoteGDBDebugProcessKt
{
    @NotNull
    public static final RunParameters createParams(@NotNull final DebuggerDriverConfiguration debuggerDriverConfiguration) {
        Intrinsics.checkParameterIsNotNull((Object)debuggerDriverConfiguration, "driverConfiguration");
        return (RunParameters)new CidrRemoteGDBDebugProcessKt$createParams.CidrRemoteGDBDebugProcessKt$createParams$1(debuggerDriverConfiguration);
    }
}
