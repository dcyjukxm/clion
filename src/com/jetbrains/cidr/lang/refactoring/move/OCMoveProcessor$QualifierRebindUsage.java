// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;

private static class QualifierRebindUsage extends MoveUsage
{
    private boolean myMoved;
    
    public QualifierRebindUsage(@NotNull final PsiReference psiReference, final OCSymbol ocSymbol, final boolean myMoved) {
        if (psiReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifier", "com/jetbrains/cidr/lang/refactoring/move/OCMoveProcessor$QualifierRebindUsage", "<init>"));
        }
        super(psiReference, ocSymbol);
        this.myMoved = myMoved;
    }
    
    public boolean isMoved() {
        return this.myMoved;
    }
}
