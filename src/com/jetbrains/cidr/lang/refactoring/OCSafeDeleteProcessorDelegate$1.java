// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.safeDelete.usageInfo.SafeDeleteReferenceSimpleDeleteUsageInfo;

class OCSafeDeleteProcessorDelegate$1 extends SafeDeleteReferenceSimpleDeleteUsageInfo {
    final /* synthetic */ PsiElement val$usageElement;
    
    @Override
    public void deleteElement() throws IncorrectOperationException {
        OCChangeUtil.safeDeleteReference(this.val$usageElement);
    }
}