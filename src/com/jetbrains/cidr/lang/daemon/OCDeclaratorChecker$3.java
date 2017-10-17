// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

class OCDeclaratorChecker$3 extends CommonProcessors.FindFirstProcessor<OCSymbol> {
    final /* synthetic */ OCReferenceElement val$referenceElement;
    
    public boolean process(final OCSymbol ocSymbol) {
        return (!OCResolveUtil.isEarlierInCode(ocSymbol, (PsiElement)this.val$referenceElement) && !OCResolveUtil.isInSameStructInCode(ocSymbol, (PsiElement)this.val$referenceElement)) || super.process((Object)ocSymbol);
    }
}