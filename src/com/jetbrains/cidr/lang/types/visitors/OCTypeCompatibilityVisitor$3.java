// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor$3 extends OCType.TypeCheckResult {
    final /* synthetic */ OCCppReferenceType val$type;
    
    @Override
    public String getMessage() {
        return "Incompatible types '" + this.val$type.getName(OCTypeCompatibilityVisitor.this.myResolveContext) + "' and '" + OCTypeCompatibilityVisitor.this.getSourceTypeName() + "'";
    }
}