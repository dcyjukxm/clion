// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCCodeInsightUtil$1 extends OCRecursiveVisitor {
    final /* synthetic */ OCClassSymbol[] val$result;
    final /* synthetic */ String val$fileName;
    
    @Override
    public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
        if (this.val$result[0] == null || (Comparing.equal(ocClassDeclaration.getName(), this.val$fileName) && (!Comparing.equal(this.val$result[0].getName(), this.val$fileName) || ocClassDeclaration.getCategory() == null))) {
            this.val$result[0] = ocClassDeclaration.getSymbol();
        }
    }
}