// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;

class OCUnusedClassInspection$1 extends UnusedVisitor {
    @Override
    public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
        final OCSymbol symbol = this.getSymbol(ocClassDeclaration);
        if (!this.myOnTheFly && symbol != null && symbol.isPredeclaration()) {
            return;
        }
        if (symbol instanceof OCProtocolSymbol) {
            this.checkSymbolUsed((PsiElement)ocClassDeclaration, symbol);
        }
        else if (symbol instanceof OCClassSymbol) {
            final OCInterfaceSymbol mainInterface = ((OCInterfaceSymbol)symbol).getMainInterface();
            if (mainInterface == null) {
                this.checkSymbolUsed((PsiElement)ocClassDeclaration, symbol);
            }
            else if (this.myOnTheFly || mainInterface == symbol) {
                this.checkSymbolUsed(((OCSymbol<PsiElement>)mainInterface).locateDefinition(), (PsiElement)ocClassDeclaration, mainInterface, false);
            }
        }
    }
}