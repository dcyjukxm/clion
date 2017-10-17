// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.handlers;

import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

static final class OCGoogleGenerateTestHandler$1 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ OCFile val$file;
    final /* synthetic */ Condition val$condition;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return this.val$file.equals(ocSymbol.getContainingOCFile()) && (this.val$condition == null || this.val$condition.value((Object)ocSymbol));
    }
}