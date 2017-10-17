// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.jetbrains.cidr.lang.editor.OCTypedHandlerDelegate;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.codeInsight.completion.InsertionContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

private static class ClassInsertHandler implements InsertHandler<LookupElement>
{
    private OCSymbolGroupContext mySymbolContext;
    
    private ClassInsertHandler(final OCSymbolGroupContext mySymbolContext) {
        this.mySymbolContext = mySymbolContext;
    }
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        final char completionChar = insertionContext.getCompletionChar();
        if (completionChar == '.' || completionChar == ']') {
            SymbolLookupBuilderUtil.access$400(insertionContext, this.mySymbolContext);
            return;
        }
        String text;
        int tailOffset;
        for (text = insertionContext.getDocument().getText(), tailOffset = insertionContext.getTailOffset(); tailOffset < text.length() && Character.isSpaceChar(text.charAt(tailOffset)); ++tailOffset) {}
        if (tailOffset < text.length() && (text.charAt(tailOffset) == '*' || text.charAt(tailOffset) == '<')) {
            SymbolLookupBuilderUtil.access$400(insertionContext, this.mySymbolContext);
            return;
        }
        final StringBuilder sb = new StringBuilder((completionChar == '*') ? "" : "*");
        final CodeStyleSettings settings = CodeStyleSettingsManager.getSettings(insertionContext.getProject());
        if (settings != null) {
            final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)settings.getCustomSettings((Class)OCCodeStyleSettings.class);
            if (ocCodeStyleSettings != null) {
                sb.setLength(0);
                if (ocCodeStyleSettings.SPACE_BEFORE_POINTER_IN_DECLARATION) {
                    sb.append(' ');
                }
                if (completionChar != '*') {
                    sb.append('*');
                    if (ocCodeStyleSettings.SPACE_AFTER_POINTER_IN_DECLARATION) {
                        sb.append(' ');
                    }
                    else {
                        OCTypedHandlerDelegate.overTypeNextStar();
                    }
                }
            }
        }
        if (completionChar == ' ') {
            insertionContext.setAddCompletionChar(false);
        }
        SymbolLookupBuilderUtil.access$400(insertionContext, this.mySymbolContext);
        insertionContext.getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)sb.toString());
        insertionContext.getEditor().getCaretModel().moveCaretRelatively(sb.length(), 0, false, false, true);
    }
}
