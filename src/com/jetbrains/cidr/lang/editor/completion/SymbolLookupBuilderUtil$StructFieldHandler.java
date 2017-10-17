// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

private static class StructFieldHandler implements InsertHandler<LookupElement>
{
    private final OCSymbol mySymbol;
    private final PsiElement myContextExpression;
    
    public StructFieldHandler(final OCSymbol mySymbol, final PsiElement myContextExpression) {
        this.mySymbol = mySymbol;
        this.myContextExpression = myContextExpression;
    }
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        FunctionInsertHandler.changeQualifyingTokenIfNeeded(insertionContext, this.myContextExpression, lookupElement);
        OCImportSymbolFix.fixAtCaret(insertionContext.getEditor(), insertionContext.getFile(), this.mySymbol);
    }
}
