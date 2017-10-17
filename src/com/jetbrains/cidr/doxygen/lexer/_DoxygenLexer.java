// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.lexer;

import com.intellij.psi.TokenType;
import com.jetbrains.cidr.doxygen.psi.DxTypes;
import com.intellij.psi.tree.IElementType;
import java.io.IOException;
import java.io.Reader;
import com.intellij.lexer.FlexLexer;

public class _DoxygenLexer implements FlexLexer
{
    public static final int YYEOF = -1;
    private static final int ZZ_BUFFERSIZE = 16384;
    public static final int YYINITIAL = 0;
    public static final int P_EOL_COMMENT = 2;
    public static final int P_BLK_COMMENT = 4;
    public static final int P_PARAM_LIST = 6;
    public static final int P_SEPARATOR = 8;
    private static final int[] ZZ_LEXSTATE;
    static final char[] ZZ_CMAP_Z;
    static final char[] ZZ_CMAP_Y;
    static final char[] ZZ_CMAP_A;
    private static final int[] ZZ_ACTION;
    private static final String ZZ_ACTION_PACKED_0 = "\u0005\u0000\u0001\u0001\u0003\u0002\u0007\u0001\u0003\u0003\u0001\u0004\u0001\u0001\u0005\u0005\u0001\u0006\u0001\u0002\u0001\u0007\u0002\u0000\u0001\b\u0002\u0000\u0002\u0002\u0001\b\u0001\t\u0004\u0001\u0001\t\u0001\u0000\u0002\u0002\u0001\b\u0001\u0000\u0001\u0002\u0001\u0007\u0001\n\u0001\u000b\u0001\f\u0001\u0001\u0004\t\u0001\u0003\u0001\r\u0001\n\u0001\u000b\u0003\t\u0001\r\u0003\t\u0001\u0000\u0001\u000e\u0002\t";
    private static final int[] ZZ_ROWMAP;
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000\"\u0000D\u0000f\u0000\u0088\u0000ª\u0000\u00cc\u0000\u00ee\u0000\u0110\u0000\u0132\u0000\u0154\u0000\u0176\u0000\u0198\u0000\u01ba\u0000\u01dc\u0000\u01fe\u0000\u0220\u0000\u0242\u0000\u0264\u0000\u0286\u0000\u02a8\u0000ª\u0000\u00cc\u0000\u00ee\u0000\u0154\u0000\u0176\u0000ª\u0000\u02ca\u0000\u02ec\u0000\u030e\u0000\u0330\u0000ª\u0000\u0352\u0000\u0198\u0000\u0374\u0000\u01ba\u0000\u0198\u0000\u0396\u0000\u03b8\u0000\u03da\u0000\u03fc\u0000\u0396\u0000\u041e\u0000\u0220\u0000\u0440\u0000\u0242\u0000\u0220\u0000\u0462\u0000\u0484\u0000ª\u0000\u04a6\u0000\u04c8\u0000\u0352\u0000\u0374\u0000\u04ea\u0000\u050c\u0000\u03fc\u0000\u052e\u0000\u0440\u0000\u0550\u0000ª\u0000ª\u0000\u0572\u0000\u0594\u0000\u05b6\u0000\u05d8\u0000\u05fa\u0000\u061c\u0000\u063e\u0000\u0660\u0000\u0396\u0000\u0682\u0000\u0660";
    private static final int[] ZZ_TRANS;
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0006\u0003\u0007\u0001\b\u0001\t\u0002\u0006\u0001\u0007\u0001\n\u0002\u0006\u0001\u000b\u0010\u0006\u0001\f\u0003\u0006\u0001\b\u0001\r\u0001\u000e\u0002\u0007\u0001\b\u0001\t\u0002\r\u0001\u000e\u0003\r\u0001\u000f\u0001\u0010\u000f\r\u0001\f\u0003\r\u0001\b\u0001\u0011\u0001\u0012\u0002\u0007\u0001\b\u0001\t\u0002\u0011\u0001\u0012\u0003\u0011\u0001\u0013\u0001\u0010\u000f\u0011\u0001\f\u0003\u0011\u0001\b\u0001\u0006\u0003\u0007\u0001\b\u0001\t\u0001\u0014\u0001\u0006\u0001\u0007\u0003\u0006\u0001\u000b\u0001\u0010\u0004\u0014\u0002\u0006\t\u0014\u0001\f\u0002\u0006\u0001\u0015\u0001\b\u0001\u0016\u0001\u0017\u0002\u0007\u0001\u0018\u0001\t\u0002\u0016\u0001\u0017\u0003\u0016\u0001\u0019\u0010\u0016\u0001\u001a\u0001\u0016\u0001\u001b\u0001\u0016\u0001\b#\u0000\u0003\u0007\u0001\u001c\u0001\u0007\u0002\u0000\u0001\u0007\u0003\u0000\u0001\u001d\u0014\u0000\u0001\u001c\u0001\u0000\u0003\u0007\u0001\b\u0001\t\u0002\u0000\u0001\u0007\u0018\u0000\u0001\b\u0001\u0000\u0003\u0007\u0002\t\u0002\u0000\u0001\u0007\u0003\u0000\u0001\u001d\u0014\u0000\u0001\t\t\u0000\u0001\u001e\u0002\u0000\u0001\u001f\u001e\u0000\u0001 \u0018\u0000\u001d!\u0001\u0000\u0004!\u0002\r\u0002\u0000\u0002\"\u0007\r\u0001\u0000\u0013\r\u0001\"\u0001\r\u0001\u000e\u0002\u0007\u0001#\u0001$\u0002\r\u0001\u000e\u0004\r\u0001\u0000\u0013\r\u0001#\u0002\r\u0002\u0000\u0002\"\u0003\r\u0001%\u0003\r\u0001\u0000\u0013\r\u0001\"\u0006\u0000\u0001&\u0007\u0000\u0001'\u0002&\u0001(\u0002\u0000\u0001)\u0001*\u0002&\u0001+\u0003&\u0001*\u0005\u0000\u0002\u0011\u0002\u0000\u0002,\u0007\u0011\u0001\u0000\u0013\u0011\u0001,\u0001\u0011\u0001\u0012\u0002\u0007\u0001-\u0001.\u0002\u0011\u0001\u0012\u0004\u0011\u0001\u0000\u0013\u0011\u0001-\u0002\u0011\u0002\u0000\u0002,\u0003\u0011\u0001/\u0003\u0011\u0001\u0000\u0013\u0011\u0001,\u0006\u0000\u0003\u0014\u0005\u0000\u0004\u0014\u0002\u0000\t\u0014%\u0000\u00010\u0002\u0000\u00031\u0002\u001c\u0002\u0000\u00011\u0003\u0000\u0001\u001d\u0014\u0000\u0001\u001c\t\u0000\u00012\u0002\u0000\u0001\u001d\u001e\u0000\u00023!\u0000\u00014\u0001\u0000\u00014\u0015\u0000\u001d!\u0001\u0000\u00015\u0003!\u0001\r\u00016\u00021\u0002#\u0002\r\u00016\u0004\r\u0001\u0000\u0013\r\u0001#\u0006\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\t&\u000b\u0000\u0003&\u0005\u0000\u0001&\u00017\u0002&\u0002\u0000\t&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0003&\u0001*\u0005&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0001&\u00018\u0007&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u00019\u0004&\u0001:\u0003&\u0005\u0000\u0001\u0011\u0001;\u00021\u0002-\u0002\u0011\u0001;\u0004\u0011\u0001\u0000\u0013\u0011\u0001- \u0000\u0001<\u0002\u0000\u00051\u0002\u0000\u00011\u0018\u0000\u00011\u000b\u0000\u0001=!\u0000\u0001>\u001c\u0000\u0003&\u0005\u0000\u0002&\u0001?\u0001&\u0002\u0000\t&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0002&\u0001@\u0006&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0002&\u0001A\u0006&\u000b\u0000\u0001B\u0007\u0000\u0004B\u0002\u0000\tB\u000b\u0000\u0003&\u0005\u0000\u0003&\u0001C\u0002\u0000\t&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0001&\u0001D\u0007&\u000b\u0000\u0003&\u0005\u0000\u0001&\u0001E\u0002&\u0002\u0000\t&\u000b\u0000\u0003B\u0005\u0000\u0004B\u0002\u0000\tB\u000b\u0000\u0003&\u0005\u0000\u0004&\u0001F\u0001\u0000\t&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0003&\u0001G\u0005&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0006&\u0001H\u0002&\u0005\u0000\u0002F\u0001\u0000\u0001F\u0002\u0000\rF\u0001I\rF\u0007\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0007&\u0001G\u0001&\u0005\u0000";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG;
    private static final int[] ZZ_ATTRIBUTE;
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0005\u0000\u0001\t\u000f\u0001\u0001\t\u0004\u0001\u0001\t\u0002\u0001\u0002\u0000\u0001\t\u0002\u0000\t\u0001\u0001\u0000\u0003\u0001\u0001\u0000\u0001\u0001\u0001\t\n\u0001\u0002\t\u0007\u0001\u0001\u0000\u0003\u0001";
    private Reader zzReader;
    private int zzState;
    private int zzLexicalState;
    private CharSequence zzBuffer;
    private int zzMarkedPos;
    private int zzCurrentPos;
    private int zzStartRead;
    private int zzEndRead;
    private boolean zzAtBOL;
    private boolean zzAtEOF;
    private boolean zzEOFDone;
    private int initCommentState;
    
    public static int ZZ_CMAP(final int n) {
        return _DoxygenLexer.ZZ_CMAP_A[_DoxygenLexer.ZZ_CMAP_Y[_DoxygenLexer.ZZ_CMAP_Z[n >> 9] << 6 | (n >> 3 & 0x3F)] << 3 | (n & 0x7)];
    }
    
    private static int[] b() {
        final int[] array = new int[73];
        b("\u0005\u0000\u0001\u0001\u0003\u0002\u0007\u0001\u0003\u0003\u0001\u0004\u0001\u0001\u0005\u0005\u0001\u0006\u0001\u0002\u0001\u0007\u0002\u0000\u0001\b\u0002\u0000\u0002\u0002\u0001\b\u0001\t\u0004\u0001\u0001\t\u0001\u0000\u0002\u0002\u0001\b\u0001\u0000\u0001\u0002\u0001\u0007\u0001\n\u0001\u000b\u0001\f\u0001\u0001\u0004\t\u0001\u0003\u0001\r\u0001\n\u0001\u000b\u0003\t\u0001\r\u0003\t\u0001\u0000\u0001\u000e\u0002\t", 0, array);
        return array;
    }
    
    private static int b(final String s, final int n, final int[] array) {
        int i = 0;
        int n2 = n;
        while (i < s.length()) {
            int char1 = s.charAt(i++);
            final char char2 = s.charAt(i++);
            try {
                do {
                    array[n2++] = char2;
                } while (--char1 > 0);
                continue;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw b(ex);
            }
            break;
        }
        return n2;
    }
    
    private static int[] a() {
        final int[] array = new int[73];
        c("\u0000\u0000\u0000\"\u0000D\u0000f\u0000\u0088\u0000ª\u0000\u00cc\u0000\u00ee\u0000\u0110\u0000\u0132\u0000\u0154\u0000\u0176\u0000\u0198\u0000\u01ba\u0000\u01dc\u0000\u01fe\u0000\u0220\u0000\u0242\u0000\u0264\u0000\u0286\u0000\u02a8\u0000ª\u0000\u00cc\u0000\u00ee\u0000\u0154\u0000\u0176\u0000ª\u0000\u02ca\u0000\u02ec\u0000\u030e\u0000\u0330\u0000ª\u0000\u0352\u0000\u0198\u0000\u0374\u0000\u01ba\u0000\u0198\u0000\u0396\u0000\u03b8\u0000\u03da\u0000\u03fc\u0000\u0396\u0000\u041e\u0000\u0220\u0000\u0440\u0000\u0242\u0000\u0220\u0000\u0462\u0000\u0484\u0000ª\u0000\u04a6\u0000\u04c8\u0000\u0352\u0000\u0374\u0000\u04ea\u0000\u050c\u0000\u03fc\u0000\u052e\u0000\u0440\u0000\u0550\u0000ª\u0000ª\u0000\u0572\u0000\u0594\u0000\u05b6\u0000\u05d8\u0000\u05fa\u0000\u061c\u0000\u063e\u0000\u0660\u0000\u0396\u0000\u0682\u0000\u0660", 0, array);
        return array;
    }
    
    private static int c(final String s, final int n, final int[] array) {
        int i;
        int n2;
        for (i = 0, n2 = n; i < s.length(); array[n2++] = (s.charAt(i++) << 16 | s.charAt(i++))) {}
        return n2;
    }
    
    private static int[] c() {
        final int[] array = new int[1700];
        a("\u0001\u0006\u0003\u0007\u0001\b\u0001\t\u0002\u0006\u0001\u0007\u0001\n\u0002\u0006\u0001\u000b\u0010\u0006\u0001\f\u0003\u0006\u0001\b\u0001\r\u0001\u000e\u0002\u0007\u0001\b\u0001\t\u0002\r\u0001\u000e\u0003\r\u0001\u000f\u0001\u0010\u000f\r\u0001\f\u0003\r\u0001\b\u0001\u0011\u0001\u0012\u0002\u0007\u0001\b\u0001\t\u0002\u0011\u0001\u0012\u0003\u0011\u0001\u0013\u0001\u0010\u000f\u0011\u0001\f\u0003\u0011\u0001\b\u0001\u0006\u0003\u0007\u0001\b\u0001\t\u0001\u0014\u0001\u0006\u0001\u0007\u0003\u0006\u0001\u000b\u0001\u0010\u0004\u0014\u0002\u0006\t\u0014\u0001\f\u0002\u0006\u0001\u0015\u0001\b\u0001\u0016\u0001\u0017\u0002\u0007\u0001\u0018\u0001\t\u0002\u0016\u0001\u0017\u0003\u0016\u0001\u0019\u0010\u0016\u0001\u001a\u0001\u0016\u0001\u001b\u0001\u0016\u0001\b#\u0000\u0003\u0007\u0001\u001c\u0001\u0007\u0002\u0000\u0001\u0007\u0003\u0000\u0001\u001d\u0014\u0000\u0001\u001c\u0001\u0000\u0003\u0007\u0001\b\u0001\t\u0002\u0000\u0001\u0007\u0018\u0000\u0001\b\u0001\u0000\u0003\u0007\u0002\t\u0002\u0000\u0001\u0007\u0003\u0000\u0001\u001d\u0014\u0000\u0001\t\t\u0000\u0001\u001e\u0002\u0000\u0001\u001f\u001e\u0000\u0001 \u0018\u0000\u001d!\u0001\u0000\u0004!\u0002\r\u0002\u0000\u0002\"\u0007\r\u0001\u0000\u0013\r\u0001\"\u0001\r\u0001\u000e\u0002\u0007\u0001#\u0001$\u0002\r\u0001\u000e\u0004\r\u0001\u0000\u0013\r\u0001#\u0002\r\u0002\u0000\u0002\"\u0003\r\u0001%\u0003\r\u0001\u0000\u0013\r\u0001\"\u0006\u0000\u0001&\u0007\u0000\u0001'\u0002&\u0001(\u0002\u0000\u0001)\u0001*\u0002&\u0001+\u0003&\u0001*\u0005\u0000\u0002\u0011\u0002\u0000\u0002,\u0007\u0011\u0001\u0000\u0013\u0011\u0001,\u0001\u0011\u0001\u0012\u0002\u0007\u0001-\u0001.\u0002\u0011\u0001\u0012\u0004\u0011\u0001\u0000\u0013\u0011\u0001-\u0002\u0011\u0002\u0000\u0002,\u0003\u0011\u0001/\u0003\u0011\u0001\u0000\u0013\u0011\u0001,\u0006\u0000\u0003\u0014\u0005\u0000\u0004\u0014\u0002\u0000\t\u0014%\u0000\u00010\u0002\u0000\u00031\u0002\u001c\u0002\u0000\u00011\u0003\u0000\u0001\u001d\u0014\u0000\u0001\u001c\t\u0000\u00012\u0002\u0000\u0001\u001d\u001e\u0000\u00023!\u0000\u00014\u0001\u0000\u00014\u0015\u0000\u001d!\u0001\u0000\u00015\u0003!\u0001\r\u00016\u00021\u0002#\u0002\r\u00016\u0004\r\u0001\u0000\u0013\r\u0001#\u0006\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\t&\u000b\u0000\u0003&\u0005\u0000\u0001&\u00017\u0002&\u0002\u0000\t&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0003&\u0001*\u0005&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0001&\u00018\u0007&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u00019\u0004&\u0001:\u0003&\u0005\u0000\u0001\u0011\u0001;\u00021\u0002-\u0002\u0011\u0001;\u0004\u0011\u0001\u0000\u0013\u0011\u0001- \u0000\u0001<\u0002\u0000\u00051\u0002\u0000\u00011\u0018\u0000\u00011\u000b\u0000\u0001=!\u0000\u0001>\u001c\u0000\u0003&\u0005\u0000\u0002&\u0001?\u0001&\u0002\u0000\t&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0002&\u0001@\u0006&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0002&\u0001A\u0006&\u000b\u0000\u0001B\u0007\u0000\u0004B\u0002\u0000\tB\u000b\u0000\u0003&\u0005\u0000\u0003&\u0001C\u0002\u0000\t&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0001&\u0001D\u0007&\u000b\u0000\u0003&\u0005\u0000\u0001&\u0001E\u0002&\u0002\u0000\t&\u000b\u0000\u0003B\u0005\u0000\u0004B\u0002\u0000\tB\u000b\u0000\u0003&\u0005\u0000\u0004&\u0001F\u0001\u0000\t&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0003&\u0001G\u0005&\u000b\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0006&\u0001H\u0002&\u0005\u0000\u0002F\u0001\u0000\u0001F\u0002\u0000\rF\u0001I\rF\u0007\u0000\u0003&\u0005\u0000\u0004&\u0002\u0000\u0007&\u0001G\u0001&\u0005\u0000", 0, array);
        return array;
    }
    
    private static int a(final String s, final int n, final int[] array) {
        int i = 0;
        int n2 = n;
        while (i < s.length()) {
            int char1 = s.charAt(i++);
            int char2 = s.charAt(i++);
            --char2;
            try {
                do {
                    array[n2++] = char2;
                } while (--char1 > 0);
                continue;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw b(ex);
            }
            break;
        }
        return n2;
    }
    
    private static int[] d() {
        final int[] array = new int[73];
        d("\u0005\u0000\u0001\t\u000f\u0001\u0001\t\u0004\u0001\u0001\t\u0002\u0001\u0002\u0000\u0001\t\u0002\u0000\t\u0001\u0001\u0000\u0003\u0001\u0001\u0000\u0001\u0001\u0001\t\n\u0001\u0002\t\u0007\u0001\u0001\u0000\u0003\u0001", 0, array);
        return array;
    }
    
    private static int d(final String s, final int n, final int[] array) {
        int i = 0;
        int n2 = n;
        while (i < s.length()) {
            int char1 = s.charAt(i++);
            final char char2 = s.charAt(i++);
            try {
                do {
                    array[n2++] = char2;
                } while (--char1 > 0);
                continue;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw b(ex);
            }
            break;
        }
        return n2;
    }
    
    public _DoxygenLexer(final Reader zzReader) {
        this.zzLexicalState = 0;
        this.zzBuffer = "";
        this.zzAtBOL = true;
        this.zzReader = zzReader;
    }
    
    private static char[] a(final String s) {
        char c = '\0';
        for (int i = 0; i < s.length(); i += 2) {
            c += s.charAt(i);
        }
        final char[] array = new char[c];
        int j = 0;
        int n = 0;
        while (j < s.length()) {
            int char1 = s.charAt(j++);
            final char char2 = s.charAt(j++);
            try {
                do {
                    array[n++] = char2;
                } while (--char1 > 0);
                continue;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                throw b(ex);
            }
            break;
        }
        return array;
    }
    
    public final int getTokenStart() {
        return this.zzStartRead;
    }
    
    public final int getTokenEnd() {
        return this.getTokenStart() + this.yylength();
    }
    
    public void reset(final CharSequence zzBuffer, final int zzCurrentPos, final int zzEndRead, final int n) {
        this.zzBuffer = zzBuffer;
        this.zzStartRead = zzCurrentPos;
        this.zzMarkedPos = zzCurrentPos;
        this.zzCurrentPos = zzCurrentPos;
        this.zzAtEOF = false;
        this.zzAtBOL = true;
        this.zzEndRead = zzEndRead;
        this.yybegin(n);
    }
    
    private boolean e() throws IOException {
        return true;
    }
    
    public final int yystate() {
        return this.zzLexicalState;
    }
    
    public final void yybegin(final int zzLexicalState) {
        this.zzLexicalState = zzLexicalState;
    }
    
    public final CharSequence yytext() {
        return this.zzBuffer.subSequence(this.zzStartRead, this.zzMarkedPos);
    }
    
    public final char yycharat(final int n) {
        return this.zzBuffer.charAt(this.zzStartRead + n);
    }
    
    public final int yylength() {
        return this.zzMarkedPos - this.zzStartRead;
    }
    
    private void a(final int n) {
        String s;
        try {
            s = _DoxygenLexer.ZZ_ERROR_MSG[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            s = _DoxygenLexer.ZZ_ERROR_MSG[0];
        }
        throw new Error(s);
    }
    
    public void yypushback(final int n) {
        try {
            if (n > this.yylength()) {
                this.a(2);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw b(ex);
        }
        this.zzMarkedPos -= n;
    }
    
    public IElementType advance() throws IOException {
        int n = this.zzEndRead;
        CharSequence charSequence = this.zzBuffer;
        final int[] zz_TRANS = _DoxygenLexer.ZZ_TRANS;
        final int[] zz_ROWMAP = _DoxygenLexer.ZZ_ROWMAP;
        final int[] zz_ATTRIBUTE = _DoxygenLexer.ZZ_ATTRIBUTE;
        while (true) {
            int n2 = this.zzMarkedPos;
            int n3 = -1;
            final int n4 = n2;
            this.zzStartRead = n4;
            this.zzCurrentPos = n4;
            int zzCurrentPos = n4;
            this.zzState = _DoxygenLexer.ZZ_LEXSTATE[this.zzLexicalState];
            if ((zz_ATTRIBUTE[this.zzState] & 0x1) == 0x1) {
                n3 = this.zzState;
            }
            int n5;
            while (true) {
                if (zzCurrentPos < n) {
                    n5 = Character.codePointAt(charSequence, zzCurrentPos);
                    zzCurrentPos += Character.charCount(n5);
                }
                else {
                    if (this.zzAtEOF) {
                        n5 = -1;
                        break;
                    }
                    this.zzCurrentPos = zzCurrentPos;
                    this.zzMarkedPos = n2;
                    final boolean e = this.e();
                    final int zzCurrentPos2 = this.zzCurrentPos;
                    n2 = this.zzMarkedPos;
                    charSequence = this.zzBuffer;
                    n = this.zzEndRead;
                    if (e) {
                        n5 = -1;
                        break;
                    }
                    n5 = Character.codePointAt(charSequence, zzCurrentPos2);
                    zzCurrentPos = zzCurrentPos2 + Character.charCount(n5);
                }
                final int zzState = zz_TRANS[zz_ROWMAP[this.zzState] + ZZ_CMAP(n5)];
                try {
                    if (zzState == -1) {
                        break;
                    }
                }
                catch (IOException ex) {
                    throw b(ex);
                }
                this.zzState = zzState;
                final int n6 = zz_ATTRIBUTE[this.zzState];
                if ((n6 & 0x1) == 0x1) {
                    n3 = this.zzState;
                    n2 = zzCurrentPos;
                    try {
                        if ((n6 & 0x8) == 0x8) {
                            break;
                        }
                        continue;
                    }
                    catch (IOException ex2) {
                        throw b(ex2);
                    }
                }
            }
            Label_0696: {
                Label_0644: {
                    Label_0620: {
                        Label_0603: {
                            Label_0588: {
                                Label_0576: {
                                    Label_0540: {
                                        Label_0527: {
                                            Label_0498: {
                                                Label_0462: {
                                                    Label_0709: {
                                                        Label_0511: {
                                                            Label_0319: {
                                                                Label_0302: {
                                                                    Label_0291: {
                                                                        try {
                                                                            this.zzMarkedPos = n2;
                                                                            if (n5 != -1) {
                                                                                break Label_0302;
                                                                            }
                                                                            final _DoxygenLexer doxygenLexer = this;
                                                                            final int n7 = doxygenLexer.zzStartRead;
                                                                            final _DoxygenLexer doxygenLexer2 = this;
                                                                            final int n8 = doxygenLexer2.zzCurrentPos;
                                                                            if (n7 == n8) {
                                                                                break Label_0291;
                                                                            }
                                                                            break Label_0302;
                                                                        }
                                                                        catch (IOException ex3) {
                                                                            throw b(ex3);
                                                                        }
                                                                        try {
                                                                            final _DoxygenLexer doxygenLexer = this;
                                                                            final int n7 = doxygenLexer.zzStartRead;
                                                                            final _DoxygenLexer doxygenLexer2 = this;
                                                                            final int n8 = doxygenLexer2.zzCurrentPos;
                                                                            if (n7 == n8) {
                                                                                this.zzAtEOF = true;
                                                                                return null;
                                                                            }
                                                                        }
                                                                        catch (IOException ex4) {
                                                                            throw b(ex4);
                                                                        }
                                                                    }
                                                                    try {
                                                                        if (n3 < 0) {
                                                                            final int n9 = n3;
                                                                            break Label_0319;
                                                                        }
                                                                    }
                                                                    catch (IOException ex5) {
                                                                        throw b(ex5);
                                                                    }
                                                                }
                                                                final int n9 = _DoxygenLexer.ZZ_ACTION[n3];
                                                                try {
                                                                    switch (n9) {
                                                                        case 1: {
                                                                            return DxTypes.DOC_COMMENT_DATA;
                                                                        }
                                                                        case 15: {
                                                                            continue;
                                                                        }
                                                                        case 2: {
                                                                            return TokenType.WHITE_SPACE;
                                                                        }
                                                                        case 16: {
                                                                            continue;
                                                                        }
                                                                        case 3: {
                                                                            break Label_0462;
                                                                        }
                                                                        case 17: {
                                                                            continue;
                                                                        }
                                                                        case 4: {
                                                                            break Label_0498;
                                                                        }
                                                                        case 18: {
                                                                            continue;
                                                                        }
                                                                        case 5: {
                                                                            break Label_0511;
                                                                        }
                                                                        case 19: {
                                                                            continue;
                                                                        }
                                                                        case 6: {
                                                                            break Label_0527;
                                                                        }
                                                                        case 20: {
                                                                            continue;
                                                                        }
                                                                        case 7: {
                                                                            break Label_0540;
                                                                        }
                                                                        case 21: {
                                                                            continue;
                                                                        }
                                                                        case 8: {
                                                                            break Label_0576;
                                                                        }
                                                                        case 22: {
                                                                            continue;
                                                                        }
                                                                        case 9: {
                                                                            break Label_0588;
                                                                        }
                                                                        case 23: {
                                                                            continue;
                                                                        }
                                                                        case 10: {
                                                                            break Label_0603;
                                                                        }
                                                                        case 24: {
                                                                            continue;
                                                                        }
                                                                        case 11: {
                                                                            break Label_0620;
                                                                        }
                                                                        case 25: {
                                                                            continue;
                                                                        }
                                                                        case 12: {
                                                                            return DxTypes.TAG_OPTION;
                                                                        }
                                                                        case 26: {
                                                                            continue;
                                                                        }
                                                                        case 13: {
                                                                            break Label_0644;
                                                                        }
                                                                        case 27: {
                                                                            continue;
                                                                        }
                                                                        case 14: {
                                                                            break Label_0696;
                                                                        }
                                                                        case 28: {
                                                                            continue;
                                                                        }
                                                                        default: {
                                                                            break Label_0709;
                                                                        }
                                                                    }
                                                                }
                                                                catch (IOException ex6) {
                                                                    throw b(ex6);
                                                                }
                                                            }
                                                            continue;
                                                        }
                                                        this.yybegin(this.initCommentState);
                                                        this.yypushback(1);
                                                        continue;
                                                    }
                                                    this.a(1);
                                                    continue;
                                                    try {
                                                        if (this.yytext().toString().endsWith("*/")) {
                                                            this.yypushback(2);
                                                        }
                                                    }
                                                    catch (IOException ex7) {
                                                        throw b(ex7);
                                                    }
                                                }
                                                return DxTypes.DOC_COMMENT_DATA;
                                            }
                                            this.yybegin(8);
                                            return DxTypes.TAG_PARAM;
                                        }
                                        this.yybegin(6);
                                        return DxTypes.PARAM_SEPARATOR;
                                        try {
                                            if (this.yytext().toString().endsWith("*/")) {
                                                this.yypushback(2);
                                            }
                                        }
                                        catch (IOException ex8) {
                                            throw b(ex8);
                                        }
                                    }
                                    return TokenType.WHITE_SPACE;
                                }
                                this.yybegin(0);
                                return DxTypes.DOC_COMMENT_END;
                            }
                            this.yybegin(this.initCommentState);
                            return DxTypes.TAG_NAME;
                        }
                        this.yybegin(this.initCommentState = 2);
                        return DxTypes.EOF_DOC_COMMENT_START;
                    }
                    this.yybegin(this.initCommentState = 4);
                    return DxTypes.DOC_COMMENT_START;
                }
                final String string = this.yytext().toString();
                try {
                    if (string.endsWith(".")) {
                        this.yybegin(8);
                        return DxTypes.ELLIPSIS;
                    }
                }
                catch (IOException ex9) {
                    throw b(ex9);
                }
                this.yypushback(string.length() - 3);
                return DxTypes.ELLIPSIS;
            }
            this.yybegin(6);
            return DxTypes.TAG_NAME;
        }
    }
    
    static {
        ZZ_LEXSTATE = new int[] { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4 };
        ZZ_CMAP_Z = a("\u0001\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u0001\u000f\u0001\u0010\u0005\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0011\f\u0015\u0001\u0016(\u0015\u0001\u0017\u0002\u0015\u0001\u0018\u0001\u0019\u0001\u001a\u0001\u001b\u0015\u0015\u0001\u001c\u0010\u0011\u0001\u001d\u0001\u001e\u0001\u001f\u0001 \u0001!\u0001\"\u0001#\u0001\u0011\u0001$\u0001%\u0001&\u0001\u0011\u0001'\u0002\u0011\u0001(\u0004\u0011\u0001\u0015\u0001)\u0001*\u0005\u0011\u0002\u0015\u0001+\u0019\u0011\u0001\u0015\u0001,\u0001\u0011\u0001- \u0011\u0001.\u000f\u0011\u0001/\u00010\u00011\u00012\u000b\u0011\u00013\b\u0011S\u0015\u00014\u0007\u0015\u00015\u00016\u001f\u0011\u0001\u0015\u00016\u0582\u0011\u00017\u017f\u0011");
        ZZ_CMAP_Y = a("\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0000\u0001\u0005\u0001\u0006\u0002\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0003\u0000\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0002\u0007\u0001\u0012\u0003\u0007\u0001\u00129\u0007\u0001\u0013\u0001\u0007\u0001\u0014\u0001\u0015\u0001\u0016\u0001\u0017\u0002\u0015\u000e\u0000\u0001\u0018\u0001\u000e\u0001\u0019\u0001\u001a\u0002\u0007\u0001\u001b\t\u0007\u0001\u001c\u0011\u0007\u0001\u001d\u0001\u001e\u0013\u0007\u0001\u0015\u0001\u001f\u0003\u0007\u0001\u0012\u0001 \u0001\u001f\u0004\u0007\u0001!\u0001\"\u0004\u0000\u0001#\u0001$\u0001\u0015\u0003\u0007\u0002%\u0001\u0015\u0001&\u0001'\u0001\u0000\u0001(\u0005\u0007\u0001)\u0003\u0000\u0001*\u0001+\u000b\u0007\u0001,\u0001#\u0001-\u0001.\u0001\u0000\u0001/\u0001\u0015\u00010\u00011\u0003\u0007\u0003\u0000\u00012\n\u0007\u00013\u0001\u0000\u00014\u0001\u0015\u0001\u0000\u00015\u0003\u0007\u0001)\u00016\u0001\u0011\u0002\u0007\u00013\u00017\u00018\u00019\u0002\u0015\u0003\u0007\u0001:\b\u0015\u0001;\u0001\u0016\u0006\u0015\u0001<\u0002\u0000\u0001=\u0001>\u0006\u0007\u0001?\u0002\u0000\u0001@\u0001\u0007\u0001A\u0001\u0000\u0002\u001f\u0001B\u0001C\u0001D\u0002\u0007\u0001;\u0001E\u0001F\u0001G\u0001H\u00010\u0001I\u0001A\u0001\u0000\u0001J\u0001'\u0001B\u0001K\u0001D\u0002\u0007\u0001;\u0001L\u0001M\u0001N\u0001O\u0001P\u0001Q\u0001R\u0001\u0000\u0001S\u0001\u0015\u0001B\u0001\u001c\u0001\u001b\u0002\u0007\u0001;\u0001T\u0001F\u0001#\u0001U\u0001V\u0001\u0015\u0001A\u0001\u0000\u0001 \u0001\u0015\u0001B\u0001C\u0001D\u0002\u0007\u0001;\u0001T\u0001F\u0001G\u0001O\u0001R\u0001I\u0001A\u0001\u0000\u0001 \u0001\u0015\u0001W\u0001X\u0001Y\u0001Z\u0001[\u0001X\u0001\u0007\u0001\\\u0001]\u0001^\u0001_\u0001\u0015\u0001R\u0001\u0000\u0001\u0015\u0001 \u0001B\u0001\u0018\u0001;\u0002\u0007\u0001;\u0001`\u0001a\u0001b\u0001^\u0001c\u0001\u0014\u0001A\u0001\u0000\u0002\u0015\u0001d\u0001\u0018\u0001;\u0002\u0007\u0001;\u0001`\u0001F\u0001b\u0001^\u0001c\u0001\u0019\u0001A\u0001\u0000\u0001e\u0001\u0015\u0001d\u0001\u0018\u0001;\u0004\u0007\u0001f\u0001b\u0001g\u00010\u0001\u0015\u0001A\u0001\u0000\u0001\u0015\u0001\u001e\u0001d\u0001\u0007\u0001\u0012\u0001\u001e\u0002\u0007\u0001\u001b\u0001h\u0001\u0012\u0001i\u0001j\u0001\u0000\u0002\u0015\u0001k\u0001\u0015\u0001\u001f\u0005\u0007\u0001l\u0001m\u0001n\u0001=\u0001\u0000\u0001o\u0004\u0015\u0001p\u0001q\u0001r\u0001\u001f\u0001s\u0001t\u0001l\u0001u\u0001v\u0001w\u0001\u0000\u0001x\u0004\u0015\u0001V\u0002\u0015\u0001o\u0001\u0000\u0001o\u0001y\u0001z\u0001\u0007\u0001\u001f\u0003\u0007\u0001\u0016\u0001\"\u0001\u0000\u0001b\u0001{\u0001\u0000\u0001\"\u0003\u0000\u0001&\u0001|\u0007\u0015\u0005\u0007\u0001)\u0001\u0000\u0001}\u0001\u0000\u0001o\u00013\u0001~\u0001\u007f\u0001\u0080\u0001\u0081\u0001\u0007\u0001\u0082\u0001\u0083\u0001\u0000\u0001w\u0004\u0007\u0001\u001c\u0001\u0010\u0005\u0007\u0001\u0084)\u0007\u0001Y\u0001\u0012\u0001Y\u0005\u0007\u0001Y\u0004\u0007\u0001Y\u0001\u0012\u0001Y\u0001\u0007\u0001\u0012\u0007\u0007\u0001Y\b\u0007\u0001\u0085\u0004\u0015\u0002\u0007\u0002\u0015\n\u0007\u0001\u0016\u0001\u0015\u0001\u001fL\u0007\u0001C\u0002\u0007\u0001\u001f\u0002\u0007\u0001%\t\u0007\u0001X\u0001V\u0001\u0015\u0001\u0007\u0001\u0018\u0001\u0086\u0001\u0015\u0002\u0007\u0001\u0086\u0001\u0015\u0002\u0007\u0001\u0087\u0001\u0015\u0001\u0007\u0001\u0018\u0001\u0088\u0001\u0015\u0006\u0007\u0001\u0089\u0003\u0000\u0001\u008a\u0001\u008b\u0001\u0000\u0001o\u0003\u0015\u0001\u008c\u0001\u0000\u0001o\u000b\u0007\u0001\u0015\u0005\u0007\u0001\u008d\b\u0007\u0001\u008e\u0001\u0015\u0003\u0007\u0001\u0016\u0001\u0000\u0001\u0002\u0001\u0000\u0001\u0002\u0001R\u0001\u0000\u0003\u0007\u0001\u008e\u0001\u0016\u0001\u0015\u0005\u0007\u0001J\u0002\u0000\u0001+\u0001o\u0001\u0000\u0001o\u0004\u0015\u0002\u0007\u0001n\u0001\u0002\u0006\u0007\u0001{\u0001=\u0003\u0000\u0001G\u0001\u0000\u0001o\u0001\u0000\u0001o\u0001!\u000b\u0015\u0001\u008f\u0005\u0007\u0001\u0089\u0001\u0000\u0001\u008f\u0001J\u0001\u0000\u0001o\u0001\u0015\u0001\u0090\u0001\u0002\u0001\u0015\u0001\u0091\u0003\u0007\u0001@\u0001\u0080\u0001\u0000\u00015\u0004\u0007\u00013\u0001\u0000\u0001\u0002\u0001\u0015\u0004\u0007\u0001\u0089\u0002\u0000\u0001\u0015\u0001\u0000\u0001\u0092\u0001\u0000\u00015\u0003\u0007\u0001\u008e\n\u0015\u0001\u0093\u0002\u0000\u0001\u0094\u0001\u0095\u0001\u0015\u0018\u0007\u0004\u0000\u0001=\u0002\u0015\u0001<\"\u0007\u0002\u008e\u0004\u0007\u0002\u008e\u0001\u0007\u0001\u0096\u0003\u0007\u0001\u008e\u0006\u0007\u0001\u0018\u0001v\u0001\u0097\u0001\u0016\u0001\u0098\u0001J\u0001\u0007\u0001\u0016\u0001\u0097\u0001\u0016\u0001\u0015\u0001\u0090\u0003\u0015\u0001\u0099\u0001\u0015\u0001!\u0001V\u0001\u0015\u0001\u009a\u0001\u0015\u0001&\u0001\u009b\u0001 \u0001!\u0002\u0015\u0001\u0007\u0001\u0016\u0003\u0007\u0001%\u0002\u0015\u0001\u0000\u0001&\u0001\u009c\u0001\u0000\u0001\u009d\u0001\u0015\u0001\u009e\u0001\u001e\u0001h\u0001\u009f\u0001\u0017\u0001 \u0001\u0007\u0001¡\u0001¢\u0001£\u0002\u0015\u0005\u0007\u0001VN\u0015\u0005\u0007\u0001\u0012\u0005\u0007\u0001\u0012\u0010\u0007\u0001\u0016\u0001¤\u0001¥\u0001\u0015\u0004\u0007\u0001\u001c\u0001\u0010\u0007\u0007\u0001!\u0001\u0015\u00010\u0002\u0007\u0001\u0012\u0001\u0015\b\u0012\u0004\u0000\u0005\u0015\u0001!:\u0015\u0001¢\u0003\u0015\u0001\u001f\u0001\u0082\u0001\u009f\u0001\u0016\u0001\u001f\t\u0007\u0001\u0012\u0001¦\u0001\u001f\n\u0007\u0001\u0084\u0001¢\u0004\u0007\u0001\u008e\u0001\u001f\n\u0007\u0001\u0012\u0002\u0015\u0003\u0007\u0001%\u0006\u0015x\u0007\u0001\u008e\t\u00159\u0007\u0001\u0016\u0006\u0015\u0011\u0007\u0001\u0016\b\u0015\u0005\u0007\u0001\u008e!\u0007\u0001\u0016\u0002\u0007\u0001\u0000\u0001¥\u0002\u0015\u0005\u0007\u0001n\u0001<\u0001§\u0003\u0007\u00010\n\u0007\u0001o\u0003\u0015\u0001!\u0001\u0007\u0001\u001e\f\u0007\u0001¨\u0001J\u0001\u0015\u0001\u0007\u0001%\t\u0015\u0001\u0007\u0001©\u0001ª\u0002\u0007\u0001)\u0002\u0015\u0001V\u0006\u0007\u0001J\u0001\u0015\u00015\u0005\u0007\u0001\u0089\u0001\u0000\u0001&\u0001\u0015\u0001\u0000\u0001o\u0002\u0000\u00015\u0001'\u0001\u0000\u00015\u0002\u0007\u00013\u0001w\u0002\u0007\u0001n\u0001\u0000\u0001\u0002\u0001\u0015\u0003\u0007\u0001\u0016\u0001>\u0005\u0007\u0001)\u0001\u0000\u0001\u009d\u0001!\u0001\u0000\u0001o\u0004\u0015\u0005\u0007\u0001@\u0001=\u0001\u0015\u0001ª\u0001«\u0001\u0000\u0001o\u0002\u0007\u0001\u0012\u0001¬\u0006\u0007\u0001\u007f\u0001\u00ad\u0001\u008d\u0002\u0015\u0001®\u0001\u0007\u0001)\u0001¯\u0001\u0015\u0003°\u0001\u0015\u0002\u0012\u0012\u0015\u0004\u0007\u0001)\u0001±\u0001\u0000\u0001o4\u0007\u0001J\u0001\u0015\u0002\u0007\u0001\u0012\u0001²\u0005\u0007\u0001J \u0015-\u0007\u0001\u008e\r\u0007\u0001\u0014\u0004\u0015\u0001\u0012\u0001\u0015\u0001²\u0001³\u0001\u0007\u0001;\u0001\u0012\u0001v\u0001´\r\u0007\u0001\u0014\u0003\u0015\u0001²,\u0007\u0001\u008e\u0002\u0015\b\u0007\u0001\u001e\u0006\u0007\u0005\u0015\u0001\u0007\u0001\u0016\u0002\u0000\u0002\u0015\u0001=\u0001\u0015\u0001[\u0002\u0015\u0001¢\u0003\u0015\u0001 \u0001\u0018\u0010\u0007\u0001µ\u0001\u009a\u0001\u0015\u0001\u0000\u0001o\u0001\u001f\u0002\u0007\u0001K\u0001\u001f\u0002\u0007\u0001%\u0001¶\n\u0007\u0001\u0012\u0003\u001e\u0001·\u0001¸\u0002\u0015\u0001¹\u0001\u0007\u0001`\u0002\u0007\u0001\u0012\u0002\u0007\u0001º\u0001\u0007\u0001\u008e\u0001\u0007\u0001\u008e\u0004\u0015\u000f\u0007\u0001%\b\u0015\u0006\u0007\u0001\u0016\u0010\u0015\u0001»\u0010\u0015\u0003\u0007\u0001\u0016\u0006\u0007\u0001V\u0005\u0015\u0003\u0007\u0001\u0012\u0002\u0015\u0003\u0007\u0001%\u0006\u0015\u0003\u0007\u0001\u008e\u0004\u0007\u0001J\u0001\u0007\u0001\u009f\u0005\u0015\u0013\u0007\u0001\u008e\u0001\u0000\u0001o*\u0015\u0001\u008e\u0001;\u0004\u0007\u0001\u001c\u0001¼\u0002\u0007\u0001\u008e\u0015\u0015\u0002\u0007\u0001\u008e\u0001\u0015\u0003\u0007\u0001\u0014\b\u0015\u0007\u0007\u0001¶\b\u0015\u0001½\u0001<\u0001`\u0001\u001f\u0002\u0007\u0001J\u0001N\u0004\u0015\u0003\u0007\u0001\u0016\u0010\u0015\u0006\u0007\u0001\u008e\u0001\u0015\u0002\u0007\u0001\u008e\u0001\u0015\u0002\u0007\u0001%\u0011\u0015\t\u0007\u0001V6\u0015\u0001\u0091\u0006\u0007\u0001\u0000\u0001=\u0003\u0015\u0001R\u0001\u0000\u0002\u0015\u0001\u0091\u0005\u0007\u0001\u0000\u0001¾\u0002\u0015\u0003\u0007\u0001V\u0001\u0000\u0001o\u0001\u0091\u0003\u0007\u0001n\u0001\u0000\u0001b\u0001\u0000\b\u0015\u0001\u0091\u0005\u0007\u0001)\u0001\u0000\u0001¿\u0001\u0015\u0001\u0000\u0001o\u0014\u0015\u0005\u0007\u0001)\u0001\u0000\u0001\u0015\u0001\u0000\u0001o&\u0015-\u0007\u0001\u0012\u0012\u0015\f\u0007\u0001%3\u0015\u0005\u0007\u0001\u0012:\u0015\u0007\u0007\u0001VX\u0015\b\u0007\u0001\u0016\u0001\u0015\u0001@\u0004\u0000\u0001=\u0001\u0015\u00010\u0001\u0091\u0001\u0007\f\u0015\u0001\u0014k\u0015\u0001\u00c0\u0001\u00c1\u0002\u0000\u0001\u00c2\u0001\u0002\u0003\u0015\u0001\u00c3\u0012\u0015\u0001\u00c47\u0015\n\u0007\u0001\u0018\b\u0007\u0001\u0018\u0001\u00c5\u0001\u00c6\u0001\u0007\u0001\u00c7\u0001`\u0007\u0007\u0001\u001c\u0001\u00c8\u0002\u0018\u0003\u0007\u0001\u00c9\u0001v\u0001\u001e\u0001;)\u0007\u0001\u008e\u0003\u0007\u0001;\u0002\u0007\u0001\u0084\u0003\u0007\u0001\u0084\u0002\u0007\u0001\u0018\u0003\u0007\u0001\u0018\u0002\u0007\u0001\u0012\u0003\u0007\u0001\u0012\u0003\u0007\u0001;\u0003\u0007\u0001;\u0002\u0007\u0001\u0084\u0001\u00ca\u0006\u0000\u0001`\u0003\u0007\u0001p\u0001\u001f\u0001\u0084\u0001\u00cb\u0001\u009e\u0001\u00cc\u0001p\u0001\u0096\u0001p\u0002\u0084\u0001Q\u0001\u0007\u0001\u001b\u0001\u0007\u0001J\u0001\u00cd\u0001\u001b\u0001\u0007\u0001J(\u0015\u001a\u0007\u0001\u0012\u0005\u0015F\u0007\u0001\u0016\u0001\u0015\u001b\u0007\u0001\u008e<\u0015\u0001P\u0003\u0015\f\u0000\u0010\u0015\u001e\u0000\u0002\u0015");
        ZZ_CMAP_A = a("\t\u0007\u0001\u0004\u0001\u0002\u0001\u0001\u0001\u0005\u0001\u0003\u0006\u0007\u0004\u0000\u0001!\u0001\n\u0002\u0000\u0001\u0006\u0005\u0000\u0001\f\u0001\u0000\u0001\u001f\u0001\u0000\u0001 \u0001\t\u0002\u0007\u0002\u0000\u0001\u000b\u0003\u0000\u0001\r\u0012\u0006\u0001\u001d\u0001\r\u0001\u001e\u0001\u0000\u0001\u0006\u0001\u0000\u0001\u0015\u0001\u001c\u0001\u000e\u0001\u0010\u0001\u0011\u0002\u0006\u0001\u0019\u0004\u0006\u0001\u0017\u0001\u0006\u0001\u000f\u0001\u0014\u0001\u0006\u0001\u0016\u0001\u001b\u0001\u0018\u0002\u0006\u0001\u001a\u0003\u0006\u0001\u0012\u0001\u0000\u0001\u0013\u0001\u0000\u0006\u0007\u0001\b\u0002\u0007\u0002\u0000\u0004\u0006\u0004\u0000\u0001\u0006\u0002\u0000\u0001\u0007\u0007\u0000\u0001\u0006\u0004\u0000\u0001\u0006\u0005\u0000\u0007\u0006\u0001\u0000\u0002\u0006\u0004\u0000\u0004\u0006\u000e\u0000\u0005\u0006\u0007\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0001\u0000\u0002\u0006\u0006\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u0001\u0006\u0001\u0000\u0004\u0006\u0001\u0000\u000b\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u0005\u0007\u0002\u0000\u0006\u0006\u0001\u0000\u0007\u0006\u0001\u0000\u0001\u0006\r\u0000\u0001\u0006\u0001\u0000\r\u0007\u0001\u0000\u0001\u0007\u0001\u0000\u0002\u0007\u0001\u0000\u0002\u0007\u0001\u0000\u0001\u0007\u0003\u0006\u0005\u0000\u0005\u0007\u0006\u0000\u0001\u0006\u0004\u0000\u0003\u0007\u0005\u0000\u0003\u0006\u0007\u0007\u0004\u0000\u0002\u0006\u0001\u0007\u000b\u0006\u0001\u0000\u0001\u0006\u0007\u0007\u0002\u0006\u0002\u0007\u0001\u0000\u0004\u0007\u0002\u0006\u0002\u0007\u0003\u0006\u0002\u0000\u0001\u0006\u0007\u0000\u0001\u0007\u0001\u0006\u0001\u0007\u0006\u0006\u0003\u0007\u0002\u0000\t\u0006\u0003\u0007\u0001\u0006\u0006\u0000\u0002\u0007\u0006\u0006\u0004\u0007\u0002\u0006\u0002\u0000\u0002\u0007\u0001\u0006\t\u0007\u0001\u0006\u0003\u0007\u0001\u0006\u0005\u0007\u0002\u0000\u0001\u0006\u0003\u0007\u0004\u0000\u0001\u0006\u0001\u0000\u0006\u0006\u0004\u0000\u000b\u0007\u0001\u0000\u0004\u0007\u0006\u0006\u0003\u0007\u0001\u0006\u0002\u0007\u0001\u0006\u0007\u0007\u0002\u0006\u0002\u0007\u0002\u0000\u0002\u0007\u0001\u0000\u0003\u0007\u0001\u0000\b\u0006\u0002\u0000\u0002\u0006\u0002\u0000\u0006\u0006\u0001\u0000\u0001\u0006\u0003\u0000\u0004\u0006\u0002\u0000\u0001\u0007\u0001\u0006\u0007\u0007\u0002\u0000\u0002\u0007\u0002\u0000\u0003\u0007\u0001\u0006\u0005\u0000\u0002\u0006\u0001\u0000\u0005\u0006\u0004\u0000\u0003\u0006\u0004\u0000\u0002\u0006\u0001\u0000\u0002\u0006\u0001\u0000\u0002\u0006\u0001\u0000\u0002\u0006\u0002\u0000\u0001\u0007\u0001\u0000\u0005\u0007\u0004\u0000\u0002\u0007\u0002\u0000\u0003\u0007\u0003\u0000\u0001\u0007\u0007\u0000\u0004\u0006\u0001\u0000\u0001\u0006\u0007\u0000\u0004\u0007\u0003\u0006\u0001\u0007\u0002\u0000\u0001\u0006\u0001\u0000\u0002\u0006\u0001\u0000\u0003\u0006\u0002\u0007\u0001\u0000\u0003\u0007\u0002\u0000\u0001\u0006\t\u0000\u0001\u0007\u0001\u0006\u0001\u0000\u0006\u0006\u0003\u0000\u0003\u0006\u0001\u0000\u0004\u0006\u0003\u0000\u0002\u0006\u0001\u0000\u0001\u0006\u0001\u0000\u0002\u0006\u0003\u0000\u0002\u0006\u0003\u0000\u0002\u0006\u0004\u0000\u0005\u0007\u0003\u0000\u0003\u0007\u0001\u0000\u0004\u0007\u0002\u0000\u0001\u0006\u0006\u0000\u0001\u0007\u0004\u0006\u0001\u0000\u0005\u0006\u0003\u0000\u0001\u0006\u0007\u0007\u0001\u0000\u0002\u0007\u0005\u0000\u0002\u0007\u0003\u0000\u0002\u0007\u0001\u0000\u0003\u0006\u0001\u0000\u0002\u0006\u0005\u0000\u0003\u0006\u0002\u0000\u0001\u0006\u0003\u0007\u0001\u0000\u0004\u0007\u0001\u0006\u0001\u0000\u0004\u0006\u0001\u0000\u0001\u0006\u0004\u0000\u0001\u0007\u0004\u0000\u0006\u0007\u0001\u0000\u0001\u0007\u0003\u0000\u0002\u0007\u0004\u0000\u0001\u0006\u0001\u0007\u0002\u0006\u0007\u0007\u0004\u0000\b\u0006\u0003\u0007\u0007\u0000\u0002\u0006\u0001\u0000\u0001\u0006\u0002\u0000\u0002\u0006\u0001\u0000\u0001\u0006\u0002\u0000\u0001\u0006\u0006\u0000\u0004\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0002\u0000\u0002\u0006\u0001\u0000\u0003\u0006\u0002\u0007\u0001\u0000\u0002\u0007\u0001\u0006\u0002\u0000\u0005\u0006\u0001\u0000\u0001\u0006\u0001\u0000\u0006\u0007\u0002\u0000\u0002\u0007\u0002\u0000\u0004\u0006\u0005\u0000\u0001\u0007\u0001\u0000\u0001\u0007\u0001\u0000\u0001\u0007\u0004\u0000\u0002\u0007\u0005\u0006\u0003\u0007\u0006\u0000\u0001\u0007\u0001\u0000\u0007\u0007\u0001\u0006\u0002\u0007\u0004\u0006\u0003\u0007\u0001\u0006\u0003\u0007\u0002\u0006\u0007\u0007\u0003\u0006\u0004\u0007\u0005\u0006\f\u0007\u0001\u0006\u0001\u0007\u0003\u0006\u0001\u0000\u0007\u0006\u0002\u0000\u0003\u0007\u0002\u0006\u0003\u0007\u0003\u0000\u0002\u0006\u0002\u0007\u0004\u0000\u0001\u0006\u0001\u0000\u0002\u0007\u0004\u0000\u0004\u0006\b\u0007\u0003\u0000\u0001\u0006\u0003\u0000\u0002\u0006\u0001\u0007\u0005\u0000\u0003\u0007\u0002\u0000\u0001\u0006\u0001\u0007\u0001\u0006\u0005\u0000\u0006\u0006\u0002\u0000\u0005\u0007\u0003\u0006\u0003\u0000\b\u0007\u0005\u0006\u0002\u0007\u0003\u0000\u0003\u0006\u0003\u0007\u0001\u0000\u0005\u0007\u0004\u0006\u0001\u0007\u0004\u0006\u0003\u0007\u0002\u0006\u0002\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0002\u0000\u0003\u0006\u0001\u0000\u0006\u0006\u0002\u0000\u0002\u0006\u0002\u0001\u0005\u0007\u0005\u0000\u0001\u0006\u0005\u0000\u0006\u0007\u0001\u0000\u0001\u0007\u0003\u0000\u0004\u0007\t\u0000\u0001\u0006\u0004\u0000\u0001\u0006\u0001\u0000\u0005\u0006\u0002\u0000\u0001\u0006\u0001\u0000\u0004\u0006\u0001\u0000\u0003\u0006\u0002\u0000\u0004\u0006\u0005\u0000\u0005\u0006\u0004\u0000\u0001\u0006\u0004\u0000\u0004\u0006\u0003\u0007\u0002\u0006\u0005\u0000\u0002\u0007\u0002\u0000\u0003\u0006\u0006\u0007\u0001\u0000\u0002\u0006\u0002\u0000\u0004\u0006\u0001\u0000\u0002\u0006\u0001\u0007\u0003\u0006\u0001\u0007\u0004\u0006\u0001\u0007\b\u0006\u0002\u0007\u0004\u0000\u0001\u0006\u0001\u0007\u0004\u0000\u0001\u0007\u0005\u0006\u0002\u0007\u0003\u0000\u0003\u0006\u0004\u0000\u0003\u0006\u0002\u0007\u0002\u0000\u0006\u0006\u0001\u0000\u0003\u0007\u0001\u0000\u0002\u0007\u0005\u0000\u0005\u0006\u0005\u0000\u0001\u0006\u0001\u0007\u0003\u0006\u0001\u0000\u0002\u0006\u0001\u0000\u0007\u0006\u0002\u0000\u0001\u0007\u0006\u0000\u0002\u0006\u0002\u0000\u0003\u0006\u0003\u0000\u0002\u0006\u0003\u0000\u0002\u0006\u0002\u0000\u0003\u0007\u0004\u0000\u0003\u0006\u0001\u0000\u0002\u0006\u0001\u0000\u0001\u0006\u0005\u0000\u0001\u0007\u0002\u0000\u0001\u0006\u0003\u0000\u0001\u0006\u0002\u0000\u0002\u0006\u0003\u0007\u0001\u0000\u0002\u0007\u0001\u0000\u0003\u0007\u0002\u0000\u0001\u0007\u0002\u0000\u0001\u0007\u0004\u0006\b\u0000\u0005\u0007\u0003\u0000\u0006\u0007\u0002\u0000\u0003\u0007\u0002\u0000\u0004\u0007\u0004\u0000\u0003\u0007\u0005\u0000\u0001\u0006\u0002\u0000\u0002\u0006\u0002\u0000\u0004\u0006\u0001\u0000\u0004\u0006\u0001\u0000\u0001\u0006\u0001\u0000\u0006\u0006\u0002\u0000\u0005\u0006\u0001\u0000\u0004\u0006\u0001\u0000\u0004\u0006\u0002\u0000\u0002\u0007\u0001\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0005\u0000\u0001\u0006\u0001\u0000\u0001\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u0003\u0006\u0001\u0000\u0003\u0006");
        ZZ_ACTION = b();
        ZZ_ROWMAP = a();
        ZZ_TRANS = c();
        ZZ_ERROR_MSG = new String[] { "Unknown internal scanner error", "Error: could not match input", "Error: pushback value was too large" };
        ZZ_ATTRIBUTE = d();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
