// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.intellij.psi.PsiElement;
import com.intellij.patterns.ElementPattern;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.jetbrains.cidr.lang.documentation.doxygen.api.DoxygenCmd;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.lang.Language;
import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.cidr.doxygen.psi.DxTypes;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.codeInsight.completion.CompletionContributor;

public class DxCompletionContributor extends CompletionContributor
{
    public DxCompletionContributor() {
        this.extend(CompletionType.BASIC, (ElementPattern)PlatformPatterns.psiElement(DxTypes.TAG_NAME).withLanguage((Language)DxLanguage.INSTANCE), (CompletionProvider)new CompletionProvider<CompletionParameters>() {
            public void addCompletions(@NotNull final CompletionParameters completionParameters, final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
                try {
                    if (completionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/doxygen/DxCompletionContributor$1", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (set == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resultSet", "com/jetbrains/cidr/doxygen/DxCompletionContributor$1", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final DoxygenCmd[] values = DoxygenCmd.values();
                for (int length = values.length, i = 0; i < length; ++i) {
                    set.addElement((LookupElement)LookupElementBuilder.create((Object)values[i]));
                }
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
        this.extend(CompletionType.BASIC, (ElementPattern)PlatformPatterns.psiElement(DxTypes.TAG_OPTION).withLanguage((Language)DxLanguage.INSTANCE), (CompletionProvider)new CompletionProvider<CompletionParameters>() {
            public void addCompletions(@NotNull final CompletionParameters completionParameters, final ProcessingContext processingContext, @NotNull final CompletionResultSet set) {
                try {
                    if (completionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/doxygen/DxCompletionContributor$2", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (set == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resultSet", "com/jetbrains/cidr/doxygen/DxCompletionContributor$2", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                set.addElement((LookupElement)LookupElementBuilder.create("in"));
                set.addElement((LookupElement)LookupElementBuilder.create("out"));
                set.addElement((LookupElement)LookupElementBuilder.create("in,out"));
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
    
    public boolean invokeAutoPopup(@NotNull final PsiElement psiElement, final char c) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/doxygen/DxCompletionContributor", "invokeAutoPopup"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
