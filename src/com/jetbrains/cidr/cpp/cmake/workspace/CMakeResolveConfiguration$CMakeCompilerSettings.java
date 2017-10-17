// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerSettings;
import com.jetbrains.cidr.toolchains.CompilerInfoCache;
import com.jetbrains.cidr.lang.workspace.compiler.CidrCompilerResult;
import java.util.List;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.lang.toolchains.CidrSwitchBuilder;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.jetbrains.cidr.lang.toolchains.CidrCompilerSwitches;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import java.io.File;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfigurationSettings;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerKind;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.toolchains.OCCompilerSettingsBackedByCompilerCache;

private class CMakeCompilerSettings extends OCCompilerSettingsBackedByCompilerCache
{
    @Nullable
    @Override
    public OCCompilerKind getCompiler(@NotNull final OCLanguageKind ocLanguageKind) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompiler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final CMakeConfigurationSettings settings = CMakeResolveConfiguration.access$100(CMakeResolveConfiguration.this).getSettings(ocLanguageKind);
        try {
            if (settings == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return settings.getCompiler().getCompilerKind();
    }
    
    @Nullable
    @Override
    public File getCompilerExecutable(@NotNull final OCLanguageKind ocLanguageKind) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerExecutable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final CMakeConfigurationSettings settings = CMakeResolveConfiguration.access$100(CMakeResolveConfiguration.this).getSettings(ocLanguageKind);
        try {
            if (settings == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return settings.getCompilerExecutable();
    }
    
    @NotNull
    @Override
    public File getCompilerWorkingDir() {
        File buildWorkingDir;
        try {
            buildWorkingDir = CMakeResolveConfiguration.access$100(CMakeResolveConfiguration.this).getBuildWorkingDir();
            if (buildWorkingDir == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerWorkingDir"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return buildWorkingDir;
    }
    
    @NotNull
    @Override
    public CidrToolEnvironment getEnvironment() {
        CidrToolEnvironment access$200;
        try {
            access$200 = CMakeResolveConfiguration.access$200(CMakeResolveConfiguration.this);
            if (access$200 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getEnvironment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return access$200;
    }
    
    @NotNull
    @Override
    public CidrCompilerSwitches getCompilerSwitches(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerSwitches"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        CMakeConfiguration access$100 = null;
        File virtualToIoFile = null;
        Label_0068: {
            try {
                access$100 = CMakeResolveConfiguration.access$100(CMakeResolveConfiguration.this);
                if (virtualFile == null) {
                    virtualToIoFile = null;
                    break Label_0068;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            virtualToIoFile = VfsUtilCore.virtualToIoFile(virtualFile);
        }
        final List<String> combinedCompilerFlags = access$100.getCombinedCompilerFlags(ocLanguageKind, virtualToIoFile);
        CidrCompilerSwitches build;
        try {
            build = new CidrSwitchBuilder().addAllRaw(combinedCompilerFlags).build();
            if (build == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerSwitches"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return build;
    }
    
    @NotNull
    @Override
    public CidrCompilerResult<CompilerInfoCache.Entry> getCompilerInfo(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        CidrCompilerResult<CompilerInfoCache.Entry> compilerInfoCache;
        try {
            compilerInfoCache = CMakeResolveConfiguration.access$400(CMakeResolveConfiguration.this).getCompilerInfoCache(CMakeResolveConfiguration.access$300(CMakeResolveConfiguration.this), this, ocLanguageKind, virtualFile);
            if (compilerInfoCache == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeResolveConfiguration$CMakeCompilerSettings", "getCompilerInfo"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return compilerInfoCache;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
