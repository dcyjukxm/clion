// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import java.util.Collections;
import java.util.List;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;

private static class PerConfigurationTargetInfo
{
    @NotNull
    final CMakeEnvironment environment;
    @NotNull
    final File cmakeListsFile;
    @NotNull
    final File generatedDir;
    @NotNull
    final CMakeCacheFile cacheFile;
    @Nullable
    final CMakeGeneratorType generatorType;
    @NotNull
    final File targetDir;
    @NotNull
    final File buildWorkingDir;
    @NotNull
    final String targetName;
    @NotNull
    final String configName;
    @NotNull
    Map<OCLanguageKind, CMakeConfigurationSettings> perLanguageSettings;
    @NotNull
    Map<File, CMakeFileSettings> sources;
    @Nullable
    File productFile;
    @Nullable
    List<String> linkerFlags;
    
    private PerConfigurationTargetInfo(@NotNull final CMakeEnvironment environment, @NotNull final File cmakeListsFile, @NotNull final File generatedDir, @NotNull final CMakeCacheFile cacheFile, @Nullable final CMakeGeneratorType generatorType, @NotNull final File targetDir, @NotNull final String targetName, @NotNull final String configName, @NotNull final File buildWorkingDir) {
        if (environment == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo", "<init>"));
        }
        if (cmakeListsFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeListsFile", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo", "<init>"));
        }
        if (generatedDir == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "generatedDir", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo", "<init>"));
        }
        if (cacheFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cacheFile", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo", "<init>"));
        }
        if (targetDir == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetDir", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo", "<init>"));
        }
        if (targetName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetName", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo", "<init>"));
        }
        if (configName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configName", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo", "<init>"));
        }
        if (buildWorkingDir == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildWorkingDir", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$PerConfigurationTargetInfo", "<init>"));
        }
        this.perLanguageSettings = Collections.emptyMap();
        this.sources = Collections.emptyMap();
        this.environment = environment;
        this.cmakeListsFile = cmakeListsFile;
        this.generatedDir = generatedDir;
        this.cacheFile = cacheFile;
        this.generatorType = generatorType;
        this.targetDir = targetDir;
        this.targetName = targetName;
        this.buildWorkingDir = buildWorkingDir;
        this.configName = configName;
    }
}
