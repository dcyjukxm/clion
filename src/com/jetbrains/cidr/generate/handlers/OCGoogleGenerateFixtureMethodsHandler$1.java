// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.handlers;

import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCGoogleGenerateFixtureMethodsHandler$1 extends CommonProcessors.FindProcessor<OCSymbol> {
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol instanceof OCStructSymbol;
    }
}