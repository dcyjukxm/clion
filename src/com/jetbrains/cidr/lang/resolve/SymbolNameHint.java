// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.intellij.openapi.util.Key;

public class SymbolNameHint
{
    public static final Key<SymbolNameHint> KEY;
    private final String myName;
    
    public SymbolNameHint(final String myName) {
        this.myName = myName;
    }
    
    public String getName() {
        return this.myName;
    }
    
    static {
        KEY = Key.create("SymbolNameHint");
    }
}
