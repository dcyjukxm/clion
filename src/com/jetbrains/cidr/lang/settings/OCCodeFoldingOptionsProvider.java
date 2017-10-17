// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.application.options.editor.CodeFoldingOptionsProvider;
import com.intellij.openapi.options.BeanConfigurable;

public class OCCodeFoldingOptionsProvider extends BeanConfigurable<OCCodeFoldingSettings> implements CodeFoldingOptionsProvider
{
    public OCCodeFoldingOptionsProvider() {
        super((Object)OCCodeFoldingSettings.getInstance());
        for (final OCCustomOption.CustomOption customOption : OCLanguageCodeStyleSettingsProvider.getFoldingCustomOptions()) {
            this.checkBox(customOption.fieldName, customOption.title);
        }
    }
}
