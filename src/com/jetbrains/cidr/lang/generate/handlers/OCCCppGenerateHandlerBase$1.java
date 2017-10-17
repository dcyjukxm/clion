// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.intellij.util.CommonProcessors;

class OCCCppGenerateHandlerBase$1 extends CommonProcessors.CollectProcessor<OCNamespaceSymbol> {
    protected boolean accept(final OCNamespaceSymbol ocNamespaceSymbol) {
        final OCSymbolKind kind = ocNamespaceSymbol.getKind();
        return (kind == OCSymbolKind.STRUCT || (OCCCppGenerateHandlerBase.this.allowUnions() && kind == OCSymbolKind.UNION) || (OCCCppGenerateHandlerBase.this.allowNamespacesAndTopLevel() && kind == OCSymbolKind.NAMESPACE)) && !ocNamespaceSymbol.isPredeclaration();
    }
}