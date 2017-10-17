// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.util.containers.MostlySingularMultiMap;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCIncludeSymbol;
import com.intellij.openapi.progress.ProgressManager;
import gnu.trove.TObjectHashingStrategy;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.util.OCMostlySingularMultiUniqueMap;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

protected static class OCSymbols implements Symbols<OCSymbol>
{
    private final OCMostlySingularMultiUniqueMap<String, OCClassSymbol> myCategoryToClass;
    private final OCMostlySingularMultiUniqueMap<String, OCSymbol> myTopLevelSymbols;
    private final OCMostlySingularMultiUniqueMap<String, OCSymbol> myMemberSymbols;
    private final OCMostlySingularMultiUniqueMap<String, OCSymbol> myCppMemberSymbols;
    private final OCMostlySingularMultiUniqueMap<String, String> myTypeAliases;
    
    private OCSymbols() {
        final TObjectHashingStrategy<OCSymbol> tObjectHashingStrategy = (TObjectHashingStrategy<OCSymbol>)new TObjectHashingStrategy<OCSymbol>() {
            public int computeHashCode(final OCSymbol ocSymbol) {
                return System.identityHashCode(ocSymbol);
            }
            
            public boolean equals(final OCSymbol ocSymbol, final OCSymbol ocSymbol2) {
                return ocSymbol == ocSymbol2;
            }
        };
        this.myCategoryToClass = new OCMostlySingularMultiUniqueMap<String, OCClassSymbol>((gnu.trove.TObjectHashingStrategy<? super OCClassSymbol>)tObjectHashingStrategy);
        this.myTopLevelSymbols = new OCMostlySingularMultiUniqueMap<String, OCSymbol>((gnu.trove.TObjectHashingStrategy<? super OCSymbol>)tObjectHashingStrategy);
        this.myMemberSymbols = new OCMostlySingularMultiUniqueMap<String, OCSymbol>((gnu.trove.TObjectHashingStrategy<? super OCSymbol>)tObjectHashingStrategy);
        this.myCppMemberSymbols = new OCMostlySingularMultiUniqueMap<String, OCSymbol>((gnu.trove.TObjectHashingStrategy<? super OCSymbol>)tObjectHashingStrategy);
        this.myTypeAliases = new OCMostlySingularMultiUniqueMap<String, String>();
    }
    
    public boolean process(final OCSymbol ocSymbol) {
        try {
            ProgressManager.checkCanceled();
            if (ocSymbol instanceof OCIncludeSymbol) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String name = ocSymbol.getName();
        Label_0060: {
            try {
                if (OCSymbolWithQualifiedName.WITHOUT_QUALIFIER.value((Object)ocSymbol)) {
                    this.myTopLevelSymbols.add(name, ocSymbol);
                    break Label_0060;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            this.myCppMemberSymbols.add(name, ocSymbol);
        }
        if (ocSymbol instanceof OCClassSymbol) {
            final OCClassSymbol ocClassSymbol = (OCClassSymbol)ocSymbol;
            try {
                if (ocClassSymbol.getCategoryName() != null) {
                    this.myCategoryToClass.add(ocClassSymbol.getCategoryName(), ocClassSymbol);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            ocClassSymbol.processMembers(OCMemberSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)(ocMemberSymbol -> {
                ProgressManager.checkCanceled();
                this.myMemberSymbols.add(ocMemberSymbol.getName(), ocMemberSymbol);
                return true;
            }));
        }
        else if (ocSymbol instanceof OCNamespaceSymbol) {
            ((OCNamespaceSymbol)ocSymbol).processMembers(null, (Processor<OCSymbol>)new Processor<OCSymbol>() {
                public boolean process(final OCSymbol ocSymbol) {
                    ProgressManager.checkCanceled();
                    OCSymbols.this.myCppMemberSymbols.add(ocSymbol.getName(), ocSymbol);
                    if (ocSymbol instanceof OCNamespaceSymbol) {
                        ((OCNamespaceSymbol)ocSymbol).processMembers(null, (Processor<OCSymbol>)this);
                    }
                    return true;
                }
            });
        }
        else if (ocSymbol.getKind().isTypedefOrAlias()) {
            this.myTypeAliases.add(ocSymbol.getType().getName(), ocSymbol.getName());
        }
        return true;
    }
    
    @Override
    public boolean processAliasNamesForType(@NotNull final String s, @NotNull final Processor<String> processor) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/symtable/OCGlobalProjectSymbolsCache$OCSymbols", "processAliasNamesForType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/symtable/OCGlobalProjectSymbolsCache$OCSymbols", "processAliasNamesForType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.myTypeAliases.processForKey((Object)s, (Processor)processor);
    }
    
    @Override
    public boolean processTopLevel(final Processor<? super OCSymbol> processor, final String s) {
        return OCResolveUtil.processMap(processor, s, (com.intellij.util.containers.MostlySingularMultiMap<String, OCSymbol>)this.myTopLevelSymbols);
    }
    
    @Override
    public boolean processAllSymbols(final Processor<? super OCSymbol> p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_2        
        //     2: aload_0        
        //     3: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCGlobalProjectSymbolsCache$OCSymbols.myTopLevelSymbols:Lcom/jetbrains/cidr/lang/util/OCMostlySingularMultiUniqueMap;
        //     6: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.processMap:(Lcom/intellij/util/Processor;Ljava/lang/String;Lcom/intellij/util/containers/MostlySingularMultiMap;)Z
        //     9: ifeq            58
        //    12: aload_1        
        //    13: aload_2        
        //    14: aload_0        
        //    15: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCGlobalProjectSymbolsCache$OCSymbols.myMemberSymbols:Lcom/jetbrains/cidr/lang/util/OCMostlySingularMultiUniqueMap;
        //    18: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.processMap:(Lcom/intellij/util/Processor;Ljava/lang/String;Lcom/intellij/util/containers/MostlySingularMultiMap;)Z
        //    21: ifeq            58
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCGlobalProjectSymbolsCache$OCSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCGlobalProjectSymbolsCache$OCSymbols.myCppMemberSymbols:Lcom/jetbrains/cidr/lang/util/OCMostlySingularMultiUniqueMap;
        //    37: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.processMap:(Lcom/intellij/util/Processor;Ljava/lang/String;Lcom/intellij/util/containers/MostlySingularMultiMap;)Z
        //    40: ifeq            58
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCGlobalProjectSymbolsCache$OCSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: iconst_1       
        //    51: goto            59
        //    54: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCGlobalProjectSymbolsCache$OCSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: iconst_0       
        //    59: ireturn        
        //    Signature:
        //  (Lcom/intellij/util/Processor<-Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Ljava/lang/String;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      24     27     31     Ljava/lang/IllegalArgumentException;
        //  12     43     46     50     Ljava/lang/IllegalArgumentException;
        //  31     54     54     58     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    
    @Override
    public boolean isEmpty() {
        return this.myMemberSymbols.isEmpty();
    }
    
    public boolean processSymbolsByCategory(final Processor<OCSymbol> processor, final String s) {
        return OCResolveUtil.processMap(processor, s, this.myCategoryToClass);
    }
    
    public OCMostlySingularMultiUniqueMap<String, OCClassSymbol> getAllNamesWithCategories() {
        return this.myCategoryToClass;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
