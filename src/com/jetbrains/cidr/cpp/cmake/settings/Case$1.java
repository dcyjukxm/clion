// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.NonNls;

enum Case$1
{
    @NotNull
    @Override
    public String apply(@NonNls @NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/settings/Case$1", "apply"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String upperCase;
        try {
            upperCase = s.toUpperCase();
            if (upperCase == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/Case$1", "apply"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return upperCase;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}