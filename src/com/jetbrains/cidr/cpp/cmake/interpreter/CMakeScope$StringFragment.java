// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.interpreter;

import org.jetbrains.annotations.NotNull;

private abstract static class StringFragment
{
    @NotNull
    private final String myString;
    
    public StringFragment(@NotNull final String myString) {
        if (myString == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "string", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$StringFragment", "<init>"));
        }
        this.myString = myString;
    }
    
    public abstract boolean eval(@NotNull final CMakeScope p0, @NotNull final StringBuilder p1);
    
    @NotNull
    protected String getFragmentString() {
        String myString;
        try {
            myString = this.myString;
            if (myString == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$StringFragment", "getFragmentString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myString;
    }
    
    abstract boolean isValid();
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
