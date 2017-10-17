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

public class OCBracesAllmanCodeStyle extends OCPredefinedCodeStyleBase
{
    public OCBracesAllmanCodeStyle() {
        super("Allman braces", "allman");
    }
    
    @Override
    public void apply(@NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/settings/formatter/OCBracesAllmanCodeStyle", "apply"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CommonCodeStyleSettings commonSettings = codeStyleSettings.getCommonSettings((Language)OCLanguage.getInstance());
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class);
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
        final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
        final int n = 2;
        ocCodeStyleSettings2.BLOCK_BRACE_PLACEMENT = n;
        commonCodeStyleSettings5.BRACE_STYLE = n;
        super.apply(codeStyleSettings);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
