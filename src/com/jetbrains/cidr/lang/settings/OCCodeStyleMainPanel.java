// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.openapi.extensions.Extensions;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;

public class OCCodeStyleMainPanel extends TabbedLanguageCodeStylePanel
{
    public OCCodeStyleMainPanel(final CodeStyleSettings codeStyleSettings, final CodeStyleSettings codeStyleSettings2) {
        super(OCLanguage.getInstance(), codeStyleSettings, codeStyleSettings2);
    }
    
    @Override
    protected void initTabs(final CodeStyleSettings codeStyleSettings) {
        super.initTabs(codeStyleSettings);
        for (final CodeStyleSettingsProvider codeStyleSettingsProvider : (CodeStyleSettingsProvider[])Extensions.getExtensions(CodeStyleSettingsProvider.EXTENSION_POINT_NAME)) {
            if (codeStyleSettingsProvider.getLanguage() == OCLanguage.getInstance() && !codeStyleSettingsProvider.hasSettingsPage()) {
                this.createTab(codeStyleSettingsProvider);
            }
        }
    }
}
