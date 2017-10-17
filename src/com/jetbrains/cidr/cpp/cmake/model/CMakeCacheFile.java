// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import java.util.Collections;
import com.jetbrains.cidr.cpp.cmake.CMakeException;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.Map;

public class CMakeCacheFile
{
    public static final String DEFAULT_PROJECT_NAME = "Project";
    private final Map<String, CMakeVariable> myVariables;
    
    public CMakeCacheFile(@NotNull final File file, @NotNull final String s) throws CMakeException {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmakeCacheFile", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile", "<init>"));
        }
        this((Map<String, CMakeVariable>)new CMakeCacheFileParser().parseUnique(file, s));
    }
    
    CMakeCacheFile(@NotNull final Map<String, CMakeVariable> map) {
        if (map == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "variables", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile", "<init>"));
        }
        this.myVariables = Collections.unmodifiableMap((Map<? extends String, ? extends CMakeVariable>)map);
    }
    
    @NotNull
    public Collection<CMakeVariable> getVariables() {
        Collection<CMakeVariable> values;
        try {
            values = this.myVariables.values();
            if (values == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile", "getVariables"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return values;
    }
    
    @Nullable
    public CMakeVariable getVariable(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile", "getVariable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myVariables.get(s);
    }
    
    @Nullable
    public String getVariableValue(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile", "getVariableValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CMakeVariable variable = this.getVariable(s);
        try {
            if (variable == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return variable.getValue();
    }
    
    @Nullable
    public CMakeGeneratorType getGeneratorType() {
        return CMakeGeneratorType.fromGeneratorSpec(this.getVariableValue("CMAKE_GENERATOR"));
    }
    
    @Nullable
    public String getProjectName() {
        return this.getVariableValue("CMAKE_PROJECT_NAME");
    }
    
    @NotNull
    public String getProjectNameValueOrDefault() {
        final String projectName = this.getProjectName();
        String s = null;
        Label_0022: {
            try {
                if (StringUtil.isEmptyOrSpaces(projectName)) {
                    final String s2;
                    s = (s2 = "Project");
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            String s2;
            s = (s2 = projectName);
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeCacheFile", "getProjectNameValueOrDefault"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    @Nullable
    public String getConfigurationTypesValue() {
        return this.getVariableValue("CMAKE_CONFIGURATION_TYPES");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
