// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.jetbrains.cidr.lang.toolchains.CidrCompilerSwitches;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;

public interface CidrCompiler
{
    @NotNull
    CompilerInfo collectInfo(@NotNull final OCLanguageKind p0, @NotNull final CidrCompilerSwitches p1, @NotNull final CidrToolEnvironment p2) throws ExecutionException;
}
