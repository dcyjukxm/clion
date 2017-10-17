// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public abstract class CMakeProjectSettings
{
    protected String languageVersion;
    protected String libraryType;
    
    @NotNull
    public abstract String getLanguageVersionLineForCMake();
    
    public void setLanguageVersion(@NotNull final String languageVersion) {
        try {
            if (languageVersion == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageVersion", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/CMakeProjectSettings", "setLanguageVersion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.languageVersion = languageVersion;
    }
    
    @Nullable
    public String getLibraryTypeForCMake() {
        try {
            if ("shared".equals(this.libraryType)) {
                return " SHARED";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return "";
    }
    
    public void setLibraryType(@NotNull final String libraryType) {
        try {
            if (libraryType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "libraryType", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/CMakeProjectSettings", "setLibraryType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.libraryType = libraryType;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
