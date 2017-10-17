// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeRecognizedCPPLanguageStandard;

public class CMakeCPPProjectSettings extends CMakeProjectSettings
{
    @NotNull
    @Override
    public String getLanguageVersionLineForCMake() {
        String string;
        try {
            string = "set(CMAKE_CXX_STANDARD " + CMakeRecognizedCPPLanguageStandard.fromDisplayString(this.languageVersion) + ")\n";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/CMakeCPPProjectSettings", "getLanguageVersionLineForCMake"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
