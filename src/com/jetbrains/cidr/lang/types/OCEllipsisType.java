// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;

public class OCEllipsisType extends OCType
{
    private static final OCEllipsisType INSTANCE;
    
    public static OCEllipsisType instance() {
        return OCEllipsisType.INSTANCE;
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitEllipsisReferenceType(this);
    }
    
    @Override
    public String getFormatString() {
        return "%p";
    }
    
    @Override
    public int hashCode() {
        return this.baseHashCode();
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCEllipsisType", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCEllipsisType", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCEllipsisType", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        throw new IllegalStateException("different instances detected");
    }
    
    static {
        INSTANCE = new OCEllipsisType();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
