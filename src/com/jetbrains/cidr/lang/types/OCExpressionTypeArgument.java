// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.expression.OCVariadicPackExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;

public class OCExpressionTypeArgument implements OCTypeArgument
{
    private OCExpressionSymbol mySymbol;
    
    public OCExpressionTypeArgument() {
    }
    
    public OCExpressionTypeArgument(@NotNull final OCExpressionSymbol mySymbol) {
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/types/OCExpressionTypeArgument", "<init>"));
        }
        this.mySymbol = mySymbol;
    }
    
    @NotNull
    public OCExpressionSymbol getSymbol() {
        OCExpressionSymbol mySymbol;
        try {
            mySymbol = this.mySymbol;
            if (mySymbol == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCExpressionTypeArgument", "getSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySymbol;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCExpressionTypeArgument", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCExpressionTypeArgument", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCExpressionTypeArgument", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCExpressionTypeArgument ocExpressionTypeArgument = (OCExpressionTypeArgument)o;
        final OCExpressionTypeArgument ocExpressionTypeArgument2 = (OCExpressionTypeArgument)o2;
        try {
            if (!comparator.equalObjects(ocExpressionTypeArgument.mySymbol, (DeepEqual.Equality<Object>)ocExpressionTypeArgument2.mySymbol)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return true;
    }
    
    @Override
    public boolean equals(final Object o) {
        return DeepEqual.equalObjects(this, o);
    }
    
    @Override
    public boolean equals(final Object o, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCExpressionTypeArgument", "equals"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.equals(o);
    }
    
    @Override
    public int hashCode() {
        return this.mySymbol.hashCode();
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + this.getSymbol() + "]";
    }
    
    @Override
    public String getNameForPresentation(final OCType.Presentation presentation, @NotNull final OCResolveContext ocResolveContext, final boolean b, final int n) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCExpressionTypeArgument", "getNameForPresentation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.mySymbol.getName();
    }
    
    @Override
    public boolean isVariadic() {
        return this.mySymbol instanceof OCVariadicPackExpressionSymbol;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
