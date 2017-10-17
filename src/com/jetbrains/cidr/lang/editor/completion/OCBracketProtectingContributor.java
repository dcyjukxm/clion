// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.intellij.codeInsight.TargetElementUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionInitializationContext;
import com.intellij.codeInsight.completion.CompletionContributor;

public class OCBracketProtectingContributor extends CompletionContributor
{
    public void beforeCompletion(@NotNull final CompletionInitializationContext completionInitializationContext) {
        try {
            if (completionInitializationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/completion/OCBracketProtectingContributor", "beforeCompletion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiReference reference = TargetElementUtil.findReference(completionInitializationContext.getEditor(), completionInitializationContext.getSelectionEndOffset());
        try {
            if (reference instanceof OCOperatorReference) {
                completionInitializationContext.setReplacementOffset(completionInitializationContext.getIdentifierEndOffset());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
