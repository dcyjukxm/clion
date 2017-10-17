// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceAliasSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCCppNamespaceAliasImpl$1 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol instanceof OCNamespaceAliasSymbol;
    }
}