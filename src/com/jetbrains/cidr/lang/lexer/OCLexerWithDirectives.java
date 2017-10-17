// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.Lexer;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.CLanguageKind;
import com.intellij.lexer.LayeredLexer;

public class OCLexerWithDirectives extends LayeredLexer
{
    @NotNull
    public static OCLexerWithDirectives createDefault() {
        OCLexerWithDirectives ocLexerWithDirectives;
        try {
            ocLexerWithDirectives = new OCLexerWithDirectives(CLanguageKind.OBJ_CPP, false, false, false, false);
            if (ocLexerWithDirectives == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/lexer/OCLexerWithDirectives", "createDefault"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocLexerWithDirectives;
    }
    
    public OCLexerWithDirectives(@NotNull final OCLanguageKind ocLanguageKind, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (ocLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/lexer/OCLexerWithDirectives", "<init>"));
        }
        super((Lexer)new OCLexer(ocLanguageKind, b, b2, b3, b4));
        this.registerLayer((Lexer)new MyCodeDirectiveLexer(ocLanguageKind, b, b2, b3, b4, 8), new IElementType[] { OCTokenTypes.DIRECTIVE_CONTENT });
        this.registerLayer((Lexer)new MyCodeDirectiveLexer(ocLanguageKind, b, b2, b3, b4, 12), new IElementType[] { OCTokenTypes.PRAGMA_DIRECTIVE_CONTENT });
        this.registerLayer((Lexer)new MyCodeDirectiveLexer(ocLanguageKind, b, b2, b3, b4, 10), new IElementType[] { OCTokenTypes.INCLUDE_DIRECTIVE_CONTENT });
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
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
}
