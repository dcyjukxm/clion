// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import com.intellij.openapi.actionSystem.AnActionEvent;
import javax.swing.Icon;
import com.intellij.ui.ToolbarDecorator;

class CMakeConfigurable$ConfigurationListEditor$1 extends ToolbarDecorator.ElementActionButton {
    public boolean isDumbAware() {
        return true;
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        ConfigurationListEditor.access$300(ConfigurationListEditor.this, ((ConfigurationEditor)ConfigurationListEditor.access$200(ConfigurationListEditor.this).getElementAt(ConfigurationListEditor.access$100(ConfigurationListEditor.this).getSelectedIndex())).create());
    }
}