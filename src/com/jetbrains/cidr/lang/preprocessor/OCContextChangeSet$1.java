// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import gnu.trove.TIntObjectProcedure;

static final class OCContextChangeSet$1 implements TIntObjectProcedure<OCContextChange> {
    final /* synthetic */ int val$diffOffset;
    final /* synthetic */ int[] val$loRef;
    
    public boolean execute(final int n, final OCContextChange ocContextChange) {
        final boolean b = ocContextChange.getOffset() < this.val$diffOffset;
        if (b && this.val$loRef[0] < n) {
            this.val$loRef[0] = n;
        }
        return b;
    }
}