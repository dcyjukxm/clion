// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

class 1SomethingProcessedDetector implements Processor<OCSymbol>
{
    private final Processor<OCSymbol> myProcessor;
    private boolean mySomethingProcessed;
    private boolean myNamespaceProcessed;
    
    1SomethingProcessedDetector(final Processor<OCSymbol> myProcessor) {
        this.mySomethingProcessed = false;
        this.myNamespaceProcessed = false;
        this.myProcessor = myProcessor;
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        this.mySomethingProcessed = true;
        if (ocSymbol.getKind() == OCSymbolKind.NAMESPACE) {
            this.myNamespaceProcessed = true;
        }
        return this.myProcessor.process((Object)ocSymbol);
    }
}
