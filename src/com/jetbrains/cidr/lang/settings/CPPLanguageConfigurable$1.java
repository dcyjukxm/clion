// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import javax.swing.event.ChangeEvent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import javax.swing.event.ChangeListener;

class CPPLanguageConfigurable$1 implements ChangeListener {
    final /* synthetic */ TextFieldWithBrowseButton val$textFieldWithBrowseButton;
    
    @Override
    public void stateChanged(final ChangeEvent changeEvent) {
        this.val$textFieldWithBrowseButton.setEnabled(CPPLanguageConfigurable.access$000(CPPLanguageConfigurable.this).isSelected());
    }
}