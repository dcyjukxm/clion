// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.intellij.util.CommonProcessors;

class OCMethodSymbolImpl$1 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
        return ocMethodSymbol.isStatic() == OCMethodSymbolImpl.this.isStatic();
    }
}