// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class MissingIfBranchesFixer extends OCFixer
{
    @Override
    public void apply(@NotNull final Editor p0, @NotNull final OCSmartEnterProcessor p1, @NotNull final PsiElement p2) throws IncorrectOperationException {
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
        //    18: ldc             "editor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "apply"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "processor"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "apply"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "psiElement"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "apply"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_3        
        //   133: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   136: ifne            144
        //   139: return         
        //   140: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   143: athrow         
        //   144: aload_3        
        //   145: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   148: astore          4
        //   150: aload_1        
        //   151: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   156: astore          5
        //   158: aload           4
        //   160: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   165: astore          6
        //   167: aload           4
        //   169: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseKeyword:()Lcom/intellij/lang/ASTNode;
        //   174: astore          7
        //   176: aload           7
        //   178: ifnull          259
        //   181: aload           6
        //   183: ifnull          237
        //   186: goto            193
        //   189: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   192: athrow         
        //   193: aload           6
        //   195: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   198: ifne            259
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   207: athrow         
        //   208: aload           5
        //   210: aload           6
        //   212: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.startLine:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/psi/PsiElement;)I
        //   215: aload           5
        //   217: aload           7
        //   219: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   224: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.startLine:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/psi/PsiElement;)I
        //   227: if_icmple       259
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   236: athrow         
        //   237: aload           5
        //   239: aload           7
        //   241: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   244: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   247: ldc             "{}"
        //   249: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   254: return         
        //   255: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   258: athrow         
        //   259: aload           4
        //   261: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getThenBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   266: astore          8
        //   268: aload_1        
        //   269: aload           8
        //   271: iconst_0       
        //   272: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.fixBlockIfNeed:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;Z)Z
        //   275: ifeq            283
        //   278: return         
        //   279: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   282: athrow         
        //   283: iconst_0       
        //   284: istore          9
        //   286: aload           8
        //   288: ifnull          343
        //   291: aload           5
        //   293: aload           8
        //   295: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.startLine:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/psi/PsiElement;)I
        //   298: aload           5
        //   300: aload           4
        //   302: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.startLine:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/psi/PsiElement;)I
        //   305: if_icmpne       343
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   314: athrow         
        //   315: aload           4
        //   317: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //   322: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.hasEmptyStatement:(Lcom/intellij/psi/PsiElement;)Z
        //   325: ifne            340
        //   328: goto            335
        //   331: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   334: athrow         
        //   335: return         
        //   336: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   339: athrow         
        //   340: iconst_1       
        //   341: istore          9
        //   343: aload           4
        //   345: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getRParenth:()Lcom/intellij/lang/ASTNode;
        //   350: astore          10
        //   352: aload           10
        //   354: ifnonnull       362
        //   357: return         
        //   358: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   361: athrow         
        //   362: aload           6
        //   364: ifnonnull       379
        //   367: iload           9
        //   369: ifeq            391
        //   372: goto            379
        //   375: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   378: athrow         
        //   379: aload           8
        //   381: ifnonnull       415
        //   384: goto            391
        //   387: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   390: athrow         
        //   391: aload           5
        //   393: aload           10
        //   395: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   398: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   401: ldc             "{}"
        //   403: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   408: goto            466
        //   411: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   414: athrow         
        //   415: aload           8
        //   417: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   420: ifne            466
        //   423: aload           5
        //   425: aload           10
        //   427: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   430: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   433: ldc             "{"
        //   435: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   440: aload           5
        //   442: aload           8
        //   444: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   447: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   450: iconst_1       
        //   451: iadd           
        //   452: ldc             "}"
        //   454: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   459: goto            466
        //   462: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/MissingIfBranchesFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   465: athrow         
        //   466: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     128    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  132    140    140    144    Lcom/intellij/util/IncorrectOperationException;
        //  176    186    189    193    Lcom/intellij/util/IncorrectOperationException;
        //  181    201    204    208    Lcom/intellij/util/IncorrectOperationException;
        //  193    230    233    237    Lcom/intellij/util/IncorrectOperationException;
        //  208    255    255    259    Lcom/intellij/util/IncorrectOperationException;
        //  268    279    279    283    Lcom/intellij/util/IncorrectOperationException;
        //  286    308    311    315    Lcom/intellij/util/IncorrectOperationException;
        //  291    328    331    335    Lcom/intellij/util/IncorrectOperationException;
        //  315    336    336    340    Lcom/intellij/util/IncorrectOperationException;
        //  352    358    358    362    Lcom/intellij/util/IncorrectOperationException;
        //  362    372    375    379    Lcom/intellij/util/IncorrectOperationException;
        //  367    384    387    391    Lcom/intellij/util/IncorrectOperationException;
        //  379    411    411    415    Lcom/intellij/util/IncorrectOperationException;
        //  415    459    462    466    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0193:
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
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
