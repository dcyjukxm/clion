// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.inline;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiReference;
import com.intellij.util.CommonProcessors;

class OCInlineLocalVarHandler$1 extends CommonProcessors.FindFirstProcessor<PsiReference> {
    protected boolean accept(final PsiReference psiReference) {
        return !OCElementUtil.isPartOfMacroSubstitution(psiReference.getElement());
    }
}