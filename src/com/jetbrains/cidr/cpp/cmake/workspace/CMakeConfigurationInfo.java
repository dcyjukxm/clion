// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.toolchains.EnvironmentProblems;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\tH\u00c6\u0003J3\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e" }, d2 = { "Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeConfigurationInfo;", "", "configurationName", "", "generationDir", "Ljava/io/File;", "environment", "Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;", "environmentProblems", "Lcom/jetbrains/cidr/toolchains/EnvironmentProblems;", "(Ljava/lang/String;Ljava/io/File;Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;Lcom/jetbrains/cidr/toolchains/EnvironmentProblems;)V", "getConfigurationName", "()Ljava/lang/String;", "getEnvironment", "()Lcom/jetbrains/cidr/cpp/cmake/CMakeEnvironment;", "getEnvironmentProblems", "()Lcom/jetbrains/cidr/toolchains/EnvironmentProblems;", "getGenerationDir", "()Ljava/io/File;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "clion" })
final class CMakeConfigurationInfo
{
    @NotNull
    private final String configurationName;
    @NotNull
    private final File generationDir;
    @Nullable
    private final CMakeEnvironment environment;
    @NotNull
    private final EnvironmentProblems environmentProblems;
    
    @NotNull
    public final String getConfigurationName() {
        return this.configurationName;
    }
    
    @NotNull
    public final File getGenerationDir() {
        return this.generationDir;
    }
    
    @Nullable
    public final CMakeEnvironment getEnvironment() {
        return this.environment;
    }
    
    @NotNull
    public final EnvironmentProblems getEnvironmentProblems() {
        return this.environmentProblems;
    }
    
    public CMakeConfigurationInfo(@NotNull final String configurationName, @NotNull final File generationDir, @Nullable final CMakeEnvironment environment, @NotNull final EnvironmentProblems environmentProblems) {
        Intrinsics.checkParameterIsNotNull((Object)configurationName, "configurationName");
        Intrinsics.checkParameterIsNotNull((Object)generationDir, "generationDir");
        Intrinsics.checkParameterIsNotNull((Object)environmentProblems, "environmentProblems");
        this.configurationName = configurationName;
        this.generationDir = generationDir;
        this.environment = environment;
        this.environmentProblems = environmentProblems;
    }
    
    @NotNull
    public final String component1() {
        return this.configurationName;
    }
    
    @NotNull
    public final File component2() {
        return this.generationDir;
    }
    
    @Nullable
    public final CMakeEnvironment component3() {
        return this.environment;
    }
    
    @NotNull
    public final EnvironmentProblems component4() {
        return this.environmentProblems;
    }
    
    @NotNull
    public final CMakeConfigurationInfo copy(@NotNull final String s, @NotNull final File file, @Nullable final CMakeEnvironment cMakeEnvironment, @NotNull final EnvironmentProblems environmentProblems) {
        Intrinsics.checkParameterIsNotNull((Object)s, "configurationName");
        Intrinsics.checkParameterIsNotNull((Object)file, "generationDir");
        Intrinsics.checkParameterIsNotNull((Object)environmentProblems, "environmentProblems");
        return new CMakeConfigurationInfo(s, file, cMakeEnvironment, environmentProblems);
    }
    
    @Override
    public String toString() {
        return "CMakeConfigurationInfo(configurationName=" + this.configurationName + ", generationDir=" + this.generationDir + ", environment=" + this.environment + ", environmentProblems=" + this.environmentProblems + ")";
    }
    
    @Override
    public int hashCode() {
        final String configurationName = this.configurationName;
        final int n = ((configurationName != null) ? configurationName.hashCode() : 0) * 31;
        final File generationDir = this.generationDir;
        final int n2 = (n + ((generationDir != null) ? generationDir.hashCode() : 0)) * 31;
        final CMakeEnvironment environment = this.environment;
        final int n3 = (n2 + ((environment != null) ? environment.hashCode() : 0)) * 31;
        final EnvironmentProblems environmentProblems = this.environmentProblems;
        return n3 + ((environmentProblems != null) ? environmentProblems.hashCode() : 0);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof CMakeConfigurationInfo) {
                final CMakeConfigurationInfo cMakeConfigurationInfo = (CMakeConfigurationInfo)o;
                if (Intrinsics.areEqual((Object)this.configurationName, (Object)cMakeConfigurationInfo.configurationName) && Intrinsics.areEqual((Object)this.generationDir, (Object)cMakeConfigurationInfo.generationDir) && Intrinsics.areEqual((Object)this.environment, (Object)cMakeConfigurationInfo.environment) && Intrinsics.areEqual((Object)this.environmentProblems, (Object)cMakeConfigurationInfo.environmentProblems)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
