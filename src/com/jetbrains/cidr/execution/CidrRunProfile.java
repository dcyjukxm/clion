// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.execution.configurations.RunProfileState;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.RunProfile;

public interface CidrRunProfile extends RunProfile
{
    @Nullable
    CidrCommandLineState getState(@NotNull final Executor p0, @NotNull final ExecutionEnvironment p1) throws ExecutionException;
}
