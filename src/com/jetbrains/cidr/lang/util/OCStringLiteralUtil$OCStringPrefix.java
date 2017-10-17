// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.types.OCIntType;
import org.jetbrains.annotations.NotNull;

public enum OCStringPrefix
{
    PREFIX_EMPTY("", false, OCEncoding.NONE, OCIntType.CHAR, OCIntType.CHAR_CONST), 
    PREFIX_R("R", true, OCEncoding.NONE, OCIntType.CHAR, OCIntType.CHAR_CONST), 
    PREFIX_L("L", false, OCEncoding.WCHAR, OCIntType.WCHAR, OCIntType.WCHAR_CONST), 
    PREFIX_LR("LR", true, OCEncoding.WCHAR, OCIntType.WCHAR, OCIntType.WCHAR_CONST), 
    PREFIX_u8("u8", false, OCEncoding.UTF8, OCIntType.CHAR, OCIntType.CHAR_CONST), 
    PREFIX_u8R("u8R", true, OCEncoding.UTF8, OCIntType.CHAR, OCIntType.CHAR_CONST), 
    PREFIX_u("u", false, OCEncoding.UTF16, OCIntType.CHAR16, OCIntType.CHAR16_CONST), 
    PREFIX_uR("uR", true, OCEncoding.UTF16, OCIntType.CHAR16, OCIntType.CHAR16_CONST), 
    PREFIX_U("U", false, OCEncoding.UTF32, OCIntType.CHAR32, OCIntType.CHAR32_CONST), 
    PREFIX_UR("UR", true, OCEncoding.UTF32, OCIntType.CHAR32, OCIntType.CHAR32_CONST);
    
    @NotNull
    public final String prefix;
    public final boolean isRaw;
    @NotNull
    public final OCEncoding encoding;
    @NotNull
    public final OCIntType charTypePlainC;
    @NotNull
    public final OCIntType charTypeCpp;
    
    private OCStringPrefix(@NotNull final String prefix, @NotNull final boolean isRaw, @NotNull final OCEncoding encoding, final OCIntType charTypePlainC, final OCIntType charTypeCpp) {
        if (prefix == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix", "<init>"));
        }
        if (encoding == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix", "<init>"));
        }
        if (charTypePlainC == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "charTypePlainC", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix", "<init>"));
        }
        if (charTypeCpp == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "charTypeCpp", "com/jetbrains/cidr/lang/util/OCStringLiteralUtil$OCStringPrefix", "<init>"));
        }
        super(s, n);
        this.prefix = prefix;
        this.isRaw = isRaw;
        this.encoding = encoding;
        this.charTypePlainC = charTypePlainC;
        this.charTypeCpp = charTypeCpp;
    }
    
    public static OCStringPrefix setRaw(final OCStringPrefix ocStringPrefix, final boolean b) {
        Label_0078: {
            Label_0052: {
                try {
                    if (!b) {
                        break Label_0078;
                    }
                    final int[] array = OCStringLiteralUtil$1.$SwitchMap$com$jetbrains$cidr$lang$util$OCStringLiteralUtil$OCStringPrefix;
                    final OCStringPrefix ocStringPrefix2 = ocStringPrefix;
                    final int n = ocStringPrefix2.ordinal();
                    final int n2 = array[n];
                    switch (n2) {
                        case 1: {
                            break Label_0052;
                            break Label_0052;
                        }
                        case 2: {
                            return OCStringPrefix.PREFIX_LR;
                        }
                        case 3: {
                            return OCStringPrefix.PREFIX_u8R;
                        }
                        case 4: {
                            return OCStringPrefix.PREFIX_uR;
                        }
                        case 5: {
                            return OCStringPrefix.PREFIX_UR;
                        }
                        default: {
                            return ocStringPrefix;
                        }
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final int[] array = OCStringLiteralUtil$1.$SwitchMap$com$jetbrains$cidr$lang$util$OCStringLiteralUtil$OCStringPrefix;
                    final OCStringPrefix ocStringPrefix2 = ocStringPrefix;
                    final int n = ocStringPrefix2.ordinal();
                    final int n2 = array[n];
                    switch (n2) {
                        case 1: {
                            return OCStringPrefix.PREFIX_R;
                        }
                        case 2: {
                            break;
                        }
                        case 3: {
                            return OCStringPrefix.PREFIX_u8R;
                        }
                        case 4: {
                            return OCStringPrefix.PREFIX_uR;
                        }
                        case 5: {
                            return OCStringPrefix.PREFIX_UR;
                        }
                        default: {
                            return ocStringPrefix;
                        }
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return OCStringPrefix.PREFIX_LR;
            try {
                switch (ocStringPrefix) {
                    case PREFIX_R: {
                        return OCStringPrefix.PREFIX_EMPTY;
                    }
                    case PREFIX_LR: {
                        break;
                    }
                    case PREFIX_u8R: {
                        return OCStringPrefix.PREFIX_u8;
                    }
                    case PREFIX_uR: {
                        return OCStringPrefix.PREFIX_u;
                    }
                    case PREFIX_UR: {
                        return OCStringPrefix.PREFIX_U;
                    }
                    default: {
                        return ocStringPrefix;
                    }
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return OCStringPrefix.PREFIX_L;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
