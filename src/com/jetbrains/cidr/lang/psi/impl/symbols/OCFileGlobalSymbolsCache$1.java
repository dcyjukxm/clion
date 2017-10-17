// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.impl.OCFileImpl;
import com.jetbrains.cidr.lang.psi.OCFile;

class OCFileGlobalSymbolsCache$1 extends SLRUSynchronizedCache<OCFile, OCFileGlobalSymbols> {
    @NotNull
    @Override
    public OCFileGlobalSymbols createValue(final OCFile ocFile) {
        OCFileGlobalSymbols buildSymbols;
        try {
            buildSymbols = OCFileGlobalSymbols.buildSymbols((OCFileImpl)ocFile);
            if (buildSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbolsCache$1", "createValue"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return buildSymbols;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}