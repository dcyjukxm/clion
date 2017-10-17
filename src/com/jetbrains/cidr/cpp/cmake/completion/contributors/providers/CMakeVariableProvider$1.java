// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

static final class CMakeVariableProvider$1 implements InsertHandler<LookupElement> {
    final /* synthetic */ String val$literalSuffix;
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        final int n = insertionContext.getStartOffset() + lookupElement.getLookupString().length();
        final boolean b = n >= insertionContext.getDocument().getText().length() || insertionContext.getDocument().getText().charAt(n) != '}';
        final boolean b2 = insertionContext.getCompletionChar() == '\t';
        final int n2 = 0;
        if (b) {
            if (b2) {
                final int index = this.val$literalSuffix.indexOf("}");
                if (index == -1) {
                    insertionContext.getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)("}" + this.val$literalSuffix));
                    insertionContext.getEditor().getCaretModel().moveToOffset(insertionContext.getTailOffset() - this.val$literalSuffix.length() + n2);
                }
                else {
                    final String substring = this.val$literalSuffix.substring(index);
                    insertionContext.getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)substring);
                    insertionContext.getEditor().getCaretModel().moveToOffset(insertionContext.getTailOffset() - substring.length() + n2 + 1);
                }
            }
            else {
                insertionContext.getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)"}");
                insertionContext.getEditor().getCaretModel().moveToOffset(insertionContext.getTailOffset() + n2);
            }
        }
        else {
            insertionContext.getEditor().getCaretModel().moveToOffset(Math.min(insertionContext.getDocument().getTextLength(), insertionContext.getTailOffset() + n2 + 1));
        }
    }
}