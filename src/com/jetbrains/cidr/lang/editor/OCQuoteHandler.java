// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.highlighter.HighlighterIterator;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.codeInsight.editorActions.JavaLikeQuoteHandler;
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler;

public class OCQuoteHandler extends SimpleTokenSetQuoteHandler implements JavaLikeQuoteHandler
{
    private static final TokenSet CONCATENATABLE_STRING;
    
    public OCQuoteHandler() {
        super(new IElementType[] { OCTokenTypes.STRING_LITERAL, OCTokenTypes.CHARACTER_LITERAL });
    }
    
    @Override
    public boolean isOpeningQuote(final HighlighterIterator p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)Z
        //     4: ifeq            13
        //     7: iconst_0       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_0        
        //    14: aload_1        
        //    15: iload_2        
        //    16: invokespecial   com/intellij/codeInsight/editorActions/SimpleTokenSetQuoteHandler.isOpeningQuote:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;I)Z
        //    19: istore_3       
        //    20: iload_3        
        //    21: ifeq            92
        //    24: aload_1        
        //    25: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //    30: ifne            92
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_1        
        //    41: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //    46: aload_1        
        //    47: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //    52: ifne            86
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: getstatic       com/intellij/psi/StringEscapesTokenTypes.STRING_LITERAL_ESCAPES:Lcom/intellij/psi/tree/TokenSet;
        //    65: aload_1        
        //    66: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    71: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    74: ifeq            86
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: iconst_0       
        //    85: istore_3       
        //    86: aload_1        
        //    87: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.advance:()V
        //    92: iload_3        
        //    93: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  20     33     36     40     Ljava/lang/IllegalArgumentException;
        //  24     55     58     62     Ljava/lang/IllegalArgumentException;
        //  40     77     80     84     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
    
    @Override
    public boolean isClosingQuote(final HighlighterIterator p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;)Z
        //     4: ifeq            13
        //     7: iconst_0       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_0        
        //    14: aload_1        
        //    15: iload_2        
        //    16: invokespecial   com/intellij/codeInsight/editorActions/SimpleTokenSetQuoteHandler.isClosingQuote:(Lcom/intellij/openapi/editor/highlighter/HighlighterIterator;I)Z
        //    19: istore_3       
        //    20: iload_3        
        //    21: ifeq            92
        //    24: aload_1        
        //    25: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //    30: ifne            92
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_1        
        //    41: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.advance:()V
        //    46: aload_1        
        //    47: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.atEnd:()Z
        //    52: ifne            86
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: getstatic       com/intellij/psi/StringEscapesTokenTypes.STRING_LITERAL_ESCAPES:Lcom/intellij/psi/tree/TokenSet;
        //    65: aload_1        
        //    66: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    71: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    74: ifeq            86
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: iconst_0       
        //    85: istore_3       
        //    86: aload_1        
        //    87: invokeinterface com/intellij/openapi/editor/highlighter/HighlighterIterator.retreat:()V
        //    92: iload_3        
        //    93: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  20     33     36     40     Ljava/lang/IllegalArgumentException;
        //  24     55     58     62     Ljava/lang/IllegalArgumentException;
        //  40     77     80     84     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
    
    private static boolean a(final HighlighterIterator highlighterIterator) {
        boolean contains = false;
        if (highlighterIterator.getStart() > 0) {
            highlighterIterator.retreat();
            contains = OCTokenTypes.ALL_NUMERIC.contains(highlighterIterator.getTokenType());
            highlighterIterator.advance();
        }
        return contains;
    }
    
    @Override
    public TokenSet getConcatenatableStringTokenTypes() {
        return TokenSet.EMPTY;
    }
    
    @Override
    public String getStringConcatenationOperatorRepresentation() {
        return "";
    }
    
    @Override
    public TokenSet getStringTokenTypes() {
        return OCQuoteHandler.CONCATENATABLE_STRING;
    }
    
    @Override
    public boolean isAppropriateElementTypeForLiteral(@NotNull final IElementType elementType) {
        try {
            if (elementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tokenType", "com/jetbrains/cidr/lang/editor/OCQuoteHandler", "isAppropriateElementTypeForLiteral"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return isAppropriateElementTypeForLiteralStatic(elementType);
    }
    
    public static boolean isAppropriateElementTypeForLiteralStatic(final IElementType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //     3: aload_0        
        //     4: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //     7: ifne            108
        //    10: aload_0        
        //    11: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    14: if_acmpeq       108
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    28: if_acmpeq       108
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_0        
        //    39: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    42: if_acmpeq       108
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aload_0        
        //    53: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACKET:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    56: if_acmpeq       108
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: aload_0        
        //    67: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    70: if_acmpeq       108
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aload_0        
        //    81: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    84: if_acmpeq       108
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_0        
        //    95: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CHARACTER_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    98: if_acmpne       116
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: iconst_1       
        //   109: goto            117
        //   112: invokestatic    com/jetbrains/cidr/lang/editor/OCQuoteHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: iconst_0       
        //   117: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  10     31     34     38     Ljava/lang/IllegalArgumentException;
        //  24     45     48     52     Ljava/lang/IllegalArgumentException;
        //  38     59     62     66     Ljava/lang/IllegalArgumentException;
        //  52     73     76     80     Ljava/lang/IllegalArgumentException;
        //  66     87     90     94     Ljava/lang/IllegalArgumentException;
        //  80     101    104    108    Ljava/lang/IllegalArgumentException;
        //  94     112    112    116    Ljava/lang/IllegalArgumentException;
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
    
    @Override
    public boolean needParenthesesAroundConcatenation(final PsiElement psiElement) {
        return false;
    }
    
    static {
        CONCATENATABLE_STRING = TokenSet.create(new IElementType[] { OCTokenTypes.STRING_LITERAL });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
