// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.objc.OCGenericParameterSymbol;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;

public class OCTypeParameterType extends OCMagicType
{
    @NotNull
    private OCTypeParameterSymbol mySymbol;
    
    public OCTypeParameterType() {
    }
    
    public OCTypeParameterType(@NotNull final OCTypeParameterSymbol ocTypeParameterSymbol) {
        if (ocTypeParameterSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/types/OCTypeParameterType", "<init>"));
        }
        this(ocTypeParameterSymbol, false, false);
    }
    
    public OCTypeParameterType(@NotNull final OCTypeParameterSymbol mySymbol, final boolean b, final boolean b2) {
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/types/OCTypeParameterType", "<init>"));
        }
        super(mySymbol.getName(), b, b2);
        this.mySymbol = mySymbol;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCTypeParameterType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCTypeParameterType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCTypeParameterType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final OCTypeParameterType ocTypeParameterType = (OCTypeParameterType)o;
        final OCTypeParameterType ocTypeParameterType2 = (OCTypeParameterType)o2;
        try {
            if (!comparator.equalObjects(ocTypeParameterType.mySymbol, (DeepEqual.Equality<Object>)ocTypeParameterType2.mySymbol)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        return true;
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitTypeParameterType(this);
    }
    
    @Override
    public boolean isPointerToObjectCompatible() {
        return this.mySymbol instanceof OCGenericParameterSymbol;
    }
    
    @NotNull
    public OCTypeParameterSymbol getSymbol() {
        OCTypeParameterSymbol mySymbol;
        try {
            mySymbol = this.mySymbol;
            if (mySymbol == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCTypeParameterType", "getSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return mySymbol;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
