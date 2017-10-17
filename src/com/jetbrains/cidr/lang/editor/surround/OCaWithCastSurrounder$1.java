// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.codeInsight.template.Expression;

static final class OCaWithCastSurrounder$1 extends Expression {
    final /* synthetic */ TextResult val$result;
    
    public Result calculateResult(final ExpressionContext expressionContext) {
        return (Result)this.val$result;
    }
    
    public Result calculateQuickResult(final ExpressionContext expressionContext) {
        return this.calculateResult(expressionContext);
    }
    
    public LookupElement[] calculateLookupItems(final ExpressionContext expressionContext) {
        return null;
    }
}