// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.jetbrains.cidr.cpp.cmake.CMakeException;
import java.io.File;
import com.intellij.util.containers.LinkedMultiMap;
import com.intellij.util.containers.hash.LinkedHashMap;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;

public abstract class CMakeStringSettingsFileParser extends CMakeSettingsFileParser<String>
{
    public CMakeStringSettingsFileParser() {
    }
    
    public CMakeStringSettingsFileParser(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "separator", "com/jetbrains/cidr/cpp/cmake/model/CMakeStringSettingsFileParser", "<init>"));
        }
        super(s);
    }
    
    @NotNull
    @Override
    protected Pair<String, String> parseVariable(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "beforeEqual", "com/jetbrains/cidr/cpp/cmake/model/CMakeStringSettingsFileParser", "parseVariable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "afterEqual", "com/jetbrains/cidr/cpp/cmake/model/CMakeStringSettingsFileParser", "parseVariable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Pair create;
        try {
            create = Pair.create((Object)s, (Object)s2);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeStringSettingsFileParser", "parseVariable"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (Pair<String, String>)create;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
