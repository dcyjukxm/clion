// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class CPPNewFileHelperProvider$Helper$Dialog$2 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        Dialog.this.updateTargetControlsState();
    }
}