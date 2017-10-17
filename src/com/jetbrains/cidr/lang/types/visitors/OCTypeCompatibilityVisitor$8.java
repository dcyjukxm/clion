// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor$8 extends OCType.TypeCheckResult {
    @Override
    public String getMessage() {
        return "Taking integer from enum class '" + OCTypeCompatibilityVisitor.this.getSourceTypeName() + "' without a cast";
    }
}