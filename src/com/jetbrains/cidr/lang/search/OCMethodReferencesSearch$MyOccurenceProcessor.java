// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.psi.search.TextOccurenceProcessor;

private static class MyOccurenceProcessor implements TextOccurenceProcessor
{
    private final OCMethod myMethod;
    private final OCMethodSymbol mySymbol;
    private final String myMethodSelector;
    private final OCObjectType myType;
    private final Processor<PsiReference> myConsumer;
    private final boolean myFindAllPotentialCalls;
    private final boolean mySetterMode;
    
    public MyOccurenceProcessor(final OCMethod myMethod, final String myMethodSelector, final Processor<PsiReference> myConsumer, final boolean mySetterMode, final boolean myFindAllPotentialCalls) {
        this.mySetterMode = mySetterMode;
        this.myMethodSelector = myMethodSelector;
        this.myMethod = myMethod;
        this.mySymbol = myMethod.getSymbol();
        this.myConsumer = myConsumer;
        this.myFindAllPotentialCalls = myFindAllPotentialCalls;
        this.myType = myMethod.getContainingClass().getType();
    }
    
    public boolean execute(@NotNull final PsiElement p0, final int p1) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "execute"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aconst_null    
        //    45: astore_3       
        //    46: aload_1        
        //    47: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    50: ifeq            148
        //    53: aload_1        
        //    54: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    57: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getName:()Ljava/lang/String;
        //    62: astore_3       
        //    63: aload_1        
        //    64: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    67: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    70: astore          4
        //    72: aload           4
        //    74: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    79: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //    82: ifeq            132
        //    85: aload           4
        //    87: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    92: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //    95: invokeinterface com/jetbrains/cidr/lang/psi/OCAssignmentExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   100: aload           4
        //   102: if_acmpne       132
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_0        
        //   113: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.mySetterMode:Z
        //   116: ifne            145
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_1       
        //   127: ireturn        
        //   128: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.mySetterMode:Z
        //   136: ifeq            145
        //   139: iconst_1       
        //   140: ireturn        
        //   141: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: goto            161
        //   148: aload_0        
        //   149: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.mySetterMode:Z
        //   152: ifeq            161
        //   155: iconst_1       
        //   156: ireturn        
        //   157: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: aload_1        
        //   162: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //   165: ifeq            221
        //   168: iload_2        
        //   169: ifne            221
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: aload_1        
        //   180: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   185: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   188: astore          4
        //   190: aload           4
        //   192: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //   197: iconst_0       
        //   198: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   203: aload_1        
        //   204: if_acmpne       218
        //   207: aload           4
        //   209: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   214: astore_3       
        //   215: aload           4
        //   217: astore_1       
        //   218: goto            315
        //   221: aload_1        
        //   222: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //   225: ifeq            241
        //   228: aload_1        
        //   229: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //   232: invokeinterface com/jetbrains/cidr/lang/psi/OCSelectorExpression.getSelector:()Ljava/lang/String;
        //   237: astore_3       
        //   238: goto            315
        //   241: aload_1        
        //   242: instanceof      Lcom/intellij/psi/xml/XmlTag;
        //   245: ifeq            266
        //   248: aload_1        
        //   249: checkcast       Lcom/intellij/psi/xml/XmlTag;
        //   252: invokeinterface com/intellij/psi/xml/XmlTag.getValue:()Lcom/intellij/psi/xml/XmlTagValue;
        //   257: invokeinterface com/intellij/psi/xml/XmlTagValue.getText:()Ljava/lang/String;
        //   262: astore_3       
        //   263: goto            315
        //   266: aload_1        
        //   267: instanceof      Lcom/intellij/psi/xml/XmlAttributeValue;
        //   270: ifeq            286
        //   273: aload_1        
        //   274: checkcast       Lcom/intellij/psi/xml/XmlAttributeValue;
        //   277: invokeinterface com/intellij/psi/xml/XmlAttributeValue.getValue:()Ljava/lang/String;
        //   282: astore_3       
        //   283: goto            315
        //   286: aload_1        
        //   287: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   292: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPropertyAttribute;
        //   295: ifeq            315
        //   298: aload_1        
        //   299: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   304: astore_1       
        //   305: aload_1        
        //   306: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPropertyAttribute;
        //   309: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getValue:()Ljava/lang/String;
        //   314: astore_3       
        //   315: aload_0        
        //   316: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myMethodSelector:Ljava/lang/String;
        //   319: aload_3        
        //   320: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   323: ifeq            713
        //   326: aload_1        
        //   327: invokeinterface com/intellij/psi/PsiElement.getReferences:()[Lcom/intellij/psi/PsiReference;
        //   332: astore          4
        //   334: aload           4
        //   336: arraylength    
        //   337: istore          5
        //   339: iconst_0       
        //   340: istore          6
        //   342: iload           6
        //   344: iload           5
        //   346: if_icmpge       713
        //   349: aload           4
        //   351: iload           6
        //   353: aaload         
        //   354: astore          7
        //   356: aload           7
        //   358: aload_0        
        //   359: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myMethod:Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   362: invokeinterface com/intellij/psi/PsiReference.isReferenceTo:(Lcom/intellij/psi/PsiElement;)Z
        //   367: ifeq            386
        //   370: aload_0        
        //   371: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myConsumer:Lcom/intellij/util/Processor;
        //   374: aload           7
        //   376: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   381: ireturn        
        //   382: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   385: athrow         
        //   386: aconst_null    
        //   387: astore          8
        //   389: iconst_1       
        //   390: istore          9
        //   392: aload           7
        //   394: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   399: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   402: ifeq            487
        //   405: aload           7
        //   407: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   412: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   415: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   420: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   423: astore          10
        //   425: aload           10
        //   427: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   430: ifeq            467
        //   433: aload           10
        //   435: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   438: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getSelfSuperToken:()Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   443: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken.SELF:Lcom/jetbrains/cidr/lang/parser/OCElementTypes$SelfSuperToken;
        //   446: if_acmpne       464
        //   449: goto            456
        //   452: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   455: athrow         
        //   456: iconst_1       
        //   457: goto            465
        //   460: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   463: athrow         
        //   464: iconst_0       
        //   465: istore          9
        //   467: aload           7
        //   469: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   474: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   477: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverContext:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   482: astore          8
        //   484: goto            522
        //   487: aload           7
        //   489: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   494: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   497: ifeq            522
        //   500: aload           7
        //   502: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   507: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   510: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   515: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTypeContext:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   520: astore          8
        //   522: aload           8
        //   524: ifnull          707
        //   527: aload_0        
        //   528: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   531: ifnull          707
        //   534: goto            541
        //   537: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   540: athrow         
        //   541: aload           8
        //   543: aload_0        
        //   544: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.mySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   547: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.fitsStaticness:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;)Z
        //   550: ifeq            707
        //   553: goto            560
        //   556: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   559: athrow         
        //   560: aload           8
        //   562: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   565: astore          10
        //   567: aload_0        
        //   568: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myFindAllPotentialCalls:Z
        //   571: ifeq            630
        //   574: iload           9
        //   576: ifne            630
        //   579: goto            586
        //   582: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   585: athrow         
        //   586: aload           10
        //   588: aload_0        
        //   589: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   592: aload_1        
        //   593: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   598: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.equalsAfterResolving:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   601: ifeq            707
        //   604: goto            611
        //   607: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   610: athrow         
        //   611: aload_0        
        //   612: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myConsumer:Lcom/intellij/util/Processor;
        //   615: aload           7
        //   617: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   622: pop            
        //   623: goto            707
        //   626: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   629: athrow         
        //   630: aload_0        
        //   631: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myFindAllPotentialCalls:Z
        //   634: ifeq            657
        //   637: aload           10
        //   639: aload_0        
        //   640: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   643: aload_1        
        //   644: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   647: ifne            691
        //   650: goto            657
        //   653: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   656: athrow         
        //   657: aload_0        
        //   658: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myFindAllPotentialCalls:Z
        //   661: ifne            707
        //   664: goto            671
        //   667: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   670: athrow         
        //   671: aload_0        
        //   672: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   675: aload           10
        //   677: aload_1        
        //   678: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   681: ifeq            707
        //   684: goto            691
        //   687: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   690: athrow         
        //   691: aload_0        
        //   692: getfield        com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.myConsumer:Lcom/intellij/util/Processor;
        //   695: aload           7
        //   697: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   702: ireturn        
        //   703: invokestatic    com/jetbrains/cidr/lang/search/OCMethodReferencesSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   706: athrow         
        //   707: iinc            6, 1
        //   710: goto            342
        //   713: iconst_1       
        //   714: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  72     105    108    112    Ljava/lang/IllegalArgumentException;
        //  85     119    122    126    Ljava/lang/IllegalArgumentException;
        //  112    128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    141    141    145    Ljava/lang/IllegalArgumentException;
        //  148    157    157    161    Ljava/lang/IllegalArgumentException;
        //  161    172    175    179    Ljava/lang/IllegalArgumentException;
        //  356    382    382    386    Ljava/lang/IllegalArgumentException;
        //  425    449    452    456    Ljava/lang/IllegalArgumentException;
        //  433    460    460    464    Ljava/lang/IllegalArgumentException;
        //  522    534    537    541    Ljava/lang/IllegalArgumentException;
        //  527    553    556    560    Ljava/lang/IllegalArgumentException;
        //  567    579    582    586    Ljava/lang/IllegalArgumentException;
        //  574    604    607    611    Ljava/lang/IllegalArgumentException;
        //  586    626    626    630    Ljava/lang/IllegalArgumentException;
        //  630    650    653    657    Ljava/lang/IllegalArgumentException;
        //  637    664    667    671    Ljava/lang/IllegalArgumentException;
        //  657    684    687    691    Ljava/lang/IllegalArgumentException;
        //  671    703    703    707    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0112:
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
