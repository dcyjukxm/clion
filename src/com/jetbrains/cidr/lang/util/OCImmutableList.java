// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import java.util.Collections;
import com.intellij.util.ArrayUtil;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.ImmutableList;

public class OCImmutableList<T> extends ImmutableList<T>
{
    private static final OCImmutableList EMPTY;
    @NotNull
    private Object[] myData;
    
    public OCImmutableList(@NotNull final Collection<T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/util/OCImmutableList", "<init>"));
        }
        this.myData = ArrayUtil.toObjectArray((Collection)collection);
    }
    
    public int size() {
        return this.myData.length;
    }
    
    public T get(final int n) {
        return (T)this.myData[n];
    }
    
    @NotNull
    public static <T> OCImmutableList<T> emptyList() {
        OCImmutableList empty;
        try {
            empty = OCImmutableList.EMPTY;
            if (empty == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCImmutableList", "emptyList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (OCImmutableList<T>)empty;
    }
    
    static {
        EMPTY = new OCImmutableList((Collection<T>)Collections.emptyList());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
