// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.search.SingleTargetRequestResultProcessor;
import com.intellij.psi.search.TextOccurenceProcessor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.search.SearchScope;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.containers.HashSet;
import com.intellij.util.Processor;

class OCElementInMacroSubstitutionReferenceSearch$3 implements Processor<OCElementInMacroAndNonCompiledCodeReferencesSearch.Usage> {
    HashSet<OCSymbol> myProcessedDirectives = new HashSet();
    final /* synthetic */ 1MacroCallProcessor val$macroCallProcessor;
    final /* synthetic */ SearchScope val$finalScope;
    final /* synthetic */ ReferencesSearch.SearchParameters val$p;
    final /* synthetic */ PsiSearchHelper val$helper;
    
    public boolean process(final OCElementInMacroAndNonCompiledCodeReferencesSearch.Usage usage) {
        final PsiElement element = usage.getElement();
        if (element instanceof OCMacroCall) {
            final OCReferenceElement macroReferenceElement = ((OCMacroCall)element).getMacroReferenceElement();
            if (macroReferenceElement != null) {
                return this.val$macroCallProcessor.process(macroReferenceElement.getReference());
            }
        }
        else if (!(element instanceof OCDefineDirective)) {
            return true;
        }
        final OCDefineDirective ocDefineDirective = (OCDefineDirective)element;
        final String name = ocDefineDirective.getName();
        if (name == null) {
            return true;
        }
        if (this.myProcessedDirectives.add(((OCSymbolDeclarator<Object>)ocDefineDirective).getSymbol())) {
            new OCElementInMacroAndNonCompiledCodeReferencesSearch().execute(new ReferencesSearch.SearchParameters((PsiElement)ocDefineDirective, this.val$finalScope, this.val$p.isIgnoreAccessScope(), this.val$p.getOptimizer()), (Processor<OCElementInMacroAndNonCompiledCodeReferencesSearch.Usage>)this);
        }
        return this.val$helper.processElementsWithWord((TextOccurenceProcessor)new TextOccurenceProcessor() {
            SingleTargetRequestResultProcessor resultProcessor = new SingleTargetRequestResultProcessor((PsiElement)ocDefineDirective);
            
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
        }, this.val$finalScope, name, (short)1, true);
    }
}