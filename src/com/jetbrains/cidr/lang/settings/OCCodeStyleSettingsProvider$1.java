// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.util.PlatformUtils;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.application.options.CodeStyleAbstractConfigurable;

class OCCodeStyleSettingsProvider$1 extends CodeStyleAbstractConfigurable {
    @Override
    protected CodeStyleAbstractPanel createPanel(final CodeStyleSettings codeStyleSettings) {
        return new OCCodeStyleMainPanel(this.getCurrentSettings(), codeStyleSettings);
    }
    
    public String getHelpTopic() {
        return PlatformUtils.isAppCode() ? "topicId2221" : "reference.settings.codestyle.ccpp";
    }
}