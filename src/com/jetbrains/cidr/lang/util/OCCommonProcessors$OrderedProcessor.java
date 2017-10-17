// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import java.util.Iterator;
import java.util.Collection;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.util.Condition;
import com.intellij.util.Processor;

public static class OrderedProcessor<T> implements Processor<T>
{
    private Condition<T>[] myOrder;
    private Processor<? super T> myProcessor;
    private MultiMap<Condition<T>, T> myMap;
    private boolean myStopAfterNonemptyCondition;
    private boolean myCancelled;
    private boolean myEmpty;
    
    public OrderedProcessor(final Processor<? super T> myProcessor, final Condition<T>... myOrder) {
        this.myMap = (com.intellij.util.containers.MultiMap<com.intellij.openapi.util.Condition<T>, T>)new MultiMap();
        this.myEmpty = true;
        this.myOrder = myOrder;
        this.myProcessor = myProcessor;
    }
    
    public OrderedProcessor(final Processor<T> myProcessor, final boolean myStopAfterNonemptyCondition, final Condition<T>... myOrder) {
        this.myMap = (com.intellij.util.containers.MultiMap<com.intellij.openapi.util.Condition<T>, T>)new MultiMap();
        this.myEmpty = true;
        this.myOrder = myOrder;
        this.myProcessor = myProcessor;
        this.myStopAfterNonemptyCondition = myStopAfterNonemptyCondition;
    }
    
    public boolean process(final T t) {
        if (this.myCancelled) {
            return false;
        }
        for (int i = 0; i < this.myOrder.length; ++i) {
            final Condition<T> condition = this.myOrder[i];
            if (condition.value((Object)t)) {
                this.myMap.putValue((Object)condition, (Object)t);
                if (i == 0) {
                    this.myEmpty = false;
                    if (!this.myProcessor.process((Object)t)) {
                        this.myCancelled = true;
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }
    
    public boolean finish() {
        if (this.myCancelled) {
            return false;
        }
        for (int i = 1; i < this.myOrder.length; ++i) {
            if (this.myStopAfterNonemptyCondition && !this.myEmpty) {
                return true;
            }
            for (final T next : this.sort(this.myMap.get((Object)this.myOrder[i]))) {
                this.myEmpty = false;
                if (!this.myProcessor.process((Object)next)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Collection<T> sort(final Collection<T> collection) {
        return collection;
    }
    
    public boolean isEmpty() {
        return this.myMap.isEmpty();
    }
}
