// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings.formatter;

import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.CodeStyleSettings;

public class OCLLDBCodeStyle extends OCLLVMCodeStyle
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCLLDBCodeStyle() {
        super("LLDB", "lldb");
    }
    
    @Override
    protected void customize(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/formatter/OCLLDBCodeStyle", "customize"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        super.customize(codeStyleSettings);
        final CommonCodeStyleSettings commonSettings = codeStyleSettings.getCommonSettings((Language)OCLanguage.getInstance());
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
        final CommonCodeStyleSettings.IndentOptions indentOptions = commonSettings.getIndentOptions();
        Label_0161: {
            try {
                final CommonCodeStyleSettings commonCodeStyleSettings = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings2 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings3 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings4 = commonSettings;
                final boolean b = true;
                commonCodeStyleSettings4.FINALLY_ON_NEW_LINE = b;
                commonCodeStyleSettings3.CATCH_ON_NEW_LINE = b;
                commonCodeStyleSettings2.WHILE_ON_NEW_LINE = b;
                commonCodeStyleSettings.ELSE_ON_NEW_LINE = b;
                final CommonCodeStyleSettings commonCodeStyleSettings5 = commonSettings;
                final CommonCodeStyleSettings commonCodeStyleSettings6 = commonSettings;
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings3 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings4 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings5 = ocCodeStyleSettings;
                final int n = 5;
                ocCodeStyleSettings5.BLOCK_BRACE_PLACEMENT = n;
                ocCodeStyleSettings4.METHOD_BRACE_PLACEMENT = n;
                ocCodeStyleSettings3.FUNCTION_BRACE_PLACEMENT = n;
                ocCodeStyleSettings2.NAMESPACE_BRACE_PLACEMENT = n;
                commonCodeStyleSettings6.CLASS_BRACE_STYLE = n;
                commonCodeStyleSettings5.BRACE_STYLE = n;
                final OCCodeStyleSettings ocCodeStyleSettings6 = ocCodeStyleSettings;
                final OCCodeStyleSettings ocCodeStyleSettings7 = ocCodeStyleSettings;
                final int n2 = 2;
                ocCodeStyleSettings7.FUNCTION_TOP_AFTER_RETURN_TYPE_WRAP = n2;
                ocCodeStyleSettings6.FUNCTION_NON_TOP_AFTER_RETURN_TYPE_WRAP = n2;
                ocCodeStyleSettings.CLASS_CONSTRUCTOR_INIT_LIST_WRAP = 5;
                commonSettings.INDENT_CASE_FROM_SWITCH = true;
                if (OCLLDBCodeStyle.$assertionsDisabled) {
                    break Label_0161;
                }
                final CommonCodeStyleSettings.IndentOptions indentOptions2 = indentOptions;
                if (indentOptions2 == null) {
                    break Label_0161;
                }
                break Label_0161;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final CommonCodeStyleSettings.IndentOptions indentOptions2 = indentOptions;
                if (indentOptions2 == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        final OCCodeStyleSettings ocCodeStyleSettings8 = ocCodeStyleSettings;
        final OCCodeStyleSettings ocCodeStyleSettings9 = ocCodeStyleSettings;
        final OCCodeStyleSettings ocCodeStyleSettings10 = ocCodeStyleSettings;
        final CommonCodeStyleSettings.IndentOptions indentOptions3 = indentOptions;
        final int n3 = 4;
        indentOptions3.INDENT_SIZE = n3;
        ocCodeStyleSettings10.INDENT_INSIDE_CODE_BLOCK = n3;
        ocCodeStyleSettings9.INDENT_CLASS_MEMBERS = n3;
        ocCodeStyleSettings8.INDENT_C_STRUCT_MEMBERS = n3;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCLLDBCodeStyle.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
