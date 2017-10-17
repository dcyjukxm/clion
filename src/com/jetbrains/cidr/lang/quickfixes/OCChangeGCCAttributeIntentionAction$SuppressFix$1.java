// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.OCSymbol;

class OCChangeGCCAttributeIntentionAction$SuppressFix$1 extends OCChangeGCCAttributeIntentionAction {
    @Override
    protected boolean isAvailable(final OCSymbol ocSymbol) {
        return super.isAvailable(ocSymbol) && SuppressFix.this.isAvailable(ocSymbol);
    }
}