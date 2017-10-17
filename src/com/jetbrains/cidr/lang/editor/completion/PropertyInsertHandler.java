// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.intellij.codeInsight.completion.InsertionContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

public class PropertyInsertHandler implements InsertHandler<LookupElement>
{
    private final PsiElement myContextExpression;
    private final OCPropertySymbol myPropertySymbol;
    private final OCSymbolGroupContext mySymbolContext;
    
    public PropertyInsertHandler(final PsiElement myContextExpression, final OCPropertySymbol myPropertySymbol, final OCSymbolGroupContext mySymbolContext) {
        this.myContextExpression = myContextExpression;
        this.myPropertySymbol = myPropertySymbol;
        this.mySymbolContext = mySymbolContext;
    }
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        if (lookupElement.getObject() instanceof OCPropertySymbol && !(this.myContextExpression instanceof OCQualifiedExpression) && (this.myContextExpression == null || !(this.myContextExpression.getParent() instanceof OCSynthesizeProperty))) {
            insertionContext.getDocument().insertString(insertionContext.getStartOffset(), (CharSequence)"self.");
            insertionContext.setTailOffset(insertionContext.getTailOffset() + 5);
        }
        if (this.myContextExpression instanceof OCQualifiedExpression) {
            PsiDocumentManager.getInstance(insertionContext.getProject()).commitDocument(insertionContext.getDocument());
            OCImportSymbolFix.fixAtCaret(insertionContext.getEditor(), insertionContext.getFile(), this.myPropertySymbol);
        }
    }
}
