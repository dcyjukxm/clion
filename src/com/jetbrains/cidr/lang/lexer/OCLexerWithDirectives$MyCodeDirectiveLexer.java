// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;

protected static class MyCodeDirectiveLexer extends OCLexer
{
    private int myInitialState;
    
    public MyCodeDirectiveLexer(@NotNull final OCLanguageKind ocLanguageKind, final boolean b, final boolean b2, final boolean b3, final boolean b4, final int myInitialState) {
        if (ocLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/lexer/OCLexerWithDirectives$MyCodeDirectiveLexer", "<init>"));
        }
        super(ocLanguageKind, b, b2, b3, b4);
        this.myInitialState = myInitialState;
    }
    
    public void start(@NotNull final CharSequence charSequence, final int n, final int n2, final int n3) {
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buffer", "com/jetbrains/cidr/lang/lexer/OCLexerWithDirectives$MyCodeDirectiveLexer", "start"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        super.start(charSequence, n, n2, this.myInitialState);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
