// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.openapi.util.Pair;
import java.io.IOException;
import com.intellij.openapi.util.io.FileUtil;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import com.jetbrains.cidr.cpp.cmake.CMakeException;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public class CMakeConfigurator
{
    @NotNull
    private final File myFile;
    @NotNull
    private final String myEncoding;
    @NotNull
    private volatile CMakeCacheFile myCache;
    
    CMakeConfigurator(@NotNull final File myFile, @NotNull final String myEncoding) throws CMakeException {
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cacheFile", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfigurator", "<init>"));
        }
        if (myEncoding == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfigurator", "<init>"));
        }
        this.myFile = myFile;
        this.myEncoding = myEncoding;
        this.myCache = new CMakeCacheFile(this.myFile, this.myEncoding);
    }
    
    @NotNull
    public File getCacheFile() {
        File myFile;
        try {
            myFile = this.myFile;
            if (myFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfigurator", "getCacheFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFile;
    }
    
    public Collection<CMakeVariable> getValues() {
        return this.myCache.getVariables();
    }
    
    @Nullable
    public CMakeVariable findVariable(@Nullable final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myCache.getVariable(s);
    }
    
    public void updateVariables(final Map<String, String> map) throws CMakeException {
        final Pair<String, Map<String, CMakeVariable>> andUpdate = new CMakeCacheFileParser().parseAndUpdate(this.myFile, this.myEncoding, map);
        try {
            FileUtil.writeToFile(this.myFile, ((String)andUpdate.first).getBytes(this.myEncoding), false);
            this.myCache = new CMakeCacheFile((Map<String, CMakeVariable>)andUpdate.second);
        }
        catch (IOException ex) {
            throw new CMakeException("Cannot write file " + this.myFile, ex);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
