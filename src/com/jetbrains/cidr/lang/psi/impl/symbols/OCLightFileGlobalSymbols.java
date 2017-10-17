// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import com.intellij.openapi.vfs.VirtualFile;
import java.util.Map;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.impl.OCFileImpl;

public class OCLightFileGlobalSymbols extends OCFileSymbolsContainerBase
{
    private final boolean myTypesOnly;
    
    public OCLightFileGlobalSymbols(final OCFileImpl ocFileImpl, final boolean myTypesOnly) {
        super(ocFileImpl);
        this.myTypesOnly = myTypesOnly;
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        final OCSymbolKind kind = ocSymbol.getKind();
        if (kind == OCSymbolKind.NAMESPACE_USING_SYMBOL) {
            this.myUsingSymbols.add((OCUsingSymbol)ocSymbol);
            this.myAllUsingSymbolsToIndex.put((OCUsingSymbol)ocSymbol, this.myCurrentUsingIndex++);
        }
        else if (!this.myTypesOnly || kind.isType() || kind == OCSymbolKind.NAMESPACE_ALIAS || kind == OCSymbolKind.NAMESPACE || kind == OCSymbolKind.SYMBOL_USING_SYMBOL) {
            this.myNameToSymbol.add((Object)ocSymbol.getName(), (Object)ocSymbol);
            if (ocSymbol.getKind() == OCSymbolKind.NAMESPACE) {
                this.fillUsingsIndex((OCNamespaceSymbol)ocSymbol);
            }
        }
        if (ocSymbol instanceof OCNamespaceSymbol && ((OCNamespaceSymbol)ocSymbol).isInlineNamespace()) {
            this.myInlineNamespaces.add((OCNamespaceSymbol)ocSymbol);
        }
        return true;
    }
    
    public static OCLightFileGlobalSymbols buildSymbols(final OCFileImpl ocFileImpl, final boolean b) {
        final OCLightFileGlobalSymbols ocLightFileGlobalSymbols = new OCLightFileGlobalSymbols(ocFileImpl, b);
        OCFileGlobalSymbolsCache.processFile(ocFileImpl, (Processor<OCSymbol>)ocLightFileGlobalSymbols, null);
        return ocLightFileGlobalSymbols;
    }
}
