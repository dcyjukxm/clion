// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.openapi.util.Condition;

public static class IsAssignableCondition<T> implements Condition<T>
{
    private Class<? extends T> clazz;
    
    public IsAssignableCondition(final Class<? extends T> clazz) {
        this.clazz = clazz;
    }
    
    public boolean value(final T t) {
        return this.clazz.isAssignableFrom(t.getClass());
    }
}
