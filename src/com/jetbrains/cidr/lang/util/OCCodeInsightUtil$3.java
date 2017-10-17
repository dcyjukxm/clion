// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

static final class OCCodeInsightUtil$3 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ String val$categoryName;
    final /* synthetic */ OCSymbolKind val$symbolKind;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return !ocSymbol.isSynthetic() && (this.val$categoryName == null || !(ocSymbol instanceof OCClassSymbol) || this.val$categoryName.equals(((OCClassSymbol)ocSymbol).getCategoryName())) && (this.val$symbolKind == null || OCResolveUtil.isDuplicate(this.val$symbolKind, ocSymbol.getKind()));
    }
}