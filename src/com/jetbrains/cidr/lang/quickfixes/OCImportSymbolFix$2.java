// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import javax.swing.ListCellRenderer;
import com.intellij.openapi.ui.popup.ListPopupStep;
import com.intellij.ui.popup.list.ListPopupImpl;

class OCImportSymbolFix$2 extends ListPopupImpl {
    @Override
    protected ListCellRenderer getListElementRenderer() {
        return new MyRenderer(this.mySpeedSearch);
    }
}