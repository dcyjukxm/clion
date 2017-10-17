// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.psi.OCStringsFile;
import com.intellij.util.CommonProcessors;

class OCProvideStringLocalizationsIntentionAction$1 extends CommonProcessors.CollectProcessor<OCStringsFile> {
    final /* synthetic */ String val$key;
    
    protected boolean accept(final OCStringsFile ocStringsFile) {
        return ocStringsFile.findStringPair(this.val$key) == null;
    }
}