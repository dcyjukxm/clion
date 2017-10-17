// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class CMakeCodeStyleSettings extends CustomCodeStyleSettings
{
    public int FORCE_COMMANDS_CASE;
    
    protected CMakeCodeStyleSettings(final CodeStyleSettings codeStyleSettings) {
        super(CMakeCodeStyleSettings.class.getSimpleName(), codeStyleSettings);
        this.FORCE_COMMANDS_CASE = Case.DO_NOT_CHANGE.getValue();
    }
    
    public boolean hasDoNotChangeFileSettings() {
        return this.FORCE_COMMANDS_CASE == Case.DO_NOT_CHANGE.getValue();
    }
}
