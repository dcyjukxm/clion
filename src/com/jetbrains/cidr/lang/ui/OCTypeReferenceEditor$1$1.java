// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.jetbrains.cidr.lang.symbols.OCSymbolHolderBase;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.ide.util.TreeChooser;

class OCTypeReferenceEditor$1$1 implements TreeChooser.Filter<OCSymbolHolderVirtualPsiElement> {
    public boolean isAccepted(final OCSymbolHolderVirtualPsiElement ocSymbolHolderVirtualPsiElement) {
        return ActionListener.this.val$symbolCondition.value(((OCSymbolHolderBase<Object>)ocSymbolHolderVirtualPsiElement).getSymbol());
    }
}