// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor$2 extends OCType.TypeCheckResult {
    @Override
    public String getMessage() {
        return "Binding '" + OCTypeCompatibilityVisitor.this.getSourceTypeName() + "' to non-const reference";
    }
}