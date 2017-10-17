// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.psi.OCFile;

public interface FileSymbolsProvider extends UsingNamespaceProvider
{
    OCFile getFile();
}
