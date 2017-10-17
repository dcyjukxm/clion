// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Comparing;
import com.intellij.psi.PsiManager;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.intellij.openapi.progress.ProgressManager;
import java.util.Map;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.preprocessor.OCParsingNameScope;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;

class 1Builder implements Processor<OCSymbol>
{
    private boolean done;
    private OCParsingNameScope myNameScope;
    final /* synthetic */ OCInclusionContext val$context;
    final /* synthetic */ FileSymbolTablesCache val$cache;
    final /* synthetic */ Set val$processedTables;
    final /* synthetic */ VirtualFile val$breakOn;
    final /* synthetic */ VirtualFile val$firstImportLink;
    final /* synthetic */ Processor val$processor;
    final /* synthetic */ String val$symbolName;
    final /* synthetic */ Map val$importsMap;
    
    1Builder(final OCParsingNameScope myNameScope, final OCInclusionContext val$context, final FileSymbolTablesCache val$cache, final Set val$processedTables, final VirtualFile val$breakOn, final VirtualFile val$firstImportLink, final Processor val$processor, final String val$symbolName, final OCParsingNameScope val$importsMap) {
        this.val$context = val$context;
        this.val$cache = val$cache;
        this.val$processedTables = val$processedTables;
        this.val$breakOn = val$breakOn;
        this.val$firstImportLink = val$firstImportLink;
        this.val$processor = val$processor;
        this.val$symbolName = val$symbolName;
        this.val$importsMap = (Map)val$importsMap;
        this.done = false;
        this.myNameScope = myNameScope;
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        ProgressManager.checkCanceled();
        if (ocSymbol instanceof OCIncludeSymbol) {
            final OCIncludeSymbol ocIncludeSymbol = (OCIncludeSymbol)ocSymbol;
            try {
                if (this.done) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final VirtualFile targetFile = ocIncludeSymbol.getTargetFile();
            FileSymbolTable forFile = null;
            Label_0117: {
                try {
                    if (targetFile == null || !targetFile.isValid()) {
                        break Label_0117;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final PsiFile file = PsiManager.getInstance(FileSymbolTable.access$000(FileSymbolTable.this)).findFile(targetFile);
                try {
                    if (!SymbolTableProvider.isSourceFile(file) || !this.val$context.reserveInclude(targetFile, ocIncludeSymbol.isOnce())) {
                        break Label_0117;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                forFile = this.val$cache.forFile(file, this.val$context);
                try {
                    if (this.val$processedTables.contains(forFile)) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            VirtualFile val$firstImportLink = null;
            Label_0199: {
                Label_0187: {
                    Label_0168: {
                        try {
                            if (this.val$breakOn == null) {
                                break Label_0168;
                            }
                            final VirtualFile virtualFile = targetFile;
                            final 1Builder 1Builder = this;
                            final VirtualFile virtualFile2 = 1Builder.val$breakOn;
                            final boolean b = Comparing.equal((Object)virtualFile, (Object)virtualFile2);
                            if (b) {
                                return false;
                            }
                            break Label_0168;
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                        try {
                            final VirtualFile virtualFile = targetFile;
                            final 1Builder 1Builder = this;
                            final VirtualFile virtualFile2 = 1Builder.val$breakOn;
                            final boolean b = Comparing.equal((Object)virtualFile, (Object)virtualFile2);
                            if (b) {
                                return false;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        try {
                            if (forFile == null) {
                                return true;
                            }
                            final 1Builder 1Builder2 = this;
                            final VirtualFile virtualFile3 = 1Builder2.val$firstImportLink;
                            if (virtualFile3 == null) {
                                break Label_0187;
                            }
                            break Label_0187;
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                    }
                    try {
                        final 1Builder 1Builder2 = this;
                        final VirtualFile virtualFile3 = 1Builder2.val$firstImportLink;
                        if (virtualFile3 == null) {
                            val$firstImportLink = targetFile;
                            break Label_0199;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                }
                val$firstImportLink = this.val$firstImportLink;
            }
            final VirtualFile virtualFile4 = val$firstImportLink;
            try {
                if (!forFile.processSymbols((Processor<OCSymbol>)this.val$processor, this.val$symbolName, this.val$processedTables, this.val$importsMap, virtualFile4, this.val$context, this.val$breakOn)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
        }
        else {
            try {
                if (this.done) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
            Label_0293: {
                try {
                    if (this.val$symbolName == null) {
                        break Label_0293;
                    }
                    final 1Builder 1Builder3 = this;
                    final String s = 1Builder3.val$symbolName;
                    final OCIncludeSymbol ocIncludeSymbol2 = (OCIncludeSymbol)ocSymbol;
                    final String s2 = ocIncludeSymbol2.getName();
                    final boolean b2 = s.equals(s2);
                    if (!b2) {
                        return true;
                    }
                    break Label_0293;
                }
                catch (IllegalArgumentException ex11) {
                    throw a(ex11);
                }
                try {
                    final 1Builder 1Builder3 = this;
                    final String s = 1Builder3.val$symbolName;
                    final OCIncludeSymbol ocIncludeSymbol2 = (OCIncludeSymbol)ocSymbol;
                    final String s2 = ocIncludeSymbol2.getName();
                    final boolean b2 = s.equals(s2);
                    if (!b2) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
                try {
                    this.b(ocSymbol);
                    this.c(ocSymbol);
                    if (!this.val$processor.process((Object)ocSymbol)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex13) {
                    throw a(ex13);
                }
            }
        }
        return true;
    }
    
    private void b(@NotNull final OCSymbol p0) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "updateImportsMap"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$importsMap:Ljava/util/Map;
        //    48: ifnull          118
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$firstImportLink:Lcom/intellij/openapi/vfs/VirtualFile;
        //    55: ifnull          118
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$importsMap:Ljava/util/Map;
        //    69: aload_1        
        //    70: aload_0        
        //    71: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$firstImportLink:Lcom/intellij/openapi/vfs/VirtualFile;
        //    74: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    79: pop            
        //    80: aload_1        
        //    81: instanceof      Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //    84: ifeq            118
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_1        
        //    95: checkcast       Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //    98: aconst_null    
        //    99: aload_0        
        //   100: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder;)Lcom/intellij/util/Processor;
        //   105: invokeinterface com/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   110: pop            
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     87     90     94     Ljava/lang/IllegalArgumentException;
        //  65     111    114    118    Ljava/lang/IllegalArgumentException;
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
    
    private void c(final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //     4: ifeq            27
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$context:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //    11: aload_1        
        //    12: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //    15: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.define:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;)V
        //    20: goto            508
        //    23: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_1        
        //    28: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCUndefMacroSymbol;
        //    31: ifeq            56
        //    34: aload_0        
        //    35: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$context:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //    38: aload_1        
        //    39: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    44: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.undef:(Ljava/lang/String;)V
        //    49: goto            508
        //    52: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_1        
        //    57: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    62: astore_2       
        //    63: aload_0        
        //    64: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$context:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //    67: invokeinterface com/jetbrains/cidr/lang/preprocessor/OCInclusionContext.getLanguageKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //    72: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isCpp:()Z
        //    77: istore_3       
        //    78: iload_3        
        //    79: ifeq            191
        //    82: aload_1        
        //    83: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //    86: ifeq            191
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_1        
        //    97: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   100: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.isPredefinition:()Z
        //   103: ifne            191
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_0        
        //   114: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   117: aload_1        
        //   118: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   123: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineNamespace:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   126: astore          4
        //   128: new             Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder;
        //   131: dup            
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.this$0:Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable;
        //   136: aload           4
        //   138: aload_0        
        //   139: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$context:Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;
        //   142: aload_0        
        //   143: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$cache:Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;
        //   146: aload_0        
        //   147: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$processedTables:Ljava/util/Set;
        //   150: aload_0        
        //   151: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$breakOn:Lcom/intellij/openapi/vfs/VirtualFile;
        //   154: aload_0        
        //   155: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$firstImportLink:Lcom/intellij/openapi/vfs/VirtualFile;
        //   158: aload_0        
        //   159: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$processor:Lcom/intellij/util/Processor;
        //   162: aload_0        
        //   163: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$symbolName:Ljava/lang/String;
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.val$importsMap:Ljava/util/Map;
        //   170: invokespecial   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.<init>:(Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable;Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;Ljava/util/Set;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/util/Processor;Ljava/lang/String;Ljava/util/Map;)V
        //   173: astore          5
        //   175: aload_1        
        //   176: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   179: aconst_null    
        //   180: aload           5
        //   182: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder;)Lcom/intellij/util/Processor;
        //   187: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   190: pop            
        //   191: aload_2        
        //   192: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isType:()Z
        //   195: ifeq            339
        //   198: aload_2        
        //   199: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   202: if_acmpne       232
        //   205: goto            212
        //   208: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: aload_0        
        //   213: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   216: aload_1        
        //   217: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   222: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineProtocol:(Ljava/lang/String;)V
        //   225: goto            508
        //   228: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: aload_2        
        //   233: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isClass:()Z
        //   236: ifne            253
        //   239: aload_2        
        //   240: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.COMPATIBILITY_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   243: if_acmpne       274
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: aload_0        
        //   254: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   257: aload_1        
        //   258: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   263: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineInterface:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //   266: pop            
        //   267: goto            508
        //   270: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: iload_3        
        //   275: ifeq            317
        //   278: aload_2        
        //   279: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //   282: ifeq            317
        //   285: goto            292
        //   288: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   291: athrow         
        //   292: aload_1        
        //   293: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   296: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isTemplateSymbol:()Z
        //   299: ifeq            317
        //   302: goto            309
        //   305: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   308: athrow         
        //   309: iconst_1       
        //   310: goto            318
        //   313: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: iconst_0       
        //   318: istore          4
        //   320: aload_0        
        //   321: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   324: aload_1        
        //   325: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   330: iload           4
        //   332: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineType:(Ljava/lang/String;Z)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //   335: pop            
        //   336: goto            508
        //   339: iload_3        
        //   340: ifeq            508
        //   343: aload_1        
        //   344: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   347: ifeq            435
        //   350: goto            357
        //   353: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   356: athrow         
        //   357: aload_1        
        //   358: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   361: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   364: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   367: ifnonnull       435
        //   370: goto            377
        //   373: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   376: athrow         
        //   377: aload_1        
        //   378: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   383: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //   386: ifne            508
        //   389: goto            396
        //   392: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   395: athrow         
        //   396: aload_1        
        //   397: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   400: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isTemplateSymbol:()Z
        //   403: ifeq            508
        //   406: goto            413
        //   409: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   412: athrow         
        //   413: aload_0        
        //   414: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   417: aload_1        
        //   418: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   423: iconst_1       
        //   424: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineValue:(Ljava/lang/String;Z)Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope$Kind;
        //   427: pop            
        //   428: goto            508
        //   431: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   434: athrow         
        //   435: aload_1        
        //   436: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   439: ifeq            508
        //   442: aload_1        
        //   443: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   448: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   451: if_acmpne       488
        //   454: goto            461
        //   457: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   460: athrow         
        //   461: aload_0        
        //   462: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   465: aload_1        
        //   466: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   469: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   472: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   475: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.flatten:()Ljava/util/List;
        //   478: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineNamespaceUsing:(Ljava/util/List;)V
        //   481: goto            508
        //   484: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   487: athrow         
        //   488: aload_0        
        //   489: getfield        com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTable$1Builder.myNameScope:Lcom/jetbrains/cidr/lang/preprocessor/OCParsingNameScope;
        //   492: aload_1        
        //   493: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   496: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   499: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   502: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.flatten:()Ljava/util/List;
        //   505: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCParsingNameScope.defineSymbolUsing:(Ljava/util/List;)V
        //   508: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      23     23     27     Ljava/lang/IllegalArgumentException;
        //  27     52     52     56     Ljava/lang/IllegalArgumentException;
        //  78     89     92     96     Ljava/lang/IllegalArgumentException;
        //  82     106    109    113    Ljava/lang/IllegalArgumentException;
        //  191    205    208    212    Ljava/lang/IllegalArgumentException;
        //  198    228    228    232    Ljava/lang/IllegalArgumentException;
        //  232    246    249    253    Ljava/lang/IllegalArgumentException;
        //  239    270    270    274    Ljava/lang/IllegalArgumentException;
        //  274    285    288    292    Ljava/lang/IllegalArgumentException;
        //  278    302    305    309    Ljava/lang/IllegalArgumentException;
        //  292    313    313    317    Ljava/lang/IllegalArgumentException;
        //  339    350    353    357    Ljava/lang/IllegalArgumentException;
        //  343    370    373    377    Ljava/lang/IllegalArgumentException;
        //  357    389    392    396    Ljava/lang/IllegalArgumentException;
        //  377    406    409    413    Ljava/lang/IllegalArgumentException;
        //  396    431    431    435    Ljava/lang/IllegalArgumentException;
        //  435    454    457    461    Ljava/lang/IllegalArgumentException;
        //  442    484    484    488    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0292:
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
