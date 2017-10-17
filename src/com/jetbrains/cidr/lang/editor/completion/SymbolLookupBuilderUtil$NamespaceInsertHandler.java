// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.editor.OCTypedHandlerDelegate;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceAlias;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

private static class NamespaceInsertHandler implements InsertHandler<LookupElement>
{
    private final PsiElement myContextExpression;
    
    private NamespaceInsertHandler(final PsiElement myContextExpression) {
        this.myContextExpression = myContextExpression;
    }
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        final OCCppUsingStatement ocCppUsingStatement = (OCCppUsingStatement)PsiTreeUtil.getContextOfType(this.myContextExpression, new Class[] { OCCppUsingStatement.class });
        if (ocCppUsingStatement != null && ocCppUsingStatement.isNamespaceUsing()) {
            return;
        }
        final PsiElement parent = this.myContextExpression.getParent();
        if (parent instanceof OCCppNamespace) {
            return;
        }
        if (parent instanceof OCCppNamespaceAlias) {
            return;
        }
        final char completionChar = insertionContext.getCompletionChar();
        String text;
        int tailOffset;
        for (text = insertionContext.getDocument().getText(), tailOffset = insertionContext.getTailOffset(); tailOffset < text.length() && Character.isSpaceChar(text.charAt(tailOffset)); ++tailOffset) {}
        if (tailOffset >= text.length() || text.charAt(tailOffset) != ':') {
            String s;
            if (completionChar == ':') {
                s = ":";
            }
            else {
                s = "::";
            }
            insertionContext.getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)s);
            insertionContext.getEditor().getCaretModel().moveCaretRelatively(s.length(), 0, false, false, true);
            OCTypedHandlerDelegate.overTypeNextColon();
        }
    }
}
