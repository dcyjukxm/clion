// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.quickfixes.OCAddSuperProtocolIntentionAction;

class OCTypeCompatibilityVisitor$17 extends OCAddSuperProtocolIntentionAction {
    final /* synthetic */ OCImplementation val$implementation;
    final /* synthetic */ OCClassSymbol val$classSymbol;
    
    @Override
    protected boolean isAvailable(final OCClassSymbol ocClassSymbol) {
        return this.val$implementation != null && this.val$classSymbol != null && this.val$classSymbol.getName().equals(this.val$implementation.getName()) && super.isAvailable(ocClassSymbol);
    }
}