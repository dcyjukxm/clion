// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import javax.swing.Icon;
import com.intellij.navigation.ItemPresentation;

class OCMethodImpl$1 implements ItemPresentation {
    public String getPresentableText() {
        return OCMethodImpl.this.getSelector();
    }
    
    public String getLocationString() {
        final ItemPresentation presentation = OCMethodImpl.this.getContainingClass().getPresentation();
        return (presentation != null) ? presentation.getPresentableText() : "";
    }
    
    public Icon getIcon(final boolean b) {
        return OCMethodImpl.this.getIcon(0);
    }
}