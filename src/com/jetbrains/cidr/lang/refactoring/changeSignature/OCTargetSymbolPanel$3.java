// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCategoryAction;

class OCTargetSymbolPanel$3 extends OCNewCategoryAction {
    final /* synthetic */ OCClassSymbol val$parent;
    
    @Override
    protected OCClassSymbol getBaseClass() {
        return this.val$parent;
    }
}