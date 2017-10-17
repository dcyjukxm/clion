// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class OCNewFileActionBase$CreateFileDialogBase$2 implements DocumentListener {
    @Override
    public void insertUpdate(final DocumentEvent documentEvent) {
        CreateFileDialogBase.this.validateOkAction();
        CreateFileDialogBase.access$102(CreateFileDialogBase.this, true);
    }
    
    @Override
    public void removeUpdate(final DocumentEvent documentEvent) {
        CreateFileDialogBase.this.validateOkAction();
        CreateFileDialogBase.access$102(CreateFileDialogBase.this, documentEvent.getDocument().getLength() != 0);
    }
    
    @Override
    public void changedUpdate(final DocumentEvent documentEvent) {
    }
}