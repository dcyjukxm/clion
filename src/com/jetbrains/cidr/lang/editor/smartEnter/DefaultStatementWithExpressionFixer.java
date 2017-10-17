// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class DefaultStatementWithExpressionFixer extends OCFixer
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
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "apply"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "apply"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //   112: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "apply"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_3        
        //   133: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //   136: ifeq            467
        //   139: aload_3        
        //   140: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDoWhileStatement;
        //   143: ifne            467
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   152: athrow         
        //   153: aload_3        
        //   154: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //   157: ifne            467
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   166: athrow         
        //   167: aload_3        
        //   168: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //   171: ifne            467
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   180: athrow         
        //   181: aload_3        
        //   182: aload_3        
        //   183: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //   186: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getKeywordType:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   191: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.hasMacroBasedStatement:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/parser/OCElementType;)Z
        //   194: ifne            467
        //   197: goto            204
        //   200: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   203: athrow         
        //   204: aload_1        
        //   205: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   210: astore          4
        //   212: aload_3        
        //   213: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //   216: astore          5
        //   218: aload           5
        //   220: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getRParenth:()Lcom/intellij/lang/ASTNode;
        //   225: astore          6
        //   227: aload           5
        //   229: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getLParenth:()Lcom/intellij/lang/ASTNode;
        //   234: astore          7
        //   236: aload           5
        //   238: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getExpression:()Lcom/intellij/psi/PsiElement;
        //   243: astore          8
        //   245: aload           7
        //   247: ifnonnull       456
        //   250: aload           4
        //   252: aload           4
        //   254: aload           5
        //   256: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   259: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   262: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
        //   267: invokeinterface com/intellij/openapi/editor/Document.getLineEndOffset:(I)I
        //   272: istore          9
        //   274: aload           5
        //   276: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getBody:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   281: astore          10
        //   283: aload           10
        //   285: ifnull          303
        //   288: iload           9
        //   290: aload           10
        //   292: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   295: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   298: invokestatic    java/lang/Math.min:(II)I
        //   301: istore          9
        //   303: iload           9
        //   305: aload           5
        //   307: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   310: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   313: invokestatic    java/lang/Math.min:(II)I
        //   316: istore          9
        //   318: ldc             ""
        //   320: astore          11
        //   322: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS_WITH_DOGS:Lcom/intellij/psi/tree/TokenSet;
        //   325: aload           5
        //   327: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getKeywordType:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   332: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   335: ifeq            363
        //   338: aload_3        
        //   339: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   344: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.OBJC_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   347: invokeinterface com/intellij/lang/ASTNode.findChildByType:(Lcom/intellij/psi/tree/IElementType;)Lcom/intellij/lang/ASTNode;
        //   352: astore          12
        //   354: aload           12
        //   356: ifnull          363
        //   359: ldc             "@"
        //   361: astore          11
        //   363: new             Ljava/lang/StringBuilder;
        //   366: dup            
        //   367: invokespecial   java/lang/StringBuilder.<init>:()V
        //   370: aload           11
        //   372: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   375: aload           5
        //   377: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getKeywordType:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   382: invokevirtual   com/jetbrains/cidr/lang/parser/OCElementType.getName:()Ljava/lang/String;
        //   385: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   388: ldc             " ("
        //   390: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   393: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   396: astore          11
        //   398: aload           4
        //   400: aload           5
        //   402: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   405: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   408: iload           9
        //   410: new             Ljava/lang/StringBuilder;
        //   413: dup            
        //   414: invokespecial   java/lang/StringBuilder.<init>:()V
        //   417: aload           11
        //   419: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   422: ldc             ")"
        //   424: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   427: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   430: invokeinterface com/intellij/openapi/editor/Document.replaceString:(IILjava/lang/CharSequence;)V
        //   435: aload_2        
        //   436: aload           5
        //   438: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   441: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   444: aload           11
        //   446: invokevirtual   java/lang/String.length:()I
        //   449: iadd           
        //   450: invokevirtual   com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.registerUnresolvedError:(I)V
        //   453: goto            467
        //   456: aload_1        
        //   457: aload_2        
        //   458: aload           7
        //   460: aload           8
        //   462: aload           6
        //   464: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionFixer.fixConditionIfNeed:(Lcom/intellij/openapi/editor/Editor;Lcom/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor;Lcom/intellij/lang/ASTNode;Lcom/intellij/psi/PsiElement;Lcom/intellij/lang/ASTNode;)V
        //   467: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     128    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  132    146    149    153    Lcom/intellij/util/IncorrectOperationException;
        //  139    160    163    167    Lcom/intellij/util/IncorrectOperationException;
        //  153    174    177    181    Lcom/intellij/util/IncorrectOperationException;
        //  167    197    200    204    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0153:
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
