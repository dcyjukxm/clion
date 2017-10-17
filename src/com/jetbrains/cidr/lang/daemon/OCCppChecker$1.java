// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.CommonProcessors;

class OCCppChecker$1 extends CommonProcessors.FindFirstProcessor<OCFunctionSymbol> {
    final /* synthetic */ Ref val$nonVirtualRef;
    
    public boolean process(final OCFunctionSymbol ocFunctionSymbol) {
        if (ocFunctionSymbol.isVirtual()) {
            return super.process((Object)ocFunctionSymbol);
        }
        this.val$nonVirtualRef.set((Object)ocFunctionSymbol);
        return true;
    }
}