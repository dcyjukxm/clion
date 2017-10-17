// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor$4 extends OCType.TypeCheckResult {
    final /* synthetic */ OCType val$type;
    
    @Override
    public String getMessage() {
        return "Types '" + this.val$type.getName(OCTypeCompatibilityVisitor.this.myResolveContext) + "' and '" + OCTypeCompatibilityVisitor.this.getSourceTypeName() + "' are not compatible";
    }
}