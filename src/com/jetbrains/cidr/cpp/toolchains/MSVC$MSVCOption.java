// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import org.jetbrains.annotations.NotNull;

private abstract static class MSVCOption extends BasicOption
{
    public MSVCOption(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/cpp/toolchains/MSVC$MSVCOption", "<init>"));
        }
        super(s);
    }
}
