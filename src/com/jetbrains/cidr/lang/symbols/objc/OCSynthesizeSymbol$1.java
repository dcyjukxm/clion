// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.intellij.openapi.util.Comparing;
import com.intellij.util.CommonProcessors;

class OCSynthesizeSymbol$1 extends CommonProcessors.FindFirstProcessor<OCPropertySymbol> {
    final /* synthetic */ String val$propertyName;
    
    protected boolean accept(final OCPropertySymbol ocPropertySymbol) {
        return Comparing.equal(this.val$propertyName, ocPropertySymbol.getName());
    }
}