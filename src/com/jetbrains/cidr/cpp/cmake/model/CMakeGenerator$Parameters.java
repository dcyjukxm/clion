// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import java.util.Collections;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.cpp.cmake.CMakeEnvironment;
import java.util.Map;
import java.util.List;
import java.io.File;
import org.jetbrains.annotations.NotNull;

public static class Parameters
{
    public final int configId;
    @NotNull
    public final String configName;
    @NotNull
    public final File generationDir;
    @NotNull
    public final List<String> additionalOptions;
    public final boolean passSystemEnvironment;
    @NotNull
    public final Map<String, String> additionalEnvironment;
    @NotNull
    public final CMakeListener listener;
    @NotNull
    public final CMakeEnvironment environment;
    
    public Parameters(final int configId, @NotNull final String configName, @NotNull final File file, @NotNull final List<String> list, final boolean passSystemEnvironment, final Map<String, String> map, @NotNull final CMakeListener listener, @NotNull final CMakeEnvironment environment) {
        if (configName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configName", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters", "<init>"));
        }
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "outputDir", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "additionalOptions", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters", "<init>"));
        }
        if (listener == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "listener", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters", "<init>"));
        }
        if (environment == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "environment", "com/jetbrains/cidr/cpp/cmake/model/CMakeGenerator$Parameters", "<init>"));
        }
        this.configId = configId;
        this.configName = configName;
        this.generationDir = new File(FileUtil.toCanonicalPath(file.getPath()));
        this.additionalOptions = Collections.unmodifiableList((List<? extends String>)list);
        this.passSystemEnvironment = passSystemEnvironment;
        this.additionalEnvironment = Collections.unmodifiableMap((Map<? extends String, ? extends String>)map);
        this.listener = listener;
        this.environment = environment;
    }
}
