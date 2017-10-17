// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;

class OCNewSourceFileAction$MyCreateFileDialog$1 implements ItemListener {
    final /* synthetic */ JCheckBox val$headerCheckBox;
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        MyCreateFileDialog.access$002(MyCreateFileDialog.this, this.val$headerCheckBox.isSelected());
        if (MyCreateFileDialog.this.myKindCombo != null) {
            MyCreateFileDialog.this.reloadExtensions();
        }
        MyCreateFileDialog.this.validateOkAction();
    }
}