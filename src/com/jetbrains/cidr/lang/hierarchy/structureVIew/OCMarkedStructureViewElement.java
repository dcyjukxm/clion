// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.ide.structureView.StructureViewTreeElement;

public interface OCMarkedStructureViewElement extends StructureViewTreeElement
{
    @Nullable
    PsiElement getParentMarkElement();
    
    @Nullable
    default OCMark getParentMark() {
        final PsiElement parentMarkElement = this.getParentMarkElement();
        return (parentMarkElement != null) ? OCMark.createFromElement(parentMarkElement) : null;
    }
    
    default int getParentMarkTextOffset() {
        final PsiElement parentMarkElement = this.getParentMarkElement();
        return (parentMarkElement != null) ? parentMarkElement.getTextOffset() : -1;
    }
}
