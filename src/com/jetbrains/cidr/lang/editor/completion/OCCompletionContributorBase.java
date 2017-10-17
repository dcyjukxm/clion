// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.patterns.StandardPatterns;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.ElementPattern;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.CompletionContributor;

public abstract class OCCompletionContributorBase extends CompletionContributor
{
    public final void register(@Nullable final CompletionType completionType, final ElementPattern<? extends PsiElement> elementPattern, final OCCompletionProvider ocCompletionProvider) {
        this.extend(completionType, StandardPatterns.or(new ElementPattern[] { elementPattern, PlatformPatterns.psiElement().inside(true, (ElementPattern)PlatformPatterns.psiElement((Class)OCMacroCallArgument.class)) }), (CompletionProvider)new CompletionProvider<CompletionParameters>() {
            protected void addCompletions(@NotNull final CompletionParameters completionParameters, final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
                try {
                    if (completionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCCompletionContributorBase$1", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (set == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "set", "com/jetbrains/cidr/lang/editor/completion/OCCompletionContributorBase$1", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final CompletionResultSet withOCPrefixMather = OCKeywordCompletionContributor.withOCPrefixMather(set, completionParameters.getPosition(), completionParameters.getOffset());
                final PsiElement[] array = { completionParameters.getPosition() };
                final String substring = array[0].getText().substring(0, completionParameters.getOffset() - array[0].getTextRange().getStartOffset());
                final OCMacroCallArgument ocMacroCallArgument = (OCMacroCallArgument)PsiTreeUtil.getParentOfType(array[0], (Class)OCMacroCallArgument.class);
                if (ocMacroCallArgument != null) {
                    ((OCMacroCall)ocMacroCallArgument.getParent()).processExpansionDependenciesWithIdent(array[0].getText(), (Processor<PsiElement>)(psiElement -> {
                        array[0] = psiElement;
                        return false;
                    }));
                }
                try {
                    if (elementPattern.accepts((Object)array[0], processingContext)) {
                        ocCompletionProvider.addCompletions(substring, new OCCompletionParameters(completionParameters, array[0]), processingContext, withOCPrefixMather);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
}
