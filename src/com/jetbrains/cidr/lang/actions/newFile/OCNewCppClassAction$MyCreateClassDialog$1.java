// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;

class OCNewCppClassAction$MyCreateClassDialog$1 implements ItemListener {
    final /* synthetic */ JCheckBox val$headerCheckBox;
    
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        MyCreateClassDialog.this.myHeaderOnly = this.val$headerCheckBox.isSelected();
        if (MyCreateClassDialog.this.myKindCombo != null) {
            MyCreateClassDialog.this.reloadExtensions();
        }
        MyCreateClassDialog.this.validateOkAction();
    }
}