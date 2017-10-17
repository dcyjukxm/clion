// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.util.CommonProcessors;

class OCTypeCompatibilityVisitor$15 extends CommonProcessors.FindFirstProcessor<OCDeclaratorSymbol> {
    final /* synthetic */ OCStructSymbol val$struct;
    final /* synthetic */ Ref val$result;
    
    protected boolean accept(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        final OCType.TypeCheckResult checkCompatible = ocDeclaratorSymbol.getType().resolve((PsiFile)this.val$struct.getContainingOCFile()).checkCompatible(OCTypeCompatibilityVisitor.this.mySourceType, OCTypeCompatibilityVisitor.this.mySource, OCTypeCompatibilityVisitor.this.myContext);
        if (checkCompatible.getState() == OCType.TypeCheckState.OK) {
            this.val$result.set((Object)checkCompatible);
            return true;
        }
        if (!checkCompatible.getState().isError(OCTypeCompatibilityVisitor.this.myContext) && this.val$result.isNull()) {
            this.val$result.set((Object)checkCompatible);
        }
        return false;
    }
}