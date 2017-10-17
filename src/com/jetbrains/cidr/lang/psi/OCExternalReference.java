// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.OCSymbol;

public interface OCExternalReference<T extends OCSymbol> extends OCReference<T>
{
    String getExternalComponentName();
}
