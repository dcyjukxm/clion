// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.compiler;

import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.toolchains.CidrCompilerSwitches;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import java.io.File;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;

public interface OCCompilerSettings
{
    @Nullable
    OCCompilerKind getCompiler(@NotNull final OCLanguageKind p0);
    
    @Nullable
    File getCompilerExecutable(@NotNull final OCLanguageKind p0);
    
    @NotNull
    File getCompilerWorkingDir();
    
    @NotNull
    CidrToolEnvironment getEnvironment();
    
    @NotNull
    CidrCompilerSwitches getCompilerSwitches(@NotNull final OCLanguageKind p0, @Nullable final VirtualFile p1);
    
    @NotNull
    default String getCompilerKey(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings", "getCompilerKey"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String s;
        try {
            s = (String)this.getCompilerKeyAndSwitches(ocLanguageKind, virtualFile).first;
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings", "getCompilerKey"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return s;
    }
    
    @NotNull
    default Pair<String, CidrCompilerSwitches> getCompilerKeyAndSwitches(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings", "getCompilerKeyAndSwitches"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CidrCompilerSwitches compilerSwitches = this.getCompilerSwitches(ocLanguageKind, virtualFile);
        final File compilerExecutable = this.getCompilerExecutable(ocLanguageKind);
        StringBuilder sb = null;
        String path = null;
        Label_0088: {
            try {
                sb = new StringBuilder();
                if (compilerExecutable == null) {
                    path = "no compiler";
                    break Label_0088;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            path = compilerExecutable.getPath();
        }
        final StringBuilder append = sb.append(path).append("\u0000").append(this.getCompilerWorkingDir()).append("\u0000").append(ocLanguageKind.getDisplayName()).append("\u0000");
        Pair pair;
        try {
            StringUtil.join((Collection)compilerSwitches.getList(CidrCompilerSwitches.Format.RAW), "\u0000", append);
            pair = Pair.pair((Object)append.toString(), (Object)compilerSwitches);
            if (pair == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/compiler/OCCompilerSettings", "getCompilerKeyAndSwitches"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (Pair<String, CidrCompilerSwitches>)pair;
    }
    
    default IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
