// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class OCIntegerLiteral implements OCLiteral
{
    @NotNull
    public final String contents;
    
    public OCIntegerLiteral(@NotNull final String contents) {
        if (contents == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contents", "com/jetbrains/cidr/lang/util/OCIntegerLiteral", "<init>"));
        }
        this.contents = contents;
    }
    
    @NotNull
    @Override
    public OCType getType(@Nullable final PsiFile psiFile, final boolean b) {
        OCIntType literalType;
        try {
            literalType = OCIntType.literalType(this.contents, (PsiElement)psiFile);
            if (literalType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCIntegerLiteral", "getType"));
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
