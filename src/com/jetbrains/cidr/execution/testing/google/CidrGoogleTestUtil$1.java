// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

static final class CidrGoogleTestUtil$1 extends CommonProcessors.CollectProcessor<OCSymbol> {
    final /* synthetic */ String val$suiteName;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol instanceof OCNamespaceSymbol && (this.val$suiteName == null || ocSymbol.getName().contains(this.val$suiteName));
    }
}