// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;

public class OCLanguageCodeStyleSettingsProvider extends OCBaseLanguageCodeStyleSettingsProvider
{
    public static final OCCustomOption CUSTOM_OPTION;
    
    public OCLanguageCodeStyleSettingsProvider() {
        super(OCLanguage.getInstance());
    }
    
    @NotNull
    @Override
    public CommonCodeStyleSettings getDefaultCommonSettings() {
        final CommonCodeStyleSettings defaultCommonSettings = super.getDefaultCommonSettings();
        CommonCodeStyleSettings commonCodeStyleSettings;
        try {
            OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.configureDefaultSettings(defaultCommonSettings);
            commonCodeStyleSettings = defaultCommonSettings;
            if (commonCodeStyleSettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "getDefaultCommonSettings"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return commonCodeStyleSettings;
    }
    
    @NotNull
    public static String getCodeSample(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileNameWithoutExt", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "getCodeSample"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final String exampleExtension = OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getExampleExtension();
        String codeSample;
        try {
            codeSample = getCodeSample(s, exampleExtension);
            if (codeSample == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "getCodeSample"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return codeSample;
    }
    
    @NotNull
    public static String getCodeSample(@NotNull final String s, @NotNull final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileNameWithoutExtension", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "getCodeSample"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "extension", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "getCodeSample"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        String exampleString;
        try {
            exampleString = OCCustomOption.ResourceReader.readExampleString(OCCustomOption.class, "formatter/" + s2 + "/" + s + "." + s2);
            if (exampleString == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "getCodeSample"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return exampleString;
    }
    
    @NotNull
    public static OCCustomOption.CustomOption[] getFoldingCustomOptions() {
        OCCustomOption.CustomOption[] foldingCustomOptions;
        try {
            foldingCustomOptions = OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getFoldingCustomOptions();
            if (foldingCustomOptions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "getFoldingCustomOptions"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return foldingCustomOptions;
    }
    
    @NotNull
    public static AttributesDescriptor[] getAttributeDescriptors() {
        AttributesDescriptor[] attributeDescriptors;
        try {
            attributeDescriptors = OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getAttributeDescriptors();
            if (attributeDescriptors == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "getAttributeDescriptors"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return attributeDescriptors;
    }
    
    @NotNull
    @Override
    protected String getCodeSampleContent(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileNameWithoutExt", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "getCodeSampleContent"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        String codeSample;
        try {
            codeSample = getCodeSample(s);
            if (codeSample == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "getCodeSampleContent"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return codeSample;
    }
    
    public String getFileExt() {
        return OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getExampleExtension();
    }
    
    public DisplayPriority getDisplayPriority() {
        return DisplayPriority.KEY_LANGUAGE_SETTINGS;
    }
    
    public IndentOptionsEditor getIndentOptionsEditor() {
        return (IndentOptionsEditor)new OCIndentOptionsEditor(this);
    }
    
    public void customizeSettings(@NotNull final CodeStyleSettingsCustomizable codeStyleSettingsCustomizable, @NotNull final LanguageCodeStyleSettingsProvider.SettingsType settingsType) {
        try {
            if (codeStyleSettingsCustomizable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "customizeSettings"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (settingsType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settingsType", "com/jetbrains/cidr/lang/settings/OCLanguageCodeStyleSettingsProvider", "customizeSettings"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        Label_0562: {
            if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.WRAPPING_AND_BRACES_SETTINGS) {
                codeStyleSettingsCustomizable.showStandardOptions(OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getWrappingAndBracesOptions());
                for (final OCCustomOption.RenameAction renameAction : OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getWrappingAndBracesRenames()) {
                    codeStyleSettingsCustomizable.renameStandardOption(renameAction.from, renameAction.to);
                }
                for (final OCCustomOption.CustomOption customOption : OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getWrappingAndBracesCustomOptions()) {
                    codeStyleSettingsCustomizable.showCustomOption((Class)OCCodeStyleSettings.class, customOption.fieldName, customOption.title, customOption.groupName, customOption.anchor, customOption.anchorFieldName, customOption.options);
                }
            }
            else if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.SPACING_SETTINGS) {
                codeStyleSettingsCustomizable.showStandardOptions(OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getSpacingOptions());
                for (final OCCustomOption.RenameAction renameAction2 : OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getSpacingRenames()) {
                    codeStyleSettingsCustomizable.renameStandardOption(renameAction2.from, renameAction2.to);
                }
                for (final OCCustomOption.CustomOption customOption2 : OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getSpacingCustomOptions()) {
                    codeStyleSettingsCustomizable.showCustomOption((Class)OCCodeStyleSettings.class, customOption2.fieldName, customOption2.title, customOption2.groupName, customOption2.anchor, customOption2.anchorFieldName, customOption2.options);
                }
            }
            else if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.INDENT_SETTINGS) {
                for (final OCCustomOption.CustomOption customOption3 : OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getIndentCustomOptions()) {
                    codeStyleSettingsCustomizable.showCustomOption((Class)OCCodeStyleSettings.class, customOption3.fieldName, customOption3.title, customOption3.groupName, customOption3.anchor, customOption3.anchorFieldName, customOption3.options);
                }
            }
            else {
                try {
                    if (settingsType == LanguageCodeStyleSettingsProvider.SettingsType.LANGUAGE_SPECIFIC) {
                        OCGenerateCodeConfigurable.customizeSettings(codeStyleSettingsCustomizable);
                        break Label_0562;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                codeStyleSettingsCustomizable.showStandardOptions(new String[] { "KEEP_BLANK_LINES_IN_DECLARATIONS", "KEEP_BLANK_LINES_IN_CODE", "KEEP_BLANK_LINES_BEFORE_RBRACE", "BLANK_LINES_BEFORE_IMPORTS", "BLANK_LINES_AFTER_IMPORTS", "BLANK_LINES_AROUND_CLASS", "BLANK_LINES_AFTER_CLASS_HEADER", "BLANK_LINES_AROUND_FIELD_IN_INTERFACE", "BLANK_LINES_AROUND_METHOD_IN_INTERFACE", "BLANK_LINES_AROUND_FIELD", "BLANK_LINES_AROUND_METHOD", "BLANK_LINES_BEFORE_METHOD_BODY" });
            }
        }
        for (final OCCustomOption.RenameAction renameAction3 : OCLanguageCodeStyleSettingsProvider.CUSTOM_OPTION.getBlankLinesRenames()) {
            codeStyleSettingsCustomizable.renameStandardOption(renameAction3.from, renameAction3.to);
        }
    }
    
    static {
        CUSTOM_OPTION = OCLanguage.getCustomOption();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
