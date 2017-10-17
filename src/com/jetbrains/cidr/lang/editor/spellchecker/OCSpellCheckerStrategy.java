// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.spellchecker;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.spellchecker.inspections.Splitter;
import com.intellij.spellchecker.inspections.PlainTextSplitter;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.StringEscapesTokenTypes;
import com.intellij.lexer.LexerBase;
import com.jetbrains.cidr.lang.lexer.OCPrefixStringLiteralLexer;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.lexer.OCHighlightingLexer;
import com.intellij.spellchecker.tokenizer.TokenConsumer;
import org.jetbrains.annotations.NotNull;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;

public class OCSpellCheckerStrategy extends SpellcheckingStrategy
{
    private static final StringLiteralTokenizer STRING_LITERAL_TOKENIZER;
    
    @NotNull
    @Override
    public Tokenizer getTokenizer(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //     4: ifne            118
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
        //    11: ifne            118
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    20: athrow         
        //    21: aload_1        
        //    22: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //    25: ifne            118
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    34: athrow         
        //    35: aload_1        
        //    36: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    39: ifne            118
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    48: athrow         
        //    49: aload_1        
        //    50: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    53: ifeq            82
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    62: athrow         
        //    63: aload_1        
        //    64: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    67: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.isDeclaration:()Z
        //    72: ifeq            118
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    81: athrow         
        //    82: aload_1        
        //    83: instanceof      Lcom/intellij/psi/PsiComment;
        //    86: ifeq            167
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    95: athrow         
        //    96: aload_1        
        //    97: checkcast       Lcom/intellij/psi/PsiComment;
        //   100: invokeinterface com/intellij/psi/PsiComment.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   105: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   108: if_acmpne       167
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   117: athrow         
        //   118: getstatic       com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.EMPTY_TOKENIZER:Lcom/intellij/spellchecker/tokenizer/Tokenizer;
        //   121: dup            
        //   122: ifnonnull       166
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   131: athrow         
        //   132: new             Ljava/lang/IllegalStateException;
        //   135: dup            
        //   136: ldc             "@NotNull method %s.%s must not return null"
        //   138: ldc             2
        //   140: anewarray       Ljava/lang/Object;
        //   143: dup            
        //   144: ldc             0
        //   146: ldc             "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy"
        //   148: aastore        
        //   149: dup            
        //   150: ldc             1
        //   152: ldc             "getTokenizer"
        //   154: aastore        
        //   155: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   158: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   161: athrow         
        //   162: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   165: athrow         
        //   166: areturn        
        //   167: aload_1        
        //   168: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   171: astore_2       
        //   172: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.ALL_STRINGS:Lcom/intellij/psi/tree/TokenSet;
        //   175: aload_2        
        //   176: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   179: ifeq            231
        //   182: getstatic       com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.STRING_LITERAL_TOKENIZER:Lcom/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer;
        //   185: dup            
        //   186: ifnonnull       230
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   195: athrow         
        //   196: new             Ljava/lang/IllegalStateException;
        //   199: dup            
        //   200: ldc             "@NotNull method %s.%s must not return null"
        //   202: ldc             2
        //   204: anewarray       Ljava/lang/Object;
        //   207: dup            
        //   208: ldc             0
        //   210: ldc             "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy"
        //   212: aastore        
        //   213: dup            
        //   214: ldc             1
        //   216: ldc             "getTokenizer"
        //   218: aastore        
        //   219: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   222: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   225: athrow         
        //   226: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   229: athrow         
        //   230: areturn        
        //   231: aload_0        
        //   232: aload_1        
        //   233: invokespecial   com/intellij/spellchecker/tokenizer/SpellcheckingStrategy.getTokenizer:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/spellchecker/tokenizer/Tokenizer;
        //   236: dup            
        //   237: ifnonnull       274
        //   240: new             Ljava/lang/IllegalStateException;
        //   243: dup            
        //   244: ldc             "@NotNull method %s.%s must not return null"
        //   246: ldc             2
        //   248: anewarray       Ljava/lang/Object;
        //   251: dup            
        //   252: ldc             0
        //   254: ldc             "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy"
        //   256: aastore        
        //   257: dup            
        //   258: ldc             1
        //   260: ldc             "getTokenizer"
        //   262: aastore        
        //   263: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   266: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   269: athrow         
        //   270: invokestatic    com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   273: athrow         
        //   274: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      14     17     21     Ljava/lang/IllegalStateException;
        //  7      28     31     35     Ljava/lang/IllegalStateException;
        //  21     42     45     49     Ljava/lang/IllegalStateException;
        //  35     56     59     63     Ljava/lang/IllegalStateException;
        //  49     75     78     82     Ljava/lang/IllegalStateException;
        //  63     89     92     96     Ljava/lang/IllegalStateException;
        //  82     111    114    118    Ljava/lang/IllegalStateException;
        //  96     125    128    132    Ljava/lang/IllegalStateException;
        //  118    162    162    166    Ljava/lang/IllegalStateException;
        //  172    189    192    196    Ljava/lang/IllegalStateException;
        //  182    226    226    230    Ljava/lang/IllegalStateException;
        //  231    270    270    274    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
        STRING_LITERAL_TOKENIZER = new StringLiteralTokenizer();
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
    
    private static class StringLiteralTokenizer extends Tokenizer<PsiElement>
    {
        @Override
        public void tokenize(@NotNull final PsiElement psiElement, @NotNull final TokenConsumer tokenConsumer) {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer", "tokenize"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (tokenConsumer == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer", "tokenize"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final TextRange textRange = psiElement.getTextRange();
            char c = '\0';
            IElementType prefix_TYPE = null;
            Object o = null;
            Label_0136: {
                try {
                    c = '\"';
                    prefix_TYPE = OCHighlightingLexer.PREFIX_TYPE;
                    if (OCElementUtil.getElementType(psiElement) == OCTokenTypes.STRING_LITERAL) {
                        o = OCHighlightingLexer.createStringLiteralLexer('\"', OCTokenTypes.STRING_LITERAL);
                        break Label_0136;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                o = OCHighlightingLexer.createRawStringLexer();
            }
            final OCPrefixStringLiteralLexer ocPrefixStringLiteralLexer = new OCPrefixStringLiteralLexer(c, prefix_TYPE, (LexerBase)o);
            final String text = psiElement.getText();
            ocPrefixStringLiteralLexer.start((CharSequence)text, 0, textRange.getEndOffset() - textRange.getStartOffset());
            final StringBuilder sb = new StringBuilder();
            int tokenStart = 0;
            while (ocPrefixStringLiteralLexer.getTokenType() != null) {
                final IElementType tokenType = ocPrefixStringLiteralLexer.getTokenType();
                final String substring = text.substring(ocPrefixStringLiteralLexer.getTokenStart(), ocPrefixStringLiteralLexer.getTokenEnd());
                Label_0287: {
                    try {
                        if (StringEscapesTokenTypes.STRING_LITERAL_ESCAPES.contains(tokenType)) {
                            a(psiElement, tokenConsumer, sb, tokenStart);
                            break Label_0287;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    Label_0278: {
                        Label_0267: {
                            try {
                                if (tokenType != OCTokenTypes.STRING_LITERAL) {
                                    break Label_0278;
                                }
                                if (sb.length() != 0) {
                                    break Label_0267;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                            tokenStart = ocPrefixStringLiteralLexer.getTokenStart();
                        }
                        sb.append(substring);
                        break Label_0287;
                    }
                    a(psiElement, tokenConsumer, sb, tokenStart);
                }
                ocPrefixStringLiteralLexer.advance();
            }
            a(psiElement, tokenConsumer, sb, tokenStart);
        }
        
        private static void a(@NotNull final PsiElement psiElement, @NotNull final TokenConsumer tokenConsumer, @NotNull final StringBuilder sb, final int n) {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer", "consumeCollectedText"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (tokenConsumer == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer", "consumeCollectedText"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (sb == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sb", "com/jetbrains/cidr/lang/editor/spellchecker/OCSpellCheckerStrategy$StringLiteralTokenizer", "consumeCollectedText"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            if (sb.length() != 0) {
                final String string = sb.toString();
                tokenConsumer.consumeToken(psiElement, string, false, n, TextRange.allOf(string), PlainTextSplitter.getInstance());
                sb.setLength(0);
            }
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
