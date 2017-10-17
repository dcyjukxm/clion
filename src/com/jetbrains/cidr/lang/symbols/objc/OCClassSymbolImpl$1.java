// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCClassSymbolImpl$1 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol instanceof OCProtocolSymbol && !ocSymbol.isPredeclaration();
    }
}