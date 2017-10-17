// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;

class OCGenerateSharedInstanceHandler$1 extends OCObjCActionContext<OCMethodSymbol> {
    @Override
    protected Class<OCMethodSymbol> getMemberSymbolClass() {
        return OCMethodSymbol.class;
    }
}