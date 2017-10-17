// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;

public class OCGotoClassContributor extends OCGotoByNameContributor
{
    public OCGotoClassContributor() {
        super((Condition<OCSymbol>)(ocSymbol -> {
            final OCSymbolKind kind = ocSymbol.getKind();
            if (ocSymbol.getKind() == OCSymbolKind.TYPEDEF) {
                final OCSymbolWithQualifiedName<PsiNameIdentifierOwner> resolvedOwner = ((OCDeclaratorSymbol)ocSymbol).getResolvedOwner();
                if (resolvedOwner != null && resolvedOwner.getKind() != OCSymbolKind.NAMESPACE) {
                    return false;
                }
            }
            return kind.isClassOrTypedef() || kind.isStructLike();
        }), false);
    }
}
