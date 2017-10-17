// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.util.Map;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.intellij.util.Function;
import com.intellij.util.PairFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DeepEqual
{
    public static boolean equalObjects(@Nullable final Object o, @Nullable final Object o2) {
        try {
            if (o == o2) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0032: {
            try {
                if (o == null) {
                    return false;
                }
                final Object o3 = o2;
                if (o3 == null) {
                    return false;
                }
                break Label_0032;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final Object o3 = o2;
                if (o3 == null) {
                    return false;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                if (o.getClass() != o2.getClass()) {
                    return false;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return newResolver().equalObjects(o, o2);
    }
    
    @NotNull
    public static Resolver newResolver() {
        DeepEqualImpl deepEqualImpl;
        try {
            deepEqualImpl = new DeepEqualImpl();
            if (deepEqualImpl == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/DeepEqual", "newResolver"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return deepEqualImpl;
    }
    
    public static Resolver newResolver(final PairFunction<Object, Object, Void> pairFunction) {
        return new DeepEqualImpl() {
            @Override
            protected <T> boolean equalObjects(final Equality<T> equality, final T t, final T t2) {
                pairFunction.fun((Object)t, (Object)t2);
                return super.equalObjects(equality, t, t2);
            }
        };
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public interface Equality<T>
    {
        boolean deepEqualStep(@NotNull final Comparator p0, @NotNull final T p1, @NotNull final T p2);
    }
    
    public interface Resolver extends Comparator
    {
        void clearCaches();
        
         <T> void setEquality(@NotNull final Class<T> p0, @Nullable final Function<T, Equality<T>> p1);
    }
    
    public interface Comparator
    {
         <T> boolean equalObjects(@Nullable final Equality<T> p0, @Nullable final Equality<T> p1);
        
        boolean equalIterable(@Nullable final Iterable p0, @Nullable final Iterable p1);
        
        boolean equalMultiMaps(@Nullable final MostlySingularMultiMap p0, @Nullable final MostlySingularMultiMap p1);
        
        boolean equalMaps(@Nullable final Map p0, @Nullable final Map p1);
        
        boolean equalObjects(@Nullable final Object p0, @Nullable final Object p1);
    }
}
