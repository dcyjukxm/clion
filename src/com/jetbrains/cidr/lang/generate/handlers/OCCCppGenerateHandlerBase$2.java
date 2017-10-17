// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import java.awt.Component;
import javax.swing.JList;
import com.intellij.ide.util.NavigationItemListCellRenderer;

class OCCCppGenerateHandlerBase$2 extends NavigationItemListCellRenderer {
    @Override
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        return super.getListCellRendererComponent(list, (o instanceof OCActionContext) ? ((OCActionContext)o).getParent() : o, n, b, b2);
    }
}