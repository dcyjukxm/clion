// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings.formatter;

import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class OCLLVMCodeStyle extends OCGoogleCodeStyle
{
    public OCLLVMCodeStyle() {
        super("LLVM", "llvm");
    }
    
    public OCLLVMCodeStyle(@NotNull final String s, @NotNull final String s2) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/settings/formatter/OCLLVMCodeStyle", "<init>"));
        }
        if (s2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "id", "com/jetbrains/cidr/lang/settings/formatter/OCLLVMCodeStyle", "<init>"));
        }
        super(s, s2);
    }
    
    @Override
    protected void customize(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/formatter/OCLLVMCodeStyle", "customize"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        super.customize(codeStyleSettings);
        final CommonCodeStyleSettings commonSettings = codeStyleSettings.getCommonSettings((Language)OCLanguage.getInstance());
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        commonSettings.KEEP_CONTROL_STATEMENT_IN_ONE_LINE = false;
        commonSettings.INDENT_CASE_FROM_SWITCH = false;
        ocCodeStyleSettings.INDENT_VISIBILITY_KEYWORDS = 0;
        final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
        final OCCodeStyleSettings ocCodeStyleSettings3 = ocCodeStyleSettings;
        final boolean b = true;
        ocCodeStyleSettings3.TEMPLATE_DECLARATION_FUNCTION_WRAP = (b ? 1 : 0);
        ocCodeStyleSettings2.TEMPLATE_DECLARATION_STRUCT_WRAP = (b ? 1 : 0);
        ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_WRAP = 1;
        ocCodeStyleSettings.SPACE_BEFORE_PROTOCOLS_BRACKETS = true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
