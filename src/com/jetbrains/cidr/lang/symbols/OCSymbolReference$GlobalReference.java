// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;

public static class GlobalReference extends OCSymbolReference
{
    @Nullable
    protected OCSymbolWithQualifiedName mySymbolContext;
    
    public GlobalReference() {
    }
    
    public GlobalReference(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final OCSymbolWithQualifiedName mySymbolContext, final SymbolFilter symbolFilter) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "<init>"));
        }
        super(ocQualifiedName, symbolFilter);
        if (this.mySymbolContext == null) {
            this.mySymbolContext = mySymbolContext;
        }
    }
    
    @Nullable
    public OCSymbolWithQualifiedName getSymbolContext() {
        return this.mySymbolContext;
    }
    
    public void setSymbolContext(@Nullable final OCSymbolWithQualifiedName mySymbolContext) {
        this.mySymbolContext = mySymbolContext;
    }
    
    @Override
    public OCSymbolReference getSymbolReferenceToQualifier() {
        return new GlobalReference(this.getQualifiedName().getQualifier(), this.mySymbolContext, this.getFilterForQualifier());
    }
    
    @NotNull
    @Override
    public OCSymbolReference createReferenceInSameContext(@NotNull final OCQualifiedName ocQualifiedName) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "createReferenceInSameContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        GlobalReference globalReference;
        try {
            globalReference = new GlobalReference(ocQualifiedName, this.mySymbolContext, this.myFilter);
            if (globalReference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "createReferenceInSameContext"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return globalReference;
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final GlobalReference globalReference = this;
                final Class<? extends GlobalReference> clazz = globalReference.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final GlobalReference globalReference = this;
                final Class<? extends GlobalReference> clazz = globalReference.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!super.equals(o)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final GlobalReference globalReference2 = (GlobalReference)o;
        Label_0093: {
            Label_0086: {
                try {
                    if (this.mySymbolContext == null) {
                        break Label_0093;
                    }
                    final GlobalReference globalReference3 = this;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = globalReference3.mySymbolContext;
                    final GlobalReference globalReference4 = globalReference2;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = globalReference4.mySymbolContext;
                    final boolean b = ocSymbolWithQualifiedName.equals(ocSymbolWithQualifiedName2);
                    if (!b) {
                        break Label_0086;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final GlobalReference globalReference3 = this;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = globalReference3.mySymbolContext;
                    final GlobalReference globalReference4 = globalReference2;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = globalReference4.mySymbolContext;
                    final boolean b = ocSymbolWithQualifiedName.equals(ocSymbolWithQualifiedName2);
                    if (!b) {
                        return false;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            try {
                if (globalReference2.mySymbolContext != null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = super.hashCode();
        int n;
        try {
            n = 31 * hashCode;
            if (this.mySymbolContext != null) {
                final int hashCode2 = this.mySymbolContext.hashCode();
                return n + hashCode2;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int hashCode2 = 0;
        return n + hashCode2;
    }
    
    @Override
    public String toString() {
        return "GLOBAL (" + this.getQualifiedName() + "):" + this.mySymbolContext;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCSymbolReference ocSymbolReference, @NotNull final OCSymbolReference ocSymbolReference2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbolReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocSymbolReference2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, ocSymbolReference, ocSymbolReference2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final GlobalReference globalReference = (GlobalReference)ocSymbolReference;
        final GlobalReference globalReference2 = (GlobalReference)ocSymbolReference2;
        try {
            if (!comparator.equalObjects(globalReference.mySymbolContext, (DeepEqual.Equality<Object>)globalReference2.mySymbolContext)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
