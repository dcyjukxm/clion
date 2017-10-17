// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

private enum TypeParsingResult
{
    TYPE_PARSED_FOR_SURE, 
    TYPE_PARSED, 
    EMPTY_TYPE_PARSED, 
    NONE, 
    UNKNOWN;
    
    public boolean isEmpty() {
        return this == TypeParsingResult.NONE || this == TypeParsingResult.EMPTY_TYPE_PARSED;
    }
}
