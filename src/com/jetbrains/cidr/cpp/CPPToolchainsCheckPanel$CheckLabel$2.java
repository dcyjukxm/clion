// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

class CPPToolchainsCheckPanel$CheckLabel$2 extends ComponentAdapter {
    @Override
    public void componentResized(final ComponentEvent componentEvent) {
        CheckLabel.this.update(true);
    }
}