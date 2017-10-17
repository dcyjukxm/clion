// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCNumericType;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor$5 extends OCType.TypeCheckResult {
    final /* synthetic */ OCNumericType val$type;
    
    @Override
    public String getMessage() {
        return "Values of type '" + OCTypeCompatibilityVisitor.this.getSourceTypeName() + "' may not fit into the receiver type '" + this.val$type.getName(OCTypeCompatibilityVisitor.this.myContext) + "'";
    }
}