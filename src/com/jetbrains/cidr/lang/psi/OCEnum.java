// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

public interface OCEnum extends OCStructLike
{
    void processEnumConsts(final Processor<OCSymbol> p0);
    
    boolean isEnumClass();
}
