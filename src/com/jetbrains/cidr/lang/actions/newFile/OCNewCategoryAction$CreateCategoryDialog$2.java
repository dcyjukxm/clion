// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class OCNewCategoryAction$CreateCategoryDialog$2 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        CreateCategoryDialog.this.validateOkAction();
    }
}