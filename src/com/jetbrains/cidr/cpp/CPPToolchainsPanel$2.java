// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class CPPToolchainsPanel$2 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        CPPToolchainsPanel.access$900(CPPToolchainsPanel.this).setEnabled(CPPToolchainsPanel.access$800(CPPToolchainsPanel.this).isSelected());
        CPPToolchainsPanel.access$700(CPPToolchainsPanel.this, CPPToolchainsCheckPanel.UpdateType.CMake);
    }
}