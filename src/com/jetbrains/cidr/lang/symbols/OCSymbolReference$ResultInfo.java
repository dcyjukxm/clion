// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Set;
import java.util.List;

private static class ResultInfo
{
    final List<OCSymbol> symbols;
    final Set<OCTypeParameterSymbol> typeDependencies;
    
    private ResultInfo(@NotNull final List<OCSymbol> symbols, @Nullable final Set<OCTypeParameterSymbol> typeDependencies) {
        if (symbols == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbols", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$ResultInfo", "<init>"));
        }
        this.symbols = symbols;
        this.typeDependencies = typeDependencies;
    }
}
