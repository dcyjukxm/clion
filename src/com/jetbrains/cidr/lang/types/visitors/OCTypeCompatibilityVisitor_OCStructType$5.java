// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor_OCStructType$5 extends OCType.TypeCheckResult {
    final /* synthetic */ OCStructSymbol val$struct;
    final /* synthetic */ OCStructType val$type;
    final /* synthetic */ Ref val$visibilityRef;
    final /* synthetic */ OCStructSymbol val$symbol;
    
    @Override
    public String getMessage() {
        return this.val$struct.getKindUppercase() + " '" + this.val$type.getBestNameInContext(OCTypeCompatibilityVisitor_OCStructType.this.myContext) + "' is a " + this.val$visibilityRef.toString() + " base class of " + this.val$symbol.getKindLowercase() + " '" + OCTypeCompatibilityVisitor_OCStructType.this.getSourceTypeName() + "'";
    }
}