// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCLambdaExpressionSymbol;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.visitors.OCNonPrimitiveTypeCloneVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCBooleanTypeVisitor;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.visitors.OCArrayToPointerChanger;
import com.intellij.openapi.project.Project;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCGenericParameterSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.ConcurrentMap;
import com.intellij.util.containers.ContainerUtil;
import gnu.trove.THashMap;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.Map;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithSubstitution;
import gnu.trove.THashSet;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import gnu.trove.TObjectHashingStrategy;
import java.util.Set;

public class OCTypeUtils
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public static <T extends OCTypeArgument> Set<T> newTypeSet() {
        return (Set<T>)new THashSet((TObjectHashingStrategy)new TObjectHashingStrategy<T>() {
            public int computeHashCode(final T t) {
                return t.hashCode();
            }
            
            public boolean equals(final T t, final T t2) {
                return DeepEqual.equalObjects(t, t2);
            }
        });
    }
    
    public static <T> Set<T> newSymbolWithSubstitutionSet() {
        return (Set<T>)new THashSet((TObjectHashingStrategy)new TObjectHashingStrategy<T>() {
            public int computeHashCode(final T t) {
                return t.hashCode() * 31 + ((t instanceof OCSymbolWithSubstitution) ? ((OCSymbolWithSubstitution)t).getSubstitution().hashCode() : 0);
            }
            
            public boolean equals(final T t, final T t2) {
                return t.equals(t2) && (!(t instanceof OCSymbolWithSubstitution) || !(t2 instanceof OCSymbolWithSubstitution) || DeepEqual.equalObjects(((OCSymbolWithSubstitution)t).getSubstitution(), ((OCSymbolWithSubstitution)t2).getSubstitution()));
            }
        });
    }
    
    public static Set<Pair<OCSymbolReference, OCTypeSubstitution>> newReferenceWithSubstitutionSet() {
        return (Set<Pair<OCSymbolReference, OCTypeSubstitution>>)new THashSet((TObjectHashingStrategy)new TObjectHashingStrategy<Pair<OCSymbolReference, OCTypeSubstitution>>() {
            public int computeHashCode(final Pair<OCSymbolReference, OCTypeSubstitution> pair) {
                return pair.hashCode();
            }
            
            public boolean equals(final Pair<OCSymbolReference, OCTypeSubstitution> pair, final Pair<OCSymbolReference, OCTypeSubstitution> pair2) {
                return DeepEqual.equalObjects(pair, pair2);
            }
        });
    }
    
    public static Map<OCTypeParameterSymbol, OCTypeArgument> newTypeParameterMap() {
        return (Map<OCTypeParameterSymbol, OCTypeArgument>)new THashMap((TObjectHashingStrategy)new TObjectHashingStrategy<OCTypeParameterSymbol>() {
            public int computeHashCode(final OCTypeParameterSymbol ocTypeParameterSymbol) {
                return ocTypeParameterSymbol.hashCode();
            }
            
            public boolean equals(final OCTypeParameterSymbol ocTypeParameterSymbol, final OCTypeParameterSymbol ocTypeParameterSymbol2) {
                return DeepEqual.equalObjects(ocTypeParameterSymbol, ocTypeParameterSymbol2);
            }
        });
    }
    
    public static Map<OCType, OCType> newTypesMap() {
        return (Map<OCType, OCType>)new THashMap((TObjectHashingStrategy)new TObjectHashingStrategy<OCType>() {
            public int computeHashCode(final OCType ocType) {
                return ocType.hashCode();
            }
            
            public boolean equals(final OCType ocType, final OCType ocType2) {
                return DeepEqual.equalObjects(ocType, ocType2);
            }
        });
    }
    
    @NotNull
    public static <TKey, TValue> Map<TKey, TValue> newDeepEqualityMap() {
        final TObjectHashingStrategy<TKey> tObjectHashingStrategy = (TObjectHashingStrategy<TKey>)new TObjectHashingStrategy<TKey>() {
            public int computeHashCode(final TKey tKey) {
                return tKey.hashCode();
            }
            
            public boolean equals(final TKey tKey, final TKey tKey2) {
                return DeepEqual.equalObjects(tKey, tKey2);
            }
        };
        ConcurrentMap concurrentMap;
        try {
            concurrentMap = ContainerUtil.newConcurrentMap((TObjectHashingStrategy)tObjectHashingStrategy);
            if (concurrentMap == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCTypeUtils", "newDeepEqualityMap"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Map<TKey, TValue>)concurrentMap;
    }
    
    public static boolean areSignaturesEqual(@NotNull final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2, @NotNull final OCResolveContext ocResolveContext, final boolean b) {
        try {
            if (ocSymbolWithQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "s1", "com/jetbrains/cidr/lang/types/OCTypeUtils", "areSignaturesEqual"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbolWithQualifiedName2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "s2", "com/jetbrains/cidr/lang/types/OCTypeUtils", "areSignaturesEqual"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCTypeUtils", "areSignaturesEqual"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return a(ocSymbolWithQualifiedName, ocSymbolWithQualifiedName2, ocResolveContext, b, false);
    }
    
    public static boolean isSignaturesConformsTo(@NotNull final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2, @NotNull final OCResolveContext ocResolveContext, final boolean b) {
        try {
            if (ocSymbolWithQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "s1", "com/jetbrains/cidr/lang/types/OCTypeUtils", "isSignaturesConformsTo"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbolWithQualifiedName2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "s2", "com/jetbrains/cidr/lang/types/OCTypeUtils", "isSignaturesConformsTo"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCTypeUtils", "isSignaturesConformsTo"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return a(ocSymbolWithQualifiedName, ocSymbolWithQualifiedName2, ocResolveContext, b, true);
    }
    
    private static boolean a(@NotNull final OCSymbolWithQualifiedName p0, @NotNull final OCSymbolWithQualifiedName p1, @NotNull final OCResolveContext p2, final boolean p3, final boolean p4) {
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
        //    18: ldc             "s1"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCTypeUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "areSignaturesEqual"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "s2"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/types/OCTypeUtils"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "areSignaturesEqual"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "context"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/types/OCTypeUtils"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "areSignaturesEqual"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   131: athrow         
        //   132: aload_0        
        //   133: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   136: ifeq            166
        //   139: aload_0        
        //   140: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   143: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.isTemplateSymbol:()Z
        //   148: ifeq            166
        //   151: goto            158
        //   154: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   157: athrow         
        //   158: iconst_1       
        //   159: goto            167
        //   162: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   165: athrow         
        //   166: iconst_0       
        //   167: aload_1        
        //   168: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   171: ifeq            201
        //   174: aload_1        
        //   175: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   178: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.isTemplateSymbol:()Z
        //   183: ifeq            201
        //   186: goto            193
        //   189: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   192: athrow         
        //   193: iconst_1       
        //   194: goto            202
        //   197: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   200: athrow         
        //   201: iconst_0       
        //   202: if_icmpeq       211
        //   205: iconst_0       
        //   206: ireturn        
        //   207: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   210: athrow         
        //   211: aload_0        
        //   212: aload_2        
        //   213: iconst_0       
        //   214: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   217: astore          5
        //   219: aload_1        
        //   220: aload_2        
        //   221: iconst_0       
        //   222: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   225: astore          6
        //   227: aload           5
        //   229: ifnull          261
        //   232: aload           6
        //   234: ifnull          261
        //   237: goto            244
        //   240: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   243: athrow         
        //   244: aload           5
        //   246: aload           6
        //   248: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.equals:(Ljava/lang/Object;)Z
        //   251: ifne            267
        //   254: goto            261
        //   257: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   260: athrow         
        //   261: iconst_0       
        //   262: ireturn        
        //   263: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   266: athrow         
        //   267: aload_0        
        //   268: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   271: aload_1        
        //   272: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   275: if_acmpne       314
        //   278: aload_0        
        //   279: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   282: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   285: ifeq            314
        //   288: goto            295
        //   291: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   294: athrow         
        //   295: aload_0        
        //   296: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   299: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   302: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   307: goto            317
        //   310: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   313: athrow         
        //   314: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   317: astore          7
        //   319: aload_0        
        //   320: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   323: aload_1        
        //   324: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   327: if_acmpne       366
        //   330: aload_1        
        //   331: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   334: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   337: ifeq            366
        //   340: goto            347
        //   343: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   346: athrow         
        //   347: aload_1        
        //   348: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   351: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   354: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   359: goto            369
        //   362: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   365: athrow         
        //   366: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   369: astore          8
        //   371: aload_0        
        //   372: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   375: ifeq            394
        //   378: aload_0        
        //   379: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   382: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   387: goto            397
        //   390: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   393: athrow         
        //   394: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   397: astore          9
        //   399: aload_1        
        //   400: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   403: ifeq            422
        //   406: aload_1        
        //   407: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   410: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   415: goto            425
        //   418: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   421: athrow         
        //   422: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   425: astore          10
        //   427: aload           9
        //   429: invokeinterface java/util/List.size:()I
        //   434: aload           10
        //   436: invokeinterface java/util/List.size:()I
        //   441: if_icmpeq       450
        //   444: iconst_0       
        //   445: ireturn        
        //   446: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   449: athrow         
        //   450: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.newTypeParameterMap:()Ljava/util/Map;
        //   453: astore          11
        //   455: aload_0        
        //   456: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   459: ifeq            476
        //   462: aload_0        
        //   463: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   466: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getTypeWithoutSubstitution:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   469: goto            480
        //   472: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   475: athrow         
        //   476: aload_0        
        //   477: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   480: aload_2        
        //   481: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   484: astore          12
        //   486: aload_1        
        //   487: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   490: ifeq            507
        //   493: aload_1        
        //   494: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   497: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getTypeWithoutSubstitution:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   500: goto            511
        //   503: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   506: athrow         
        //   507: aload_1        
        //   508: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   511: aload_2        
        //   512: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   515: astore          13
        //   517: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor;
        //   520: dup            
        //   521: iconst_0       
        //   522: iload_3        
        //   523: iconst_0       
        //   524: iconst_0       
        //   525: iconst_0       
        //   526: aload           13
        //   528: aconst_null    
        //   529: aload           11
        //   531: aconst_null    
        //   532: aload_2        
        //   533: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.<init>:(ZZZZZLcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Ljava/util/Map;Ljava/util/Set;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   536: aload           12
        //   538: aload           13
        //   540: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   543: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.isUnified:()Z
        //   546: ifne            555
        //   549: iconst_0       
        //   550: ireturn        
        //   551: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   554: athrow         
        //   555: iload           4
        //   557: ifeq            566
        //   560: iconst_1       
        //   561: ireturn        
        //   562: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   565: athrow         
        //   566: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor;
        //   569: dup            
        //   570: iconst_0       
        //   571: iload_3        
        //   572: iconst_0       
        //   573: iconst_0       
        //   574: iconst_0       
        //   575: aload           12
        //   577: aconst_null    
        //   578: new             Ljava/util/HashMap;
        //   581: dup            
        //   582: invokespecial   java/util/HashMap.<init>:()V
        //   585: aconst_null    
        //   586: aload_2        
        //   587: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.<init>:(ZZZZZLcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Ljava/util/Map;Ljava/util/Set;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   590: aload           13
        //   592: aload           12
        //   594: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   597: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.isUnified:()Z
        //   600: ifne            609
        //   603: iconst_0       
        //   604: ireturn        
        //   605: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   608: athrow         
        //   609: aload           11
        //   611: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   616: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   621: astore          14
        //   623: aload           14
        //   625: invokeinterface java/util/Iterator.hasNext:()Z
        //   630: ifeq            900
        //   633: aload           14
        //   635: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   640: checkcast       Ljava/util/Map$Entry;
        //   643: astore          15
        //   645: aload           15
        //   647: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   652: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   655: ifne            664
        //   658: iconst_0       
        //   659: ireturn        
        //   660: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   663: athrow         
        //   664: aload           15
        //   666: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   671: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   674: astore          16
        //   676: aload           15
        //   678: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   683: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   686: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   689: astore          17
        //   691: aload_0        
        //   692: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   695: aload_1        
        //   696: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   699: if_acmpne       773
        //   702: aload           16
        //   704: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.getName:()Ljava/lang/String;
        //   709: aload           17
        //   711: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.getName:()Ljava/lang/String;
        //   716: invokestatic    java/util/Objects.equals:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   719: ifne            773
        //   722: goto            729
        //   725: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   728: athrow         
        //   729: aload           9
        //   731: aload           16
        //   733: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   738: ifne            773
        //   741: goto            748
        //   744: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   747: athrow         
        //   748: aload           10
        //   750: aload           17
        //   752: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   757: ifne            773
        //   760: goto            767
        //   763: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   766: athrow         
        //   767: iconst_0       
        //   768: ireturn        
        //   769: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   772: athrow         
        //   773: aload           16
        //   775: ifnull          897
        //   778: aload           17
        //   780: ifnull          897
        //   783: goto            790
        //   786: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   789: athrow         
        //   790: aload           9
        //   792: aload           16
        //   794: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   799: aload           10
        //   801: aload           17
        //   803: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   808: if_icmpeq       824
        //   811: goto            818
        //   814: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   817: athrow         
        //   818: iconst_0       
        //   819: ireturn        
        //   820: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   823: athrow         
        //   824: aload           7
        //   826: aload           16
        //   828: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   833: aload           8
        //   835: aload           17
        //   837: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   842: if_icmpeq       851
        //   845: iconst_0       
        //   846: ireturn        
        //   847: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   850: athrow         
        //   851: aload           16
        //   853: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   856: ifeq            897
        //   859: aload           17
        //   861: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   864: ifeq            897
        //   867: goto            874
        //   870: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   873: athrow         
        //   874: aload           16
        //   876: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   879: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol.getQualifierTypeParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   882: astore          16
        //   884: aload           17
        //   886: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   889: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol.getQualifierTypeParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterTypeSymbol;
        //   892: astore          17
        //   894: goto            773
        //   897: goto            623
        //   900: iconst_0       
        //   901: istore          14
        //   903: iload           14
        //   905: aload           9
        //   907: invokeinterface java/util/List.size:()I
        //   912: if_icmpge       1015
        //   915: aload           9
        //   917: iload           14
        //   919: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   924: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   927: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   932: aload_2        
        //   933: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   936: astore          15
        //   938: aload           10
        //   940: iload           14
        //   942: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   947: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   950: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   955: aload_2        
        //   956: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   959: astore          16
        //   961: aload           15
        //   963: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   966: ifeq            984
        //   969: aload           16
        //   971: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   974: ifne            1009
        //   977: goto            984
        //   980: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   983: athrow         
        //   984: aload           15
        //   986: aload           16
        //   988: iconst_1       
        //   989: aload_2        
        //   990: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   993: ifne            1009
        //   996: goto            1003
        //   999: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1002: athrow         
        //  1003: iconst_0       
        //  1004: ireturn        
        //  1005: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //  1008: athrow         
        //  1009: iinc            14, 1
        //  1012: goto            903
        //  1015: iconst_1       
        //  1016: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  88     128    128    132    Ljava/lang/IllegalStateException;
        //  132    151    154    158    Ljava/lang/IllegalStateException;
        //  139    162    162    166    Ljava/lang/IllegalStateException;
        //  167    186    189    193    Ljava/lang/IllegalStateException;
        //  174    197    197    201    Ljava/lang/IllegalStateException;
        //  202    207    207    211    Ljava/lang/IllegalStateException;
        //  227    237    240    244    Ljava/lang/IllegalStateException;
        //  232    254    257    261    Ljava/lang/IllegalStateException;
        //  244    263    263    267    Ljava/lang/IllegalStateException;
        //  267    288    291    295    Ljava/lang/IllegalStateException;
        //  278    310    310    314    Ljava/lang/IllegalStateException;
        //  319    340    343    347    Ljava/lang/IllegalStateException;
        //  330    362    362    366    Ljava/lang/IllegalStateException;
        //  371    390    390    394    Ljava/lang/IllegalStateException;
        //  399    418    418    422    Ljava/lang/IllegalStateException;
        //  427    446    446    450    Ljava/lang/IllegalStateException;
        //  455    472    472    476    Ljava/lang/IllegalStateException;
        //  486    503    503    507    Ljava/lang/IllegalStateException;
        //  517    551    551    555    Ljava/lang/IllegalStateException;
        //  555    562    562    566    Ljava/lang/IllegalStateException;
        //  566    605    605    609    Ljava/lang/IllegalStateException;
        //  645    660    660    664    Ljava/lang/IllegalStateException;
        //  691    722    725    729    Ljava/lang/IllegalStateException;
        //  702    741    744    748    Ljava/lang/IllegalStateException;
        //  729    760    763    767    Ljava/lang/IllegalStateException;
        //  748    769    769    773    Ljava/lang/IllegalStateException;
        //  773    783    786    790    Ljava/lang/IllegalStateException;
        //  778    811    814    818    Ljava/lang/IllegalStateException;
        //  790    820    820    824    Ljava/lang/IllegalStateException;
        //  824    847    847    851    Ljava/lang/IllegalStateException;
        //  851    867    870    874    Ljava/lang/IllegalStateException;
        //  961    977    980    984    Ljava/lang/IllegalStateException;
        //  969    996    999    1003   Ljava/lang/IllegalStateException;
        //  984    1005   1005   1009   Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0244:
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
    
    public static OCType getExtractExpressionType(final OCType ocType, @NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCTypeUtils", "getExtractExpressionType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return getExtractExpressionType(ocType, psiElement, b, false);
    }
    
    public static OCType getExtractExpressionType(final OCType p0, @NotNull final PsiElement p1, final boolean p2, final boolean p3) {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCTypeUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getExtractExpressionType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    51: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    54: astore_0       
        //    55: aload_0        
        //    56: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    59: ifeq            89
        //    62: aload_0        
        //    63: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    66: astore          4
        //    68: aload           4
        //    70: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    73: aload           4
        //    75: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //    78: aload           4
        //    80: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    83: iconst_0       
        //    84: iconst_0       
        //    85: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/ARCAttribute;Lcom/jetbrains/cidr/lang/types/OCType;ZZ)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    88: astore_0       
        //    89: aload_0        
        //    90: iload_3        
        //    91: aload_1        
        //    92: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isPassableByReference:(Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/intellij/psi/PsiElement;)Z
        //    95: ifeq            131
        //    98: iload_2        
        //    99: ifeq            117
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   108: athrow         
        //   109: aload_0        
        //   110: goto            127
        //   113: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   116: athrow         
        //   117: aload_0        
        //   118: aload_1        
        //   119: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   124: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   127: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   130: areturn        
        //   131: iload_2        
        //   132: ifne            173
        //   135: aload_0        
        //   136: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   139: ifeq            173
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   148: athrow         
        //   149: aload_0        
        //   150: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   153: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   156: aload_1        
        //   157: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   162: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   165: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   168: areturn        
        //   169: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   172: athrow         
        //   173: iload_2        
        //   174: ifne            243
        //   177: aload_0        
        //   178: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   181: ifeq            243
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   190: athrow         
        //   191: aload_0        
        //   192: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   195: ifne            243
        //   198: goto            205
        //   201: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   204: athrow         
        //   205: aload_0        
        //   206: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   209: ifne            243
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   218: athrow         
        //   219: aload_0        
        //   220: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   223: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   226: aload_1        
        //   227: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   232: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   235: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   238: areturn        
        //   239: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   242: athrow         
        //   243: aload_0        
        //   244: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  89     102    105    109    Ljava/lang/IllegalStateException;
        //  98     113    113    117    Ljava/lang/IllegalStateException;
        //  131    142    145    149    Ljava/lang/IllegalStateException;
        //  135    169    169    173    Ljava/lang/IllegalStateException;
        //  173    184    187    191    Ljava/lang/IllegalStateException;
        //  177    198    201    205    Ljava/lang/IllegalStateException;
        //  191    212    215    219    Ljava/lang/IllegalStateException;
        //  205    239    239    243    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0191:
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
    
    public static boolean isPassableByReference(final OCType p0, final boolean p1, final PsiElement p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_1        
        //     1: ifne            52
        //     4: aload_0        
        //     5: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //     8: ifeq            38
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    17: athrow         
        //    18: aload_0        
        //    19: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    22: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    28: if_acmpne       52
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    37: athrow         
        //    38: aload_0        
        //    39: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //    42: ifeq            74
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    51: athrow         
        //    52: aload_2        
        //    53: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //    56: ifne            74
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    65: athrow         
        //    66: iconst_1       
        //    67: goto            75
        //    70: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    73: athrow         
        //    74: iconst_0       
        //    75: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      11     14     18     Ljava/lang/IllegalStateException;
        //  4      31     34     38     Ljava/lang/IllegalStateException;
        //  18     45     48     52     Ljava/lang/IllegalStateException;
        //  38     59     62     66     Ljava/lang/IllegalStateException;
        //  52     70     70     74     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    public static OCType getCollectionItemType(@NotNull final OCType ocType, @Nullable final PsiElement psiElement) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "collectionType", "com/jetbrains/cidr/lang/types/OCTypeUtils", "getCollectionItemType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        if (ocType instanceof OCObjectType) {
            final OCObjectType ocObjectType = (OCObjectType)ocType;
            final OCClassSymbol classSymbol = ocObjectType.getClassSymbol();
            if (classSymbol != null) {
                final OCProtocolSymbol ocProtocolSymbol = OCSymbolBase.findSymbolDefinition("NSFastEnumeration", OCSymbolKind.PROTOCOL, classSymbol.getProject(), classSymbol.getContainingFile());
                try {
                    if (ocProtocolSymbol == null || !ocObjectType.implementsProtocol(ocProtocolSymbol)) {
                        return null;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
                    protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
                        OCType ocType = ocMethodSymbol.getReturnType().resolve(new OCResolveContext(psiElement));
                        if (ocType instanceof OCPointerType) {
                            ocType = ((OCPointerType)ocType).getRefType();
                        }
                        return ocType instanceof OCObjectType && "NSEnumerator".equals(((OCObjectType)ocType).getClassName());
                    }
                };
                final Iterator<String> iterator = ContainerUtil.newArrayList((Object[])new String[] { "keyEnumerator", "objectEnumerator", null }).iterator();
            Label_0200:
                while (true) {
                    Label_0172: {
                        try {
                            if (findFirstProcessor.isFound()) {
                                break Label_0200;
                            }
                            final Iterator<String> iterator2 = iterator;
                            final boolean b = iterator2.hasNext();
                            if (b) {
                                break Label_0172;
                            }
                            break Label_0200;
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final Iterator<String> iterator2 = iterator;
                            final boolean b = iterator2.hasNext();
                            if (b) {
                                ocObjectType.processMembers(iterator.next(), OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor, true, false);
                                continue;
                            }
                        }
                        catch (IllegalStateException ex4) {
                            throw a(ex4);
                        }
                    }
                    break;
                }
                if (findFirstProcessor.isFound()) {
                    final OCMethodSymbol ocMethodSymbol = (OCMethodSymbol)findFirstProcessor.getFoundValue();
                    Label_0236: {
                        try {
                            if (OCTypeUtils.$assertionsDisabled) {
                                break Label_0236;
                            }
                            final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                            if (ocMethodSymbol2 == null) {
                                break Label_0236;
                            }
                            break Label_0236;
                        }
                        catch (IllegalStateException ex5) {
                            throw a(ex5);
                        }
                        try {
                            final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                            if (ocMethodSymbol2 == null) {
                                throw new AssertionError();
                            }
                        }
                        catch (IllegalStateException ex6) {
                            throw a(ex6);
                        }
                    }
                    OCType ocType2 = ocMethodSymbol.getReturnType().resolve(new OCResolveContext(psiElement));
                    if (ocType2 instanceof OCPointerType) {
                        ocType2 = ((OCPointerType)ocType2).getRefType();
                    }
                    final OCInterfaceSymbol interface1 = ((OCObjectType)ocType2).getInterface();
                    if (interface1 != null) {
                        final List<OCGenericParameterSymbol> genericParameters = interface1.getGenericParameters();
                        if (!genericParameters.isEmpty()) {
                            final OCTypeArgument substitution = interface1.getSubstitution().getSubstitutionFor(genericParameters.get(0));
                            try {
                                if (substitution instanceof OCType) {
                                    return (OCType)substitution;
                                }
                            }
                            catch (IllegalStateException ex7) {
                                throw a(ex7);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public static OCType decayType(@NotNull final OCType ocType, @NotNull final Project project) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/OCTypeUtils", "decayType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/types/OCTypeUtils", "decayType"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return decayType(ocType, project, true);
    }
    
    public static OCType decayType(@NotNull OCType ocType, @NotNull final Project project, final boolean b) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/OCTypeUtils", "decayType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/types/OCTypeUtils", "decayType"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        if (ocType instanceof OCArrayType) {
            ocType = OCArrayToPointerChanger.INSTANCE.visitArrayType((OCArrayType)ocType);
        }
        if (ocType instanceof OCCppReferenceType) {
            ocType = ((OCCppReferenceType)ocType).getRefType();
        }
        if (ocType instanceof OCFunctionType) {
            ocType = OCPointerType.to(ocType);
        }
        try {
            if (b) {
                return ocType.cloneWithoutCVQualifiers(project);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return ocType;
    }
    
    public static boolean isInstanceOfType(@Nullable final OCExpression ocExpression, @NotNull final OCResolveContext ocResolveContext, @NotNull final Class<? extends OCType>... array) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/OCTypeUtils", "isInstanceOfType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeClasses", "com/jetbrains/cidr/lang/types/OCTypeUtils", "isInstanceOfType"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return isInstanceOfType(getResolvedCppReferencedType(ocExpression, ocResolveContext), array);
    }
    
    @Nullable
    public static OCType getResolvedCppReferencedType(@Nullable final OCExpression ocExpression, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/OCTypeUtils", "getResolvedCppReferencedType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocExpression != null) {
                return getResolvedCppReferencedType(ocExpression.getType(), ocResolveContext);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @NotNull
    public static OCType getResolvedCppReferencedType(@NotNull final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/OCTypeUtils", "getResolvedCppReferencedType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/OCTypeUtils", "getResolvedCppReferencedType"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCType resolve = ocType.resolve(ocResolveContext);
        OCType cppReferencedType;
        try {
            cppReferencedType = getCppReferencedType(resolve);
            if (cppReferencedType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCTypeUtils", "getResolvedCppReferencedType"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return cppReferencedType;
    }
    
    @NotNull
    public static OCType getCppReferencedType(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/OCTypeUtils", "getCppReferencedType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0104: {
            OCType ocType3 = null;
            Label_0069: {
                try {
                    if (!(ocType instanceof OCCppReferenceType)) {
                        break Label_0104;
                    }
                    final OCType ocType2 = ocType;
                    final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)ocType2;
                    ocType3 = ocCppReferenceType.getRefType();
                    if (ocType3 == null) {
                        break Label_0069;
                    }
                    return ocType3;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCType ocType2 = ocType;
                    final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)ocType2;
                    ocType3 = ocCppReferenceType.getRefType();
                    if (ocType3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCTypeUtils", "getCppReferencedType"));
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            return ocType3;
            try {
                if (ocType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCTypeUtils", "getCppReferencedType"));
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return ocType;
    }
    
    public static boolean isInstanceOfType(@Nullable final OCType ocType, @NotNull final Class<? extends OCType>... array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeClasses", "com/jetbrains/cidr/lang/types/OCTypeUtils", "isInstanceOfType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocType == null) {
                return false;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        for (final Class<? extends OCType> clazz : array) {
            try {
                if (clazz.isInstance(ocType)) {
                    return true;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static int getNonVariadicParametersCount(final List<? extends OCType> list) {
        int n = 0;
        for (final OCType ocType : list) {
            Label_0049: {
                try {
                    if (ocType instanceof OCVariadicType) {
                        continue;
                    }
                    final OCType ocType2 = ocType;
                    final boolean b = ocType2 instanceof OCEllipsisType;
                    if (!b) {
                        break Label_0049;
                    }
                    continue;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final OCType ocType2 = ocType;
                    final boolean b = ocType2 instanceof OCEllipsisType;
                    if (b) {
                        continue;
                    }
                    ++n;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
        }
        return n;
    }
    
    public static boolean hasAutoInside(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/OCTypeUtils", "hasAutoInside"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocType.accept((OCTypeVisitor<Boolean>)new OCBooleanTypeVisitor() {
            @Override
            public Boolean visitAutoType(final OCAutoType ocAutoType) {
                return ocAutoType.getExpressionSymbol() == null && ocAutoType.getExpressionElement() == null;
            }
        });
    }
    
    @NotNull
    public static OCType replaceAutoTypesWithTypeParameters(@NotNull final OCType ocType, @NotNull final Map<OCType, OCType> map) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/OCTypeUtils", "replaceAutoTypesWithTypeParameters"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (map == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "map", "com/jetbrains/cidr/lang/types/OCTypeUtils", "replaceAutoTypesWithTypeParameters"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCType ocType2;
        try {
            ocType2 = ocType.accept((OCTypeVisitor<OCType>)new OCNonPrimitiveTypeCloneVisitor() {
                @Override
                public OCType visitAutoType(final OCAutoType ocAutoType) {
                    final OCTypeParameterType ocTypeParameterType = new OCTypeParameterType(new OCTypeParameterTypeSymbol(null, null, map.size(), "auto", null, Collections.emptyList(), null, false), ocAutoType.isConst(), ocAutoType.isVolatile());
                    map.put(ocAutoType, ocTypeParameterType);
                    return ocTypeParameterType;
                }
            });
            if (ocType2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCTypeUtils", "replaceAutoTypesWithTypeParameters"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return ocType2;
    }
    
    public static boolean isUnresolvedLambdaAutoType(@Nullable final OCType ocType) {
        Label_0024: {
            try {
                if (!(ocType instanceof OCAutoType)) {
                    return false;
                }
                final OCType ocType2 = ocType;
                final OCAutoType ocAutoType = (OCAutoType)ocType2;
                final boolean b = ocAutoType.needsAutoParamsResolving();
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCType ocType2 = ocType;
                final OCAutoType ocAutoType = (OCAutoType)ocType2;
                final boolean b = ocAutoType.needsAutoParamsResolving();
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public static OCType resolveLambdaAutoType(@NotNull final OCType ocType, @NotNull final OCResolveContext ocResolveContext, @NotNull final OCArgumentsList<? extends OCTypeOwner> list, final boolean b) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/OCTypeUtils", "resolveLambdaAutoType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCTypeUtils", "resolveLambdaAutoType"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentsList", "com/jetbrains/cidr/lang/types/OCTypeUtils", "resolveLambdaAutoType"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final OCExpressionSymbol expressionSymbol = ((OCAutoType)ocType).getExpressionSymbol();
        try {
            if (expressionSymbol instanceof OCLambdaExpressionSymbol) {
                return ((OCLambdaExpressionSymbol)expressionSymbol).getResolvedType(ocResolveContext, list, b);
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return ocType;
    }
    
    @Nullable
    public static OCType resolveLambdaAutoType(@NotNull final OCType ocType, @NotNull final OCType ocType2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lType", "com/jetbrains/cidr/lang/types/OCTypeUtils", "resolveLambdaAutoType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocType2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rType", "com/jetbrains/cidr/lang/types/OCTypeUtils", "resolveLambdaAutoType"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCTypeUtils", "resolveLambdaAutoType"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        if (ocType.getTerminalType() instanceof OCFunctionType) {
            final OCType resolveLambdaAutoType = resolveLambdaAutoType(ocType2, ocResolveContext, new OCArgumentsList<OCTypeOwner>(((OCFunctionType)ocType.getTerminalType()).getParameterTypes(), null), false);
            Label_0200: {
                try {
                    if (resolveLambdaAutoType == null) {
                        break Label_0200;
                    }
                    final OCType ocType3 = resolveLambdaAutoType;
                    final OCType ocType4 = ocType3.getTerminalType();
                    final boolean b = ocType4 instanceof OCFunctionType;
                    if (b) {
                        return resolveLambdaAutoType;
                    }
                    break Label_0200;
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCType ocType3 = resolveLambdaAutoType;
                    final OCType ocType4 = ocType3.getTerminalType();
                    final boolean b = ocType4 instanceof OCFunctionType;
                    if (b) {
                        return resolveLambdaAutoType;
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            ((OCAutoType)ocType2).setNeedsAutoParamsResolving(false);
            return ocType2.resolve(ocResolveContext);
        }
        final OCExpressionSymbol expressionSymbol = ((OCAutoType)ocType2).getExpressionSymbol();
        try {
            if (expressionSymbol != null) {
                return expressionSymbol.getResolvedType(ocResolveContext);
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
        return ocType2;
    }
    
    public static boolean isPolymorphic(@NotNull final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/OCTypeUtils", "isPolymorphic"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCTypeUtils", "isPolymorphic"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        Label_0119: {
            try {
                if (!ocType.isCppStructType()) {
                    return false;
                }
                final OCType ocType2 = ocType;
                final OCStructType ocStructType = (OCStructType)ocType2;
                final String s = null;
                final Processor processor = ocSymbol -> {
                    Label_0024: {
                        try {
                            if (!(ocSymbol instanceof OCFunctionSymbol)) {
                                break Label_0024;
                            }
                            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                            final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                            final boolean b = ocFunctionSymbol2.isVirtual();
                            if (!b) {
                                break Label_0024;
                            }
                            return false;
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        try {
                            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                            final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                            final boolean b = ocFunctionSymbol2.isVirtual();
                            if (!b) {
                                return true;
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                    }
                    return false;
                };
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocStructType.processMembers(s, (Processor<OCSymbol>)processor, ocResolveContext2);
                if (!b) {
                    break Label_0119;
                }
                return false;
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                final OCType ocType2 = ocType;
                final OCStructType ocStructType = (OCStructType)ocType2;
                final String s = null;
                final Processor processor = ocSymbol -> {
                    Label_0024: {
                        try {
                            if (!(ocSymbol instanceof OCFunctionSymbol)) {
                                break Label_0024;
                            }
                            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                            final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                            final boolean b = ocFunctionSymbol2.isVirtual();
                            if (!b) {
                                break Label_0024;
                            }
                            return false;
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        try {
                            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                            final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                            final boolean b = ocFunctionSymbol2.isVirtual();
                            if (!b) {
                                return true;
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                    }
                    return false;
                };
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocStructType.processMembers(s, (Processor<OCSymbol>)processor, ocResolveContext2);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCTypeUtils.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
