// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import org.jetbrains.annotations.Nullable;
import gnu.trove.THashMap;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import gnu.trove.TObjectHashingStrategy;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Pair;
import java.util.Map;

public abstract static class CachingEvaluator<T> implements Evaluator<T>
{
    private static final int MAX_SUBSTITUTIONS_PER_SYMBOL = 450;
    private Map<Pair<OCSymbol, OCTypeSubstitution>, T> mySymbolsWithSubstitutions;
    private MostlySingularMultiMap<OCSymbol, OCTypeSubstitution> mySubstitutions;
    @NotNull
    protected OCResolveContext myContext;
    
    protected CachingEvaluator(@NotNull final OCResolveContext myContext) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator", "<init>"));
        }
        this.mySymbolsWithSubstitutions = (Map<Pair<OCSymbol, OCTypeSubstitution>, T>)new THashMap((TObjectHashingStrategy)new TObjectHashingStrategy<Pair<OCSymbol, OCTypeSubstitution>>() {
            public int computeHashCode(final Pair<OCSymbol, OCTypeSubstitution> pair) {
                return pair.hashCode();
            }
            
            public boolean equals(final Pair<OCSymbol, OCTypeSubstitution> pair, final Pair<OCSymbol, OCTypeSubstitution> pair2) {
                return DeepEqual.equalObjects(pair, pair2);
            }
        });
        this.mySubstitutions = (MostlySingularMultiMap<OCSymbol, OCTypeSubstitution>)new MostlySingularMultiMap();
        this.myContext = myContext;
    }
    
    public CachingEvaluator(@NotNull final CachingEvaluator<T> cachingEvaluator, @NotNull final OCResolveContext myContext) {
        if (cachingEvaluator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clone", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator", "<init>"));
        }
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator", "<init>"));
        }
        this.mySymbolsWithSubstitutions = (Map<Pair<OCSymbol, OCTypeSubstitution>, T>)new THashMap((TObjectHashingStrategy)new TObjectHashingStrategy<Pair<OCSymbol, OCTypeSubstitution>>() {
            public int computeHashCode(final Pair<OCSymbol, OCTypeSubstitution> pair) {
                return pair.hashCode();
            }
            
            public boolean equals(final Pair<OCSymbol, OCTypeSubstitution> pair, final Pair<OCSymbol, OCTypeSubstitution> pair2) {
                return DeepEqual.equalObjects(pair, pair2);
            }
        });
        this.mySubstitutions = (MostlySingularMultiMap<OCSymbol, OCTypeSubstitution>)new MostlySingularMultiMap();
        this.mySymbolsWithSubstitutions = cachingEvaluator.mySymbolsWithSubstitutions;
        this.mySubstitutions = cachingEvaluator.mySubstitutions;
        this.myContext = myContext;
    }
    
    private OCTypeSubstitution a(final OCSymbol ocSymbol) {
        return this.myContext.useFor(ocSymbol).getSubstitution();
    }
    
    protected void cache(@Nullable final OCSymbol ocSymbol, @Nullable final T t) {
        final OCTypeSubstitution a = this.a(ocSymbol);
        try {
            this.mySymbolsWithSubstitutions.put((Pair<OCSymbol, OCTypeSubstitution>)Pair.create((Object)ocSymbol, (Object)a), t);
            if (ocSymbol != null) {
                this.mySubstitutions.add((Object)ocSymbol, (Object)a);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    protected void remove(@Nullable final OCSymbol ocSymbol) {
        final OCTypeSubstitution a = this.a(ocSymbol);
        try {
            this.mySymbolsWithSubstitutions.remove(Pair.create((Object)ocSymbol, (Object)a));
            if (ocSymbol != null) {
                this.mySubstitutions.removeAllValues((Object)ocSymbol);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    protected boolean contains(@Nullable final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //     5: astore_2       
        //     6: aload_0        
        //     7: getfield        com/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator.mySymbolsWithSubstitutions:Ljava/util/Map;
        //    10: aload_1        
        //    11: aload_2        
        //    12: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //    15: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //    20: ifne            63
        //    23: aload_1        
        //    24: ifnull          71
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    33: athrow         
        //    34: aload_0        
        //    35: getfield        com/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator.mySubstitutions:Lcom/intellij/util/containers/MostlySingularMultiMap;
        //    38: aload_1        
        //    39: invokevirtual   com/intellij/util/containers/MostlySingularMultiMap.get:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //    42: checkcast       Ljava/util/List;
        //    45: invokeinterface java/util/List.size:()I
        //    50: sipush          450
        //    53: if_icmplt       71
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: iconst_1       
        //    64: goto            72
        //    67: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  6      27     30     34     Ljava/lang/IllegalArgumentException;
        //  23     56     59     63     Ljava/lang/IllegalArgumentException;
        //  34     67     67     71     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
    protected T get(@Nullable final OCSymbol ocSymbol) {
        return this.mySymbolsWithSubstitutions.get(Pair.create((Object)ocSymbol, (Object)this.a(ocSymbol)));
    }
    
    @NotNull
    public OCResolveContext getContext() {
        OCResolveContext myContext;
        try {
            myContext = this.myContext;
            if (myContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$CachingEvaluator", "getContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myContext;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
