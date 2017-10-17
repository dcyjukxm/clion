// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import java.util.List;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

static final class OCCreateMissingSwitchCasesIntentionAction$1 extends OCRecursiveVisitor {
    final /* synthetic */ Ref val$wasUnresolved;
    final /* synthetic */ Ref val$wasDefault;
    final /* synthetic */ List val$ranges;
    final /* synthetic */ List val$caseStmts;
    
    @Override
    public void visitCaseStatement(final OCCaseStatement ocCaseStatement) {
        super.visitCaseStatement(ocCaseStatement);
        Pair pair = null;
        if (ocCaseStatement.getExpression() != null) {
            final Number evaluate = OCExpressionEvaluator.evaluate(ocCaseStatement.getExpression());
            if (evaluate != null) {
                pair = Pair.create((Object)evaluate.intValue(), (Object)evaluate.intValue());
            }
            else {
                this.val$wasUnresolved.set((Object)true);
            }
        }
        else if (ocCaseStatement.getRangeFirst() != null && ocCaseStatement.getRangeSecond() != null) {
            final Number evaluate2 = OCExpressionEvaluator.evaluate(ocCaseStatement.getRangeFirst());
            final Number evaluate3 = OCExpressionEvaluator.evaluate(ocCaseStatement.getRangeSecond());
            if (evaluate2 != null && evaluate3 != null) {
                pair = Pair.create((Object)evaluate2.intValue(), (Object)evaluate3.intValue());
            }
            else {
                this.val$wasUnresolved.set((Object)true);
            }
        }
        else if (ocCaseStatement.isDefault()) {
            this.val$wasDefault.set((Object)true);
        }
        if (pair != null) {
            this.val$ranges.add(pair);
            this.val$caseStmts.add(ocCaseStatement);
        }
    }
    
    @Override
    public void visitSwitchStatement(final OCSwitchStatement ocSwitchStatement) {
    }
}