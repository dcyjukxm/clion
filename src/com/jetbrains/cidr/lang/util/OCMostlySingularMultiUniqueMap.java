// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import gnu.trove.THashMap;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import gnu.trove.THashSet;
import java.util.Set;
import gnu.trove.TObjectHashingStrategy;
import com.intellij.util.containers.MostlySingularMultiMap;

public class OCMostlySingularMultiUniqueMap<K, V> extends MostlySingularMultiMap<K, V>
{
    private final TObjectHashingStrategy<? super V> myValueEqualityStrategy;
    protected final Set<V> myAllValues;
    
    public OCMostlySingularMultiUniqueMap() {
        this(TObjectHashingStrategy.CANONICAL);
    }
    
    public OCMostlySingularMultiUniqueMap(final TObjectHashingStrategy<? super V> myValueEqualityStrategy) {
        this.myValueEqualityStrategy = myValueEqualityStrategy;
        this.myAllValues = this.createSet();
    }
    
    @NotNull
    protected Set<V> createSet() {
        THashSet set;
        try {
            set = new THashSet((TObjectHashingStrategy)this.myValueEqualityStrategy);
            if (set == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCMostlySingularMultiUniqueMap", "createSet"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Set<V>)set;
    }
    
    public void add(@NotNull final K k, @NotNull final V v) {
        try {
            if (k == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/util/OCMostlySingularMultiUniqueMap", "add"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (v == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/lang/util/OCMostlySingularMultiUniqueMap", "add"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (this.myAllValues.add(v)) {
                super.add((Object)k, (Object)v);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
    }
    
    public boolean processAllValues(@NotNull final Processor<? super V> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/util/OCMostlySingularMultiUniqueMap", "processAllValues"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ContainerUtil.process((Iterable)this.myAllValues, (Processor)processor);
    }
    
    public boolean remove(@NotNull final K k, @NotNull final V v) {
        try {
            if (k == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/util/OCMostlySingularMultiUniqueMap", "remove"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (v == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/lang/util/OCMostlySingularMultiUniqueMap", "remove"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        this.myAllValues.remove(v);
        return super.remove((Object)k, (Object)v);
    }
    
    public boolean removeAllValues(@NotNull final K k) {
        try {
            if (k == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/util/OCMostlySingularMultiUniqueMap", "removeAllValues"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.processForKey((Object)k, o -> {
            this.myAllValues.remove(o);
            return true;
        });
        return super.removeAllValues((Object)k);
    }
    
    public void compact() {
        ((THashMap)this.myAllValues).compact();
        super.compact();
    }
    
    public void clear() {
        this.myAllValues.clear();
        super.clear();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
