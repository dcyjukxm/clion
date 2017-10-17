// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

class OCMethodImpl$2 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    final /* synthetic */ boolean val$isStatic;
    
    protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
        return ocMethodSymbol.isStatic() == this.val$isStatic;
    }
}