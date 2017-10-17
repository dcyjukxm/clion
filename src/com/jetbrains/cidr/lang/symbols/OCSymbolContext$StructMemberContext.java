// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;

public static class StructMemberContext extends OCSymbolContext
{
    private final boolean myStatic;
    
    public StructMemberContext(@Nullable final OCExpectedTypeUtil.Expectable expectable, final OCSymbolKind ocSymbolKind, final boolean myStatic, final OCSymbol ocSymbol) {
        super(expectable, ocSymbolKind, ocSymbol);
        this.myStatic = myStatic;
    }
    
    public boolean isStatic() {
        return this.myStatic;
    }
    
    @Override
    public String getName() {
        final String name = super.getName();
        return this.myStatic ? ("static " + name) : name;
    }
}
