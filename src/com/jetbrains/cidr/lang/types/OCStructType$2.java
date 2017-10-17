// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;

class OCStructType$2 implements Condition<OCSymbol> {
    Boolean myExplicitConstructorCall;
    final /* synthetic */ Producer val$isExplicitCall;
    
    public boolean value(final OCSymbol ocSymbol) {
        if (!(ocSymbol instanceof OCFunctionSymbol) || !((OCFunctionSymbol)ocSymbol).isExplicit()) {
            return true;
        }
        if (this.myExplicitConstructorCall == null) {
            this.myExplicitConstructorCall = (Boolean)this.val$isExplicitCall.produce();
        }
        return this.myExplicitConstructorCall;
    }
}