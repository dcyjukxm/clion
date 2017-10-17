// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.lexer.LexerBase;

public class OCRawStringLexerBase extends LexerBase
{
    private static final Logger LOG;
    private static final short LQUOTE = 100;
    private static final short PREFIX = 101;
    private static final short INSIDE = 102;
    private static final short SUFFIX = 103;
    private static final short RQUOTE = 104;
    private CharSequence myBuffer;
    private int myStart;
    private int myEnd;
    private int myState;
    private int myPrefixStart;
    private int myPrefixEnd;
    private int mySuffixStart;
    private int mySuffixEnd;
    private int myLastState;
    private int myBufferEnd;
    private final IElementType myMarkerLiteralToken;
    private final IElementType myContentLiteralToken;
    
    public OCRawStringLexerBase(@NotNull final IElementType myMarkerLiteralToken, @NotNull final IElementType myContentLiteralToken) {
        if (myMarkerLiteralToken == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "markerLiteralToken", "com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase", "<init>"));
        }
        if (myContentLiteralToken == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contentLiteralToken", "com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase", "<init>"));
        }
        this.myMarkerLiteralToken = myMarkerLiteralToken;
        this.myContentLiteralToken = myContentLiteralToken;
    }
    
    @Contract(pure = true)
    private static boolean a(final char c) {
        try {
            if ("\\\t\f\r\n\u000b ()".indexOf(c) == -1) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public void start(@NotNull final CharSequence p0, final int p1, final int p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "buffer"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "start"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface java/lang/CharSequence.length:()I
        //    50: iload_3        
        //    51: if_icmpge       113
        //    54: getstatic       com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //    57: new             Ljava/lang/StringBuilder;
        //    60: dup            
        //    61: invokespecial   java/lang/StringBuilder.<init>:()V
        //    64: ldc             "buffer Length: "
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: aload_1        
        //    70: invokeinterface java/lang/CharSequence.length:()I
        //    75: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    78: ldc             ", endOffset: "
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    83: iload_3        
        //    84: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    87: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    90: iconst_1       
        //    91: anewarray       Ljava/lang/String;
        //    94: dup            
        //    95: iconst_0       
        //    96: aload_1        
        //    97: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   102: aastore        
        //   103: invokestatic    com/intellij/diagnostic/LogMessageEx.error:(Lcom/intellij/openapi/diagnostic/Logger;Ljava/lang/String;[Ljava/lang/String;)V
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_0        
        //   114: aload_1        
        //   115: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBuffer:Ljava/lang/CharSequence;
        //   118: aload_0        
        //   119: iload_2        
        //   120: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myStart:I
        //   123: aload_0        
        //   124: iload_3        
        //   125: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBufferEnd:I
        //   128: aload_0        
        //   129: iload           4
        //   131: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myLastState:I
        //   134: aload_1        
        //   135: invokeinterface java/lang/CharSequence.length:()I
        //   140: ifle            222
        //   143: aload_1        
        //   144: iload_2        
        //   145: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   150: bipush          34
        //   152: if_icmpne       222
        //   155: goto            162
        //   158: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: aload_0        
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myStart:I
        //   167: iconst_1       
        //   168: iadd           
        //   169: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myEnd:I
        //   172: aload_0        
        //   173: bipush          100
        //   175: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myState:I
        //   178: aload_1        
        //   179: aload_0        
        //   180: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBufferEnd:I
        //   183: iconst_1       
        //   184: isub           
        //   185: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   190: bipush          34
        //   192: if_icmpne       213
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: aload_0        
        //   203: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBufferEnd:I
        //   206: iconst_1       
        //   207: isub           
        //   208: istore          5
        //   210: goto            242
        //   213: aload_0        
        //   214: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBufferEnd:I
        //   217: istore          5
        //   219: goto            242
        //   222: aload_0        
        //   223: aload_0        
        //   224: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myStart:I
        //   227: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myEnd:I
        //   230: aload_0        
        //   231: bipush          101
        //   233: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myState:I
        //   236: aload_0        
        //   237: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBufferEnd:I
        //   240: istore          5
        //   242: aload_0        
        //   243: aload_0        
        //   244: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myEnd:I
        //   247: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixStart:I
        //   250: aload_0        
        //   251: aload_0        
        //   252: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixStart:I
        //   255: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   258: iconst_0       
        //   259: istore          6
        //   261: aload_0        
        //   262: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   265: iload           5
        //   267: if_icmpge       330
        //   270: aload_0        
        //   271: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBuffer:Ljava/lang/CharSequence;
        //   274: aload_0        
        //   275: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   278: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   283: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(C)Z
        //   286: ifeq            330
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: iload           6
        //   298: bipush          16
        //   300: if_icmpge       330
        //   303: goto            310
        //   306: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   309: athrow         
        //   310: aload_0        
        //   311: dup            
        //   312: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   315: iconst_1       
        //   316: iadd           
        //   317: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   320: iinc            6, 1
        //   323: goto            261
        //   326: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   329: athrow         
        //   330: aload_0        
        //   331: aload_0        
        //   332: iload           5
        //   334: dup_x1         
        //   335: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.mySuffixEnd:I
        //   338: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.mySuffixStart:I
        //   341: aload_0        
        //   342: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   345: aload_0        
        //   346: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBufferEnd:I
        //   349: if_icmpge       533
        //   352: iload           6
        //   354: bipush          16
        //   356: if_icmpge       533
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: aload_0        
        //   367: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBuffer:Ljava/lang/CharSequence;
        //   370: aload_0        
        //   371: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   374: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   379: bipush          40
        //   381: if_icmpne       533
        //   384: goto            391
        //   387: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: aload_0        
        //   392: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   395: aload_0        
        //   396: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixStart:I
        //   399: if_icmpne       418
        //   402: goto            409
        //   405: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   408: athrow         
        //   409: ldc             ""
        //   411: goto            440
        //   414: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   417: athrow         
        //   418: aload_0        
        //   419: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBuffer:Ljava/lang/CharSequence;
        //   422: aload_0        
        //   423: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixStart:I
        //   426: aload_0        
        //   427: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   430: invokeinterface java/lang/CharSequence.subSequence:(II)Ljava/lang/CharSequence;
        //   435: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   440: astore          7
        //   442: aload_0        
        //   443: dup            
        //   444: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   447: iconst_1       
        //   448: iadd           
        //   449: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myPrefixEnd:I
        //   452: new             Ljava/lang/StringBuilder;
        //   455: dup            
        //   456: invokespecial   java/lang/StringBuilder.<init>:()V
        //   459: ldc             ")"
        //   461: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   464: aload           7
        //   466: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   469: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   472: astore          8
        //   474: iload           5
        //   476: aload           8
        //   478: invokevirtual   java/lang/String.length:()I
        //   481: isub           
        //   482: istore          9
        //   484: aload_0        
        //   485: getfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.myBuffer:Ljava/lang/CharSequence;
        //   488: iload           9
        //   490: iload           5
        //   492: invokeinterface java/lang/CharSequence.subSequence:(II)Ljava/lang/CharSequence;
        //   497: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   502: astore          10
        //   504: aload           10
        //   506: aload           8
        //   508: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   511: ifeq            533
        //   514: aload_0        
        //   515: iload           9
        //   517: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.mySuffixStart:I
        //   520: aload_0        
        //   521: iload           5
        //   523: putfield        com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.mySuffixEnd:I
        //   526: goto            533
        //   529: invokestatic    com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     106    109    113    Ljava/lang/IllegalArgumentException;
        //  113    155    158    162    Ljava/lang/IllegalArgumentException;
        //  143    195    198    202    Ljava/lang/IllegalArgumentException;
        //  261    289    292    296    Ljava/lang/IllegalArgumentException;
        //  270    303    306    310    Ljava/lang/IllegalArgumentException;
        //  296    326    326    330    Ljava/lang/IllegalArgumentException;
        //  330    359    362    366    Ljava/lang/IllegalArgumentException;
        //  352    384    387    391    Ljava/lang/IllegalArgumentException;
        //  366    402    405    409    Ljava/lang/IllegalArgumentException;
        //  391    414    414    418    Ljava/lang/IllegalArgumentException;
        //  504    526    529    533    Ljava/lang/IllegalArgumentException;
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
    
    public String getRawText() {
        return this.myBuffer.subSequence(this.myPrefixEnd, this.mySuffixStart).toString();
    }
    
    public int getState() {
        return this.myLastState;
    }
    
    @Nullable
    public IElementType getTokenType() {
        try {
            if (this.myStart >= this.myEnd) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            switch (this.myState) {
                case 101:
                case 103: {
                    return this.myMarkerLiteralToken;
                }
                case 100:
                case 102:
                case 104: {
                    break;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.myContentLiteralToken;
    }
    
    public int getTokenStart() {
        return this.myStart;
    }
    
    public int getTokenEnd() {
        return this.myEnd;
    }
    
    public void advance() {
        Label_0080: {
            OCRawStringLexerBase ocRawStringLexerBase = null;
            int myState = 0;
            Label_0066: {
                Label_0055: {
                    try {
                        this.myLastState = this.myState;
                        this.myStart = this.myEnd;
                        if (this.myEnd != this.myBufferEnd - 1) {
                            break Label_0080;
                        }
                        ocRawStringLexerBase = this;
                        final OCRawStringLexerBase ocRawStringLexerBase2 = this;
                        final CharSequence charSequence = ocRawStringLexerBase2.myBuffer;
                        final OCRawStringLexerBase ocRawStringLexerBase3 = this;
                        final int n = ocRawStringLexerBase3.myEnd;
                        final char c = charSequence.charAt(n);
                        final char c2 = '\"';
                        if (c == c2) {
                            break Label_0055;
                        }
                        break Label_0055;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        ocRawStringLexerBase = this;
                        final OCRawStringLexerBase ocRawStringLexerBase2 = this;
                        final CharSequence charSequence = ocRawStringLexerBase2.myBuffer;
                        final OCRawStringLexerBase ocRawStringLexerBase3 = this;
                        final int n = ocRawStringLexerBase3.myEnd;
                        final char c = charSequence.charAt(n);
                        final char c2 = '\"';
                        if (c == c2) {
                            myState = 104;
                            break Label_0066;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                myState = 101;
            }
            ocRawStringLexerBase.myState = myState;
            this.myEnd = this.myBufferEnd;
            return;
            try {
                if (this.myEnd == this.myPrefixStart) {
                    this.myEnd = this.myPrefixEnd;
                    this.myState = 101;
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        try {
            if (this.myEnd == this.mySuffixStart) {
                this.myEnd = this.mySuffixEnd;
                this.myState = 103;
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (this.myEnd == this.myPrefixEnd) {
                this.myEnd = this.mySuffixStart;
                this.myState = 102;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
    }
    
    @NotNull
    public CharSequence getBufferSequence() {
        CharSequence myBuffer;
        try {
            myBuffer = this.myBuffer;
            if (myBuffer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase", "getBufferSequence"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myBuffer;
    }
    
    public int getBufferEnd() {
        return this.myBufferEnd;
    }
    
    static {
        LOG = Logger.getInstance("#com.intellij.lexer.StringLiteralLexer");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
