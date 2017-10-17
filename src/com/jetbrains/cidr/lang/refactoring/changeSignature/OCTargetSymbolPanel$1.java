// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.util.Trinity;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.JList;
import com.intellij.ui.ListCellRendererWrapper;

class OCTargetSymbolPanel$1 extends ListCellRendererWrapper<Object> {
    public void customize(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        if (o instanceof OCSymbol) {
            this.setText(((OCSymbol)o).getPresentableName());
            this.setIcon(((OCSymbol)o).getIcon());
        }
        else if (o instanceof Trinity) {
            final Trinity trinity = (Trinity)o;
            this.setText(((OCClassSymbol)trinity.getFirst()).getPresentableName() + (String)trinity.getThird());
            this.setIcon(((OCClassSymbol)trinity.getFirst()).getIcon());
        }
        else if ("separator".equals(o)) {
            this.setSeparator();
        }
        else if (o != null) {
            this.setText(o.toString());
        }
    }
}