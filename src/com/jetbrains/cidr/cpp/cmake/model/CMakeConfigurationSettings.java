// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.openapi.util.io.FileUtil;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

public class CMakeConfigurationSettings implements Serializable
{
    @NotNull
    private final CMakeCompiler myCompiler;
    @Nullable
    private final File myCompilerExecutable;
    @NotNull
    private final List<String> myFlags;
    @NotNull
    private final List<String> myDefines;
    
    public CMakeConfigurationSettings(@NotNull final CMakeCompiler myCompiler, @Nullable final File myCompilerExecutable, @NotNull final List<String> list, @NotNull final List<String> list2) {
        if (myCompiler == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compiler", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfigurationSettings", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "flags", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfigurationSettings", "<init>"));
        }
        if (list2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "defines", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfigurationSettings", "<init>"));
        }
        this.myCompiler = myCompiler;
        this.myCompilerExecutable = myCompilerExecutable;
        this.myFlags = Collections.unmodifiableList((List<? extends String>)list);
        this.myDefines = Collections.unmodifiableList((List<? extends String>)list2);
    }
    
    @NotNull
    public CMakeCompiler getCompiler() {
        CMakeCompiler myCompiler;
        try {
            myCompiler = this.myCompiler;
            if (myCompiler == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfigurationSettings", "getCompiler"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myCompiler;
    }
    
    @Nullable
    public File getCompilerExecutable() {
        return this.myCompilerExecutable;
    }
    
    @NotNull
    public List<String> getFlags() {
        List<String> myFlags;
        try {
            myFlags = this.myFlags;
            if (myFlags == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfigurationSettings", "getFlags"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFlags;
    }
    
    @NotNull
    public List<String> getDefines() {
        List<String> myDefines;
        try {
            myDefines = this.myDefines;
            if (myDefines == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfigurationSettings", "getDefines"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myDefines;
    }
    
    @Override
    public String toString() {
        return "ConfigurationSettings: {compilers='" + this.myCompiler + "'@'" + this.myCompiler + "', flags='" + this.myFlags + "', defines='" + this.myDefines + "'}";
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final CMakeConfigurationSettings cMakeConfigurationSettings = this;
                final Class<? extends CMakeConfigurationSettings> clazz = cMakeConfigurationSettings.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final CMakeConfigurationSettings cMakeConfigurationSettings = this;
                final Class<? extends CMakeConfigurationSettings> clazz = cMakeConfigurationSettings.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final CMakeConfigurationSettings cMakeConfigurationSettings2 = (CMakeConfigurationSettings)o;
        try {
            if (!this.myCompiler.equals(cMakeConfigurationSettings2.myCompiler)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!FileUtil.filesEqual(this.myCompilerExecutable, cMakeConfigurationSettings2.myCompilerExecutable)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!this.myFlags.equals(cMakeConfigurationSettings2.myFlags)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (!this.myDefines.equals(cMakeConfigurationSettings2.myDefines)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * (31 * this.myCompiler.hashCode() + FileUtil.fileHashCode(this.myCompilerExecutable)) + this.myFlags.hashCode()) + this.myDefines.hashCode();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
