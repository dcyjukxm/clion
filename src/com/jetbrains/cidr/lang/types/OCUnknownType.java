// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;

public class OCUnknownType extends OCMagicType
{
    public static final OCUnknownType INSTANCE;
    
    @Override
    public boolean isUnknown() {
        return true;
    }
    
    @Override
    public boolean isUnresolved(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCUnknownType", "isUnresolved"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return true;
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitUnknownType(this);
    }
    
    @Override
    public void attachAliasName(@Nullable final String s) {
    }
    
    @Override
    public void attachGuessedType(@Nullable final OCType ocType) {
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCUnknownType", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCUnknownType", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCUnknownType", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        throw new IllegalStateException("different instances detected");
    }
    
    static {
        INSTANCE = new OCUnknownType();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
