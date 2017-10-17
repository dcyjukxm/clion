// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor$18 extends OCType.TypeCheckResult {
    final /* synthetic */ OCProtocolSymbol val$protocol;
    
    @Override
    public String getMessage() {
        return "Interface '" + OCTypeCompatibilityVisitor.this.getSourceTypeName() + "' doesn't implement " + this.val$protocol.getNameWithKindLowercase();
    }
}