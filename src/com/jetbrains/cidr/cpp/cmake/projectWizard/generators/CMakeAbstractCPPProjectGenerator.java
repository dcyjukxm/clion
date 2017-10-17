// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.CMakeCPPProjectSettings;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.CMakeProjectSettings;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeRecognizedCPPLanguageStandard;

public abstract class CMakeAbstractCPPProjectGenerator extends CMakeProjectGenerator
{
    public static final String CPP_PROJECTS_GROUP_NAME = "C++";
    
    public CMakeAbstractCPPProjectGenerator() {
        this.myLanguageVersion = CMakeRecognizedCPPLanguageStandard.CPP11.getDisplayString();
    }
    
    @NotNull
    @Override
    public String getGroupName() {
        String s;
        try {
            s = "C++";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeAbstractCPPProjectGenerator", "getGroupName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    public String[] getLanguageVersions() {
        return (String[])ContainerUtil.map2Array((Object[])CMakeRecognizedCPPLanguageStandard.values(), (Class)String.class, cMakeRecognizedCPPLanguageStandard -> cMakeRecognizedCPPLanguageStandard.getDisplayString());
    }
    
    @Nullable
    @Override
    public CMakeProjectSettings createProjectSettings() {
        return new CMakeCPPProjectSettings();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
