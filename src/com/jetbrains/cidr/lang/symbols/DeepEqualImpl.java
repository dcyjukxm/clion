// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.util.containers.MostlySingularMultiMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.OCLog;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import com.intellij.util.Function;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import gnu.trove.THashMap;

class DeepEqualImpl implements DeepEqual.Resolver, DeepEqual.Comparator
{
    @NotNull
    private THashMap<Entry, State> myVisited;
    @NotNull
    private List<Entry> myComparisonStack;
    @NotNull
    private Map<Entry, Set<Entry>> myDependencies;
    private HashMap<Class, Function<Object, DeepEqual.Equality>> myCustomMappings;
    
    public DeepEqualImpl() {
        this((THashMap<Entry, State>)new THashMap());
    }
    
    public DeepEqualImpl(@NotNull final THashMap<Entry, State> myVisited) {
        if (myVisited == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "traversingState", "com/jetbrains/cidr/lang/symbols/DeepEqualImpl", "<init>"));
        }
        this.myComparisonStack = new ArrayList<Entry>();
        this.myDependencies = new HashMap<Entry, Set<Entry>>();
        this.myVisited = myVisited;
    }
    
    @Override
    public void clearCaches() {
        this.myVisited.clear();
    }
    
    @Override
    public <T> void setEquality(@NotNull final Class<T> clazz, @Nullable final Function<T, DeepEqual.Equality<T>> function) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "klass", "com/jetbrains/cidr/lang/symbols/DeepEqualImpl", "setEquality"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myCustomMappings == null) {
                this.myCustomMappings = new HashMap<Class, Function<Object, DeepEqual.Equality>>();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (function == null) {
                this.myCustomMappings.remove(clazz);
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        this.myCustomMappings.put(clazz, (Function<Object, DeepEqual.Equality>)function);
    }
    
    @Override
    public boolean equalIterable(final Iterable iterable, final Iterable iterable2) {
        Label_0035: {
            Label_0021: {
                try {
                    if (iterable instanceof Set) {
                        break Label_0021;
                    }
                    final Collection<Object> collection = (Collection<Object>)iterable2;
                    final boolean b = collection instanceof Set;
                    if (b) {
                        break Label_0021;
                    }
                    break Label_0035;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final Collection<Object> collection = (Collection<Object>)iterable2;
                    final boolean b = collection instanceof Set;
                    if (b) {
                        OCLog.LOG.error("write separate visit method for Sets");
                        return false;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            try {
                if (iterable == iterable2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        Label_0067: {
            try {
                if (iterable == null) {
                    return false;
                }
                final Collection<Object> collection2 = (Collection<Object>)iterable2;
                if (collection2 == null) {
                    return false;
                }
                break Label_0067;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final Collection<Object> collection2 = (Collection<Object>)iterable2;
                if (collection2 == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                if (((Collection<Object>)iterable).getClass() != ((Collection<Object>)iterable2).getClass()) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        Label_0125: {
            try {
                if (!(iterable instanceof Collection)) {
                    break Label_0125;
                }
                final Collection<Object> collection3 = (Collection<Object>)iterable;
                final Collection<Object> collection4 = collection3;
                final int n = collection4.size();
                final Collection<Object> collection5 = (Collection<Object>)iterable2;
                final Collection<Object> collection6 = collection5;
                final int n2 = collection6.size();
                if (n != n2) {
                    return false;
                }
                break Label_0125;
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            try {
                final Collection<Object> collection3 = (Collection<Object>)iterable;
                final Collection<Object> collection4 = collection3;
                final int n = collection4.size();
                final Collection<Object> collection5 = (Collection<Object>)iterable2;
                final Collection<Object> collection6 = collection5;
                final int n2 = collection6.size();
                if (n != n2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        final Iterator<Object> iterator = iterable.iterator();
        final Iterator<Object> iterator2 = iterable2.iterator();
        while (true) {
            try {
                if (!iterator.hasNext() || !iterator2.hasNext()) {
                    break;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
            final Object next = iterator.next();
            final Object next2 = iterator2.next();
            try {
                if (!this.equalObjects(next, next2)) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
        }
        try {
            if (iterator.hasNext()) {
                return false;
            }
            final Iterator<Object> iterator3 = iterator2;
            final boolean b2 = iterator3.hasNext();
            if (b2) {
                return false;
            }
            return true;
        }
        catch (IllegalArgumentException ex11) {
            throw a(ex11);
        }
        try {
            final Iterator<Object> iterator3 = iterator2;
            final boolean b2 = iterator3.hasNext();
            if (b2) {
                return false;
            }
        }
        catch (IllegalArgumentException ex12) {
            throw a(ex12);
        }
        return true;
    }
    
    @Override
    public boolean equalObjects(final Object p0, final Object p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_2        
        //     2: if_acmpne       11
        //     5: iconst_1       
        //     6: ireturn        
        //     7: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    10: athrow         
        //    11: aload_1        
        //    12: ifnull          26
        //    15: aload_2        
        //    16: ifnonnull       32
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    25: athrow         
        //    26: iconst_0       
        //    27: ireturn        
        //    28: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    31: athrow         
        //    32: aload_1        
        //    33: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    36: astore_3       
        //    37: aload_3        
        //    38: aload_2        
        //    39: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    42: if_acmpeq       51
        //    45: iconst_0       
        //    46: ireturn        
        //    47: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    50: athrow         
        //    51: aload_1        
        //    52: instanceof      Lcom/intellij/openapi/util/Pair;
        //    55: ifeq            124
        //    58: aload_0        
        //    59: aload_1        
        //    60: checkcast       Lcom/intellij/openapi/util/Pair;
        //    63: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //    66: aload_2        
        //    67: checkcast       Lcom/intellij/openapi/util/Pair;
        //    70: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //    73: invokevirtual   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.equalObjects:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    76: ifeq            122
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    85: athrow         
        //    86: aload_0        
        //    87: aload_1        
        //    88: checkcast       Lcom/intellij/openapi/util/Pair;
        //    91: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //    94: aload_2        
        //    95: checkcast       Lcom/intellij/openapi/util/Pair;
        //    98: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   101: invokevirtual   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.equalObjects:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   104: ifeq            122
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   113: athrow         
        //   114: iconst_1       
        //   115: goto            123
        //   118: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   121: athrow         
        //   122: iconst_0       
        //   123: ireturn        
        //   124: aload_1        
        //   125: instanceof      Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Equality;
        //   128: ifeq            146
        //   131: aload_0        
        //   132: aload_1        
        //   133: checkcast       Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Equality;
        //   136: aload_1        
        //   137: aload_2        
        //   138: invokevirtual   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.equalObjects:(Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Equality;Ljava/lang/Object;Ljava/lang/Object;)Z
        //   141: ireturn        
        //   142: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   145: athrow         
        //   146: aload_1        
        //   147: instanceof      Ljava/lang/Iterable;
        //   150: ifeq            170
        //   153: aload_0        
        //   154: aload_1        
        //   155: checkcast       Ljava/lang/Iterable;
        //   158: aload_2        
        //   159: checkcast       Ljava/lang/Iterable;
        //   162: invokevirtual   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.equalIterable:(Ljava/lang/Iterable;Ljava/lang/Iterable;)Z
        //   165: ireturn        
        //   166: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   169: athrow         
        //   170: aload_1        
        //   171: instanceof      Ljava/util/Map;
        //   174: ifeq            194
        //   177: aload_0        
        //   178: aload_1        
        //   179: checkcast       Ljava/util/Map;
        //   182: aload_2        
        //   183: checkcast       Ljava/util/Map;
        //   186: invokevirtual   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.equalMaps:(Ljava/util/Map;Ljava/util/Map;)Z
        //   189: ireturn        
        //   190: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   193: athrow         
        //   194: aload_1        
        //   195: instanceof      Lcom/intellij/util/containers/MostlySingularMultiMap;
        //   198: ifeq            218
        //   201: aload_0        
        //   202: aload_1        
        //   203: checkcast       Lcom/intellij/util/containers/MostlySingularMultiMap;
        //   206: aload_2        
        //   207: checkcast       Lcom/intellij/util/containers/MostlySingularMultiMap;
        //   210: invokevirtual   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.equalMultiMaps:(Lcom/intellij/util/containers/MostlySingularMultiMap;Lcom/intellij/util/containers/MostlySingularMultiMap;)Z
        //   213: ireturn        
        //   214: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   217: athrow         
        //   218: aload_1        
        //   219: instanceof      Ljava/lang/String;
        //   222: ifne            253
        //   225: aload_1        
        //   226: instanceof      Ljava/lang/Number;
        //   229: ifne            253
        //   232: goto            239
        //   235: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   238: athrow         
        //   239: aload_1        
        //   240: instanceof      Ljava/lang/Boolean;
        //   243: ifeq            263
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   252: athrow         
        //   253: aload_1        
        //   254: aload_2        
        //   255: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   258: ireturn        
        //   259: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   262: athrow         
        //   263: aload_1        
        //   264: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   267: invokevirtual   java/lang/Class.isEnum:()Z
        //   270: ifeq            279
        //   273: iconst_0       
        //   274: ireturn        
        //   275: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   278: athrow         
        //   279: aload_0        
        //   280: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myCustomMappings:Ljava/util/HashMap;
        //   283: ifnull          326
        //   286: aload_0        
        //   287: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myCustomMappings:Ljava/util/HashMap;
        //   290: aload_3        
        //   291: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   294: checkcast       Lcom/intellij/util/Function;
        //   297: astore          4
        //   299: aload           4
        //   301: ifnull          326
        //   304: aload           4
        //   306: aload_1        
        //   307: invokeinterface com/intellij/util/Function.fun:(Ljava/lang/Object;)Ljava/lang/Object;
        //   312: checkcast       Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Equality;
        //   315: astore          5
        //   317: aload_0        
        //   318: aload           5
        //   320: aload_1        
        //   321: aload_2        
        //   322: invokevirtual   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.equalObjects:(Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Equality;Ljava/lang/Object;Ljava/lang/Object;)Z
        //   325: ireturn        
        //   326: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   329: new             Ljava/lang/StringBuilder;
        //   332: dup            
        //   333: invokespecial   java/lang/StringBuilder.<init>:()V
        //   336: ldc             "trying to compare objects of class "
        //   338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   341: aload_1        
        //   342: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   345: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   348: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   351: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //   354: iconst_0       
        //   355: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      7      7      11     Ljava/lang/IllegalArgumentException;
        //  11     19     22     26     Ljava/lang/IllegalArgumentException;
        //  15     28     28     32     Ljava/lang/IllegalArgumentException;
        //  37     47     47     51     Ljava/lang/IllegalArgumentException;
        //  51     79     82     86     Ljava/lang/IllegalArgumentException;
        //  58     107    110    114    Ljava/lang/IllegalArgumentException;
        //  86     118    118    122    Ljava/lang/IllegalArgumentException;
        //  124    142    142    146    Ljava/lang/IllegalArgumentException;
        //  146    166    166    170    Ljava/lang/IllegalArgumentException;
        //  170    190    190    194    Ljava/lang/IllegalArgumentException;
        //  194    214    214    218    Ljava/lang/IllegalArgumentException;
        //  218    232    235    239    Ljava/lang/IllegalArgumentException;
        //  225    246    249    253    Ljava/lang/IllegalArgumentException;
        //  239    259    259    263    Ljava/lang/IllegalArgumentException;
        //  263    275    275    279    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0086:
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
    public boolean equalObjects(final DeepEqual.Equality equality, final DeepEqual.Equality equality2) {
        return this.equalObjects(equality, equality, equality2);
    }
    
    protected <T> boolean equalObjects(final DeepEqual.Equality<T> p0, final T p1, final T p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: aload_3        
        //     2: if_acmpne       11
        //     5: iconst_1       
        //     6: ireturn        
        //     7: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    10: athrow         
        //    11: aload_2        
        //    12: ifnull          26
        //    15: aload_3        
        //    16: ifnonnull       32
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    25: athrow         
        //    26: iconst_0       
        //    27: ireturn        
        //    28: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    31: athrow         
        //    32: aload_2        
        //    33: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    36: aload_3        
        //    37: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    40: if_acmpeq       49
        //    43: iconst_0       
        //    44: ireturn        
        //    45: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    48: athrow         
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myComparisonStack:Ljava/util/List;
        //    53: invokeinterface java/util/List.isEmpty:()Z
        //    58: istore          4
        //    60: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //    63: new             Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$Entry;
        //    66: dup            
        //    67: aload_2        
        //    68: aload_3        
        //    69: aconst_null    
        //    70: invokespecial   com/jetbrains/cidr/lang/symbols/DeepEqualImpl$Entry.<init>:(Ljava/lang/Object;Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$a;)V
        //    73: astore          5
        //    75: iload           4
        //    77: ifeq            88
        //    80: aconst_null    
        //    81: goto            100
        //    84: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myVisited:Lgnu/trove/THashMap;
        //    92: aload           5
        //    94: invokevirtual   gnu/trove/THashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    97: checkcast       Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   100: astore          6
        //   102: aload           6
        //   104: ifnonnull       267
        //   107: iload           4
        //   109: ifne            139
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   118: athrow         
        //   119: aload_0        
        //   120: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myVisited:Lgnu/trove/THashMap;
        //   123: aload           5
        //   125: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.MAYBE:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   128: invokevirtual   gnu/trove/THashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   131: pop            
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   138: athrow         
        //   139: aload_0        
        //   140: aload           5
        //   142: invokespecial   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.b:(Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$Entry;)V
        //   145: aload_1        
        //   146: aload_0        
        //   147: aload_2        
        //   148: aload_3        
        //   149: invokeinterface com/jetbrains/cidr/lang/symbols/DeepEqual$Equality.deepEqualStep:(Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Comparator;Ljava/lang/Object;Ljava/lang/Object;)Z
        //   154: istore          7
        //   156: aload_0        
        //   157: invokespecial   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:()Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   160: astore          8
        //   162: iload           4
        //   164: ifeq            197
        //   167: iload           7
        //   169: ifeq            189
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   178: athrow         
        //   179: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.EQUALS:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   182: goto            192
        //   185: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   188: athrow         
        //   189: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.NOT_EQUALS:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   192: astore          6
        //   194: goto            216
        //   197: iload           7
        //   199: ifeq            211
        //   202: aload           8
        //   204: goto            214
        //   207: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   210: athrow         
        //   211: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.NOT_EQUALS:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   214: astore          6
        //   216: iload           4
        //   218: ifne            267
        //   221: aload           6
        //   223: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.MAYBE:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   226: if_acmpeq       267
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   235: athrow         
        //   236: aload_0        
        //   237: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myVisited:Lgnu/trove/THashMap;
        //   240: aload           5
        //   242: aload           6
        //   244: invokevirtual   gnu/trove/THashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   247: pop            
        //   248: aload_0        
        //   249: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myDependencies:Ljava/util/Map;
        //   252: aload           5
        //   254: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //   259: pop            
        //   260: goto            267
        //   263: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   266: athrow         
        //   267: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl.$assertionsDisabled:Z
        //   270: ifne            297
        //   273: aload           6
        //   275: ifnonnull       297
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   284: athrow         
        //   285: new             Ljava/lang/AssertionError;
        //   288: dup            
        //   289: invokespecial   java/lang/AssertionError.<init>:()V
        //   292: athrow         
        //   293: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   296: athrow         
        //   297: aload           6
        //   299: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.MAYBE:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   302: if_acmpne       318
        //   305: aload_0        
        //   306: aload           5
        //   308: invokespecial   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$Entry;)V
        //   311: goto            318
        //   314: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   317: athrow         
        //   318: iload           4
        //   320: ifeq            413
        //   323: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl.$assertionsDisabled:Z
        //   326: ifne            367
        //   329: goto            336
        //   332: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   335: athrow         
        //   336: aload_0        
        //   337: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myComparisonStack:Ljava/util/List;
        //   340: invokeinterface java/util/List.isEmpty:()Z
        //   345: ifne            367
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   354: athrow         
        //   355: new             Ljava/lang/AssertionError;
        //   358: dup            
        //   359: invokespecial   java/lang/AssertionError.<init>:()V
        //   362: athrow         
        //   363: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   366: athrow         
        //   367: aload_0        
        //   368: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myDependencies:Ljava/util/Map;
        //   371: invokeinterface java/util/Map.isEmpty:()Z
        //   376: ifne            413
        //   379: aload_0        
        //   380: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myDependencies:Ljava/util/Map;
        //   383: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //   388: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   393: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   398: checkcast       Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$Entry;
        //   401: astore          7
        //   403: aload_0        
        //   404: aload           7
        //   406: invokespecial   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.c:(Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$Entry;)Z
        //   409: pop            
        //   410: goto            367
        //   413: aload           6
        //   415: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.NOT_EQUALS:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   418: if_acmpeq       429
        //   421: iconst_1       
        //   422: goto            430
        //   425: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   428: athrow         
        //   429: iconst_0       
        //   430: ireturn        
        //   431: astore          5
        //   433: iload           4
        //   435: ifeq            481
        //   438: aload_0        
        //   439: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myDependencies:Ljava/util/Map;
        //   442: invokeinterface java/util/Map.clear:()V
        //   447: aload_0        
        //   448: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myComparisonStack:Ljava/util/List;
        //   451: invokeinterface java/util/List.clear:()V
        //   456: aload_0        
        //   457: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myVisited:Lgnu/trove/THashMap;
        //   460: invokevirtual   gnu/trove/THashMap.entrySet:()Ljava/util/Set;
        //   463: invokedynamic   test:()Ljava/util/function/Predicate;
        //   468: invokeinterface java/util/Set.removeIf:(Ljava/util/function/Predicate;)Z
        //   473: pop            
        //   474: goto            481
        //   477: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   480: athrow         
        //   481: aload           5
        //   483: athrow         
        //    Signature:
        //  <T:Ljava/lang/Object;>(Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Equality<TT;>;TT;TT;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  413    425    425    429    Ljava/lang/Throwable;
        //  336    363    363    367    Ljava/lang/Throwable;
        //  323    348    351    355    Ljava/lang/Throwable;
        //  318    329    332    336    Ljava/lang/Throwable;
        //  297    311    314    318    Ljava/lang/Throwable;
        //  273    293    293    297    Ljava/lang/Throwable;
        //  267    278    281    285    Ljava/lang/Throwable;
        //  221    260    263    267    Ljava/lang/Throwable;
        //  216    229    232    236    Ljava/lang/Throwable;
        //  197    207    207    211    Ljava/lang/Throwable;
        //  167    185    185    189    Ljava/lang/Throwable;
        //  162    172    175    179    Ljava/lang/Throwable;
        //  107    132    135    139    Ljava/lang/Throwable;
        //  102    112    115    119    Ljava/lang/Throwable;
        //  75     84     84     88     Ljava/lang/Throwable;
        //  32     45     45     49     Ljava/lang/Throwable;
        //  15     28     28     32     Ljava/lang/Throwable;
        //  11     19     22     26     Ljava/lang/Throwable;
        //  0      7      7      11     Ljava/lang/Throwable;
        //  60     430    431    484    Ljava/lang/Throwable;
        //  433    474    477    481    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0336:
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
    
    private boolean c(final Entry p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myDependencies:Ljava/util/Map;
        //     4: aload_1        
        //     5: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    10: checkcast       Ljava/util/Set;
        //    13: astore_2       
        //    14: aload_0        
        //    15: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myVisited:Lgnu/trove/THashMap;
        //    18: aload_1        
        //    19: invokevirtual   gnu/trove/THashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    22: checkcast       Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //    25: astore_3       
        //    26: aload_3        
        //    27: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.MAYBE:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //    30: if_acmpeq       68
        //    33: aload_3        
        //    34: ifnull          58
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    43: athrow         
        //    44: aload_3        
        //    45: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.EQUALS:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //    48: if_acmpne       66
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    57: athrow         
        //    58: iconst_1       
        //    59: goto            67
        //    62: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    65: athrow         
        //    66: iconst_0       
        //    67: ireturn        
        //    68: iconst_1       
        //    69: istore          4
        //    71: aload_2        
        //    72: ifnull          119
        //    75: aload_2        
        //    76: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    81: astore          5
        //    83: aload           5
        //    85: invokeinterface java/util/Iterator.hasNext:()Z
        //    90: ifeq            119
        //    93: aload           5
        //    95: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   100: checkcast       Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$Entry;
        //   103: astore          6
        //   105: iload           4
        //   107: aload_0        
        //   108: aload           6
        //   110: invokespecial   com/jetbrains/cidr/lang/symbols/DeepEqualImpl.c:(Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$Entry;)Z
        //   113: iand           
        //   114: istore          4
        //   116: goto            83
        //   119: aload_0        
        //   120: getfield        com/jetbrains/cidr/lang/symbols/DeepEqualImpl.myVisited:Lgnu/trove/THashMap;
        //   123: aload_1        
        //   124: iload           4
        //   126: ifeq            139
        //   129: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.EQUALS:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   132: goto            142
        //   135: invokestatic    com/jetbrains/cidr/lang/symbols/DeepEqualImpl.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   138: athrow         
        //   139: getstatic       com/jetbrains/cidr/lang/symbols/DeepEqualImpl$State.NOT_EQUALS:Lcom/jetbrains/cidr/lang/symbols/DeepEqualImpl$State;
        //   142: invokevirtual   gnu/trove/THashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   145: pop            
        //   146: iload           4
        //   148: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  26     37     40     44     Ljava/lang/IllegalArgumentException;
        //  33     51     54     58     Ljava/lang/IllegalArgumentException;
        //  44     62     62     66     Ljava/lang/IllegalArgumentException;
        //  119    135    135    139    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0044:
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
    
    private void a(final Entry entry) {
        final Entry b = this.b();
        try {
            if (b == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        b.myPushedState = State.MAYBE;
        Set<Entry> set = this.myDependencies.get(b);
        if (set == null) {
            set = new HashSet<Entry>();
            this.myDependencies.put(b, set);
        }
        set.add(entry);
    }
    
    private State a() {
        return this.myComparisonStack.remove(this.myComparisonStack.size() - 1).myPushedState;
    }
    
    private void b(final Entry entry) {
        entry.myPushedState = State.EQUALS;
        this.myComparisonStack.add(entry);
    }
    
    private Entry b() {
        try {
            if (this.myComparisonStack.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myComparisonStack.get(this.myComparisonStack.size() - 1);
    }
    
    @Override
    public boolean equalMaps(final Map map, final Map map2) {
        try {
            if (map == map2) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0032: {
            try {
                if (map == null) {
                    return false;
                }
                final Object o = map2;
                if (o == null) {
                    return false;
                }
                break Label_0032;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final Object o = map2;
                if (o == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final Set<Map.Entry<Object, V>> entrySet = map.entrySet();
        final Set<Map.Entry<K, V>> entrySet2 = map2.entrySet();
        try {
            if (entrySet.size() != entrySet2.size()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        for (final Map.Entry<Object, V> entry : entrySet) {
            boolean equalObjects = false;
            for (final Map.Entry<K, V> entry2 : entrySet2) {
                if (this.equalObjects(entry.getKey(), entry2.getKey())) {
                    equalObjects = this.equalObjects(entry.getValue(), entry2.getValue());
                    break;
                }
            }
            try {
                if (!equalObjects) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return true;
    }
    
    @Override
    public boolean equalMultiMaps(final MostlySingularMultiMap mostlySingularMultiMap, final MostlySingularMultiMap mostlySingularMultiMap2) {
        try {
            if (mostlySingularMultiMap == mostlySingularMultiMap2) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0032: {
            try {
                if (mostlySingularMultiMap == null) {
                    return false;
                }
                final MostlySingularMultiMap mostlySingularMultiMap3 = mostlySingularMultiMap2;
                if (mostlySingularMultiMap3 == null) {
                    return false;
                }
                break Label_0032;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final MostlySingularMultiMap mostlySingularMultiMap3 = mostlySingularMultiMap2;
                if (mostlySingularMultiMap3 == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final Set keySet = mostlySingularMultiMap.keySet();
        final Set keySet2 = mostlySingularMultiMap2.keySet();
        try {
            if (keySet.size() != keySet2.size()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        for (final Object next : keySet) {
            try {
                if (next instanceof DeepEqual.Equality) {
                    OCLog.LOG.error("CustomObject key passed to equalMultiMaps");
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            final Iterable value = mostlySingularMultiMap.get(next);
            final Iterable value2 = mostlySingularMultiMap2.get(next);
            try {
                if (value == value2) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                if (!this.equalIterable(value, value2)) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return true;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!DeepEqualImpl.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
    
    private static class Entry
    {
        private final Object t1;
        private final Object t2;
        State myPushedState;
        private int myHash;
        
        private Entry(final Object t1, final Object t2) {
            this.t1 = t1;
            this.t2 = t2;
        }
        
        @Override
        public int hashCode() {
            if (this.myHash == 0) {
                this.myHash = System.identityHashCode(this.t1) + System.identityHashCode(this.t2);
            }
            return this.myHash;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (o.getClass() != this.getClass()) {
                return false;
            }
            final Entry entry = (Entry)o;
            return (entry.t1 == this.t1 && entry.t2 == this.t2) || (entry.t2 == this.t1 && entry.t1 == this.t2);
        }
    }
    
    private enum State
    {
        MAYBE, 
        EQUALS, 
        NOT_EQUALS;
    }
}
