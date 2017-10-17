// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.execution.testing.CidrTestScope;

static final class CidrCatchTestCommandLineState$1 extends CidrTestScope {
    final /* synthetic */ String val$arg;
    
    @Override
    public String toString() {
        return StringUtil.notNullize(this.val$arg);
    }
}