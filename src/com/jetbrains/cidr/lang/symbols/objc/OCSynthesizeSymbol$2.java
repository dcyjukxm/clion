// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.intellij.openapi.util.Comparing;
import com.intellij.util.CommonProcessors;

class OCSynthesizeSymbol$2 extends CommonProcessors.FindFirstProcessor<OCInstanceVariableSymbol> {
    protected boolean accept(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
        return Comparing.equal(OCSynthesizeSymbol.this.getIvarName(), ocInstanceVariableSymbol.getName()) && !ocInstanceVariableSymbol.isClang4ImplicitIvar();
    }
}