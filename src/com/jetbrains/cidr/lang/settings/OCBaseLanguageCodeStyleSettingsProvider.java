// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;

public abstract class OCBaseLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider
{
    protected final Language myLanguage;
    
    protected OCBaseLanguageCodeStyleSettingsProvider(@NotNull final Language myLanguage) {
        if (myLanguage == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "language", "com/jetbrains/cidr/lang/settings/OCBaseLanguageCodeStyleSettingsProvider", "<init>"));
        }
        this.myLanguage = myLanguage;
    }
    
    @NotNull
    public Language getLanguage() {
        Language myLanguage;
        try {
            myLanguage = this.myLanguage;
            if (myLanguage == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCBaseLanguageCodeStyleSettingsProvider", "getLanguage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myLanguage;
    }
    
    @NotNull
    public CommonCodeStyleSettings getDefaultCommonSettings() {
        final CommonCodeStyleSettings commonCodeStyleSettings = new CommonCodeStyleSettings(this.getLanguage());
        CommonCodeStyleSettings commonCodeStyleSettings2;
        try {
            commonCodeStyleSettings.initIndentOptions();
            commonCodeStyleSettings2 = commonCodeStyleSettings;
            if (commonCodeStyleSettings2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCBaseLanguageCodeStyleSettingsProvider", "getDefaultCommonSettings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return commonCodeStyleSettings2;
    }
    
    public String getCodeSample(@NotNull final LanguageCodeStyleSettingsProvider.SettingsType settingsType) {
        try {
            if (settingsType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settingsType", "com/jetbrains/cidr/lang/settings/OCBaseLanguageCodeStyleSettingsProvider", "getCodeSample"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.getCodeSampleContent(getCodeSampleFileName(settingsType));
    }
    
    @NotNull
    public static String getCodeSampleFileName(@NotNull final LanguageCodeStyleSettingsProvider.SettingsType settingsType) {
        try {
            if (settingsType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settingsType", "com/jetbrains/cidr/lang/settings/OCBaseLanguageCodeStyleSettingsProvider", "getCodeSampleFileName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String s = null;
        switch (settingsType) {
            default: {
                s = "General";
                break;
            }
            case SPACING_SETTINGS: {
                s = "Spaces";
                break;
            }
            case BLANK_LINES_SETTINGS: {
                s = "BlankLines";
                break;
            }
            case INDENT_SETTINGS: {
                s = "Indents";
                break;
            }
        }
        String s2;
        try {
            s2 = s;
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCBaseLanguageCodeStyleSettingsProvider", "getCodeSampleFileName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return s2;
    }
    
    @NotNull
    protected abstract String getCodeSampleContent(@NotNull final String p0);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
