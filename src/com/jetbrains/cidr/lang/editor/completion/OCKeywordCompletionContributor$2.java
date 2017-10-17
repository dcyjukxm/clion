// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.PatternCondition;

static final class OCKeywordCompletionContributor$2 extends PatternCondition<PsiElement> {
    final /* synthetic */ OCCompilerFeatures.Feature val$feature;
    
    public boolean accepts(@NotNull final PsiElement psiElement, final ProcessingContext processingContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$2", "accepts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCCompilerFeatures.isFeatureEnabled(psiElement.getContainingFile(), this.val$feature);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}