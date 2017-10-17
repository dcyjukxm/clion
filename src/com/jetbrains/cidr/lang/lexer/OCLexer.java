// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

import com.intellij.lexer.FlexLexer;
import java.io.Reader;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.CLanguageKind;
import com.intellij.lexer.Lexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.lexer.FlexAdapter;

public class OCLexer extends FlexAdapter
{
    private static final IElementType CHECK_JIT_ADAPTATION;
    public static final int INITIAL = 0;
    public static final int INITIAL_INSIDE_LINE = 1024;
    public static final int DIRECTIVE_BODY_STATE = 8;
    public static final int PRAGMA_BODY_STATE = 12;
    public static final int INCLUDE_BODY_STATE = 10;
    public static final int MAX_RAW_STRING_PREFIX_LENGTH = 16;
    
    @NotNull
    public static Lexer createRawLexerForPreprocessor() {
        OCLexer ocLexer;
        try {
            ocLexer = new OCLexer(CLanguageKind.OBJ_CPP, false, false, false, false);
            if (ocLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/lexer/OCLexer", "createRawLexerForPreprocessor"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Lexer)ocLexer;
    }
    
    public OCLexer(@NotNull final OCLanguageKind ocLanguageKind, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (ocLanguageKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/lexer/OCLexer", "<init>"));
        }
        super((FlexLexer)new _OCLexer(null) {
            @Override
            public void reset(final CharSequence charSequence, final int n, final int n2, final int n3) {
                final boolean b = n3 == 1024;
                super.reset(charSequence, n, n2, b ? 0 : n3);
                this.myLogicalLineStartOffset = (b ? -1 : n);
            }
        });
        final _OCLexer ocLexer = (_OCLexer)this.getFlex();
        try {
            if (ocLanguageKind.isCpp()) {
                ocLexer.allowCPPKeywords();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocLanguageKind.isObjC()) {
                ocLexer.allowObjCKeywords();
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (b2) {
                ocLexer.allowNullabilityKeywords();
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (b3) {
                ocLexer.allowGccAutoType();
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        try {
            if (b4) {
                ocLexer.allowAvailabilityExpression();
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        try {
            if (b) {
                ocLexer.forHighlighting();
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
    }
    
    static {
        CHECK_JIT_ADAPTATION = _OCLexer.FLEX_ERROR;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
