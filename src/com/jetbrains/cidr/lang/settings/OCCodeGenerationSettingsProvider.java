// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;

public class OCCodeGenerationSettingsProvider extends CodeStyleSettingsProvider
{
    @NotNull
    public Configurable createSettingsPage(final CodeStyleSettings codeStyleSettings, final CodeStyleSettings codeStyleSettings2) {
        OCGenerateCodeConfigurable ocGenerateCodeConfigurable;
        try {
            ocGenerateCodeConfigurable = new OCGenerateCodeConfigurable(codeStyleSettings);
            if (ocGenerateCodeConfigurable == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCodeGenerationSettingsProvider", "createSettingsPage"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Configurable)ocGenerateCodeConfigurable;
    }
    
    public String getConfigurableDisplayName() {
        return "Code Generation";
    }
    
    public boolean hasSettingsPage() {
        return false;
    }
    
    public Language getLanguage() {
        return OCLanguage.getInstance();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
