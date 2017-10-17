// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCIntType;
import org.jetbrains.annotations.NotNull;

public class OCCharLiteral implements OCLiteral
{
    public static final OCCharLiteral BAD_LITERAL;
    @NotNull
    public final OCStringLiteralUtil.OCCharType charType;
    @NotNull
    public final String prefix;
    @NotNull
    public final String contents;
    
    public OCCharLiteral(@NotNull final OCStringLiteralUtil.OCCharType charType, @NotNull final String prefix, @NotNull final String contents) {
        if (charType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "charType", "com/jetbrains/cidr/lang/util/OCCharLiteral", "<init>"));
        }
        if (prefix == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/lang/util/OCCharLiteral", "<init>"));
        }
        if (contents == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contents", "com/jetbrains/cidr/lang/util/OCCharLiteral", "<init>"));
        }
        this.charType = charType;
        this.prefix = prefix;
        this.contents = contents;
    }
    
    public String getContents(final boolean b) {
        try {
            if (b) {
                return this.contents;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCStringLiteralUtil.unescapeAnsiStringCharacters(this.contents);
    }
    
    @NotNull
    public OCIntType getType(final boolean b) {
        OCIntType ocIntType = null;
        Label_0025: {
            try {
                if (b) {
                    final OCIntType ocIntType2;
                    ocIntType = (ocIntType2 = this.charType.typePlainC);
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCIntType ocIntType2;
            ocIntType = (ocIntType2 = this.charType.typeCpp);
            try {
                if (ocIntType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCharLiteral", "getType"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return ocIntType;
    }
    
    @NotNull
    @Override
    public OCType getType(@Nullable final PsiFile psiFile, final boolean b) {
        OCIntType type;
        try {
            type = this.getType(b);
            if (type == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCharLiteral", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return type;
    }
    
    static {
        BAD_LITERAL = new OCCharLiteral(OCStringLiteralUtil.OCCharType.CHAR_LITERAL, "", "");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
