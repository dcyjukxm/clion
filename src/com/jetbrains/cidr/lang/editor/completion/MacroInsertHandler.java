// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.util.List;
import com.jetbrains.cidr.lang.util.OCImmutableList;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.completion.InsertionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

public class MacroInsertHandler implements InsertHandler<LookupElement>
{
    private final OCMacroSymbol mySymbol;
    
    public MacroInsertHandler(final OCMacroSymbol mySymbol) {
        this.mySymbol = mySymbol;
    }
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        if (this.mySymbol.hasParameterList()) {
            final boolean b = !this.mySymbol.getParameterNames().isEmpty();
            CallableInsertUtils.addParensIfRequired(insertionContext, lookupElement, b);
            if (b && CallableInsertUtils.shouldInsertPlaceholders(insertionContext)) {
                final StringBuilder sb = new StringBuilder();
                final OCImmutableList<String> parameterNames = this.mySymbol.getParameterNames();
                final boolean vararg = this.mySymbol.isVararg();
                final int size = ((List)parameterNames).size();
                final int n = vararg ? (size - 1) : Integer.MAX_VALUE;
                for (int i = 0; i < size; ++i) {
                    final String s = ((List<String>)parameterNames).get(i);
                    if (i > 0) {
                        sb.append(", ");
                    }
                    if (i == 0 || i < n) {
                        sb.append("<#");
                    }
                    sb.append("__VA_ARGS__...".equals(s) ? "..." : s);
                    if (i != n - 1) {
                        sb.append("#>");
                    }
                }
                insertionContext.getDocument().insertString(insertionContext.getEditor().getCaretModel().getOffset(), (CharSequence)sb);
                CallableInsertUtils.selectFirstPlaceholderIfPresent(insertionContext);
            }
            if (b) {
                AutoPopupController.getInstance(insertionContext.getProject()).autoPopupParameterInfo(insertionContext.getEditor(), null);
            }
        }
    }
}
