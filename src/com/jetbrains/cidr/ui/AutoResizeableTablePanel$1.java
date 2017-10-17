// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

class AutoResizeableTablePanel$1 implements TableModelListener {
    @Override
    public void tableChanged(final TableModelEvent tableModelEvent) {
        AutoResizeableTablePanel.this.updateSize();
    }
}