// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.intellij.util.CommonProcessors;

class OCClassSymbolImpl$2 extends CommonProcessors.FindFirstProcessor<OCClassSymbol> {
    protected boolean accept(final OCClassSymbol ocClassSymbol) {
        return ocClassSymbol.getCategoryName() == null;
    }
}