// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import java.util.List;

static class DeclaratorsParsingResult
{
    Type myType;
    List<String> mySingleNames;
    
    DeclaratorsParsingResult(final Type myType, final List<String> mySingleNames) {
        this.myType = Type.FAIL;
        this.myType = myType;
        this.mySingleNames = mySingleNames;
    }
    
    public enum Type
    {
        PARSED_FUNCTION, 
        PARSED, 
        FAIL;
        
        public boolean isOk() {
            return this == Type.PARSED_FUNCTION || this == Type.PARSED;
        }
    }
}
