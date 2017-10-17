// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.util.Iterator;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import java.util.Collection;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class OCNameSuggestionContributor$2 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCNameSuggestionContributor$2", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement position = ocCompletionParameters.getPosition();
        final OCMethodSelectorPart ocMethodSelectorPart = (OCMethodSelectorPart)position.getParent();
        if (ocMethodSelectorPart.getParameter() == position) {
            final Iterator<String> iterator = OCNameSuggester.suggestForType(ocMethodSelectorPart.getType().resolve(position.getContainingFile()), position, (Collection<String>)Collections.emptyList()).iterator();
            while (iterator.hasNext()) {
                set.addElement(OCCompletionPriority.elementWithPriority((LookupElement)LookupElementBuilder.create((String)iterator.next()), OCNameSuggestionContributor.PRIORITY));
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}