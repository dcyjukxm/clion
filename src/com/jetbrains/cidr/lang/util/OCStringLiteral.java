// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

public class OCStringLiteral implements OCLiteral
{
    public static final OCStringLiteral BAD_LITERAL;
    @NotNull
    public final OCStringLiteralUtil.OCStringPrefix prefix;
    @NotNull
    public final String contents;
    
    public OCStringLiteral(@NotNull final OCStringLiteralUtil.OCStringPrefix prefix, @NotNull final String contents) {
        if (prefix == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/lang/util/OCStringLiteral", "<init>"));
        }
        if (contents == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contents", "com/jetbrains/cidr/lang/util/OCStringLiteral", "<init>"));
        }
        this.prefix = prefix;
        this.contents = contents;
    }
    
    public String getContents(final boolean b) {
        try {
            if (this.prefix.isRaw != b) {
                return this.contents;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (b) {
                return StringUtil.escapeStringCharacters(this.contents);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return OCStringLiteralUtil.unescapeAnsiStringCharacters(this.contents);
    }
    
    @NotNull
    public OCIntType getElementType(final boolean b) {
        OCIntType ocIntType = null;
        Label_0025: {
            try {
                if (b) {
                    final OCIntType ocIntType2;
                    ocIntType = (ocIntType2 = this.prefix.charTypePlainC);
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCIntType ocIntType2;
            ocIntType = (ocIntType2 = this.prefix.charTypeCpp);
            try {
                if (ocIntType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteral", "getElementType"));
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
        OCArrayType to;
        try {
            to = OCArrayType.to(this.getElementType(b), this.getContentLengthInCharacters());
            if (to == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCStringLiteral", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return to;
    }
    
    public int getContentLengthInCharacters() {
        final String contents = this.getContents(false);
        if (this.prefix.encoding == OCStringLiteralUtil.OCEncoding.UTF8) {
            return this.prefix.encoding.getBytes(contents).length + 1;
        }
        return contents.length() + 1;
    }
    
    @Override
    public String toString() {
        return "OCStringLiteral{prefix=" + this.prefix + ", contents='" + this.contents + '\'' + '}';
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final OCStringLiteral ocStringLiteral = this;
                final Class<? extends OCStringLiteral> clazz = ocStringLiteral.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCStringLiteral ocStringLiteral = this;
                final Class<? extends OCStringLiteral> clazz = ocStringLiteral.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCStringLiteral ocStringLiteral2 = (OCStringLiteral)o;
        try {
            if (this.prefix != ocStringLiteral2.prefix) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.contents.equals(ocStringLiteral2.contents)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * this.prefix.hashCode() + this.contents.hashCode();
    }
    
    static {
        BAD_LITERAL = new OCStringLiteral(OCStringLiteralUtil.OCStringPrefix.PREFIX_EMPTY, "");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
