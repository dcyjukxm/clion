// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings.formatter;

import com.intellij.internal.statistic.UsageTrigger;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.codeStyle.PredefinedCodeStyle;

public class OCPredefinedCodeStyleBase extends PredefinedCodeStyle
{
    @NotNull
    private final String myId;
    
    public OCPredefinedCodeStyleBase(@NotNull final String s, @NotNull final String myId) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/settings/formatter/OCPredefinedCodeStyleBase", "<init>"));
        }
        if (myId == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "id", "com/jetbrains/cidr/lang/settings/formatter/OCPredefinedCodeStyleBase", "<init>"));
        }
        super(s, (Language)OCLanguage.getInstance());
        this.myId = myId;
    }
    
    public void apply(final CodeStyleSettings codeStyleSettings) {
        UsageTrigger.trigger("cidr.formatter.cs." + this.myId);
    }
}
