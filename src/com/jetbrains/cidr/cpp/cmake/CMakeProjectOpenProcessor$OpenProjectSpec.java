// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public static class OpenProjectSpec
{
    @NotNull
    public final File sourceDir;
    @Nullable
    public final File generationDir;
    @Nullable
    public final String configuration;
    @Nullable
    public final String generator;
    
    public OpenProjectSpec(@NotNull final File sourceDir, @Nullable final File generationDir, @Nullable final String configuration, @Nullable final String generator) {
        if (sourceDir == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceDir", "com/jetbrains/cidr/cpp/cmake/CMakeProjectOpenProcessor$OpenProjectSpec", "<init>"));
        }
        this.sourceDir = sourceDir;
        this.generationDir = generationDir;
        this.configuration = configuration;
        this.generator = generator;
    }
    
    public boolean isInSourceGeneration() {
        return FileUtil.filesEqual(this.sourceDir, this.generationDir);
    }
}
