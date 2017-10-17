// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class OCNewFileWithSelectedExtensionAction$CreateFileDialog$2 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            final int selectedIndex = CreateFileDialog.this.myKindCombo.getComboBox().getSelectedIndex();
            if (selectedIndex < CreateFileDialog.this.pairs.size()) {
                CreateFileDialog.this.myFileExtensionPair = (OCCodeStyleSettings.FileExtensionPair)CreateFileDialog.this.pairs.get(selectedIndex);
            }
        }
        CreateFileDialog.this.validateOkAction();
    }
}