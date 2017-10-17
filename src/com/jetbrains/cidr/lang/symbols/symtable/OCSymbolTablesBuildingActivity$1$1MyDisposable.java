// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.Disposable;

class 1MyDisposable implements Disposable
{
    boolean shouldCancel;
    final /* synthetic */ MyProgressIndicator val$indicator;
    
    1MyDisposable(final MyProgressIndicator val$indicator) {
        this.val$indicator = val$indicator;
        this.shouldCancel = true;
    }
    
    public void dispose() {
        if (this.shouldCancel) {
            this.val$indicator.cancel();
        }
    }
}
