// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCElement;

public class OCFunctionUsage extends OCUsageInfo<OCElement>
{
    public OCFunctionUsage(@NotNull final PsiReference psiReference) {
        if (psiReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage", "<init>"));
        }
        super(psiReference);
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo p0, @NotNull final OCElement p1, @NotNull final Project p2) {
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
        //    18: ldc             "changeInfo"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processUsage"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processUsage"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "project"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processUsage"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_2        
        //   133: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   136: ifne            153
        //   139: aload_2        
        //   140: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;
        //   143: ifeq            159
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_2        
        //   154: astore          4
        //   156: goto            218
        //   159: aload_2        
        //   160: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   165: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;
        //   168: ifeq            185
        //   171: aload_2        
        //   172: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   177: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   180: astore          4
        //   182: goto            218
        //   185: aload_2        
        //   186: iconst_4       
        //   187: anewarray       Ljava/lang/Class;
        //   190: dup            
        //   191: iconst_0       
        //   192: ldc             Lcom/jetbrains/cidr/lang/psi/OCUnaryExpression;.class
        //   194: aastore        
        //   195: dup            
        //   196: iconst_1       
        //   197: ldc             Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;.class
        //   199: aastore        
        //   200: dup            
        //   201: iconst_2       
        //   202: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallExpression;.class
        //   204: aastore        
        //   205: dup            
        //   206: iconst_3       
        //   207: ldc             Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;.class
        //   209: aastore        
        //   210: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   213: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   216: astore          4
        //   218: iconst_0       
        //   219: istore          5
        //   221: aload           4
        //   223: ifnull          354
        //   226: aload_1        
        //   227: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.isParameterSetOrOrderChanged:()Z
        //   230: ifeq            354
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   239: athrow         
        //   240: aload_1        
        //   241: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeBlock:()Z
        //   244: ifne            268
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: aload_1        
        //   255: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeMethod:()Z
        //   258: ifeq            342
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.$assertionsDisabled:Z
        //   271: ifne            308
        //   274: goto            281
        //   277: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: aload           4
        //   283: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   286: ifne            308
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: new             Ljava/lang/AssertionError;
        //   299: dup            
        //   300: invokespecial   java/lang/AssertionError.<init>:()V
        //   303: athrow         
        //   304: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   307: athrow         
        //   308: aconst_null    
        //   309: aload           4
        //   311: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   314: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getArguments:()Ljava/util/List;
        //   319: aload_1        
        //   320: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.generateCall:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Ljava/util/List;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;)Lcom/intellij/psi/PsiElement;
        //   323: astore          6
        //   325: aload           4
        //   327: aload           6
        //   329: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   332: astore          6
        //   334: aload           6
        //   336: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.fixAllSymbolsRecursively:(Lcom/intellij/psi/PsiElement;)V
        //   339: goto            455
        //   342: aload           4
        //   344: aload_1        
        //   345: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.changeConstructorOrMethodCall:(Lcom/jetbrains/cidr/lang/psi/OCElement;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;)V
        //   348: iconst_1       
        //   349: istore          5
        //   351: goto            455
        //   354: aload_1        
        //   355: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.isNameChanged:()Z
        //   358: ifeq            455
        //   361: aload_2        
        //   362: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   365: ifeq            396
        //   368: goto            375
        //   371: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   374: athrow         
        //   375: aload_2        
        //   376: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   379: aload_1        
        //   380: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewName:()Ljava/lang/String;
        //   383: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.setNameOfIdentifier:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
        //   388: pop            
        //   389: goto            452
        //   392: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   395: athrow         
        //   396: aload_2        
        //   397: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   400: ifeq            424
        //   403: aload_2        
        //   404: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   407: aload_1        
        //   408: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewName:()Ljava/lang/String;
        //   411: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.setName:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
        //   416: pop            
        //   417: goto            452
        //   420: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   423: athrow         
        //   424: aload_2        
        //   425: instanceof      Lcom/intellij/psi/PsiNamedElement;
        //   428: ifeq            452
        //   431: aload_2        
        //   432: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //   435: aload_1        
        //   436: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewName:()Ljava/lang/String;
        //   439: invokeinterface com/intellij/psi/PsiNamedElement.setName:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
        //   444: pop            
        //   445: goto            452
        //   448: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   451: athrow         
        //   452: iconst_1       
        //   453: istore          5
        //   455: aload           4
        //   457: ifnull          507
        //   460: iload           5
        //   462: ifeq            507
        //   465: goto            472
        //   468: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCFunctionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   471: athrow         
        //   472: aload           4
        //   474: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getRangeWithMacros:()Lcom/intellij/openapi/util/TextRange;
        //   479: astore          6
        //   481: aload_3        
        //   482: invokestatic    com/intellij/psi/codeStyle/CodeStyleManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleManager;
        //   485: aload           4
        //   487: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   492: aload           6
        //   494: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   497: aload           6
        //   499: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   502: iconst_1       
        //   503: invokevirtual   com/intellij/psi/codeStyle/CodeStyleManager.reformatRange:(Lcom/intellij/psi/PsiElement;IIZ)Lcom/intellij/psi/PsiElement;
        //   506: pop            
        //   507: iconst_1       
        //   508: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    146    149    153    Ljava/lang/IllegalArgumentException;
        //  221    233    236    240    Ljava/lang/IllegalArgumentException;
        //  226    247    250    254    Ljava/lang/IllegalArgumentException;
        //  240    261    264    268    Ljava/lang/IllegalArgumentException;
        //  254    274    277    281    Ljava/lang/IllegalArgumentException;
        //  268    289    292    296    Ljava/lang/IllegalArgumentException;
        //  281    304    304    308    Ljava/lang/IllegalArgumentException;
        //  354    368    371    375    Ljava/lang/IllegalArgumentException;
        //  361    392    392    396    Ljava/lang/IllegalArgumentException;
        //  396    420    420    424    Ljava/lang/IllegalArgumentException;
        //  424    445    448    452    Ljava/lang/IllegalArgumentException;
        //  455    465    468    472    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0240:
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
                if (!OCFunctionUsage.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
