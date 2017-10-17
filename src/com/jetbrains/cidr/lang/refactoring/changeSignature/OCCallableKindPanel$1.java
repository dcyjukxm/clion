// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class OCCallableKindPanel$1 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            ((ChangeListener)OCCallableKindPanel.access$000(OCCallableKindPanel.this).getMulticaster()).stateChanged(new ChangeEvent(this));
        }
    }
}