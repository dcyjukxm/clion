// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.parser.OCElementType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;

public class OCBraceMatcher implements PairedBraceMatcher
{
    public static final BracePair[] PAIRS;
    
    @NotNull
    public BracePair[] getPairs() {
        BracePair[] pairs;
        try {
            pairs = OCBraceMatcher.PAIRS;
            if (pairs == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCBraceMatcher", "getPairs"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return pairs;
    }
    
    public boolean isPairedBracesAllowedBeforeType(@NotNull final IElementType elementType, @Nullable final IElementType elementType2) {
        try {
            if (elementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lbraceType", "com/jetbrains/cidr/lang/editor/OCBraceMatcher", "isPairedBracesAllowedBeforeType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (elementType2 instanceof OCElementType) {
                return a(elementType2);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return true;
    }
    
    private static boolean a(final IElementType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //     3: aload_0        
        //     4: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //     7: ifne            94
        //    10: aload_0        
        //    11: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    14: if_acmpeq       94
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/editor/OCBraceMatcher.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    28: if_acmpeq       94
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/editor/OCBraceMatcher.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    37: athrow         
        //    38: aload_0        
        //    39: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    42: if_acmpeq       94
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/editor/OCBraceMatcher.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    51: athrow         
        //    52: aload_0        
        //    53: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    56: if_acmpeq       94
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/editor/OCBraceMatcher.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    65: athrow         
        //    66: aload_0        
        //    67: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    70: if_acmpeq       94
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/editor/OCBraceMatcher.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    79: athrow         
        //    80: aload_0        
        //    81: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    84: if_acmpne       102
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/editor/OCBraceMatcher.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    93: athrow         
        //    94: iconst_1       
        //    95: goto            103
        //    98: invokestatic    com/jetbrains/cidr/lang/editor/OCBraceMatcher.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   101: athrow         
        //   102: iconst_0       
        //   103: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      17     20     24     Ljava/lang/IllegalStateException;
        //  10     31     34     38     Ljava/lang/IllegalStateException;
        //  24     45     48     52     Ljava/lang/IllegalStateException;
        //  38     59     62     66     Ljava/lang/IllegalStateException;
        //  52     73     76     80     Ljava/lang/IllegalStateException;
        //  66     87     90     94     Ljava/lang/IllegalStateException;
        //  80     98     98     102    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    public int getCodeConstructStart(final PsiFile psiFile, final int n) {
        return n;
    }
    
    static {
        PAIRS = new BracePair[] { new BracePair((IElementType)OCTokenTypes.LPAR, (IElementType)OCTokenTypes.RPAR, false), new BracePair((IElementType)OCTokenTypes.LBRACE, (IElementType)OCTokenTypes.RBRACE, true), new BracePair((IElementType)OCTokenTypes.LBRACKET, (IElementType)OCTokenTypes.RBRACKET, false), new BracePair((IElementType)OCTokenTypes.PROTOCOL_KEYWORD, (IElementType)OCTokenTypes.END_KEYWORD, false), new BracePair((IElementType)OCTokenTypes.INTERFACE_KEYWORD, (IElementType)OCTokenTypes.END_KEYWORD, false), new BracePair((IElementType)OCTokenTypes.IMPLEMENTATION_KEYWORD, (IElementType)OCTokenTypes.END_KEYWORD, false) };
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
