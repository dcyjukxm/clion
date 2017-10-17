// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.intellij.psi.PsiElement;

static final class OCGotoSuperHandler$1 extends OCGotoActionSync {
    final /* synthetic */ PsiElement val$element;
    
    @Override
    protected List<? extends OCSymbol> evaluateTargets() {
        final List access$000 = OCGotoSuperHandler.access$000(this.val$element);
        int n = true ? 1 : 0;
        for (final OCSymbol ocSymbol : access$000) {
            boolean b;
            if (ocSymbol instanceof OCProtocolSymbol) {
                b = true;
            }
            else if (ocSymbol instanceof OCMethodSymbol) {
                b = (((OCMethodSymbol)ocSymbol).getParent() instanceof OCProtocolSymbol);
            }
            else {
                b = (ocSymbol instanceof OCFunctionSymbol && ocSymbol.isPredeclaration());
            }
            n &= (b ? 1 : 0);
        }
        this.myIcon = ((n != 0) ? AllIcons.Gutter.ImplementingMethod : AllIcons.Gutter.OverridingMethod);
        this.myName = "Go to ";
        final OCSymbol ocSymbol2 = access$000.isEmpty() ? null : access$000.get(0);
        if (ocSymbol2 instanceof OCMemberSymbol) {
            this.myName = this.myName + "super " + ocSymbol2.getKind().getNameLowercase() + " in " + ((OCMemberSymbol)ocSymbol2).getParent().getNameWithKindLowercase();
        }
        else if (ocSymbol2 instanceof OCFunctionSymbol) {
            final OCSymbolWithQualifiedName<OCElement> parent = ((OCFunctionSymbol)ocSymbol2).getParent();
            if (parent != null) {
                this.myName = this.myName + "base function in " + parent.getNameWithKindLowercase();
            }
        }
        else {
            this.myName += "super type";
        }
        return (List<? extends OCSymbol>)access$000;
    }
}