// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl.symbols;

import com.intellij.psi.util.PsiModificationTracker;

class OCFileGlobalSymbolsCache$4 implements PsiModificationTracker.Listener {
    final /* synthetic */ PsiModificationTracker val$tracker;
    
    public void modificationCountChanged() {
        final long outOfCodeBlockModificationCount = this.val$tracker.getOutOfCodeBlockModificationCount();
        if (OCFileGlobalSymbolsCache.access$000(OCFileGlobalSymbolsCache.this) != outOfCodeBlockModificationCount) {
            OCFileGlobalSymbolsCache.access$002(OCFileGlobalSymbolsCache.this, outOfCodeBlockModificationCount);
            OCFileGlobalSymbolsCache.this.clear();
        }
    }
}