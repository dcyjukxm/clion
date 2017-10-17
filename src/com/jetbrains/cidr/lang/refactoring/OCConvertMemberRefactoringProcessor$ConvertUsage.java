// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;
import com.intellij.usageView.UsageInfo;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;

private static class ConvertUsage<M extends OCMemberSymbol> extends UsageInfo
{
    private M mySymbol;
    
    public ConvertUsage(@NotNull final PsiReference psiReference, final M mySymbol) {
        if (psiReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/OCConvertMemberRefactoringProcessor$ConvertUsage", "<init>"));
        }
        super(psiReference);
        this.mySymbol = mySymbol;
    }
    
    public M getSymbol() {
        return this.mySymbol;
    }
}
