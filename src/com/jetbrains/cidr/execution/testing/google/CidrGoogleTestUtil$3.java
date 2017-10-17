// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;

static final class CidrGoogleTestUtil$3 extends CommonProcessors.CollectProcessor<OCSymbol> {
    final /* synthetic */ PsiFile val$file;
    
    protected boolean accept(final OCSymbol ocSymbol) {
        return ocSymbol instanceof OCStructSymbol && "::testing::TestCase".equals(ocSymbol.getType().getCanonicalName((PsiElement)this.val$file));
    }
}