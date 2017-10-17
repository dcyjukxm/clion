// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.NotNullLazyValue;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.AtomicNotNullLazyValue;
import com.intellij.util.containers.SLRUCache;

public abstract class SLRUSynchronizedCache<K, V>
{
    @NotNull
    private final SLRUCache<K, AtomicNotNullLazyValue<V>> myCache;
    
    public SLRUSynchronizedCache(final int n, final int n2) {
        this.myCache = new SLRUCache<K, AtomicNotNullLazyValue<V>>(n, n2) {
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
        };
    }
    
    public abstract V createValue(final K p0);
    
    @NotNull
    public V get(final K k) {
        final NotNullLazyValue notNullLazyValue;
        synchronized (this.myCache) {
            notNullLazyValue = (NotNullLazyValue)this.myCache.get((Object)k);
        }
        Object value;
        try {
            value = notNullLazyValue.getValue();
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/symbols/SLRUSynchronizedCache", "get"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (V)value;
    }
    
    @Nullable
    public V getIfCached(final K k) {
        final AtomicNotNullLazyValue atomicNotNullLazyValue;
        synchronized (this.myCache) {
            atomicNotNullLazyValue = (AtomicNotNullLazyValue)this.myCache.getIfCached((Object)k);
        }
        try {
            if (atomicNotNullLazyValue == null) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (atomicNotNullLazyValue.isComputed()) {
                return (V)atomicNotNullLazyValue.getValue();
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    public void clear() {
        synchronized (this.myCache) {
            this.myCache.clear();
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
