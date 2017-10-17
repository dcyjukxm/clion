// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;

public enum StaticMode
{
    STATIC, 
    INSTANCE, 
    NO_MATTER;
    
    public boolean fitsStaticness(final OCMemberSymbol ocMemberSymbol) {
        if (ocMemberSymbol == null) {
            return false;
        }
        switch (this) {
            case STATIC: {
                return ocMemberSymbol.isStatic() || ((OCSymbolWithParent<T, OCClassSymbol>)ocMemberSymbol).getParent().getName().equals("NSObject");
            }
            case INSTANCE: {
                return !ocMemberSymbol.isStatic();
            }
            case NO_MATTER: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
