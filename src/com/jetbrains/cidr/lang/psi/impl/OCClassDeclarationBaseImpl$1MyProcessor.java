// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.CommonProcessors;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.Processor;

class 1MyProcessor implements Processor<OCMemberSymbol>
{
    PsiElement candidate;
    int bestDistance;
    final /* synthetic */ OCMemberSymbol val$associateMember;
    
    1MyProcessor(final OCMemberSymbol val$associateMember) {
        this.val$associateMember = val$associateMember;
        this.candidate = null;
        this.bestDistance = Integer.MAX_VALUE;
    }
    
    public boolean process(final OCMemberSymbol ocMemberSymbol) {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        ((OCSymbolWithParent<T, OCClassSymbol>)this.val$associateMember).getParent().processMembers(ocMemberSymbol.getName(), this.val$associateMember.getClass(), (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
        if (findFirstProcessor.isFound() && !ocMemberSymbol.isSynthetic()) {
            PsiElement candidate = ocMemberSymbol.locateDefinition();
            final int bestDistance = ((OCMemberSymbol)findFirstProcessor.getFoundValue()).getOffset() - this.val$associateMember.getOffset();
            while (candidate != null && candidate.getParent() != OCClassDeclarationBaseImpl.this) {
                candidate = candidate.getParent();
            }
            if (Math.abs(bestDistance) < Math.abs(this.bestDistance) && candidate != null) {
                this.bestDistance = bestDistance;
                this.candidate = candidate;
            }
        }
        return true;
    }
    
    PsiElement getCandidate() {
        if (this.candidate != null && this.bestDistance <= 0) {
            return this.candidate.getNextSibling();
        }
        return this.candidate;
    }
}
