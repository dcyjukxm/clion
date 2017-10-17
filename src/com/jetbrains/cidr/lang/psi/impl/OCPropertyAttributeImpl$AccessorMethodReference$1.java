// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

class OCPropertyAttributeImpl$AccessorMethodReference$1 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        return ocMethodSymbol.isStatic() || super.process((Object)ocMethodSymbol);
    }
}