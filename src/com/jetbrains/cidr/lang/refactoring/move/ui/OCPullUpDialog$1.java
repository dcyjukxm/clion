// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.psi.impl.OCElementBase;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.JList;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.ui.ListCellRendererWrapper;

class OCPullUpDialog$1 extends ListCellRendererWrapper<OCSymbolDeclarator> {
    public void customize(final JList list, final OCSymbolDeclarator ocSymbolDeclarator, final int n, final boolean b, final boolean b2) {
        final OCSymbol ocSymbol = OCPullUpDialog.access$000(OCPullUpDialog.this).get(ocSymbolDeclarator);
        if (ocSymbol != null) {
            this.setText(ocSymbol.getPresentableName());
            this.setIcon(ocSymbol.getIcon());
        }
        else {
            this.setText(((OCElementBase)ocSymbolDeclarator).getName());
        }
    }
}