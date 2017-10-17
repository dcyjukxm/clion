// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.util.containers.LinkedMultiMap;
import com.intellij.util.Function;
import com.jetbrains.cidr.cpp.cmake.CMakeException;
import java.util.Map;
import java.io.File;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

class CMakeCacheFileParser extends CMakeSettingsFileParser<CMakeVariable>
{
    @NotNull
    private StringBuilder myDescriptionBuilder;
    
    CMakeCacheFileParser() {
        this.myDescriptionBuilder = new StringBuilder();
    }
    
    @Override
    protected void cleanup() {
        this.myDescriptionBuilder.setLength(0);
    }
    
    @Override
    protected void preprocess(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "line", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFileParser", "preprocess"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (s.startsWith("//")) {
                this.myDescriptionBuilder.append(StringUtil.trimStart(s, "//"));
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (s.isEmpty()) {
                this.myDescriptionBuilder.setLength(0);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
    }
    
    @NotNull
    @Override
    protected Pair<String, CMakeVariable> parseVariable(@NotNull String trim, @NotNull final String s) {
        try {
            if (trim == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFileParser", "parseVariable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFileParser", "parseVariable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        String trim2 = null;
        final int index = trim.indexOf(":");
        if (index >= 0) {
            trim2 = trim.substring(index + 1).trim();
            trim = trim.substring(0, index).trim();
        }
        CMakeVariable.Type type = CMakeVariable.Type.UNINITIALIZED;
        if (trim2 != null) {
            try {
                type = CMakeVariable.Type.valueOf(trim2);
            }
            catch (IllegalArgumentException ex4) {
                CPPLog.LOG.warn("Unknown CMake variable type: " + trim + ":" + trim2);
            }
        }
        final String string = this.myDescriptionBuilder.toString();
        Pair create;
        try {
            this.myDescriptionBuilder.setLength(0);
            create = Pair.create((Object)trim, (Object)new CMakeVariable(trim, string, type, s));
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFileParser", "parseVariable"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return (Pair<String, CMakeVariable>)create;
    }
    
    public Pair<String, Map<String, CMakeVariable>> parseAndUpdate(@NotNull final File file, @NotNull final String s, final Map<String, String> map) throws CMakeException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFileParser", "parseAndUpdate"));
            }
        }
        catch (CMakeException ex) {
            throw c(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFileParser", "parseAndUpdate"));
            }
        }
        catch (CMakeException ex2) {
            throw c(ex2);
        }
        return this.parseAndUpdate(CMakeSettingsFileParser.loadText(file, s), map);
    }
    
    public Pair<String, Map<String, CMakeVariable>> parseAndUpdate(@NotNull final String s, final Map<String, String> map) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFileParser", "parseAndUpdate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final com.intellij.openapi.util.Pair<String, com.intellij.util.containers.LinkedMultiMap<String, CMakeVariable>> parse = this.parse(s, (com.intellij.util.Function<com.intellij.openapi.util.Pair<String, CMakeVariable>, com.intellij.openapi.util.Pair<String, CMakeVariable>>)(pair -> {
            final String s = (String)pair.first;
            final CMakeVariable cMakeVariable = (CMakeVariable)pair.second;
            final String name = cMakeVariable.getName();
            try {
                if (!map.containsKey(name)) {
                    return pair;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            final String s2 = map.get(name);
            return Pair.create((Object)(s.substring(0, s.indexOf("=") + 1) + StringUtil.notNullize(s2)), (Object)new CMakeVariable(name, cMakeVariable.getDescription(), cMakeVariable.getType(), s2));
        }));
        return (Pair<String, Map<String, CMakeVariable>>)Pair.create(parse.first, (Object)this.toUniqueMap((com.intellij.util.containers.LinkedMultiMap<String, CMakeVariable>)parse.second));
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
