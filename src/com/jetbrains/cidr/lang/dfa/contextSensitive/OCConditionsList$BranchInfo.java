// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa.contextSensitive;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

static class BranchInfo
{
    PsiElement myCondition;
    boolean myBranch;
    boolean myCanBeShortCutted;
    
    BranchInfo(@Nullable final PsiElement myCondition, final boolean myBranch, final boolean myCanBeShortCutted) {
        this.myCondition = myCondition;
        this.myBranch = myBranch;
        this.myCanBeShortCutted = myCanBeShortCutted;
    }
    
    @Nullable
    PsiElement getCondition() {
        return this.myCondition;
    }
    
    boolean isTrueBranch() {
        return this.myBranch;
    }
    
    boolean isCanBeShortCutted() {
        return this.myCanBeShortCutted;
    }
}
