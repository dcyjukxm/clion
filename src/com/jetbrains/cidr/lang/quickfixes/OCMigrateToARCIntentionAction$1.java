// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCMigrateToARCIntentionAction$1 extends OCRecursiveVisitor {
    final /* synthetic */ List val$exprs;
    final /* synthetic */ List val$poolDeclarations;
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        super.visitSendMessageExpression(ocSendMessageExpression);
        this.val$exprs.add(ocSendMessageExpression);
    }
    
    @Override
    public void visitDeclarationStatement(final OCDeclarationStatement ocDeclarationStatement) {
        super.visitDeclarationStatement(ocDeclarationStatement);
        if (ocDeclarationStatement.getDeclaration().getType().getSimpleName((PsiElement)ocDeclarationStatement).equals("NSAutoreleasePool")) {
            this.val$poolDeclarations.add(ocDeclarationStatement);
        }
    }
}