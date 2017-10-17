// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import java.io.IOException;
import java.io.Reader;
import com.intellij.lexer.FlexLexer;

class _OCStringsLexer implements FlexLexer
{
    public static final int YYEOF = -1;
    private static final int ZZ_BUFFERSIZE = 16384;
    public static final int YYINITIAL = 0;
    private static final int[] ZZ_LEXSTATE;
    static final char[] ZZ_CMAP_Z;
    static final char[] ZZ_CMAP_Y;
    static final char[] ZZ_CMAP_A;
    private static final int[] ZZ_ACTION;
    private static final String ZZ_ACTION_PACKED_0 = "\u0001\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0002\u0001\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0000\u0001\u0007\u0001\b\u0001\t\u0002\u0004\u0001\b\u0001\u0000\u0001\t";
    private static final int[] ZZ_ROWMAP;
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000\f\u0000\u0018\u0000$\u00000\u0000<\u0000H\u0000\f\u0000\f\u00000\u0000\f\u0000T\u0000`\u0000l\u0000\f\u0000x\u0000\u0084\u0000\f";
    private static final int[] ZZ_TRANS;
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0002\u0001\u0005\u0002\u0003\u0001\u0006\u0001\u0002\u0001\u0007\u0001\b\u0001\t\r\u0000\u0001\u0003\u0003\u0000\u0002\u0003\u0007\u0000\u0002\u0004\r\u0000\u0001\n\u0001\u000b\f\u0000\u0001\f\u0001\r\u0003\u0000\u0004\u0007\u0001\u000e\u0004\u0007\u0001\u000f\u0002\u0007\u0004\f\u0001\u0010\u0001\f\u0001\u0000\u0005\f\b\r\u0001\u0011\u0003\r\f\u0007\u0004\f\u0002\u0010\u0006\f\u0007\r\u0001\u0012\u0001\u0011\u0003\r";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG;
    private static final int[] ZZ_ATTRIBUTE;
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0001\u0000\u0001\t\u0005\u0001\u0002\t\u0001\u0000\u0001\t\u0003\u0001\u0001\t\u0001\u0001\u0001\u0000\u0001\t";
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
    private boolean myIsHighlightingLexer;
    
    public static int ZZ_CMAP(final int n) {
        return _OCStringsLexer.ZZ_CMAP_A[_OCStringsLexer.ZZ_CMAP_Y[_OCStringsLexer.ZZ_CMAP_Z[n >> 9] << 6 | (n >> 3 & 0x3F)] << 3 | (n & 0x7)];
    }
    
    private static int[] e() {
        final int[] array = new int[18];
        b("\u0001\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0002\u0001\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0000\u0001\u0007\u0001\b\u0001\t\u0002\u0004\u0001\b\u0001\u0000\u0001\t", 0, array);
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
    
    private static int[] b() {
        final int[] array = new int[18];
        d("\u0000\u0000\u0000\f\u0000\u0018\u0000$\u00000\u0000<\u0000H\u0000\f\u0000\f\u00000\u0000\f\u0000T\u0000`\u0000l\u0000\f\u0000x\u0000\u0084\u0000\f", 0, array);
        return array;
    }
    
    private static int d(final String s, final int n, final int[] array) {
        int i;
        int n2;
        for (i = 0, n2 = n; i < s.length(); array[n2++] = (s.charAt(i++) << 16 | s.charAt(i++))) {}
        return n2;
    }
    
    private static int[] a() {
        final int[] array = new int[144];
        a("\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0002\u0001\u0005\u0002\u0003\u0001\u0006\u0001\u0002\u0001\u0007\u0001\b\u0001\t\r\u0000\u0001\u0003\u0003\u0000\u0002\u0003\u0007\u0000\u0002\u0004\r\u0000\u0001\n\u0001\u000b\f\u0000\u0001\f\u0001\r\u0003\u0000\u0004\u0007\u0001\u000e\u0004\u0007\u0001\u000f\u0002\u0007\u0004\f\u0001\u0010\u0001\f\u0001\u0000\u0005\f\b\r\u0001\u0011\u0003\r\f\u0007\u0004\f\u0002\u0010\u0006\f\u0007\r\u0001\u0012\u0001\u0011\u0003\r", 0, array);
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
        final int[] array = new int[18];
        c("\u0001\u0000\u0001\t\u0005\u0001\u0002\t\u0001\u0000\u0001\t\u0003\u0001\u0001\t\u0001\u0001\u0001\u0000\u0001\t", 0, array);
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
    
    public void forHighlighting() {
        this.myIsHighlightingLexer = true;
    }
    
    _OCStringsLexer(final Reader zzReader) {
        this.zzLexicalState = 0;
        this.zzBuffer = "";
        this.zzAtBOL = true;
        this.myIsHighlightingLexer = false;
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
            s = _OCStringsLexer.ZZ_ERROR_MSG[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            s = _OCStringsLexer.ZZ_ERROR_MSG[0];
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
        final int[] zz_TRANS = _OCStringsLexer.ZZ_TRANS;
        final int[] zz_ROWMAP = _OCStringsLexer.ZZ_ROWMAP;
        final int[] zz_ATTRIBUTE = _OCStringsLexer.ZZ_ATTRIBUTE;
        while (true) {
            int n2 = this.zzMarkedPos;
            int n3 = -1;
            final int n4 = n2;
            this.zzStartRead = n4;
            this.zzCurrentPos = n4;
            int zzCurrentPos = n4;
            this.zzState = _OCStringsLexer.ZZ_LEXSTATE[this.zzLexicalState];
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
                    final boolean d = this.d();
                    final int zzCurrentPos2 = this.zzCurrentPos;
                    n2 = this.zzMarkedPos;
                    charSequence = this.zzBuffer;
                    n = this.zzEndRead;
                    if (d) {
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
            Label_0476: {
                Label_0319: {
                    Label_0302: {
                        Label_0291: {
                            try {
                                this.zzMarkedPos = n2;
                                if (n5 != -1) {
                                    break Label_0302;
                                }
                                final _OCStringsLexer ocStringsLexer = this;
                                final int n7 = ocStringsLexer.zzStartRead;
                                final _OCStringsLexer ocStringsLexer2 = this;
                                final int n8 = ocStringsLexer2.zzCurrentPos;
                                if (n7 == n8) {
                                    break Label_0291;
                                }
                                break Label_0302;
                            }
                            catch (IOException ex3) {
                                throw b(ex3);
                            }
                            try {
                                final _OCStringsLexer ocStringsLexer = this;
                                final int n7 = ocStringsLexer.zzStartRead;
                                final _OCStringsLexer ocStringsLexer2 = this;
                                final int n8 = ocStringsLexer2.zzCurrentPos;
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
                    final int n9 = _OCStringsLexer.ZZ_ACTION[n3];
                    try {
                        switch (n9) {
                            case 1: {
                                this.yybegin(0);
                                return TokenType.BAD_CHARACTER;
                            }
                            case 10: {
                                continue;
                            }
                            case 2: {
                                return TokenType.WHITE_SPACE;
                            }
                            case 11: {
                                continue;
                            }
                            case 3: {
                                return OCTokenTypes.IDENTIFIER;
                            }
                            case 12: {
                                continue;
                            }
                            case 4: {
                                return OCTokenTypes.STRING_LITERAL;
                            }
                            case 13: {
                                continue;
                            }
                            case 5: {
                                return OCTokenTypes.SEMICOLON;
                            }
                            case 14: {
                                continue;
                            }
                            case 6: {
                                return OCTokenTypes.EQ;
                            }
                            case 15: {
                                continue;
                            }
                            case 7: {
                                return OCTokenTypes.EOL_ESCAPE;
                            }
                            case 16: {
                                continue;
                            }
                            case 8: {
                                return OCTokenTypes.EOL_COMMENT;
                            }
                            case 17: {
                                continue;
                            }
                            case 9: {
                                return OCTokenTypes.BLOCK_COMMENT;
                            }
                            case 18: {
                                continue;
                            }
                            default: {
                                break Label_0476;
                            }
                        }
                    }
                    catch (IOException ex6) {
                        throw b(ex6);
                    }
                }
                continue;
            }
            this.a(1);
        }
    }
    
    static {
        ZZ_LEXSTATE = new int[] { 0, 0 };
        ZZ_CMAP_Z = a("\u0001\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0001\u000e\u0001\u000f\u0001\u0010\u0005\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0011\f\u0015\u0001\u0016(\u0015\u0001\u0017\u0002\u0015\u0001\u0018\u0001\u0019\u0001\u001a\u0001\u001b\u0015\u0015\u0001\u001c\u0010\u0011\u0001\u001d\u0001\u001e\u0001\u001f\u0001 \u0001!\u0001\"\u0001#\u0001\u0011\u0001$\u0001%\u0001&\u0001\u0011\u0001'\u0002\u0011\u0001(\u0004\u0011\u0001\u0015\u0001)\u0001*\u0005\u0011\u0002\u0015\u0001+\u0019\u0011\u0001\u0015\u0001,\u0001\u0011\u0001- \u0011\u0001.\u000f\u0011\u0001/\u00010\u00011\u00012\u000b\u0011\u00013\b\u0011S\u0015\u00014\u0007\u0015\u00015\u00016\u001f\u0011\u0001\u0015\u00016\u0582\u0011\u00017\u017f\u0011");
        ZZ_CMAP_Y = a("\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0000\u0001\u0005\u0001\u0006\u0002\u0007\u0001\b\u0001\u0006\u0002\u0007\u0001\t\u0004\u0000\u0001\n\u0001\u000b\u0001\f\u0001\r\u0002\u0007\u0001\u000e\u0003\u0007\u0001\u000e9\u0007\u0001\u000f\u0001\u0007\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0002\u0011\u000e\u0000\u0001\u0014\u0001\n\u0001\u0015\u0001\u0016\u0002\u0007\u0001\u0017\t\u0007\u0001\u0018\u0011\u0007\u0001\u0019\u0001\u001a\u0013\u0007\u0001\u0011\u0001\u0006\u0003\u0007\u0001\u000e\u0001\u001b\u0001\u0006\u0004\u0007\u0001\u001c\u0001\u001d\u0004\u0000\u0001\u001e\u0001\u001f\u0001\u0011\u0003\u0007\u0002 \u0001\u0011\u0001!\u0001\"\u0001\u0000\u0001#\u0005\u0007\u0001$\u0003\u0000\u0001%\u0001&\u000b\u0007\u0001'\u0001\u001e\u0001(\u0001)\u0001\u0000\u0001*\u0001\u0011\u0001+\u0001,\u0003\u0007\u0003\u0000\u0001-\n\u0007\u0001.\u0001\u0000\u0001/\u0001\u0011\u0001\u0000\u00010\u0003\u0007\u0001$\u00011\u0001\r\u0002\u0007\u0001.\u00012\u00013\u00014\u0002\u0011\u0003\u0007\u00015\b\u0011\u00016\u0001\u0012\u0006\u0011\u00017\u0002\u0000\u00018\u00019\u0006\u0007\u0001:\u0002\u0000\u0001;\u0001\u0007\u0001<\u0001\u0000\u0002\u0006\u0001=\u0001>\u0001?\u0002\u0007\u00016\u0001@\u0001A\u0001B\u0001C\u0001+\u0001D\u0001<\u0001\u0000\u0001E\u0001\"\u0001=\u0001F\u0001?\u0002\u0007\u00016\u0001G\u0001H\u0001I\u0001J\u0001K\u0001L\u0001M\u0001\u0000\u0001N\u0001\u0011\u0001=\u0001\u0018\u0001\u0017\u0002\u0007\u00016\u0001O\u0001A\u0001\u001e\u0001P\u0001Q\u0001\u0011\u0001<\u0001\u0000\u0001\u001b\u0001\u0011\u0001=\u0001>\u0001?\u0002\u0007\u00016\u0001O\u0001A\u0001B\u0001J\u0001M\u0001D\u0001<\u0001\u0000\u0001\u001b\u0001\u0011\u0001R\u0001S\u0001T\u0001U\u0001V\u0001S\u0001\u0007\u0001W\u0001X\u0001Y\u0001Z\u0001\u0011\u0001M\u0001\u0000\u0001\u0011\u0001\u001b\u0001=\u0001\u0014\u00016\u0002\u0007\u00016\u0001[\u0001\\\u0001]\u0001Y\u0001^\u0001\u0010\u0001<\u0001\u0000\u0002\u0011\u0001_\u0001\u0014\u00016\u0002\u0007\u00016\u0001[\u0001A\u0001]\u0001Y\u0001^\u0001\u0015\u0001<\u0001\u0000\u0001`\u0001\u0011\u0001_\u0001\u0014\u00016\u0004\u0007\u0001a\u0001]\u0001b\u0001+\u0001\u0011\u0001<\u0001\u0000\u0001\u0011\u0001\u001a\u0001_\u0001\u0007\u0001\u000e\u0001\u001a\u0002\u0007\u0001\u0017\u0001c\u0001\u000e\u0001d\u0001e\u0001\u0000\u0002\u0011\u0001f\u0001\u0011\u0001\u0006\u0005\u0007\u0001g\u0001h\u0001i\u00018\u0001\u0000\u0001j\u0004\u0011\u0001k\u0001l\u0001m\u0001\u0006\u0001n\u0001o\u0001g\u0001p\u0001q\u0001r\u0001\u0000\u0001s\u0004\u0011\u0001Q\u0002\u0011\u0001j\u0001\u0000\u0001j\u0001t\u0001u\u0001\u0007\u0001\u0006\u0003\u0007\u0001\u0012\u0001\u001d\u0001\u0000\u0001]\u0001v\u0001\u0000\u0001\u001d\u0003\u0000\u0001!\u0001w\u0007\u0011\u0005\u0007\u0001$\u0001\u0000\u0001x\u0001\u0000\u0001j\u0001.\u0001y\u0001z\u0001{\u0001|\u0001\u0007\u0001}\u0001~\u0001\u0000\u0001r\u0004\u0007\u0001\u0018\u0001\f\u0005\u0007\u0001\u007f)\u0007\u0001T\u0001\u000e\u0001T\u0005\u0007\u0001T\u0004\u0007\u0001T\u0001\u000e\u0001T\u0001\u0007\u0001\u000e\u0007\u0007\u0001T\b\u0007\u0001\u0080\u0004\u0011\u0002\u0007\u0002\u0011\n\u0007\u0001\u0012\u0001\u0011\u0001\u0006L\u0007\u0001>\u0002\u0007\u0001\u0006\u0002\u0007\u0001 \t\u0007\u0001S\u0001Q\u0001\u0011\u0001\u0007\u0001\u0014\u0001\u0081\u0001\u0011\u0002\u0007\u0001\u0081\u0001\u0011\u0002\u0007\u0001\u0082\u0001\u0011\u0001\u0007\u0001\u0014\u0001\u0083\u0001\u0011\u0006\u0007\u0001\u0084\u0003\u0000\u0001\u0085\u0001\u0086\u0001\u0000\u0001j\u0003\u0011\u0001\u0087\u0001\u0000\u0001j\u000b\u0007\u0001\u0011\u0005\u0007\u0001\u0088\b\u0007\u0001\u0089\u0001\u0011\u0003\u0007\u0001\u0012\u0001\u0000\u0001\u0002\u0001\u0000\u0001\u0002\u0001M\u0001\u0000\u0003\u0007\u0001\u0089\u0001\u0012\u0001\u0011\u0005\u0007\u0001E\u0002\u0000\u0001&\u0001j\u0001\u0000\u0001j\u0004\u0011\u0002\u0007\u0001i\u0001\u0002\u0006\u0007\u0001v\u00018\u0003\u0000\u0001B\u0001\u0000\u0001j\u0001\u0000\u0001j\u0001\u001c\u000b\u0011\u0001\u008a\u0005\u0007\u0001\u0084\u0001\u0000\u0001\u008a\u0001E\u0001\u0000\u0001j\u0001\u0011\u0001\u008b\u0001\u0002\u0001\u0011\u0001\u008c\u0003\u0007\u0001;\u0001{\u0001\u0000\u00010\u0004\u0007\u0001.\u0001\u0000\u0001\u0002\u0001\u0011\u0004\u0007\u0001\u0084\u0002\u0000\u0001\u0011\u0001\u0000\u0001\u008d\u0001\u0000\u00010\u0003\u0007\u0001\u0089\n\u0011\u0001\u008e\u0002\u0000\u0001\u008f\u0001\u0090\u0001\u0011\u0018\u0007\u0004\u0000\u00018\u0002\u0011\u00017\"\u0007\u0002\u0089\u0004\u0007\u0002\u0089\u0001\u0007\u0001\u0091\u0003\u0007\u0001\u0089\u0006\u0007\u0001\u0014\u0001q\u0001\u0092\u0001\u0012\u0001\u0093\u0001E\u0001\u0007\u0001\u0012\u0001\u0092\u0001\u0012\u0001\u0011\u0001\u008b\u0003\u0011\u0001\u0094\u0001\u0011\u0001\u001c\u0001Q\u0001\u0011\u0001\u0095\u0001\u0011\u0001!\u0001\u0096\u0001\u001b\u0001\u001c\u0002\u0011\u0001\u0007\u0001\u0012\u0003\u0007\u0001 \u0002\u0011\u0001\u0000\u0001!\u0001\u0097\u0001\u0000\u0001\u0098\u0001\u0011\u0001\u0099\u0001\u001a\u0001c\u0001\u009a\u0001\u0013\u0001\u009b\u0001\u0007\u0001\u009c\u0001\u009d\u0001\u009e\u0002\u0011\u0005\u0007\u0001QN\u0011\u0005\u0007\u0001\u000e\u0005\u0007\u0001\u000e\u0010\u0007\u0001\u0012\u0001\u009f\u0001 \u0001\u0011\u0004\u0007\u0001\u0018\u0001\f\u0007\u0007\u0001\u001c\u0001\u0011\u0001+\u0002\u0007\u0001\u000e\u0001\u0011\b\u000e\u0004\u0000\u0005\u0011\u0001\u001c:\u0011\u0001\u009d\u0003\u0011\u0001\u0006\u0001}\u0001\u009a\u0001\u0012\u0001\u0006\t\u0007\u0001\u000e\u0001¡\u0001\u0006\n\u0007\u0001\u007f\u0001\u009d\u0004\u0007\u0001\u0089\u0001\u0006\n\u0007\u0001\u000e\u0002\u0011\u0003\u0007\u0001 \u0006\u0011x\u0007\u0001\u0089\t\u00119\u0007\u0001\u0012\u0006\u0011\u0011\u0007\u0001\u0012\b\u0011\u0005\u0007\u0001\u0089!\u0007\u0001\u0012\u0002\u0007\u0001\u0000\u0001 \u0002\u0011\u0005\u0007\u0001i\u00017\u0001¢\u0003\u0007\u0001+\n\u0007\u0001j\u0003\u0011\u0001\u001c\u0001\u0007\u0001\u001a\f\u0007\u0001£\u0001E\u0001\u0011\u0001\u0007\u0001 \t\u0011\u0001\u0007\u0001¤\u0001¥\u0002\u0007\u0001$\u0002\u0011\u0001Q\u0006\u0007\u0001E\u0001\u0011\u00010\u0005\u0007\u0001\u0084\u0001\u0000\u0001!\u0001\u0011\u0001\u0000\u0001j\u0002\u0000\u00010\u0001\"\u0001\u0000\u00010\u0002\u0007\u0001.\u0001r\u0002\u0007\u0001i\u0001\u0000\u0001\u0002\u0001\u0011\u0003\u0007\u0001\u0012\u00019\u0005\u0007\u0001$\u0001\u0000\u0001\u0098\u0001\u001c\u0001\u0000\u0001j\u0004\u0011\u0005\u0007\u0001;\u00018\u0001\u0011\u0001¥\u0001¦\u0001\u0000\u0001j\u0002\u0007\u0001\u000e\u0001§\u0006\u0007\u0001z\u0001¨\u0001\u0088\u0002\u0011\u0001©\u0001\u0007\u0001$\u0001ª\u0001\u0011\u0003«\u0001\u0011\u0002\u000e\u0012\u0011\u0004\u0007\u0001$\u0001¬\u0001\u0000\u0001j4\u0007\u0001E\u0001\u0011\u0002\u0007\u0001\u000e\u0001\u00ad\u0005\u0007\u0001E \u0011-\u0007\u0001\u0089\r\u0007\u0001\u0010\u0004\u0011\u0001\u000e\u0001\u0011\u0001\u00ad\u0001®\u0001\u0007\u00016\u0001\u000e\u0001q\u0001¯\r\u0007\u0001\u0010\u0003\u0011\u0001\u00ad,\u0007\u0001\u0089\u0002\u0011\b\u0007\u0001\u001a\u0006\u0007\u0005\u0011\u0001\u0007\u0001\u0012\u0002\u0000\u0002\u0011\u00018\u0001\u0011\u0001V\u0002\u0011\u0001\u009d\u0003\u0011\u0001\u001b\u0001\u0014\u0010\u0007\u0001°\u0001\u0095\u0001\u0011\u0001\u0000\u0001j\u0001\u0006\u0002\u0007\u0001F\u0001\u0006\u0002\u0007\u0001 \u0001±\n\u0007\u0001\u000e\u0003\u001a\u0001²\u0001³\u0002\u0011\u0001´\u0001\u0007\u0001[\u0002\u0007\u0001\u000e\u0002\u0007\u0001µ\u0001\u0007\u0001\u0089\u0001\u0007\u0001\u0089\u0004\u0011\u000f\u0007\u0001 \b\u0011\u0006\u0007\u0001\u0012\u0010\u0011\u0001¶\u0010\u0011\u0003\u0007\u0001\u0012\u0006\u0007\u0001Q\u0005\u0011\u0003\u0007\u0001\u000e\u0002\u0011\u0003\u0007\u0001 \u0006\u0011\u0003\u0007\u0001\u0089\u0004\u0007\u0001E\u0001\u0007\u0001\u009a\u0005\u0011\u0013\u0007\u0001\u0089\u0001\u0000\u0001j*\u0011\u0001\u0089\u00016\u0004\u0007\u0001\u0018\u0001·\u0002\u0007\u0001\u0089\u0015\u0011\u0002\u0007\u0001\u0089\u0001\u0011\u0003\u0007\u0001\u0010\b\u0011\u0007\u0007\u0001±\b\u0011\u0001¸\u00017\u0001[\u0001\u0006\u0002\u0007\u0001E\u0001I\u0004\u0011\u0003\u0007\u0001\u0012\u0010\u0011\u0006\u0007\u0001\u0089\u0001\u0011\u0002\u0007\u0001\u0089\u0001\u0011\u0002\u0007\u0001 \u0011\u0011\t\u0007\u0001Q6\u0011\u0001\u008c\u0006\u0007\u0001\u0000\u00018\u0003\u0011\u0001M\u0001\u0000\u0002\u0011\u0001\u008c\u0005\u0007\u0001\u0000\u0001¹\u0002\u0011\u0003\u0007\u0001Q\u0001\u0000\u0001j\u0001\u008c\u0003\u0007\u0001i\u0001\u0000\u0001]\u0001\u0000\b\u0011\u0001\u008c\u0005\u0007\u0001$\u0001\u0000\u0001º\u0001\u0011\u0001\u0000\u0001j\u0014\u0011\u0005\u0007\u0001$\u0001\u0000\u0001\u0011\u0001\u0000\u0001j&\u0011-\u0007\u0001\u000e\u0012\u0011\f\u0007\u0001 3\u0011\u0005\u0007\u0001\u000e:\u0011\u0007\u0007\u0001QX\u0011\b\u0007\u0001\u0012\u0001\u0011\u0001;\u0004\u0000\u00018\u0001\u0011\u0001+\u0001\u008c\u0001\u0007\f\u0011\u0001\u0010k\u0011\u0001»\u0001¼\u0002\u0000\u0001½\u0001\u0002\u0003\u0011\u0001¾\u0012\u0011\u0001¿7\u0011\n\u0007\u0001\u0014\b\u0007\u0001\u0014\u0001\u00c0\u0001\u00c1\u0001\u0007\u0001\u00c2\u0001[\u0007\u0007\u0001\u0018\u0001\u00c3\u0002\u0014\u0003\u0007\u0001\u00c4\u0001q\u0001\u001a\u00016)\u0007\u0001\u0089\u0003\u0007\u00016\u0002\u0007\u0001\u007f\u0003\u0007\u0001\u007f\u0002\u0007\u0001\u0014\u0003\u0007\u0001\u0014\u0002\u0007\u0001\u000e\u0003\u0007\u0001\u000e\u0003\u0007\u00016\u0003\u0007\u00016\u0002\u0007\u0001\u007f\u0001\u00c5\u0006\u0000\u0001[\u0003\u0007\u0001k\u0001\u0006\u0001\u007f\u0001\u00c6\u0001\u0099\u0001\u00c7\u0001k\u0001\u0091\u0001k\u0002\u007f\u0001L\u0001\u0007\u0001\u0017\u0001\u0007\u0001E\u0001\u00c8\u0001\u0017\u0001\u0007\u0001E(\u0011\u001a\u0007\u0001\u000e\u0005\u0011F\u0007\u0001\u0012\u0001\u0011\u001b\u0007\u0001\u0089<\u0011\u0001K\u0003\u0011\f\u0000\u0010\u0011\u001e\u0000\u0002\u0011");
        ZZ_CMAP_A = a("\t\u0003\u0001\u0005\u0001\u0006\u0001\u0000\u0001\u0001\u0001\u0000\u0006\u0003\u0004\u0000\u0001\u0005\u0001\u0000\u0001\t\u0001\u0000\u0001\u0002\u0005\u0000\u0001\b\u0004\u0000\u0001\u0007\u0002\u0003\u0001\u0000\u0001\n\u0001\u0000\u0001\u000b\u0003\u0000\u0012\u0002\u0001\u0000\u0001\u0004\u0002\u0000\u0004\u0002\u0004\u0000\u0001\u0003\u0002\u0000\u0004\u0002\u0004\u0000\u0001\u0002\u0002\u0000\u0001\u0003\u0007\u0000\u0001\u0002\u0004\u0000\u0001\u0002\u0005\u0000\u0007\u0002\u0001\u0000\u0002\u0002\u0004\u0000\u0004\u0002\u000e\u0000\u0005\u0002\u0007\u0000\u0001\u0002\u0001\u0000\u0001\u0002\u0001\u0000\u0005\u0002\u0001\u0000\u0002\u0002\u0006\u0000\u0001\u0002\u0001\u0000\u0003\u0002\u0001\u0000\u0001\u0002\u0001\u0000\u0004\u0002\u0001\u0000\u000b\u0002\u0001\u0000\u0003\u0002\u0001\u0000\u0005\u0003\u0002\u0000\u0006\u0002\u0001\u0000\u0001\u0002\r\u0000\u0001\u0002\u0001\u0000\r\u0003\u0001\u0000\u0001\u0003\u0001\u0000\u0002\u0003\u0001\u0000\u0002\u0003\u0001\u0000\u0001\u0003\u0003\u0002\u0005\u0000\u0005\u0003\u0006\u0000\u0001\u0002\u0004\u0000\u0003\u0003\u0005\u0000\u0003\u0002\u0007\u0003\u0004\u0000\u0002\u0002\u0001\u0003\u000b\u0002\u0001\u0000\u0001\u0002\u0007\u0003\u0002\u0002\u0002\u0003\u0001\u0000\u0004\u0003\u0002\u0002\u0002\u0003\u0003\u0002\u0002\u0000\u0001\u0002\u0007\u0000\u0001\u0003\u0001\u0002\u0001\u0003\u0006\u0002\u0003\u0003\u0002\u0000\t\u0002\u0003\u0003\u0001\u0002\u0006\u0000\u0002\u0003\u0006\u0002\u0004\u0003\u0002\u0002\u0002\u0000\u0002\u0003\u0001\u0002\t\u0003\u0001\u0002\u0003\u0003\u0001\u0002\u0005\u0003\u0002\u0000\u0001\u0002\u0003\u0003\u0004\u0000\u0001\u0002\u0001\u0000\u0006\u0002\u0004\u0000\u000b\u0003\u0001\u0000\u0004\u0003\u0006\u0002\u0003\u0003\u0001\u0002\u0002\u0003\u0001\u0002\u0007\u0003\u0002\u0002\u0002\u0003\u0002\u0000\u0002\u0003\u0001\u0000\u0003\u0003\u0001\u0000\b\u0002\u0002\u0000\u0002\u0002\u0002\u0000\u0006\u0002\u0001\u0000\u0001\u0002\u0003\u0000\u0004\u0002\u0002\u0000\u0001\u0003\u0001\u0002\u0007\u0003\u0002\u0000\u0002\u0003\u0002\u0000\u0003\u0003\u0001\u0002\u0005\u0000\u0002\u0002\u0001\u0000\u0005\u0002\u0004\u0000\u0003\u0002\u0004\u0000\u0002\u0002\u0001\u0000\u0002\u0002\u0001\u0000\u0002\u0002\u0001\u0000\u0002\u0002\u0002\u0000\u0001\u0003\u0001\u0000\u0005\u0003\u0004\u0000\u0002\u0003\u0002\u0000\u0003\u0003\u0003\u0000\u0001\u0003\u0007\u0000\u0004\u0002\u0001\u0000\u0001\u0002\u0007\u0000\u0004\u0003\u0003\u0002\u0001\u0003\u0002\u0000\u0001\u0002\u0001\u0000\u0002\u0002\u0001\u0000\u0003\u0002\u0002\u0003\u0001\u0000\u0003\u0003\u0002\u0000\u0001\u0002\t\u0000\u0001\u0003\u0001\u0002\u0001\u0000\u0006\u0002\u0003\u0000\u0003\u0002\u0001\u0000\u0004\u0002\u0003\u0000\u0002\u0002\u0001\u0000\u0001\u0002\u0001\u0000\u0002\u0002\u0003\u0000\u0002\u0002\u0003\u0000\u0002\u0002\u0004\u0000\u0005\u0003\u0003\u0000\u0003\u0003\u0001\u0000\u0004\u0003\u0002\u0000\u0001\u0002\u0006\u0000\u0001\u0003\u0004\u0002\u0001\u0000\u0005\u0002\u0003\u0000\u0001\u0002\u0007\u0003\u0001\u0000\u0002\u0003\u0005\u0000\u0002\u0003\u0003\u0000\u0002\u0003\u0001\u0000\u0003\u0002\u0001\u0000\u0002\u0002\u0005\u0000\u0003\u0002\u0002\u0000\u0001\u0002\u0003\u0003\u0001\u0000\u0004\u0003\u0001\u0002\u0001\u0000\u0004\u0002\u0001\u0000\u0001\u0002\u0004\u0000\u0001\u0003\u0004\u0000\u0006\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0002\u0003\u0004\u0000\u0001\u0002\u0001\u0003\u0002\u0002\u0007\u0003\u0004\u0000\b\u0002\u0003\u0003\u0007\u0000\u0002\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0002\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0001\u0002\u0006\u0000\u0004\u0002\u0001\u0000\u0003\u0002\u0001\u0000\u0001\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0002\u0002\u0001\u0000\u0003\u0002\u0002\u0003\u0001\u0000\u0002\u0003\u0001\u0002\u0002\u0000\u0005\u0002\u0001\u0000\u0001\u0002\u0001\u0000\u0006\u0003\u0002\u0000\u0002\u0003\u0002\u0000\u0004\u0002\u0005\u0000\u0001\u0003\u0001\u0000\u0001\u0003\u0001\u0000\u0001\u0003\u0004\u0000\u0002\u0003\u0005\u0002\u0003\u0003\u0006\u0000\u0001\u0003\u0001\u0000\u0007\u0003\u0001\u0002\u0002\u0003\u0004\u0002\u0003\u0003\u0001\u0002\u0003\u0003\u0002\u0002\u0007\u0003\u0003\u0002\u0004\u0003\u0005\u0002\f\u0003\u0001\u0002\u0001\u0003\u0003\u0002\u0001\u0000\u0007\u0002\u0002\u0000\u0003\u0003\u0002\u0002\u0003\u0003\u0003\u0000\u0002\u0002\u0002\u0003\u0004\u0000\u0001\u0002\u0001\u0000\u0002\u0003\u0004\u0000\u0004\u0002\b\u0003\u0003\u0000\u0001\u0002\u0003\u0000\u0002\u0002\u0001\u0003\u0005\u0000\u0003\u0003\u0002\u0000\u0001\u0002\u0001\u0003\u0001\u0002\u0005\u0000\u0006\u0002\u0002\u0000\u0005\u0003\u0003\u0002\u0003\u0000\b\u0003\u0005\u0002\u0002\u0003\u0003\u0000\u0003\u0002\u0003\u0003\u0001\u0000\u0005\u0003\u0004\u0002\u0001\u0003\u0004\u0002\u0003\u0003\u0002\u0002\u0002\u0000\u0001\u0002\u0001\u0000\u0001\u0002\u0001\u0000\u0001\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0003\u0002\u0001\u0000\u0006\u0002\u0002\u0000\u0002\u0002\u0002\u0000\u0005\u0003\u0005\u0000\u0001\u0002\u0005\u0000\u0006\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0004\u0003\t\u0000\u0001\u0002\u0004\u0000\u0001\u0002\u0001\u0000\u0005\u0002\u0002\u0000\u0001\u0002\u0001\u0000\u0004\u0002\u0001\u0000\u0003\u0002\u0002\u0000\u0004\u0002\u0005\u0000\u0005\u0002\u0004\u0000\u0001\u0002\u0004\u0000\u0004\u0002\u0003\u0003\u0002\u0002\u0005\u0000\u0002\u0003\u0002\u0000\u0003\u0002\u0006\u0003\u0001\u0000\u0002\u0002\u0002\u0000\u0004\u0002\u0001\u0000\u0002\u0002\u0001\u0003\u0003\u0002\u0001\u0003\u0004\u0002\u0001\u0003\b\u0002\u0002\u0003\u0004\u0000\u0001\u0002\u0001\u0003\u0004\u0000\u0001\u0003\u0005\u0002\u0002\u0003\u0003\u0000\u0003\u0002\u0004\u0000\u0003\u0002\u0002\u0003\u0002\u0000\u0006\u0002\u0001\u0000\u0003\u0003\u0001\u0000\u0002\u0003\u0005\u0000\u0005\u0002\u0005\u0000\u0001\u0002\u0001\u0003\u0003\u0002\u0001\u0000\u0002\u0002\u0001\u0000\u0007\u0002\u0002\u0000\u0001\u0003\u0006\u0000\u0002\u0002\u0002\u0000\u0003\u0002\u0003\u0000\u0002\u0002\u0003\u0000\u0002\u0002\u0002\u0000\u0003\u0003\u0004\u0000\u0003\u0002\u0001\u0000\u0002\u0002\u0001\u0000\u0001\u0002\u0005\u0000\u0001\u0003\u0002\u0000\u0001\u0002\u0003\u0000\u0001\u0002\u0002\u0000\u0002\u0002\u0003\u0003\u0001\u0000\u0002\u0003\u0001\u0000\u0003\u0003\u0002\u0000\u0001\u0003\u0002\u0000\u0001\u0003\u0004\u0002\b\u0000\u0005\u0003\u0003\u0000\u0006\u0003\u0002\u0000\u0003\u0003\u0002\u0000\u0004\u0003\u0004\u0000\u0003\u0003\u0005\u0000\u0001\u0002\u0002\u0000\u0002\u0002\u0002\u0000\u0004\u0002\u0001\u0000\u0004\u0002\u0001\u0000\u0001\u0002\u0001\u0000\u0006\u0002\u0002\u0000\u0005\u0002\u0001\u0000\u0004\u0002\u0001\u0000\u0004\u0002\u0002\u0000\u0002\u0003\u0001\u0000\u0001\u0002\u0001\u0000\u0001\u0002\u0005\u0000\u0001\u0002\u0001\u0000\u0001\u0002\u0001\u0000\u0003\u0002\u0001\u0000\u0003\u0002\u0001\u0000\u0003\u0002");
        ZZ_ACTION = e();
        ZZ_ROWMAP = b();
        ZZ_TRANS = a();
        ZZ_ERROR_MSG = new String[] { "Unknown internal scanner error", "Error: could not match input", "Error: pushback value was too large" };
        ZZ_ATTRIBUTE = c();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
