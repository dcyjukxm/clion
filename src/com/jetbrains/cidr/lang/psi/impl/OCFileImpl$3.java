// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCFileImpl$3 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ String val$name;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return (ocSymbol instanceof OCClassSymbol || (ocSymbol instanceof OCStructSymbol && !((OCStructSymbol)ocSymbol).isInnerClass())) && this.val$name.equals(ocSymbol.getName());
    }
}