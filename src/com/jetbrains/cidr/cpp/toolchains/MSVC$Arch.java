// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

public static class Arch extends MSVCOption
{
    public static final String TYPE = "msvc.arch";
    public static final String DEFAULT = "x86";
    
    public Arch(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/cpp/toolchains/MSVC$Arch", "<init>"));
        }
        super(s);
    }
    
    @NotNull
    @Override
    protected String getType() {
        String s;
        try {
            s = "msvc.arch";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$Arch", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return s;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
