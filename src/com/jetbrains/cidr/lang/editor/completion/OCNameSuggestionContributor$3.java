// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class OCNameSuggestionContributor$3 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCNameSuggestionContributor$3", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement parent = ocCompletionParameters.getPosition().getParent();
        OCGlobalProjectSymbolsCache.processTopLevelSymbols(parent.getProject(), (Processor<OCSymbol>)(ocSymbol -> {
            Label_0025: {
                try {
                    if (!(ocSymbol instanceof OCClassSymbol)) {
                        return true;
                    }
                    final PsiElement psiElement2 = parent;
                    final boolean b = psiElement2 instanceof OCProtocol;
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final boolean b2 = ocSymbol2 instanceof OCProtocolSymbol;
                    if (b == b2) {
                        break Label_0025;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement2 = parent;
                    final boolean b = psiElement2 instanceof OCProtocol;
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final boolean b2 = ocSymbol2 instanceof OCProtocolSymbol;
                    if (b == b2) {
                        set.addElement(OCCompletionPriority.elementWithPriority((LookupElement)LookupElementBuilder.create(ocSymbol.getName()), OCNameSuggestionContributor.PRIORITY));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return true;
        }), null);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}