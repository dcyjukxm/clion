// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor_OCBlockPointerType$1 extends OCType.TypeCheckResult {
    final /* synthetic */ OCFunctionType val$type;
    
    @Override
    public String getMessage() {
        return "Incompatible pointer types '" + this.val$type.getName(OCTypeCompatibilityVisitor_OCBlockPointerType.this.myContext) + "' and '" + OCTypeCompatibilityVisitor_OCBlockPointerType.this.getSourceTypeName() + "'";
    }
}