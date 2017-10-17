// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lang.lexer;

import com.jetbrains.cidr.execution.debugger.backend.lang.GDBTokenType;
import com.intellij.psi.tree.IElementType;
import java.io.IOException;
import java.io.Reader;
import com.intellij.lexer.FlexLexer;

class _GDBLexer implements FlexLexer
{
    public static final int YYEOF = -1;
    private static final int ZZ_BUFFERSIZE = 16384;
    public static final int YYINITIAL = 0;
    private static final int[] ZZ_LEXSTATE;
    static final char[] ZZ_CMAP_Z;
    static final char[] ZZ_CMAP_Y;
    static final char[] ZZ_CMAP_A;
    private static final int[] ZZ_ACTION;
    private static final String ZZ_ACTION_PACKED_0 = "\u0001\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0002\u0001\u0001\u0005\u0001\b\u0002\u0007\u0001\t\u0001\n\u0001\u0000\u0001\u000b";
    private static final int[] ZZ_ROWMAP;
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000\u0013\u0000&\u00009\u0000L\u0000_\u0000\u0013\u0000r\u0000\u0085\u0000\u0098\u0000«\u0000\u0013\u0000¾\u0000\u0013\u0000\u0085\u0000\u0013\u0000\u00d1\u0000\u00d1";
    private static final int[] ZZ_TRANS;
    private static final String ZZ_TRANS_PACKED_0 = "\u0001\u0002\u0003\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0004\u0001\u0007\u0001\u0002\u0001\b\u0001\t\u0001\n\u0001\u0002\u0002\u0004\u0001\u000b\u0001\u0004\u0015\u0000\u0003\u0003\u0013\u0000\u0004\u0004\u0006\u0000\u0004\u0004\u0006\u0000\u0001\f\u0013\u0000\u0001\u0006\t\u0000\u0001\u0006\u0002\u0000\u0003\b\u0001\u0000\u0005\b\u0001\r\u0001\u000e\b\b\u0006\u0000\u0001\u000f\t\u0000\u0001\u000f\u0003\u0000\u0001\u0010\u000b\u0000\u0003\u0010\u0001\u0000\u0001\u0010\u0007\u0000\u0001\u0006\t\u0000\u0001\u0006\u0001\u0011\u0001\u0000\u0003\b\u0001\u0000\u000f\b\u0006\u0000\u0002\u0012\u0007\u0000\u0002\u0012\u0002\u0000";
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final String[] ZZ_ERROR_MSG;
    private static final int[] ZZ_ATTRIBUTE;
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0001\u0000\u0001\t\u0004\u0001\u0001\t\u0004\u0001\u0001\t\u0001\u0001\u0001\t\u0001\u0001\u0001\t\u0001\u0000\u0001\u0001";
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
        return _GDBLexer.ZZ_CMAP_A[_GDBLexer.ZZ_CMAP_Y[_GDBLexer.ZZ_CMAP_Z[n >> 12] | (n >> 6 & 0x3F)] << 6 | (n & 0x3F)];
    }
    
    private static int[] c() {
        final int[] array = new int[18];
        d("\u0001\u0000\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0007\u0002\u0001\u0001\u0005\u0001\b\u0002\u0007\u0001\t\u0001\n\u0001\u0000\u0001\u000b", 0, array);
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
        final int[] array = new int[18];
        b("\u0000\u0000\u0000\u0013\u0000&\u00009\u0000L\u0000_\u0000\u0013\u0000r\u0000\u0085\u0000\u0098\u0000«\u0000\u0013\u0000¾\u0000\u0013\u0000\u0085\u0000\u0013\u0000\u00d1\u0000\u00d1", 0, array);
        return array;
    }
    
    private static int b(final String s, final int n, final int[] array) {
        int i;
        int n2;
        for (i = 0, n2 = n; i < s.length(); array[n2++] = (s.charAt(i++) << 16 | s.charAt(i++))) {}
        return n2;
    }
    
    private static int[] a() {
        final int[] array = new int[228];
        c("\u0001\u0002\u0003\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\u0004\u0001\u0007\u0001\u0002\u0001\b\u0001\t\u0001\n\u0001\u0002\u0002\u0004\u0001\u000b\u0001\u0004\u0015\u0000\u0003\u0003\u0013\u0000\u0004\u0004\u0006\u0000\u0004\u0004\u0006\u0000\u0001\f\u0013\u0000\u0001\u0006\t\u0000\u0001\u0006\u0002\u0000\u0003\b\u0001\u0000\u0005\b\u0001\r\u0001\u000e\b\b\u0006\u0000\u0001\u000f\t\u0000\u0001\u000f\u0003\u0000\u0001\u0010\u000b\u0000\u0003\u0010\u0001\u0000\u0001\u0010\u0007\u0000\u0001\u0006\t\u0000\u0001\u0006\u0001\u0011\u0001\u0000\u0003\b\u0001\u0000\u000f\b\u0006\u0000\u0002\u0012\u0007\u0000\u0002\u0012\u0002\u0000", 0, array);
        return array;
    }
    
    private static int c(final String s, final int n, final int[] array) {
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
        final int[] array = new int[18];
        a("\u0001\u0000\u0001\t\u0004\u0001\u0001\t\u0004\u0001\u0001\t\u0001\u0001\u0001\t\u0001\u0001\u0001\t\u0001\u0000\u0001\u0001", 0, array);
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
    
    _GDBLexer(final Reader zzReader) {
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
            s = _GDBLexer.ZZ_ERROR_MSG[n];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            s = _GDBLexer.ZZ_ERROR_MSG[0];
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
        final int[] zz_TRANS = _GDBLexer.ZZ_TRANS;
        final int[] zz_ROWMAP = _GDBLexer.ZZ_ROWMAP;
        final int[] zz_ATTRIBUTE = _GDBLexer.ZZ_ATTRIBUTE;
        while (true) {
            int n2 = this.zzMarkedPos;
            int n3 = -1;
            final int n4 = n2;
            this.zzStartRead = n4;
            this.zzCurrentPos = n4;
            int zzCurrentPos = n4;
            this.zzState = _GDBLexer.ZZ_LEXSTATE[this.zzLexicalState];
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
            Label_0501: {
                Label_0319: {
                    Label_0302: {
                        Label_0291: {
                            try {
                                this.zzMarkedPos = n2;
                                if (n5 != -1) {
                                    break Label_0302;
                                }
                                final _GDBLexer gdbLexer = this;
                                final int n7 = gdbLexer.zzStartRead;
                                final _GDBLexer gdbLexer2 = this;
                                final int n8 = gdbLexer2.zzCurrentPos;
                                if (n7 == n8) {
                                    break Label_0291;
                                }
                                break Label_0302;
                            }
                            catch (IOException ex3) {
                                throw b(ex3);
                            }
                            try {
                                final _GDBLexer gdbLexer = this;
                                final int n7 = gdbLexer.zzStartRead;
                                final _GDBLexer gdbLexer2 = this;
                                final int n8 = gdbLexer2.zzCurrentPos;
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
                    final int n9 = _GDBLexer.ZZ_ACTION[n3];
                    try {
                        switch (n9) {
                            case 1: {
                                return GDBTokenType.SOME_CHARACTER;
                            }
                            case 12: {
                                continue;
                            }
                            case 2: {
                                return GDBTokenType.WHITE_SPACE;
                            }
                            case 13: {
                                continue;
                            }
                            case 3: {
                                return GDBTokenType.IDENTIFIER;
                            }
                            case 14: {
                                continue;
                            }
                            case 4: {
                                return GDBTokenType.DASH;
                            }
                            case 15: {
                                continue;
                            }
                            case 5: {
                                return GDBTokenType.NUMBER;
                            }
                            case 16: {
                                continue;
                            }
                            case 6: {
                                return GDBTokenType.STAR;
                            }
                            case 17: {
                                continue;
                            }
                            case 7: {
                                return GDBTokenType.QUOTED_STRING;
                            }
                            case 18: {
                                continue;
                            }
                            case 8: {
                                return GDBTokenType.DASHDASH;
                            }
                            case 19: {
                                continue;
                            }
                            case 9: {
                                return GDBTokenType.AT;
                            }
                            case 20: {
                                continue;
                            }
                            case 10: {
                                return GDBTokenType.FORMAT_SPECIFIER;
                            }
                            case 21: {
                                continue;
                            }
                            case 11: {
                                return GDBTokenType.HEX_NUMBER;
                            }
                            case 22: {
                                continue;
                            }
                            default: {
                                break Label_0501;
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
        ZZ_CMAP_Z = a("\u0001\u0000\u0001@\u0001\u0080\u010d@");
        ZZ_CMAP_Y = a("\u0001\u0000\u0001\u0001\u0001\u0002}\u0003\u0001\u0004?\u0003");
        ZZ_CMAP_A = a("\t\u0000\u0001\u0002\u0001\u0003\u0002\u0012\u0001\u0003\u0012\u0000\u0001\u0001\u0001\u0000\u0001\n\u0007\u0000\u0001\b\u0002\u0000\u0001\u0005\u0001\u0000\u0001\f\u0001\u0010\t\u0006\u0006\u0000\u0001\u000b\u001a\u0004\u0001\u0000\u0001\t\u0002\u0000\u0001\u0004\u0001\u0000\u0004\u000f\u0001\u0007\u0001\u000f\b\u0004\u0001\u000e\u0004\u0004\u0002\u000e\u0001\u0004\u0001\u000e\u0001\u0011\u0002\u0004\u0001\u0000\u0001\r\b\u0000\u0001\u0012¢\u0000\u0002\u0012\u0016\u0000");
        ZZ_ACTION = c();
        ZZ_ROWMAP = e();
        ZZ_TRANS = a();
        ZZ_ERROR_MSG = new String[] { "Unknown internal scanner error", "Error: could not match input", "Error: pushback value was too large" };
        ZZ_ATTRIBUTE = b();
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
