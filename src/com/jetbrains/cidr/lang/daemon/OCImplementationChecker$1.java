// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.Processor;

class OCImplementationChecker$1 implements Processor<OCMethodSymbol> {
    int cnt;
    
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        return ++this.cnt < 2;
    }
}