// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.jetbrains.cidr.execution.CidrCommandLineState;
import com.intellij.execution.Executor;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public interface CidrTestRunConfiguration
{
    @NotNull
    CidrTestRunConfigurationData getTestData();
    
    @Nullable
    String getTestingFrameworkName();
    
    @NotNull
    CidrLauncher createLauncher();
    
    @NotNull
    CidrCommandLineState createState(@NotNull final ExecutionEnvironment p0, @NotNull final Executor p1, @Nullable final CidrTestScope p2);
    
    @NotNull
    CidrRerunFailedTestsAction.CidrReturnTestProfile createTestRunProfile(final CidrRerunFailedTestsAction p0, final CidrTestScope p1);
}
