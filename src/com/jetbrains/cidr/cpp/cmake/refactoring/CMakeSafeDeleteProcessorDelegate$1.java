// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.refactoring;

import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.safeDelete.usageInfo.SafeDeleteReferenceSimpleDeleteUsageInfo;

class CMakeSafeDeleteProcessorDelegate$1 extends SafeDeleteReferenceSimpleDeleteUsageInfo {
    final /* synthetic */ PsiElement val$usageElement;
    
    @Override
    public void deleteElement() throws IncorrectOperationException {
        CMakeSafeDeleteProcessorDelegate.access$000(this.val$usageElement);
    }
}