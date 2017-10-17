// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class QualifiedSelectorCompletionContributor$1 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String p0, @NotNull final OCCompletionParameters p1, final ProcessingContext p2, final CompletionResultSet p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "parameters"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "addCompletions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: invokevirtual   com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters.getPosition:()Lcom/intellij/psi/PsiElement;
        //    48: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    53: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    56: astore          5
        //    58: aload           5
        //    60: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    65: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTypeContext:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //    70: astore          6
        //    72: ldc             2147483647
        //    74: istore          7
        //    76: aload           5
        //    78: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.qualifyingTokensForCompletion:()Ljava/util/List;
        //    83: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    88: astore          8
        //    90: aload           8
        //    92: invokeinterface java/util/Iterator.hasNext:()Z
        //    97: ifeq            139
        //   100: aload           8
        //   102: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   107: checkcast       Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   110: astore          9
        //   112: aload_0        
        //   113: aload           9
        //   115: aload_2        
        //   116: aload           4
        //   118: aload           5
        //   120: aload           6
        //   122: invokespecial   com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionParameters;Lcom/intellij/codeInsight/completion/CompletionResultSet;Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;)I
        //   125: istore          10
        //   127: iload           7
        //   129: iload           10
        //   131: invokestatic    java/lang/Math.min:(II)I
        //   134: istore          7
        //   136: goto            90
        //   139: aload           6
        //   141: ifnull          249
        //   144: iload           7
        //   146: iconst_1       
        //   147: if_icmpgt       249
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload           4
        //   159: new             Ljava/lang/StringBuilder;
        //   162: dup            
        //   163: invokespecial   java/lang/StringBuilder.<init>:()V
        //   166: ldc             "Press "
        //   168: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   171: ldc             "SmartTypeCompletion"
        //   173: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor.access$000:(Ljava/lang/String;)Ljava/lang/String;
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: ldc             " to filter results by type"
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   187: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addLookupAdvertisement:(Ljava/lang/String;)V
        //   190: aload           4
        //   192: new             Ljava/lang/StringBuilder;
        //   195: dup            
        //   196: invokespecial   java/lang/StringBuilder.<init>:()V
        //   199: ldc             "Press "
        //   201: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: ldc             "CodeCompletion"
        //   206: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor.access$100:(Ljava/lang/String;)Ljava/lang/String;
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   212: iload           7
        //   214: ifne            233
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: ldc             ""
        //   226: goto            235
        //   229: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: ldc             " again"
        //   235: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   238: ldc             " for symbols from NSObject categories"
        //   240: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   243: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   246: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addLookupAdvertisement:(Ljava/lang/String;)V
        //   249: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  139    150    153    157    Ljava/lang/IllegalArgumentException;
        //  144    217    220    224    Ljava/lang/IllegalArgumentException;
        //  157    229    229    233    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0157:
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
    
    private int a(@NotNull final OCPunctuatorElementType p0, @NotNull final OCCompletionParameters p1, final CompletionResultSet p2, final OCQualifiedExpression p3, final OCObjectTypeContext p4) {
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
        //    18: ldc             "qualifyingToken"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "addCompletions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "parameters"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "addCompletions"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: new             Lcom/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1$1;
        //    91: dup            
        //    92: aload_0        
        //    93: aload_2        
        //    94: invokespecial   com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1$1.<init>:(Lcom/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1;Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionParameters;)V
        //    97: astore          6
        //    99: new             Lcom/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor;
        //   102: dup            
        //   103: aload           6
        //   105: iconst_5       
        //   106: anewarray       Lcom/intellij/openapi/util/Condition;
        //   109: dup            
        //   110: iconst_0       
        //   111: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //   116: aastore        
        //   117: dup            
        //   118: iconst_1       
        //   119: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //   124: aastore        
        //   125: dup            
        //   126: iconst_2       
        //   127: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //   132: aastore        
        //   133: dup            
        //   134: iconst_3       
        //   135: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //   140: aastore        
        //   141: dup            
        //   142: iconst_4       
        //   143: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbol.NON_FANTOM_SYMBOL_CONDITION:Lcom/intellij/openapi/util/Condition;
        //   146: aastore        
        //   147: invokespecial   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.<init>:(Lcom/intellij/util/Processor;[Lcom/intellij/openapi/util/Condition;)V
        //   150: astore          7
        //   152: new             Lcom/intellij/openapi/util/Ref;
        //   155: dup            
        //   156: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //   159: astore          8
        //   161: aload           4
        //   163: aconst_null    
        //   164: aload           7
        //   166: iconst_0       
        //   167: aload_1        
        //   168: iconst_0       
        //   169: iconst_1       
        //   170: aload           8
        //   172: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.processTargets:(Ljava/lang/String;Lcom/intellij/util/Processor;ZLcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;ZZLcom/intellij/openapi/util/Ref;)Z
        //   177: pop            
        //   178: aload           7
        //   180: invokevirtual   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.finish:()Z
        //   183: pop            
        //   184: aload           8
        //   186: invokevirtual   com/intellij/openapi/util/Ref.isNull:()Z
        //   189: ifeq            216
        //   192: aload           8
        //   194: aload           4
        //   196: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   201: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   206: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aload_2        
        //   217: invokevirtual   com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters.getInvocationCount:()I
        //   220: istore          9
        //   222: iload           9
        //   224: iconst_2       
        //   225: if_icmpgt       596
        //   228: iconst_1       
        //   229: istore          10
        //   231: aload           6
        //   233: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
        //   236: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   241: astore          11
        //   243: aload           11
        //   245: invokeinterface java/util/Iterator.hasNext:()Z
        //   250: ifeq            578
        //   253: aload           11
        //   255: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   260: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   263: astore          12
        //   265: iconst_0       
        //   266: istore          13
        //   268: aload           12
        //   270: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   273: ifeq            387
        //   276: aload           12
        //   278: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   281: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   286: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   289: astore          14
        //   291: iload           9
        //   293: iconst_1       
        //   294: if_icmpgt       343
        //   297: ldc             "NSObject"
        //   299: aload           14
        //   301: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   306: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   309: ifeq            343
        //   312: goto            319
        //   315: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   318: athrow         
        //   319: aload           14
        //   321: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   326: ifnull          343
        //   329: goto            336
        //   332: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   335: athrow         
        //   336: goto            243
        //   339: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   342: athrow         
        //   343: aload           5
        //   345: ifnull          384
        //   348: aload           14
        //   350: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   355: aload           5
        //   357: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   360: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassName:()Ljava/lang/String;
        //   363: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //   366: ifeq            384
        //   369: goto            376
        //   372: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   375: athrow         
        //   376: iconst_1       
        //   377: goto            385
        //   380: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   383: athrow         
        //   384: iconst_0       
        //   385: istore          13
        //   387: aload           12
        //   389: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isUnavailable:()Z
        //   394: ifne            243
        //   397: aload           12
        //   399: aload           4
        //   401: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isForbiddenByARC:(Lcom/intellij/psi/PsiElement;)Z
        //   406: ifeq            416
        //   409: goto            243
        //   412: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   415: athrow         
        //   416: iload           9
        //   418: iconst_1       
        //   419: if_icmpgt       454
        //   422: aload           12
        //   424: aload           4
        //   426: aload           8
        //   428: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   431: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   434: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.isVisible:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   437: ifne            454
        //   440: goto            447
        //   443: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   446: athrow         
        //   447: goto            243
        //   450: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   453: athrow         
        //   454: aload           12
        //   456: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   459: ifeq            514
        //   462: aload           12
        //   464: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isSynthetic:()Z
        //   469: ifeq            514
        //   472: goto            479
        //   475: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   478: athrow         
        //   479: aload           12
        //   481: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   484: ifeq            243
        //   487: goto            494
        //   490: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   493: athrow         
        //   494: aload           12
        //   496: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   499: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isAccessorWithAliasedName:()Z
        //   504: ifne            514
        //   507: goto            243
        //   510: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   513: athrow         
        //   514: aload           12
        //   516: aconst_null    
        //   517: aload           5
        //   519: ifnull          534
        //   522: aload           5
        //   524: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   527: goto            535
        //   530: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   533: athrow         
        //   534: aconst_null    
        //   535: aload           4
        //   537: iload           13
        //   539: aconst_null    
        //   540: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.lookup:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCObjectType;Lcom/intellij/psi/PsiElement;ZLcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;)Lcom/intellij/codeInsight/lookup/LookupElement;
        //   543: astore          14
        //   545: aload_3        
        //   546: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.getPrefixMatcher:()Lcom/intellij/codeInsight/completion/PrefixMatcher;
        //   549: aload           14
        //   551: invokevirtual   com/intellij/codeInsight/completion/PrefixMatcher.prefixMatches:(Lcom/intellij/codeInsight/lookup/LookupElement;)Z
        //   554: ifeq            575
        //   557: aload_3        
        //   558: aload           14
        //   560: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.addElement:(Lcom/intellij/codeInsight/lookup/LookupElement;)V
        //   563: getstatic       com/jetbrains/cidr/lang/psi/OCQualifiedExpression.COMPLETION_QUALIFYING_TOKEN_KEY:Lcom/intellij/openapi/util/Key;
        //   566: aload           14
        //   568: aload_1        
        //   569: invokevirtual   com/intellij/openapi/util/Key.set:(Lcom/intellij/openapi/util/UserDataHolder;Ljava/lang/Object;)V
        //   572: iconst_0       
        //   573: istore          10
        //   575: goto            243
        //   578: iload           10
        //   580: ifne            590
        //   583: goto            596
        //   586: invokestatic    com/jetbrains/cidr/lang/editor/completion/QualifiedSelectorCompletionContributor$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   589: athrow         
        //   590: iinc            9, 1
        //   593: goto            222
        //   596: iload           9
        //   598: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  161    209    212    216    Ljava/lang/IllegalArgumentException;
        //  291    312    315    319    Ljava/lang/IllegalArgumentException;
        //  297    329    332    336    Ljava/lang/IllegalArgumentException;
        //  319    339    339    343    Ljava/lang/IllegalArgumentException;
        //  343    369    372    376    Ljava/lang/IllegalArgumentException;
        //  348    380    380    384    Ljava/lang/IllegalArgumentException;
        //  397    412    412    416    Ljava/lang/IllegalArgumentException;
        //  416    440    443    447    Ljava/lang/IllegalArgumentException;
        //  422    450    450    454    Ljava/lang/IllegalArgumentException;
        //  454    472    475    479    Ljava/lang/IllegalArgumentException;
        //  462    487    490    494    Ljava/lang/IllegalArgumentException;
        //  494    510    510    514    Ljava/lang/IllegalArgumentException;
        //  514    530    530    534    Ljava/lang/IllegalArgumentException;
        //  578    586    586    590    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0319:
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