// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.cmakecache.psi;

import com.intellij.psi.tree.IElementType;
import java.io.IOException;
import java.io.Reader;
import com.intellij.lexer.FlexLexer;

class _CMakeCacheLexer implements FlexLexer
{
    public static final int YYEOF = -1;
    private static final int ZZ_BUFFERSIZE = 16384;
    public static final int YYINITIAL = 0;
    public static final int IN_CACHE_ENTRY = 2;
    public static final int IN_KEY = 4;
    public static final int IN_TYPE = 6;
    public static final int IN_VALUE = 8;
    public static final int IN_QUOTED_KEY = 10;
    public static final int IN_QUOTED_VALUE = 12;
    public static final int IN_EOF = 14;
    private static final int[] ZZ_LEXSTATE;
    static final char[] ZZ_CMAP_Z;
    static final char[] ZZ_CMAP_Y;
    static final char[] ZZ_CMAP_A;
    private static final int[] ZZ_ACTION;
    private static final String ZZ_ACTION_PACKED_0 = "\t\u0000\u0001\u0001\u0002\u0002\u0001\u0003\u0001\u0001\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0002\r\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0004";
    private static final int[] ZZ_ROWMAP;
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000\u000b\u0000\u0016\u0000!\u0000,\u00007\u0000B\u0000M\u0000X\u0000c\u0000n\u0000y\u0000\u0084\u0000\u008f\u0000\u009a\u0000n\u0000n\u0000n\u0000n\u0000n\u0000n\u0000¥\u0000°\u0000n\u0000»\u0000\u00c6\u0000\u00d1\u0000\u00dc\u0000n\u0000n\u0000\u00e7";
    private static final int[] ZZ_TRANS;
    private static final String ZZ_TRANS_PACKED_0 = "\u0003\n\u0001\u000b\u0002\n\u0001\f\u0001\r\u0001\u000e\u0001\n\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0010\u0001\u0013\u0001\u0014\u0001\u0010\u0001\u0015\u0004\u0010\u0001\u0011\u0001\u0012\u0001\u0010\u0001\u0013\u0001\u0014\u0001\u0010\u0001\u0016\u0003\u0010\u0001\u0017\u0002\u0015\u0001\u0018\u0002\u0017\u0001\u0019\u0004\u0017\u0002\u001a\u0001\u0012\u0001\u0018\u0002\u001a\u0001\u0019\u0004\u001a\u0003\u001b\u0001\u000b\u0001\u001b\u0001\u0014\u0001\f\u0004\u001b\u0003\u001c\u0001\u0015\u0001\u001d\u0001\u001c\u0001\u0015\u0004\u001c\u0003\u001b\u0001\u000b\u0001\u001b\u0001\u001e\u0001\f\u0004\u001b\u000b\u0015\u0003\n\u0001\u0000\u0002\n\u0002\u0000\u0003\n\u000e\u0000\u0001\u000b\u000e\u0000\u0001\r\u0003\u0000\u0003\n\u0001\u0000\u0002\n\u0002\u0000\u0001\u000f\u0002\n\u0003\u000f\u0001\u0000\u0002\u000f\u0001\u0000\u0001\u001f\u0001\u000f\u0001\n\u0001\u000f\u0007\u0000\u0001\u0016\u0003\u0000\u0001\u0017\u0003\u0000\u0002\u0017\u0001\u0000\u0004\u0017\u0003\u0000\u0001\u0018\u0007\u0000\u0002\u001a\u0002\u0000\u0002\u001a\u0001\u0000\u0004\u001a\u0003\u001b\u0001\u0000\u0001\u001b\u0002\u0000\u0004\u001b\u0003\u001c\u0002\u0000\u0001\u001c\u0001\u0000\u0004\u001c\u0003\u001f\u0001\u0000\u0002\u001f\u0001\u0000\u0002\u001f\u0001\u0000\u0001\u001f";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG;
    private static final int[] ZZ_ATTRIBUTE;
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\t\u0000\u0001\u0001\u0001\t\u0004\u0001\u0006\t\u0002\u0001\u0001\t\u0004\u0001\u0002\t\u0001\u0001";
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
    
    public static int ZZ_CMAP(final int n) {
        return _CMakeCacheLexer.ZZ_CMAP_A[_CMakeCacheLexer.ZZ_CMAP_Y[_CMakeCacheLexer.ZZ_CMAP_Z[n >> 12] | (n >> 6 & 0x3F)] | (n & 0x3F)];
    }
    
    private static int[] b() {
        final int[] array = new int[31];
        b("\t\u0000\u0001\u0001\u0002\u0002\u0001\u0003\u0001\u0001\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0002\r\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0004", 0, array);
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
        final int[] array = new int[31];
        d("\u0000\u0000\u0000\u000b\u0000\u0016\u0000!\u0000,\u00007\u0000B\u0000M\u0000X\u0000c\u0000n\u0000y\u0000\u0084\u0000\u008f\u0000\u009a\u0000n\u0000n\u0000n\u0000n\u0000n\u0000n\u0000¥\u0000°\u0000n\u0000»\u0000\u00c6\u0000\u00d1\u0000\u00dc\u0000n\u0000n\u0000\u00e7", 0, array);
        return array;
    }
    
    private static int d(final String s, final int n, final int[] array) {
        int i;
        int n2;
        for (i = 0, n2 = n; i < s.length(); array[n2++] = (s.charAt(i++) << 16 | s.charAt(i++))) {}
        return n2;
    }
    
    private static int[] e() {
        final int[] array = new int[242];
        a("\u0003\n\u0001\u000b\u0002\n\u0001\f\u0001\r\u0001\u000e\u0001\n\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0010\u0001\u0013\u0001\u0014\u0001\u0010\u0001\u0015\u0004\u0010\u0001\u0011\u0001\u0012\u0001\u0010\u0001\u0013\u0001\u0014\u0001\u0010\u0001\u0016\u0003\u0010\u0001\u0017\u0002\u0015\u0001\u0018\u0002\u0017\u0001\u0019\u0004\u0017\u0002\u001a\u0001\u0012\u0001\u0018\u0002\u001a\u0001\u0019\u0004\u001a\u0003\u001b\u0001\u000b\u0001\u001b\u0001\u0014\u0001\f\u0004\u001b\u0003\u001c\u0001\u0015\u0001\u001d\u0001\u001c\u0001\u0015\u0004\u001c\u0003\u001b\u0001\u000b\u0001\u001b\u0001\u001e\u0001\f\u0004\u001b\u000b\u0015\u0003\n\u0001\u0000\u0002\n\u0002\u0000\u0003\n\u000e\u0000\u0001\u000b\u000e\u0000\u0001\r\u0003\u0000\u0003\n\u0001\u0000\u0002\n\u0002\u0000\u0001\u000f\u0002\n\u0003\u000f\u0001\u0000\u0002\u000f\u0001\u0000\u0001\u001f\u0001\u000f\u0001\n\u0001\u000f\u0007\u0000\u0001\u0016\u0003\u0000\u0001\u0017\u0003\u0000\u0002\u0017\u0001\u0000\u0004\u0017\u0003\u0000\u0001\u0018\u0007\u0000\u0002\u001a\u0002\u0000\u0002\u001a\u0001\u0000\u0004\u001a\u0003\u001b\u0001\u0000\u0001\u001b\u0002\u0000\u0004\u001b\u0003\u001c\u0002\u0000\u0001\u001c\u0001\u0000\u0004\u001c\u0003\u001f\u0001\u0000\u0002\u001f\u0001\u0000\u0002\u001f\u0001\u0000\u0001\u001f", 0, array);
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
    
    private static int[] c() {
        final int[] array = new int[31];
        c("\t\u0000\u0001\u0001\u0001\t\u0004\u0001\u0006\t\u0002\u0001\u0001\t\u0004\u0001\u0002\t\u0001\u0001", 0, array);
        return array;
    }
    
    private static int c(final String s, final int n, final int[] array) {
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
    
    public _CMakeCacheLexer() {
        this(null);
    }
    
    _CMakeCacheLexer(final Reader zzReader) {
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
    
    private boolean d() throws IOException {
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
            s = _CMakeCacheLexer.ZZ_ERROR_MSG[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            s = _CMakeCacheLexer.ZZ_ERROR_MSG[0];
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
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzEndRead:I
        //     4: istore          5
        //     6: aload_0        
        //     7: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzBuffer:Ljava/lang/CharSequence;
        //    10: astore          6
        //    12: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.ZZ_TRANS:[I
        //    15: astore          7
        //    17: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.ZZ_ROWMAP:[I
        //    20: astore          8
        //    22: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.ZZ_ATTRIBUTE:[I
        //    25: astore          9
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzMarkedPos:I
        //    31: istore          4
        //    33: iload           4
        //    35: aload_0        
        //    36: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzStartRead:I
        //    39: if_icmple       275
        //    42: aload           6
        //    44: iload           4
        //    46: iconst_1       
        //    47: isub           
        //    48: invokeinterface java/lang/CharSequence.charAt:(I)C
        //    53: lookupswitch {
        //               10: 124
        //               11: 124
        //               12: 124
        //               13: 136
        //              133: 124
        //             8232: 124
        //             8233: 124
        //          default: 270
        //        }
        //   120: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   123: athrow         
        //   124: aload_0        
        //   125: iconst_1       
        //   126: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzAtBOL:Z
        //   129: goto            275
        //   132: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   135: athrow         
        //   136: iload           4
        //   138: iload           5
        //   140: if_icmpge       180
        //   143: aload_0        
        //   144: aload           6
        //   146: iload           4
        //   148: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   153: bipush          10
        //   155: if_icmpeq       173
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   164: athrow         
        //   165: iconst_1       
        //   166: goto            174
        //   169: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   172: athrow         
        //   173: iconst_0       
        //   174: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzAtBOL:Z
        //   177: goto            275
        //   180: aload_0        
        //   181: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzAtEOF:Z
        //   184: ifeq            199
        //   187: aload_0        
        //   188: iconst_0       
        //   189: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzAtBOL:Z
        //   192: goto            275
        //   195: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   198: athrow         
        //   199: aload_0        
        //   200: invokespecial   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.d:()Z
        //   203: istore          10
        //   205: aload_0        
        //   206: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzMarkedPos:I
        //   209: istore          4
        //   211: aload_0        
        //   212: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzEndRead:I
        //   215: istore          5
        //   217: aload_0        
        //   218: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzBuffer:Ljava/lang/CharSequence;
        //   221: astore          6
        //   223: iload           10
        //   225: ifeq            240
        //   228: aload_0        
        //   229: iconst_0       
        //   230: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzAtBOL:Z
        //   233: goto            267
        //   236: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   239: athrow         
        //   240: aload_0        
        //   241: aload           6
        //   243: iload           4
        //   245: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   250: bipush          10
        //   252: if_icmpeq       263
        //   255: iconst_1       
        //   256: goto            264
        //   259: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   262: athrow         
        //   263: iconst_0       
        //   264: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzAtBOL:Z
        //   267: goto            275
        //   270: aload_0        
        //   271: iconst_0       
        //   272: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzAtBOL:Z
        //   275: iconst_m1      
        //   276: istore_2       
        //   277: aload_0        
        //   278: aload_0        
        //   279: iload           4
        //   281: dup_x1         
        //   282: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzStartRead:I
        //   285: dup_x1         
        //   286: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzCurrentPos:I
        //   289: istore_3       
        //   290: aload_0        
        //   291: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzAtBOL:Z
        //   294: ifeq            318
        //   297: aload_0        
        //   298: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.ZZ_LEXSTATE:[I
        //   301: aload_0        
        //   302: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzLexicalState:I
        //   305: iconst_1       
        //   306: iadd           
        //   307: iaload         
        //   308: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzState:I
        //   311: goto            330
        //   314: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   317: athrow         
        //   318: aload_0        
        //   319: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.ZZ_LEXSTATE:[I
        //   322: aload_0        
        //   323: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzLexicalState:I
        //   326: iaload         
        //   327: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzState:I
        //   330: aload           9
        //   332: aload_0        
        //   333: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzState:I
        //   336: iaload         
        //   337: istore          10
        //   339: iload           10
        //   341: iconst_1       
        //   342: iand           
        //   343: iconst_1       
        //   344: if_icmpne       352
        //   347: aload_0        
        //   348: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzState:I
        //   351: istore_2       
        //   352: iload_3        
        //   353: iload           5
        //   355: if_icmpge       375
        //   358: aload           6
        //   360: iload_3        
        //   361: invokestatic    java/lang/Character.codePointAt:(Ljava/lang/CharSequence;I)I
        //   364: istore_1       
        //   365: iload_3        
        //   366: iload_1        
        //   367: invokestatic    java/lang/Character.charCount:(I)I
        //   370: iadd           
        //   371: istore_3       
        //   372: goto            451
        //   375: aload_0        
        //   376: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzAtEOF:Z
        //   379: ifeq            387
        //   382: iconst_m1      
        //   383: istore_1       
        //   384: goto            532
        //   387: aload_0        
        //   388: iload_3        
        //   389: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzCurrentPos:I
        //   392: aload_0        
        //   393: iload           4
        //   395: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzMarkedPos:I
        //   398: aload_0        
        //   399: invokespecial   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.d:()Z
        //   402: istore          11
        //   404: aload_0        
        //   405: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzCurrentPos:I
        //   408: istore_3       
        //   409: aload_0        
        //   410: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzMarkedPos:I
        //   413: istore          4
        //   415: aload_0        
        //   416: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzBuffer:Ljava/lang/CharSequence;
        //   419: astore          6
        //   421: aload_0        
        //   422: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzEndRead:I
        //   425: istore          5
        //   427: iload           11
        //   429: ifeq            437
        //   432: iconst_m1      
        //   433: istore_1       
        //   434: goto            532
        //   437: aload           6
        //   439: iload_3        
        //   440: invokestatic    java/lang/Character.codePointAt:(Ljava/lang/CharSequence;I)I
        //   443: istore_1       
        //   444: iload_3        
        //   445: iload_1        
        //   446: invokestatic    java/lang/Character.charCount:(I)I
        //   449: iadd           
        //   450: istore_3       
        //   451: aload           7
        //   453: aload           8
        //   455: aload_0        
        //   456: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzState:I
        //   459: iaload         
        //   460: iload_1        
        //   461: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.ZZ_CMAP:(I)I
        //   464: iadd           
        //   465: iaload         
        //   466: istore          11
        //   468: iload           11
        //   470: iconst_m1      
        //   471: if_icmpne       481
        //   474: goto            532
        //   477: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   480: athrow         
        //   481: aload_0        
        //   482: iload           11
        //   484: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzState:I
        //   487: aload           9
        //   489: aload_0        
        //   490: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzState:I
        //   493: iaload         
        //   494: istore          10
        //   496: iload           10
        //   498: iconst_1       
        //   499: iand           
        //   500: iconst_1       
        //   501: if_icmpne       529
        //   504: aload_0        
        //   505: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzState:I
        //   508: istore_2       
        //   509: iload_3        
        //   510: istore          4
        //   512: iload           10
        //   514: bipush          8
        //   516: iand           
        //   517: bipush          8
        //   519: if_icmpne       529
        //   522: goto            532
        //   525: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   528: athrow         
        //   529: goto            352
        //   532: aload_0        
        //   533: iload           4
        //   535: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzMarkedPos:I
        //   538: iload_1        
        //   539: iconst_m1      
        //   540: if_icmpne       677
        //   543: aload_0        
        //   544: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzStartRead:I
        //   547: aload_0        
        //   548: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzCurrentPos:I
        //   551: if_icmpne       677
        //   554: goto            561
        //   557: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   560: athrow         
        //   561: aload_0        
        //   562: iconst_1       
        //   563: putfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzAtEOF:Z
        //   566: aload_0        
        //   567: getfield        com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.zzLexicalState:I
        //   570: lookupswitch {
        //                0: 632
        //                8: 649
        //               12: 662
        //               32: 646
        //               33: 659
        //               34: 672
        //          default: 675
        //        }
        //   628: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   631: athrow         
        //   632: aload_0        
        //   633: bipush          14
        //   635: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   638: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.EOL:Lcom/intellij/psi/tree/IElementType;
        //   641: areturn        
        //   642: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   645: athrow         
        //   646: goto            1062
        //   649: aload_0        
        //   650: bipush          14
        //   652: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   655: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.EOL:Lcom/intellij/psi/tree/IElementType;
        //   658: areturn        
        //   659: goto            1062
        //   662: aload_0        
        //   663: bipush          14
        //   665: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   668: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.EOL:Lcom/intellij/psi/tree/IElementType;
        //   671: areturn        
        //   672: goto            1062
        //   675: aconst_null    
        //   676: areturn        
        //   677: iload_2        
        //   678: ifge            689
        //   681: iload_2        
        //   682: goto            694
        //   685: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   688: athrow         
        //   689: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.ZZ_ACTION:[I
        //   692: iload_2        
        //   693: iaload         
        //   694: tableswitch {
        //                2: 852
        //                3: 875
        //                4: 887
        //                5: 899
        //                6: 911
        //                7: 927
        //                8: 940
        //                9: 953
        //               10: 966
        //               11: 979
        //               12: 986
        //               13: 993
        //               14: 1005
        //               15: 1012
        //               16: 1024
        //               17: 1031
        //               18: 1038
        //               19: 1050
        //               20: 872
        //               21: 884
        //               22: 896
        //               23: 908
        //               24: 924
        //               25: 937
        //               26: 950
        //               27: 963
        //               28: 976
        //               29: 983
        //               30: 990
        //               31: 1002
        //               32: 1009
        //               33: 1021
        //               34: 1028
        //               35: 1035
        //               36: 1047
        //               37: 1054
        //          default: 1057
        //        }
        //   852: aload_0        
        //   853: aload_0        
        //   854: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yylength:()I
        //   857: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yypushback:(I)V
        //   860: aload_0        
        //   861: iconst_2       
        //   862: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   865: goto            872
        //   868: invokestatic    com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   871: athrow         
        //   872: goto            1062
        //   875: aload_0        
        //   876: iconst_0       
        //   877: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   880: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.EOL:Lcom/intellij/psi/tree/IElementType;
        //   883: areturn        
        //   884: goto            1062
        //   887: aload_0        
        //   888: iconst_0       
        //   889: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   892: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.WHITESPACES:Lcom/intellij/psi/tree/IElementType;
        //   895: areturn        
        //   896: goto            1062
        //   899: aload_0        
        //   900: iconst_0       
        //   901: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   904: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.COMMENT:Lcom/intellij/psi/tree/IElementType;
        //   907: areturn        
        //   908: goto            1062
        //   911: aload_0        
        //   912: iconst_4       
        //   913: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   916: aload_0        
        //   917: aload_0        
        //   918: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yylength:()I
        //   921: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yypushback:(I)V
        //   924: goto            1062
        //   927: aload_0        
        //   928: bipush          6
        //   930: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   933: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.TYPE_SEPARATOR:Lcom/intellij/psi/tree/IElementType;
        //   936: areturn        
        //   937: goto            1062
        //   940: aload_0        
        //   941: bipush          8
        //   943: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   946: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.VALUE_SEPARATOR:Lcom/intellij/psi/tree/IElementType;
        //   949: areturn        
        //   950: goto            1062
        //   953: aload_0        
        //   954: bipush          10
        //   956: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   959: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.KEY_QUOTE:Lcom/intellij/psi/tree/IElementType;
        //   962: areturn        
        //   963: goto            1062
        //   966: aload_0        
        //   967: bipush          12
        //   969: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   972: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.VALUE_QUOTE:Lcom/intellij/psi/tree/IElementType;
        //   975: areturn        
        //   976: goto            1062
        //   979: getstatic       com/intellij/psi/TokenType.BAD_CHARACTER:Lcom/intellij/psi/tree/IElementType;
        //   982: areturn        
        //   983: goto            1062
        //   986: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.WHITESPACES:Lcom/intellij/psi/tree/IElementType;
        //   989: areturn        
        //   990: goto            1062
        //   993: aload_0        
        //   994: iconst_2       
        //   995: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //   998: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.KEY:Lcom/intellij/psi/tree/IElementType;
        //  1001: areturn        
        //  1002: goto            1062
        //  1005: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.EOL:Lcom/intellij/psi/tree/IElementType;
        //  1008: areturn        
        //  1009: goto            1062
        //  1012: aload_0        
        //  1013: iconst_2       
        //  1014: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //  1017: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.TYPE:Lcom/intellij/psi/tree/IElementType;
        //  1020: areturn        
        //  1021: goto            1062
        //  1024: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.VALUE:Lcom/intellij/psi/tree/IElementType;
        //  1027: areturn        
        //  1028: goto            1062
        //  1031: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.KEY:Lcom/intellij/psi/tree/IElementType;
        //  1034: areturn        
        //  1035: goto            1062
        //  1038: aload_0        
        //  1039: iconst_2       
        //  1040: invokevirtual   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.yybegin:(I)V
        //  1043: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.KEY_QUOTE:Lcom/intellij/psi/tree/IElementType;
        //  1046: areturn        
        //  1047: goto            1062
        //  1050: getstatic       com/jetbrains/cidr/cpp/cmake/cmakecache/psi/CMakeCacheTokenTypes.VALUE_QUOTE:Lcom/intellij/psi/tree/IElementType;
        //  1053: areturn        
        //  1054: goto            1062
        //  1057: aload_0        
        //  1058: iconst_1       
        //  1059: invokespecial   com/jetbrains/cidr/cpp/cmake/cmakecache/psi/_CMakeCacheLexer.a:(I)V
        //  1062: goto            27
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  33     120    120    124    Ljava/io/IOException;
        //  42     132    132    136    Ljava/io/IOException;
        //  136    158    161    165    Ljava/io/IOException;
        //  143    169    169    173    Ljava/io/IOException;
        //  180    195    195    199    Ljava/io/IOException;
        //  223    236    236    240    Ljava/io/IOException;
        //  240    259    259    263    Ljava/io/IOException;
        //  290    314    314    318    Ljava/io/IOException;
        //  468    477    477    481    Ljava/io/IOException;
        //  512    525    525    529    Ljava/io/IOException;
        //  532    554    557    561    Ljava/io/IOException;
        //  543    628    628    632    Ljava/io/IOException;
        //  561    642    642    646    Ljava/io/IOException;
        //  677    685    685    689    Ljava/io/IOException;
        //  694    865    868    872    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0561:
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
        ZZ_LEXSTATE = new int[] { 0, 0, 1, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8 };
        ZZ_CMAP_Z = a("\u0001\u0000\u0001@\u0001\u0080\u010d@");
        ZZ_CMAP_Y = a("\u0001\u0000\u0001@\u0001\u0080}@\u0001\u00c0?@");
        ZZ_CMAP_A = a("\t\u0000\u0001\u0007\u0001\u0003\u0002\t\u0001\u0006\u0012\u0000\u0001\u0007\u0001\u0000\u0001\u0004\u0001\n\u0003\u0000\u0001\u0005\u0007\u0000\u0001\b\n\u0000\u0001\u0001\u0002\u0000\u0001\u0002G\u0000\u0001\tb\u0000\u0002\t\u0016\u0000");
        ZZ_ACTION = b();
        ZZ_ROWMAP = a();
        ZZ_TRANS = e();
        ZZ_ERROR_MSG = new String[] { "Unknown internal scanner error", "Error: could not match input", "Error: pushback value was too large" };
        ZZ_ATTRIBUTE = c();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
