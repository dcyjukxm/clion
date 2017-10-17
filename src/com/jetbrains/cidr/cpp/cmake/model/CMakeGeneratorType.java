// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public enum CMakeGeneratorType
{
    UNIX("Unix Makefiles"), 
    MINGW("MinGW Makefiles"), 
    NMAKE("NMake Makefiles");
    
    private String myValue;
    
    private CMakeGeneratorType(final String myValue) {
        this.myValue = myValue;
    }
    
    @NotNull
    public String getValue() {
        String myValue;
        try {
            myValue = this.myValue;
            if (myValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeGeneratorType", "getValue"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myValue;
    }
    
    public boolean isSupported() {
        return true;
    }
    
    @Nullable
    public static CMakeGeneratorType fromGeneratorSpec(@Nullable final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        for (final CMakeGeneratorType cMakeGeneratorType : values()) {
            try {
                if (s.contains(cMakeGeneratorType.getValue())) {
                    return cMakeGeneratorType;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
