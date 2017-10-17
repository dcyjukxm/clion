// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.codeInsight.template.EverywhereContextType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;

public static class LanguageBase extends OCCodeContextType
{
    public LanguageBase(@NotNull final OCLanguageKind ocLanguageKind) {
        if (ocLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "needKind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageBase", "<init>"));
        }
        super(ocLanguageKind, LanguageInfo.getContextName(ocLanguageKind), ocLanguageKind.getDisplayName(), (Class<? extends TemplateContextType>)EverywhereContextType.class);
    }
}
