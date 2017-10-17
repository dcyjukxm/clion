// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.AtomicNotNullLazyValue;

class SLRUSynchronizedCache$1$1 extends AtomicNotNullLazyValue<V> {
    final /* synthetic */ Object val$key;
    
    @NotNull
    protected V compute() {
        V value;
        try {
            value = SLRUCache.this.this$0.createValue(this.val$key);
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/symbols/SLRUSynchronizedCache$1$1", "compute"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return value;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}