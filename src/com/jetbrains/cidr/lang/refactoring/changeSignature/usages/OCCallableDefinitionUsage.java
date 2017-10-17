// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCCallable;

public class OCCallableDefinitionUsage<T extends OCCallable> extends OCUsageInfo<T>
{
    protected boolean myInherited;
    
    public OCCallableDefinitionUsage(@NotNull final T t, final boolean myInherited) {
        if (t == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage", "<init>"));
        }
        super((PsiElement)t);
        this.myInherited = myInherited;
    }
    
    @Override
    public int getUsageRank() {
        return 0;
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo p0, @NotNull final T p1, @NotNull final Project p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processUsage"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processUsage"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   112: ldc             "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processUsage"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_1        
        //   133: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.isParameterSetOrOrderChanged:()Z
        //   136: ifne            145
        //   139: iconst_0       
        //   140: ireturn        
        //   141: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aconst_null    
        //   146: astore          4
        //   148: aload_1        
        //   149: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewContainerClass:()Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   152: astore          5
        //   154: aload_1        
        //   155: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeBlock:()Z
        //   158: ifeq            175
        //   161: aload_2        
        //   162: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   165: ifeq            188
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload_1        
        //   176: aload_2        
        //   177: iconst_1       
        //   178: iconst_1       
        //   179: aload_0        
        //   180: getfield        com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.myInherited:Z
        //   183: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureUsageProcessor.generateMethodDefinition:(Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;Lcom/jetbrains/cidr/lang/psi/OCCallable;ZZZ)Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   186: astore          4
        //   188: aload_1        
        //   189: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getOldMethodDescriptor:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   192: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getCallableKind:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //   195: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind.METHOD:Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCCallableKind;
        //   198: if_acmpeq       244
        //   201: aload           5
        //   203: ifnull          244
        //   206: goto            213
        //   209: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: aload           4
        //   215: ifnull          244
        //   218: goto            225
        //   221: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: aload_2        
        //   226: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   229: aload           5
        //   231: aload           4
        //   233: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   236: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   239: astore          4
        //   241: goto            442
        //   244: aload_1        
        //   245: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.willBeBlock:()Z
        //   248: ifne            309
        //   251: aload_2        
        //   252: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   255: ifeq            309
        //   258: goto            265
        //   261: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: aload           4
        //   267: ifnull          309
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: aload_2        
        //   278: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   283: aload           4
        //   285: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   288: pop            
        //   289: aload_2        
        //   290: aload_1        
        //   291: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getNewName:()Ljava/lang/String;
        //   294: aload_2        
        //   295: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   298: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   301: pop            
        //   302: goto            442
        //   305: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   308: athrow         
        //   309: aload           4
        //   311: ifnull          438
        //   314: aload_2        
        //   315: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   318: ifeq            424
        //   321: goto            328
        //   324: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   327: athrow         
        //   328: aload           4
        //   330: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   333: ifeq            424
        //   336: goto            343
        //   339: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   342: athrow         
        //   343: aload_2        
        //   344: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   347: astore          6
        //   349: aload           4
        //   351: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   354: astore          7
        //   356: aload           6
        //   358: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   363: aload           7
        //   365: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   370: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   373: pop            
        //   374: aload           6
        //   376: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   381: aload           7
        //   383: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   388: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   391: pop            
        //   392: aload           6
        //   394: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   399: aload           7
        //   401: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   406: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   409: pop            
        //   410: aload           6
        //   412: aload           7
        //   414: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.restoreFunction:(Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;)V
        //   417: aload           6
        //   419: astore          4
        //   421: goto            442
        //   424: aload_2        
        //   425: aload           4
        //   427: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   430: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   433: astore          4
        //   435: goto            442
        //   438: aload_2        
        //   439: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   442: aload           4
        //   444: ifnull          502
        //   447: aload           4
        //   449: new             Lcom/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage$1;
        //   452: dup            
        //   453: aload_0        
        //   454: invokespecial   com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage$1.<init>:(Lcom/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage;)V
        //   457: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   462: aload_1        
        //   463: aload           4
        //   465: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.addNewMethod:(Lcom/jetbrains/cidr/lang/psi/OCCallable;)V
        //   468: aload_2        
        //   469: aload_1        
        //   470: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.getOldMethodDescriptor:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //   473: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getMethod:()Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   476: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   479: ifeq            502
        //   482: goto            489
        //   485: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   488: athrow         
        //   489: aload_1        
        //   490: aload           4
        //   492: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo.setNewMethod:(Lcom/jetbrains/cidr/lang/psi/OCCallable;)V
        //   495: goto            502
        //   498: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCCallableDefinitionUsage.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   501: athrow         
        //   502: iconst_1       
        //   503: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeInfo;TT;Lcom/intellij/openapi/project/Project;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    141    141    145    Ljava/lang/IllegalArgumentException;
        //  154    168    171    175    Ljava/lang/IllegalArgumentException;
        //  188    206    209    213    Ljava/lang/IllegalArgumentException;
        //  201    218    221    225    Ljava/lang/IllegalArgumentException;
        //  244    258    261    265    Ljava/lang/IllegalArgumentException;
        //  251    270    273    277    Ljava/lang/IllegalArgumentException;
        //  265    305    305    309    Ljava/lang/IllegalArgumentException;
        //  309    321    324    328    Ljava/lang/IllegalArgumentException;
        //  314    336    339    343    Ljava/lang/IllegalArgumentException;
        //  442    482    485    489    Ljava/lang/IllegalArgumentException;
        //  447    495    498    502    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0265:
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
