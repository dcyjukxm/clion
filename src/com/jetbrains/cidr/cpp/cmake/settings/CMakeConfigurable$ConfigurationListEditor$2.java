// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import com.intellij.ui.AnActionButton;
import com.intellij.ui.AnActionButtonRunnable;

class CMakeConfigurable$ConfigurationListEditor$2 implements AnActionButtonRunnable {
    public void run(final AnActionButton anActionButton) {
        ConfigurationListEditor.access$400(ConfigurationListEditor.this, () -> ConfigurationListEditor.access$200(ConfigurationListEditor.this).remove(ConfigurationListEditor.access$100(ConfigurationListEditor.this).getSelectedIndex()));
    }
}