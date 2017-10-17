// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCChangeGCCAttributeIntentionAction;

class OCUnusedCodeInspection$UnusedVisitor$3 extends OCChangeGCCAttributeIntentionAction.SuppressFix {
    @Override
    protected boolean isAvailable(final OCSymbol ocSymbol) {
        return !(ocSymbol instanceof OCClassSymbol) && !(ocSymbol instanceof OCPropertySymbol) && !(ocSymbol instanceof OCMacroSymbol) && ocSymbol.getKind() != OCSymbolKind.ENUM_CONST;
    }
}