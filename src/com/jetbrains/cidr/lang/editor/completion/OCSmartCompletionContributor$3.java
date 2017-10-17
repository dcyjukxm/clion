// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.openapi.util.UserDataHolder;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;

static final class OCSmartCompletionContributor$3 extends CompletionBuilder {
    final /* synthetic */ OCPunctuatorElementType val$qualifyingToken;
    
    @Override
    protected void addLookup(@NotNull final LookupElement lookupElement) {
        try {
            if (lookupElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lookup", "com/jetbrains/cidr/lang/editor/completion/OCSmartCompletionContributor$3", "addLookup"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCQualifiedExpression.COMPLETION_QUALIFYING_TOKEN_KEY.set((UserDataHolder)lookupElement, (Object)this.val$qualifyingToken);
        super.addLookup(lookupElement);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}