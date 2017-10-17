// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class CPPToolchainsPanel$3 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        CPPToolchainsPanel.access$1100(CPPToolchainsPanel.this).setEnabled(CPPToolchainsPanel.access$1000(CPPToolchainsPanel.this).isSelected());
        CPPToolchainsPanel.access$700(CPPToolchainsPanel.this, CPPToolchainsCheckPanel.UpdateType.GDB);
    }
}