// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.lexer;

import java.io.IOException;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.Stack;
import java.io.Reader;
import com.intellij.lexer.FlexLexer;

public class _ModuleMapLexer implements FlexLexer
{
    public static final int YYEOF = -1;
    private static final int ZZ_BUFFERSIZE = 16384;
    public static final int YYINITIAL = 0;
    public static final int IN_STRING = 2;
    public static final int IN_BLOCK_COMMENT = 4;
    public static final int ON_SLASH = 6;
    private static final int[] ZZ_LEXSTATE;
    private static final String ZZ_CMAP_PACKED = "\u0001\u0002\b\u0000\u0001\u0002\u0001\u0001\u0002\u0002\u0001\u0001\u0012\u0000\u0001\u0002\u0001!\u0001(\u0001\u0000\u0001\u001d\u0005\u0000\u0001\u0004\u0001\u0000\u0001'\u0001\u0000\u0001\"\u0001\u0003\n\u001e\u0007\u0000\u001a\u001d\u0001%\u0001 \u0001&\u0001\u0000\u0001\u000b\u0001\u001f\u0001\r\u0001\u001c\u0001\u0005\u0001\u001a\u0001\u0010\u0001\b\u0001\n\u0001\u001b\u0001\t\u0001\u001d\u0001\u0017\u0001\u0015\u0001\f\u0001\u0007\u0001\u0006\u0001\u0012\u0001\u0018\u0001\u000e\u0001\u000f\u0001\u0013\u0001\u0019\u0001\u0014\u0001\u0016\u0001\u0011\u0002\u001d\u0001#\u0001\u0000\u0001$*\u0000\u0001\u001d\u0001\u0000\u0001\u001d\u0002\u0000\u0001\u001d\u0001\u0000\u0001\u001d\u0002\u0000\u0004\u001d\u0001\u0000\u0004\u001d\u0001\u0000\u0003\u001d\u0001\u0000\u0017\u001d\u0001\u0000\u001f\u001d\u0001\u0000\b\u001d\u0200\u001dp\u0000\u1310\u001d\u0001\u0000\u018d\u001d\u0001\u0000\u05b1\u001d@\u0000\u0200\u001d\u000b\u0000\u0003\u001d\u001c\u0000\u0005\u001d\u0010\u0000\u0002\u001d\u0013\u0000\u0001\u001d\u000b\u0000\u0010\u001d`\u001d0\u0000\u0090\u001d\u02d0\u0000 \u001d\u0276\u0000\u001e\u001d\u046c\u0000\u0200\u001d\u0080\u0000\u0180\u001d\u0004\u0000\u0004\u001d\u0019\u0000\u000f\u001d\u0001\u0000\u000f\u001d\ua7c0\u001d\u2100\u0000\u043e\u001d\u0002\u0000\u0090\u001d \u00000\u001d\u0010\u0000\u0015\u001d\u0002\u0000\u01b7\u001d\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\u0012\u0000";
    private static final char[] ZZ_CMAP;
    private static final int[] ZZ_ACTION;
    private static final String ZZ_ACTION_PACKED_0 = "\u0001\u0000\u0001\u0001\u0002\u0000\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u000b\u0006\u0001\u0002\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u0001\u0001\u0001\u000f\u0001\u0001\u0001\u0010\u0003\u0011\u0001\u0012\u0001\u0013\u0001\u0014\f\u0006\u0001\u0015\u0001\u0016\u000b\u0006\u0001\u0017\u000b\u0006\u0001\u0018\u0012\u0006\u0001\u0019\u0002\u0006\u0001\u001a\u0001\u0006\u0001\u001b\u0003\u0006\u0001\u001c\u0004\u0006\u0001\u001d\u0001\u0006\u0001\u001e\u0001\u001f\u0002\u0006\u0001 \u0001\u0006\u0001!\u0001\"\u0001#\u0001\u0006\u0001$\u0003\u0006\u0001%";
    private static final int[] ZZ_ROWMAP;
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000)\u0000R\u0000{\u0000¤\u0000\u00cd\u0000¤\u0000¤\u0000\u00f6\u0000\u011f\u0000\u0148\u0000\u0171\u0000\u019a\u0000\u01c3\u0000\u01ec\u0000\u0215\u0000\u023e\u0000\u0267\u0000\u0290\u0000\u02b9\u0000¤\u0000¤\u0000¤\u0000¤\u0000¤\u0000¤\u0000¤\u0000¤\u0000\u02e2\u0000¤\u0000¤\u0000¤\u0000¤\u0000\u030b\u0000\u0334\u0000¤\u0000\u035d\u0000¤\u0000\u0386\u0000¤\u0000\u03af\u0000\u03d8\u0000\u0401\u0000\u042a\u0000\u0453\u0000\u047c\u0000\u04a5\u0000\u04ce\u0000\u04f7\u0000\u0520\u0000¤\u0000¤\u0000\u0549\u0000\u0572\u0000\u059b\u0000\u05c4\u0000\u05ed\u0000\u0616\u0000\u063f\u0000\u0668\u0000\u0691\u0000\u06ba\u0000\u06e3\u0000\u011f\u0000\u070c\u0000\u0735\u0000\u075e\u0000\u0787\u0000\u07b0\u0000\u07d9\u0000\u0802\u0000\u082b\u0000\u0854\u0000\u087d\u0000\u08a6\u0000\u011f\u0000\u08cf\u0000\u08f8\u0000\u0921\u0000\u094a\u0000\u0973\u0000\u099c\u0000\u09c5\u0000\u09ee\u0000\u0a17\u0000\u0a40\u0000\u0a69\u0000\u0a92\u0000\u0abb\u0000\u0ae4\u0000\u0b0d\u0000\u0b36\u0000\u0b5f\u0000\u0b88\u0000\u011f\u0000\u0bb1\u0000\u0bda\u0000\u011f\u0000\u0c03\u0000\u011f\u0000\u0c2c\u0000\u0c55\u0000\u0c7e\u0000\u011f\u0000\u0ca7\u0000\u0cd0\u0000\u0cf9\u0000\u0d22\u0000\u011f\u0000\u0d4b\u0000\u011f\u0000\u011f\u0000\u0d74\u0000\u0d9d\u0000\u011f\u0000\u0dc6\u0000\u011f\u0000\u011f\u0000\u011f\u0000\u0def\u0000\u011f\u0000\u0e18\u0000\u0e41\u0000\u0e6a\u0000\u011f";
    private static final int[] ZZ_TRANS;
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0005\u0002\u0006\u0001\u0007\u0001\b\u0001\t\u0002\n\u0001\u000b\u0003\n\u0001\f\u0001\n\u0001\r\u0001\n\u0001\u000e\u0001\n\u0001\u000f\u0001\u0010\u0001\n\u0001\u0011\u0003\n\u0001\u0012\u0001\n\u0001\u0013\u0002\n\u0001\u0005\u0001\u0014\u0001\u0005\u0001\u0015\u0001\u0016\u0001\u0017\u0001\u0018\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u001c\u0001\u001d\u0001\u001e\u001e\u001d\u0001\u001f\u0007\u001d\u0001 \u0003!\u0001\"\u0001#$!\u0003$\u0001%\u0001&$$*\u0000\u0002\u0006+\u0000\u0001\n\u0001'\u0018\n\u0001(\u000e\u0000\u001a\n\u0001(\u000e\u0000\t\n\u0001)\u0010\n\u0001(\u000e\u0000\u0001\n\u0001*\u0018\n\u0001(\u000e\u0000\u000b\n\u0001+\u000e\n\u0001(\u000e\u0000\f\n\u0001,\r\n\u0001(\u000e\u0000\t\n\u0001-\u0010\n\u0001(\u000e\u0000\u000b\n\u0001.\u000e\n\u0001(\u000e\u0000\u0004\n\u0001/\u0015\n\u0001(\u000e\u0000\u0007\n\u00010\u0002\n\u00011\u000f\n\u0001(\u000e\u0000\u000b\n\u00012\u000e\n\u0001(\u000e\u0000\u0019\n\u000b\u0000\u0001\u001d\u0001\u0000\u001e\u001d\u0001\u0000\u0007\u001d\u0005\u0000\u00013'\u0000\u00014%\u0000\u0001%\u0001\u0000'%\u0005\u0000\u0002\n\u00015\u0017\n\u0001(\u000e\u0000\b\n\u00016\u0011\n\u0001(\u000e\u0000\u0015\n\u00017\u0004\n\u0001(\u000e\u0000\u0013\n\u00018\u0006\n\u0001(\u000e\u0000\u00019\f\n\u0001:\u0001;\u000b\n\u0001(\u000e\u0000\u0004\n\u0001<\u0015\n\u0001(\u000e\u0000\f\n\u0001=\r\n\u0001(\u000e\u0000\u0002\n\u0001>\u0017\n\u0001(\u000e\u0000\u0017\n\u0001?\u0002\n\u0001(\u000e\u0000\u000b\n\u0001@\u000e\n\u0001(\u000e\u0000\b\n\u0001A\u0011\n\u0001(\u000e\u0000\u0003\n\u0001B\u0016\n\u0001(\u000e\u0000\u0007\n\u0001C\u0012\n\u0001(\u000e\u0000\u0014\n\u0001D\u0005\n\u0001(\u000e\u0000\u0014\n\u0001E\u0005\n\u0001(\u000e\u0000\u0010\n\u0001F\t\n\u0001(\u000e\u0000\u0001\n\u0001G\u000e\n\u0001H\t\n\u0001(\u000e\u0000\u000b\n\u0001I\u000e\n\u0001(\u000e\u0000\u000f\n\u0001J\n\n\u0001(\u000e\u0000\u000e\n\u0001K\u000b\n\u0001(\u000e\u0000\u0012\n\u0001L\u0007\n\u0001(\u000e\u0000\t\n\u0001M\u0010\n\u0001(\u000e\u0000\u0015\n\u0001N\u0004\n\u0001(\u000e\u0000\u0004\n\u0001O\u000b\n\u0001P\t\n\u0001(\u000e\u0000\u000b\n\u0001Q\u000e\n\u0001(\u000e\u0000\u0010\n\u0001R\t\n\u0001(\u000e\u0000\u0004\n\u0001S\u0015\n\u0001(\u000e\u0000\u0014\n\u0001T\u0005\n\u0001(\u000e\u0000\t\n\u0001U\u0010\n\u0001(\u000e\u0000\u0004\n\u0001V\u0015\n\u0001(\u000e\u0000\t\n\u0001W\u0010\n\u0001(\u000e\u0000\b\n\u0001X\u0011\n\u0001(\u000e\u0000\u0014\n\u0001Y\u0005\n\u0001(\u000e\u0000\u000b\n\u0001Z\u000e\n\u0001(\u000e\u0000\u000b\n\u0001[\u000e\n\u0001(\u000e\u0000\u0005\n\u0001\\\u0014\n\u0001(\u000e\u0000\u0004\n\u0001]\u0015\n\u0001(\u000e\u0000\u0011\n\u0001^\b\n\u0001(\u000e\u0000\u000b\n\u0001_\u000e\n\u0001(\u000e\u0000\t\n\u0001`\u0010\n\u0001(\u000e\u0000\u0015\n\u0001a\u0004\n\u0001(\u000e\u0000\u000e\n\u0001b\u000b\n\u0001(\u000e\u0000\u0001c\u0019\n\u0001(\u000e\u0000\u0002\n\u0001d\u0017\n\u0001(\u000e\u0000\u000e\n\u0001e\u000b\n\u0001(\u000e\u0000\b\n\u0001f\u0011\n\u0001(\u000e\u0000\u0010\n\u0001g\t\n\u0001(\u000e\u0000\t\n\u0001h\u0010\n\u0001(\u000e\u0000\u0006\n\u0001i\u0013\n\u0001(\u000e\u0000\u0001j\u0019\n\u0001(\u000e\u0000\u0001\n\u0001k\u0018\n\u0001(\u000e\u0000\u000b\n\u0001l\u000e\n\u0001(\u000e\u0000\u000b\n\u0001m\u000e\n\u0001(\u000e\u0000\u0004\n\u0001n\u0015\n\u0001(\u000e\u0000\u000b\n\u0001o\u000e\n\u0001(\u000e\u0000\u0010\n\u0001p\t\n\u0001(\u000e\u0000\u0010\n\u0001q\t\n\u0001(\u000e\u0000\u0007\n\u0001r\u0012\n\u0001(\u000e\u0000\u000e\n\u0001s\u000b\n\u0001(\u000e\u0000\t\n\u0001t\u0010\n\u0001(\u000e\u0000\n\n\u0001u\u000f\n\u0001(\u000e\u0000\u000e\n\u0001v\u000b\n\u0001(\u000e\u0000\b\n\u0001w\u0011\n\u0001(\u000e\u0000\b\n\u0001x\u0011\n\u0001(\u000e\u0000\u0012\n\u0001y\u0007\n\u0001(\u000e\u0000\u0001z\u0019\n\u0001(\u000e\u0000\t\n\u0001{\u0010\n\u0001(\u000e\u0000\u0001\n\u0001|\u0018\n\u0001(\u000e\u0000\n\n\u0001}\u000f\n\u0001(\t\u0000";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG;
    private static final int[] ZZ_ATTRIBUTE;
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0001\u0000\u0001\u0001\u0002\u0000\u0001\t\u0001\u0001\u0002\t\f\u0001\b\t\u0001\u0001\u0004\t\u0002\u0001\u0001\t\u0001\u0001\u0001\t\u0001\u0001\u0001\t\n\u0001\u0002\tI\u0001";
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
    private Stack<Integer> myStack;
    private int myBlockCommentNesting;
    private int myScanStart;
    
    private static int[] f() {
        final int[] array = new int[125];
        a("\u0001\u0000\u0001\u0001\u0002\u0000\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u000b\u0006\u0001\u0002\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u0001\u0001\u0001\u000f\u0001\u0001\u0001\u0010\u0003\u0011\u0001\u0012\u0001\u0013\u0001\u0014\f\u0006\u0001\u0015\u0001\u0016\u000b\u0006\u0001\u0017\u000b\u0006\u0001\u0018\u0012\u0006\u0001\u0019\u0002\u0006\u0001\u001a\u0001\u0006\u0001\u001b\u0003\u0006\u0001\u001c\u0004\u0006\u0001\u001d\u0001\u0006\u0001\u001e\u0001\u001f\u0002\u0006\u0001 \u0001\u0006\u0001!\u0001\"\u0001#\u0001\u0006\u0001$\u0003\u0006\u0001%", 0, array);
        return array;
    }
    
    private static int a(final String s, final int n, final int[] array) {
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
    
    private static int[] i() {
        final int[] array = new int[125];
        c("\u0000\u0000\u0000)\u0000R\u0000{\u0000¤\u0000\u00cd\u0000¤\u0000¤\u0000\u00f6\u0000\u011f\u0000\u0148\u0000\u0171\u0000\u019a\u0000\u01c3\u0000\u01ec\u0000\u0215\u0000\u023e\u0000\u0267\u0000\u0290\u0000\u02b9\u0000¤\u0000¤\u0000¤\u0000¤\u0000¤\u0000¤\u0000¤\u0000¤\u0000\u02e2\u0000¤\u0000¤\u0000¤\u0000¤\u0000\u030b\u0000\u0334\u0000¤\u0000\u035d\u0000¤\u0000\u0386\u0000¤\u0000\u03af\u0000\u03d8\u0000\u0401\u0000\u042a\u0000\u0453\u0000\u047c\u0000\u04a5\u0000\u04ce\u0000\u04f7\u0000\u0520\u0000¤\u0000¤\u0000\u0549\u0000\u0572\u0000\u059b\u0000\u05c4\u0000\u05ed\u0000\u0616\u0000\u063f\u0000\u0668\u0000\u0691\u0000\u06ba\u0000\u06e3\u0000\u011f\u0000\u070c\u0000\u0735\u0000\u075e\u0000\u0787\u0000\u07b0\u0000\u07d9\u0000\u0802\u0000\u082b\u0000\u0854\u0000\u087d\u0000\u08a6\u0000\u011f\u0000\u08cf\u0000\u08f8\u0000\u0921\u0000\u094a\u0000\u0973\u0000\u099c\u0000\u09c5\u0000\u09ee\u0000\u0a17\u0000\u0a40\u0000\u0a69\u0000\u0a92\u0000\u0abb\u0000\u0ae4\u0000\u0b0d\u0000\u0b36\u0000\u0b5f\u0000\u0b88\u0000\u011f\u0000\u0bb1\u0000\u0bda\u0000\u011f\u0000\u0c03\u0000\u011f\u0000\u0c2c\u0000\u0c55\u0000\u0c7e\u0000\u011f\u0000\u0ca7\u0000\u0cd0\u0000\u0cf9\u0000\u0d22\u0000\u011f\u0000\u0d4b\u0000\u011f\u0000\u011f\u0000\u0d74\u0000\u0d9d\u0000\u011f\u0000\u0dc6\u0000\u011f\u0000\u011f\u0000\u011f\u0000\u0def\u0000\u011f\u0000\u0e18\u0000\u0e41\u0000\u0e6a\u0000\u011f", 0, array);
        return array;
    }
    
    private static int c(final String s, final int n, final int[] array) {
        int i;
        int n2;
        for (i = 0, n2 = n; i < s.length(); array[n2++] = (s.charAt(i++) << 16 | s.charAt(i++))) {}
        return n2;
    }
    
    private static int[] d() {
        final int[] array = new int[3731];
        b("\u0001\u0005\u0002\u0006\u0001\u0007\u0001\b\u0001\t\u0002\n\u0001\u000b\u0003\n\u0001\f\u0001\n\u0001\r\u0001\n\u0001\u000e\u0001\n\u0001\u000f\u0001\u0010\u0001\n\u0001\u0011\u0003\n\u0001\u0012\u0001\n\u0001\u0013\u0002\n\u0001\u0005\u0001\u0014\u0001\u0005\u0001\u0015\u0001\u0016\u0001\u0017\u0001\u0018\u0001\u0019\u0001\u001a\u0001\u001b\u0001\u001c\u0001\u001d\u0001\u001e\u001e\u001d\u0001\u001f\u0007\u001d\u0001 \u0003!\u0001\"\u0001#$!\u0003$\u0001%\u0001&$$*\u0000\u0002\u0006+\u0000\u0001\n\u0001'\u0018\n\u0001(\u000e\u0000\u001a\n\u0001(\u000e\u0000\t\n\u0001)\u0010\n\u0001(\u000e\u0000\u0001\n\u0001*\u0018\n\u0001(\u000e\u0000\u000b\n\u0001+\u000e\n\u0001(\u000e\u0000\f\n\u0001,\r\n\u0001(\u000e\u0000\t\n\u0001-\u0010\n\u0001(\u000e\u0000\u000b\n\u0001.\u000e\n\u0001(\u000e\u0000\u0004\n\u0001/\u0015\n\u0001(\u000e\u0000\u0007\n\u00010\u0002\n\u00011\u000f\n\u0001(\u000e\u0000\u000b\n\u00012\u000e\n\u0001(\u000e\u0000\u0019\n\u000b\u0000\u0001\u001d\u0001\u0000\u001e\u001d\u0001\u0000\u0007\u001d\u0005\u0000\u00013'\u0000\u00014%\u0000\u0001%\u0001\u0000'%\u0005\u0000\u0002\n\u00015\u0017\n\u0001(\u000e\u0000\b\n\u00016\u0011\n\u0001(\u000e\u0000\u0015\n\u00017\u0004\n\u0001(\u000e\u0000\u0013\n\u00018\u0006\n\u0001(\u000e\u0000\u00019\f\n\u0001:\u0001;\u000b\n\u0001(\u000e\u0000\u0004\n\u0001<\u0015\n\u0001(\u000e\u0000\f\n\u0001=\r\n\u0001(\u000e\u0000\u0002\n\u0001>\u0017\n\u0001(\u000e\u0000\u0017\n\u0001?\u0002\n\u0001(\u000e\u0000\u000b\n\u0001@\u000e\n\u0001(\u000e\u0000\b\n\u0001A\u0011\n\u0001(\u000e\u0000\u0003\n\u0001B\u0016\n\u0001(\u000e\u0000\u0007\n\u0001C\u0012\n\u0001(\u000e\u0000\u0014\n\u0001D\u0005\n\u0001(\u000e\u0000\u0014\n\u0001E\u0005\n\u0001(\u000e\u0000\u0010\n\u0001F\t\n\u0001(\u000e\u0000\u0001\n\u0001G\u000e\n\u0001H\t\n\u0001(\u000e\u0000\u000b\n\u0001I\u000e\n\u0001(\u000e\u0000\u000f\n\u0001J\n\n\u0001(\u000e\u0000\u000e\n\u0001K\u000b\n\u0001(\u000e\u0000\u0012\n\u0001L\u0007\n\u0001(\u000e\u0000\t\n\u0001M\u0010\n\u0001(\u000e\u0000\u0015\n\u0001N\u0004\n\u0001(\u000e\u0000\u0004\n\u0001O\u000b\n\u0001P\t\n\u0001(\u000e\u0000\u000b\n\u0001Q\u000e\n\u0001(\u000e\u0000\u0010\n\u0001R\t\n\u0001(\u000e\u0000\u0004\n\u0001S\u0015\n\u0001(\u000e\u0000\u0014\n\u0001T\u0005\n\u0001(\u000e\u0000\t\n\u0001U\u0010\n\u0001(\u000e\u0000\u0004\n\u0001V\u0015\n\u0001(\u000e\u0000\t\n\u0001W\u0010\n\u0001(\u000e\u0000\b\n\u0001X\u0011\n\u0001(\u000e\u0000\u0014\n\u0001Y\u0005\n\u0001(\u000e\u0000\u000b\n\u0001Z\u000e\n\u0001(\u000e\u0000\u000b\n\u0001[\u000e\n\u0001(\u000e\u0000\u0005\n\u0001\\\u0014\n\u0001(\u000e\u0000\u0004\n\u0001]\u0015\n\u0001(\u000e\u0000\u0011\n\u0001^\b\n\u0001(\u000e\u0000\u000b\n\u0001_\u000e\n\u0001(\u000e\u0000\t\n\u0001`\u0010\n\u0001(\u000e\u0000\u0015\n\u0001a\u0004\n\u0001(\u000e\u0000\u000e\n\u0001b\u000b\n\u0001(\u000e\u0000\u0001c\u0019\n\u0001(\u000e\u0000\u0002\n\u0001d\u0017\n\u0001(\u000e\u0000\u000e\n\u0001e\u000b\n\u0001(\u000e\u0000\b\n\u0001f\u0011\n\u0001(\u000e\u0000\u0010\n\u0001g\t\n\u0001(\u000e\u0000\t\n\u0001h\u0010\n\u0001(\u000e\u0000\u0006\n\u0001i\u0013\n\u0001(\u000e\u0000\u0001j\u0019\n\u0001(\u000e\u0000\u0001\n\u0001k\u0018\n\u0001(\u000e\u0000\u000b\n\u0001l\u000e\n\u0001(\u000e\u0000\u000b\n\u0001m\u000e\n\u0001(\u000e\u0000\u0004\n\u0001n\u0015\n\u0001(\u000e\u0000\u000b\n\u0001o\u000e\n\u0001(\u000e\u0000\u0010\n\u0001p\t\n\u0001(\u000e\u0000\u0010\n\u0001q\t\n\u0001(\u000e\u0000\u0007\n\u0001r\u0012\n\u0001(\u000e\u0000\u000e\n\u0001s\u000b\n\u0001(\u000e\u0000\t\n\u0001t\u0010\n\u0001(\u000e\u0000\n\n\u0001u\u000f\n\u0001(\u000e\u0000\u000e\n\u0001v\u000b\n\u0001(\u000e\u0000\b\n\u0001w\u0011\n\u0001(\u000e\u0000\b\n\u0001x\u0011\n\u0001(\u000e\u0000\u0012\n\u0001y\u0007\n\u0001(\u000e\u0000\u0001z\u0019\n\u0001(\u000e\u0000\t\n\u0001{\u0010\n\u0001(\u000e\u0000\u0001\n\u0001|\u0018\n\u0001(\u000e\u0000\n\n\u0001}\u000f\n\u0001(\t\u0000", 0, array);
        return array;
    }
    
    private static int b(final String s, final int n, final int[] array) {
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
    
    private static int[] e() {
        final int[] array = new int[125];
        d("\u0001\u0000\u0001\u0001\u0002\u0000\u0001\t\u0001\u0001\u0002\t\f\u0001\b\t\u0001\u0001\u0004\t\u0002\u0001\u0001\t\u0001\u0001\u0001\t\u0001\u0001\u0001\t\n\u0001\u0002\tI\u0001", 0, array);
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
    
    public _ModuleMapLexer() {
        this(null);
    }
    
    private void b(final int n) {
        this.myStack.push((Object)this.yystate());
        this.yybegin(n);
    }
    
    private void h() {
        this.yybegin((int)this.myStack.pop());
    }
    
    private int c() {
        return this.zzStartRead = this.myScanStart;
    }
    
    private int a() {
        return this.myScanStart = this.zzStartRead;
    }
    
    private IElementType a(final IElementType elementType) {
        this.h();
        this.c();
        return elementType;
    }
    
    private IElementType a(final IElementType elementType, final int n) {
        this.b(n);
        this.c();
        return elementType;
    }
    
    protected void onReset() {
        this.myStack.clear();
        this.myBlockCommentNesting = 0;
    }
    
    public _ModuleMapLexer(final Reader zzReader) {
        this.zzLexicalState = 0;
        this.zzBuffer = "";
        this.zzAtBOL = true;
        this.myStack = (Stack<Integer>)new Stack();
        this.zzReader = zzReader;
    }
    
    private static char[] a(final String s) {
        final char[] array = new char[1114112];
        int i = 0;
        int n = 0;
        while (i < 280) {
            int char1 = s.charAt(i++);
            final char char2 = s.charAt(i++);
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
    
    private boolean b() throws IOException {
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
            s = _ModuleMapLexer.ZZ_ERROR_MSG[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            s = _ModuleMapLexer.ZZ_ERROR_MSG[0];
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
    
    private void g() {
        try {
            if (!this.zzEOFDone) {
                this.zzEOFDone = true;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw b(ex);
        }
    }
    
    public IElementType advance() throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzEndRead:I
        //     4: istore          5
        //     6: aload_0        
        //     7: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzBuffer:Ljava/lang/CharSequence;
        //    10: astore          6
        //    12: getstatic       com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.ZZ_CMAP:[C
        //    15: astore          7
        //    17: getstatic       com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.ZZ_TRANS:[I
        //    20: astore          8
        //    22: getstatic       com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.ZZ_ROWMAP:[I
        //    25: astore          9
        //    27: getstatic       com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.ZZ_ATTRIBUTE:[I
        //    30: astore          10
        //    32: aload_0        
        //    33: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzMarkedPos:I
        //    36: istore          4
        //    38: iconst_m1      
        //    39: istore_2       
        //    40: aload_0        
        //    41: aload_0        
        //    42: iload           4
        //    44: dup_x1         
        //    45: putfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzStartRead:I
        //    48: dup_x1         
        //    49: putfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzCurrentPos:I
        //    52: istore_3       
        //    53: aload_0        
        //    54: getstatic       com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.ZZ_LEXSTATE:[I
        //    57: aload_0        
        //    58: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzLexicalState:I
        //    61: iaload         
        //    62: putfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzState:I
        //    65: aload           10
        //    67: aload_0        
        //    68: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzState:I
        //    71: iaload         
        //    72: istore          11
        //    74: iload           11
        //    76: iconst_1       
        //    77: iand           
        //    78: iconst_1       
        //    79: if_icmpne       87
        //    82: aload_0        
        //    83: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzState:I
        //    86: istore_2       
        //    87: iload_3        
        //    88: iload           5
        //    90: if_icmpge       110
        //    93: aload           6
        //    95: iload_3        
        //    96: invokestatic    java/lang/Character.codePointAt:(Ljava/lang/CharSequence;I)I
        //    99: istore_1       
        //   100: iload_3        
        //   101: iload_1        
        //   102: invokestatic    java/lang/Character.charCount:(I)I
        //   105: iadd           
        //   106: istore_3       
        //   107: goto            186
        //   110: aload_0        
        //   111: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzAtEOF:Z
        //   114: ifeq            122
        //   117: iconst_m1      
        //   118: istore_1       
        //   119: goto            267
        //   122: aload_0        
        //   123: iload_3        
        //   124: putfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzCurrentPos:I
        //   127: aload_0        
        //   128: iload           4
        //   130: putfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzMarkedPos:I
        //   133: aload_0        
        //   134: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:()Z
        //   137: istore          12
        //   139: aload_0        
        //   140: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzCurrentPos:I
        //   143: istore_3       
        //   144: aload_0        
        //   145: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzMarkedPos:I
        //   148: istore          4
        //   150: aload_0        
        //   151: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzBuffer:Ljava/lang/CharSequence;
        //   154: astore          6
        //   156: aload_0        
        //   157: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzEndRead:I
        //   160: istore          5
        //   162: iload           12
        //   164: ifeq            172
        //   167: iconst_m1      
        //   168: istore_1       
        //   169: goto            267
        //   172: aload           6
        //   174: iload_3        
        //   175: invokestatic    java/lang/Character.codePointAt:(Ljava/lang/CharSequence;I)I
        //   178: istore_1       
        //   179: iload_3        
        //   180: iload_1        
        //   181: invokestatic    java/lang/Character.charCount:(I)I
        //   184: iadd           
        //   185: istore_3       
        //   186: aload           8
        //   188: aload           9
        //   190: aload_0        
        //   191: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzState:I
        //   194: iaload         
        //   195: aload           7
        //   197: iload_1        
        //   198: caload         
        //   199: iadd           
        //   200: iaload         
        //   201: istore          12
        //   203: iload           12
        //   205: iconst_m1      
        //   206: if_icmpne       216
        //   209: goto            267
        //   212: invokestatic    com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   215: athrow         
        //   216: aload_0        
        //   217: iload           12
        //   219: putfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzState:I
        //   222: aload           10
        //   224: aload_0        
        //   225: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzState:I
        //   228: iaload         
        //   229: istore          11
        //   231: iload           11
        //   233: iconst_1       
        //   234: iand           
        //   235: iconst_1       
        //   236: if_icmpne       264
        //   239: aload_0        
        //   240: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzState:I
        //   243: istore_2       
        //   244: iload_3        
        //   245: istore          4
        //   247: iload           11
        //   249: bipush          8
        //   251: iand           
        //   252: bipush          8
        //   254: if_icmpne       264
        //   257: goto            267
        //   260: invokestatic    com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   263: athrow         
        //   264: goto            87
        //   267: aload_0        
        //   268: iload           4
        //   270: putfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzMarkedPos:I
        //   273: iload_1        
        //   274: iconst_m1      
        //   275: if_icmpne       411
        //   278: aload_0        
        //   279: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzStartRead:I
        //   282: aload_0        
        //   283: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzCurrentPos:I
        //   286: if_icmpne       411
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   295: athrow         
        //   296: aload_0        
        //   297: iconst_1       
        //   298: putfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzAtEOF:Z
        //   301: aload_0        
        //   302: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.g:()V
        //   305: aload_0        
        //   306: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.zzLexicalState:I
        //   309: lookupswitch {
        //                2: 372
        //                4: 387
        //                6: 398
        //              126: 384
        //              127: 395
        //              128: 406
        //          default: 409
        //        }
        //   368: invokestatic    com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   371: athrow         
        //   372: aload_0        
        //   373: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.STRING:Lcom/intellij/psi/tree/IElementType;
        //   376: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/IElementType;
        //   379: areturn        
        //   380: invokestatic    com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   383: athrow         
        //   384: goto            1071
        //   387: aload_0        
        //   388: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.BLOCK_COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   391: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/IElementType;
        //   394: areturn        
        //   395: goto            1071
        //   398: aload_0        
        //   399: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.SLASH:Lcom/intellij/psi/tree/IElementType;
        //   402: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/IElementType;
        //   405: areturn        
        //   406: goto            1071
        //   409: aconst_null    
        //   410: areturn        
        //   411: iload_2        
        //   412: ifge            423
        //   415: iload_2        
        //   416: goto            428
        //   419: invokestatic    com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   422: athrow         
        //   423: getstatic       com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.ZZ_ACTION:[I
        //   426: iload_2        
        //   427: iaload         
        //   428: tableswitch {
        //                2: 740
        //                3: 743
        //                4: 750
        //                5: 757
        //                6: 771
        //                7: 778
        //                8: 785
        //                9: 792
        //               10: 799
        //               11: 806
        //               12: 813
        //               13: 820
        //               14: 827
        //               15: 834
        //               16: 847
        //               17: 863
        //               18: 874
        //               19: 877
        //               20: 893
        //               21: 904
        //               22: 916
        //               23: 929
        //               24: 961
        //               25: 968
        //               26: 975
        //               27: 982
        //               28: 989
        //               29: 996
        //               30: 1003
        //               31: 1010
        //               32: 1017
        //               33: 1024
        //               34: 1031
        //               35: 1038
        //               36: 1045
        //               37: 1052
        //               38: 1059
        //               39: 740
        //               40: 747
        //               41: 754
        //               42: 768
        //               43: 775
        //               44: 782
        //               45: 789
        //               46: 796
        //               47: 803
        //               48: 810
        //               49: 817
        //               50: 824
        //               51: 831
        //               52: 844
        //               53: 860
        //               54: 871
        //               55: 874
        //               56: 890
        //               57: 901
        //               58: 913
        //               59: 926
        //               60: 958
        //               61: 965
        //               62: 972
        //               63: 979
        //               64: 986
        //               65: 993
        //               66: 1000
        //               67: 1007
        //               68: 1014
        //               69: 1021
        //               70: 1028
        //               71: 1035
        //               72: 1042
        //               73: 1049
        //               74: 1056
        //               75: 1063
        //          default: 1066
        //        }
        //   740: goto            1071
        //   743: getstatic       com/intellij/psi/TokenType.BAD_CHARACTER:Lcom/intellij/psi/tree/IElementType;
        //   746: areturn        
        //   747: goto            1071
        //   750: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   753: areturn        
        //   754: goto            1071
        //   757: aload_0        
        //   758: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:()I
        //   761: pop            
        //   762: aload_0        
        //   763: bipush          6
        //   765: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:(I)V
        //   768: goto            1071
        //   771: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.WILDCARD:Lcom/intellij/psi/tree/IElementType;
        //   774: areturn        
        //   775: goto            1071
        //   778: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.IDENTIFIER:Lcom/intellij/psi/tree/IElementType;
        //   781: areturn        
        //   782: goto            1071
        //   785: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.EXCL:Lcom/intellij/psi/tree/IElementType;
        //   788: areturn        
        //   789: goto            1071
        //   792: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.DOT:Lcom/intellij/psi/tree/IElementType;
        //   795: areturn        
        //   796: goto            1071
        //   799: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.L_CURLY:Lcom/intellij/psi/tree/IElementType;
        //   802: areturn        
        //   803: goto            1071
        //   806: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.R_CURLY:Lcom/intellij/psi/tree/IElementType;
        //   809: areturn        
        //   810: goto            1071
        //   813: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.L_BRACKET:Lcom/intellij/psi/tree/IElementType;
        //   816: areturn        
        //   817: goto            1071
        //   820: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.R_BRACKET:Lcom/intellij/psi/tree/IElementType;
        //   823: areturn        
        //   824: goto            1071
        //   827: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.COMMA:Lcom/intellij/psi/tree/IElementType;
        //   830: areturn        
        //   831: goto            1071
        //   834: aload_0        
        //   835: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:()I
        //   838: pop            
        //   839: aload_0        
        //   840: iconst_2       
        //   841: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:(I)V
        //   844: goto            1071
        //   847: aload_0        
        //   848: iconst_1       
        //   849: invokevirtual   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.yypushback:(I)V
        //   852: aload_0        
        //   853: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.STRING:Lcom/intellij/psi/tree/IElementType;
        //   856: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/IElementType;
        //   859: areturn        
        //   860: goto            1071
        //   863: aload_0        
        //   864: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.STRING:Lcom/intellij/psi/tree/IElementType;
        //   867: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/IElementType;
        //   870: areturn        
        //   871: goto            1071
        //   874: goto            1071
        //   877: aload_0        
        //   878: iconst_1       
        //   879: invokevirtual   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.yypushback:(I)V
        //   882: aload_0        
        //   883: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.SLASH:Lcom/intellij/psi/tree/IElementType;
        //   886: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/IElementType;
        //   889: areturn        
        //   890: goto            1071
        //   893: aload_0        
        //   894: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.EOL_COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   897: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/IElementType;
        //   900: areturn        
        //   901: goto            1071
        //   904: aload_0        
        //   905: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.h:()V
        //   908: aload_0        
        //   909: iconst_4       
        //   910: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:(I)V
        //   913: goto            1071
        //   916: aload_0        
        //   917: dup            
        //   918: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.myBlockCommentNesting:I
        //   921: iconst_1       
        //   922: iadd           
        //   923: putfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.myBlockCommentNesting:I
        //   926: goto            1071
        //   929: aload_0        
        //   930: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.myBlockCommentNesting:I
        //   933: ifne            948
        //   936: aload_0        
        //   937: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.BLOCK_COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   940: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/psi/tree/IElementType;
        //   943: areturn        
        //   944: invokestatic    com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   947: athrow         
        //   948: aload_0        
        //   949: dup            
        //   950: getfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.myBlockCommentNesting:I
        //   953: iconst_1       
        //   954: isub           
        //   955: putfield        com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.myBlockCommentNesting:I
        //   958: goto            1071
        //   961: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.USE:Lcom/intellij/psi/tree/IElementType;
        //   964: areturn        
        //   965: goto            1071
        //   968: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.LINK:Lcom/intellij/psi/tree/IElementType;
        //   971: areturn        
        //   972: goto            1071
        //   975: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.MODULE:Lcom/intellij/psi/tree/IElementType;
        //   978: areturn        
        //   979: goto            1071
        //   982: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.EXPORT:Lcom/intellij/psi/tree/IElementType;
        //   985: areturn        
        //   986: goto            1071
        //   989: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.EXTERN:Lcom/intellij/psi/tree/IElementType;
        //   992: areturn        
        //   993: goto            1071
        //   996: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.HEADER:Lcom/intellij/psi/tree/IElementType;
        //   999: areturn        
        //  1000: goto            1071
        //  1003: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.EXCLUDE:Lcom/intellij/psi/tree/IElementType;
        //  1006: areturn        
        //  1007: goto            1071
        //  1010: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.PRIVATE:Lcom/intellij/psi/tree/IElementType;
        //  1013: areturn        
        //  1014: goto            1071
        //  1017: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.TEXTUAL:Lcom/intellij/psi/tree/IElementType;
        //  1020: areturn        
        //  1021: goto            1071
        //  1024: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.CONFLICT:Lcom/intellij/psi/tree/IElementType;
        //  1027: areturn        
        //  1028: goto            1071
        //  1031: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.REQUIRES:Lcom/intellij/psi/tree/IElementType;
        //  1034: areturn        
        //  1035: goto            1071
        //  1038: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.EXPLICIT:Lcom/intellij/psi/tree/IElementType;
        //  1041: areturn        
        //  1042: goto            1071
        //  1045: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.UMBRELLA:Lcom/intellij/psi/tree/IElementType;
        //  1048: areturn        
        //  1049: goto            1071
        //  1052: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.FRAMEWORK:Lcom/intellij/psi/tree/IElementType;
        //  1055: areturn        
        //  1056: goto            1071
        //  1059: getstatic       com/jetbrains/cidr/modulemap/ModuleMapParserTypes.CONFIG_MACROS:Lcom/intellij/psi/tree/IElementType;
        //  1062: areturn        
        //  1063: goto            1071
        //  1066: aload_0        
        //  1067: iconst_1       
        //  1068: invokespecial   com/jetbrains/cidr/modulemap/lexer/_ModuleMapLexer.a:(I)V
        //  1071: goto            32
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  203    212    212    216    Ljava/io/IOException;
        //  247    260    260    264    Ljava/io/IOException;
        //  267    289    292    296    Ljava/io/IOException;
        //  278    368    368    372    Ljava/io/IOException;
        //  296    380    380    384    Ljava/io/IOException;
        //  411    419    419    423    Ljava/io/IOException;
        //  929    944    944    948    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0296:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        ZZ_LEXSTATE = new int[] { 0, 0, 1, 1, 2, 2, 3, 3 };
        ZZ_CMAP = a("\u0001\u0002\b\u0000\u0001\u0002\u0001\u0001\u0002\u0002\u0001\u0001\u0012\u0000\u0001\u0002\u0001!\u0001(\u0001\u0000\u0001\u001d\u0005\u0000\u0001\u0004\u0001\u0000\u0001'\u0001\u0000\u0001\"\u0001\u0003\n\u001e\u0007\u0000\u001a\u001d\u0001%\u0001 \u0001&\u0001\u0000\u0001\u000b\u0001\u001f\u0001\r\u0001\u001c\u0001\u0005\u0001\u001a\u0001\u0010\u0001\b\u0001\n\u0001\u001b\u0001\t\u0001\u001d\u0001\u0017\u0001\u0015\u0001\f\u0001\u0007\u0001\u0006\u0001\u0012\u0001\u0018\u0001\u000e\u0001\u000f\u0001\u0013\u0001\u0019\u0001\u0014\u0001\u0016\u0001\u0011\u0002\u001d\u0001#\u0001\u0000\u0001$*\u0000\u0001\u001d\u0001\u0000\u0001\u001d\u0002\u0000\u0001\u001d\u0001\u0000\u0001\u001d\u0002\u0000\u0004\u001d\u0001\u0000\u0004\u001d\u0001\u0000\u0003\u001d\u0001\u0000\u0017\u001d\u0001\u0000\u001f\u001d\u0001\u0000\b\u001d\u0200\u001dp\u0000\u1310\u001d\u0001\u0000\u018d\u001d\u0001\u0000\u05b1\u001d@\u0000\u0200\u001d\u000b\u0000\u0003\u001d\u001c\u0000\u0005\u001d\u0010\u0000\u0002\u001d\u0013\u0000\u0001\u001d\u000b\u0000\u0010\u001d`\u001d0\u0000\u0090\u001d\u02d0\u0000 \u001d\u0276\u0000\u001e\u001d\u046c\u0000\u0200\u001d\u0080\u0000\u0180\u001d\u0004\u0000\u0004\u001d\u0019\u0000\u000f\u001d\u0001\u0000\u000f\u001d\ua7c0\u001d\u2100\u0000\u043e\u001d\u0002\u0000\u0090\u001d \u00000\u001d\u0010\u0000\u0015\u001d\u0002\u0000\u01b7\u001d\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\uffff\u0000\u0012\u0000");
        ZZ_ACTION = f();
        ZZ_ROWMAP = i();
        ZZ_TRANS = d();
        ZZ_ERROR_MSG = new String[] { "Unknown internal scanner error", "Error: could not match input", "Error: pushback value was too large" };
        ZZ_ATTRIBUTE = e();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
