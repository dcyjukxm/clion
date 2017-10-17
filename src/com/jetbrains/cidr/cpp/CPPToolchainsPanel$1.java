// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class CPPToolchainsPanel$1 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        final boolean selected = CPPToolchainsPanel.access$000(CPPToolchainsPanel.this).isSelected();
        final boolean selected2 = CPPToolchainsPanel.access$100(CPPToolchainsPanel.this).isSelected();
        final boolean selected3 = CPPToolchainsPanel.access$200(CPPToolchainsPanel.this).isSelected();
        CPPToolchainsPanel.access$300(CPPToolchainsPanel.this).setEnabled(selected);
        CPPToolchainsPanel.access$400(CPPToolchainsPanel.this).setEnabled(selected2);
        CPPToolchainsPanel.access$500(CPPToolchainsPanel.this).setEnabled(selected3);
        CPPToolchainsPanel.access$600(CPPToolchainsPanel.this);
        CPPToolchainsPanel.access$700(CPPToolchainsPanel.this, CPPToolchainsCheckPanel.UpdateType.All);
    }
}