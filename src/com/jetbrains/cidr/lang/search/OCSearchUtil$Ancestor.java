// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;

public static class Ancestor
{
    OCSymbolWithParent symbol;
    boolean isOutOfProject;
    
    public OCSymbolWithParent getSymbol() {
        return this.symbol;
    }
    
    public boolean isOutOfProject() {
        return this.isOutOfProject;
    }
}
