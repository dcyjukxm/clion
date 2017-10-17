// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor_OCArrayType$2 extends OCType.TypeCheckResult {
    final /* synthetic */ OCPointerType val$type;
    
    @Override
    public String getMessage() {
        return "Deprecated conversion from string literal to '" + this.val$type.getName(OCTypeCompatibilityVisitor_OCArrayType.this.myResolveContext) + "'";
    }
}