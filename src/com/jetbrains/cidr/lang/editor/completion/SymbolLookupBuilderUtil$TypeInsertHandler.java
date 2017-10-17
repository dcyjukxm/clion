// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

private static class TypeInsertHandler implements InsertHandler<LookupElement>
{
    private final OCSymbol mySymbol;
    private final PsiElement myContext;
    
    private TypeInsertHandler(final OCSymbol mySymbol, final PsiElement myContext) {
        this.mySymbol = mySymbol;
        this.myContext = myContext;
    }
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        final Document document = insertionContext.getDocument();
        final Editor editor = insertionContext.getEditor();
        final CharSequence charsSequence = document.getCharsSequence();
        final int tailOffset = insertionContext.getTailOffset();
        final CodeStyleSettings settings = CodeStyleSettingsManager.getSettings(insertionContext.getProject());
        if (!(this.myContext.getParent().getParent() instanceof OCCppNewExpression)) {
            if (settings != null && ((OCCodeStyleSettings)settings.getCustomSettings((Class)OCCodeStyleSettings.class)).SPACE_BEFORE_POINTER_IN_DECLARATION) {
                boolean b = true;
                if ((this.mySymbol.getKind() != OCSymbolKind.ENUM && this.mySymbol instanceof OCNamespaceSymbol) || this.mySymbol instanceof OCTypeParameterSymbol) {
                    b = false;
                }
                if (this.mySymbol.getKind() == OCSymbolKind.TYPEDEF && this.mySymbol.getType().resolve((PsiFile)this.mySymbol.getContainingOCFile()) instanceof OCStructType) {
                    b = false;
                }
                if (this.mySymbol.getKind() == OCSymbolKind.SYMBOL_USING_SYMBOL) {
                    b = false;
                }
                if (b && tailOffset < charsSequence.length()) {
                    final char char1 = charsSequence.charAt(tailOffset);
                    if (char1 == ')' || char1 == ']') {
                        return;
                    }
                    if (char1 != ' ' && char1 != '\t' && insertionContext.getCompletionChar() != ' ') {
                        document.insertString(tailOffset, (CharSequence)" ");
                    }
                    editor.getCaretModel().moveToOffset(tailOffset + 1);
                }
            }
        }
        final PsiElement parent = this.myContext.getParent();
        if (this.mySymbol.getKind().isStructLike() && this.myContext != null && OCCodeInsightUtil.isInPlainOldC(this.myContext) && parent != null && !(parent instanceof OCStructLike)) {
            document.insertString(insertionContext.getStartOffset(), (CharSequence)(this.mySymbol.getKind().getNameLowercase() + " "));
        }
        PsiDocumentManager.getInstance(insertionContext.getProject()).commitDocument(document);
        OCImportSymbolFix.fixAtCaret(insertionContext.getEditor(), insertionContext.getFile(), this.mySymbol);
        PsiDocumentManager.getInstance(insertionContext.getProject()).doPostponedOperationsAndUnblockDocument(insertionContext.getDocument());
    }
}
