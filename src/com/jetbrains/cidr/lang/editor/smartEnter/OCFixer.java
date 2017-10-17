// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.psi.OCBlockOwner;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiRecursiveElementWalkingVisitor;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Document;
import java.util.regex.Pattern;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.lang.SmartEnterProcessorWithFixers;

public abstract class OCFixer extends SmartEnterProcessorWithFixers.Fixer<OCSmartEnterProcessor>
{
    public static final OCElementType RAW_PSEUDOTYPE;
    public static final Pattern OLD_END_PREFXIX;
    
    protected static int startLine(@NotNull final Document document, @NotNull final PsiElement psiElement) {
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "doc", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "startLine"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "startLine"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return document.getLineNumber(getRangeWithMacros(psiElement).getStartOffset());
    }
    
    @Nullable
    static PsiErrorElement getErrorElementOffset(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elt", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "getErrorElementOffset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiErrorElement[] array = { null };
        psiElement.accept((PsiElementVisitor)new PsiRecursiveElementWalkingVisitor() {
            public void visitErrorElement(final PsiErrorElement psiErrorElement) {
                array[0] = psiErrorElement;
                this.stopWalking();
            }
        });
        return array[0];
    }
    
    @Contract("null -> false")
    static boolean isCompleteBlockOwner(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;
        //     4: ifeq            55
        //     7: aload_0        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getOpeningBrace:()Lcom/intellij/psi/PsiElement;
        //    16: ifnull          53
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_0        
        //    27: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;
        //    30: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //    35: ifnull          53
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: iconst_1       
        //    46: goto            54
        //    49: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: iconst_0       
        //    54: ireturn        
        //    55: iconst_0       
        //    56: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  7      38     41     45     Ljava/lang/IllegalArgumentException;
        //  26     49     49     53     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    
    static boolean hasNonSpaceChildren(@NotNull final OCBlockOwner ocBlockOwner) {
        try {
            if (ocBlockOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "block", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "hasNonSpaceChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement closingBrace = ocBlockOwner.getClosingBrace();
        int n = 1;
        PsiElement psiElement = ocBlockOwner.getOpeningBrace();
        while (true) {
            Label_0092: {
                Label_0076: {
                    try {
                        if (psiElement == null) {
                            break;
                        }
                        final PsiElement psiElement2 = psiElement;
                        final PsiElement psiElement3 = closingBrace;
                        if (psiElement2 != psiElement3) {
                            break Label_0076;
                        }
                        break;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final PsiElement psiElement2 = psiElement;
                        final PsiElement psiElement3 = closingBrace;
                        if (psiElement2 == psiElement3) {
                            break;
                        }
                        if (n == 0) {
                            break Label_0092;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                n = 0;
                break Label_0092;
                try {
                    if (!(psiElement instanceof PsiWhiteSpace)) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            psiElement = psiElement.getNextSibling();
        }
        return false;
    }
    
    @Contract("null -> true")
    static boolean hasEmptyStatement(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          77
        //     4: aload_0        
        //     5: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //    10: ifne            85
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: aload_0        
        //    21: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //    26: ifnull          77
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    35: athrow         
        //    36: aload_0        
        //    37: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //    42: aload_0        
        //    43: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //    48: if_acmpne       85
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload_0        
        //    59: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //    64: instanceof      Lcom/intellij/psi/PsiErrorElement;
        //    67: ifeq            85
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: iconst_1       
        //    78: goto            86
        //    81: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: iconst_0       
        //    86: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     16     20     Ljava/lang/IllegalArgumentException;
        //  4      29     32     36     Ljava/lang/IllegalArgumentException;
        //  20     51     54     58     Ljava/lang/IllegalArgumentException;
        //  36     70     73     77     Ljava/lang/IllegalArgumentException;
        //  58     81     81     85     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    
    @Contract("_, null, _ -> false")
    static boolean fixBlockIfNeed(@NotNull final Editor p0, @Nullable final PsiElement p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "editor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "fixBlockIfNeed"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;
        //    48: ifeq            300
        //    51: aload_1        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;
        //    55: astore_3       
        //    56: aload_3        
        //    57: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getOpeningBrace:()Lcom/intellij/psi/PsiElement;
        //    62: astore          4
        //    64: aload_0        
        //    65: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    70: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    75: istore          5
        //    77: aload           4
        //    79: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.isRealLeafElement:(Lcom/intellij/psi/PsiElement;)Z
        //    82: ifeq            300
        //    85: aload_3        
        //    86: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.isGoodEmpty:(Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;)Z
        //    89: ifne            300
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: aload_0        
        //   100: iload_2        
        //   101: ifeq            120
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: iload           5
        //   113: goto            128
        //   116: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   119: athrow         
        //   120: aload           4
        //   122: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   125: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   128: aload_1        
        //   129: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   134: invokeinterface com/intellij/psi/PsiFile.getFileType:()Lcom/intellij/openapi/fileTypes/FileType;
        //   139: invokestatic    com/intellij/codeInsight/editorActions/enter/EnterAfterUnmatchedBraceHandler.isAfterUnmatchedLBrace:(Lcom/intellij/openapi/editor/Editor;ILcom/intellij/openapi/fileTypes/FileType;)Z
        //   142: ifeq            300
        //   145: aload_3        
        //   146: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   149: ifeq            214
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_3        
        //   160: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   163: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getStatements:()Ljava/util/List;
        //   168: astore          7
        //   170: aload           7
        //   172: invokeinterface java/util/List.size:()I
        //   177: ifle            202
        //   180: aload           7
        //   182: iconst_0       
        //   183: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   188: checkcast       Lcom/intellij/psi/PsiElement;
        //   191: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   194: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   197: istore          6
        //   199: goto            211
        //   202: aload_1        
        //   203: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   206: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   209: istore          6
        //   211: goto            224
        //   214: aload           4
        //   216: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   219: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   222: istore          6
        //   224: aload_0        
        //   225: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   230: astore          7
        //   232: iload           5
        //   234: iload           6
        //   236: if_icmple       287
        //   239: aload           7
        //   241: invokeinterface com/intellij/openapi/editor/Document.getText:()Ljava/lang/String;
        //   246: iload           5
        //   248: iconst_1       
        //   249: isub           
        //   250: ldc             " \t"
        //   252: invokestatic    com/intellij/util/text/CharArrayUtil.shiftBackward:(Ljava/lang/CharSequence;ILjava/lang/String;)I
        //   255: iload           6
        //   257: if_icmpgt       287
        //   260: goto            267
        //   263: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   266: athrow         
        //   267: aload_0        
        //   268: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   273: iload           6
        //   275: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: aload           7
        //   289: iload           6
        //   291: ldc             "}"
        //   293: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   298: iconst_1       
        //   299: ireturn        
        //   300: iconst_0       
        //   301: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  77     92     95     99     Ljava/lang/IllegalArgumentException;
        //  85     104    107    111    Ljava/lang/IllegalArgumentException;
        //  99     116    116    120    Ljava/lang/IllegalArgumentException;
        //  128    152    155    159    Ljava/lang/IllegalArgumentException;
        //  232    260    263    267    Ljava/lang/IllegalArgumentException;
        //  239    280    283    287    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0099:
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
    
    public static boolean isGoodEmpty(@NotNull final OCBlockOwner p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "block"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isGoodEmpty"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getOpeningBrace:()Lcom/intellij/psi/PsiElement;
        //    50: astore_1       
        //    51: aload_0        
        //    52: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //    57: astore_2       
        //    58: aload_1        
        //    59: ifnull          73
        //    62: aload_2        
        //    63: ifnonnull       79
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: iconst_0       
        //    74: ireturn        
        //    75: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_1        
        //    80: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //    85: astore_3       
        //    86: aload_3        
        //    87: aload_2        
        //    88: if_acmpeq       122
        //    91: aload_3        
        //    92: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    95: ifeq            130
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_3        
        //   106: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   111: aload_2        
        //   112: if_acmpne       130
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: iconst_1       
        //   123: goto            131
        //   126: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: iconst_0       
        //   131: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  58     66     69     73     Ljava/lang/IllegalArgumentException;
        //  62     75     75     79     Ljava/lang/IllegalArgumentException;
        //  86     98     101    105    Ljava/lang/IllegalArgumentException;
        //  91     115    118    122    Ljava/lang/IllegalArgumentException;
        //  105    126    126    130    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0105:
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
    
    @Nullable
    static PsiElement getFirstBodyElement(@NotNull final OCBlockOwner ocBlockOwner) {
        try {
            if (ocBlockOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "block", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "getFirstBodyElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement openingBrace = ocBlockOwner.getOpeningBrace();
        try {
            if (openingBrace == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement nextSibling = openingBrace.getNextSibling();
        try {
            if (nextSibling == ocBlockOwner.getClosingBrace()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return nextSibling;
    }
    
    @Contract("_, null -> false")
    public static boolean fixSemicolonAtTheEnd(@NotNull final Editor editor, @Nullable final PsiElement psiElement) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "fixSemicolonAtTheEnd"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int semicolonInsertionOffset = getSemicolonInsertionOffset(editor, psiElement);
        try {
            if (semicolonInsertionOffset == -1) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        editor.getDocument().insertString(semicolonInsertionOffset, (CharSequence)";");
        return true;
    }
    
    public static int getSemicolonInsertionOffset(@Nullable final Editor editor, @Nullable final PsiElement psiElement) {
        return getInsertionOffset(editor, psiElement, OCTokenTypes.SEMICOLON);
    }
    
    public static int getInsertionOffset(@Nullable final Editor p0, @Nullable final PsiElement p1, final IElementType p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       10
        //     4: iconst_m1      
        //     5: ireturn        
        //     6: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //     9: athrow         
        //    10: aload_1        
        //    11: instanceof      Lcom/intellij/psi/PsiErrorElement;
        //    14: ifeq            30
        //    17: aload_1        
        //    18: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    23: goto            39
        //    26: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: aload_1        
        //    31: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    36: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findLastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    39: astore_3       
        //    40: aload_3        
        //    41: ifnonnull       50
        //    44: iconst_m1      
        //    45: ireturn        
        //    46: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: aload_3        
        //    51: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getNextEssentialLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    54: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //    57: aload_2        
        //    58: if_acmpne       67
        //    61: iconst_m1      
        //    62: ireturn        
        //    63: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //    70: aload_3        
        //    71: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    76: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    79: ifne            89
        //    82: aload_3        
        //    83: instanceof      Lcom/intellij/psi/PsiErrorElement;
        //    86: ifeq            100
        //    89: aload_3        
        //    90: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.prevLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    93: astore_3       
        //    94: aload_3        
        //    95: ifnonnull       67
        //    98: iconst_m1      
        //    99: ireturn        
        //   100: aload_3        
        //   101: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   106: astore          4
        //   108: aload           4
        //   110: aload_2        
        //   111: if_acmpeq       141
        //   114: aload_0        
        //   115: ifnull          147
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: aload_0        
        //   126: aload_3        
        //   127: iconst_0       
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.needCorrectionLiteral:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/lang/ASTNode;Z)Z
        //   131: ifeq            147
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: iconst_m1      
        //   142: ireturn        
        //   143: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload_3        
        //   148: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   151: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   154: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      6      6      10     Ljava/lang/IllegalArgumentException;
        //  10     26     26     30     Ljava/lang/IllegalArgumentException;
        //  40     46     46     50     Ljava/lang/IllegalArgumentException;
        //  50     63     63     67     Ljava/lang/IllegalArgumentException;
        //  108    118    121    125    Ljava/lang/IllegalArgumentException;
        //  114    134    137    141    Ljava/lang/IllegalArgumentException;
        //  125    143    143    147    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0125:
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
    
    @Nullable
    public static ASTNode getPrevEssentialLeaf(final ASTNode astNode) {
        return getNeighborEssentialLeaf(astNode, false);
    }
    
    @Nullable
    public static ASTNode getNextEssentialLeaf(final ASTNode astNode) {
        return getNeighborEssentialLeaf(astNode, true);
    }
    
    public static boolean isLikeStructVarDeclaration(@NotNull final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "structLike"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isLikeStructVarDeclaration"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    48: ifeq            70
        //    51: aload_0        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    55: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getBaseClausesList:()Lcom/jetbrains/cidr/lang/psi/OCCppBaseClauseList;
        //    60: ifnull          76
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: iconst_0       
        //    71: ireturn        
        //    72: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_0        
        //    77: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    82: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getNextEssentialLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    85: astore_1       
        //    86: aload_1        
        //    87: ifnonnull       96
        //    90: iconst_0       
        //    91: ireturn        
        //    92: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_1        
        //    97: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   102: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   105: if_acmpne       150
        //   108: aload_1        
        //   109: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.endsTheLine:(Lcom/intellij/lang/ASTNode;)Z
        //   112: ifne            142
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload_1        
        //   123: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getNextEssentialLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   126: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //   129: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   132: if_acmpne       150
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: iconst_1       
        //   143: goto            151
        //   146: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: iconst_0       
        //   151: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     63     66     70     Ljava/lang/IllegalArgumentException;
        //  51     72     72     76     Ljava/lang/IllegalArgumentException;
        //  86     92     92     96     Ljava/lang/IllegalArgumentException;
        //  96     115    118    122    Ljava/lang/IllegalArgumentException;
        //  108    135    138    142    Ljava/lang/IllegalArgumentException;
        //  122    146    146    150    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0122:
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
    
    public static boolean endsTheLine(@NotNull final ASTNode p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "elem"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "endsTheLine"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.nextLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    48: astore_1       
        //    49: aload_1        
        //    50: ifnull          115
        //    53: aload_1        
        //    54: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    57: ifeq            91
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_1        
        //    68: bipush          10
        //    70: invokeinterface com/intellij/lang/ASTNode.textContains:(C)Z
        //    75: ifeq            107
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: iconst_1       
        //    86: ireturn        
        //    87: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: aload_1        
        //    92: instanceof      Lcom/intellij/psi/PsiComment;
        //    95: ifeq            105
        //    98: goto            107
        //   101: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: iconst_0       
        //   106: ireturn        
        //   107: aload_1        
        //   108: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.nextLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   111: astore_1       
        //   112: goto            49
        //   115: iconst_1       
        //   116: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  49     60     63     67     Ljava/lang/IllegalArgumentException;
        //  53     78     81     85     Ljava/lang/IllegalArgumentException;
        //  67     87     87     91     Ljava/lang/IllegalArgumentException;
        //  91     101    101    105    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
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
    
    @Nullable
    public static ASTNode getNeighborEssentialLeaf(final ASTNode astNode, final boolean b) {
        ASTNode astNode2 = null;
        Label_0019: {
            try {
                if (b) {
                    astNode2 = TreeUtil.nextLeaf(astNode);
                    break Label_0019;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            astNode2 = TreeUtil.prevLeaf(astNode);
        }
        ASTNode astNode3;
        for (ASTNode node = astNode2; node != null; node = astNode3) {
            final IElementType elementType = node.getElementType();
            final ASTNode parent = TreeUtil.findParent(node, OCElementTypes.MACRO_CALL);
            Label_0070: {
                if (parent == null) {
                    try {
                        if (OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(elementType)) {
                            break Label_0070;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return node;
                }
                node = parent;
                try {
                    if (b) {
                        astNode3 = TreeUtil.nextLeaf(node);
                        continue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            astNode3 = TreeUtil.prevLeaf(node);
        }
        return null;
    }
    
    @Contract("null->false")
    static boolean isRealLeafElement(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.$assertionsDisabled:Z
        //     3: ifne            43
        //     6: aload_0        
        //     7: ifnull          43
        //    10: goto            17
        //    13: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    16: athrow         
        //    17: aload_0        
        //    18: instanceof      Lcom/intellij/psi/impl/source/tree/LeafElement;
        //    21: ifne            43
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: new             Ljava/lang/AssertionError;
        //    34: dup            
        //    35: invokespecial   java/lang/AssertionError.<init>:()V
        //    38: athrow         
        //    39: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aload_0        
        //    44: ifnull          69
        //    47: aload_0        
        //    48: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    51: ifne            69
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: iconst_1       
        //    62: goto            70
        //    65: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: iconst_0       
        //    70: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      10     13     17     Ljava/lang/IllegalArgumentException;
        //  6      24     27     31     Ljava/lang/IllegalArgumentException;
        //  17     39     39     43     Ljava/lang/IllegalArgumentException;
        //  43     54     57     61     Ljava/lang/IllegalArgumentException;
        //  47     65     65     69     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    
    static boolean hasMacroBasedStatement(@NotNull PsiElement psi, @NotNull final OCElementType ocElementType) {
        try {
            if (psi == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "hasMacroBasedStatement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocElementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "keywordType", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "hasMacroBasedStatement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (OCTokenTypes.KEYWORDS_WITH_DOGS.contains((IElementType)ocElementType)) {
            final ASTNode childByType = psi.getNode().findChildByType((IElementType)OCElementTypes.OBJC_KEYWORD);
            if (childByType != null) {
                psi = childByType.getPsi();
            }
        }
        final ASTNode childByType2 = psi.getNode().findChildByType((IElementType)ocElementType);
        Label_0155: {
            try {
                if (childByType2 == null) {
                    break Label_0155;
                }
                final ASTNode astNode = childByType2;
                final boolean b = astNode instanceof OCMacroForeignLeafElement;
                if (b) {
                    break Label_0155;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final ASTNode astNode = childByType2;
                final boolean b = astNode instanceof OCMacroForeignLeafElement;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    @Contract("_, null, _ -> false")
    public static boolean needCorrectionLiteral(@NotNull final Editor p0, @Nullable final ASTNode p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "editor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "needCorrectionLiteral"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iconst_0       
        //    45: istore_3       
        //    46: aload_1        
        //    47: ifnull          860
        //    50: aload_1        
        //    51: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    54: ifne            860
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload_1        
        //    65: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    70: astore          4
        //    72: aload           4
        //    74: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    77: if_acmpeq       110
        //    80: aload           4
        //    82: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CHARACTER_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    85: if_acmpeq       110
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: aload           4
        //    97: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WRONG_RAW_STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   100: if_acmpne       860
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload           4
        //   112: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CHARACTER_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   115: if_acmpne       134
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: bipush          39
        //   127: goto            136
        //   130: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: bipush          34
        //   136: istore          5
        //   138: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RAW_STRING_LITERALS:Lcom/intellij/psi/tree/TokenSet;
        //   141: aload           4
        //   143: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   146: istore          6
        //   148: new             Lcom/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer;
        //   151: dup            
        //   152: iload           5
        //   154: getstatic       com/jetbrains/cidr/lang/lexer/OCHighlightingLexer.PREFIX_TYPE:Lcom/intellij/psi/tree/IElementType;
        //   157: iload           6
        //   159: ifne            177
        //   162: iload           5
        //   164: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   167: invokestatic    com/jetbrains/cidr/lang/lexer/OCHighlightingLexer.createStringLiteralLexer:(CLcom/intellij/psi/tree/IElementType;)Lcom/intellij/lexer/StringLiteralLexer;
        //   170: goto            190
        //   173: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: new             Lcom/jetbrains/cidr/lang/lexer/OCRawStringLexerBase;
        //   180: dup            
        //   181: getstatic       com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.RAW_PSEUDOTYPE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   184: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   187: invokespecial   com/jetbrains/cidr/lang/lexer/OCRawStringLexerBase.<init>:(Lcom/intellij/psi/tree/IElementType;Lcom/intellij/psi/tree/IElementType;)V
        //   190: invokespecial   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.<init>:(CLcom/intellij/psi/tree/IElementType;Lcom/intellij/lexer/LexerBase;)V
        //   193: astore          7
        //   195: aload_0        
        //   196: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   201: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   206: astore          8
        //   208: aload_1        
        //   209: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //   214: istore          9
        //   216: iconst_m1      
        //   217: istore          10
        //   219: aload           4
        //   221: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WRONG_RAW_STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   224: if_acmpne       351
        //   227: aload           8
        //   229: invokeinterface java/lang/CharSequence.length:()I
        //   234: istore          11
        //   236: aload           8
        //   238: bipush          10
        //   240: iload           9
        //   242: invokestatic    com/intellij/openapi/util/text/StringUtil.indexOf:(Ljava/lang/CharSequence;CI)I
        //   245: istore          12
        //   247: iload           12
        //   249: iflt            261
        //   252: iload           11
        //   254: iload           12
        //   256: invokestatic    java/lang/Math.min:(II)I
        //   259: istore          11
        //   261: getstatic       com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.OLD_END_PREFXIX:Ljava/util/regex/Pattern;
        //   264: aload           8
        //   266: iload           9
        //   268: iload           11
        //   270: invokeinterface java/lang/CharSequence.subSequence:(II)Ljava/lang/CharSequence;
        //   275: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //   278: astore          13
        //   280: aload           13
        //   282: invokevirtual   java/util/regex/Matcher.find:()Z
        //   285: ifeq            334
        //   288: iload           9
        //   290: aload           13
        //   292: iconst_0       
        //   293: invokevirtual   java/util/regex/Matcher.end:(I)I
        //   296: iadd           
        //   297: iload           11
        //   299: if_icmpgt       334
        //   302: goto            309
        //   305: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   308: athrow         
        //   309: iload           9
        //   311: aload           13
        //   313: iconst_0       
        //   314: invokevirtual   java/util/regex/Matcher.end:(I)I
        //   317: iadd           
        //   318: istore          11
        //   320: iload           9
        //   322: aload           13
        //   324: iconst_0       
        //   325: invokevirtual   java/util/regex/Matcher.start:(I)I
        //   328: iadd           
        //   329: istore          10
        //   331: goto            348
        //   334: iload           5
        //   336: aload           8
        //   338: iload           9
        //   340: iload           11
        //   342: iconst_1       
        //   343: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(CLjava/lang/CharSequence;IIZ)I
        //   346: istore          11
        //   348: goto            370
        //   351: iload           5
        //   353: aload           8
        //   355: iload           9
        //   357: aload_1        
        //   358: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   361: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   364: iconst_0       
        //   365: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(CLjava/lang/CharSequence;IIZ)I
        //   368: istore          11
        //   370: aload           7
        //   372: aload           8
        //   374: iload           9
        //   376: iload           11
        //   378: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.start:(Ljava/lang/CharSequence;II)V
        //   381: aconst_null    
        //   382: astore          12
        //   384: iconst_0       
        //   385: istore          13
        //   387: iconst_m1      
        //   388: istore          14
        //   390: iconst_m1      
        //   391: istore          15
        //   393: iconst_1       
        //   394: istore          16
        //   396: aload           7
        //   398: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   401: ifnull          588
        //   404: aload           7
        //   406: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   409: astore          17
        //   411: aload           17
        //   413: getstatic       com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.RAW_PSEUDOTYPE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   416: if_acmpne       526
        //   419: iload           13
        //   421: ifne            457
        //   424: goto            431
        //   427: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   430: athrow         
        //   431: aload           8
        //   433: aload           7
        //   435: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.getTokenEnd:()I
        //   438: iconst_1       
        //   439: isub           
        //   440: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   445: bipush          40
        //   447: if_icmpeq       494
        //   450: goto            457
        //   453: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   456: athrow         
        //   457: iload           13
        //   459: iconst_1       
        //   460: if_icmpne       554
        //   463: goto            470
        //   466: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   469: athrow         
        //   470: aload           8
        //   472: aload           7
        //   474: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.getTokenStart:()I
        //   477: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   482: bipush          41
        //   484: if_icmpne       554
        //   487: goto            494
        //   490: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   493: athrow         
        //   494: aload           8
        //   496: aload           7
        //   498: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.getTokenStart:()I
        //   501: aload           7
        //   503: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.getTokenEnd:()I
        //   506: iconst_1       
        //   507: isub           
        //   508: invokeinterface java/lang/CharSequence.subSequence:(II)Ljava/lang/CharSequence;
        //   513: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //   518: astore          12
        //   520: iinc            13, 1
        //   523: goto            554
        //   526: aload           17
        //   528: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   531: if_acmpne       554
        //   534: iload           15
        //   536: iconst_m1      
        //   537: if_icmpne       554
        //   540: goto            547
        //   543: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   546: athrow         
        //   547: aload           7
        //   549: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.getTokenStart:()I
        //   552: istore          15
        //   554: aload           7
        //   556: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.getTokenEnd:()I
        //   559: istore          14
        //   561: aload           17
        //   563: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   566: if_acmpeq       577
        //   569: iconst_1       
        //   570: goto            578
        //   573: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   576: athrow         
        //   577: iconst_0       
        //   578: istore          16
        //   580: aload           7
        //   582: invokevirtual   com/jetbrains/cidr/lang/lexer/OCPrefixStringLiteralLexer.advance:()V
        //   585: goto            396
        //   588: iload           15
        //   590: iconst_m1      
        //   591: if_icmpeq       860
        //   594: iload           14
        //   596: iconst_m1      
        //   597: if_icmpeq       860
        //   600: goto            607
        //   603: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   606: athrow         
        //   607: iload           15
        //   609: iload           14
        //   611: iconst_1       
        //   612: isub           
        //   613: if_icmpeq       658
        //   616: goto            623
        //   619: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   622: athrow         
        //   623: iload           16
        //   625: ifne            658
        //   628: goto            635
        //   631: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   634: athrow         
        //   635: aload           8
        //   637: iload           14
        //   639: iconst_1       
        //   640: isub           
        //   641: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   646: iload           5
        //   648: if_icmpeq       699
        //   651: goto            658
        //   654: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   657: athrow         
        //   658: iload_2        
        //   659: ifeq            694
        //   662: goto            669
        //   665: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   668: athrow         
        //   669: aload_0        
        //   670: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   675: iload           14
        //   677: iload           5
        //   679: invokestatic    java/lang/String.valueOf:(C)Ljava/lang/String;
        //   682: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   687: goto            694
        //   690: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   693: athrow         
        //   694: iinc            14, 1
        //   697: iconst_1       
        //   698: istore_3       
        //   699: iload           6
        //   701: ifeq            771
        //   704: aload           12
        //   706: ifnonnull       771
        //   709: goto            716
        //   712: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   715: athrow         
        //   716: ldc             ""
        //   718: astore          12
        //   720: iload_2        
        //   721: ifeq            748
        //   724: aload_0        
        //   725: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   730: iload           15
        //   732: iconst_1       
        //   733: iadd           
        //   734: ldc             "("
        //   736: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   741: goto            748
        //   744: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   747: athrow         
        //   748: iload           10
        //   750: iflt            763
        //   753: iinc            10, 1
        //   756: goto            763
        //   759: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   762: athrow         
        //   763: iinc            13, 1
        //   766: iinc            14, 1
        //   769: iconst_1       
        //   770: istore_3       
        //   771: iload           13
        //   773: iconst_1       
        //   774: if_icmpne       860
        //   777: new             Ljava/lang/StringBuilder;
        //   780: dup            
        //   781: invokespecial   java/lang/StringBuilder.<init>:()V
        //   784: ldc             ")"
        //   786: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   789: aload           12
        //   791: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   794: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   797: astore          17
        //   799: iload_2        
        //   800: ifeq            858
        //   803: iload           10
        //   805: iflt            841
        //   808: goto            815
        //   811: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   814: athrow         
        //   815: aload_0        
        //   816: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   821: iload           10
        //   823: iload           11
        //   825: iconst_1       
        //   826: isub           
        //   827: aload           17
        //   829: invokeinterface com/intellij/openapi/editor/Document.replaceString:(IILjava/lang/CharSequence;)V
        //   834: goto            858
        //   837: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   840: athrow         
        //   841: aload_0        
        //   842: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   847: iload           14
        //   849: iconst_1       
        //   850: isub           
        //   851: aload           17
        //   853: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   858: iconst_1       
        //   859: istore_3       
        //   860: iload_3        
        //   861: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  46     57     60     64     Ljava/lang/IllegalArgumentException;
        //  72     88     91     95     Ljava/lang/IllegalArgumentException;
        //  80     103    106    110    Ljava/lang/IllegalArgumentException;
        //  95     118    121    125    Ljava/lang/IllegalArgumentException;
        //  110    130    130    134    Ljava/lang/IllegalArgumentException;
        //  148    173    173    177    Ljava/lang/IllegalArgumentException;
        //  280    302    305    309    Ljava/lang/IllegalArgumentException;
        //  411    424    427    431    Ljava/lang/IllegalArgumentException;
        //  419    450    453    457    Ljava/lang/IllegalArgumentException;
        //  431    463    466    470    Ljava/lang/IllegalArgumentException;
        //  457    487    490    494    Ljava/lang/IllegalArgumentException;
        //  526    540    543    547    Ljava/lang/IllegalArgumentException;
        //  561    573    573    577    Ljava/lang/IllegalArgumentException;
        //  588    600    603    607    Ljava/lang/IllegalArgumentException;
        //  594    616    619    623    Ljava/lang/IllegalArgumentException;
        //  607    628    631    635    Ljava/lang/IllegalArgumentException;
        //  623    651    654    658    Ljava/lang/IllegalArgumentException;
        //  635    662    665    669    Ljava/lang/IllegalArgumentException;
        //  658    687    690    694    Ljava/lang/IllegalArgumentException;
        //  699    709    712    716    Ljava/lang/IllegalArgumentException;
        //  720    741    744    748    Ljava/lang/IllegalArgumentException;
        //  748    756    759    763    Ljava/lang/IllegalArgumentException;
        //  799    808    811    815    Ljava/lang/IllegalArgumentException;
        //  803    837    837    841    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0095:
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
    
    public static int getCompletionPointAfterLPAR(@NotNull final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "getCompletionPointAfterLPAR"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "getCompletionPointAfterLPAR"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        int n = -1;
        if (caretInVerticalRangeOfElement(editor, psiElement)) {
            n = getCompletionPoint(editor);
        }
        else {
            final ASTNode childByType = psiElement.getNode().findChildByType((IElementType)OCTokenTypes.LPAR);
            if (childByType != null) {
                editor.getCaretModel().moveToOffset(getRangeWithMacros(childByType).getEndOffset());
                n = getCompletionPoint(editor);
            }
        }
        return Math.min(n, getRangeWithMacros(psiElement).getEndOffset());
    }
    
    public static boolean caretInVerticalRangeOfElement(@NotNull final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "caretInVerticalRangeOfElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "caretInVerticalRangeOfElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Document document = editor.getDocument();
        final TextRange rangeWithMacros = getRangeWithMacros(psiElement);
        final int lineNumber = document.getLineNumber(rangeWithMacros.getStartOffset());
        final int lineNumber2 = document.getLineNumber(rangeWithMacros.getEndOffset());
        final int lineNumber3 = document.getLineNumber(editor.getCaretModel().getOffset());
        Label_0164: {
            try {
                if (lineNumber > lineNumber3) {
                    return false;
                }
                final int n = lineNumber3;
                final int n2 = lineNumber2;
                if (n <= n2) {
                    break Label_0164;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final int n = lineNumber3;
                final int n2 = lineNumber2;
                if (n <= n2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    public static int getCompletionPoint(@NotNull final Editor p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "editor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getCompletionPoint"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    50: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    55: istore_1       
        //    56: invokestatic    com/jetbrains/cidr/lang/lexer/OCLexerWithDirectives.createDefault:()Lcom/jetbrains/cidr/lang/lexer/OCLexerWithDirectives;
        //    59: astore_2       
        //    60: aload_0        
        //    61: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //    66: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //    71: astore_3       
        //    72: aload_3        
        //    73: iload_1        
        //    74: ldc             " \t"
        //    76: invokestatic    com/intellij/util/text/CharArrayUtil.shiftBackward:(Ljava/lang/CharSequence;ILjava/lang/String;)I
        //    79: istore_1       
        //    80: aload_2        
        //    81: aload_3        
        //    82: iload_1        
        //    83: aload_3        
        //    84: invokeinterface java/lang/CharSequence.length:()I
        //    89: invokevirtual   com/intellij/lexer/Lexer.start:(Ljava/lang/CharSequence;II)V
        //    92: aload_2        
        //    93: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    96: ifnull          169
        //    99: aload_2        
        //   100: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   103: astore          4
        //   105: aload_2        
        //   106: invokevirtual   com/intellij/lexer/Lexer.getTokenText:()Ljava/lang/String;
        //   109: ldc             "\n"
        //   111: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   114: ifne            169
        //   117: aload           4
        //   119: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   122: if_acmpeq       169
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   135: aload           4
        //   137: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   140: ifeq            157
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: goto            169
        //   153: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload_2        
        //   158: invokevirtual   com/intellij/lexer/Lexer.getTokenEnd:()I
        //   161: istore_1       
        //   162: aload_2        
        //   163: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   166: goto            92
        //   169: iload_1        
        //   170: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  105    125    128    132    Ljava/lang/IllegalArgumentException;
        //  117    143    146    150    Ljava/lang/IllegalArgumentException;
        //  132    153    153    157    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0132:
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
    
    private static int a(final char c, final CharSequence charSequence, final int n, final int n2, final boolean b) {
        final int index = StringUtil.indexOf(charSequence, c, n, n2);
        Label_0056: {
            if (index >= 0) {
                final int index2 = StringUtil.indexOf(charSequence, c, index + 1, n2);
                Label_0043: {
                    try {
                        if (index2 < 0) {
                            break Label_0056;
                        }
                        final boolean b2 = b;
                        if (b2) {
                            break Label_0043;
                        }
                        return n2;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final boolean b2 = b;
                        if (b2) {
                            return index2 + 1;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return n2;
            }
        }
        final int index3 = StringUtil.indexOf(charSequence, ';', n, n2);
        try {
            if (index3 >= 0) {
                return index3;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return n2;
    }
    
    static TextRange getRangeWithMacros(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "getRangeWithMacros"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return getRangeWithMacros(astNode.getPsi());
    }
    
    static TextRange getRangeWithMacros(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "getRangeWithMacros"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement instanceof OCElement) {
                return ((OCElement)psiElement).getRangeWithMacros();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement instanceof OCMacroForeignLeafElement) {
                return ((OCMacroForeignLeafElement)psiElement).getRealTextRange();
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return psiElement.getTextRange();
    }
    
    static void fixConditionIfNeed(@NotNull final Editor editor, @NotNull final OCSmartEnterProcessor ocSmartEnterProcessor, @NotNull final ASTNode astNode, @Nullable final PsiElement psiElement, @Nullable final ASTNode astNode2) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "fixConditionIfNeed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSmartEnterProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "fixConditionIfNeed"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lParenth", "com/jetbrains/cidr/lang/editor/smartEnter/OCFixer", "fixConditionIfNeed"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final int endOffset = getRangeWithMacros(astNode).getEndOffset();
        Label_0160: {
            try {
                if (astNode2 == null) {
                    break Label_0160;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = hasEmptyStatement(psiElement2);
                if (b) {
                    break Label_0160;
                }
                return;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = hasEmptyStatement(psiElement2);
                if (b) {
                    ocSmartEnterProcessor.registerUnresolvedError(getRangeWithMacros(astNode2).getStartOffset());
                }
                return;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        editor.getCaretModel().moveToOffset(endOffset);
        final int completionPoint = getCompletionPoint(editor);
        try {
            if (completionPoint == endOffset) {
                ocSmartEnterProcessor.registerUnresolvedError(endOffset);
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        editor.getDocument().insertString(completionPoint, (CharSequence)")");
    }
    
    @Nullable
    static PsiErrorElement getNextError(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //     4: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //     7: istore_1       
        //     8: aload_0        
        //     9: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    14: astore_2       
        //    15: aload_2        
        //    16: ifnull          51
        //    19: aload_2        
        //    20: instanceof      Lcom/intellij/psi/PsiFile;
        //    23: ifne            51
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    32: athrow         
        //    33: aload_2        
        //    34: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //    37: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //    40: iload_1        
        //    41: if_icmpeq       57
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aconst_null    
        //    52: areturn        
        //    53: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: aload_2        
        //    58: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //    63: astore_3       
        //    64: aload_3        
        //    65: instanceof      Lcom/intellij/psi/PsiErrorElement;
        //    68: ifeq            80
        //    71: aload_3        
        //    72: checkcast       Lcom/intellij/psi/PsiErrorElement;
        //    75: areturn        
        //    76: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aload_2        
        //    81: astore_0       
        //    82: goto            8
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  15     26     29     33     Ljava/lang/IllegalArgumentException;
        //  19     44     47     51     Ljava/lang/IllegalArgumentException;
        //  33     53     53     57     Ljava/lang/IllegalArgumentException;
        //  64     76     76     80     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
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
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCFixer.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        RAW_PSEUDOTYPE = new OCElementType("raw");
        OLD_END_PREFXIX = Pattern.compile("\\)\\S*\"");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
