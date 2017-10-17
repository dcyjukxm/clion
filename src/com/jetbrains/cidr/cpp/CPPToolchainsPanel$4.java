// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import javax.swing.event.DocumentEvent;
import com.intellij.ui.components.JBRadioButton;
import javax.swing.ButtonGroup;
import com.intellij.ui.DocumentAdapter;

class CPPToolchainsPanel$4 extends DocumentAdapter {
    final /* synthetic */ ButtonGroup val$radioGriup;
    final /* synthetic */ JBRadioButton val$radio;
    
    protected void textChanged(final DocumentEvent documentEvent) {
        if (this.val$radioGriup.getSelection() == null) {
            this.val$radio.setSelected(true);
        }
    }
}