// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.surround;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.lang.surroundWith.Surrounder;
import com.intellij.lang.surroundWith.SurroundDescriptor;

public class OCExpressionSurroundDescriptor implements SurroundDescriptor
{
    private static final Surrounder[] SURROUNDERS;
    
    @NotNull
    public PsiElement[] getElementsToSurround(final PsiFile p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiFile.getText:()Ljava/lang/String;
        //     6: astore          4
        //     8: aload           4
        //    10: iload_2        
        //    11: ldc             " \t\n"
        //    13: invokestatic    com/intellij/util/text/CharArrayUtil.shiftForward:(Ljava/lang/CharSequence;ILjava/lang/String;)I
        //    16: istore_2       
        //    17: aload           4
        //    19: iload_3        
        //    20: iconst_1       
        //    21: isub           
        //    22: ldc             " \t\n"
        //    24: invokestatic    com/intellij/util/text/CharArrayUtil.shiftBackward:(Ljava/lang/CharSequence;ILjava/lang/String;)I
        //    27: iconst_1       
        //    28: iadd           
        //    29: istore_3       
        //    30: aload_1        
        //    31: iload_2        
        //    32: iload_3        
        //    33: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.findExpressionAtRange:(Lcom/intellij/psi/PsiFile;II)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    36: astore          5
        //    38: aload           5
        //    40: ifnonnull       92
        //    43: getstatic       com/intellij/psi/PsiElement.EMPTY_ARRAY:[Lcom/intellij/psi/PsiElement;
        //    46: dup            
        //    47: ifnonnull       91
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCExpressionSurroundDescriptor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    56: athrow         
        //    57: new             Ljava/lang/IllegalStateException;
        //    60: dup            
        //    61: ldc             "@NotNull method %s.%s must not return null"
        //    63: ldc             2
        //    65: anewarray       Ljava/lang/Object;
        //    68: dup            
        //    69: ldc             0
        //    71: ldc             "com/jetbrains/cidr/lang/editor/surround/OCExpressionSurroundDescriptor"
        //    73: aastore        
        //    74: dup            
        //    75: ldc             1
        //    77: ldc             "getElementsToSurround"
        //    79: aastore        
        //    80: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    83: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    86: athrow         
        //    87: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCExpressionSurroundDescriptor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    90: athrow         
        //    91: areturn        
        //    92: aload           5
        //    94: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    99: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   102: ifeq            187
        //   105: aload           5
        //   107: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   112: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   117: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findLastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //   120: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   125: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   128: if_acmpne       187
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCExpressionSurroundDescriptor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   137: athrow         
        //   138: getstatic       com/intellij/psi/PsiElement.EMPTY_ARRAY:[Lcom/intellij/psi/PsiElement;
        //   141: dup            
        //   142: ifnonnull       186
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCExpressionSurroundDescriptor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   151: athrow         
        //   152: new             Ljava/lang/IllegalStateException;
        //   155: dup            
        //   156: ldc             "@NotNull method %s.%s must not return null"
        //   158: ldc             2
        //   160: anewarray       Ljava/lang/Object;
        //   163: dup            
        //   164: ldc             0
        //   166: ldc             "com/jetbrains/cidr/lang/editor/surround/OCExpressionSurroundDescriptor"
        //   168: aastore        
        //   169: dup            
        //   170: ldc             1
        //   172: ldc             "getElementsToSurround"
        //   174: aastore        
        //   175: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   178: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   181: athrow         
        //   182: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCExpressionSurroundDescriptor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   185: athrow         
        //   186: areturn        
        //   187: invokestatic    com/intellij/featureStatistics/FeatureUsageTracker.getInstance:()Lcom/intellij/featureStatistics/FeatureUsageTracker;
        //   190: ldc             "codeassists.surroundwith.expression"
        //   192: invokevirtual   com/intellij/featureStatistics/FeatureUsageTracker.triggerFeatureUsed:(Ljava/lang/String;)V
        //   195: iconst_1       
        //   196: anewarray       Lcom/intellij/psi/PsiElement;
        //   199: dup            
        //   200: iconst_0       
        //   201: aload           5
        //   203: aastore        
        //   204: dup            
        //   205: ifnonnull       242
        //   208: new             Ljava/lang/IllegalStateException;
        //   211: dup            
        //   212: ldc             "@NotNull method %s.%s must not return null"
        //   214: ldc             2
        //   216: anewarray       Ljava/lang/Object;
        //   219: dup            
        //   220: ldc             0
        //   222: ldc             "com/jetbrains/cidr/lang/editor/surround/OCExpressionSurroundDescriptor"
        //   224: aastore        
        //   225: dup            
        //   226: ldc             1
        //   228: ldc             "getElementsToSurround"
        //   230: aastore        
        //   231: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   234: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   237: athrow         
        //   238: invokestatic    com/jetbrains/cidr/lang/editor/surround/OCExpressionSurroundDescriptor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   241: athrow         
        //   242: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  38     50     53     57     Ljava/lang/IllegalStateException;
        //  43     87     87     91     Ljava/lang/IllegalStateException;
        //  92     131    134    138    Ljava/lang/IllegalStateException;
        //  105    145    148    152    Ljava/lang/IllegalStateException;
        //  138    182    182    186    Ljava/lang/IllegalStateException;
        //  187    238    238    242    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0138:
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
    
    @NotNull
    public Surrounder[] getSurrounders() {
        Surrounder[] surrounders;
        try {
            surrounders = OCExpressionSurroundDescriptor.SURROUNDERS;
            if (surrounders == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/surround/OCExpressionSurroundDescriptor", "getSurrounders"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return surrounders;
    }
    
    public boolean isExclusive() {
        return false;
    }
    
    static {
        SURROUNDERS = new Surrounder[] { new OCParenthesisSurrounder(), new OCNotExpressionSurrounder(), new OCaWithCastSurrounder(), new OCBlockExpressionSurrounder() };
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
