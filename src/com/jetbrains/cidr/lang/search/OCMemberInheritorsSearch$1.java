// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.util.Computable;

static final class OCMemberInheritorsSearch$1 implements Computable<OCClassSymbol> {
    final /* synthetic */ OCMethod val$method;
    
    public OCClassSymbol compute() {
        return this.val$method.getContainingClass().getSymbol();
    }
}