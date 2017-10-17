// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import java.util.HashSet;
import com.intellij.util.Processor;

class OCDirectStructInheritorsSearch$DefaultSearcher$1 implements Processor<String> {
    final /* synthetic */ HashSet val$aliases;
    final /* synthetic */ OCStructInheritorsSearch.SearchParameters val$p;
    
    public boolean process(final String s) {
        if (!this.val$aliases.contains(s)) {
            this.val$aliases.add(s);
            OCGlobalProjectSymbolsCache.processAliasNamesForType(this.val$p.getProject(), s, (Processor<String>)this);
        }
        return true;
    }
}