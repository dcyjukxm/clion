// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;

public interface OCClassPredeclaration extends OCClassDeclaration<OCClassSymbol>
{
    boolean isProtocol();
}
