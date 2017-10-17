// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.AddSpaceInsertHandler;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import org.jetbrains.annotations.NotNull;

class OCKeywordCompletionContributor$6 implements LookupDecorator {
    @NotNull
    @Override
    public LookupElementBuilder decorate(@NotNull final OCCompletionParameters ocCompletionParameters, @NotNull LookupElementBuilder withInsertHandler) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$6", "decorate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (withInsertHandler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$6", "decorate"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (((OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(ocCompletionParameters.getOriginalFile().getProject()).getCustomSettings((Class)OCCodeStyleSettings.class)).SPACE_BETWEEN_OPERATOR_AND_PUNCTUATOR) {
            withInsertHandler = withInsertHandler.withInsertHandler((InsertHandler)new AddSpaceInsertHandler(false));
        }
        LookupElementBuilder lookupElementBuilder;
        try {
            lookupElementBuilder = withInsertHandler;
            if (lookupElementBuilder == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCKeywordCompletionContributor$6", "decorate"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return lookupElementBuilder;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}