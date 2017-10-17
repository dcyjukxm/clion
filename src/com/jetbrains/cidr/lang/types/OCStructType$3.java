// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

static final class OCStructType$3 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ OCCppReferenceType val$constRefToOwnType;
    final /* synthetic */ OCFile val$file;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        if (!(ocSymbol instanceof OCFunctionSymbol)) {
            return false;
        }
        final List<OCType> parameterTypes = ((OCFunctionSymbol)ocSymbol).getType().getParameterTypes();
        return parameterTypes.size() == 1 && parameterTypes.get(0).equalsAfterResolving(this.val$constRefToOwnType, (PsiElement)this.val$file);
    }
}