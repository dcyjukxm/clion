// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.lexer.OCLexer;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.refactoring.NamesValidator;

public class OCNamesValidator implements NamesValidator
{
    public boolean isKeyword(@NotNull final String s, final Project project) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/util/OCNamesValidator", "isKeyword"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return isKeyword(s);
    }
    
    public static boolean isKeyword(final String s) {
        return OCTokenTypes.KEYWORDS.contains(a(s, OCLanguageKind.OBJ_CPP));
    }
    
    public boolean isIdentifier(@NotNull final String s, final Project project) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/util/OCNamesValidator", "isIdentifier"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return isIdentifier(s);
    }
    
    public static boolean isIdentifier(final String s) {
        try {
            if (a(s, OCLanguageKind.OBJ_CPP) == OCTokenTypes.IDENTIFIER) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Nullable
    private static IElementType a(final String s, final OCLanguageKind ocLanguageKind) {
        final OCLexer ocLexer = new OCLexer(ocLanguageKind, false, false, false, false);
        ocLexer.start((CharSequence)s);
        final IElementType tokenType = ocLexer.getTokenType();
        try {
            ocLexer.advance();
            if (ocLexer.getTokenType() == null) {
                return tokenType;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
