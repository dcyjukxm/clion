// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;

static final class OCTypeUtils$7 extends CommonProcessors.FindFirstProcessor<OCMethodSymbol> {
    final /* synthetic */ PsiElement val$context;
    
    protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
        OCType ocType = ocMethodSymbol.getReturnType().resolve(new OCResolveContext(this.val$context));
        if (ocType instanceof OCPointerType) {
            ocType = ((OCPointerType)ocType).getRefType();
        }
        return ocType instanceof OCObjectType && "NSEnumerator".equals(((OCObjectType)ocType).getClassName());
    }
}