// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCRemoveTypeModifierIntentionAction;

class OCCppChecker$2 extends OCRemoveTypeModifierIntentionAction {
    final /* synthetic */ OCFunctionSymbol val$baseFunction;
    
    @Override
    protected String getSubject(final OCSymbol ocSymbol) {
        return this.val$baseFunction.getNameWithParent();
    }
}