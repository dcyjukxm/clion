// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

public class OCLexerUtils
{
    public static int indexOf(final CharSequence charSequence, final CharSequence charSequence2) {
        return indexOf(charSequence, charSequence2, 0);
    }
    
    public static int indexOf(final CharSequence charSequence, final CharSequence charSequence2, final int n) {
        return indexOf(charSequence, 0, charSequence.length(), charSequence2, 0, charSequence2.length(), n);
    }
    
    static int indexOf(final CharSequence charSequence, final int n, final int n2, final CharSequence charSequence2, final int n3, final int n4, int n5) {
        if (n5 >= n2) {
            return (n4 == 0) ? n2 : -1;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        if (n4 == 0) {
            return n5;
        }
        final char char1 = charSequence2.charAt(n3);
        for (int n6 = n + (n2 - n4), i = n + n5; i <= n6; ++i) {
            if (charSequence.charAt(i) != char1) {
                while (++i <= n6 && charSequence.charAt(i) != char1) {}
            }
            if (i <= n6) {
                int n7 = i + 1;
                final int n8 = n7 + n4 - 1;
                for (int n9 = n3 + 1; n7 < n8 && charSequence.charAt(n7) == charSequence2.charAt(n9); ++n7, ++n9) {}
                if (n7 == n8) {
                    return i - n;
                }
            }
        }
        return -1;
    }
}
