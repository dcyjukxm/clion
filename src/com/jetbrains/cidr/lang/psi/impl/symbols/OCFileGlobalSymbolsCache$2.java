// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.impl.OCFileImpl;
import com.jetbrains.cidr.lang.psi.OCFile;

class OCFileGlobalSymbolsCache$2 extends SLRUSynchronizedCache<OCFile, OCLightFileGlobalSymbols> {
    @NotNull
    @Override
    public OCLightFileGlobalSymbols createValue(final OCFile ocFile) {
        OCLightFileGlobalSymbols buildSymbols;
        try {
            buildSymbols = OCLightFileGlobalSymbols.buildSymbols((OCFileImpl)ocFile, false);
            if (buildSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/symbols/OCFileGlobalSymbolsCache$2", "createValue"));
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