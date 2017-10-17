// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor_OCFunctionType$1 extends OCType.TypeCheckResult {
    final /* synthetic */ OCFunctionType val$type;
    final /* synthetic */ OCFunctionType val$functionType;
    
    @Override
    public String getMessage() {
        return "Incompatible function return types '" + this.val$type.getReturnType().getName(OCTypeCompatibilityVisitor_OCFunctionType.this.myContext) + "' and '" + this.val$functionType.getReturnType().getName(OCTypeCompatibilityVisitor_OCFunctionType.this.myContext) + "'";
    }
}