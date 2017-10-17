// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.CMakeProjectSettings;

static final class CMakeProjectGenerator$1 extends CMakeProjectSettings {
    @NotNull
    @Override
    public String getLanguageVersionLineForCMake() {
        String s;
        try {
            s = "";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$1", "getLanguageVersionLineForCMake"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}