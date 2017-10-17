// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.lang.Language;
import com.jetbrains.cidr.cpp.cmake.CMakeLanguage;
import com.jetbrains.cidr.lang.settings.OCCustomOption;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.application.options.codeStyle.OptionTableWithPreviewPanel;

public static class CMakeGeneralSettingsPanel extends OptionTableWithPreviewPanel
{
    private static final String CASE_OPTIONS = "Commands case";
    
    public CMakeGeneralSettingsPanel(final CodeStyleSettings codeStyleSettings) {
        super(codeStyleSettings);
        this.init();
    }
    
    @Override
    protected void initTables() {
        this.showCustomOption(CMakeCodeStyleSettings.class, "FORCE_COMMANDS_CASE", "Force commands case", "Commands case", Case.TOKEN_CASE_NAMES, Case.TOKEN_CASE_VALUES);
    }
    
    @Override
    public LanguageCodeStyleSettingsProvider.SettingsType getSettingsType() {
        return LanguageCodeStyleSettingsProvider.SettingsType.LANGUAGE_SPECIFIC;
    }
    
    @Override
    protected String getPreviewText() {
        return OCCustomOption.ResourceReader.readExampleString(this.getClass(), "formatter/Other.cmake");
    }
    
    @Override
    protected void customizeSettings() {
        this.resetDefaultNames();
        final LanguageCodeStyleSettingsProvider forLanguage = LanguageCodeStyleSettingsProvider.forLanguage((Language)CMakeLanguage.getInstance());
        if (forLanguage != null) {
            forLanguage.customizeSettings((CodeStyleSettingsCustomizable)this, this.getSettingsType());
        }
    }
    
    @Override
    protected String getTabTitle() {
        return "Other";
    }
    
    @Nullable
    @Override
    public Language getDefaultLanguage() {
        return CMakeLanguage.getInstance();
    }
}
