// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public interface OCLocalSymbolDeclarator<T extends OCSymbol> extends OCSymbolDeclarator<T>
{
    @Nullable
    T getLocalSymbol();
}
