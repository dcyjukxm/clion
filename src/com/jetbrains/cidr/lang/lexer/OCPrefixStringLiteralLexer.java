// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.lexer;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.lexer.LexerBase;

public class OCPrefixStringLiteralLexer extends LexerBase
{
    private static final Logger LOG;
    private static final short PREFIX = 99;
    private static final short AFTER_PREFIX = 100;
    public static final char NO_QUOTE_CHAR = '\uffff';
    private CharSequence myBuffer;
    private int myStart;
    private int myEnd;
    private int myState;
    private int myLastState;
    private int myBufferEnd;
    private final char myPrefixStopChar;
    private final LexerBase myRestLexer;
    private final IElementType myPrefixLiteralToken;
    
    public OCPrefixStringLiteralLexer(final char myPrefixStopChar, @NotNull final IElementType myPrefixLiteralToken, @NotNull final LexerBase myRestLexer) {
        if (myPrefixLiteralToken == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefixLiteralToken", "com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer", "<init>"));
        }
        if (myRestLexer == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "restLexer", "com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer", "<init>"));
        }
        this.myPrefixStopChar = myPrefixStopChar;
        this.myPrefixLiteralToken = myPrefixLiteralToken;
        this.myRestLexer = myRestLexer;
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
        //    24: ldc             "com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "start"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface java/lang/CharSequence.length:()I
        //    50: iload_3        
        //    51: if_icmpge       113
        //    54: getstatic       com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.LOG:Lcom/intellij/openapi/diagnostic/Logger;
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
        //   109: invokestatic    com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_0        
        //   114: aload_1        
        //   115: putfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myBuffer:Ljava/lang/CharSequence;
        //   118: aload_0        
        //   119: iload_2        
        //   120: putfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myStart:I
        //   123: aload_0        
        //   124: iload_3        
        //   125: putfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myBufferEnd:I
        //   128: aload_0        
        //   129: iload           4
        //   131: putfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myLastState:I
        //   134: aload_0        
        //   135: aload_0        
        //   136: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myStart:I
        //   139: putfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myEnd:I
        //   142: aload_0        
        //   143: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myPrefixStopChar:C
        //   146: ldc             65535
        //   148: if_icmpeq       213
        //   151: aload_0        
        //   152: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myEnd:I
        //   155: aload_0        
        //   156: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myBufferEnd:I
        //   159: if_icmpge       213
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload_0        
        //   170: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myBuffer:Ljava/lang/CharSequence;
        //   173: aload_0        
        //   174: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myEnd:I
        //   177: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myPrefixStopChar:C
        //   186: if_icmpeq       213
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: aload_0        
        //   197: dup            
        //   198: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myEnd:I
        //   201: iconst_1       
        //   202: iadd           
        //   203: putfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myEnd:I
        //   206: goto            151
        //   209: invokestatic    com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: aload_0        
        //   214: aload_0        
        //   215: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myEnd:I
        //   218: aload_0        
        //   219: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myBufferEnd:I
        //   222: if_icmpne       236
        //   225: aload_0        
        //   226: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myLastState:I
        //   229: goto            238
        //   232: invokestatic    com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: bipush          99
        //   238: putfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myState:I
        //   241: aload_0        
        //   242: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myEnd:I
        //   245: aload_0        
        //   246: getfield        com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.myStart:I
        //   249: if_icmpne       263
        //   252: aload_0        
        //   253: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.advance:()V
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     106    109    113    Ljava/lang/IllegalArgumentException;
        //  113    162    165    169    Ljava/lang/IllegalArgumentException;
        //  151    189    192    196    Ljava/lang/IllegalArgumentException;
        //  169    209    209    213    Ljava/lang/IllegalArgumentException;
        //  213    232    232    236    Ljava/lang/IllegalArgumentException;
        //  238    256    259    263    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0151:
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
    
    public int getState() {
        try {
            if (this.myState == 100) {
                return this.myRestLexer.getState();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myLastState;
    }
    
    @Nullable
    public IElementType getTokenType() {
        try {
            if (this.myState == 100) {
                return this.myRestLexer.getTokenType();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myPrefixLiteralToken;
    }
    
    public int getTokenStart() {
        try {
            if (this.myState == 100) {
                return this.myRestLexer.getTokenStart();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myStart;
    }
    
    public int getTokenEnd() {
        try {
            if (this.myState == 100) {
                return this.myRestLexer.getTokenEnd();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myEnd;
    }
    
    public void advance() {
        try {
            if (this.myState == 100) {
                this.myRestLexer.advance();
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myState == 99) {
                this.myRestLexer.start(this.myBuffer, this.myEnd, this.myBufferEnd, this.myLastState);
                this.myState = 100;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @NotNull
    public CharSequence getBufferSequence() {
        CharSequence myBuffer;
        try {
            myBuffer = this.myBuffer;
            if (myBuffer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer", "getBufferSequence"));
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
