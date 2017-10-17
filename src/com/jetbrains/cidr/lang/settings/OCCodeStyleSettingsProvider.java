// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.codeStyle.DisplayPriority;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.PlatformUtils;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;

public class OCCodeStyleSettingsProvider extends CodeStyleSettingsProvider
{
    public CustomCodeStyleSettings createCustomSettings(final CodeStyleSettings codeStyleSettings) {
        return new OCCodeStyleSettings(codeStyleSettings);
    }
    
    @NotNull
    public Configurable createSettingsPage(final CodeStyleSettings codeStyleSettings, final CodeStyleSettings codeStyleSettings2) {
        CodeStyleAbstractConfigurable codeStyleAbstractConfigurable;
        try {
            codeStyleAbstractConfigurable = new CodeStyleAbstractConfigurable(codeStyleSettings, codeStyleSettings2, this.getConfigurableDisplayName()) {
                @Override
                protected CodeStyleAbstractPanel createPanel(final CodeStyleSettings codeStyleSettings) {
                    return new OCCodeStyleMainPanel(this.getCurrentSettings(), codeStyleSettings);
                }
                
                public String getHelpTopic() {
                    return PlatformUtils.isAppCode() ? "topicId2221" : "reference.settings.codestyle.ccpp";
                }
            };
            if (codeStyleAbstractConfigurable == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCodeStyleSettingsProvider", "createSettingsPage"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Configurable)codeStyleAbstractConfigurable;
    }
    
    public DisplayPriority getPriority() {
        try {
            if (PlatformUtils.isCidr()) {
                return DisplayPriority.KEY_LANGUAGE_SETTINGS;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return DisplayPriority.LANGUAGE_SETTINGS;
    }
    
    public String getConfigurableDisplayName() {
        return OCLanguage.getInstance().getDisplayName();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
