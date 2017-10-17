// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

public enum CMakeRecognizedCPPLanguageStandard
{
    CPP98("98"), 
    CPP11("11"), 
    CPP14("14"), 
    CPP17("17");
    
    @NotNull
    private final String myStandard;
    
    private CMakeRecognizedCPPLanguageStandard(final String myStandard) {
        if (myStandard == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "standard", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedCPPLanguageStandard", "<init>"));
        }
        super(s, n);
        this.myStandard = myStandard;
    }
    
    @NotNull
    public String getStandard() {
        String myStandard;
        try {
            myStandard = this.myStandard;
            if (myStandard == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedCPPLanguageStandard", "getStandard"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myStandard;
    }
    
    @NotNull
    public String getDisplayString() {
        String string;
        try {
            string = "C++" + this.myStandard;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedCPPLanguageStandard", "getDisplayString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @NotNull
    public static String fromDisplayString(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "displayString", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedCPPLanguageStandard", "fromDisplayString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String trimStart;
        try {
            trimStart = StringUtil.trimStart(s, "C++");
            if (trimStart == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedCPPLanguageStandard", "fromDisplayString"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return trimStart;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
