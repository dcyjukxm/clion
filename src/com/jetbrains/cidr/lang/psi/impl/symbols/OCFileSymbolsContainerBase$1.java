// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import javax.swing.Icon;
import org.jetbrains.annotations.Nullable;
import com.intellij.navigation.ItemPresentation;

class OCFileSymbolsContainerBase$1 implements ItemPresentation {
    @Nullable
    public String getPresentableText() {
        return OCFileSymbolsContainerBase.this.getName();
    }
    
    @Nullable
    public String getLocationString() {
        return OCFileSymbolsContainerBase.access$000(OCFileSymbolsContainerBase.this).getName();
    }
    
    @Nullable
    public Icon getIcon(final boolean b) {
        return OCFileSymbolsContainerBase.access$000(OCFileSymbolsContainerBase.this).getIcon(0);
    }
}