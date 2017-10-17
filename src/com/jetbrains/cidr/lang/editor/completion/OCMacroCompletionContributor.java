// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.patterns.ElementPattern;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCDirective;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.CompletionContributor;

public class OCMacroCompletionContributor extends CompletionContributor
{
    public OCMacroCompletionContributor() {
        this.extend(CompletionType.SMART, (ElementPattern)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withSuperParent(2, (Class)OCDirective.class)).withParent((Class)OCReferenceElement.class), (CompletionProvider)new CompletionProvider<CompletionParameters>() {
            protected void addCompletions(@NotNull final CompletionParameters completionParameters, final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
                try {
                    if (completionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCMacroCompletionContributor$1", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (set == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/completion/OCMacroCompletionContributor$1", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                OCResolveUtil.processGlobalSymbols(null, completionParameters.getPosition(), (Processor<OCSymbol>)(ocSymbol -> {
                    try {
                        if (set == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/completion/OCMacroCompletionContributor$1", "lambda$addCompletions$0"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (ocSymbol instanceof OCMacroSymbol) {
                            set.addElement((LookupElement)SymbolLookupBuilderUtil.lookup(ocSymbol, null, OCSymbolGroupContext.MACRO_OR_UNDEF_OR_MACRO_PARAMETER_CONTEXT));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return true;
                }));
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
}
