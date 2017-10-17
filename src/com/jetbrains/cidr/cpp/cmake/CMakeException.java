// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import org.jetbrains.annotations.NotNull;
import java.io.File;

public class CMakeException extends Exception
{
    public CMakeException(final Throwable t) {
        super(t);
    }
    
    public CMakeException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public CMakeException(final String s) {
        super(s);
    }
    
    public static CMakeException cannotReadFile(@NotNull final File file, @NotNull final Throwable t) {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/CMakeException", "cannotReadFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cause", "com/jetbrains/cidr/cpp/cmake/CMakeException", "cannotReadFile"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return new CMakeException("Cannot read " + file, t);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
