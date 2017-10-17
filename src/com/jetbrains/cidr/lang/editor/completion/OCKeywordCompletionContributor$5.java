// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.PatternCondition;

class OCKeywordCompletionContributor$5 extends PatternCondition<PsiElement> {
    public boolean accepts(@NotNull final PsiElement psiElement, final ProcessingContext processingContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$5", "accepts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCCompilerFeatures.supportsInstancetype(psiElement.getContainingFile());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}