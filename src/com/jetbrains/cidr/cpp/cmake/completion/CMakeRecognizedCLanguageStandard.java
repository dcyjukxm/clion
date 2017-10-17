// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

public enum CMakeRecognizedCLanguageStandard
{
    C90("90"), 
    C99("99"), 
    C11("11");
    
    @NotNull
    private final String myStandard;
    
    private CMakeRecognizedCLanguageStandard(final String myStandard) {
        if (myStandard == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "standard", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedCLanguageStandard", "<init>"));
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedCLanguageStandard", "getStandard"));
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
            string = "C" + this.myStandard;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedCLanguageStandard", "getDisplayString"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "displayString", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedCLanguageStandard", "fromDisplayString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String trimStart;
        try {
            trimStart = StringUtil.trimStart(s, "C");
            if (trimStart == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/completion/CMakeRecognizedCLanguageStandard", "fromDisplayString"));
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
