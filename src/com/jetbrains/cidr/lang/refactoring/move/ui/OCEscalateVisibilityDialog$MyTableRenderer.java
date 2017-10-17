// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import javax.swing.Icon;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import javax.swing.JTable;
import com.intellij.ui.ColoredTableCellRenderer;

private class MyTableRenderer extends ColoredTableCellRenderer
{
    public void customizeCellRenderer(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        final int convertColumnIndexToModel = OCEscalateVisibilityDialog.access$400(OCEscalateVisibilityDialog.this).convertColumnIndexToModel(n2);
        final OCMemberInfo ocMemberInfo = OCEscalateVisibilityDialog.access$200(OCEscalateVisibilityDialog.this).get(n);
        String string = o.toString();
        if (convertColumnIndexToModel == 3 && OCVisibility.shouldBeDeclaredInInterface(ocMemberInfo.getSymbol(), (OCVisibility)o)) {
            string = "declare in interface";
        }
        if (convertColumnIndexToModel == 1) {
            this.setIcon(((OCSymbolHolderVirtualPsiElement)ocMemberInfo.getMember()).getIcon(0));
        }
        else {
            this.setIcon((Icon)null);
        }
        this.setIconOpaque(false);
        this.setOpaque(false);
        this.append(string);
    }
}
