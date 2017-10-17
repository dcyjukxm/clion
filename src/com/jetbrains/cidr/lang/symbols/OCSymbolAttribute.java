// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;

public enum OCSymbolAttribute
{
    CONST(OCTokenTypes.CONST_KEYWORD), 
    STATIC(OCTokenTypes.STATIC_KEYWORD), 
    EXTERN(OCTokenTypes.EXTERN_KEYWORD), 
    BLOCK_MODIFIABLE(OCTokenTypes.BLOCK_KEYWORD), 
    MUTABLE((OCElementType)OCTokenTypes.MUTABLE_CPP_KEYWORD), 
    CONSTEXPR((OCElementType)OCTokenTypes.CONSTEXPR_CPP_KEYWORD), 
    REGISTER(OCTokenTypes.REGISTER_KEYWORD), 
    THREAD_LOCAL(OCTokenTypes.THREAD_LOCAL_KEYWORD), 
    FRIEND((OCElementType)OCTokenTypes.FRIEND_CPP_KEYWORD), 
    FINAL((OCElementType)OCTokenTypes.FINAL_CPP_KEYWORD), 
    VIRTUAL((OCElementType)OCTokenTypes.VIRTUAL_CPP_KEYWORD), 
    EXPLICIT((OCElementType)OCTokenTypes.EXPLICIT_CPP_KEYWORD), 
    DEFAULT(OCTokenTypes.DEFAULT_KEYWORD), 
    DELETE((OCElementType)OCTokenTypes.DELETE_CPP_KEYWORD), 
    OVERRIDE((OCElementType)OCTokenTypes.OVERRIDE_CPP_KEYWORD), 
    INLINE(OCTokenTypes.INLINE_KEYWORD);
    
    private final OCElementType myType;
    public static final int DEFAULT_ATTRS = 0;
    
    private OCSymbolAttribute(final OCElementType myType) {
        this.myType = myType;
    }
    
    @Override
    public String toString() {
        return this.myType.getName();
    }
    
    public int getMask() {
        assert this.ordinal() < 24;
        return 1 << this.ordinal();
    }
}
