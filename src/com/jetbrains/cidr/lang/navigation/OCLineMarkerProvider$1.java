// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import java.util.Collections;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import javax.swing.Icon;
import com.intellij.psi.PsiElement;

static final class OCLineMarkerProvider$1 extends OCGotoActionSync {
    final /* synthetic */ PsiElement val$element;
    
    @Override
    protected List<OCSymbol> evaluateTargets() {
        final OCStructSymbol symbol = ((OCSymbolDeclarator)this.val$element).getSymbol();
        final OCSymbol relatedSymbol = OCLineMarkerProvider.getRelatedSymbol(symbol);
        final Object o = (symbol instanceof OCStructSymbol) ? OCSearchUtil.getRelatedSymbols(symbol, true) : Collections.emptyList();
        this.myName = "Go to ";
        if (!((List)o).isEmpty()) {
            this.myName += "definition(s)";
        }
        else if (symbol != null && relatedSymbol != null) {
            if (relatedSymbol.getKind().isClass()) {
                this.myName = this.myName + relatedSymbol.getKind().getNameLowercase() + " of '" + relatedSymbol.getName() + "'";
            }
            else if (relatedSymbol instanceof OCStructSymbol && relatedSymbol.isPredeclaration()) {
                this.myName = this.myName + "predefinition of " + relatedSymbol.getNameWithKindLowercase();
            }
            else if (relatedSymbol.getKind() == symbol.getKind()) {
                this.myName = this.myName + (relatedSymbol.isDefinition() ? "definition" : "declaration") + " of " + relatedSymbol.getNameWithKindLowercase();
            }
            else {
                this.myName = this.myName + " related " + relatedSymbol.getKind();
            }
        }
        return (List<OCSymbol>)(((List)o).isEmpty() ? Collections.singletonList(relatedSymbol) : o);
    }
}