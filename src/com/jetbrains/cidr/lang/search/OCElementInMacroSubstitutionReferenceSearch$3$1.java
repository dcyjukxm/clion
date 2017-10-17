// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.intellij.psi.search.SingleTargetRequestResultProcessor;
import com.intellij.psi.search.TextOccurenceProcessor;

class OCElementInMacroSubstitutionReferenceSearch$3$1 implements TextOccurenceProcessor {
    SingleTargetRequestResultProcessor resultProcessor = new SingleTargetRequestResultProcessor((PsiElement)this.val$directive);
    final /* synthetic */ OCDefineDirective val$directive;
    
    public boolean execute(@NotNull final PsiElement psiElement, final int n) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$3$1", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.resultProcessor.processTextOccurrence(psiElement, n, (Processor)Processor.this.val$macroCallProcessor);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}