// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.CMakeLanguage;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;

public class CMakeCodeStyleSettingsProvider extends CodeStyleSettingsProvider
{
    @Nullable
    public Language getLanguage() {
        return CMakeLanguage.INSTANCE;
    }
    
    @Nullable
    public CustomCodeStyleSettings createCustomSettings(final CodeStyleSettings codeStyleSettings) {
        return new CMakeCodeStyleSettings(codeStyleSettings);
    }
    
    @NotNull
    public Configurable createSettingsPage(@NotNull final CodeStyleSettings codeStyleSettings, @NotNull final CodeStyleSettings codeStyleSettings2) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettingsProvider", "createSettingsPage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (codeStyleSettings2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "originalSettings", "com/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettingsProvider", "createSettingsPage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        CodeStyleAbstractConfigurable codeStyleAbstractConfigurable;
        try {
            codeStyleAbstractConfigurable = new CodeStyleAbstractConfigurable(codeStyleSettings, codeStyleSettings2, this.getConfigurableDisplayName()) {
                @Nullable
                public String getHelpTopic() {
                    return "Code_Style_CMake";
                }
                
                @Override
                protected CodeStyleAbstractPanel createPanel(final CodeStyleSettings codeStyleSettings) {
                    return new CMakeTabbedLanguageCodeStylePanel(this.getCurrentSettings(), codeStyleSettings);
                }
            };
            if (codeStyleAbstractConfigurable == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettingsProvider", "createSettingsPage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (Configurable)codeStyleAbstractConfigurable;
    }
    
    @NotNull
    public DisplayPriority getPriority() {
        DisplayPriority language_SETTINGS;
        try {
            language_SETTINGS = DisplayPriority.LANGUAGE_SETTINGS;
            if (language_SETTINGS == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettingsProvider", "getPriority"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return language_SETTINGS;
    }
    
    @NotNull
    public String getConfigurableDisplayName() {
        String displayName;
        try {
            displayName = CMakeLanguage.INSTANCE.getDisplayName();
            if (displayName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettingsProvider", "getConfigurableDisplayName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return displayName;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
