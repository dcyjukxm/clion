// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.PsiElementProcessor;

class OCElementInMacroSubstitutionReferenceSearch$2 implements PsiElementProcessor {
    final /* synthetic */ 1MacroCallProcessor val$macroCallProcessor;
    
    public boolean execute(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$2", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(psiElement instanceof OCMacroCall)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCReferenceElement macroReferenceElement = ((OCMacroCall)psiElement).getMacroReferenceElement();
        try {
            if (macroReferenceElement != null) {
                return this.val$macroCallProcessor.process(macroReferenceElement.getReference());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}