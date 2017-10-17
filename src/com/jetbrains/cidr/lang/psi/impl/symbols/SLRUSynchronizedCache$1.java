// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.AtomicNotNullLazyValue;
import com.intellij.util.containers.SLRUCache;

class SLRUSynchronizedCache$1 extends SLRUCache<K, AtomicNotNullLazyValue<V>> {
    @NotNull
    public AtomicNotNullLazyValue<V> createValue(final K k) {
        AtomicNotNullLazyValue<V> atomicNotNullLazyValue;
        try {
            atomicNotNullLazyValue = new AtomicNotNullLazyValue<V>() {
                @NotNull
                protected V compute() {
                    V value;
                    try {
                        value = SLRUSynchronizedCache.this.createValue(k);
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
            };
            if (atomicNotNullLazyValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/symbols/SLRUSynchronizedCache$1", "createValue"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return atomicNotNullLazyValue;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}