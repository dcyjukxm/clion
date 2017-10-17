// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.codeInsight.template.Expression;

public class OCTemplateExpression extends Expression
{
    private final TextResult myResult;
    private final LookupElement[] myLookupItems;
    
    public OCTemplateExpression(final String s, final String... array) {
        this.myResult = new TextResult(s);
        this.myLookupItems = ((array.length == 1) ? LookupElement.EMPTY_ARRAY : new LookupElement[array.length]);
        if (array.length > 1) {
            for (int i = 0; i < array.length; ++i) {
                this.myLookupItems[i] = (LookupElement)LookupElementBuilder.create(array[i]);
            }
        }
    }
    
    public Result calculateResult(final ExpressionContext expressionContext) {
        return (Result)this.myResult;
    }
    
    public Result calculateQuickResult(final ExpressionContext expressionContext) {
        return (Result)this.myResult;
    }
    
    public LookupElement[] calculateLookupItems(final ExpressionContext expressionContext) {
        return this.myLookupItems;
    }
}
