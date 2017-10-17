// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

class OCUnusedGlobalDeclarationInspection$1 extends UnusedVisitor {
    @Override
    public void visitDeclarator(final OCDeclarator ocDeclarator) {
        final OCSymbol symbol = this.getSymbol(ocDeclarator);
        if (symbol != null && symbol.isGlobal()) {
            if (!this.myOnTheFly && (symbol instanceof OCDeclaratorSymbol || symbol instanceof OCFunctionSymbol) && !symbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<OCElement>>)new CommonProcessors.FindFirstProcessor<OCSymbol>() {
                protected boolean accept(final OCSymbol ocSymbol) {
                    return (symbol instanceof OCFunctionSymbol && symbol.isDefinition() && ocSymbol.isPredeclaration()) || (symbol instanceof OCDeclaratorSymbol && symbol.isPredeclaration() && ocSymbol.isDefinition()) || (ocSymbol instanceof OCDeclaratorSymbol && ((OCDeclaratorSymbol)ocSymbol).isExtern());
                }
            })) {
                return;
            }
            if (symbol instanceof OCFunctionSymbol && OCUnusedGlobalDeclarationInspection.access$000((OCFunctionSymbol)symbol)) {
                return;
            }
            if (ocDeclarator.getParent() instanceof OCParameterDeclaration && ocDeclarator.getParent().getParent() instanceof OCTemplateParameterList && OCUnusedTemplateParameterInspection.isTraitTemplateParameter((OCTemplateParameterList)ocDeclarator.getParent().getParent())) {
                return;
            }
            this.checkSymbolUsed((PsiElement)ocDeclarator, symbol, this.myHasWrites);
        }
    }
}