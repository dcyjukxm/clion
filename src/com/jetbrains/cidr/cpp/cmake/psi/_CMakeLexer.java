// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import java.io.IOException;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.Stack;
import java.io.Reader;
import com.intellij.lexer.FlexLexer;

public class _CMakeLexer implements FlexLexer
{
    public static final int YYEOF = -1;
    private static final int ZZ_BUFFERSIZE = 16384;
    public static final int YYINITIAL = 0;
    public static final int IN_BLOCK_COMMENT = 2;
    public static final int IN_ARGUMENTS_LIST = 4;
    public static final int IN_BRACKET_ARGUMENT = 6;
    public static final int IN_QUOTED_ARGUMENT = 8;
    public static final int IN_BRACKET_ARGUMENT_END = 10;
    public static final int IN_QUOTED_ARGUMENT_END = 12;
    public static final int IN_EOF = 14;
    private static final int[] ZZ_LEXSTATE;
    static final char[] ZZ_CMAP_Z;
    static final char[] ZZ_CMAP_Y;
    static final char[] ZZ_CMAP_A;
    private static final int[] ZZ_ACTION;
    private static final String ZZ_ACTION_PACKED_0 = "\u0004\u0000\u0001\u0001\u0005\u0000\u0001\u0002\u0001\u0003\u0002\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\u0002\u0005\u0003\u0002\b\u0001\t\u0001\n\u0001\u0005\u0001\u000b\u0001\t\u0001\f\u0001\u0002\u0001\t\u0001\b\u0001\u0001\u0001\r\u0001\u000e\u0001\u0002\u0001\u0000\u0002\u0007\u0002\u000f\u0006\u0003\u0001\u0000\u0001\u0010\u0003\u0000\u0001\u0011\u0001\u0000\u0001\u0012\u0002\u0000\u0001\u0013\u0001\u0000\u0001\u0014\u0003\u0003\u0001\u0000\u0001\u0003\u0001\u0000\u0002\u0003\u0002\u0000\u0002\u0003\u0001\u0000\u0004\u0003\u0002\u0015\u0001\u0000\u0004\u0003\u0002\u0016\u0004\u0003\u0001\u0000\u0001\u0003\u0002\u0017\u0001\u0018\u0001\u0003\u0001\u0000\u0003\u0003\u0001\u0000\u0002\u0003\u0002\u0019\u0001\u001a\u0001\u0000\u0003\u0003\u0001\u0000\u0002\u0003\u0002\u001b\u0002\u0003\u0002\u001c\u0001\u001d\u0001\u0003\u0001\u0000\u0001\u0003\u0001\u001e\u0001\u0000\u0001\u0003\u0002\u001f";
    private static final int[] ZZ_ROWMAP;
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000$\u0000H\u0000l\u0000\u0090\u0000´\u0000\u00d8\u0000\u00fc\u0000\u0120\u0000\u0144\u0000\u0168\u0000\u018c\u0000\u01b0\u0000\u0168\u0000\u01d4\u0000\u0168\u0000\u01f8\u0000\u021c\u0000\u0240\u0000\u0264\u0000\u0288\u0000\u02ac\u0000\u02d0\u0000\u0168\u0000\u02f4\u0000\u0318\u0000\u0168\u0000\u033c\u0000\u0168\u0000\u0360\u0000\u0168\u0000\u0384\u0000\u03a8\u0000\u03cc\u0000\u03f0\u0000\u0168\u0000\u0414\u0000\u0438\u0000\u045c\u0000\u0480\u0000\u04a4\u0000\u0168\u0000\u018c\u0000\u04c8\u0000\u04ec\u0000\u0510\u0000\u0534\u0000\u0558\u0000\u057c\u0000\u02f4\u0000\u0168\u0000\u05a0\u0000\u0384\u0000\u05c4\u0000\u05e8\u0000\u03cc\u0000\u0168\u0000\u0414\u0000\u0438\u0000\u0168\u0000\u0168\u0000\u060c\u0000\u0630\u0000\u0654\u0000\u0678\u0000\u069c\u0000\u06c0\u0000\u06e4\u0000\u0708\u0000\u072c\u0000\u0750\u0000\u0774\u0000\u0798\u0000\u07bc\u0000\u07e0\u0000\u0804\u0000\u0828\u0000\u084c\u0000\u0870\u0000\u0894\u0000\u08b8\u0000\u08dc\u0000\u0900\u0000\u0924\u0000\u0948\u0000\u096c\u0000\u0168\u0000\u018c\u0000\u0990\u0000\u09b4\u0000\u09d8\u0000\u09fc\u0000\u0a20\u0000\u0a44\u0000\u0168\u0000\u018c\u0000\u018c\u0000\u0a68\u0000\u0a8c\u0000\u0ab0\u0000\u0ad4\u0000\u0af8\u0000\u0b1c\u0000\u0b40\u0000\u0b64\u0000\u0168\u0000\u018c\u0000\u018c\u0000\u0b88\u0000\u0bac\u0000\u0bd0\u0000\u0bf4\u0000\u0c18\u0000\u0c3c\u0000\u0c60\u0000\u0168\u0000\u018c\u0000\u0c84\u0000\u0ca8\u0000\u0168\u0000\u018c\u0000\u018c\u0000\u0ccc\u0000\u0cf0\u0000\u0d14\u0000\u018c\u0000\u0d38\u0000\u0d5c\u0000\u0168\u0000\u018c";
    private static final int[] ZZ_TRANS;
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u000b\u0001\f\u0002\u000b\u0001\r\u0001\u000e\u0002\u000f\u0001\u0010\u0001\u000b\u0001\u0011\u0002\u000b\u0003\f\u0005\u000b\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0015\u0001\f\u0001\u000b\u0002\f\u0001\u0016\u0005\f\u0001\u0017\u0014\u0018\u0001\u0019\u000f\u0018\u0003\u001a\u0001\u001b\u0004\u001c\u0001\u0010\u0001\u001d\u0001\u0011\u0001\u001e\u0004\u001a\u0001\u001f\u0001 \u0001!\u0011\u001a\u0014\u0018\u0001\"\u000f\u0018\u0003#\u0001$\r#\u0001%\u0012#\u0014\u000b\u0001&3\u000b\n\u0000\u0001\n\u0019\u0000$\t\u0012\u0000\u0001'6\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0005\u0000\u0001\u000e$\u0000\u0002\u000f\u001c\u0000\u0004(\u0002\u0000\f(\u0001)\u0011(\u0017\u0000\u0001*\r\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0001\f\u0001+\u0002\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001,\u0002\f\u0001-\u0001\f\u0001\u0000\u0002\f\n\u0000\u0002\f\u0001.\u0006\u0000\u0003\f\u0001/\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0003\f\u00010\u0005\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0005\f\u00011\u0003\f\u0013\u0000\u00012\u00013\u000f\u0000\u0003\u001a\u00014\u0007\u0000\u0001\u001e\u0004\u001a\u0001\u0000\u00015\u0012\u001a\u0004\u0000\u0004\u001c\u001c\u0000\u0003\u001a\u00014\u0004\u0000\u00016\u0002\u0000\u0001\u001e\u0004\u001a\u0001\u0000\u00015\u0012\u001a\u0003\u0000\u0001\u001a\u0002\u0000\u0001\u001a\u0001\u0000\n\u001a\u0012\u0000\u0003\u001a\u00014\u0007\u0000\u0001\u001e\u0004\u001a\u0001\u0000\u00015\u00017\u0001!\u0010\u001a\u0013\u0000\u00018\u00019\u000f\u0000\u0003#\u0001\u0000\r#\u0001:\u0012#\u0003\u0000\u0004#\u0001\u0000\n#%\u0000\u0001;\u0001<!\u0000\u0001=\u0001'\u0010\u0000\u0004(\u0002\u0000\"(\u0002\u0000\f(\u0001>\u0001)\u0010(\u0001\u0000\u0002\f\n\u0000\u0001\f\u0001?\u0001\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0002\f\u0001@\u0006\u0000\u0004\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0001\f\u0001A\u0007\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001B\u0001C\b\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001D\u0001E\u0003\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001F\u0002\f\u00034\u0001\u001a\r4\u0001G\u00124\u0001\u0000\u0001H\u000b\u0000\u0003H\u0006\u0000\u0004H\u0001\u0000\tH$7$>\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0002\f\u0001I\u0001\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001J\u0002\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001K\u0001L\u0001M\u0002\f\u0001\u0000\u0002\f\u0001N\u0005\f\u0001O\u0018\u0000\u0001P\f\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0002\f\u0001Q\u0001\f\u0001\u0000\t\f\u0019\u0000\u0001R\u000b\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0003\f\u0001S\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0001\f\u0001T\u0001\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0003\u0000\u00044\u0001\u0000\n4\u0013\u0000\u0002H\u0006\u0000\u0001\u001a\u0003\u0000\u0003H\u0006\u0000\u0004H\u0001\u0000\tH\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0005\f\u0001U\u0003\f\u0001\u0000\u0002\f\n\u0000\u0001V\u0002\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0017\u0000\u0001W\r\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0001\f\u0001X\u0002\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001Y\u0002\f\u0001Z\u0001\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0003\f\u0001[\u0005\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0005\f\u0001\\\u0003\f\u0015\u0000\u0002]\u000e\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001]\u0001^\u0003\f\u0001\u0000\t\f\u0018\u0000\u0001_\f\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0002\f\u0001`\u0001\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001a\u0004\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001b\u0002\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001c\u0001d\u0003\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0001\f\u0001e\u0001\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0002\f\u0001f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001g\u0001h\u0003\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001i\u0002\f\u0017\u0000\u0001j\r\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0001\f\u0001k\u0002\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0003\f\u0001l\u0005\f\u001f\u0000\u0001m\u0005\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001n\u0004\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0002\f\u0001o\u0001\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001p\u0002\f\u0019\u0000\u0001q\u000b\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0003\f\u0001r\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0001\f\u0001s\u0001\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u000f\u0000\u0001t\u0015\u0000\u0002\f\n\u0000\u0002\f\u0001u\u0006\u0000\u0004\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0005\f\u0001v\u0003\f\u0001\u0000\u0002\f\n\u0000\u0001w\u0002\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0018\u0000\u0001x\f\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0002\f\u0001y\u0001\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001z\u0004\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001{\u0002\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001|\u0001}\u0003\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0003\f\u0001~\u0005\f\u001f\u0000\u0001\u007f\u0005\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001\u0080\u0004\f\u000f\u0000\u0001\u0081\u0015\u0000\u0002\f\n\u0000\u0002\f\u0001\u0082\u0006\u0000\u0004\f\u0001\u0000\t\f";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG;
    private static final int[] ZZ_ATTRIBUTE;
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0004\u0000\u0001\u0001\u0003\u0000\u0001\u0001\u0001\u0000\u0001\t\u0002\u0001\u0001\t\u0001\u0001\u0001\t\u0007\u0001\u0001\t\u0002\u0001\u0001\t\u0001\u0001\u0001\t\u0001\u0001\u0001\t\u0004\u0001\u0001\t\u0002\u0001\u0001\u0000\u0002\u0001\u0001\t\u0007\u0001\u0001\u0000\u0001\t\u0003\u0000\u0001\u0001\u0001\u0000\u0001\t\u0002\u0000\u0002\t\u0004\u0001\u0001\u0000\u0001\u0001\u0001\u0000\u0002\u0001\u0002\u0000\u0002\u0001\u0001\u0000\u0006\u0001\u0001\u0000\u0004\u0001\u0001\t\u0005\u0001\u0001\u0000\u0001\u0001\u0001\t\u0003\u0001\u0001\u0000\u0003\u0001\u0001\u0000\u0002\u0001\u0001\t\u0002\u0001\u0001\u0000\u0003\u0001\u0001\u0000\u0002\u0001\u0001\t\u0003\u0001\u0001\t\u0003\u0001\u0001\u0000\u0002\u0001\u0001\u0000\u0001\u0001\u0001\t\u0001\u0001";
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
    private boolean[] zzFin;
    private Stack<Integer> myStack;
    private int myBracketArgLength;
    private int myScanStart;
    private IElementType myComplexTokenType;
    
    public static int ZZ_CMAP(final int n) {
        return _CMakeLexer.ZZ_CMAP_A[_CMakeLexer.ZZ_CMAP_Y[_CMakeLexer.ZZ_CMAP_Z[n >> 13] | (n >> 7 & 0x3F)] << 7 | (n & 0x7F)];
    }
    
    private static int[] c() {
        final int[] array = new int[130];
        d("\u0004\u0000\u0001\u0001\u0005\u0000\u0001\u0002\u0001\u0003\u0002\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\u0002\u0005\u0003\u0002\b\u0001\t\u0001\n\u0001\u0005\u0001\u000b\u0001\t\u0001\f\u0001\u0002\u0001\t\u0001\b\u0001\u0001\u0001\r\u0001\u000e\u0001\u0002\u0001\u0000\u0002\u0007\u0002\u000f\u0006\u0003\u0001\u0000\u0001\u0010\u0003\u0000\u0001\u0011\u0001\u0000\u0001\u0012\u0002\u0000\u0001\u0013\u0001\u0000\u0001\u0014\u0003\u0003\u0001\u0000\u0001\u0003\u0001\u0000\u0002\u0003\u0002\u0000\u0002\u0003\u0001\u0000\u0004\u0003\u0002\u0015\u0001\u0000\u0004\u0003\u0002\u0016\u0004\u0003\u0001\u0000\u0001\u0003\u0002\u0017\u0001\u0018\u0001\u0003\u0001\u0000\u0003\u0003\u0001\u0000\u0002\u0003\u0002\u0019\u0001\u001a\u0001\u0000\u0003\u0003\u0001\u0000\u0002\u0003\u0002\u001b\u0002\u0003\u0002\u001c\u0001\u001d\u0001\u0003\u0001\u0000\u0001\u0003\u0001\u001e\u0001\u0000\u0001\u0003\u0002\u001f", 0, array);
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
    
    private static int[] e() {
        final int[] array = new int[130];
        c("\u0000\u0000\u0000$\u0000H\u0000l\u0000\u0090\u0000´\u0000\u00d8\u0000\u00fc\u0000\u0120\u0000\u0144\u0000\u0168\u0000\u018c\u0000\u01b0\u0000\u0168\u0000\u01d4\u0000\u0168\u0000\u01f8\u0000\u021c\u0000\u0240\u0000\u0264\u0000\u0288\u0000\u02ac\u0000\u02d0\u0000\u0168\u0000\u02f4\u0000\u0318\u0000\u0168\u0000\u033c\u0000\u0168\u0000\u0360\u0000\u0168\u0000\u0384\u0000\u03a8\u0000\u03cc\u0000\u03f0\u0000\u0168\u0000\u0414\u0000\u0438\u0000\u045c\u0000\u0480\u0000\u04a4\u0000\u0168\u0000\u018c\u0000\u04c8\u0000\u04ec\u0000\u0510\u0000\u0534\u0000\u0558\u0000\u057c\u0000\u02f4\u0000\u0168\u0000\u05a0\u0000\u0384\u0000\u05c4\u0000\u05e8\u0000\u03cc\u0000\u0168\u0000\u0414\u0000\u0438\u0000\u0168\u0000\u0168\u0000\u060c\u0000\u0630\u0000\u0654\u0000\u0678\u0000\u069c\u0000\u06c0\u0000\u06e4\u0000\u0708\u0000\u072c\u0000\u0750\u0000\u0774\u0000\u0798\u0000\u07bc\u0000\u07e0\u0000\u0804\u0000\u0828\u0000\u084c\u0000\u0870\u0000\u0894\u0000\u08b8\u0000\u08dc\u0000\u0900\u0000\u0924\u0000\u0948\u0000\u096c\u0000\u0168\u0000\u018c\u0000\u0990\u0000\u09b4\u0000\u09d8\u0000\u09fc\u0000\u0a20\u0000\u0a44\u0000\u0168\u0000\u018c\u0000\u018c\u0000\u0a68\u0000\u0a8c\u0000\u0ab0\u0000\u0ad4\u0000\u0af8\u0000\u0b1c\u0000\u0b40\u0000\u0b64\u0000\u0168\u0000\u018c\u0000\u018c\u0000\u0b88\u0000\u0bac\u0000\u0bd0\u0000\u0bf4\u0000\u0c18\u0000\u0c3c\u0000\u0c60\u0000\u0168\u0000\u018c\u0000\u0c84\u0000\u0ca8\u0000\u0168\u0000\u018c\u0000\u018c\u0000\u0ccc\u0000\u0cf0\u0000\u0d14\u0000\u018c\u0000\u0d38\u0000\u0d5c\u0000\u0168\u0000\u018c", 0, array);
        return array;
    }
    
    private static int c(final String s, final int n, final int[] array) {
        int i;
        int n2;
        for (i = 0, n2 = n; i < s.length(); array[n2++] = (s.charAt(i++) << 16 | s.charAt(i++))) {}
        return n2;
    }
    
    private static int[] f() {
        final int[] array = new int[3456];
        a("\u0001\u000b\u0001\f\u0002\u000b\u0001\r\u0001\u000e\u0002\u000f\u0001\u0010\u0001\u000b\u0001\u0011\u0002\u000b\u0003\f\u0005\u000b\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0015\u0001\f\u0001\u000b\u0002\f\u0001\u0016\u0005\f\u0001\u0017\u0014\u0018\u0001\u0019\u000f\u0018\u0003\u001a\u0001\u001b\u0004\u001c\u0001\u0010\u0001\u001d\u0001\u0011\u0001\u001e\u0004\u001a\u0001\u001f\u0001 \u0001!\u0011\u001a\u0014\u0018\u0001\"\u000f\u0018\u0003#\u0001$\r#\u0001%\u0012#\u0014\u000b\u0001&3\u000b\n\u0000\u0001\n\u0019\u0000$\t\u0012\u0000\u0001'6\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0005\u0000\u0001\u000e$\u0000\u0002\u000f\u001c\u0000\u0004(\u0002\u0000\f(\u0001)\u0011(\u0017\u0000\u0001*\r\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0001\f\u0001+\u0002\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001,\u0002\f\u0001-\u0001\f\u0001\u0000\u0002\f\n\u0000\u0002\f\u0001.\u0006\u0000\u0003\f\u0001/\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0003\f\u00010\u0005\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0005\f\u00011\u0003\f\u0013\u0000\u00012\u00013\u000f\u0000\u0003\u001a\u00014\u0007\u0000\u0001\u001e\u0004\u001a\u0001\u0000\u00015\u0012\u001a\u0004\u0000\u0004\u001c\u001c\u0000\u0003\u001a\u00014\u0004\u0000\u00016\u0002\u0000\u0001\u001e\u0004\u001a\u0001\u0000\u00015\u0012\u001a\u0003\u0000\u0001\u001a\u0002\u0000\u0001\u001a\u0001\u0000\n\u001a\u0012\u0000\u0003\u001a\u00014\u0007\u0000\u0001\u001e\u0004\u001a\u0001\u0000\u00015\u00017\u0001!\u0010\u001a\u0013\u0000\u00018\u00019\u000f\u0000\u0003#\u0001\u0000\r#\u0001:\u0012#\u0003\u0000\u0004#\u0001\u0000\n#%\u0000\u0001;\u0001<!\u0000\u0001=\u0001'\u0010\u0000\u0004(\u0002\u0000\"(\u0002\u0000\f(\u0001>\u0001)\u0010(\u0001\u0000\u0002\f\n\u0000\u0001\f\u0001?\u0001\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0002\f\u0001@\u0006\u0000\u0004\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0001\f\u0001A\u0007\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001B\u0001C\b\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001D\u0001E\u0003\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001F\u0002\f\u00034\u0001\u001a\r4\u0001G\u00124\u0001\u0000\u0001H\u000b\u0000\u0003H\u0006\u0000\u0004H\u0001\u0000\tH$7$>\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0002\f\u0001I\u0001\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001J\u0002\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001K\u0001L\u0001M\u0002\f\u0001\u0000\u0002\f\u0001N\u0005\f\u0001O\u0018\u0000\u0001P\f\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0002\f\u0001Q\u0001\f\u0001\u0000\t\f\u0019\u0000\u0001R\u000b\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0003\f\u0001S\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0001\f\u0001T\u0001\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0003\u0000\u00044\u0001\u0000\n4\u0013\u0000\u0002H\u0006\u0000\u0001\u001a\u0003\u0000\u0003H\u0006\u0000\u0004H\u0001\u0000\tH\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0005\f\u0001U\u0003\f\u0001\u0000\u0002\f\n\u0000\u0001V\u0002\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0017\u0000\u0001W\r\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0001\f\u0001X\u0002\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001Y\u0002\f\u0001Z\u0001\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0003\f\u0001[\u0005\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0005\f\u0001\\\u0003\f\u0015\u0000\u0002]\u000e\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001]\u0001^\u0003\f\u0001\u0000\t\f\u0018\u0000\u0001_\f\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0002\f\u0001`\u0001\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001a\u0004\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001b\u0002\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001c\u0001d\u0003\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0001\f\u0001e\u0001\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0002\f\u0001f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001g\u0001h\u0003\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001i\u0002\f\u0017\u0000\u0001j\r\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0001\f\u0001k\u0002\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0003\f\u0001l\u0005\f\u001f\u0000\u0001m\u0005\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001n\u0004\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0002\f\u0001o\u0001\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001p\u0002\f\u0019\u0000\u0001q\u000b\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0003\f\u0001r\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0001\f\u0001s\u0001\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u000f\u0000\u0001t\u0015\u0000\u0002\f\n\u0000\u0002\f\u0001u\u0006\u0000\u0004\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0005\f\u0001v\u0003\f\u0001\u0000\u0002\f\n\u0000\u0001w\u0002\f\u0006\u0000\u0004\f\u0001\u0000\t\f\u0018\u0000\u0001x\f\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0002\f\u0001y\u0001\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001z\u0004\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0006\f\u0001{\u0002\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0005\u0000\u0001|\u0001}\u0003\f\u0001\u0000\t\f\u0001\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0003\f\u0001~\u0005\f\u001f\u0000\u0001\u007f\u0005\u0000\u0002\f\n\u0000\u0003\f\u0006\u0000\u0004\f\u0001\u0000\u0004\f\u0001\u0080\u0004\f\u000f\u0000\u0001\u0081\u0015\u0000\u0002\f\n\u0000\u0002\f\u0001\u0082\u0006\u0000\u0004\f\u0001\u0000\t\f", 0, array);
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
    
    private static int[] b() {
        final int[] array = new int[130];
        b("\u0004\u0000\u0001\u0001\u0003\u0000\u0001\u0001\u0001\u0000\u0001\t\u0002\u0001\u0001\t\u0001\u0001\u0001\t\u0007\u0001\u0001\t\u0002\u0001\u0001\t\u0001\u0001\u0001\t\u0001\u0001\u0001\t\u0004\u0001\u0001\t\u0002\u0001\u0001\u0000\u0002\u0001\u0001\t\u0007\u0001\u0001\u0000\u0001\t\u0003\u0000\u0001\u0001\u0001\u0000\u0001\t\u0002\u0000\u0002\t\u0004\u0001\u0001\u0000\u0001\u0001\u0001\u0000\u0002\u0001\u0002\u0000\u0002\u0001\u0001\u0000\u0006\u0001\u0001\u0000\u0004\u0001\u0001\t\u0005\u0001\u0001\u0000\u0001\u0001\u0001\t\u0003\u0001\u0001\u0000\u0003\u0001\u0001\u0000\u0002\u0001\u0001\t\u0002\u0001\u0001\u0000\u0003\u0001\u0001\u0000\u0002\u0001\u0001\t\u0003\u0001\u0001\t\u0003\u0001\u0001\u0000\u0002\u0001\u0001\u0000\u0001\u0001\u0001\t\u0001\u0001", 0, array);
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
    
    public _CMakeLexer() {
        this(null);
    }
    
    private void a(final int n) {
        this.myStack.push((Object)this.yystate());
        this.yybegin(n);
    }
    
    private void g() {
        this.yybegin((int)this.myStack.pop());
    }
    
    private int a(final IElementType myComplexTokenType) {
        this.myComplexTokenType = myComplexTokenType;
        return this.myScanStart = this.zzMarkedPos;
    }
    
    private IElementType d() {
        this.zzStartRead = this.myScanStart;
        return this.myComplexTokenType;
    }
    
    protected void onReset() {
        this.myStack.clear();
    }
    
    public _CMakeLexer(final Reader zzReader) {
        this.zzLexicalState = 0;
        this.zzBuffer = "";
        this.zzAtBOL = true;
        this.zzFin = new boolean[16385];
        this.myStack = (Stack<Integer>)new Stack();
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
    
    private boolean a() throws IOException {
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
    
    private void b(final int n) {
        String s;
        try {
            s = _CMakeLexer.ZZ_ERROR_MSG[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            s = _CMakeLexer.ZZ_ERROR_MSG[0];
        }
        throw new Error(s);
    }
    
    public void yypushback(final int n) {
        try {
            if (n > this.yylength()) {
                this.b(2);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw b(ex);
        }
        this.zzMarkedPos -= n;
    }
    
    public IElementType advance() throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzEndRead:I
        //     4: istore          5
        //     6: aload_0        
        //     7: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzBuffer:Ljava/lang/CharSequence;
        //    10: astore          6
        //    12: getstatic       com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.ZZ_TRANS:[I
        //    15: astore          7
        //    17: getstatic       com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.ZZ_ROWMAP:[I
        //    20: astore          8
        //    22: getstatic       com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.ZZ_ATTRIBUTE:[I
        //    25: astore          9
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //    31: istore          4
        //    33: iconst_m1      
        //    34: istore_2       
        //    35: aload_0        
        //    36: aload_0        
        //    37: iload           4
        //    39: dup_x1         
        //    40: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzStartRead:I
        //    43: dup_x1         
        //    44: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzCurrentPos:I
        //    47: istore_3       
        //    48: aload_0        
        //    49: getstatic       com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.ZZ_LEXSTATE:[I
        //    52: aload_0        
        //    53: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzLexicalState:I
        //    56: iaload         
        //    57: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzState:I
        //    60: aload           9
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzState:I
        //    66: iaload         
        //    67: istore          10
        //    69: iload           10
        //    71: iconst_1       
        //    72: iand           
        //    73: iconst_1       
        //    74: if_icmpne       82
        //    77: aload_0        
        //    78: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzState:I
        //    81: istore_2       
        //    82: iload_3        
        //    83: iload           5
        //    85: if_icmpge       105
        //    88: aload           6
        //    90: iload_3        
        //    91: invokestatic    java/lang/Character.codePointAt:(Ljava/lang/CharSequence;I)I
        //    94: istore_1       
        //    95: iload_3        
        //    96: iload_1        
        //    97: invokestatic    java/lang/Character.charCount:(I)I
        //   100: iadd           
        //   101: istore_3       
        //   102: goto            181
        //   105: aload_0        
        //   106: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzAtEOF:Z
        //   109: ifeq            117
        //   112: iconst_m1      
        //   113: istore_1       
        //   114: goto            262
        //   117: aload_0        
        //   118: iload_3        
        //   119: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzCurrentPos:I
        //   122: aload_0        
        //   123: iload           4
        //   125: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //   128: aload_0        
        //   129: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.a:()Z
        //   132: istore          11
        //   134: aload_0        
        //   135: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzCurrentPos:I
        //   138: istore_3       
        //   139: aload_0        
        //   140: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //   143: istore          4
        //   145: aload_0        
        //   146: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzBuffer:Ljava/lang/CharSequence;
        //   149: astore          6
        //   151: aload_0        
        //   152: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzEndRead:I
        //   155: istore          5
        //   157: iload           11
        //   159: ifeq            167
        //   162: iconst_m1      
        //   163: istore_1       
        //   164: goto            262
        //   167: aload           6
        //   169: iload_3        
        //   170: invokestatic    java/lang/Character.codePointAt:(Ljava/lang/CharSequence;I)I
        //   173: istore_1       
        //   174: iload_3        
        //   175: iload_1        
        //   176: invokestatic    java/lang/Character.charCount:(I)I
        //   179: iadd           
        //   180: istore_3       
        //   181: aload           7
        //   183: aload           8
        //   185: aload_0        
        //   186: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzState:I
        //   189: iaload         
        //   190: iload_1        
        //   191: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.ZZ_CMAP:(I)I
        //   194: iadd           
        //   195: iaload         
        //   196: istore          11
        //   198: iload           11
        //   200: iconst_m1      
        //   201: if_icmpne       211
        //   204: goto            262
        //   207: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   210: athrow         
        //   211: aload_0        
        //   212: iload           11
        //   214: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzState:I
        //   217: aload           9
        //   219: aload_0        
        //   220: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzState:I
        //   223: iaload         
        //   224: istore          10
        //   226: iload           10
        //   228: iconst_1       
        //   229: iand           
        //   230: iconst_1       
        //   231: if_icmpne       259
        //   234: aload_0        
        //   235: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzState:I
        //   238: istore_2       
        //   239: iload_3        
        //   240: istore          4
        //   242: iload           10
        //   244: bipush          8
        //   246: iand           
        //   247: bipush          8
        //   249: if_icmpne       259
        //   252: goto            262
        //   255: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   258: athrow         
        //   259: goto            82
        //   262: aload_0        
        //   263: iload           4
        //   265: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //   268: iload_1        
        //   269: iconst_m1      
        //   270: if_icmpne       411
        //   273: aload_0        
        //   274: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzStartRead:I
        //   277: aload_0        
        //   278: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzCurrentPos:I
        //   281: if_icmpne       411
        //   284: goto            291
        //   287: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   290: athrow         
        //   291: aload_0        
        //   292: iconst_1       
        //   293: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzAtEOF:Z
        //   296: aload_0        
        //   297: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzLexicalState:I
        //   300: lookupswitch {
        //                0: 364
        //                2: 381
        //                6: 395
        //              131: 378
        //              132: 392
        //              133: 406
        //          default: 409
        //        }
        //   360: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   363: athrow         
        //   364: aload_0        
        //   365: bipush          14
        //   367: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yybegin:(I)V
        //   370: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.EOL:Lcom/intellij/psi/tree/IElementType;
        //   373: areturn        
        //   374: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   377: athrow         
        //   378: goto            1649
        //   381: aload_0        
        //   382: bipush          14
        //   384: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yybegin:(I)V
        //   387: aload_0        
        //   388: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.d:()Lcom/intellij/psi/tree/IElementType;
        //   391: areturn        
        //   392: goto            1649
        //   395: aload_0        
        //   396: bipush          14
        //   398: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yybegin:(I)V
        //   401: aload_0        
        //   402: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.d:()Lcom/intellij/psi/tree/IElementType;
        //   405: areturn        
        //   406: goto            1649
        //   409: aconst_null    
        //   410: areturn        
        //   411: iload_2        
        //   412: ifge            423
        //   415: iload_2        
        //   416: goto            428
        //   419: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   422: athrow         
        //   423: getstatic       com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.ZZ_ACTION:[I
        //   426: iload_2        
        //   427: iaload         
        //   428: tableswitch {
        //                2: 692
        //                3: 704
        //                4: 711
        //                5: 718
        //                6: 725
        //                7: 732
        //                8: 744
        //                9: 751
        //               10: 754
        //               11: 761
        //               12: 790
        //               13: 801
        //               14: 808
        //               15: 819
        //               16: 834
        //               17: 841
        //               18: 868
        //               19: 1182
        //               20: 1237
        //               21: 1248
        //               22: 1567
        //               23: 1574
        //               24: 1581
        //               25: 1588
        //               26: 1595
        //               27: 1602
        //               28: 1609
        //               29: 1616
        //               30: 1623
        //               31: 1630
        //               32: 1637
        //               33: 701
        //               34: 708
        //               35: 715
        //               36: 722
        //               37: 729
        //               38: 741
        //               39: 748
        //               40: 751
        //               41: 758
        //               42: 787
        //               43: 798
        //               44: 805
        //               45: 816
        //               46: 831
        //               47: 838
        //               48: 865
        //               49: 1179
        //               50: 1234
        //               51: 1245
        //               52: 1564
        //               53: 1571
        //               54: 1578
        //               55: 1585
        //               56: 1592
        //               57: 1599
        //               58: 1606
        //               59: 1613
        //               60: 1620
        //               61: 1627
        //               62: 1634
        //               63: 1641
        //          default: 1644
        //        }
        //   692: aload_0        
        //   693: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.d:()Lcom/intellij/psi/tree/IElementType;
        //   696: areturn        
        //   697: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   700: athrow         
        //   701: goto            1649
        //   704: getstatic       com/intellij/psi/TokenType.BAD_CHARACTER:Lcom/intellij/psi/tree/IElementType;
        //   707: areturn        
        //   708: goto            1649
        //   711: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.ID:Lcom/intellij/psi/tree/IElementType;
        //   714: areturn        
        //   715: goto            1649
        //   718: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.EOL:Lcom/intellij/psi/tree/IElementType;
        //   721: areturn        
        //   722: goto            1649
        //   725: getstatic       com/intellij/psi/TokenType.WHITE_SPACE:Lcom/intellij/psi/tree/IElementType;
        //   728: areturn        
        //   729: goto            1649
        //   732: aload_0        
        //   733: iconst_4       
        //   734: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.a:(I)V
        //   737: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.LPAR:Lcom/intellij/psi/tree/IElementType;
        //   740: areturn        
        //   741: goto            1649
        //   744: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   747: areturn        
        //   748: goto            1649
        //   751: goto            1649
        //   754: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.LITERAL:Lcom/intellij/psi/tree/IElementType;
        //   757: areturn        
        //   758: goto            1649
        //   761: aload_0        
        //   762: bipush          8
        //   764: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.a:(I)V
        //   767: aload_0        
        //   768: aload_0        
        //   769: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //   772: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.myScanStart:I
        //   775: aload_0        
        //   776: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.LITERAL:Lcom/intellij/psi/tree/IElementType;
        //   779: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.a:(Lcom/intellij/psi/tree/IElementType;)I
        //   782: pop            
        //   783: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.QUOTE:Lcom/intellij/psi/tree/IElementType;
        //   786: areturn        
        //   787: goto            1649
        //   790: aload_0        
        //   791: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.g:()V
        //   794: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.RPAR:Lcom/intellij/psi/tree/IElementType;
        //   797: areturn        
        //   798: goto            1649
        //   801: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.SEMI:Lcom/intellij/psi/tree/IElementType;
        //   804: areturn        
        //   805: goto            1649
        //   808: aload_0        
        //   809: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.g:()V
        //   812: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.QUOTE:Lcom/intellij/psi/tree/IElementType;
        //   815: areturn        
        //   816: goto            1649
        //   819: aload_0        
        //   820: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.LITERAL:Lcom/intellij/psi/tree/IElementType;
        //   823: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.a:(Lcom/intellij/psi/tree/IElementType;)I
        //   826: pop            
        //   827: getstatic       com/intellij/psi/TokenType.BAD_CHARACTER:Lcom/intellij/psi/tree/IElementType;
        //   830: areturn        
        //   831: goto            1649
        //   834: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.IF:Lcom/intellij/psi/tree/IElementType;
        //   837: areturn        
        //   838: goto            1649
        //   841: aload_0        
        //   842: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.myBracketArgLength:I
        //   845: aload_0        
        //   846: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yylength:()I
        //   849: if_icmpne       865
        //   852: aload_0        
        //   853: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.g:()V
        //   856: aload_0        
        //   857: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.d:()Lcom/intellij/psi/tree/IElementType;
        //   860: areturn        
        //   861: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   864: athrow         
        //   865: goto            1649
        //   868: bipush          9
        //   870: istore          11
        //   872: aload_0        
        //   873: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzStartRead:I
        //   876: istore          12
        //   878: aload_0        
        //   879: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzFin:[Z
        //   882: arraylength    
        //   883: aload           6
        //   885: invokeinterface java/lang/CharSequence.length:()I
        //   890: if_icmpgt       915
        //   893: aload_0        
        //   894: aload           6
        //   896: invokeinterface java/lang/CharSequence.length:()I
        //   901: iconst_1       
        //   902: iadd           
        //   903: newarray        Z
        //   905: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzFin:[Z
        //   908: goto            915
        //   911: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   914: athrow         
        //   915: aload_0        
        //   916: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzFin:[Z
        //   919: astore          13
        //   921: iload           11
        //   923: iconst_m1      
        //   924: if_icmpeq       1010
        //   927: iload           12
        //   929: aload_0        
        //   930: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //   933: if_icmpge       1010
        //   936: goto            943
        //   939: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   942: athrow         
        //   943: aload           13
        //   945: iload           12
        //   947: aload           9
        //   949: iload           11
        //   951: iaload         
        //   952: iconst_1       
        //   953: iand           
        //   954: iconst_1       
        //   955: if_icmpne       973
        //   958: goto            965
        //   961: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   964: athrow         
        //   965: iconst_1       
        //   966: goto            974
        //   969: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   972: athrow         
        //   973: iconst_0       
        //   974: bastore        
        //   975: aload           6
        //   977: iload           12
        //   979: invokestatic    java/lang/Character.codePointAt:(Ljava/lang/CharSequence;I)I
        //   982: istore_1       
        //   983: iload           12
        //   985: iload_1        
        //   986: invokestatic    java/lang/Character.charCount:(I)I
        //   989: iadd           
        //   990: istore          12
        //   992: aload           7
        //   994: aload           8
        //   996: iload           11
        //   998: iaload         
        //   999: iload_1        
        //  1000: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.ZZ_CMAP:(I)I
        //  1003: iadd           
        //  1004: iaload         
        //  1005: istore          11
        //  1007: goto            921
        //  1010: iload           11
        //  1012: iconst_m1      
        //  1013: if_icmpeq       1051
        //  1016: aload           13
        //  1018: iload           12
        //  1020: iinc            12, 1
        //  1023: aload           9
        //  1025: iload           11
        //  1027: iaload         
        //  1028: iconst_1       
        //  1029: iand           
        //  1030: iconst_1       
        //  1031: if_icmpne       1049
        //  1034: goto            1041
        //  1037: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1040: athrow         
        //  1041: iconst_1       
        //  1042: goto            1050
        //  1045: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1048: athrow         
        //  1049: iconst_0       
        //  1050: bastore        
        //  1051: iload           12
        //  1053: aload_0        
        //  1054: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //  1057: if_icmpgt       1076
        //  1060: aload           13
        //  1062: iload           12
        //  1064: iinc            12, 1
        //  1067: iconst_0       
        //  1068: bastore        
        //  1069: goto            1051
        //  1072: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1075: athrow         
        //  1076: bipush          8
        //  1078: istore          11
        //  1080: aload_0        
        //  1081: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //  1084: istore          12
        //  1086: aload           13
        //  1088: iload           12
        //  1090: baload         
        //  1091: ifeq            1112
        //  1094: aload           9
        //  1096: iload           11
        //  1098: iaload         
        //  1099: iconst_1       
        //  1100: iand           
        //  1101: iconst_1       
        //  1102: if_icmpeq       1147
        //  1105: goto            1112
        //  1108: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1111: athrow         
        //  1112: aload           6
        //  1114: iload           12
        //  1116: invokestatic    java/lang/Character.codePointBefore:(Ljava/lang/CharSequence;I)I
        //  1119: istore_1       
        //  1120: iload           12
        //  1122: iload_1        
        //  1123: invokestatic    java/lang/Character.charCount:(I)I
        //  1126: isub           
        //  1127: istore          12
        //  1129: aload           7
        //  1131: aload           8
        //  1133: iload           11
        //  1135: iaload         
        //  1136: iload_1        
        //  1137: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.ZZ_CMAP:(I)I
        //  1140: iadd           
        //  1141: iaload         
        //  1142: istore          11
        //  1144: goto            1086
        //  1147: aload_0        
        //  1148: iload           12
        //  1150: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //  1153: aload_0        
        //  1154: bipush          6
        //  1156: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.a:(I)V
        //  1159: aload_0        
        //  1160: aload_0        
        //  1161: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yylength:()I
        //  1164: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.myBracketArgLength:I
        //  1167: aload_0        
        //  1168: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.LITERAL:Lcom/intellij/psi/tree/IElementType;
        //  1171: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.a:(Lcom/intellij/psi/tree/IElementType;)I
        //  1174: pop            
        //  1175: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.BRACKET_ARG_START:Lcom/intellij/psi/tree/IElementType;
        //  1178: areturn        
        //  1179: goto            1649
        //  1182: aload_0        
        //  1183: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.myBracketArgLength:I
        //  1186: aload_0        
        //  1187: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yylength:()I
        //  1190: if_icmpne       1234
        //  1193: aload_0        
        //  1194: aload_0        
        //  1195: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yylength:()I
        //  1198: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yypushback:(I)V
        //  1201: aload_0        
        //  1202: bipush          10
        //  1204: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yybegin:(I)V
        //  1207: aload_0        
        //  1208: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.myScanStart:I
        //  1211: aload_0        
        //  1212: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzStartRead:I
        //  1215: if_icmpge       1234
        //  1218: goto            1225
        //  1221: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1224: athrow         
        //  1225: aload_0        
        //  1226: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.d:()Lcom/intellij/psi/tree/IElementType;
        //  1229: areturn        
        //  1230: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1233: athrow         
        //  1234: goto            1649
        //  1237: aload_0        
        //  1238: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.g:()V
        //  1241: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.BRACKET_ARG_END:Lcom/intellij/psi/tree/IElementType;
        //  1244: areturn        
        //  1245: goto            1649
        //  1248: bipush          7
        //  1250: istore          11
        //  1252: aload_0        
        //  1253: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzStartRead:I
        //  1256: istore          12
        //  1258: aload_0        
        //  1259: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzFin:[Z
        //  1262: arraylength    
        //  1263: aload           6
        //  1265: invokeinterface java/lang/CharSequence.length:()I
        //  1270: if_icmpgt       1295
        //  1273: aload_0        
        //  1274: aload           6
        //  1276: invokeinterface java/lang/CharSequence.length:()I
        //  1281: iconst_1       
        //  1282: iadd           
        //  1283: newarray        Z
        //  1285: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzFin:[Z
        //  1288: goto            1295
        //  1291: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1294: athrow         
        //  1295: aload_0        
        //  1296: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzFin:[Z
        //  1299: astore          13
        //  1301: iload           11
        //  1303: iconst_m1      
        //  1304: if_icmpeq       1390
        //  1307: iload           12
        //  1309: aload_0        
        //  1310: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //  1313: if_icmpge       1390
        //  1316: goto            1323
        //  1319: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1322: athrow         
        //  1323: aload           13
        //  1325: iload           12
        //  1327: aload           9
        //  1329: iload           11
        //  1331: iaload         
        //  1332: iconst_1       
        //  1333: iand           
        //  1334: iconst_1       
        //  1335: if_icmpne       1353
        //  1338: goto            1345
        //  1341: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1344: athrow         
        //  1345: iconst_1       
        //  1346: goto            1354
        //  1349: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1352: athrow         
        //  1353: iconst_0       
        //  1354: bastore        
        //  1355: aload           6
        //  1357: iload           12
        //  1359: invokestatic    java/lang/Character.codePointAt:(Ljava/lang/CharSequence;I)I
        //  1362: istore_1       
        //  1363: iload           12
        //  1365: iload_1        
        //  1366: invokestatic    java/lang/Character.charCount:(I)I
        //  1369: iadd           
        //  1370: istore          12
        //  1372: aload           7
        //  1374: aload           8
        //  1376: iload           11
        //  1378: iaload         
        //  1379: iload_1        
        //  1380: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.ZZ_CMAP:(I)I
        //  1383: iadd           
        //  1384: iaload         
        //  1385: istore          11
        //  1387: goto            1301
        //  1390: iload           11
        //  1392: iconst_m1      
        //  1393: if_icmpeq       1431
        //  1396: aload           13
        //  1398: iload           12
        //  1400: iinc            12, 1
        //  1403: aload           9
        //  1405: iload           11
        //  1407: iaload         
        //  1408: iconst_1       
        //  1409: iand           
        //  1410: iconst_1       
        //  1411: if_icmpne       1429
        //  1414: goto            1421
        //  1417: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1420: athrow         
        //  1421: iconst_1       
        //  1422: goto            1430
        //  1425: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1428: athrow         
        //  1429: iconst_0       
        //  1430: bastore        
        //  1431: iload           12
        //  1433: aload_0        
        //  1434: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //  1437: if_icmpgt       1456
        //  1440: aload           13
        //  1442: iload           12
        //  1444: iinc            12, 1
        //  1447: iconst_0       
        //  1448: bastore        
        //  1449: goto            1431
        //  1452: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1455: athrow         
        //  1456: bipush          8
        //  1458: istore          11
        //  1460: aload_0        
        //  1461: getfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //  1464: istore          12
        //  1466: aload           13
        //  1468: iload           12
        //  1470: baload         
        //  1471: ifeq            1492
        //  1474: aload           9
        //  1476: iload           11
        //  1478: iaload         
        //  1479: iconst_1       
        //  1480: iand           
        //  1481: iconst_1       
        //  1482: if_icmpeq       1527
        //  1485: goto            1492
        //  1488: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //  1491: athrow         
        //  1492: aload           6
        //  1494: iload           12
        //  1496: invokestatic    java/lang/Character.codePointBefore:(Ljava/lang/CharSequence;I)I
        //  1499: istore_1       
        //  1500: iload           12
        //  1502: iload_1        
        //  1503: invokestatic    java/lang/Character.charCount:(I)I
        //  1506: isub           
        //  1507: istore          12
        //  1509: aload           7
        //  1511: aload           8
        //  1513: iload           11
        //  1515: iaload         
        //  1516: iload_1        
        //  1517: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.ZZ_CMAP:(I)I
        //  1520: iadd           
        //  1521: iaload         
        //  1522: istore          11
        //  1524: goto            1466
        //  1527: aload_0        
        //  1528: iload           12
        //  1530: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.zzMarkedPos:I
        //  1533: aload_0        
        //  1534: iconst_2       
        //  1535: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.a:(I)V
        //  1538: aload_0        
        //  1539: aload_0        
        //  1540: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yylength:()I
        //  1543: iconst_1       
        //  1544: isub           
        //  1545: putfield        com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.myBracketArgLength:I
        //  1548: aload_0        
        //  1549: aload_0        
        //  1550: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yylength:()I
        //  1553: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.yypushback:(I)V
        //  1556: aload_0        
        //  1557: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //  1560: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.a:(Lcom/intellij/psi/tree/IElementType;)I
        //  1563: pop            
        //  1564: goto            1649
        //  1567: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.ELSE:Lcom/intellij/psi/tree/IElementType;
        //  1570: areturn        
        //  1571: goto            1649
        //  1574: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.ENDIF:Lcom/intellij/psi/tree/IElementType;
        //  1577: areturn        
        //  1578: goto            1649
        //  1581: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.WHILE:Lcom/intellij/psi/tree/IElementType;
        //  1584: areturn        
        //  1585: goto            1649
        //  1588: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.MACRO:Lcom/intellij/psi/tree/IElementType;
        //  1591: areturn        
        //  1592: goto            1649
        //  1595: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.ELSEIF:Lcom/intellij/psi/tree/IElementType;
        //  1598: areturn        
        //  1599: goto            1649
        //  1602: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.FOREACH:Lcom/intellij/psi/tree/IElementType;
        //  1605: areturn        
        //  1606: goto            1649
        //  1609: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.FUNCTION:Lcom/intellij/psi/tree/IElementType;
        //  1612: areturn        
        //  1613: goto            1649
        //  1616: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.ENDWHILE:Lcom/intellij/psi/tree/IElementType;
        //  1619: areturn        
        //  1620: goto            1649
        //  1623: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.ENDMACRO:Lcom/intellij/psi/tree/IElementType;
        //  1626: areturn        
        //  1627: goto            1649
        //  1630: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.ENDFOREACH:Lcom/intellij/psi/tree/IElementType;
        //  1633: areturn        
        //  1634: goto            1649
        //  1637: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.ENDFUNCTION:Lcom/intellij/psi/tree/IElementType;
        //  1640: areturn        
        //  1641: goto            1649
        //  1644: aload_0        
        //  1645: iconst_1       
        //  1646: invokespecial   com/jetbrains/cidr/cpp/cmake/psi/_CMakeLexer.b:(I)V
        //  1649: goto            27
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  198    207    207    211    Ljava/io/IOException;
        //  242    255    255    259    Ljava/io/IOException;
        //  262    284    287    291    Ljava/io/IOException;
        //  273    360    360    364    Ljava/io/IOException;
        //  291    374    374    378    Ljava/io/IOException;
        //  411    419    419    423    Ljava/io/IOException;
        //  428    697    697    701    Ljava/io/IOException;
        //  841    861    861    865    Ljava/io/IOException;
        //  878    908    911    915    Ljava/io/IOException;
        //  921    936    939    943    Ljava/io/IOException;
        //  927    958    961    965    Ljava/io/IOException;
        //  943    969    969    973    Ljava/io/IOException;
        //  1010   1034   1037   1041   Ljava/io/IOException;
        //  1016   1045   1045   1049   Ljava/io/IOException;
        //  1051   1072   1072   1076   Ljava/io/IOException;
        //  1086   1105   1108   1112   Ljava/io/IOException;
        //  1182   1218   1221   1225   Ljava/io/IOException;
        //  1193   1230   1230   1234   Ljava/io/IOException;
        //  1258   1288   1291   1295   Ljava/io/IOException;
        //  1301   1316   1319   1323   Ljava/io/IOException;
        //  1307   1338   1341   1345   Ljava/io/IOException;
        //  1323   1349   1349   1353   Ljava/io/IOException;
        //  1390   1414   1417   1421   Ljava/io/IOException;
        //  1396   1425   1425   1429   Ljava/io/IOException;
        //  1431   1452   1452   1456   Ljava/io/IOException;
        //  1466   1485   1488   1492   Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0291:
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
        ZZ_LEXSTATE = new int[] { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 6, 6 };
        ZZ_CMAP_Z = a("\u0001\u0000\u0087@");
        ZZ_CMAP_Y = a("\u0001\u0000\u0001\u0001\u0001\u0002}\u0001");
        ZZ_CMAP_A = a("\t\u0000\u0001\u0007\u0001\u0005\u0001\u0000\u0001\u0007\u0001\u0004\u0012\u0000\u0001\u0006\u0001\u0000\u0001\u0003\u0001\n\u0001\u000b\u0003\u0000\u0001\b\u0001\t\u0006\u0000\n\u0002\u0001\u0000\u0001\u0010\u0001\u0000\u0001\u0013\u0002\u0000\u0001\f\u0001 \u0001\u0001\u0001!\u0001\u001c\u0001\u0018\u0001\u0017\u0001\u0001\u0001\u001e\u0001\u0016\u0002\u0001\u0001\u0019\u0001#\u0001\u000f\u0001\u001f\u0002\u0001\u0001\u000e\u0001\u001b\u0001\r\u0001\"\u0001\u0001\u0001\u001d\u0003\u0001\u0001\u0012\u0001\u0011\u0001\u0014\u0001\f\u0001\u0001\u0001\u0000\u0001 \u0001\u0001\u0001!\u0001\u001c\u0001\u0018\u0001\u0017\u0001\u0001\u0001\u001e\u0001\u0016\u0002\u0001\u0001\u0019\u0001#\u0001\u000f\u0001\u001f\u0002\u0001\u0001\u000e\u0001\u001b\u0001\r\u0001\"\u0001\u0001\u0001\u001d\u0003\u0001µ\u0000\u0002\u0015M\u0000\u0001\u001a");
        ZZ_ACTION = c();
        ZZ_ROWMAP = e();
        ZZ_TRANS = f();
        ZZ_ERROR_MSG = new String[] { "Unknown internal scanner error", "Error: could not match input", "Error: pushback value was too large" };
        ZZ_ATTRIBUTE = b();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
