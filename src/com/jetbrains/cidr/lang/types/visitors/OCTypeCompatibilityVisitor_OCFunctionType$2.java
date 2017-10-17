// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;

class OCTypeCompatibilityVisitor_OCFunctionType$2 extends OCType.TypeCheckResult {
    final /* synthetic */ OCType val$selfArgumentType;
    final /* synthetic */ OCType val$hisArgumentType;
    
    @Override
    public String getMessage() {
        return "Incompatible function parameter types '" + this.val$selfArgumentType.getName(OCTypeCompatibilityVisitor_OCFunctionType.this.myContext) + "' and '" + this.val$hisArgumentType.getName(OCTypeCompatibilityVisitor_OCFunctionType.this.myContext) + "'";
    }
}