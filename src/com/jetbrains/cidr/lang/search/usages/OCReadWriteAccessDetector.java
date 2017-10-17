// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;

public class OCReadWriteAccessDetector extends ReadWriteAccessDetector
{
    public boolean isReadWriteAccessible(@NotNull final PsiElement p0) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isReadWriteAccessible"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    48: ifne            98
        //    51: aload_1        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //    55: ifne            98
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_1        
        //    66: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    69: ifeq            106
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_1        
        //    80: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    85: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //    88: ifeq            106
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: iconst_1       
        //    99: goto            107
        //   102: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: iconst_0       
        //   107: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     72     75     79     Ljava/lang/IllegalArgumentException;
        //  65     91     94     98     Ljava/lang/IllegalArgumentException;
        //  79     102    102    106    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    public boolean isDeclarationWriteAccess(@NotNull final PsiElement p0) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isDeclarationWriteAccess"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    48: ifeq            102
        //    51: aload_1        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    55: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    60: ifnonnull       94
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_1        
        //    71: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    76: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    81: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //    84: ifeq            102
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: iconst_1       
        //    95: goto            103
        //    98: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: iconst_0       
        //   103: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     63     66     70     Ljava/lang/IllegalArgumentException;
        //  51     87     90     94     Ljava/lang/IllegalArgumentException;
        //  70     98     98     102    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    public ReadWriteAccessDetector.Access getReferenceAccess(@NotNull final PsiElement psiElement, @NotNull final PsiReference psiReference) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "referencedElement", "com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector", "getReferenceAccess"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector", "getReferenceAccess"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ReadWriteAccessDetector.Access expressionAccess;
        try {
            expressionAccess = this.getExpressionAccess(psiReference.getElement());
            if (expressionAccess == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector", "getReferenceAccess"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return expressionAccess;
    }
    
    @NotNull
    public ReadWriteAccessDetector.Access getExpressionAccess(@NotNull final PsiElement p0) {
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
        //    18: ldc             "expression"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getExpressionAccess"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    48: ifne            129
        //    51: aload_1        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    55: ifne            129
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_1        
        //    66: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    69: ifne            129
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_1        
        //    80: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArraySelectionExpression;
        //    83: ifne            129
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: aload_1        
        //    94: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //    97: ifeq            365
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_1        
        //   108: checkcast       Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //   111: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   116: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   119: if_acmpne       365
        //   122: goto            129
        //   125: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   128: athrow         
        //   129: aload_1        
        //   130: iconst_2       
        //   131: anewarray       Ljava/lang/Class;
        //   134: dup            
        //   135: iconst_0       
        //   136: ldc             Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;.class
        //   138: aastore        
        //   139: dup            
        //   140: iconst_1       
        //   141: ldc             Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;.class
        //   143: aastore        
        //   144: invokestatic    com/intellij/psi/util/PsiTreeUtil.skipParentsOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   147: astore_2       
        //   148: aconst_null    
        //   149: astore_3       
        //   150: aload_2        
        //   151: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   154: ifeq            255
        //   157: aload_2        
        //   158: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   161: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   166: aload_1        
        //   167: iconst_0       
        //   168: invokestatic    com/intellij/psi/util/PsiTreeUtil.isAncestor:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Z
        //   171: ifeq            255
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: aload_2        
        //   182: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   185: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   190: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EQ:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   193: if_acmpne       213
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Write:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   206: goto            216
        //   209: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.ReadWrite:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   216: dup            
        //   217: ifnonnull       254
        //   220: new             Ljava/lang/IllegalStateException;
        //   223: dup            
        //   224: ldc             "@NotNull method %s.%s must not return null"
        //   226: ldc             2
        //   228: anewarray       Ljava/lang/Object;
        //   231: dup            
        //   232: ldc             0
        //   234: ldc             "com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector"
        //   236: aastore        
        //   237: dup            
        //   238: ldc             1
        //   240: ldc             "getExpressionAccess"
        //   242: aastore        
        //   243: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   246: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   249: athrow         
        //   250: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: areturn        
        //   255: aload_2        
        //   256: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPrefixExpression;
        //   259: ifeq            275
        //   262: aload_2        
        //   263: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPrefixExpression;
        //   266: invokeinterface com/jetbrains/cidr/lang/psi/OCPrefixExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   271: astore_3       
        //   272: goto            292
        //   275: aload_2        
        //   276: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPostfixExpression;
        //   279: ifeq            292
        //   282: aload_2        
        //   283: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPostfixExpression;
        //   286: invokeinterface com/jetbrains/cidr/lang/psi/OCPostfixExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   291: astore_3       
        //   292: aload_3        
        //   293: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUSPLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   296: if_acmpeq       313
        //   299: aload_3        
        //   300: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUSMINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   303: if_acmpne       323
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.ReadWrite:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   316: goto            326
        //   319: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Read:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   326: dup            
        //   327: ifnonnull       364
        //   330: new             Ljava/lang/IllegalStateException;
        //   333: dup            
        //   334: ldc             "@NotNull method %s.%s must not return null"
        //   336: ldc             2
        //   338: anewarray       Ljava/lang/Object;
        //   341: dup            
        //   342: ldc             0
        //   344: ldc             "com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector"
        //   346: aastore        
        //   347: dup            
        //   348: ldc             1
        //   350: ldc             "getExpressionAccess"
        //   352: aastore        
        //   353: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   356: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   359: athrow         
        //   360: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   363: athrow         
        //   364: areturn        
        //   365: aload_1        
        //   366: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   369: ifeq            448
        //   372: aload_1        
        //   373: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   376: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   381: ldc             ":"
        //   383: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   386: ifeq            406
        //   389: goto            396
        //   392: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   395: athrow         
        //   396: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Write:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   399: goto            409
        //   402: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   405: athrow         
        //   406: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Read:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   409: dup            
        //   410: ifnonnull       447
        //   413: new             Ljava/lang/IllegalStateException;
        //   416: dup            
        //   417: ldc             "@NotNull method %s.%s must not return null"
        //   419: ldc             2
        //   421: anewarray       Ljava/lang/Object;
        //   424: dup            
        //   425: ldc             0
        //   427: ldc             "com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector"
        //   429: aastore        
        //   430: dup            
        //   431: ldc             1
        //   433: ldc             "getExpressionAccess"
        //   435: aastore        
        //   436: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   439: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   442: athrow         
        //   443: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   446: athrow         
        //   447: areturn        
        //   448: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Read:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   451: dup            
        //   452: ifnonnull       489
        //   455: new             Ljava/lang/IllegalStateException;
        //   458: dup            
        //   459: ldc             "@NotNull method %s.%s must not return null"
        //   461: ldc             2
        //   463: anewarray       Ljava/lang/Object;
        //   466: dup            
        //   467: ldc             0
        //   469: ldc             "com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector"
        //   471: aastore        
        //   472: dup            
        //   473: ldc             1
        //   475: ldc             "getExpressionAccess"
        //   477: aastore        
        //   478: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   481: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   484: athrow         
        //   485: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   488: athrow         
        //   489: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     72     75     79     Ljava/lang/IllegalArgumentException;
        //  65     86     89     93     Ljava/lang/IllegalArgumentException;
        //  79     100    103    107    Ljava/lang/IllegalArgumentException;
        //  93     122    125    129    Ljava/lang/IllegalArgumentException;
        //  150    174    177    181    Ljava/lang/IllegalArgumentException;
        //  157    196    199    203    Ljava/lang/IllegalArgumentException;
        //  181    209    209    213    Ljava/lang/IllegalArgumentException;
        //  216    250    250    254    Ljava/lang/IllegalArgumentException;
        //  292    306    309    313    Ljava/lang/IllegalArgumentException;
        //  299    319    319    323    Ljava/lang/IllegalArgumentException;
        //  326    360    360    364    Ljava/lang/IllegalArgumentException;
        //  365    389    392    396    Ljava/lang/IllegalArgumentException;
        //  372    402    402    406    Ljava/lang/IllegalArgumentException;
        //  409    443    443    447    Ljava/lang/IllegalArgumentException;
        //  448    485    485    489    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    public boolean canBeConstReference(final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //     6: astore_3       
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    11: ifeq            23
        //    14: aload_3        
        //    15: astore_1       
        //    16: aload_1        
        //    17: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    22: astore_3       
        //    23: aload_3        
        //    24: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    27: ifeq            133
        //    30: aload_3        
        //    31: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    34: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    37: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    42: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //    45: ifeq            116
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_3        
        //    56: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    59: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    64: astore          4
        //    66: aload           4
        //    68: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    71: ifeq            116
        //    74: aload           4
        //    76: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    79: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isConst:()Z
        //    82: ifne            116
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload           4
        //    94: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    97: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.resolveIsStatic:()Z
        //   100: ifne            116
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: iconst_0       
        //   111: ireturn        
        //   112: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload_0        
        //   117: aload_3        
        //   118: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.getExpressionAccess:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   121: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Read:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   124: if_acmpeq       133
        //   127: iconst_0       
        //   128: ireturn        
        //   129: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_1        
        //   134: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   137: ifeq            184
        //   140: aload_1        
        //   141: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   144: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpectedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   147: astore          4
        //   149: aload           4
        //   151: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   154: ifeq            184
        //   157: aload           4
        //   159: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   162: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   165: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   168: ifne            184
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: iconst_0       
        //   179: ireturn        
        //   180: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: iload_2        
        //   185: ifeq            273
        //   188: aload_3        
        //   189: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   192: ifeq            273
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: aload_3        
        //   203: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   206: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getSourceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   211: aload_1        
        //   212: if_acmpne       273
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload_3        
        //   223: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   226: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   231: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   236: astore          4
        //   238: aload           4
        //   240: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   243: ifeq            273
        //   246: aload           4
        //   248: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   251: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   254: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   257: ifne            273
        //   260: goto            267
        //   263: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   266: athrow         
        //   267: iconst_0       
        //   268: ireturn        
        //   269: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   272: athrow         
        //   273: iload_2        
        //   274: ifeq            337
        //   277: aload_3        
        //   278: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   281: ifeq            337
        //   284: goto            291
        //   287: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   290: athrow         
        //   291: aload_3        
        //   292: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   295: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   300: astore          4
        //   302: aload           4
        //   304: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   307: ifeq            337
        //   310: aload           4
        //   312: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   315: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   318: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   321: ifne            337
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: iconst_0       
        //   332: ireturn        
        //   333: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   336: athrow         
        //   337: aload_3        
        //   338: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //   341: ifeq            390
        //   344: aload_3        
        //   345: checkcast       Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;
        //   348: invokeinterface com/jetbrains/cidr/lang/psi/OCUnaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   353: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   356: if_acmpne       390
        //   359: goto            366
        //   362: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   365: athrow         
        //   366: aload_0        
        //   367: aload_3        
        //   368: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.getExpressionAccess:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   371: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Read:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   374: if_acmpeq       390
        //   377: goto            384
        //   380: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   383: athrow         
        //   384: iconst_0       
        //   385: ireturn        
        //   386: invokestatic    com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   389: athrow         
        //   390: iconst_1       
        //   391: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  23     48     51     55     Ljava/lang/IllegalArgumentException;
        //  66     85     88     92     Ljava/lang/IllegalArgumentException;
        //  74     103    106    110    Ljava/lang/IllegalArgumentException;
        //  92     112    112    116    Ljava/lang/IllegalArgumentException;
        //  116    129    129    133    Ljava/lang/IllegalArgumentException;
        //  149    171    174    178    Ljava/lang/IllegalArgumentException;
        //  157    180    180    184    Ljava/lang/IllegalArgumentException;
        //  184    195    198    202    Ljava/lang/IllegalArgumentException;
        //  188    215    218    222    Ljava/lang/IllegalArgumentException;
        //  238    260    263    267    Ljava/lang/IllegalArgumentException;
        //  246    269    269    273    Ljava/lang/IllegalArgumentException;
        //  273    284    287    291    Ljava/lang/IllegalArgumentException;
        //  302    324    327    331    Ljava/lang/IllegalArgumentException;
        //  310    333    333    337    Ljava/lang/IllegalArgumentException;
        //  337    359    362    366    Ljava/lang/IllegalArgumentException;
        //  344    377    380    384    Ljava/lang/IllegalArgumentException;
        //  366    386    386    390    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
