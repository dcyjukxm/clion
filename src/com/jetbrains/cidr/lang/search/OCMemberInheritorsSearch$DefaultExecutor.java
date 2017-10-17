// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.search.GlobalSearchScope;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.openapi.application.QueryExecutorBase;

private static class DefaultExecutor extends QueryExecutorBase<OCMemberSymbol, SearchParameters>
{
    public void processQuery(@NotNull final SearchParameters searchParameters, @NotNull final Processor<OCMemberSymbol> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final GlobalSearchScope scope = searchParameters.getScope();
        OCCommonProcessors.OrderedProcessor orderedProcessor = null;
        Label_0176: {
            try {
                if (searchParameters.isInterfacesThenImplementations()) {
                    final int n;
                    final Condition[] array;
                    orderedProcessor = new OCCommonProcessors.OrderedProcessor(processor, n != 0, array);
                    n = 1;
                    array = new Condition[] { ocMemberSymbol -> {
                            try {
                                if (((OCSymbolWithParent<T, OCClassSymbol>)ocMemberSymbol).getParent().getKind() != OCSymbolKind.IMPLEMENTATION) {
                                    return true;
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            return false;
                        }, Conditions.alwaysTrue() };
                    break Label_0176;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (searchParameters.isImplementationsThenInterfaces()) {
                    final int n2;
                    final Condition[] array2;
                    orderedProcessor = new OCCommonProcessors.OrderedProcessor((com.intellij.util.Processor<Object>)processor, n2 != 0, (com.intellij.openapi.util.Condition<Object>[])array2);
                    n2 = 1;
                    array2 = new Condition[] { ocMemberSymbol -> {
                            try {
                                if (((OCSymbolWithParent<T, OCClassSymbol>)ocMemberSymbol).getParent().getKind() == OCSymbolKind.IMPLEMENTATION) {
                                    return true;
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            return false;
                        }, Conditions.alwaysTrue() };
                    break Label_0176;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            orderedProcessor = (OCCommonProcessors.OrderedProcessor)processor;
        }
        final OCCommonProcessors.OrderedProcessor orderedProcessor2 = orderedProcessor;
        final Processor processor2 = p3 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: ifnonnull       44
            //     4: new             Ljava/lang/IllegalArgumentException;
            //     7: dup            
            //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    10: ldc             3
            //    12: anewarray       Ljava/lang/Object;
            //    15: dup            
            //    16: ldc             0
            //    18: ldc             "p"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "lambda$processQuery$2"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
            //    47: aload_0        
            //    48: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.getMemberClass:()Ljava/lang/Class;
            //    51: aload_3        
            //    52: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
            //    55: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
            //    58: ifeq            81
            //    61: aload_1        
            //    62: aload_3        
            //    63: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
            //    68: invokevirtual   com/intellij/psi/search/GlobalSearchScope.accept:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
            //    71: ifne            87
            //    74: goto            81
            //    77: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    80: athrow         
            //    81: iconst_1       
            //    82: ireturn        
            //    83: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    86: athrow         
            //    87: aload_3        
            //    88: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
            //    91: ifne            108
            //    94: aload_3        
            //    95: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
            //    98: ifeq            135
            //   101: goto            108
            //   104: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   107: athrow         
            //   108: aload_0        
            //   109: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.getStaticMode:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode;
            //   112: aload_3        
            //   113: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
            //   116: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext$StaticMode.fitsStaticness:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;)Z
            //   119: ifne            135
            //   122: goto            129
            //   125: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   128: athrow         
            //   129: iconst_1       
            //   130: ireturn        
            //   131: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   134: athrow         
            //   135: aload_3        
            //   136: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
            //   139: astore          4
            //   141: aload           4
            //   143: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   148: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //   151: astore          5
            //   153: aload_0        
            //   154: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.getReceiverClass:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //   157: astore          6
            //   159: aload_0        
            //   160: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.isIncludeInterfaceAndProtocolResponders:()Z
            //   163: ifne            202
            //   166: aload           5
            //   168: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //   171: ifne            196
            //   174: goto            181
            //   177: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   180: athrow         
            //   181: aload           5
            //   183: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
            //   186: ifeq            202
            //   189: goto            196
            //   192: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   195: athrow         
            //   196: iconst_1       
            //   197: ireturn        
            //   198: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   201: athrow         
            //   202: aload           6
            //   204: ifnull          240
            //   207: aload_0        
            //   208: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.isIncludeSelfImplementation:()Z
            //   211: ifne            246
            //   214: goto            221
            //   217: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   220: athrow         
            //   221: aload           5
            //   223: aload           6
            //   225: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSameClass:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Z
            //   230: ifeq            246
            //   233: goto            240
            //   236: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   239: athrow         
            //   240: iconst_1       
            //   241: ireturn        
            //   242: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   245: athrow         
            //   246: aload_0        
            //   247: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.isInheritors:()Z
            //   250: ifeq            308
            //   253: aload           5
            //   255: aload           6
            //   257: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSubclass:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Z
            //   262: ifeq            308
            //   265: goto            272
            //   268: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   271: athrow         
            //   272: aload_0        
            //   273: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.isIncludeFromID:()Z
            //   276: ifne            391
            //   279: goto            286
            //   282: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   285: athrow         
            //   286: aload           5
            //   288: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
            //   293: ldc             "id"
            //   295: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //   298: ifeq            391
            //   301: goto            308
            //   304: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   307: athrow         
            //   308: aload_0        
            //   309: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.isAncestors:()Z
            //   312: ifeq            404
            //   315: goto            322
            //   318: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   321: athrow         
            //   322: aload_0        
            //   323: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.isInheritors:()Z
            //   326: ifeq            355
            //   329: goto            336
            //   332: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   335: athrow         
            //   336: aload           6
            //   338: aload           5
            //   340: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSubclass:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Z
            //   345: ifeq            404
            //   348: goto            355
            //   351: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   354: athrow         
            //   355: aload_0        
            //   356: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.isIncludeFromID:()Z
            //   359: ifne            391
            //   362: goto            369
            //   365: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   368: athrow         
            //   369: aload           6
            //   371: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
            //   376: ldc             "id"
            //   378: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //   381: ifne            404
            //   384: goto            391
            //   387: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   390: athrow         
            //   391: aload_2        
            //   392: aload           4
            //   394: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //   399: ireturn        
            //   400: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   403: athrow         
            //   404: iconst_1       
            //   405: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     74     77     81     Ljava/lang/IllegalArgumentException;
            //  61     83     83     87     Ljava/lang/IllegalArgumentException;
            //  87     101    104    108    Ljava/lang/IllegalArgumentException;
            //  94     122    125    129    Ljava/lang/IllegalArgumentException;
            //  108    131    131    135    Ljava/lang/IllegalArgumentException;
            //  159    174    177    181    Ljava/lang/IllegalArgumentException;
            //  166    189    192    196    Ljava/lang/IllegalArgumentException;
            //  181    198    198    202    Ljava/lang/IllegalArgumentException;
            //  202    214    217    221    Ljava/lang/IllegalArgumentException;
            //  207    233    236    240    Ljava/lang/IllegalArgumentException;
            //  221    242    242    246    Ljava/lang/IllegalArgumentException;
            //  246    265    268    272    Ljava/lang/IllegalArgumentException;
            //  253    279    282    286    Ljava/lang/IllegalArgumentException;
            //  272    301    304    308    Ljava/lang/IllegalArgumentException;
            //  286    315    318    322    Ljava/lang/IllegalArgumentException;
            //  308    329    332    336    Ljava/lang/IllegalArgumentException;
            //  322    348    351    355    Ljava/lang/IllegalArgumentException;
            //  336    362    365    369    Ljava/lang/IllegalArgumentException;
            //  355    384    387    391    Ljava/lang/IllegalArgumentException;
            //  369    400    400    404    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0108:
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
        };
        Label_0289: {
            Label_0275: {
                try {
                    if (!searchParameters.isAncestors() || searchParameters.isInheritors()) {
                        break Label_0275;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                final OCClassSymbol receiverClass = searchParameters.getReceiverClass();
                OCType resolvedType = null;
                Label_0236: {
                    try {
                        if (receiverClass != null) {
                            resolvedType = receiverClass.getResolvedType();
                            break Label_0236;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                    resolvedType = null;
                }
                final OCType ocType = resolvedType;
                try {
                    if (ocType instanceof OCObjectType) {
                        ((OCObjectType)ocType).processMembers(searchParameters.getSelectorName(), searchParameters.getMemberClass(), (com.intellij.util.Processor<? super OCMemberSymbol>)processor2);
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                break Label_0289;
            }
            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(searchParameters.getProject(), (Processor<OCSymbol>)processor2, searchParameters.getSelectorName());
            try {
                if (orderedProcessor2 instanceof OCCommonProcessors.OrderedProcessor) {
                    orderedProcessor2.finish();
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
