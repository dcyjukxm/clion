// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

private enum DeclaratorParsingResult
{
    PARSED_FUNCTION, 
    PARSED, 
    FAIL, 
    PARSED_EMPTY, 
    FAIL_EMPTY;
    
    public boolean isOk() {
        return this == DeclaratorParsingResult.PARSED_FUNCTION || this == DeclaratorParsingResult.PARSED || this == DeclaratorParsingResult.PARSED_EMPTY;
    }
    
    public boolean isEmpty() {
        return this == DeclaratorParsingResult.PARSED_EMPTY || this == DeclaratorParsingResult.FAIL_EMPTY;
    }
    
    public static DeclaratorParsingResult parsed(final boolean b, final boolean b2) {
        return b2 ? DeclaratorParsingResult.PARSED_FUNCTION : (b ? DeclaratorParsingResult.PARSED_EMPTY : DeclaratorParsingResult.PARSED);
    }
    
    public static DeclaratorParsingResult fail(final boolean b) {
        return b ? DeclaratorParsingResult.FAIL_EMPTY : DeclaratorParsingResult.FAIL;
    }
}
