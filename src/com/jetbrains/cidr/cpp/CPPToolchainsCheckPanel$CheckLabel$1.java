// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Container;
import com.intellij.util.ui.AbstractLayoutManager;

class CPPToolchainsCheckPanel$CheckLabel$1 extends AbstractLayoutManager {
    public Dimension preferredLayoutSize(final Container container) {
        return new Dimension(100, CheckLabel.access$000(CheckLabel.this).getPreferredSize().height);
    }
    
    public void layoutContainer(final Container container) {
        final Rectangle bounds = container.getBounds();
        final Insets insets = container.getInsets();
        CheckLabel.access$000(CheckLabel.this).setBounds(insets.left, insets.top, bounds.width - insets.left - insets.right, bounds.height - insets.top - insets.bottom);
    }
}