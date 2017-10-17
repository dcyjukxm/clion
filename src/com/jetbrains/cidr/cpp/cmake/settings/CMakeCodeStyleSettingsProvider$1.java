// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import com.intellij.application.options.CodeStyleAbstractPanel;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.application.options.CodeStyleAbstractConfigurable;

class CMakeCodeStyleSettingsProvider$1 extends CodeStyleAbstractConfigurable {
    @Nullable
    public String getHelpTopic() {
        return "Code_Style_CMake";
    }
    
    @Override
    protected CodeStyleAbstractPanel createPanel(final CodeStyleSettings codeStyleSettings) {
        return new CMakeTabbedLanguageCodeStylePanel(this.getCurrentSettings(), codeStyleSettings);
    }
}