// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.intellij.openapi.util.TextRange;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.ParserDefinition;
import com.jetbrains.cidr.lang.editor.OCASTFactory;
import com.intellij.psi.InjectedLanguagePlaces;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.LanguageInjector;

public class DoxygenLanguageInjector implements LanguageInjector
{
    public void getLanguagesToInject(@NotNull final PsiLanguageInjectionHost psiLanguageInjectionHost, @NotNull final InjectedLanguagePlaces injectedLanguagePlaces) {
        try {
            if (psiLanguageInjectionHost == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "host", "com/jetbrains/cidr/doxygen/DoxygenLanguageInjector", "getLanguagesToInject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (injectedLanguagePlaces == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "injectionPlacesRegistrar", "com/jetbrains/cidr/doxygen/DoxygenLanguageInjector", "getLanguagesToInject"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (psiLanguageInjectionHost instanceof OCASTFactory.PsiCommentPlaceholder) {
            final DxLanguage instance = DxLanguage.INSTANCE;
            final ParserDefinition parserDefinition = (ParserDefinition)LanguageParserDefinitions.INSTANCE.forLanguage((Language)instance);
            try {
                if (parserDefinition != null) {
                    injectedLanguagePlaces.addPlace((Language)instance, new TextRange(0, psiLanguageInjectionHost.getTextLength()), (String)null, (String)null);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
