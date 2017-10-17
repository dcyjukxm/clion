// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.util.PairFunction;

static final class DeepEqual$1 extends DeepEqualImpl {
    final /* synthetic */ PairFunction val$preEqualCustom;
    
    @Override
    protected <T> boolean equalObjects(final Equality<T> equality, final T t, final T t2) {
        this.val$preEqualCustom.fun((Object)t, (Object)t2);
        return super.equalObjects(equality, t, t2);
    }
}