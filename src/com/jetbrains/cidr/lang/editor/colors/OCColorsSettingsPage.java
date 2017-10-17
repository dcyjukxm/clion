// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.colors;

import org.jetbrains.annotations.Nullable;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import java.util.Map;
import com.intellij.codeHighlighting.RainbowHighlighter;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.jetbrains.cidr.lang.settings.OCLanguageCodeStyleSettingsProvider;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.psi.codeStyle.DisplayPriority;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.codeStyle.DisplayPrioritySortable;
import com.intellij.application.options.colors.InspectionColorSettingsPage;
import com.intellij.openapi.options.colors.RainbowColorSettingsPage;

public class OCColorsSettingsPage implements RainbowColorSettingsPage, InspectionColorSettingsPage, DisplayPrioritySortable
{
    @NotNull
    public String getDisplayName() {
        String displayName;
        try {
            displayName = OCLanguage.getInstance().getDisplayName();
            if (displayName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/colors/OCColorsSettingsPage", "getDisplayName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return displayName;
    }
    
    public Icon getIcon() {
        return null;
    }
    
    public DisplayPriority getPriority() {
        return DisplayPriority.KEY_LANGUAGE_SETTINGS;
    }
    
    @NotNull
    public AttributesDescriptor[] getAttributeDescriptors() {
        AttributesDescriptor[] attributeDescriptors;
        try {
            attributeDescriptors = OCLanguageCodeStyleSettingsProvider.getAttributeDescriptors();
            if (attributeDescriptors == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/colors/OCColorsSettingsPage", "getAttributeDescriptors"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return attributeDescriptors;
    }
    
    @NotNull
    public ColorDescriptor[] getColorDescriptors() {
        ColorDescriptor[] empty_ARRAY;
        try {
            empty_ARRAY = ColorDescriptor.EMPTY_ARRAY;
            if (empty_ARRAY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/colors/OCColorsSettingsPage", "getColorDescriptors"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return empty_ARRAY;
    }
    
    @NotNull
    public SyntaxHighlighter getHighlighter() {
        OCFileHighlighter ocFileHighlighter;
        try {
            ocFileHighlighter = new OCFileHighlighter(OCLanguageKind.OBJ_CPP, false, false, false);
            if (ocFileHighlighter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/colors/OCColorsSettingsPage", "getHighlighter"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (SyntaxHighlighter)ocFileHighlighter;
    }
    
    @NotNull
    public String getDemoText() {
        String replace;
        try {
            replace = OCLanguageCodeStyleSettingsProvider.getCodeSample("Colors").replace("{$SEMANTIC_DEMO}", RainbowHighlighter.generatePaletteExample("\n     * "));
            if (replace == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/colors/OCColorsSettingsPage", "getDemoText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return replace;
    }
    
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        final Map<String, TextAttributesKey> rainbowHLM = RainbowHighlighter.createRainbowHLM();
        rainbowHLM.put("kw", OCHighlightingKeys.SELFSUPERTHIS);
        rainbowHLM.put("evar", OCHighlightingKeys.EXTERN_VARIABLE);
        rainbowHLM.put("gvar", OCHighlightingKeys.GLOBAL_VARIABLE);
        rainbowHLM.put("lvar", OCHighlightingKeys.LOCAL_VARIABLE);
        rainbowHLM.put("par", OCHighlightingKeys.PARAMETER);
        rainbowHLM.put("fun", OCHighlightingKeys.FUNCTION);
        rainbowHLM.put("op", OCHighlightingKeys.OVERLOADED_OPERATOR);
        rainbowHLM.put("prop", OCHighlightingKeys.PROPERTY);
        rainbowHLM.put("prop_attr", OCHighlightingKeys.PROPERTY_ATTRIBUTE);
        rainbowHLM.put("sfield", OCHighlightingKeys.STRUCT_FIELD);
        rainbowHLM.put("sel", OCHighlightingKeys.MESSAGE_ARGUMENT);
        rainbowHLM.put("ifdef", OCHighlightingKeys.CONDITIONALLY_NOT_COMPILED);
        rainbowHLM.put("macro", OCHighlightingKeys.MACRONAME);
        rainbowHLM.put("macro_par", OCHighlightingKeys.MACRO_PARAMETER);
        rainbowHLM.put("ivar", OCHighlightingKeys.INSTANCE_VARIABLE);
        rainbowHLM.put("struct", OCHighlightingKeys.STRUCT_LIKE);
        rainbowHLM.put("cls", OCHighlightingKeys.CLASS_REFERENCE);
        rainbowHLM.put("prt", OCHighlightingKeys.PROTOCOL_REFERENCE);
        rainbowHLM.put("enum", OCHighlightingKeys.ENUM_CONST);
        rainbowHLM.put("label", OCHighlightingKeys.LABEL);
        rainbowHLM.put("def", OCHighlightingKeys.TYPEDEF);
        rainbowHLM.put("fmt", OCHighlightingKeys.OC_FORMAT_STRING_TOKEN);
        rainbowHLM.put("templt", OCHighlightingKeys.TEMPLATE_TYPE);
        rainbowHLM.put("templv", OCHighlightingKeys.TEMPLATE_VALUE);
        rainbowHLM.put("ns", OCHighlightingKeys.NAMESPACE_LIKE);
        rainbowHLM.put("generic", OCHighlightingKeys.GENERIC_PARAMETER);
        return rainbowHLM;
    }
    
    public boolean isRainbowType(final TextAttributesKey textAttributesKey) {
        Label_0027: {
            try {
                if (OCHighlightingKeys.LOCAL_VARIABLE.equals((Object)textAttributesKey)) {
                    break Label_0027;
                }
                final TextAttributesKey textAttributesKey2 = OCHighlightingKeys.PARAMETER;
                final TextAttributesKey textAttributesKey3 = textAttributesKey;
                final boolean b = textAttributesKey2.equals((Object)textAttributesKey3);
                if (b) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final TextAttributesKey textAttributesKey2 = OCHighlightingKeys.PARAMETER;
                final TextAttributesKey textAttributesKey3 = textAttributesKey;
                final boolean b = textAttributesKey2.equals((Object)textAttributesKey3);
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public Language getLanguage() {
        return OCLanguage.getInstance();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
