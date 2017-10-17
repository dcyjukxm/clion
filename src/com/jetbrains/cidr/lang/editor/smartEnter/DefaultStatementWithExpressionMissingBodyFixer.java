// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.intellij.lang.SmartEnterProcessorWithFixers;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class DefaultStatementWithExpressionMissingBodyFixer extends OCFixer
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
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "apply"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "apply"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //   112: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "apply"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_3        
        //   133: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //   136: ifeq            153
        //   139: aload_3        
        //   140: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDoWhileStatement;
        //   143: ifeq            158
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   152: athrow         
        //   153: return         
        //   154: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   157: athrow         
        //   158: aload_3        
        //   159: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //   162: astore          4
        //   164: aload_1        
        //   165: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   170: astore          5
        //   172: aload           4
        //   174: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getBody:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   179: astore          6
        //   181: aload_1        
        //   182: aload           6
        //   184: iconst_0       
        //   185: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.fixBlockIfNeed:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;Z)Z
        //   188: ifeq            196
        //   191: return         
        //   192: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   195: athrow         
        //   196: aload           6
        //   198: ifnull          280
        //   201: aload           5
        //   203: aload           6
        //   205: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.startLine:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/psi/PsiElement;)I
        //   208: aload           5
        //   210: aload           4
        //   212: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.startLine:(Lcom/intellij/openapi/editor/Document;Lcom/intellij/psi/PsiElement;)I
        //   215: if_icmpne       242
        //   218: goto            225
        //   221: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   224: athrow         
        //   225: aload           4
        //   227: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getExpression:()Lcom/intellij/psi/PsiElement;
        //   232: ifnonnull       275
        //   235: goto            242
        //   238: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   241: athrow         
        //   242: aload           6
        //   244: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   247: ifeq            280
        //   250: goto            257
        //   253: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   256: athrow         
        //   257: aload           6
        //   259: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   262: invokevirtual   com/intellij/openapi/util/TextRange.isEmpty:()Z
        //   265: ifne            280
        //   268: goto            275
        //   271: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   274: athrow         
        //   275: return         
        //   276: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   279: athrow         
        //   280: aload           4
        //   282: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getRParenth:()Lcom/intellij/lang/ASTNode;
        //   287: astore          7
        //   289: aload           7
        //   291: ifnull          318
        //   294: aload           5
        //   296: aload           7
        //   298: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.getRangeWithMacros:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/openapi/util/TextRange;
        //   301: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   304: ldc             "{}"
        //   306: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   311: goto            318
        //   314: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/DefaultStatementWithExpressionMissingBodyFixer.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   317: athrow         
        //   318: return         
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
        //  139    154    154    158    Lcom/intellij/util/IncorrectOperationException;
        //  181    192    192    196    Lcom/intellij/util/IncorrectOperationException;
        //  196    218    221    225    Lcom/intellij/util/IncorrectOperationException;
        //  201    235    238    242    Lcom/intellij/util/IncorrectOperationException;
        //  225    250    253    257    Lcom/intellij/util/IncorrectOperationException;
        //  242    268    271    275    Lcom/intellij/util/IncorrectOperationException;
        //  257    276    276    280    Lcom/intellij/util/IncorrectOperationException;
        //  289    311    314    318    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0225:
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
