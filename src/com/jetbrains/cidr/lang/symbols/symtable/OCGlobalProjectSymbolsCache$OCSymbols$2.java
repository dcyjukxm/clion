// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

class OCGlobalProjectSymbolsCache$OCSymbols$2 implements Processor<OCSymbol> {
    public boolean process(final OCSymbol ocSymbol) {
        ProgressManager.checkCanceled();
        OCSymbols.access$000(OCSymbols.this).add(ocSymbol.getName(), ocSymbol);
        if (ocSymbol instanceof OCNamespaceSymbol) {
            ((OCNamespaceSymbol)ocSymbol).processMembers(null, (Processor<OCSymbol>)this);
        }
        return true;
    }
}