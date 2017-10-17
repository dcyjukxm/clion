// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

class OCObjectTypeContext$1 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
        return OCObjectTypeContext.this.fitsStaticness(ocMethodSymbol);
    }
}