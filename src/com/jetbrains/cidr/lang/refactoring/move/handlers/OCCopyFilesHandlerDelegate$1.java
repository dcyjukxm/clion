// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import java.util.Map;

class OCCopyFilesHandlerDelegate$1 extends MyProcessor {
    final /* synthetic */ Map val$filesMap;
    
    @Override
    protected void cloneClass(final OCFile ocFile, final OCFile ocFile2) {
        super.cloneClass(ocFile, ocFile2);
        this.val$filesMap.put(ocFile, ocFile2);
    }
}