// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class OCFloatLiteral implements OCLiteral
{
    @NotNull
    public final String contents;
    
    public OCFloatLiteral(@NotNull final String contents) {
        if (contents == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contents", "com/jetbrains/cidr/lang/util/OCFloatLiteral", "<init>"));
        }
        this.contents = contents;
    }
    
    @NotNull
    @Override
    public OCType getType(@Nullable final PsiFile psiFile, final boolean b) {
        OCRealType literalType;
        try {
            literalType = OCRealType.literalType(this.contents);
            if (literalType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCFloatLiteral", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return literalType;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
