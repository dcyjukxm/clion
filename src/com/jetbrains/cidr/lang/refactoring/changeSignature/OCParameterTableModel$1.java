// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.intellij.psi.PsiCodeFragment;
import com.intellij.refactoring.changeSignature.ParameterTableModelItemBase;

class OCParameterTableModel$1 extends ParameterTableModelItemBase<OCParameterInfo> {
    final /* synthetic */ boolean val$isEllipsisType;
    
    @Override
    public boolean isEllipsisType() {
        return this.val$isEllipsisType;
    }
}