// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

public enum Property
{
    IS_PASS_BY_REF, 
    IS_HAS_INITIALIZER, 
    IS_DECLARED_IN_LAMBDA_CAPTURE;
    
    public static final int DEFAULT = 0;
    
    public int getMask() {
        assert this.ordinal() < 8;
        return 1 << 31 - this.ordinal();
    }
}
