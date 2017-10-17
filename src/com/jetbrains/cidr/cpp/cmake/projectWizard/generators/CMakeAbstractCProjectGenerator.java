// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.CMakeCProjectSettings;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.CMakeProjectSettings;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeRecognizedCLanguageStandard;

public abstract class CMakeAbstractCProjectGenerator extends CMakeProjectGenerator
{
    private static final String C_PROJECTS_GROUP_NAME = "C";
    
    public CMakeAbstractCProjectGenerator() {
        this.myLanguageVersion = CMakeRecognizedCLanguageStandard.C99.getDisplayString();
    }
    
    @NotNull
    @Override
    public String getGroupName() {
        String s;
        try {
            s = "C";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeAbstractCProjectGenerator", "getGroupName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    public String[] getLanguageVersions() {
        return (String[])ContainerUtil.map2Array((Object[])CMakeRecognizedCLanguageStandard.values(), (Class)String.class, cMakeRecognizedCLanguageStandard -> cMakeRecognizedCLanguageStandard.getDisplayString());
    }
    
    @Nullable
    @Override
    public CMakeProjectSettings createProjectSettings() {
        return new CMakeCProjectSettings();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
