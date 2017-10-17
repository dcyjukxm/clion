// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.codeInsight.template.PsiElementResult;

public class OCElementResult extends PsiElementResult
{
    public OCElementResult(final OCElement ocElement) {
        super((PsiElement)ocElement);
    }
    
    @Override
    public String toString() {
        if (this.getElement() instanceof PsiNamedElement) {
            return ((PsiNamedElement)this.getElement()).getName();
        }
        return super.toString();
    }
}
