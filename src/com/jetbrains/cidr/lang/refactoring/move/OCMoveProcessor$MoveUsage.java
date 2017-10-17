// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.usageView.UsageInfo;

private static class MoveUsage extends UsageInfo
{
    private OCSymbol myFormerSymbol;
    
    public MoveUsage(@NotNull final PsiReference psiReference, final OCSymbol myFormerSymbol) {
        if (psiReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$MoveUsage", "<init>"));
        }
        super(psiReference);
        this.myFormerSymbol = myFormerSymbol;
    }
    
    public OCSymbol getFormerSymbol() {
        return this.myFormerSymbol;
    }
}
