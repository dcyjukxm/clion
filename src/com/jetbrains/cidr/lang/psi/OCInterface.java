// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;

public interface OCInterface extends OCClassDeclaration<OCInterfaceSymbol>
{
    OCInterfaceSymbol getSymbol();
}
