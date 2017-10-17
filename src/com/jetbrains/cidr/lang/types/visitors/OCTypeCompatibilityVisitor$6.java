// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCNumericType;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor$6 extends OCType.TypeCheckResult {
    final /* synthetic */ OCNumericType val$type;
    
    @Override
    public String getMessage() {
        return "Using '" + this.val$type.getName(OCTypeCompatibilityVisitor.this.myContext) + "' for complex values of type '" + OCTypeCompatibilityVisitor.this.getSourceTypeName() + "'";
    }
}