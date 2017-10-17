// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

class OCImplementPropertyAccessorsQuickFix$1 extends CommonProcessors.CollectProcessor<OCMethodSymbol> {
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        return ocMethodSymbol.getGeneratedFromProperty() != OCImplementPropertyAccessorsQuickFix.access$000(OCImplementPropertyAccessorsQuickFix.this) || super.process((Object)ocMethodSymbol);
    }
}