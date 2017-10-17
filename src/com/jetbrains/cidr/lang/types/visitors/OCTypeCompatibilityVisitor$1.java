// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCType;

static final class OCTypeCompatibilityVisitor$1 extends OCType.TypeCheckResult {
    final /* synthetic */ OCType.TypeCheckResult val$curResult;
    
    @Override
    public String getMessage() {
        return this.val$curResult.getMessage();
    }
}