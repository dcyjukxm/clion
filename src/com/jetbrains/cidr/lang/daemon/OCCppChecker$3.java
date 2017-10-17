// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.util.CommonProcessors;

class OCCppChecker$3 extends CommonProcessors.CollectProcessor<OCDeclaratorSymbol> {
    protected boolean accept(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        if (ocDeclaratorSymbol.getKind() == OCSymbolKind.STRUCT_FIELD && !ocDeclaratorSymbol.isFriendOrStatic() && !(ocDeclaratorSymbol.getResolvedType() instanceof OCArrayType)) {
            final OCType resolvedType = ocDeclaratorSymbol.getResolvedType();
            return resolvedType instanceof OCStructType || resolvedType instanceof OCCppReferenceType || ocDeclaratorSymbol.isConst();
        }
        return false;
    }
}