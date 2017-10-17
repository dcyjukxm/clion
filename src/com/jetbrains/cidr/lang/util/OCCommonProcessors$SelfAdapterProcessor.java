// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.util.Processor;

public static class SelfAdapterProcessor<S, T extends S> implements Processor<T>
{
    private final Processor<S> myAdaptee;
    
    public SelfAdapterProcessor(final Processor<S> myAdaptee) {
        this.myAdaptee = myAdaptee;
    }
    
    public boolean process(final T t) {
        return this.myAdaptee.process((Object)t);
    }
}
