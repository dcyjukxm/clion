// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCKeywordElementType;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public enum OCCastKind
{
    STATIC_CAST(OCTokenTypes.STATIC_CAST_CPP_KEYWORD), 
    CONST_CAST(OCTokenTypes.CONST_CAST_CPP_KEYWORD), 
    DYNAMIC_CAST(OCTokenTypes.DYNAMIC_CAST_CPP_KEYWORD), 
    REINTERPRET_CAST(OCTokenTypes.REINTERPRET_CAST_CPP_KEYWORD), 
    C_STYLE_CAST((OCKeywordElementType)null);
    
    @NotNull
    private final String myTokenText;
    
    private OCCastKind(final OCKeywordElementType ocKeywordElementType) {
        this.myTokenText = ((ocKeywordElementType != null) ? ocKeywordElementType.getName() : "");
    }
    
    @NotNull
    public String getTokenText() {
        String myTokenText;
        try {
            myTokenText = this.myTokenText;
            if (myTokenText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/OCCastKind", "getTokenText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myTokenText;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
