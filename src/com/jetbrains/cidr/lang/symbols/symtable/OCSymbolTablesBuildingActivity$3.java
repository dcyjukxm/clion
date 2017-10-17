// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.concurrency.SensitiveProgressWrapper;

static final class OCSymbolTablesBuildingActivity$3 extends SensitiveProgressWrapper {
    @Override
    protected boolean isReuseable() {
        return true;
    }
}