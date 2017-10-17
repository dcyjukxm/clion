// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiReference;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceProvider;

public abstract class OCResourceCompletionProvider extends PsiReferenceProvider
{
    private String myClassName;
    private String myArgumentSelector;
    
    public OCResourceCompletionProvider(final String myClassName, final String myArgumentSelector) {
        this.myClassName = myClassName;
        this.myArgumentSelector = myArgumentSelector;
    }
    
    public String getClassName() {
        return this.myClassName;
    }
    
    public String getArgumentSelector() {
        return this.myArgumentSelector;
    }
    
    @NotNull
    public PsiReference[] getReferencesByElement(@NotNull final PsiElement p0, @NotNull final ProcessingContext p1) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getReferencesByElement"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "context"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getReferencesByElement"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: iconst_4       
        //    90: anewarray       Ljava/lang/Class;
        //    93: dup            
        //    94: iconst_0       
        //    95: ldc             Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;.class
        //    97: aastore        
        //    98: dup            
        //    99: iconst_1       
        //   100: ldc             Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;.class
        //   102: aastore        
        //   103: dup            
        //   104: iconst_2       
        //   105: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclaration;.class
        //   107: aastore        
        //   108: dup            
        //   109: iconst_3       
        //   110: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;.class
        //   112: aastore        
        //   113: invokestatic    com/intellij/psi/util/PsiTreeUtil.skipParentsOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   116: astore_3       
        //   117: aload_3        
        //   118: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCallArgument;
        //   121: ifeq            224
        //   124: aload_3        
        //   125: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   130: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   133: astore          4
        //   135: aload           4
        //   137: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getArguments:()Ljava/util/List;
        //   142: iconst_0       
        //   143: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   148: aload_3        
        //   149: if_acmpne       224
        //   152: aload_0        
        //   153: aload           4
        //   155: aconst_null    
        //   156: invokevirtual   com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.getReferenceByCall:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/resolve/references/OCResourceReference;
        //   159: astore          5
        //   161: aload           5
        //   163: ifnull          182
        //   166: iconst_1       
        //   167: anewarray       Lcom/intellij/psi/PsiReference;
        //   170: dup            
        //   171: iconst_0       
        //   172: aload           5
        //   174: aastore        
        //   175: goto            185
        //   178: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: getstatic       com/intellij/psi/PsiReference.EMPTY_ARRAY:[Lcom/intellij/psi/PsiReference;
        //   185: dup            
        //   186: ifnonnull       223
        //   189: new             Ljava/lang/IllegalStateException;
        //   192: dup            
        //   193: ldc             "@NotNull method %s.%s must not return null"
        //   195: ldc             2
        //   197: anewarray       Ljava/lang/Object;
        //   200: dup            
        //   201: ldc             0
        //   203: ldc             "com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider"
        //   205: aastore        
        //   206: dup            
        //   207: ldc             1
        //   209: ldc             "getReferencesByElement"
        //   211: aastore        
        //   212: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   215: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   218: athrow         
        //   219: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: areturn        
        //   224: aload_3        
        //   225: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   228: ifeq            276
        //   231: aload_1        
        //   232: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   235: ifeq            276
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   244: athrow         
        //   245: aload_3        
        //   246: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   249: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentSelector:()Lcom/jetbrains/cidr/lang/psi/OCArgumentSelector;
        //   254: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentSelector.getSelectorName:()Ljava/lang/String;
        //   259: aload_0        
        //   260: getfield        com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.myArgumentSelector:Ljava/lang/String;
        //   263: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   266: ifne            325
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   275: athrow         
        //   276: getstatic       com/intellij/psi/PsiReference.EMPTY_ARRAY:[Lcom/intellij/psi/PsiReference;
        //   279: dup            
        //   280: ifnonnull       324
        //   283: goto            290
        //   286: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   289: athrow         
        //   290: new             Ljava/lang/IllegalStateException;
        //   293: dup            
        //   294: ldc             "@NotNull method %s.%s must not return null"
        //   296: ldc             2
        //   298: anewarray       Ljava/lang/Object;
        //   301: dup            
        //   302: ldc             0
        //   304: ldc             "com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider"
        //   306: aastore        
        //   307: dup            
        //   308: ldc             1
        //   310: ldc             "getReferencesByElement"
        //   312: aastore        
        //   313: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   316: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   319: athrow         
        //   320: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   323: athrow         
        //   324: areturn        
        //   325: aload_0        
        //   326: getfield        com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.myClassName:Ljava/lang/String;
        //   329: aload_1        
        //   330: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   335: invokestatic    com/jetbrains/cidr/lang/types/OCReferenceType.resolvedFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   338: astore          4
        //   340: aload           4
        //   342: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   345: ifne            397
        //   348: getstatic       com/intellij/psi/PsiReference.EMPTY_ARRAY:[Lcom/intellij/psi/PsiReference;
        //   351: dup            
        //   352: ifnonnull       396
        //   355: goto            362
        //   358: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   361: athrow         
        //   362: new             Ljava/lang/IllegalStateException;
        //   365: dup            
        //   366: ldc             "@NotNull method %s.%s must not return null"
        //   368: ldc             2
        //   370: anewarray       Ljava/lang/Object;
        //   373: dup            
        //   374: ldc             0
        //   376: ldc             "com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider"
        //   378: aastore        
        //   379: dup            
        //   380: ldc             1
        //   382: ldc             "getReferencesByElement"
        //   384: aastore        
        //   385: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   388: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   391: athrow         
        //   392: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   395: athrow         
        //   396: areturn        
        //   397: aload_3        
        //   398: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   403: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   406: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverContext:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   411: astore          5
        //   413: aload           5
        //   415: ifnonnull       467
        //   418: getstatic       com/intellij/psi/PsiReference.EMPTY_ARRAY:[Lcom/intellij/psi/PsiReference;
        //   421: dup            
        //   422: ifnonnull       466
        //   425: goto            432
        //   428: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   431: athrow         
        //   432: new             Ljava/lang/IllegalStateException;
        //   435: dup            
        //   436: ldc             "@NotNull method %s.%s must not return null"
        //   438: ldc             2
        //   440: anewarray       Ljava/lang/Object;
        //   443: dup            
        //   444: ldc             0
        //   446: ldc             "com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider"
        //   448: aastore        
        //   449: dup            
        //   450: ldc             1
        //   452: ldc             "getReferencesByElement"
        //   454: aastore        
        //   455: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   458: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   461: athrow         
        //   462: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   465: athrow         
        //   466: areturn        
        //   467: aload           5
        //   469: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   472: astore          6
        //   474: aload           6
        //   476: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getName:()Ljava/lang/String;
        //   479: ldc             "id"
        //   481: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   484: ifne            527
        //   487: aload           6
        //   489: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getName:()Ljava/lang/String;
        //   492: ldc             "NSObject"
        //   494: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   497: ifne            527
        //   500: goto            507
        //   503: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   506: athrow         
        //   507: aload           4
        //   509: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   512: aload           6
        //   514: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isAncestorOf:(Lcom/jetbrains/cidr/lang/types/OCObjectType;)Z
        //   517: ifeq            598
        //   520: goto            527
        //   523: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   526: athrow         
        //   527: aload_0        
        //   528: aload_1        
        //   529: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   532: aconst_null    
        //   533: invokevirtual   com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.createReference:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/resolve/references/OCResourceReference;
        //   536: astore          7
        //   538: aload           7
        //   540: ifnull          598
        //   543: iconst_1       
        //   544: anewarray       Lcom/intellij/psi/PsiReference;
        //   547: dup            
        //   548: iconst_0       
        //   549: aload           7
        //   551: aastore        
        //   552: dup            
        //   553: ifnonnull       597
        //   556: goto            563
        //   559: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   562: athrow         
        //   563: new             Ljava/lang/IllegalStateException;
        //   566: dup            
        //   567: ldc             "@NotNull method %s.%s must not return null"
        //   569: ldc             2
        //   571: anewarray       Ljava/lang/Object;
        //   574: dup            
        //   575: ldc             0
        //   577: ldc             "com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider"
        //   579: aastore        
        //   580: dup            
        //   581: ldc             1
        //   583: ldc             "getReferencesByElement"
        //   585: aastore        
        //   586: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   589: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   592: athrow         
        //   593: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   596: athrow         
        //   597: areturn        
        //   598: getstatic       com/intellij/psi/PsiReference.EMPTY_ARRAY:[Lcom/intellij/psi/PsiReference;
        //   601: dup            
        //   602: ifnonnull       639
        //   605: new             Ljava/lang/IllegalStateException;
        //   608: dup            
        //   609: ldc             "@NotNull method %s.%s must not return null"
        //   611: ldc             2
        //   613: anewarray       Ljava/lang/Object;
        //   616: dup            
        //   617: ldc             0
        //   619: ldc             "com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider"
        //   621: aastore        
        //   622: dup            
        //   623: ldc             1
        //   625: ldc             "getReferencesByElement"
        //   627: aastore        
        //   628: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   631: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   634: athrow         
        //   635: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   638: athrow         
        //   639: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  161    178    178    182    Ljava/lang/IllegalArgumentException;
        //  185    219    219    223    Ljava/lang/IllegalArgumentException;
        //  224    238    241    245    Ljava/lang/IllegalArgumentException;
        //  231    269    272    276    Ljava/lang/IllegalArgumentException;
        //  245    283    286    290    Ljava/lang/IllegalArgumentException;
        //  276    320    320    324    Ljava/lang/IllegalArgumentException;
        //  340    355    358    362    Ljava/lang/IllegalArgumentException;
        //  348    392    392    396    Ljava/lang/IllegalArgumentException;
        //  413    425    428    432    Ljava/lang/IllegalArgumentException;
        //  418    462    462    466    Ljava/lang/IllegalArgumentException;
        //  474    500    503    507    Ljava/lang/IllegalArgumentException;
        //  487    520    523    527    Ljava/lang/IllegalArgumentException;
        //  538    556    559    563    Ljava/lang/IllegalArgumentException;
        //  543    593    593    597    Ljava/lang/IllegalArgumentException;
        //  598    635    635    639    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0245:
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
    public OCResourceReference getReferenceByCall(final PsiElement p0, @Nullable final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //     4: ifeq            96
        //     7: aload_1        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getMacroReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    16: astore_3       
        //    17: aload_3        
        //    18: ifnull          96
        //    21: aload_3        
        //    22: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getName:()Ljava/lang/String;
        //    27: ldc             "NSLocalizedString"
        //    29: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    32: ifeq            96
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_2        
        //    43: ifnull          86
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: aload_1        
        //    54: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    57: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getArguments:()Ljava/util/List;
        //    62: iconst_0       
        //    63: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    68: checkcast       Lcom/intellij/psi/PsiElement;
        //    71: aload_2        
        //    72: iconst_0       
        //    73: invokestatic    com/intellij/psi/util/PsiTreeUtil.isAncestor:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Z
        //    76: ifeq            96
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: aload_1        
        //    87: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    90: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getExpansionExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    95: astore_1       
        //    96: aload_1        
        //    97: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   100: ifne            109
        //   103: aconst_null    
        //   104: areturn        
        //   105: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: aload_1        
        //   110: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   113: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //   118: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   123: astore_3       
        //   124: aload_3        
        //   125: invokeinterface java/util/Iterator.hasNext:()Z
        //   130: ifeq            237
        //   133: aload_3        
        //   134: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   139: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   142: astore          4
        //   144: aload           4
        //   146: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentSelector:()Lcom/jetbrains/cidr/lang/psi/OCArgumentSelector;
        //   151: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentSelector.getSelectorName:()Ljava/lang/String;
        //   156: aload_0        
        //   157: getfield        com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.myArgumentSelector:Ljava/lang/String;
        //   160: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   163: ifeq            234
        //   166: aload           4
        //   168: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   173: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParenthesesAndCasts:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   176: astore          5
        //   178: aload           5
        //   180: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   183: ifne            220
        //   186: aload           5
        //   188: ifnull          232
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   197: athrow         
        //   198: aload           5
        //   200: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextWithMacros:()Ljava/lang/String;
        //   205: ldc             "CIDR_RULE_ZZZ"
        //   207: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   210: ifeq            232
        //   213: goto            220
        //   216: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: aload_0        
        //   221: aload           5
        //   223: aload_2        
        //   224: invokevirtual   com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.createReference:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/resolve/references/OCResourceReference;
        //   227: areturn        
        //   228: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCResourceCompletionProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: aconst_null    
        //   233: areturn        
        //   234: goto            124
        //   237: aconst_null    
        //   238: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  17     35     38     42     Ljava/lang/IllegalArgumentException;
        //  21     46     49     53     Ljava/lang/IllegalArgumentException;
        //  42     79     82     86     Ljava/lang/IllegalArgumentException;
        //  96     105    105    109    Ljava/lang/IllegalArgumentException;
        //  178    191    194    198    Ljava/lang/IllegalArgumentException;
        //  186    213    216    220    Ljava/lang/IllegalArgumentException;
        //  198    228    228    232    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0042:
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
    protected abstract OCResourceReference createReference(final OCExpression p0, @Nullable final PsiElement p1);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
