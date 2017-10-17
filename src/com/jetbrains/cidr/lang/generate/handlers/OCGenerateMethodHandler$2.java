// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

class OCGenerateMethodHandler$2 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
        return !ocMethodSymbol.isStatic();
    }
}