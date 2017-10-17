// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.interpreter;

import org.jetbrains.annotations.NotNull;

private static class PlainStringFragment extends StringFragment
{
    public PlainStringFragment(final String s) {
        super(s);
    }
    
    @Override
    public boolean eval(@NotNull final CMakeScope cMakeScope, @NotNull final StringBuilder sb) {
        try {
            if (cMakeScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$PlainStringFragment", "eval"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$PlainStringFragment", "eval"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        sb.append(this.getFragmentString());
        return true;
    }
    
    @Override
    boolean isValid() {
        return true;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
