// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.toolchains.GDB;
import java.io.File;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import java.util.List;
import com.jetbrains.cidr.cpp.toolchains.CPPEnvironment;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.cpp.toolchains.GDBEnvironment;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.toolchains.EnvironmentProblems;
import kotlin.Metadata;
import com.jetbrains.cidr.cpp.execution.debugger.backend.GDBDriverConfiguration;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014¨\u0006\u0007" }, d2 = { "com/jetbrains/cidr/cpp/execution/remote/RemoteGDBLauncher$createDebugProcess$configuration$1", "Lcom/jetbrains/cidr/cpp/execution/debugger/backend/GDBDriverConfiguration;", "(Lcom/jetbrains/cidr/cpp/execution/remote/RemoteGDBLauncher;)V", "createGDBEnvironment", "Lcom/jetbrains/cidr/cpp/toolchains/GDBEnvironment;", "problems", "Lcom/jetbrains/cidr/toolchains/EnvironmentProblems;", "clion" })
public static final class RemoteGDBLauncher$createDebugProcess$configuration$1 extends GDBDriverConfiguration {
    @Nullable
    @Override
    protected GDBEnvironment createGDBEnvironment(@NotNull final EnvironmentProblems environmentProblems) {
        Intrinsics.checkParameterIsNotNull((Object)environmentProblems, "problems");
        if (this.this$0.getMyDebugger() == null) {
            return super.createGDBEnvironment(environmentProblems);
        }
        final CPPEnvironment create = CPPEnvironment.create(environmentProblems, CollectionsKt.emptyList());
        if (create != null) {
            return GDBEnvironment.create(environmentProblems, new GDB(new File(this.this$0.getMyDebugger()), create.getToolSet()));
        }
        return null;
    }
}