// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.util.Processor;

public static class TypeFilteredProcessor<T, S extends T> implements Processor<T>
{
    private final Processor<S> myAdaptee;
    private final Class<? extends S> myClass;
    
    public TypeFilteredProcessor(final Processor<S> myAdaptee, final Class<? extends S> myClass) {
        this.myAdaptee = myAdaptee;
        this.myClass = myClass;
    }
    
    public boolean process(final T t) {
        return !this.myClass.isAssignableFrom(t.getClass()) || this.myAdaptee.process((Object)t);
    }
}
