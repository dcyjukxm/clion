// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCStatementExpression;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCCodeInsightUtil$2 extends OCRecursiveVisitor {
    final /* synthetic */ Ref val$result;
    
    @Override
    public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
        if (ocPrefixExpression.getOperationSign() == OCTokenTypes.PLUSPLUS || ocPrefixExpression.getOperationSign() == OCTokenTypes.MINUSMINUS) {
            this.val$result.set((Object)true);
        }
        else {
            super.visitPrefixExpression(ocPrefixExpression);
        }
    }
    
    @Override
    public void visitPostfixExpression(final OCPostfixExpression ocPostfixExpression) {
        if (ocPostfixExpression.getOperationSign() == OCTokenTypes.PLUSPLUS || ocPostfixExpression.getOperationSign() == OCTokenTypes.MINUSMINUS) {
            this.val$result.set((Object)true);
        }
        else {
            super.visitPostfixExpression(ocPostfixExpression);
        }
    }
    
    @Override
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        this.val$result.set((Object)true);
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        this.val$result.set((Object)true);
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        this.val$result.set((Object)true);
    }
    
    @Override
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
        this.val$result.set((Object)true);
    }
    
    @Override
    public void visitLambdaExpression(final OCLambdaExpression ocLambdaExpression) {
        this.val$result.set((Object)true);
    }
    
    @Override
    public void visitStatementExpression(final OCStatementExpression ocStatementExpression) {
        this.val$result.set((Object)true);
    }
    
    @Override
    public void visitCppNewExpression(final OCCppNewExpression ocCppNewExpression) {
        this.val$result.set((Object)true);
    }
}