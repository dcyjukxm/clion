// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

public enum Property
{
    IS_TEMPLATE, 
    IS_OPERATOR, 
    IS_PURE_VIRTUAL, 
    IS_CONVERSION_OPERATOR, 
    IS_UDL;
    
    public static final int DEFAULT = 0;
    
    public int getMask() {
        assert this.ordinal() < 8;
        return 1 << 31 - this.ordinal();
    }
}
