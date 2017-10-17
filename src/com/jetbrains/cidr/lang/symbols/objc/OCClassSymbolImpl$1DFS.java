// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import java.util.HashSet;
import java.util.Stack;
import java.util.Set;

class 1DFS
{
    Set<OCClassSymbol> processedClasses;
    Stack<OCClassSymbol> stack;
    boolean wasProcessed;
    final /* synthetic */ String val$ancestorName;
    final /* synthetic */ OCClassSymbol val$ancestor;
    
    1DFS(final String val$ancestorName, final OCClassSymbol val$ancestor) {
        this.val$ancestorName = val$ancestorName;
        this.val$ancestor = val$ancestor;
        this.processedClasses = new HashSet<OCClassSymbol>();
        this.stack = new Stack<OCClassSymbol>();
    }
    
     <T extends OCClassSymbol> boolean traverse(final String s, final Class<T> clazz) {
        if (s.equals(this.val$ancestorName) && clazz == OCProtocolSymbol.class == this.val$ancestor instanceof OCProtocolSymbol) {
            return true;
        }
        this.wasProcessed = false;
        OCGlobalProjectSymbolsCache.processTopLevelSymbols(OCClassSymbolImpl.this.getProject(), (Processor<OCSymbol>)new OCCommonProcessors.TypeFilteredProcessor((com.intellij.util.Processor<Object>)(ocClassSymbol -> {
            if (!ocClassSymbol.isPredeclaration()) {
                this.wasProcessed = true;
                if (!this.processedClasses.contains(ocClassSymbol)) {
                    this.processedClasses.add(ocClassSymbol);
                    this.stack.push(ocClassSymbol);
                }
            }
            return true;
        }), clazz), s);
        return false;
    }
}
