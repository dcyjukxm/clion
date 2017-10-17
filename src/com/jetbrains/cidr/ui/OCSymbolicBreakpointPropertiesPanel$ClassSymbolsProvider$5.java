// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ui;

import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.application.ReadAction;

class OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$5 extends ReadAction<OCQualifiedName> {
    final /* synthetic */ String val$parentName;
    final /* synthetic */ CommonProcessors.CollectProcessor val$processor;
    
    protected void run(@NotNull final Result<OCQualifiedName> result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/ui/OCSymbolicBreakpointPropertiesPanel$ClassSymbolsProvider$5", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        boolean b = false;
        Label_0099: {
            Label_0075: {
                try {
                    if (this.val$parentName == null) {
                        break Label_0099;
                    }
                    if (!this.val$parentName.isEmpty()) {
                        break Label_0075;
                    }
                }
                catch (Throwable t2) {
                    throw a(t2);
                }
                b = true;
                break Label_0099;
            }
            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(ClassSymbolsProvider.access$200(ClassSymbolsProvider.this), (Processor<OCSymbol>)(ocSymbol -> {
                try {
                    if (ocSymbol instanceof OCNamespaceSymbol) {
                        ((OCNamespaceSymbol)ocSymbol).processMembers(null, (Processor<OCSymbol>)collectProcessor);
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return true;
            }), this.val$parentName);
            try {
                if (this.val$processor.getResults().isEmpty()) {
                    OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(ClassSymbolsProvider.access$200(ClassSymbolsProvider.this), (Processor<OCSymbol>)this.val$processor, null, b);
                }
            }
            catch (Throwable t3) {
                throw a(t3);
            }
        }
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}