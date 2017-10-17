// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion;

import org.jetbrains.annotations.NotNull;

public enum CMakeRecognizedGnuLanguage
{
    C("CC"), 
    C_PLUS_PLUS("CXX"), 
    FORTRAN("G77");
    
    @NotNull
    private final String myLanguage;
    
    private CMakeRecognizedGnuLanguage(final String myLanguage) {
        if (myLanguage == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "language", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedGnuLanguage", "<init>"));
        }
        super(s, n);
        this.myLanguage = myLanguage;
    }
    
    @NotNull
    public String getLanguage() {
        String myLanguage;
        try {
            myLanguage = this.myLanguage;
            if (myLanguage == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedGnuLanguage", "getLanguage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myLanguage;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
