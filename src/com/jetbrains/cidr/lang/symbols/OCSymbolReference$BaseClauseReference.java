// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import org.jetbrains.annotations.NotNull;

public static class BaseClauseReference extends GlobalReference
{
    public BaseClauseReference() {
    }
    
    public BaseClauseReference(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final SymbolFilter symbolFilter) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$BaseClauseReference", "<init>"));
        }
        super(ocQualifiedName, ocSymbolWithQualifiedName, symbolFilter);
    }
    
    @Override
    public OCSymbolReference getSymbolReferenceToQualifier() {
        return new BaseClauseReference(this.getQualifiedName().getQualifier(), this.getSymbolContext(), this.getFilterForQualifier());
    }
    
    @NotNull
    @Override
    public OCSymbolReference createReferenceInSameContext(@NotNull final OCQualifiedName ocQualifiedName) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$BaseClauseReference", "createReferenceInSameContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        BaseClauseReference baseClauseReference;
        try {
            baseClauseReference = new BaseClauseReference(ocQualifiedName, this.getSymbolContext(), this.myFilter);
            if (baseClauseReference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$BaseClauseReference", "createReferenceInSameContext"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return baseClauseReference;
    }
    
    @Override
    public String toString() {
        return "BASE CLAUSE (" + this.getQualifiedName() + "):" + this.getSymbolContext();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
