// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class CPPNewFileHelperProvider$Helper$Dialog$3 implements ListSelectionListener {
    @Override
    public void valueChanged(final ListSelectionEvent listSelectionEvent) {
        final int selectedIndex = Dialog.access$100(Dialog.this).getSelectedIndex();
        Dialog.access$300(Dialog.this).updatePreviewComponent(Dialog.access$200(Dialog.this), (selectedIndex < 0) ? null : ((NewFileTarget)Dialog.access$100(Dialog.this).getItemAt(selectedIndex)));
    }
}