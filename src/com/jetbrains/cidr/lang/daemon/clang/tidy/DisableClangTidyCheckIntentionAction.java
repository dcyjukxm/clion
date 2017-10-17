// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

public class DisableClangTidyCheckIntentionAction extends DisableClangTidyIntentionAction
{
    @NotNull
    private final String myCheckName;
    
    public DisableClangTidyCheckIntentionAction(@NotNull final String myCheckName) {
        if (myCheckName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "checkName", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyCheckIntentionAction", "<init>"));
        }
        this.myCheckName = myCheckName;
    }
    
    @NotNull
    @Override
    protected String modifyConfiguration(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyCheckIntentionAction", "modifyConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String disableCheck;
        try {
            disableCheck = ClangTidyUtil.disableCheck(s, this.myCheckName);
            if (disableCheck == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyCheckIntentionAction", "modifyConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return disableCheck;
    }
    
    @NotNull
    @Override
    public String getText() {
        String string;
        try {
            string = "Disable " + StringUtil.wrapWithDoubleQuote(this.myCheckName) + " check";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyCheckIntentionAction", "getText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return string;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
