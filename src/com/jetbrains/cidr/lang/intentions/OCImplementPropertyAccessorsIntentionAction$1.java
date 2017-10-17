// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

class OCImplementPropertyAccessorsIntentionAction$1 extends CommonProcessors.CollectProcessor<OCMethodSymbol> {
    final /* synthetic */ OCPropertySymbol val$property;
    
    public boolean process(final OCMethodSymbol ocMethodSymbol) {
        return ocMethodSymbol.getGeneratedFromProperty() != this.val$property || super.process((Object)ocMethodSymbol);
    }
}