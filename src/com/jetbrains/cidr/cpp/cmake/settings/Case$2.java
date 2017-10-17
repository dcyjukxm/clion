// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.settings;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.NonNls;

enum Case$2
{
    @NotNull
    @Override
    public String apply(@NonNls @NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/settings/Case$2", "apply"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String lowerCase;
        try {
            lowerCase = s.toLowerCase();
            if (lowerCase == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/settings/Case$2", "apply"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return lowerCase;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}