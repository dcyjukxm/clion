// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor_OCPointerType$2 extends OCType.TypeCheckResult {
    final /* synthetic */ OCPointerType val$type;
    
    @Override
    public String getMessage() {
        return "Incompatible pointer types '" + this.val$type.getName(OCTypeCompatibilityVisitor_OCPointerType.this.myResolveContext) + "' and '" + OCTypeCompatibilityVisitor_OCPointerType.this.getSourceTypeName() + "'";
    }
}