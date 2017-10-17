// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import org.jetbrains.annotations.NotNull;

public static class FormatSpecifiersInfo
{
    public FormatType formatType;
    public int formatStringIndex;
    public int argumentsIndex;
    
    public FormatSpecifiersInfo(@NotNull final FormatType formatType, final int n, final int n2) {
        if (formatType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "formatType", "com/jetbrains/cidr/lang/util/OCFormatSpecifiersUtil$FormatSpecifiersInfo", "<init>"));
        }
        this.formatType = formatType;
        int formatStringIndex;
        if (n < 0) {
            formatStringIndex = -1;
        }
        else {
            formatStringIndex = n;
        }
        int argumentsIndex = 0;
        Label_0076: {
            try {
                this.formatStringIndex = formatStringIndex;
                if (n2 < 0) {
                    argumentsIndex = -1;
                    break Label_0076;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            argumentsIndex = n2;
        }
        this.argumentsIndex = argumentsIndex;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
