// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import org.jetbrains.annotations.Nullable;

public static class SelectorPartSymbolImpl implements SelectorPartSymbol
{
    @Nullable
    private String selectorName;
    @Nullable
    private OCDeclaratorSymbol parameter;
    @NotNull
    private OCTypeSubstitution substitution;
    
    public SelectorPartSymbolImpl() {
        this.substitution = OCTypeSubstitution.ID;
    }
    
    public SelectorPartSymbolImpl(@Nullable final OCDeclaratorSymbol parameter, @Nullable final String selectorName) {
        this.substitution = OCTypeSubstitution.ID;
        this.parameter = parameter;
        this.selectorName = selectorName;
    }
    
    public SelectorPartSymbolImpl(@NotNull final SelectorPartSymbolImpl selectorPartSymbolImpl, @NotNull final OCTypeSubstitution ocTypeSubstitution, @NotNull final OCResolveContext ocResolveContext) {
        if (selectorPartSymbolImpl == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origin", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "<init>"));
        }
        if (ocTypeSubstitution == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "<init>"));
        }
        this(selectorPartSymbolImpl.parameter, selectorPartSymbolImpl.selectorName);
        this.substitution = OCTypeSubstitution.compose(selectorPartSymbolImpl.substitution, ocTypeSubstitution, ocResolveContext);
    }
    
    @Nullable
    @Override
    public OCDeclaratorSymbol getParameter() {
        return this.substitution.substitute(this.parameter, new OCResolveContext());
    }
    
    @Nullable
    @Override
    public OCDeclaratorSymbol getParameterWithoutSubstitution() {
        return this.parameter;
    }
    
    @NotNull
    @Override
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution substitution;
        try {
            substitution = this.substitution;
            if (substitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return substitution;
    }
    
    @Nullable
    @Override
    public String getSelectorName() {
        return this.selectorName;
    }
    
    @Override
    public boolean equals(final Object o) {
        return DeepEqual.equalObjects(this, o);
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final SelectorPartSymbol selectorPartSymbol, @NotNull final SelectorPartSymbol selectorPartSymbol2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (selectorPartSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (selectorPartSymbol2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!Comparing.equal(selectorPartSymbol.getSelectorName(), selectorPartSymbol2.getSelectorName())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!comparator.equalObjects(selectorPartSymbol.getSubstitution(), (DeepEqual.Equality<Object>)selectorPartSymbol2.getSubstitution())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return comparator.equalObjects(selectorPartSymbol.getParameter(), (DeepEqual.Equality<Object>)selectorPartSymbol2.getParameter());
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        Label_0022: {
            try {
                if (this.selectorName != null) {
                    hashCode = this.selectorName.hashCode();
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n = hashCode;
        int n2;
        try {
            n2 = 31 * n;
            if (this.parameter != null) {
                final int hashCode2 = this.parameter.hashCode();
                return n2 + hashCode2;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int hashCode2 = 0;
        return n2 + hashCode2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
