// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCDFAInspection$3 extends OCRecursiveVisitor {
    final /* synthetic */ Ref val$elementRef;
    
    @Override
    public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
        if (this.val$elementRef.isNull()) {
            this.val$elementRef.set((Object)ocClassDeclaration.getNameIdentifier());
        }
    }
    
    @Override
    public void visitStruct(final OCStruct ocStruct) {
        if (this.val$elementRef.isNull()) {
            this.val$elementRef.set((Object)ocStruct.getNameIdentifier());
        }
    }
    
    @Override
    public void visitFunctionDefinition(final OCFunctionDefinition ocFunctionDefinition) {
        if (this.val$elementRef.isNull()) {
            this.val$elementRef.set((Object)ocFunctionDefinition.getNameIdentifier());
        }
    }
}