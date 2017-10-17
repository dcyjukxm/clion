// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

public static class OCEnumConstInsertHandler implements InsertHandler<LookupElement>
{
    private final OCDeclaratorSymbol mySymbol;
    private final PsiElement myContextExpression;
    
    public OCEnumConstInsertHandler(final OCDeclaratorSymbol mySymbol, final PsiElement myContextExpression) {
        this.mySymbol = mySymbol;
        this.myContextExpression = myContextExpression;
    }
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        if (this.myContextExpression instanceof OCReferenceExpression) {
            final OCQualifiedName shortestPossibleName = OCBindUtil.getShortestPossibleName(this.mySymbol.getResolvedQualifiedName(), ((OCReferenceExpression)this.myContextExpression).getReferenceElement(), this.mySymbol);
            if (shortestPossibleName != null) {
                final Document document = insertionContext.getDocument();
                document.replaceString(insertionContext.getStartOffset(), insertionContext.getTailOffset(), (CharSequence)shortestPossibleName.getCanonicalName(true));
                PsiDocumentManager.getInstance(insertionContext.getProject()).commitDocument(document);
            }
        }
        OCImportSymbolFix.fixAtCaret(insertionContext.getEditor(), insertionContext.getFile(), this.mySymbol);
    }
}
