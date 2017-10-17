// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import java.util.Comparator;
import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import com.intellij.util.Function;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.LinkedHashSet;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CidrOrderMerger
{
    @NotNull
    public static <T> List<T> mergeWithUpdatedOrderPrecedence(@NotNull final List<T> list, @NotNull List<T> subList, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "originalOrder", "com/jetbrains/cidr/execution/CidrOrderMerger", "mergeWithUpdatedOrderPrecedence"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (subList == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "updatedOrder", "com/jetbrains/cidr/execution/CidrOrderMerger", "mergeWithUpdatedOrderPrecedence"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final com.intellij.util.Function<Object, Object> a = a(b);
        final com.intellij.util.Function<Object, Object> b2 = b(b);
        final LinkedHashSet<Object> set = new LinkedHashSet<Object>();
        for (final T next : list) {
            int n = 0;
            Label_0160: {
                try {
                    if (b) {
                        n = ContainerUtil.indexOfIdentity((List)subList, (Object)next);
                        break Label_0160;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                n = subList.indexOf(next);
            }
            final int n2 = n;
            try {
                if (n2 == -1) {
                    set.add(a.fun((Object)next));
                    continue;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            set.addAll(ContainerUtil.map((Collection)subList.subList(0, n2 + 1), (Function)a));
            subList = subList.subList(n2 + 1, subList.size());
        }
        List map;
        try {
            set.addAll(ContainerUtil.map((Collection)subList, (Function)a));
            map = ContainerUtil.map((Collection)set, (Function)b2);
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrOrderMerger", "mergeWithUpdatedOrderPrecedence"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return (List<T>)map;
    }
    
    @NotNull
    public static <T> List<T> mergeWithOriginalOrderPrecedence(@NotNull final List<T> list, @NotNull final List<T> list2, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentEntries", "com/jetbrains/cidr/execution/CidrOrderMerger", "mergeWithOriginalOrderPrecedence"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "updatedEntries", "com/jetbrains/cidr/execution/CidrOrderMerger", "mergeWithOriginalOrderPrecedence"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final com.intellij.util.Function<Object, Object> a = a(b);
        final com.intellij.util.Function<Object, Object> b2 = b(b);
        final ArrayList<Pair> list3 = new ArrayList<Pair>();
        for (final T next : list) {
            int n = 0;
            Label_0160: {
                try {
                    if (b) {
                        n = ContainerUtil.indexOfIdentity((List)list2, (Object)next);
                        break Label_0160;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                n = list2.indexOf(next);
            }
            list3.add(Pair.create((Object)next, (Object)n));
        }
        final ArrayList list4 = new ArrayList(ContainerUtil.mapNotNull((Collection)list3, pair -> {
            try {
                if ((int)pair.second == -1) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return (Integer)pair.second;
        }));
        list4.sort((Comparator)Integer::compareTo);
        final int intValue = (int)ContainerUtil.getFirstItem((Collection)list4, (Object)(-1));
        final LinkedHashSet<Object> set = new LinkedHashSet<Object>();
        for (final Pair pair : list3) {
            final int intValue2 = (int)pair.second;
            try {
                if (intValue2 == -1) {
                    set.add(a.fun(pair.first));
                    continue;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (intValue2 == intValue) {
                    set.addAll(ContainerUtil.map((Collection)list2.subList(0, intValue2), (Function)a));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            Integer value = (Integer)ContainerUtil.find((Iterable)list4, n2 -> {
                try {
                    if (n2 > intValue2) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return false;
            });
            if (value == null) {
                value = list2.size();
            }
            set.addAll(ContainerUtil.map((Collection)list2.subList(intValue2, value), (Function)a));
        }
        List map;
        try {
            set.addAll(ContainerUtil.map((Collection)list2, (Function)a));
            map = ContainerUtil.map((Collection)set, (Function)b2);
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/CidrOrderMerger", "mergeWithOriginalOrderPrecedence"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return (List<T>)map;
    }
    
    private static <T> Function<T, Object> a(final boolean b) {
        return (Function<T, Object>)(o -> {
            try {
                if (b) {
                    return new IdentityWrapper(o);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return o;
        });
    }
    
    private static <T> Function<Object, T> b(final boolean b) {
        return (Function<Object, T>)(o -> {
            try {
                if (b) {
                    return ((IdentityWrapper)o).wrappee;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return o;
        });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class IdentityWrapper
    {
        final Object wrappee;
        
        public IdentityWrapper(final Object wrappee) {
            this.wrappee = wrappee;
        }
        
        @Override
        public boolean equals(final Object o) {
            return this.wrappee == ((IdentityWrapper)o).wrappee;
        }
        
        @Override
        public int hashCode() {
            return System.identityHashCode(this.wrappee);
        }
    }
}
