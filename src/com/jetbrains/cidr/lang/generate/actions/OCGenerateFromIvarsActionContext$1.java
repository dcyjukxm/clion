// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import java.util.Set;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.util.CommonProcessors;

class OCGenerateFromIvarsActionContext$1 extends CommonProcessors.CollectProcessor<OCInstanceVariableSymbol> {
    final /* synthetic */ Set val$processedNames;
    
    protected boolean accept(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
        return !ocInstanceVariableSymbol.isForbiddenClang4ImplicitIvar() && this.val$processedNames.add(ocInstanceVariableSymbol.getName()) && OCGenerateFromIvarsActionContext.this.showSymbol(ocInstanceVariableSymbol);
    }
}