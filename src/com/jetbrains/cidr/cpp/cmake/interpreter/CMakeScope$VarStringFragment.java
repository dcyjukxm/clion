// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.interpreter;

import org.jetbrains.annotations.NotNull;
import java.util.List;

private static class VarStringFragment extends StringFragment
{
    @NotNull
    private final List<StringFragment> myFragments;
    private final boolean myValid;
    
    public VarStringFragment(@NotNull final String s, @NotNull final List<StringFragment> myFragments, final boolean myValid) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "string", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$VarStringFragment", "<init>"));
        }
        if (myFragments == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fragments", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$VarStringFragment", "<init>"));
        }
        super(s);
        this.myFragments = myFragments;
        this.myValid = myValid;
    }
    
    @Override
    public boolean eval(@NotNull final CMakeScope cMakeScope, @NotNull final StringBuilder sb) {
        try {
            if (cMakeScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$VarStringFragment", "eval"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$VarStringFragment", "eval"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (!this.isValid()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final String evalFragments = CMakeScope.evalFragments(this.myFragments, cMakeScope);
        try {
            if (evalFragments == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final String variableValue = cMakeScope.getVariableValue(evalFragments);
        try {
            if (variableValue == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        sb.append(variableValue);
        return true;
    }
    
    public boolean isValid() {
        return this.myValid;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
