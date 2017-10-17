// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCStringsFile;
import javax.swing.Icon;
import com.intellij.navigation.ItemPresentation;

class OCLocalizedStringImpl$1 implements ItemPresentation {
    public Icon getIcon(final boolean b) {
        return null;
    }
    
    public String getPresentableText() {
        return OCLocalizedStringImpl.this.getValue();
    }
    
    public String getLocationString() {
        return ((OCStringsFile)OCLocalizedStringImpl.this.getContainingFile()).getLocalizationName();
    }
}