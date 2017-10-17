// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.util.CommonProcessors;

class OCPropertySymbolImpl$2 extends CommonProcessors.FindFirstProcessor<OCPropertySymbol> {
    protected boolean accept(final OCPropertySymbol ocPropertySymbol) {
        return ((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent().getCategoryName() == null;
    }
}