// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassPredeclaration;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCFileImpl$4 extends OCRecursiveVisitor {
    final /* synthetic */ Ref val$result;
    final /* synthetic */ String val$name;
    
    @Override
    public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
        if (!(ocClassDeclaration instanceof OCClassPredeclaration) && this.val$result.isNull() && (this.val$name == null || this.val$name.equals(ocClassDeclaration.getCanonicalName()))) {
            this.val$result.set((Object)ocClassDeclaration);
        }
        super.visitClassDeclaration(ocClassDeclaration);
    }
    
    @Override
    public void visitStruct(final OCStruct ocStruct) {
        if (this.val$result.isNull() && (this.val$name == null || this.val$name.equals(ocStruct.getName()))) {
            this.val$result.set((Object)ocStruct);
        }
        super.visitStruct(ocStruct);
    }
}