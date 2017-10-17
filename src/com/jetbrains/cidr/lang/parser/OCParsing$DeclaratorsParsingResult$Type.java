// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

public enum Type
{
    PARSED_FUNCTION, 
    PARSED, 
    FAIL;
    
    public boolean isOk() {
        return this == Type.PARSED_FUNCTION || this == Type.PARSED;
    }
}
