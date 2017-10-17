// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCInternator;

static final class OCNamesInternary$1 extends OCInternator<String> {
    @NotNull
    @Override
    protected String valueToStore(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/lang/symbols/symtable/OCNamesInternary$1", "valueToStore"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String s2;
        try {
            s2 = new String(s);
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/OCNamesInternary$1", "valueToStore"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return s2;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}