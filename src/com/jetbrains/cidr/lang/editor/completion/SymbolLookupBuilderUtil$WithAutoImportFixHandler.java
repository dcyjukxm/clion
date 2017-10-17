// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.completion.InsertionContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

private static class WithAutoImportFixHandler implements InsertHandler<LookupElement>
{
    private OCSymbolGroupContext mySymbolContext;
    private boolean myInsertSpace;
    
    private WithAutoImportFixHandler(final OCSymbolGroupContext mySymbolContext, final boolean myInsertSpace) {
        this.mySymbolContext = mySymbolContext;
        this.myInsertSpace = myInsertSpace;
    }
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        SymbolLookupBuilderUtil.access$400(insertionContext, this.mySymbolContext);
        if (this.myInsertSpace && insertionContext.getCompletionChar() != ' ') {
            final int tailOffset = insertionContext.getTailOffset();
            final CharSequence charsSequence = insertionContext.getDocument().getCharsSequence();
            if (tailOffset < charsSequence.length()) {
                final char char1 = charsSequence.charAt(tailOffset);
                if (char1 != ' ' && char1 != '>' && char1 != '<') {
                    insertionContext.getDocument().insertString(tailOffset, (CharSequence)" ");
                }
            }
            insertionContext.getEditor().getCaretModel().moveCaretRelatively(1, 0, false, false, true);
        }
    }
}
