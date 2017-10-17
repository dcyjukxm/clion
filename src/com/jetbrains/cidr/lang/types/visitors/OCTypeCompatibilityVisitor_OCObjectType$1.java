// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor_OCObjectType$1 extends OCType.TypeCheckResult {
    final /* synthetic */ OCObjectType val$type;
    
    @Override
    public String getMessage() {
        return "Interface '" + OCTypeCompatibilityVisitor_OCObjectType.this.getSourceTypeName() + "' is not a successor of '" + this.val$type.getName(OCTypeCompatibilityVisitor_OCObjectType.this.myContext) + "'";
    }
}