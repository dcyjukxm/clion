// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor_OCStructType$6 extends OCType.TypeCheckResult {
    final /* synthetic */ OCStructSymbol val$symbol;
    final /* synthetic */ OCStructSymbol val$struct;
    final /* synthetic */ OCStructType val$type;
    
    @Override
    public String getMessage() {
        return this.val$symbol.getKindUppercase() + " '" + OCTypeCompatibilityVisitor_OCStructType.this.getSourceTypeName() + "' is not compatible with " + this.val$struct.getKindLowercase() + " '" + this.val$type.getBestNameInContext(OCTypeCompatibilityVisitor_OCStructType.this.myContext) + "'";
    }
}