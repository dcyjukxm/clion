// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityAfterResolvingVisitor;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.CommonProcessors;

class OCGenerateGetterSetterContext$2 extends CommonProcessors.FindFirstProcessor<OCFunctionSymbol> {
    final /* synthetic */ OCDeclaratorSymbol val$field;
    
    protected boolean accept(final OCFunctionSymbol ocFunctionSymbol) {
        if (ocFunctionSymbol.getParameterSymbols().size() == 1) {
            OCType ocType;
            for (ocType = ocFunctionSymbol.getParameterSymbols().get(0).getType(); ocType instanceof OCCppReferenceType; ocType = ((OCCppReferenceType)ocType).getRefType()) {}
            if (new OCTypeEqualityAfterResolvingVisitor(this.val$field.getType(), false, false, true, true, true, (PsiFile)ocFunctionSymbol.getContainingOCFile()).equal(ocType)) {
                return true;
            }
        }
        return false;
    }
}