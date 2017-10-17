// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Iterator;
import com.intellij.util.Query;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.CollectionQuery;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.util.containers.HashMap;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.util.containers.HashSet;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.openapi.application.QueryExecutorBase;

private static class DefaultExecutor extends QueryExecutorBase<OCFunctionSymbol, SearchParameters>
{
    public void processQuery(@NotNull final SearchParameters searchParameters, @NotNull final Processor<OCFunctionSymbol> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor", "processQuery"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final GlobalSearchScope scope = searchParameters.getScope();
        final HashSet set = new HashSet();
        final OCQualifiedName parentStruct = searchParameters.getParentStruct();
        HashSet set2 = null;
        Label_0304: {
            Label_0274: {
                Label_0156: {
                    try {
                        if (searchParameters.myInheritenceCache == null || !searchParameters.myInheritenceCache.containsKey((Object)parentStruct)) {
                            break Label_0156;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    set.addAll(searchParameters.myInheritenceCache.get((Object)parentStruct));
                    break Label_0274;
                }
                Query<OCStructSymbol> query;
                if (searchParameters.myIncludeOnlyDirectInheritors) {
                    query = OCDirectStructInheritorsSearch.search(parentStruct, scope, (PsiFile)searchParameters.myContext, searchParameters.myProject);
                }
                else {
                    query = OCStructInheritorsSearch.search(parentStruct, scope, (PsiFile)searchParameters.myContext, searchParameters.myProject);
                }
                final Iterator iterator = query.findAll().iterator();
                while (iterator.hasNext()) {
                    set.add((Object)iterator.next().getResolvedQualifiedName(false));
                }
                try {
                    if (searchParameters.myInheritenceCache != null) {
                        searchParameters.myInheritenceCache.putValues((Object)parentStruct, (Collection)set);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    if (searchParameters.myIsFriendOrStatic) {
                        set2 = new HashSet();
                        break Label_0304;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            set2 = new HashSet((Collection)set);
        }
        final HashSet set3 = set2;
        try {
            if (searchParameters.myIncludeSameSymbols) {
                set3.add((Object)parentStruct.dropArguments());
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        final HashMap hashMap = new HashMap();
        boolean b2 = false;
        Label_0370: {
            Label_0361: {
                try {
                    if (searchParameters.myImplementationsThenPredefinitions) {
                        break Label_0361;
                    }
                    final SearchParameters searchParameters2 = searchParameters;
                    final boolean b = searchParameters2.myPredefinitionsThenImplementations;
                    if (b) {
                        break Label_0361;
                    }
                    break Label_0361;
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                try {
                    final SearchParameters searchParameters2 = searchParameters;
                    final boolean b = searchParameters2.myPredefinitionsThenImplementations;
                    if (b) {
                        b2 = true;
                        break Label_0370;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
            b2 = false;
        }
        Processor processor2;
        if (b2) {
            processor2 = (p2 -> {
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
                //    18: ldc             "p"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "lambda$processQuery$0"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: aload_0        
                //    45: aload_2        
                //    46: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //    49: invokevirtual   com/intellij/util/containers/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
                //    52: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //    55: astore_3       
                //    56: aload_1        
                //    57: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters.access$800:(Lcom/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters;)Z
                //    60: ifne            130
                //    63: aload_3        
                //    64: ifnull          130
                //    67: goto            74
                //    70: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    73: athrow         
                //    74: aload_3        
                //    75: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isPredeclaration:()Z
                //    78: ifeq            102
                //    81: goto            88
                //    84: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    87: athrow         
                //    88: aload_1        
                //    89: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters.access$900:(Lcom/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters;)Z
                //    92: ifne            130
                //    95: goto            102
                //    98: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   101: athrow         
                //   102: aload_3        
                //   103: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isPredeclaration:()Z
                //   106: ifne            147
                //   109: goto            116
                //   112: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   115: athrow         
                //   116: aload_1        
                //   117: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters.access$1000:(Lcom/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters;)Z
                //   120: ifeq            147
                //   123: goto            130
                //   126: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   129: athrow         
                //   130: aload_0        
                //   131: aload_2        
                //   132: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //   135: aload_2        
                //   136: invokevirtual   com/intellij/util/containers/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
                //   139: pop            
                //   140: goto            147
                //   143: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   146: athrow         
                //   147: iconst_1       
                //   148: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                //  56     67     70     74     Ljava/lang/IllegalArgumentException;
                //  63     81     84     88     Ljava/lang/IllegalArgumentException;
                //  74     95     98     102    Ljava/lang/IllegalArgumentException;
                //  88     109    112    116    Ljava/lang/IllegalArgumentException;
                //  102    123    126    130    Ljava/lang/IllegalArgumentException;
                //  116    140    143    147    Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0074:
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
            });
        }
        else {
            processor2 = processor;
        }
        final OCFile access$600 = searchParameters.myContext;
        VirtualFile virtualFile = null;
        Label_0424: {
            try {
                if (access$600 != null) {
                    virtualFile = access$600.getContainingFile().getVirtualFile();
                    break Label_0424;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
            virtualFile = null;
        }
        final VirtualFile virtualFile2 = virtualFile;
        OCType resolve = null;
        Label_0450: {
            try {
                if (searchParameters.myType != null) {
                    resolve = searchParameters.myType.resolve((PsiFile)access$600);
                    break Label_0450;
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
            resolve = null;
        }
        final OCType ocType = resolve;
        try {
            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(searchParameters.getProject(), (Processor<OCSymbol>)(p7 -> {
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
                //    18: ldc             "p"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "lambda$processQuery$2"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
                //    47: aload           7
                //    49: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //    52: ifeq            76
                //    55: aload_0        
                //    56: aload           7
                //    58: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
                //    63: invokevirtual   com/intellij/psi/search/GlobalSearchScope.accept:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
                //    66: ifne            82
                //    69: goto            76
                //    72: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    75: athrow         
                //    76: iconst_1       
                //    77: ireturn        
                //    78: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    81: athrow         
                //    82: aload           7
                //    84: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //    87: astore          8
                //    89: aload_1        
                //    90: invokevirtual   com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters.isFriend:()Z
                //    93: ifne            111
                //    96: aload           8
                //    98: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriend:()Z
                //   101: ifeq            117
                //   104: goto            111
                //   107: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   110: athrow         
                //   111: iconst_1       
                //   112: ireturn        
                //   113: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   116: athrow         
                //   117: aload           7
                //   119: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //   122: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
                //   125: astore          9
                //   127: aload           9
                //   129: ifnull          144
                //   132: aload           9
                //   134: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //   137: goto            145
                //   140: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   143: athrow         
                //   144: aconst_null    
                //   145: astore          10
                //   147: aload_1        
                //   148: invokevirtual   com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters.isFriend:()Z
                //   151: ifne            264
                //   154: aload           8
                //   156: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriend:()Z
                //   159: ifeq            184
                //   162: goto            169
                //   165: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   168: athrow         
                //   169: aload           8
                //   171: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppOperator:()Z
                //   174: ifeq            264
                //   177: goto            184
                //   180: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   183: athrow         
                //   184: aload           10
                //   186: ifnonnull       227
                //   189: goto            196
                //   192: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   195: athrow         
                //   196: aload_2        
                //   197: getstatic       com/jetbrains/cidr/lang/symbols/OCQualifiedName.GLOBAL:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //   200: if_acmpne       227
                //   203: goto            210
                //   206: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   209: athrow         
                //   210: aload_3        
                //   211: getstatic       com/jetbrains/cidr/lang/symbols/OCQualifiedName.GLOBAL:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //   214: invokevirtual   com/intellij/util/containers/HashSet.contains:(Ljava/lang/Object;)Z
                //   217: ifne            264
                //   220: goto            227
                //   223: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   226: athrow         
                //   227: aload           10
                //   229: ifnull          258
                //   232: goto            239
                //   235: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   238: athrow         
                //   239: aload_3        
                //   240: aload           10
                //   242: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.dropArguments:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
                //   245: invokevirtual   com/intellij/util/containers/HashSet.contains:(Ljava/lang/Object;)Z
                //   248: ifne            264
                //   251: goto            258
                //   254: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   257: athrow         
                //   258: iconst_1       
                //   259: ireturn        
                //   260: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   263: athrow         
                //   264: aload           8
                //   266: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isLocalInFile:()Z
                //   269: ifeq            298
                //   272: aload           4
                //   274: aload           8
                //   276: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
                //   279: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
                //   282: ifne            298
                //   285: goto            292
                //   288: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   291: athrow         
                //   292: iconst_0       
                //   293: ireturn        
                //   294: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   297: athrow         
                //   298: aload           8
                //   300: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
                //   303: astore          11
                //   305: aload           5
                //   307: ifnull          338
                //   310: aload           8
                //   312: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
                //   315: aload           5
                //   317: aload           11
                //   319: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.equalsAfterResolving:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
                //   322: ifne            338
                //   325: goto            332
                //   328: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   331: athrow         
                //   332: iconst_1       
                //   333: ireturn        
                //   334: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$DefaultExecutor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   337: athrow         
                //   338: aload           7
                //   340: aload           6
                //   342: invokedynamic   process:(Lcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
                //   347: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
                //   352: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                //  44     69     72     76     Ljava/lang/IllegalArgumentException;
                //  55     78     78     82     Ljava/lang/IllegalArgumentException;
                //  89     104    107    111    Ljava/lang/IllegalArgumentException;
                //  96     113    113    117    Ljava/lang/IllegalArgumentException;
                //  127    140    140    144    Ljava/lang/IllegalArgumentException;
                //  147    162    165    169    Ljava/lang/IllegalArgumentException;
                //  154    177    180    184    Ljava/lang/IllegalArgumentException;
                //  169    189    192    196    Ljava/lang/IllegalArgumentException;
                //  184    203    206    210    Ljava/lang/IllegalArgumentException;
                //  196    220    223    227    Ljava/lang/IllegalArgumentException;
                //  210    232    235    239    Ljava/lang/IllegalArgumentException;
                //  227    251    254    258    Ljava/lang/IllegalArgumentException;
                //  239    260    260    264    Ljava/lang/IllegalArgumentException;
                //  264    285    288    292    Ljava/lang/IllegalArgumentException;
                //  272    294    294    298    Ljava/lang/IllegalArgumentException;
                //  305    325    328    332    Ljava/lang/IllegalArgumentException;
                //  310    334    334    338    Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0169:
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
            }), searchParameters.myFunctionName);
            if (processor2 != processor) {
                new CollectionQuery(hashMap.values()).forEach((Processor)processor);
            }
        }
        catch (IllegalArgumentException ex11) {
            throw a(ex11);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
