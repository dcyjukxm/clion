// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import java.util.Set;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

class OCImplementOCMethodsHandler$1$1 extends CommonProcessors.CollectProcessor<OCMethodSymbol> {
    final /* synthetic */ Set val$names;
    
    protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
        if (this.val$names.add(ocMethodSymbol.getName())) {
            OCImplementOCMethodsHandler$1.access$000(OCOverrideImplementActionContext.this).add(ocMethodSymbol);
            return true;
        }
        return false;
    }
}