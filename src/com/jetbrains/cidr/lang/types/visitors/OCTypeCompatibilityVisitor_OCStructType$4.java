// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor_OCStructType$4 extends OCType.TypeCheckResult {
    final /* synthetic */ OCStructType val$type;
    
    @Override
    public String getMessage() {
        return "Incompatible enum types '" + this.val$type.getBestNameInContext(OCTypeCompatibilityVisitor_OCStructType.this.myContext) + "' and '" + OCTypeCompatibilityVisitor_OCStructType.this.getSourceTypeName() + "'";
    }
}