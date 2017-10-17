// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

public enum SymbolKindFilter implements SymbolFilter
{
    NONE, 
    ONLY_NAMESPACE, 
    ONLY_NAMESPACE_LIKE, 
    ONLY_STRUCT, 
    ONLY_ENUM, 
    ONLY_UNION;
    
    public static SymbolFilter parse(final String s) {
        if (s == null) {
            return SymbolKindFilter.NONE;
        }
        if (s.equals("struct") || s.equals("class")) {
            return SymbolKindFilter.ONLY_STRUCT;
        }
        if (s.equals("enum")) {
            return SymbolKindFilter.ONLY_ENUM;
        }
        if (s.equals("union")) {
            return SymbolKindFilter.ONLY_UNION;
        }
        return SymbolKindFilter.NONE;
    }
    
    @Override
    public boolean accept(final OCSymbol ocSymbol) {
        final OCSymbolKind kind = ocSymbol.getKind();
        switch (this) {
            case NONE: {
                return true;
            }
            case ONLY_NAMESPACE: {
                return kind == OCSymbolKind.NAMESPACE || kind == OCSymbolKind.NAMESPACE_ALIAS;
            }
            case ONLY_NAMESPACE_LIKE: {
                return kind.canBeNamespace() || kind == OCSymbolKind.USING_SYMBOL_ALIAS || kind == OCSymbolKind.SYMBOL_USING_SYMBOL;
            }
            case ONLY_STRUCT: {
                return kind == OCSymbolKind.STRUCT;
            }
            case ONLY_ENUM: {
                return kind == OCSymbolKind.ENUM;
            }
            case ONLY_UNION: {
                return kind == OCSymbolKind.UNION;
            }
            default: {
                return false;
            }
        }
    }
    
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
