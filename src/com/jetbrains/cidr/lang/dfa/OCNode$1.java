// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.dfa;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementsRange;

class OCNode$1 extends OCElementsRange {
    @Override
    public TextRange getTextRange() {
        return new TextRange(this.getStartOffset(), this.getEndOffset());
    }
}