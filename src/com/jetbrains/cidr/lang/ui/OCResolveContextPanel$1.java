// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import javax.swing.Icon;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import com.intellij.openapi.wm.impl.status.TextPanel;

class OCResolveContextPanel$1 extends TextPanel {
    @Override
    public void doLayout() {
        super.doLayout();
        final Rectangle bounds = this.getBounds();
        final Insets insets = this.getInsets();
        final Dimension minimumSize = OCResolveContextPanel.access$000(OCResolveContextPanel.this).getMinimumSize();
        final int n = (int)minimumSize.getWidth();
        final int n2 = (int)minimumSize.getHeight();
        OCResolveContextPanel.access$000(OCResolveContextPanel.this).setBounds(bounds.width - insets.right - n, bounds.height / 2 - n2 / 2, n, n2);
        final Icon icon = OCResolveContextPanel.access$100(OCResolveContextPanel.this).getIcon();
        final int iconWidth = icon.getIconWidth();
        final int iconHeight = icon.getIconHeight();
        OCResolveContextPanel.access$100(OCResolveContextPanel.this).setBounds(bounds.width - insets.right - iconWidth - 2, bounds.height / 2 - iconHeight / 2, iconWidth, iconHeight);
    }
}