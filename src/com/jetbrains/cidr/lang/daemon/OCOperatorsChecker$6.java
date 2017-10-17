// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCRemoveElementsIntentionAction;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import java.util.List;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCOperatorsChecker$6 extends OCRecursiveVisitor {
    final /* synthetic */ Ref val$wasDefault;
    final /* synthetic */ List val$missingCases;
    final /* synthetic */ boolean val$isEnumClass;
    
    @Override
    public void visitCaseStatement(final OCCaseStatement ocCaseStatement) {
        super.visitCaseStatement(ocCaseStatement);
        final OCStatement statement = ocCaseStatement.getStatement();
        if (statement == null) {
            OCOperatorsChecker.this.addErrorAnnotation((PsiElement)ocCaseStatement, OCInspections.ConstructionIsNotAllowed.class, "CIDR", "Statement is expected");
        }
        else if (statement instanceof OCDeclarationStatement && OCCodeInsightUtil.isInPlainOldC((PsiElement)ocCaseStatement)) {
            OCOperatorsChecker.this.registerQuickFix(OCOperatorsChecker.this.addErrorAnnotation((PsiElement)statement, OCInspections.ConstructionIsNotAllowed.class, "CIDR", "Declaration is not allowed here"), (IntentionAction)new OCRemoveElementsIntentionAction((PsiElement)statement, "Remove statement"));
        }
        if (ocCaseStatement.isDefault()) {
            if (this.val$wasDefault.get()) {
                OCOperatorsChecker.this.addErrorAnnotation((PsiElement)ocCaseStatement, OCInspections.DuplicateSwitchCase.class, "err_multiple_default_labels_defined", "Duplicate default label");
            }
            else {
                if (this.val$missingCases.isEmpty() && this.val$isEnumClass) {
                    OCOperatorsChecker.this.registerQuickFix(OCOperatorsChecker.this.addWarningAnnotation((PsiElement)ocCaseStatement, OCInspections.DuplicateSwitchCase.class, "CIDR", "Default case is useless: all cases are covered"), (IntentionAction)new OCRemoveElementsIntentionAction((PsiElement)ocCaseStatement, "Remove statement"));
                }
                this.val$wasDefault.set((Object)true);
            }
        }
    }
    
    @Override
    public void visitSwitchStatement(final OCSwitchStatement ocSwitchStatement) {
    }
}