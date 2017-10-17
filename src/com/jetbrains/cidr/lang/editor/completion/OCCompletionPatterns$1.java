// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.PatternCondition;

static final class OCCompletionPatterns$1 extends PatternCondition<PsiElement> {
    public boolean accepts(@NotNull final PsiElement psiElement, final ProcessingContext processingContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$1", "accepts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile originalFile = psiElement.getContainingFile().getOriginalFile();
        Label_0087: {
            try {
                if (!(originalFile instanceof OCFile)) {
                    return false;
                }
                final OCFile ocFile = (OCFile)originalFile;
                final OCFile ocFile2 = ocFile;
                final OCLanguageKind ocLanguageKind = ocFile2.getKind();
                final boolean b = ocLanguageKind.isCpp();
                if (b) {
                    break Label_0087;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCFile ocFile = (OCFile)originalFile;
                final OCFile ocFile2 = ocFile;
                final OCLanguageKind ocLanguageKind = ocFile2.getKind();
                final boolean b = ocLanguageKind.isCpp();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}