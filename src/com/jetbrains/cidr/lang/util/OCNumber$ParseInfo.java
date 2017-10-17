// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.openapi.util.text.StringUtil;

public static final class ParseInfo
{
    public int radix;
    public String numbers;
    public int countL;
    public int countU;
    public boolean negative;
    
    public ParseInfo() {
        this.radix = 10;
        this.countL = 0;
        this.countU = 0;
        this.negative = false;
    }
    
    public static ParseInfo parse(String substring) throws NumberFormatException {
        final ParseInfo parseInfo = new ParseInfo();
        int i;
        for (i = substring.length() - 1; i >= 0; --i) {
            final char char1 = substring.charAt(i);
            Label_0097: {
                Label_0080: {
                    Label_0061: {
                        Label_0044: {
                            try {
                                if (char1 == 'L') {
                                    break Label_0044;
                                }
                                final char c = char1;
                                final char c2 = 'l';
                                if (c == c2) {
                                    break Label_0044;
                                }
                                break Label_0061;
                            }
                            catch (NumberFormatException ex) {
                                throw b(ex);
                            }
                            try {
                                final char c = char1;
                                final char c2 = 'l';
                                if (c == c2) {
                                    final ParseInfo parseInfo2 = parseInfo;
                                    ++parseInfo2.countL;
                                    continue;
                                }
                            }
                            catch (NumberFormatException ex2) {
                                throw b(ex2);
                            }
                        }
                        try {
                            if (char1 == 'U') {
                                break Label_0080;
                            }
                            final char c3 = char1;
                            final char c4 = 'u';
                            if (c3 == c4) {
                                break Label_0080;
                            }
                            break Label_0097;
                        }
                        catch (NumberFormatException ex3) {
                            throw b(ex3);
                        }
                    }
                    try {
                        final char c3 = char1;
                        final char c4 = 'u';
                        if (c3 == c4) {
                            final ParseInfo parseInfo3 = parseInfo;
                            ++parseInfo3.countU;
                            continue;
                        }
                    }
                    catch (NumberFormatException ex4) {
                        throw b(ex4);
                    }
                }
                try {
                    if (StringUtil.isHexDigit(char1)) {
                        ++i;
                        break;
                    }
                }
                catch (NumberFormatException ex5) {
                    throw b(ex5);
                }
            }
        }
        substring = substring.substring(0, i);
        int n = 0;
        try {
            if (substring.length() == 0) {
                throw new NumberFormatException("Zero length string");
            }
        }
        catch (NumberFormatException ex6) {
            throw b(ex6);
        }
        final char char2 = substring.charAt(0);
        Label_0351: {
            Label_0324: {
                Label_0308: {
                    Label_0281: {
                        Label_0266: {
                            Label_0239: {
                                Label_0223: {
                                    Label_0196: {
                                        try {
                                            if (char2 == '-') {
                                                parseInfo.negative = true;
                                                ++n;
                                                break Label_0196;
                                            }
                                        }
                                        catch (NumberFormatException ex7) {
                                            throw b(ex7);
                                        }
                                        try {
                                            if (char2 == '+') {
                                                ++n;
                                            }
                                        }
                                        catch (NumberFormatException ex8) {
                                            throw b(ex8);
                                        }
                                        try {
                                            if (substring.startsWith("0x", n)) {
                                                break Label_0223;
                                            }
                                            final String s = substring;
                                            final String s2 = "0X";
                                            final int n2 = n;
                                            final boolean b = s.startsWith(s2, n2);
                                            if (b) {
                                                break Label_0223;
                                            }
                                            break Label_0239;
                                        }
                                        catch (NumberFormatException ex9) {
                                            throw b(ex9);
                                        }
                                    }
                                    try {
                                        final String s = substring;
                                        final String s2 = "0X";
                                        final int n2 = n;
                                        final boolean b = s.startsWith(s2, n2);
                                        if (b) {
                                            n += 2;
                                            parseInfo.radix = 16;
                                            break Label_0324;
                                        }
                                    }
                                    catch (NumberFormatException ex10) {
                                        throw b(ex10);
                                    }
                                }
                                try {
                                    if (substring.startsWith("0b", n)) {
                                        break Label_0266;
                                    }
                                    final String s3 = substring;
                                    final String s4 = "0B";
                                    final int n3 = n;
                                    final boolean b2 = s3.startsWith(s4, n3);
                                    if (b2) {
                                        break Label_0266;
                                    }
                                    break Label_0281;
                                }
                                catch (NumberFormatException ex11) {
                                    throw b(ex11);
                                }
                            }
                            try {
                                final String s3 = substring;
                                final String s4 = "0B";
                                final int n3 = n;
                                final boolean b2 = s3.startsWith(s4, n3);
                                if (b2) {
                                    n += 2;
                                    parseInfo.radix = 2;
                                    break Label_0324;
                                }
                            }
                            catch (NumberFormatException ex12) {
                                throw b(ex12);
                            }
                        }
                        try {
                            if (!substring.startsWith("0", n)) {
                                break Label_0324;
                            }
                            final String s5 = substring;
                            final int n4 = s5.length();
                            final int n5 = 1;
                            final int n6 = n;
                            final int n7 = n5 + n6;
                            if (n4 > n7) {
                                break Label_0308;
                            }
                            break Label_0324;
                        }
                        catch (NumberFormatException ex13) {
                            throw b(ex13);
                        }
                    }
                    try {
                        final String s5 = substring;
                        final int n4 = s5.length();
                        final int n5 = 1;
                        final int n6 = n;
                        final int n7 = n5 + n6;
                        if (n4 > n7) {
                            ++n;
                            parseInfo.radix = 8;
                        }
                    }
                    catch (NumberFormatException ex14) {
                        throw b(ex14);
                    }
                }
                try {
                    if (substring.startsWith("-", n)) {
                        break Label_0351;
                    }
                    final String s6 = substring;
                    final String s7 = "+";
                    final int n8 = n;
                    final boolean b3 = s6.startsWith(s7, n8);
                    if (b3) {
                        break Label_0351;
                    }
                    break Label_0351;
                }
                catch (NumberFormatException ex15) {
                    throw b(ex15);
                }
            }
            try {
                final String s6 = substring;
                final String s7 = "+";
                final int n8 = n;
                final boolean b3 = s6.startsWith(s7, n8);
                if (b3) {
                    throw new NumberFormatException("Sign character in wrong position");
                }
            }
            catch (NumberFormatException ex16) {
                throw b(ex16);
            }
        }
        parseInfo.numbers = StringUtil.replace(substring.substring(n), "'", "");
        return parseInfo;
    }
    
    private static NumberFormatException b(final NumberFormatException ex) {
        return ex;
    }
}
