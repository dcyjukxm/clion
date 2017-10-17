// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import org.jetbrains.annotations.Contract;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

class OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$3 implements Processor<OCSymbol> {
    final /* synthetic */ CommonProcessors.CollectProcessor val$methodSymbolCollector;
    
    @Contract("null -> true")
    public boolean process(final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCClassSymbol) {
            ((OCClassSymbol)ocSymbol).processMembersInAllCategories((String)null, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)this.val$methodSymbolCollector);
            for (final String s : ((OCClassSymbol)ocSymbol).getProtocolNames()) {
                if (!s.equals(ocSymbol.getName())) {
                    OCGlobalProjectSymbolsCache.processTopLevelSymbols(ClassSymbolsProvider.access$200(ClassSymbolsProvider.this), (Processor<OCSymbol>)this, s);
                }
            }
        }
        return true;
    }
}