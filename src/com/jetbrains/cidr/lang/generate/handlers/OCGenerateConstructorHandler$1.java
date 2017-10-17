// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCGenerateConstructorHandler$1 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ OCCaretLocation val$location;
    final /* synthetic */ OCType val$resolved;
    
    @Contract("null -> false")
    protected boolean accept(final OCSymbol ocSymbol) {
        if (ocSymbol instanceof OCFunctionSymbol && ((OCFunctionSymbol)ocSymbol).isCppConstructor()) {
            final OCType resolvedType = ocSymbol.getResolvedType();
            if (resolvedType instanceof OCFunctionType && new OCTypeEqualityVisitor(resolvedType, false, new OCResolveContext((PsiElement)this.val$location.getFile())).isFunctionSignatureEqual(this.val$resolved)) {
                return true;
            }
        }
        return false;
    }
}