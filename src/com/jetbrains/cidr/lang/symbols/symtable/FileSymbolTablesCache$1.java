// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.psi.impl.PsiModificationTrackerImpl;
import com.intellij.openapi.util.SimpleModificationTracker;

class FileSymbolTablesCache$1 extends SimpleModificationTracker {
    final /* synthetic */ PsiModificationTrackerImpl val$psiTracker;
    
    public void incModificationCount() {
        super.incModificationCount();
        this.val$psiTracker.incOutOfCodeBlockModificationCounter();
    }
}