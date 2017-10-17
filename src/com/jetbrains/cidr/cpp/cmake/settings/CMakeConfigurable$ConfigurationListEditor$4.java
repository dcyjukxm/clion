// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class CMakeConfigurable$ConfigurationListEditor$4 implements ListSelectionListener {
    @Override
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getValueIsAdjusting()) {
            return;
        }
        ConfigurationListEditor.this.updateSelectedEditor();
    }
}