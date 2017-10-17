// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.util.Computable;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor$12 extends OCType.TypeCheckResult {
    final /* synthetic */ Computable val$finalMessage;
    
    @Override
    public String getMessage() {
        return (String)this.val$finalMessage.compute();
    }
}