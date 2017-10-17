// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import com.jetbrains.cidr.lang.settings.OCNewClassSettings;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class OCNewClassAction$CreateClassDialog$1 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        OCNewClassSettings.getInstance().myLanguageIndex = CreateClassDialog.access$000(CreateClassDialog.this).getSelectedIndex();
        CreateClassDialog.this.validateOkAction();
    }
}