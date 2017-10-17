// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import javax.swing.Icon;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.JTable;
import com.intellij.ui.ColoredTableCellRenderer;

private class MyTableRenderer extends ColoredTableCellRenderer
{
    public void customizeCellRenderer(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        final int convertColumnIndexToModel = OCPushDownDialog.access$500(OCPushDownDialog.this).convertColumnIndexToModel(n2);
        final OCSymbol ocSymbol = OCPushDownDialog.access$200(OCPushDownDialog.this).get(n);
        if (convertColumnIndexToModel == 1) {
            this.setIcon(ocSymbol.getIcon());
        }
        else {
            this.setIcon((Icon)null);
        }
        this.setIconOpaque(false);
        this.setOpaque(false);
        this.append((String)o);
    }
}
