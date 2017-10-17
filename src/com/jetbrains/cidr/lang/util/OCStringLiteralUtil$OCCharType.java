// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCIntType;

public enum OCCharType
{
    CHAR_LITERAL(OCIntType.INT, OCIntType.CHAR), 
    MULTICHAR_LITERAL(OCIntType.INT), 
    WCHAR_LITERAL(OCIntType.WCHAR), 
    CHAR16_LITERAL(OCIntType.CHAR16), 
    CHAR32_LITERAL(OCIntType.CHAR32);
    
    @NotNull
    public final OCIntType typePlainC;
    @NotNull
    public final OCIntType typeCpp;
    
    private OCCharType(final OCIntType ocIntType) {
        if (ocIntType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCCharType", "<init>"));
        }
        this(ocIntType, ocIntType);
    }
    
    private OCCharType(final OCIntType typePlainC, final OCIntType typeCpp) {
        if (typePlainC == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typePlainC", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCCharType", "<init>"));
        }
        if (typeCpp == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeCpp", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCCharType", "<init>"));
        }
        super(s, n);
        this.typePlainC = typePlainC;
        this.typeCpp = typeCpp;
    }
}
