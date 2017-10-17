// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import org.jetbrains.annotations.NotNull;

public static class OCIndentInfo
{
    @NotNull
    public final OffsetType baseIndent;
    @NotNull
    public final Integer ifIndent;
    public final int ifNestLevel;
    
    public OCIndentInfo(@NotNull final OffsetType baseIndent, @NotNull final Integer ifIndent, final int ifNestLevel) {
        if (baseIndent == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseIndent", "com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OCIndentInfo", "<init>"));
        }
        if (ifIndent == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ifIndent", "com/jetbrains/cidr/lang/formatting/OCPreprocessorFormatterData$OCIndentInfo", "<init>"));
        }
        this.baseIndent = baseIndent;
        this.ifIndent = ifIndent;
        this.ifNestLevel = ifNestLevel;
    }
}
