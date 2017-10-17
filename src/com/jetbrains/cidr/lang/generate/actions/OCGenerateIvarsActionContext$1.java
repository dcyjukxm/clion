// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.Set;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.util.CommonProcessors;

class OCGenerateIvarsActionContext$1 extends CommonProcessors.CollectProcessor<OCPropertySymbol> {
    final /* synthetic */ OCObjectType val$type;
    final /* synthetic */ Set val$names;
    
    protected boolean accept(final OCPropertySymbol ocPropertySymbol) {
        return (((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent().getName().equals(this.val$type.getClassName()) || ocPropertySymbol.getParent() instanceof OCProtocolSymbol) && this.val$names.add(ocPropertySymbol.getName());
    }
}