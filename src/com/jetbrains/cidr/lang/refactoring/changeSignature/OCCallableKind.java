// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.openapi.util.text.StringUtil;

public enum OCCallableKind
{
    METHOD, 
    FUNCTION, 
    BLOCK, 
    LAMBDA;
    
    @Override
    public String toString() {
        switch (this) {
            case METHOD: {
                return "Method";
            }
            case FUNCTION: {
                return "Function";
            }
            case BLOCK: {
                return "Block";
            }
            case LAMBDA: {
                return "Lambda";
            }
            default: {
                return null;
            }
        }
    }
    
    public String toStringLowercase() {
        return StringUtil.decapitalize(this.toString());
    }
    
    public OCSymbolKind getSymbolKind() {
        switch (this) {
            case METHOD: {
                return OCSymbolKind.METHOD;
            }
            case FUNCTION: {
                return OCSymbolKind.FUNCTION_DECLARATION;
            }
            case BLOCK: {
                return OCSymbolKind.BLOCK;
            }
            case LAMBDA: {
                return OCSymbolKind.LAMBDA;
            }
            default: {
                return null;
            }
        }
    }
    
    public static OCCallableKind parse(final String s) {
        for (final OCCallableKind ocCallableKind : values()) {
            if (ocCallableKind.toString().equals(s)) {
                return ocCallableKind;
            }
        }
        assert false : "Unknown callable kind: " + s;
        return null;
    }
}
