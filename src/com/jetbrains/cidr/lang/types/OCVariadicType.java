// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;

public class OCVariadicType extends OCType
{
    private OCType myUnderlyingType;
    
    public OCVariadicType() {
    }
    
    public OCVariadicType(@NotNull final OCType myUnderlyingType) {
        if (myUnderlyingType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "underlyingType", "com/jetbrains/cidr/lang/types/OCVariadicType", "<init>"));
        }
        this.myUnderlyingType = myUnderlyingType;
    }
    
    @NotNull
    public OCType getUnderlyingType() {
        OCType myUnderlyingType;
        try {
            myUnderlyingType = this.myUnderlyingType;
            if (myUnderlyingType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCVariadicType", "getUnderlyingType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myUnderlyingType;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCVariadicType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCVariadicType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCVariadicType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return comparator.equalObjects(((OCVariadicType)o).myUnderlyingType, (DeepEqual.Equality<Object>)((OCVariadicType)o2).myUnderlyingType);
    }
    
    @Override
    public int hashCode() {
        return this.baseHashCode() * 31 + this.myUnderlyingType.hashCode();
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitVariadicType(this);
    }
    
    @Override
    public boolean isInstanceable() {
        return this.myUnderlyingType.isInstanceable();
    }
    
    @Override
    public boolean isVariadic() {
        return true;
    }
    
    @Override
    public boolean isMagicInside(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCVariadicType", "isMagicInside"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myUnderlyingType.isMagicInside(ocResolveContext);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
