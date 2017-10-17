// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.util.text.StringUtil;

static final class CidrPathConsoleFilter$1 extends StringUtil.BombedCharSequence {
    final /* synthetic */ long val$expirationTime;
    
    protected void checkCanceled() {
        try {
            if (System.currentTimeMillis() > this.val$expirationTime) {
                throw new ProcessCanceledException();
            }
        }
        catch (ProcessCanceledException ex) {
            throw a(ex);
        }
    }
    
    private static ProcessCanceledException a(final ProcessCanceledException ex) {
        return ex;
    }
}