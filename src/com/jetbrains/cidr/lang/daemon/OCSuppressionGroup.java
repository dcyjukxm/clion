// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import org.jetbrains.annotations.NotNull;

public class OCSuppressionGroup
{
    @NotNull
    public final String compiler;
    @NotNull
    public final String suppressionOption;
    
    public OCSuppressionGroup(@NotNull final String compiler, @NotNull final String suppressionOption) {
        if (compiler == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "compiler", "com/jetbrains/cidr/lang/daemon/OCSuppressionGroup", "<init>"));
        }
        if (suppressionOption == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suppressionOption", "com/jetbrains/cidr/lang/daemon/OCSuppressionGroup", "<init>"));
        }
        this.compiler = compiler;
        this.suppressionOption = suppressionOption;
    }
}
