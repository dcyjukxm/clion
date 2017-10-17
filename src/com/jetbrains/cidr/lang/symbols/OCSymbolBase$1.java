// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import javax.swing.Icon;
import com.intellij.navigation.ItemPresentation;

class OCSymbolBase$1 implements ItemPresentation {
    public String getPresentableText() {
        return OCSymbolBase.this.getPresentableText();
    }
    
    public String getLocationString() {
        return OCSymbolBase.this.getLocationString();
    }
    
    public Icon getIcon(final boolean b) {
        return OCSymbolBase.this.getIcon();
    }
}