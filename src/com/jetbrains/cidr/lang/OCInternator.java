// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.util.ConcurrencyUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.ContainerUtil;
import java.util.concurrent.ConcurrentMap;

public abstract class OCInternator<T>
{
    private final ConcurrentMap<T, T> myCache;
    
    public OCInternator() {
        this.myCache = (ConcurrentMap<T, T>)ContainerUtil.createConcurrentWeakKeyWeakValueMap();
    }
    
    @NotNull
    public T intern(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/lang/OCInternator", "intern"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Object value = this.myCache.get(t);
        Label_0106: {
            Object o = null;
            Label_0071: {
                try {
                    if (value == null) {
                        break Label_0106;
                    }
                    o = value;
                    if (o == null) {
                        break Label_0071;
                    }
                    return (T)o;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    o = value;
                    if (o == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCInternator", "intern"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return (T)o;
        }
        final Object valueToStore = this.valueToStore(t);
        Object cacheOrGet;
        try {
            cacheOrGet = ConcurrencyUtil.cacheOrGet((ConcurrentMap)this.myCache, valueToStore, valueToStore);
            if (cacheOrGet == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/OCInternator", "intern"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return (T)cacheOrGet;
    }
    
    @NotNull
    protected abstract T valueToStore(@NotNull final T p0);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
