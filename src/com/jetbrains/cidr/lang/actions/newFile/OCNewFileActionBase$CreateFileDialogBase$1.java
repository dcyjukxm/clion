// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class OCNewFileActionBase$CreateFileDialogBase$1 implements DocumentListener {
    final /* synthetic */ NameFocusListener val$focusListener;
    
    private void a() {
        CreateFileDialogBase.this.validateOkAction();
        this.val$focusListener.resetSelectionStart();
    }
    
    @Override
    public void insertUpdate(final DocumentEvent documentEvent) {
        this.a();
    }
    
    @Override
    public void removeUpdate(final DocumentEvent documentEvent) {
        this.a();
    }
    
    @Override
    public void changedUpdate(final DocumentEvent documentEvent) {
        this.a();
    }
}