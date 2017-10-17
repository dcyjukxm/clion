// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lang;

import com.intellij.openapi.util.TextRange;
import com.intellij.lang.Language;
import com.intellij.lang.LanguageParserDefinitions;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.InjectedLanguagePlaces;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.LanguageInjector;

public class OCDebuggerLanguageInjector implements LanguageInjector
{
    public void getLanguagesToInject(@NotNull final PsiLanguageInjectionHost psiLanguageInjectionHost, @NotNull final InjectedLanguagePlaces injectedLanguagePlaces) {
        try {
            if (psiLanguageInjectionHost == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "host", "com/jetbrains/cidr/execution/debugger/backend/lang/OCDebuggerLanguageInjector", "getLanguagesToInject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (injectedLanguagePlaces == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "injectionPlacesRegistrar", "com/jetbrains/cidr/execution/debugger/backend/lang/OCDebuggerLanguageInjector", "getLanguagesToInject"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCLanguage instance = OCLanguage.getInstance();
        Label_0116: {
            try {
                if (!(psiLanguageInjectionHost instanceof GDBExpressionPlaceholder)) {
                    return;
                }
                final LanguageParserDefinitions languageParserDefinitions = LanguageParserDefinitions.INSTANCE;
                final OCLanguage ocLanguage = instance;
                final Object o = languageParserDefinitions.forLanguage((Language)ocLanguage);
                if (o != null) {
                    break Label_0116;
                }
                return;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final LanguageParserDefinitions languageParserDefinitions = LanguageParserDefinitions.INSTANCE;
                final OCLanguage ocLanguage = instance;
                final Object o = languageParserDefinitions.forLanguage((Language)ocLanguage);
                if (o != null) {
                    injectedLanguagePlaces.addPlace((Language)instance, new TextRange(0, psiLanguageInjectionHost.getTextLength()), (String)null, (String)null);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
