// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.intellij.util.CommonProcessors;

static final class OCCppDefinitionsUtil$1 extends CommonProcessors.FindFirstProcessor<OCNamespaceSymbol> {
    final /* synthetic */ VirtualFile val$virtualFile;
    
    protected boolean accept(final OCNamespaceSymbol ocNamespaceSymbol) {
        return this.val$virtualFile != null && this.val$virtualFile.equals(ocNamespaceSymbol.getContainingFile());
    }
}