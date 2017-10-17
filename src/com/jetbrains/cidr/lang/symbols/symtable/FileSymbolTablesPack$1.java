// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import gnu.trove.TObjectHashingStrategy;

class FileSymbolTablesPack$1 implements TObjectHashingStrategy<OCSymbol> {
    public int computeHashCode(final OCSymbol ocSymbol) {
        return ocSymbol.hashCodeExcludingOffset();
    }
    
    public boolean equals(final OCSymbol ocSymbol, final OCSymbol ocSymbol2) {
        ProgressManager.checkCanceled();
        return FileSymbolTablesPack.access$000(FileSymbolTablesPack.this).equalObjects(ocSymbol, (DeepEqual.Equality<Object>)ocSymbol2);
    }
}