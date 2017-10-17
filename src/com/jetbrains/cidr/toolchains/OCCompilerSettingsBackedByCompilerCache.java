// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import com.jetbrains.cidr.lang.workspace.compiler.CidrCompilerResult;
import java.util.Collections;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerSettings;

public abstract class OCCompilerSettingsBackedByCompilerCache implements OCCompilerSettings
{
    @NotNull
    public String getPreprocessorDefines(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/toolchains/OCCompilerSettingsBackedByCompilerCache", "getPreprocessorDefines"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final CompilerInfoCache.Entry entry = this.getCompilerInfo(ocLanguageKind, virtualFile).getResult();
        String s = null;
        Label_0074: {
            try {
                if (entry != null) {
                    final String defines;
                    s = (defines = entry.defines);
                    break Label_0074;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            String defines;
            s = (defines = "");
            try {
                if (defines == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/OCCompilerSettingsBackedByCompilerCache", "getPreprocessorDefines"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return s;
    }
    
    public Map<OCCompilerFeatures.Type<?>, ?> getCompilerFeatures(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/toolchains/OCCompilerSettingsBackedByCompilerCache", "getCompilerFeatures"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final CompilerInfoCache.Entry entry = this.getCompilerInfo(ocLanguageKind, virtualFile).getResult();
        try {
            if (entry != null) {
                return entry.features;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return Collections.emptyMap();
    }
    
    @NotNull
    public abstract CidrCompilerResult<CompilerInfoCache.Entry> getCompilerInfo(@NotNull final OCLanguageKind p0, @Nullable final VirtualFile p1);
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
