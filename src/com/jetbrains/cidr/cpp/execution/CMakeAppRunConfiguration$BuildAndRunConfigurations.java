// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import java.io.File;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;

public static class BuildAndRunConfigurations
{
    @NotNull
    public final CMakeConfiguration buildConfiguration;
    @Nullable
    public final CMakeConfiguration runConfiguration;
    @Nullable
    public final File runExecutable;
    @Nullable
    public final String explicitBuildTargetName;
    
    public BuildAndRunConfigurations(@NotNull final CMakeConfiguration buildConfiguration, @Nullable final CMakeConfiguration runConfiguration, @Nullable final File runExecutable, @Nullable final String explicitBuildTargetName) {
        if (buildConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildConfiguration", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations", "<init>"));
        }
        this.buildConfiguration = buildConfiguration;
        this.runConfiguration = runConfiguration;
        this.runExecutable = runExecutable;
        this.explicitBuildTargetName = explicitBuildTargetName;
    }
    
    public BuildAndRunConfigurations(@NotNull final CMakeConfiguration cMakeConfiguration) {
        if (cMakeConfiguration == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildConfiguration", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations", "<init>"));
        }
        this(cMakeConfiguration, null, null, null);
    }
    
    @NotNull
    public CMakeConfiguration getRunConfiguration() {
        CMakeConfiguration cMakeConfiguration = null;
        Label_0022: {
            try {
                if (this.runConfiguration != null) {
                    final CMakeConfiguration cMakeConfiguration2;
                    cMakeConfiguration = (cMakeConfiguration2 = this.runConfiguration);
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            CMakeConfiguration cMakeConfiguration2;
            cMakeConfiguration = (cMakeConfiguration2 = this.buildConfiguration);
            try {
                if (cMakeConfiguration2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfiguration$BuildAndRunConfigurations", "getRunConfiguration"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return cMakeConfiguration;
    }
    
    @Nullable
    public File getRunFile() {
        try {
            if (this.runExecutable != null) {
                return this.runExecutable;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.getRunConfiguration().getProductFile();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
