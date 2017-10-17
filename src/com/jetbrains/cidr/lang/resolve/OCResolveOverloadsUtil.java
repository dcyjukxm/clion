// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityAfterResolvingVisitor;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbolAttribute;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCLiteralExpressionSymbol;
import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.types.visitors.OCSimpleTypeSubstitution;
import java.util.Set;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbol;
import com.jetbrains.cidr.lang.resolve.v2.TemplatesUtils;
import com.jetbrains.cidr.lang.resolve.v2.Conversions;
import com.jetbrains.cidr.lang.resolve.v2.ImplicitConversionSequence;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.Map;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithSubstitution;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import java.util.LinkedHashSet;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCStructType;

public class OCResolveOverloadsUtil
{
    private static final int MAX_FUNCTIONS_TO_FILTER_OUT_IDENTICAL = 6;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @Nullable
    public static OCSymbol resolveConstructorOverloads(@NotNull final OCStructType ocStructType, Collection<OCSymbol> set, @NotNull OCArgumentsList<?> argumentList, @Nullable final OCType ocType, boolean b, boolean b2, boolean b3, final boolean b4, final boolean b5, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocStructType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "resolveConstructorOverloads"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (argumentList == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "resolveConstructorOverloads"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "resolveConstructorOverloads"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        OCSymbol symbol = ocStructType.getSymbol();
        final List<OCCompoundInitializer> exprs = argumentList.getExprs();
        Label_0400: {
            Label_0350: {
                Label_0327: {
                    Label_0168: {
                        try {
                            if (exprs == null) {
                                break Label_0327;
                            }
                            final List<OCCompoundInitializer> list = exprs;
                            final int n = list.size();
                            final boolean b6 = true;
                            if (n == (b6 ? 1 : 0)) {
                                break Label_0168;
                            }
                            break Label_0327;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                        try {
                            final List<OCCompoundInitializer> list = exprs;
                            final int n = list.size();
                            final boolean b6 = true;
                            if (n != (b6 ? 1 : 0)) {
                                break Label_0327;
                            }
                            if (!(exprs.get(0) instanceof OCCompoundInitializer)) {
                                break Label_0327;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    set = new LinkedHashSet<OCSymbol>(set);
                    try {
                        if (OCCodeInsightUtil.isInitializerListType(ocStructType, ocResolveContext.getFile())) {
                            return ocStructType.getSymbol();
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                    argumentList = OCArgumentsList.getArgumentList(exprs.get(0).getInitializerExpressions(), ocResolveContext);
                    final ArrayList<OCSymbol> list2 = new ArrayList<OCSymbol>();
                    final OCSymbol a = a(ocStructType, argumentList, list2, ocResolveContext.getFile(), b4);
                    try {
                        set.removeAll(list2);
                        if (a != null) {
                            return a;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                    if (list2.size() == 1) {
                        symbol = (OCSymbol)list2.get(0);
                    }
                    try {
                        if (!b4) {
                            return symbol;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                    try {
                        if (symbol instanceof OCFunctionSymbol) {
                            break Label_0350;
                        }
                        final OCArgumentsList<OCExpression> list3 = argumentList;
                        final int n2 = list3.getCount();
                        final boolean b7 = true;
                        if (n2 == (b7 ? 1 : 0)) {
                            break Label_0350;
                        }
                        break Label_0400;
                    }
                    catch (IllegalArgumentException ex9) {
                        throw a(ex9);
                    }
                }
                try {
                    final OCArgumentsList<OCExpression> list3 = argumentList;
                    final int n2 = list3.getCount();
                    final boolean b7 = true;
                    if (n2 != (b7 ? 1 : 0)) {
                        break Label_0400;
                    }
                    if (ocStructType.checkCompatible(argumentList.getTypes().get(0), null, ocResolveContext.getElement(), b4, true, ocResolveContext).getState() != OCType.TypeCheckState.OK) {
                        break Label_0400;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
            }
            b2 = (b = true);
        }
        if (ocStructType.isMagicInside(ocResolveContext)) {
            b3 = false;
        }
        LinkedHashSet set2 = null;
        OCArgumentsList<OCExpression> list4 = null;
        OCExprValueCategory ocExprValueCategory = null;
        OCOperatorReference.OperatorPlacement operatorPlacement = null;
        boolean b8 = false;
        boolean b9 = false;
        boolean b10 = false;
        boolean b12 = false;
        Label_0457: {
            Label_0448: {
                try {
                    set2 = set;
                    list4 = argumentList;
                    ocExprValueCategory = null;
                    operatorPlacement = null;
                    b8 = b;
                    b9 = b2;
                    b10 = b3;
                    if (ocStructType.isMagicInside(ocResolveContext)) {
                        break Label_0448;
                    }
                    final OCStructType ocStructType2 = ocStructType;
                    final boolean b11 = ocStructType2.hasSeveralSpecializations();
                    if (b11) {
                        break Label_0448;
                    }
                    break Label_0448;
                }
                catch (IllegalArgumentException ex11) {
                    throw a(ex11);
                }
                try {
                    final OCStructType ocStructType2 = ocStructType;
                    final boolean b11 = ocStructType2.hasSeveralSpecializations();
                    if (b11) {
                        b12 = true;
                        break Label_0457;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
            }
            b12 = false;
        }
        final OCSymbol resolveOverloads = resolveOverloads(set2, list4, ocType, ocExprValueCategory, operatorPlacement, b8, b9, b10, b4, b12, ocResolveContext);
        Label_0481: {
            try {
                if (resolveOverloads != null) {
                    break Label_0481;
                }
                final boolean b13 = b5;
                if (b13) {
                    break Label_0481;
                }
                return symbol;
            }
            catch (IllegalArgumentException ex13) {
                throw a(ex13);
            }
            try {
                final boolean b13 = b5;
                if (b13) {
                    return resolveOverloads;
                }
            }
            catch (IllegalArgumentException ex14) {
                throw a(ex14);
            }
        }
        return symbol;
    }
    
    private static boolean a(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "hasUnevaluatedSubstitutions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Ref create = Ref.create((Object)false);
        try {
            if (ocSymbol instanceof OCSymbolWithSubstitution) {
                ((OCSymbolWithSubstitution)ocSymbol).getSubstitution().processSubstitutions((Processor<Map.Entry<OCTypeParameterSymbol, OCTypeArgument>>)(entry -> {
                    final OCTypeArgument ocTypeArgument = entry.getValue();
                    Label_0037: {
                        try {
                            if (!(ocTypeArgument instanceof OCExpressionTypeArgument)) {
                                return true;
                            }
                            final OCTypeArgument ocTypeArgument2 = ocTypeArgument;
                            final OCExpressionTypeArgument ocExpressionTypeArgument = (OCExpressionTypeArgument)ocTypeArgument2;
                            final OCExpressionSymbol ocExpressionSymbol = ocExpressionTypeArgument.getSymbol();
                            final boolean b = ocExpressionSymbol instanceof OCLiteralExpressionSymbol;
                            if (!b) {
                                break Label_0037;
                            }
                            return true;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final OCTypeArgument ocTypeArgument2 = ocTypeArgument;
                            final OCExpressionTypeArgument ocExpressionTypeArgument = (OCExpressionTypeArgument)ocTypeArgument2;
                            final OCExpressionSymbol ocExpressionSymbol = ocExpressionTypeArgument.getSymbol();
                            final boolean b = ocExpressionSymbol instanceof OCLiteralExpressionSymbol;
                            if (!b) {
                                create.set((Object)true);
                                return false;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    return true;
                }));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (boolean)create.get();
    }
    
    @Nullable
    public static OCSymbol resolveOverloads(@NotNull final Collection<OCSymbol> p0, @NotNull final OCArgumentsList<?> p1, @Nullable final OCType p2, @Nullable final OCExprValueCategory p3, @Nullable final OCOperatorReference.OperatorPlacement p4, final boolean p5, final boolean p6, final boolean p7, final boolean p8, final boolean p9, @NotNull final OCResolveContext p10) {
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
        //    18: ldc             "cases"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "resolveOverloads"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "arguments"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "resolveOverloads"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           10
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "context"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "resolveOverloads"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_0        
        //   134: invokeinterface java/util/Collection.stream:()Ljava/util/stream/Stream;
        //   139: invokedynamic   test:()Ljava/util/function/Predicate;
        //   144: invokeinterface java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
        //   149: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   152: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   157: checkcast       Ljava/util/Collection;
        //   160: astore          11
        //   162: aload_0        
        //   163: invokeinterface java/util/Collection.isEmpty:()Z
        //   168: ifne            200
        //   171: aload           11
        //   173: invokeinterface java/util/Collection.isEmpty:()Z
        //   178: ifeq            200
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: aload_0        
        //   189: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/Collection;)Ljava/lang/Object;
        //   192: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   195: areturn        
        //   196: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: iload           9
        //   202: aload_1        
        //   203: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.hasNonExpandedVariadics:()Z
        //   206: ior            
        //   207: istore          9
        //   209: iload           5
        //   211: aload           10
        //   213: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isInSFINAE:()Z
        //   216: ior            
        //   217: istore          5
        //   219: aload_2        
        //   220: ifnull          234
        //   223: aload_2        
        //   224: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   227: goto            235
        //   230: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   233: athrow         
        //   234: aconst_null    
        //   235: astore          12
        //   237: aload_0        
        //   238: aload_1        
        //   239: aload           12
        //   241: aload           4
        //   243: iload           6
        //   245: iload           8
        //   247: aload           10
        //   249: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/util/Collection;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;ZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   252: astore          13
        //   254: aload_3        
        //   255: ifnonnull       262
        //   258: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.XValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   261: astore_3       
        //   262: iload           5
        //   264: ifne            371
        //   267: aload           13
        //   269: invokeinterface java/util/List.size:()I
        //   274: iconst_1       
        //   275: if_icmpne       298
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: aload           13
        //   287: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   290: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   293: areturn        
        //   294: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: aload           13
        //   300: invokeinterface java/util/List.size:()I
        //   305: ifne            371
        //   308: iload           6
        //   310: ifeq            328
        //   313: goto            320
        //   316: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   319: athrow         
        //   320: aconst_null    
        //   321: goto            370
        //   324: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   327: athrow         
        //   328: new             Ljava/util/ArrayList;
        //   331: dup            
        //   332: aload_0        
        //   333: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
        //   336: aload           10
        //   338: iload           7
        //   340: ifeq            363
        //   343: iload           9
        //   345: ifne            363
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: iconst_1       
        //   356: goto            364
        //   359: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   362: athrow         
        //   363: iconst_0       
        //   364: getstatic       com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause.NoViable:Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;
        //   367: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZLcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   370: areturn        
        //   371: new             Ljava/util/ArrayList;
        //   374: dup            
        //   375: aload           13
        //   377: invokeinterface java/util/List.size:()I
        //   382: invokespecial   java/util/ArrayList.<init>:(I)V
        //   385: astore          14
        //   387: aload           13
        //   389: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   394: astore          15
        //   396: aload           15
        //   398: invokeinterface java/util/Iterator.hasNext:()Z
        //   403: ifeq            1204
        //   406: aload           15
        //   408: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   413: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   416: astore          16
        //   418: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   421: new             Ljava/util/ArrayList;
        //   424: dup            
        //   425: aload_1        
        //   426: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getCount:()I
        //   429: invokespecial   java/util/ArrayList.<init>:(I)V
        //   432: astore          18
        //   434: aload           16
        //   436: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   439: ifeq            746
        //   442: aload           16
        //   444: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   447: astore          19
        //   449: aload           19
        //   451: aload           4
        //   453: aload           10
        //   455: aload           12
        //   457: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.getParameterTypes:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Ljava/util/List;
        //   460: astore          17
        //   462: aload           19
        //   464: aload           10
        //   466: iconst_0       
        //   467: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   470: astore          20
        //   472: aload           20
        //   474: ifnull          510
        //   477: aload           20
        //   479: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   482: ifeq            510
        //   485: goto            492
        //   488: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   491: athrow         
        //   492: aload           18
        //   494: invokestatic    com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.magic:()Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   497: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   502: pop            
        //   503: goto            743
        //   506: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   509: athrow         
        //   510: aload_2        
        //   511: ifnull          743
        //   514: aload           19
        //   516: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //   519: ifne            743
        //   522: goto            529
        //   525: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   528: athrow         
        //   529: aload           20
        //   531: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   534: ifeq            743
        //   537: goto            544
        //   540: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   543: athrow         
        //   544: aload           20
        //   546: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   549: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   552: astore          21
        //   554: aconst_null    
        //   555: astore          22
        //   557: aload_2        
        //   558: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   561: ifeq            572
        //   564: aload_2        
        //   565: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   568: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   571: astore_2       
        //   572: aload_2        
        //   573: aload           10
        //   575: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   578: ifne            629
        //   581: aload_2        
        //   582: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   585: ifeq            612
        //   588: goto            595
        //   591: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   594: athrow         
        //   595: aload_2        
        //   596: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   599: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.hasSeveralSpecializations:()Z
        //   602: ifne            629
        //   605: goto            612
        //   608: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   611: athrow         
        //   612: aload           21
        //   614: aload           10
        //   616: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   619: ifeq            637
        //   622: goto            629
        //   625: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   628: athrow         
        //   629: invokestatic    com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.magic:()Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   632: astore          22
        //   634: goto            706
        //   637: aload           19
        //   639: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriendOrStatic:()Z
        //   642: ifne            706
        //   645: aload_2        
        //   646: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   649: ifne            693
        //   652: goto            659
        //   655: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   658: athrow         
        //   659: aload_2        
        //   660: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   663: ifeq            396
        //   666: goto            673
        //   669: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   672: athrow         
        //   673: aload_2        
        //   674: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   677: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   680: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   683: ifne            693
        //   686: goto            396
        //   689: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   692: athrow         
        //   693: aload           21
        //   695: aload_2        
        //   696: aload           19
        //   698: aload_3        
        //   699: aload           10
        //   701: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.TryObjectArgumentInitialization:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   704: astore          22
        //   706: aload           22
        //   708: ifnull          743
        //   711: aload           22
        //   713: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isBad:()Z
        //   716: ifeq            733
        //   719: goto            726
        //   722: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   725: athrow         
        //   726: goto            396
        //   729: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   732: athrow         
        //   733: aload           18
        //   735: aload           22
        //   737: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   742: pop            
        //   743: goto            775
        //   746: aload           16
        //   748: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   751: ifeq            770
        //   754: aload           16
        //   756: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   759: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   762: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   765: astore          17
        //   767: goto            775
        //   770: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   773: astore          17
        //   775: iconst_0       
        //   776: istore          19
        //   778: iload           19
        //   780: aload_1        
        //   781: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getCount:()I
        //   784: if_icmpge       1071
        //   787: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   790: iload           19
        //   792: aload           17
        //   794: invokeinterface java/util/List.size:()I
        //   799: if_icmplt       819
        //   802: goto            809
        //   805: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   808: athrow         
        //   809: invokestatic    com/jetbrains/cidr/lang/types/OCEllipsisType.instance:()Lcom/jetbrains/cidr/lang/types/OCEllipsisType;
        //   812: goto            836
        //   815: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   818: athrow         
        //   819: aload           17
        //   821: iload           19
        //   823: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   828: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   831: aload           10
        //   833: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   836: astore          20
        //   838: aload_1        
        //   839: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getTypes:()Ljava/util/List;
        //   842: iload           19
        //   844: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   849: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   852: astore          21
        //   854: aload           20
        //   856: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   859: ifeq            869
        //   862: goto            1065
        //   865: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   868: athrow         
        //   869: aload           21
        //   871: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isUnresolvedLambdaAutoType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   874: ifeq            948
        //   877: aload           20
        //   879: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   882: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   885: ifeq            948
        //   888: goto            895
        //   891: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   894: athrow         
        //   895: aload           20
        //   897: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   900: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   903: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //   906: astore          22
        //   908: aload           21
        //   910: aload           10
        //   912: new             Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;
        //   915: dup            
        //   916: aload           22
        //   918: aconst_null    
        //   919: invokespecial   com/jetbrains/cidr/lang/resolve/OCArgumentsList.<init>:(Ljava/util/List;Ljava/util/List;)V
        //   922: iconst_1       
        //   923: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.resolveLambdaAutoType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   926: astore          21
        //   928: aload           21
        //   930: ifnull          396
        //   933: aload           21
        //   935: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   938: if_acmpne       948
        //   941: goto            396
        //   944: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   947: athrow         
        //   948: aload           20
        //   950: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   953: ifeq            991
        //   956: aload           21
        //   958: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   961: ifeq            991
        //   964: goto            971
        //   967: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   970: athrow         
        //   971: aload           20
        //   973: checkcast       Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   976: invokevirtual   com/jetbrains/cidr/lang/types/OCVariadicType.getUnderlyingType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   979: astore          20
        //   981: aload           21
        //   983: checkcast       Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   986: invokevirtual   com/jetbrains/cidr/lang/types/OCVariadicType.getUnderlyingType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   989: astore          21
        //   991: aload_1        
        //   992: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getExprs:()Ljava/util/List;
        //   995: ifnull          1019
        //   998: aload_1        
        //   999: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getExprs:()Ljava/util/List;
        //  1002: iload           19
        //  1004: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  1009: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //  1012: goto            1020
        //  1015: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1018: athrow         
        //  1019: aconst_null    
        //  1020: astore          22
        //  1022: aload           22
        //  1024: aload           21
        //  1026: aload           20
        //  1028: iload           8
        //  1030: iconst_1       
        //  1031: iconst_0       
        //  1032: iconst_0       
        //  1033: aload           10
        //  1035: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.calculateConversion:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //  1038: astore          23
        //  1040: aload           18
        //  1042: aload           23
        //  1044: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1049: pop            
        //  1050: aload           23
        //  1052: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isBad:()Z
        //  1055: ifeq            1065
        //  1058: goto            396
        //  1061: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1064: athrow         
        //  1065: iinc            19, 1
        //  1068: goto            778
        //  1071: ldc             "operator++"
        //  1073: aload           16
        //  1075: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //  1080: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1083: ifne            1108
        //  1086: ldc             "operator--"
        //  1088: aload           16
        //  1090: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //  1095: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1098: ifeq            1182
        //  1101: goto            1108
        //  1104: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1107: athrow         
        //  1108: aload           4
        //  1110: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.POSTFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
        //  1113: if_acmpne       1144
        //  1116: goto            1123
        //  1119: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1122: athrow         
        //  1123: aload           17
        //  1125: invokeinterface java/util/List.size:()I
        //  1130: aload_1        
        //  1131: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getCount:()I
        //  1134: if_icmpeq       396
        //  1137: goto            1144
        //  1140: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1143: athrow         
        //  1144: aload           4
        //  1146: getstatic       com/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement.PREFIX:Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;
        //  1149: if_acmpne       1182
        //  1152: aload           17
        //  1154: invokeinterface java/util/List.size:()I
        //  1159: aload_1        
        //  1160: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getCount:()I
        //  1163: iconst_1       
        //  1164: iadd           
        //  1165: if_icmpne       1182
        //  1168: goto            1175
        //  1171: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1174: athrow         
        //  1175: goto            396
        //  1178: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1181: athrow         
        //  1182: aload           14
        //  1184: new             Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload;
        //  1187: dup            
        //  1188: aload           16
        //  1190: aload           18
        //  1192: invokespecial   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/util/List;)V
        //  1195: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //  1200: pop            
        //  1201: goto            396
        //  1204: aconst_null    
        //  1205: astore          15
        //  1207: aload           14
        //  1209: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1214: astore          16
        //  1216: aload           16
        //  1218: invokeinterface java/util/Iterator.hasNext:()Z
        //  1223: ifeq            1298
        //  1226: aload           16
        //  1228: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1233: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload;
        //  1236: astore          17
        //  1238: iload           9
        //  1240: aload           17
        //  1242: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload.myHasMagic:Z
        //  1245: ior            
        //  1246: istore          9
        //  1248: aload           15
        //  1250: ifnull          1291
        //  1253: aload           17
        //  1255: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload.myHasMagic:Z
        //  1258: ifne            1291
        //  1261: goto            1268
        //  1264: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1267: athrow         
        //  1268: aload           17
        //  1270: aload           15
        //  1272: aload_1        
        //  1273: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getCount:()I
        //  1276: aload           10
        //  1278: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload;Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload;ILcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1281: ifeq            1295
        //  1284: goto            1291
        //  1287: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1290: athrow         
        //  1291: aload           17
        //  1293: astore          15
        //  1295: goto            1216
        //  1298: aload           15
        //  1300: ifnonnull       1385
        //  1303: iload           5
        //  1305: ifeq            1321
        //  1308: goto            1315
        //  1311: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1314: athrow         
        //  1315: aconst_null    
        //  1316: areturn        
        //  1317: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1320: athrow         
        //  1321: aload           13
        //  1323: invokeinterface java/util/List.size:()I
        //  1328: iconst_1       
        //  1329: if_icmple       1376
        //  1332: aload           13
        //  1334: aload           10
        //  1336: iload           7
        //  1338: ifeq            1368
        //  1341: goto            1348
        //  1344: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1347: athrow         
        //  1348: iload           9
        //  1350: ifne            1368
        //  1353: goto            1360
        //  1356: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1359: athrow         
        //  1360: iconst_1       
        //  1361: goto            1369
        //  1364: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1367: athrow         
        //  1368: iconst_0       
        //  1369: getstatic       com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause.NoViable:Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;
        //  1372: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZLcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1375: areturn        
        //  1376: aload           13
        //  1378: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //  1381: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1384: areturn        
        //  1385: new             Ljava/util/ArrayList;
        //  1388: dup            
        //  1389: invokespecial   java/util/ArrayList.<init>:()V
        //  1392: astore          16
        //  1394: aload           14
        //  1396: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1401: astore          17
        //  1403: aload           17
        //  1405: invokeinterface java/util/Iterator.hasNext:()Z
        //  1410: ifeq            1546
        //  1413: aload           17
        //  1415: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1420: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload;
        //  1423: astore          18
        //  1425: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //  1428: aload           18
        //  1430: aload           15
        //  1432: if_acmpeq       1488
        //  1435: aload           15
        //  1437: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload.myHasMagic:Z
        //  1440: ifne            1488
        //  1443: goto            1450
        //  1446: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1449: athrow         
        //  1450: aload           18
        //  1452: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload.myHasMagic:Z
        //  1455: ifne            1488
        //  1458: goto            1465
        //  1461: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1464: athrow         
        //  1465: aload           15
        //  1467: aload           18
        //  1469: aload_1        
        //  1470: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getCount:()I
        //  1473: aload           10
        //  1475: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload;Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload;ILcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1478: ifne            1543
        //  1481: goto            1488
        //  1484: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1487: athrow         
        //  1488: aload           16
        //  1490: aload           18
        //  1492: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1495: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //  1498: pop            
        //  1499: aload           18
        //  1501: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1504: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithSubstitution;
        //  1507: ifeq            1543
        //  1510: goto            1517
        //  1513: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1516: athrow         
        //  1517: aload           18
        //  1519: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1522: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithSubstitution;
        //  1525: aload           10
        //  1527: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.hasNonResolvedTemplateParameters:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1530: ifeq            1543
        //  1533: goto            1540
        //  1536: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1539: athrow         
        //  1540: iconst_1       
        //  1541: istore          9
        //  1543: goto            1403
        //  1546: aload           16
        //  1548: invokevirtual   java/util/ArrayList.size:()I
        //  1551: iconst_1       
        //  1552: if_icmple       1614
        //  1555: aload           16
        //  1557: aload           10
        //  1559: iload           7
        //  1561: ifeq            1591
        //  1564: goto            1571
        //  1567: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1570: athrow         
        //  1571: iload           9
        //  1573: ifne            1591
        //  1576: goto            1583
        //  1579: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1582: athrow         
        //  1583: iconst_1       
        //  1584: goto            1592
        //  1587: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1590: athrow         
        //  1591: iconst_0       
        //  1592: iload           9
        //  1594: ifeq            1607
        //  1597: getstatic       com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause.Magic:Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;
        //  1600: goto            1610
        //  1603: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1606: athrow         
        //  1607: getstatic       com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause.Ambiguous:Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;
        //  1610: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZLcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1613: areturn        
        //  1614: aload           15
        //  1616: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$Overload.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1619: areturn        
        //    Signature:
        //  (Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList<*>;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;ZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  162    181    184    188    Ljava/lang/IllegalArgumentException;
        //  171    196    196    200    Ljava/lang/IllegalArgumentException;
        //  219    230    230    234    Ljava/lang/IllegalArgumentException;
        //  262    278    281    285    Ljava/lang/IllegalArgumentException;
        //  267    294    294    298    Ljava/lang/IllegalArgumentException;
        //  298    313    316    320    Ljava/lang/IllegalArgumentException;
        //  308    324    324    328    Ljava/lang/IllegalArgumentException;
        //  328    348    351    355    Ljava/lang/IllegalArgumentException;
        //  343    359    359    363    Ljava/lang/IllegalArgumentException;
        //  472    485    488    492    Ljava/lang/IllegalArgumentException;
        //  477    506    506    510    Ljava/lang/IllegalArgumentException;
        //  510    522    525    529    Ljava/lang/IllegalArgumentException;
        //  514    537    540    544    Ljava/lang/IllegalArgumentException;
        //  572    588    591    595    Ljava/lang/IllegalArgumentException;
        //  581    605    608    612    Ljava/lang/IllegalArgumentException;
        //  595    622    625    629    Ljava/lang/IllegalArgumentException;
        //  637    652    655    659    Ljava/lang/IllegalArgumentException;
        //  645    666    669    673    Ljava/lang/IllegalArgumentException;
        //  673    689    689    693    Ljava/lang/IllegalArgumentException;
        //  706    719    722    726    Ljava/lang/IllegalArgumentException;
        //  711    729    729    733    Ljava/lang/IllegalArgumentException;
        //  778    802    805    809    Ljava/lang/IllegalArgumentException;
        //  787    815    815    819    Ljava/lang/IllegalArgumentException;
        //  854    865    865    869    Ljava/lang/IllegalArgumentException;
        //  869    888    891    895    Ljava/lang/IllegalArgumentException;
        //  933    944    944    948    Ljava/lang/IllegalArgumentException;
        //  948    964    967    971    Ljava/lang/IllegalArgumentException;
        //  991    1015   1015   1019   Ljava/lang/IllegalArgumentException;
        //  1040   1061   1061   1065   Ljava/lang/IllegalArgumentException;
        //  1071   1101   1104   1108   Ljava/lang/IllegalArgumentException;
        //  1086   1116   1119   1123   Ljava/lang/IllegalArgumentException;
        //  1108   1137   1140   1144   Ljava/lang/IllegalArgumentException;
        //  1144   1168   1171   1175   Ljava/lang/IllegalArgumentException;
        //  1152   1178   1178   1182   Ljava/lang/IllegalArgumentException;
        //  1248   1261   1264   1268   Ljava/lang/IllegalArgumentException;
        //  1253   1284   1287   1291   Ljava/lang/IllegalArgumentException;
        //  1298   1308   1311   1315   Ljava/lang/IllegalArgumentException;
        //  1303   1317   1317   1321   Ljava/lang/IllegalArgumentException;
        //  1321   1341   1344   1348   Ljava/lang/IllegalArgumentException;
        //  1332   1353   1356   1360   Ljava/lang/IllegalArgumentException;
        //  1348   1364   1364   1368   Ljava/lang/IllegalArgumentException;
        //  1425   1443   1446   1450   Ljava/lang/IllegalArgumentException;
        //  1435   1458   1461   1465   Ljava/lang/IllegalArgumentException;
        //  1450   1481   1484   1488   Ljava/lang/IllegalArgumentException;
        //  1465   1510   1513   1517   Ljava/lang/IllegalArgumentException;
        //  1488   1533   1536   1540   Ljava/lang/IllegalArgumentException;
        //  1546   1564   1567   1571   Ljava/lang/IllegalArgumentException;
        //  1555   1576   1579   1583   Ljava/lang/IllegalArgumentException;
        //  1571   1587   1587   1591   Ljava/lang/IllegalArgumentException;
        //  1592   1603   1603   1607   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0595:
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
    
    private static boolean a(@NotNull final Overload overload, @NotNull final Overload overload2, final int n, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (overload == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "overload1", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "isBetterOverload"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (overload2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "overload2", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "isBetterOverload"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "isBetterOverload"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCSymbol mySymbol = overload.mySymbol;
        final OCSymbol mySymbol2 = overload2.mySymbol;
        boolean b = false;
        int n2 = 0;
        int n3 = 0;
        Label_0259: {
            if (overload.myConversionSequences.size() == overload2.myConversionSequences.size() + 1) {
                n2 = 1;
            }
            else if (overload.myConversionSequences.size() + 1 == overload2.myConversionSequences.size()) {
                n3 = 1;
            }
            else {
                Label_0245: {
                    try {
                        if (OCResolveOverloadsUtil.$assertionsDisabled) {
                            break Label_0259;
                        }
                        final Overload overload3 = overload;
                        final List<ImplicitConversionSequence> list = overload3.myConversionSequences;
                        final int n4 = list.size();
                        final Overload overload4 = overload2;
                        final List<ImplicitConversionSequence> list2 = overload4.myConversionSequences;
                        final int n5 = list2.size();
                        if (n4 != n5) {
                            break Label_0245;
                        }
                        break Label_0259;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final Overload overload3 = overload;
                        final List<ImplicitConversionSequence> list = overload3.myConversionSequences;
                        final int n4 = list.size();
                        final Overload overload4 = overload2;
                        final List<ImplicitConversionSequence> list2 = overload4.myConversionSequences;
                        final int n5 = list2.size();
                        if (n4 != n5) {
                            throw new AssertionError((Object)"Incorrect num of conversions");
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
            }
            while (true) {
                Label_0361: {
                    Label_0352: {
                        try {
                            if (n2 >= overload.myConversionSequences.size()) {
                                break Label_0361;
                            }
                            switch (Conversions.CompareImplicitConversionSequences(overload.myConversionSequences.get(n2), overload2.myConversionSequences.get(n3), ocResolveContext)) {
                                case Better: {
                                    break;
                                }
                                case Worse: {
                                    return false;
                                }
                                default: {
                                    break Label_0352;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        b = true;
                    }
                    ++n2;
                    ++n3;
                    continue;
                    try {
                        if (b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                boolean b3 = false;
                Label_0407: {
                    Label_0398: {
                        try {
                            if (!(mySymbol instanceof OCFunctionSymbol)) {
                                break Label_0398;
                            }
                            final OCSymbol ocSymbol = mySymbol;
                            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                            final boolean b2 = ocFunctionSymbol.isTemplateSymbol();
                            if (b2) {
                                break Label_0398;
                            }
                            break Label_0398;
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                        try {
                            final OCSymbol ocSymbol = mySymbol;
                            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                            final boolean b2 = ocFunctionSymbol.isTemplateSymbol();
                            if (b2) {
                                b3 = true;
                                break Label_0407;
                            }
                        }
                        catch (IllegalArgumentException ex9) {
                            throw a(ex9);
                        }
                    }
                    b3 = false;
                }
                final boolean b4 = b3;
                boolean b6 = false;
                Label_0444: {
                    Label_0435: {
                        try {
                            if (!(mySymbol2 instanceof OCFunctionSymbol)) {
                                break Label_0435;
                            }
                            final OCSymbol ocSymbol2 = mySymbol2;
                            final OCFunctionSymbol ocFunctionSymbol2 = (OCFunctionSymbol)ocSymbol2;
                            final boolean b5 = ocFunctionSymbol2.isTemplateSymbol();
                            if (b5) {
                                break Label_0435;
                            }
                            break Label_0435;
                        }
                        catch (IllegalArgumentException ex10) {
                            throw a(ex10);
                        }
                        try {
                            final OCSymbol ocSymbol2 = mySymbol2;
                            final OCFunctionSymbol ocFunctionSymbol2 = (OCFunctionSymbol)ocSymbol2;
                            final boolean b5 = ocFunctionSymbol2.isTemplateSymbol();
                            if (b5) {
                                b6 = true;
                                break Label_0444;
                            }
                        }
                        catch (IllegalArgumentException ex11) {
                            throw a(ex11);
                        }
                    }
                    b6 = false;
                }
                final boolean b7 = b6;
                try {
                    if (b4 != b7) {
                        return b7;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
                try {
                    if (!b4 || !b7) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex13) {
                    throw a(ex13);
                }
                final OCTemplateSymbol moreSpecializedTemplate;
                final OCTemplateSymbol ocTemplateSymbol = moreSpecializedTemplate = TemplatesUtils.getMoreSpecializedTemplate((OCFunctionSymbol)mySymbol, (OCFunctionSymbol)mySymbol2, n, ocResolveContext);
                Label_0512: {
                    try {
                        if (ocTemplateSymbol == null) {
                            return false;
                        }
                        final OCTemplateSymbol ocTemplateSymbol2 = moreSpecializedTemplate;
                        final OCFunctionSymbol ocFunctionSymbol3 = (OCFunctionSymbol)mySymbol;
                        if (ocTemplateSymbol2 == ocFunctionSymbol3) {
                            break Label_0512;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex14) {
                        throw a(ex14);
                    }
                    try {
                        final OCTemplateSymbol ocTemplateSymbol2 = moreSpecializedTemplate;
                        final OCFunctionSymbol ocFunctionSymbol3 = (OCFunctionSymbol)mySymbol;
                        if (ocTemplateSymbol2 == ocFunctionSymbol3) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex15) {
                        throw a(ex15);
                    }
                }
                return false;
            }
        }
    }
    
    @NotNull
    public static List<OCType> getParameterTypes(@NotNull final OCFunctionSymbol ocFunctionSymbol, @Nullable final OCOperatorReference.OperatorPlacement operatorPlacement, @NotNull final OCResolveContext ocResolveContext, @Nullable final CVQualifiers cvQualifiers) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "function", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "getParameterTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "getParameterTypes"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Object expandVariadicTypes = OCArgumentsList.expandVariadicTypes(ocFunctionSymbol.getType().getParameterTypes(), ocResolveContext);
        if (a(ocFunctionSymbol, operatorPlacement, ocResolveContext)) {
            final ArrayList list = new ArrayList<OCType>(((List)expandVariadicTypes).size() + 1);
            OCStructType type = ((OCStructSymbol)ocFunctionSymbol.getResolvedOwner(ocResolveContext, false)).getType();
            if (cvQualifiers != null) {
                type = (OCStructType)type.cloneWithAddedCVQualifiers(cvQualifiers, ocResolveContext.getProject());
            }
            list.add(type);
            list.addAll((Collection<?>)expandVariadicTypes);
            expandVariadicTypes = list;
        }
        List<?> list2;
        try {
            list2 = (List<?>)expandVariadicTypes;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "getParameterTypes"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (List<OCType>)list2;
    }
    
    private static boolean a(final OCFunctionSymbol ocFunctionSymbol, @Nullable final OCOperatorReference.OperatorPlacement operatorPlacement, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "needsPseudoParamAdded"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0063: {
            try {
                if (operatorPlacement == null) {
                    return false;
                }
                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocFunctionSymbol2.isCppMemberOperator(ocResolveContext2);
                if (b) {
                    break Label_0063;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = ocFunctionSymbol2.isCppMemberOperator(ocResolveContext2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static int b(final OCFunctionSymbol ocFunctionSymbol, @Nullable final OCOperatorReference.OperatorPlacement operatorPlacement, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "getNonInitializedParametersCount"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int nonInitializedParametersCount = ocFunctionSymbol.getNonInitializedParametersCount(ocResolveContext);
        try {
            if (a(ocFunctionSymbol, operatorPlacement, ocResolveContext)) {
                ++nonInitializedParametersCount;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return nonInitializedParametersCount;
    }
    
    private static OCSymbol a(final Collection<OCSymbol> collection) {
        for (final OCSymbol ocSymbol : collection) {
            try {
                if (!(ocSymbol instanceof OCFunctionSymbol)) {
                    continue;
                }
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = ocSymbol2.isPredeclaration();
                if (!b) {
                    return ocSymbol;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = ocSymbol2.isPredeclaration();
                if (!b) {
                    return ocSymbol;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        for (final OCSymbol ocSymbol3 : collection) {
            try {
                if (!ocSymbol3.isPredeclaration()) {
                    return ocSymbol3;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final Iterator<OCSymbol> iterator3 = (Iterator<OCSymbol>)collection.iterator();
        if (iterator3.hasNext()) {
            return iterator3.next();
        }
        return null;
    }
    
    private static OCSymbol a(final List<OCSymbol> p0, @NotNull final OCResolveContext p1, final boolean p2, final OCFunctionGroupSymbol.Cause p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "groupAmbiguousFunctions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aconst_null    
        //    45: astore          4
        //    47: aconst_null    
        //    48: astore          5
        //    50: aload_0        
        //    51: invokedynamic   compare:()Ljava/util/Comparator;
        //    56: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
        //    59: new             Ljava/util/ArrayList;
        //    62: dup            
        //    63: aload_0        
        //    64: invokeinterface java/util/List.size:()I
        //    69: invokespecial   java/util/ArrayList.<init>:(I)V
        //    72: astore          6
        //    74: aload_0        
        //    75: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    80: astore          7
        //    82: aload           7
        //    84: invokeinterface java/util/Iterator.hasNext:()Z
        //    89: ifeq            244
        //    92: aload           7
        //    94: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    99: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   102: astore          8
        //   104: aload           8
        //   106: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   109: ifeq            205
        //   112: aload           8
        //   114: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   117: astore          9
        //   119: aload           9
        //   121: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   124: astore          10
        //   126: aload           10
        //   128: ifnonnull       167
        //   131: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   134: new             Ljava/lang/StringBuilder;
        //   137: dup            
        //   138: invokespecial   java/lang/StringBuilder.<init>:()V
        //   141: ldc             "null qn "
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: aload           9
        //   148: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getName:()Ljava/lang/String;
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   157: invokevirtual   com/intellij/openapi/diagnostic/Logger.warn:(Ljava/lang/String;)V
        //   160: goto            82
        //   163: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload           6
        //   169: new             Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor;
        //   172: dup            
        //   173: aload           8
        //   175: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   178: aload           9
        //   180: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   183: aload_1        
        //   184: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   187: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   190: aload           10
        //   192: aload_1        
        //   193: invokespecial   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   196: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   201: pop            
        //   202: goto            241
        //   205: aload           8
        //   207: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   210: ifeq            241
        //   213: aload           8
        //   215: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //   220: ifeq            237
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload           8
        //   232: astore          5
        //   234: goto            241
        //   237: aload           8
        //   239: astore          4
        //   241: goto            82
        //   244: new             Ljava/util/ArrayList;
        //   247: dup            
        //   248: aload           6
        //   250: invokeinterface java/util/List.size:()I
        //   255: invokespecial   java/util/ArrayList.<init>:(I)V
        //   258: astore          7
        //   260: aload           6
        //   262: invokeinterface java/util/List.size:()I
        //   267: bipush          6
        //   269: if_icmpge       384
        //   272: aload           6
        //   274: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   279: astore          8
        //   281: aload           8
        //   283: invokeinterface java/util/Iterator.hasNext:()Z
        //   288: ifeq            381
        //   291: aload           8
        //   293: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   298: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor;
        //   301: astore          9
        //   303: iconst_1       
        //   304: istore          10
        //   306: aload           7
        //   308: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   313: astore          11
        //   315: aload           11
        //   317: invokeinterface java/util/Iterator.hasNext:()Z
        //   322: ifeq            356
        //   325: aload           11
        //   327: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   332: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor;
        //   335: astore          12
        //   337: aload           9
        //   339: aload           12
        //   341: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.isUndistinguishable:(Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor;)Z
        //   344: ifeq            353
        //   347: iconst_0       
        //   348: istore          10
        //   350: goto            356
        //   353: goto            315
        //   356: iload           10
        //   358: ifeq            378
        //   361: aload           7
        //   363: aload           9
        //   365: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   370: pop            
        //   371: goto            378
        //   374: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: goto            281
        //   381: goto            494
        //   384: aload           6
        //   386: invokeinterface java/util/List.isEmpty:()Z
        //   391: ifne            494
        //   394: aconst_null    
        //   395: astore          8
        //   397: iconst_1       
        //   398: istore          9
        //   400: aload           6
        //   402: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   407: astore          10
        //   409: aload           10
        //   411: invokeinterface java/util/Iterator.hasNext:()Z
        //   416: ifeq            462
        //   419: aload           10
        //   421: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   426: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor;
        //   429: astore          11
        //   431: aload           8
        //   433: ifnonnull       443
        //   436: aload           11
        //   438: astore          8
        //   440: goto            459
        //   443: aload           8
        //   445: aload           11
        //   447: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.isUndistinguishable:(Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor;)Z
        //   450: ifne            459
        //   453: iconst_0       
        //   454: istore          9
        //   456: goto            462
        //   459: goto            409
        //   462: iload           9
        //   464: ifeq            484
        //   467: aload           7
        //   469: aload           8
        //   471: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   476: pop            
        //   477: goto            494
        //   480: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   483: athrow         
        //   484: aload           7
        //   486: aload           6
        //   488: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   493: pop            
        //   494: aconst_null    
        //   495: astore          8
        //   497: aload           7
        //   499: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   504: astore          9
        //   506: aload           9
        //   508: invokeinterface java/util/Iterator.hasNext:()Z
        //   513: ifeq            591
        //   516: aload           9
        //   518: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   523: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor;
        //   526: astore          10
        //   528: aload           10
        //   530: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.symbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   533: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   536: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   539: aload_1        
        //   540: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   543: astore          11
        //   545: aload           8
        //   547: ifnonnull       557
        //   550: aload           11
        //   552: astore          8
        //   554: goto            588
        //   557: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor;
        //   560: dup            
        //   561: aload           8
        //   563: iconst_1       
        //   564: iconst_0       
        //   565: iconst_0       
        //   566: iconst_0       
        //   567: iconst_0       
        //   568: iconst_1       
        //   569: iconst_0       
        //   570: aload_1        
        //   571: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   574: aload           11
        //   576: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.equal:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   579: ifne            588
        //   582: aconst_null    
        //   583: astore          8
        //   585: goto            591
        //   588: goto            506
        //   591: aload           7
        //   593: invokeinterface java/util/List.size:()I
        //   598: iconst_1       
        //   599: if_icmpne       621
        //   602: aload           7
        //   604: iconst_0       
        //   605: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   610: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor;
        //   613: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.symbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   616: areturn        
        //   617: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   620: athrow         
        //   621: aload           7
        //   623: invokeinterface java/util/List.size:()I
        //   628: iconst_1       
        //   629: if_icmple       690
        //   632: iload_2        
        //   633: ifeq            651
        //   636: goto            643
        //   639: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   642: athrow         
        //   643: aconst_null    
        //   644: goto            689
        //   647: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   650: athrow         
        //   651: new             Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   654: dup            
        //   655: aload           7
        //   657: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   662: invokedynamic   apply:()Ljava/util/function/Function;
        //   667: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   672: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   675: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   680: checkcast       Ljava/util/List;
        //   683: aload           8
        //   685: aload_3        
        //   686: invokespecial   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol.<init>:(Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;)V
        //   689: areturn        
        //   690: aload           4
        //   692: ifnull          702
        //   695: aload           4
        //   697: areturn        
        //   698: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   701: athrow         
        //   702: aload           5
        //   704: ifnull          714
        //   707: aload           5
        //   709: areturn        
        //   710: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   713: athrow         
        //   714: aconst_null    
        //   715: areturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZLcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol$Cause;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  126    163    163    167    Ljava/lang/IllegalArgumentException;
        //  205    223    226    230    Ljava/lang/IllegalArgumentException;
        //  356    371    374    378    Ljava/lang/IllegalArgumentException;
        //  462    480    480    484    Ljava/lang/IllegalArgumentException;
        //  591    617    617    621    Ljava/lang/IllegalArgumentException;
        //  621    636    639    643    Ljava/lang/IllegalArgumentException;
        //  632    647    647    651    Ljava/lang/IllegalArgumentException;
        //  690    698    698    702    Ljava/lang/IllegalArgumentException;
        //  702    710    710    714    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: (p0:FuncDescriptor) -> return:OCFunctionSymbol(getfield:OCFunctionSymbol(FuncDescriptor::symbol, p0:FuncDescriptor))
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.GotoRemoval.traverseGraph(GotoRemoval.java:88)
        //     at com.strobel.decompiler.ast.GotoRemoval.removeGotos(GotoRemoval.java:52)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:294)
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
    
    private static OCSymbol a(final OCStructType ocStructType, @NotNull final OCArgumentsList<?> list, final Collection<OCSymbol> collection, @Nullable final PsiFile psiFile, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "findCtorWithInitializerList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Ref ref = new Ref();
        final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)psiFile);
        ocStructType.processMembers(ocStructType.getSymbol().getName(), (Processor<OCSymbol>)(ocSymbol -> {
            try {
                if (list == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil", "lambda$findCtorWithInitializerList$4"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            if (ocSymbol instanceof OCFunctionSymbol) {
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                try {
                    if (!ocFunctionSymbol.isCppConstructor() || ocFunctionSymbol.getNonInitializedParametersCount(ocResolveContext) != 1) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                OCType ocType = ocFunctionSymbol.getParameterSymbols().get(0).getType().resolve(psiFile);
                if (ocType instanceof OCCppReferenceType) {
                    ocType = ((OCCppReferenceType)ocType).getRefType();
                }
                if (ocType instanceof OCStructType) {
                    final OCStructSymbol symbol = ((OCStructType)ocType).getSymbol();
                    final List<OCTypeParameterSymbol> templateParameters = symbol.getTemplateParameters();
                    Label_0342: {
                        Label_0309: {
                            try {
                                if (!OCCodeInsightUtil.isInitializerListType(ocType, psiFile) || templateParameters.size() != 1) {
                                    break Label_0309;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                            collection.add(ocFunctionSymbol);
                            final OCTypeArgument substitution = symbol.getSubstitution().getSubstitutionFor(templateParameters.get(0));
                            if (substitution instanceof OCType) {
                                boolean b2 = true;
                                for (int i = 0; i < list.getExprs().size(); ++i) {
                                    b2 &= ((OCType)substitution).isCompatible(list.getTypes().get(i), list.getExprs().get(i), (PsiElement)psiFile);
                                }
                                try {
                                    if (b2) {
                                        ref.set((Object)ocFunctionSymbol);
                                        return false;
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                            }
                            try {
                                if (!b) {
                                    return true;
                                }
                                final OCStructType ocStructType = (OCStructType)ocType;
                                final OCStructType ocStructType2 = ocStructType;
                                final OCArgumentsList<OCTypeOwner> list2 = (OCArgumentsList<OCTypeOwner>)list;
                                final ArrayList<OCSymbol> list3 = new ArrayList<OCSymbol>();
                                final PsiFile psiFile2 = psiFile;
                                final boolean b3 = false;
                                final OCSymbol ocSymbol2 = a(ocStructType2, list2, list3, psiFile2, b3);
                                if (ocSymbol2 != null) {
                                    break Label_0342;
                                }
                                return true;
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            final OCStructType ocStructType = (OCStructType)ocType;
                            final OCStructType ocStructType2 = ocStructType;
                            final OCArgumentsList<OCTypeOwner> list2 = (OCArgumentsList<OCTypeOwner>)list;
                            final ArrayList<OCSymbol> list3 = new ArrayList<OCSymbol>();
                            final PsiFile psiFile2 = psiFile;
                            final boolean b3 = false;
                            final OCSymbol ocSymbol2 = a(ocStructType2, list2, list3, psiFile2, b3);
                            if (ocSymbol2 != null) {
                                ref.set((Object)ocFunctionSymbol);
                                return false;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                    }
                }
            }
            return true;
        }), ocResolveContext);
        return (OCSymbol)ref.get();
    }
    
    private static <E extends OCTypeOwner> List<OCSymbol> a(@NotNull final Collection<OCSymbol> p0, @NotNull final OCArgumentsList<E> p1, @Nullable final CVQualifiers p2, @Nullable final OCOperatorReference.OperatorPlacement p3, final boolean p4, final boolean p5, @NotNull final OCResolveContext p6) {
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
        //    18: ldc             "symbols"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "resolveTemplateSpecializations"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "arguments"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "resolveTemplateSpecializations"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           6
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "context"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "resolveTemplateSpecializations"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: new             Ljava/util/ArrayList;
        //   136: dup            
        //   137: invokespecial   java/util/ArrayList.<init>:()V
        //   140: astore          7
        //   142: new             Ljava/util/ArrayList;
        //   145: dup            
        //   146: invokespecial   java/util/ArrayList.<init>:()V
        //   149: astore          8
        //   151: aload_1        
        //   152: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getCount:()I
        //   155: istore          9
        //   157: aload_0        
        //   158: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   163: astore          10
        //   165: aload           10
        //   167: invokeinterface java/util/Iterator.hasNext:()Z
        //   172: ifeq            735
        //   175: aload           10
        //   177: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   182: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   185: astore          11
        //   187: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   190: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.newTypeParameterMap:()Ljava/util/Map;
        //   193: astore          12
        //   195: new             Lcom/intellij/util/containers/HashSet;
        //   198: dup            
        //   199: invokespecial   com/intellij/util/containers/HashSet.<init>:()V
        //   202: astore          13
        //   204: aload           11
        //   206: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   209: ifeq            436
        //   212: aload           11
        //   214: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   217: astore          14
        //   219: aload           14
        //   221: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isTemplateSymbol:()Z
        //   224: ifeq            249
        //   227: aload           14
        //   229: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isExtern:()Z
        //   232: ifeq            249
        //   235: goto            242
        //   238: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   241: athrow         
        //   242: goto            165
        //   245: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aload           14
        //   251: aload_3        
        //   252: aload           6
        //   254: aload_2        
        //   255: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.getParameterTypes:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Ljava/util/List;
        //   258: astore          15
        //   260: aload           15
        //   262: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getNonVariadicParametersCount:(Ljava/util/List;)I
        //   265: istore          16
        //   267: aload           14
        //   269: aload_3        
        //   270: aload           6
        //   272: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.b:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)I
        //   275: iload           9
        //   277: if_icmple       294
        //   280: aload_1        
        //   281: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.hasNonExpandedVariadics:()Z
        //   284: ifeq            433
        //   287: goto            294
        //   290: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   293: athrow         
        //   294: iload           9
        //   296: iload           16
        //   298: if_icmple       323
        //   301: goto            308
        //   304: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   307: athrow         
        //   308: aload           14
        //   310: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isVararg:()Z
        //   313: ifeq            433
        //   316: goto            323
        //   319: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: aload           15
        //   325: aconst_null    
        //   326: aload_1        
        //   327: aload           6
        //   329: aload           6
        //   331: aload           12
        //   333: aload           13
        //   335: iload           5
        //   337: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Ljava/util/Map;Ljava/util/Set;Z)Lcom/jetbrains/cidr/lang/resolve/OCFunctionArgumentsProcessor$ArgumentsProcessor;
        //   342: invokestatic    com/jetbrains/cidr/lang/resolve/OCFunctionArgumentsProcessor.processArguments:(Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/resolve/OCFunctionArgumentsProcessor$ArgumentsProcessor;)Z
        //   345: istore          17
        //   347: aload           14
        //   349: aload           12
        //   351: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Ljava/util/Map;)V
        //   354: aload           12
        //   356: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.create:(Ljava/util/Map;)Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //   359: astore          18
        //   361: iload           17
        //   363: ifeq            390
        //   366: aload           14
        //   368: aload           18
        //   370: aload           13
        //   372: aload           6
        //   374: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;Ljava/util/Set;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   377: ifeq            390
        //   380: goto            387
        //   383: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: iconst_0       
        //   388: istore          17
        //   390: aload           18
        //   392: aload           11
        //   394: aload           6
        //   396: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   399: astore          19
        //   401: aload           8
        //   403: aload           19
        //   405: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   410: pop            
        //   411: iload           17
        //   413: ifeq            433
        //   416: aload           7
        //   418: aload           19
        //   420: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   425: pop            
        //   426: goto            433
        //   429: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   432: athrow         
        //   433: goto            732
        //   436: aload           11
        //   438: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   441: ifeq            614
        //   444: iload           9
        //   446: iconst_1       
        //   447: if_icmpne       614
        //   450: goto            457
        //   453: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   456: athrow         
        //   457: aload           11
        //   459: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   462: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.hasDeclaredConstructor:()Z
        //   465: ifne            614
        //   468: goto            475
        //   471: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   474: athrow         
        //   475: aload           11
        //   477: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   480: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isPredefinition:()Z
        //   483: ifne            614
        //   486: goto            493
        //   489: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   492: athrow         
        //   493: aload_1        
        //   494: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getTypes:()Ljava/util/List;
        //   497: iconst_0       
        //   498: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   503: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   506: astore          14
        //   508: aload_1        
        //   509: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getExprs:()Ljava/util/List;
        //   512: ifnull          535
        //   515: aload_1        
        //   516: invokevirtual   com/jetbrains/cidr/lang/resolve/OCArgumentsList.getExprs:()Ljava/util/List;
        //   519: iconst_0       
        //   520: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   525: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   528: goto            536
        //   531: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   534: athrow         
        //   535: aconst_null    
        //   536: astore          15
        //   538: aload           11
        //   540: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   545: aload           14
        //   547: aload           15
        //   549: aload           12
        //   551: aload           13
        //   553: iconst_1       
        //   554: iconst_0       
        //   555: aload           6
        //   557: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Ljava/util/Map;Ljava/util/Set;ZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   560: astore          16
        //   562: aload           12
        //   564: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.create:(Ljava/util/Map;)Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //   567: aload           11
        //   569: aload           6
        //   571: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   574: astore          17
        //   576: aload           8
        //   578: aload           17
        //   580: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   585: pop            
        //   586: aload           16
        //   588: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   591: if_acmpeq       611
        //   594: aload           7
        //   596: aload           17
        //   598: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   603: pop            
        //   604: goto            611
        //   607: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   610: athrow         
        //   611: goto            732
        //   614: aload           11
        //   616: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   619: ifeq            687
        //   622: iload           9
        //   624: ifne            687
        //   627: goto            634
        //   630: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   633: athrow         
        //   634: aload           11
        //   636: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   639: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.hasDeclaredConstructor:()Z
        //   642: ifne            687
        //   645: goto            652
        //   648: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   651: athrow         
        //   652: aload           11
        //   654: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   657: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isPredefinition:()Z
        //   660: ifne            687
        //   663: goto            670
        //   666: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   669: athrow         
        //   670: aload           7
        //   672: aload           11
        //   674: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   679: pop            
        //   680: goto            732
        //   683: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   686: athrow         
        //   687: aload           11
        //   689: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   692: ifne            732
        //   695: aload           11
        //   697: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   702: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_TYPE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   705: if_acmpeq       732
        //   708: goto            715
        //   711: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   714: athrow         
        //   715: aload           7
        //   717: aload           11
        //   719: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   724: pop            
        //   725: goto            732
        //   728: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   731: athrow         
        //   732: goto            165
        //   735: aload           7
        //   737: invokeinterface java/util/List.isEmpty:()Z
        //   742: ifeq            766
        //   745: iload           4
        //   747: ifne            766
        //   750: goto            757
        //   753: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   756: athrow         
        //   757: aload           8
        //   759: goto            768
        //   762: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   765: athrow         
        //   766: aload           7
        //   768: areturn        
        //    Signature:
        //  <E::Lcom/jetbrains/cidr/lang/types/OCTypeOwner;>(Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList<TE;>;Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;ZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  219    235    238    242    Ljava/lang/IllegalArgumentException;
        //  227    245    245    249    Ljava/lang/IllegalArgumentException;
        //  267    287    290    294    Ljava/lang/IllegalArgumentException;
        //  280    301    304    308    Ljava/lang/IllegalArgumentException;
        //  294    316    319    323    Ljava/lang/IllegalArgumentException;
        //  361    380    383    387    Ljava/lang/IllegalArgumentException;
        //  401    426    429    433    Ljava/lang/IllegalArgumentException;
        //  436    450    453    457    Ljava/lang/IllegalArgumentException;
        //  444    468    471    475    Ljava/lang/IllegalArgumentException;
        //  457    486    489    493    Ljava/lang/IllegalArgumentException;
        //  508    531    531    535    Ljava/lang/IllegalArgumentException;
        //  576    604    607    611    Ljava/lang/IllegalArgumentException;
        //  614    627    630    634    Ljava/lang/IllegalArgumentException;
        //  622    645    648    652    Ljava/lang/IllegalArgumentException;
        //  634    663    666    670    Ljava/lang/IllegalArgumentException;
        //  652    683    683    687    Ljava/lang/IllegalArgumentException;
        //  687    708    711    715    Ljava/lang/IllegalArgumentException;
        //  695    725    728    732    Ljava/lang/IllegalArgumentException;
        //  735    750    753    757    Ljava/lang/IllegalArgumentException;
        //  745    762    762    766    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0294:
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
    
    private static void a(final OCFunctionSymbol ocFunctionSymbol, final Map<OCTypeParameterSymbol, OCTypeArgument> map) {
        for (final OCTypeParameterSymbol<OCTypeArgument> ocTypeParameterSymbol : ocFunctionSymbol.getTemplateParameters()) {
            Label_0055: {
                try {
                    if (ocTypeParameterSymbol.getDefaultValue() == null) {
                        continue;
                    }
                    final Map<OCTypeParameterSymbol<OCTypeArgument>, OCTypeArgument> map2 = (Map<OCTypeParameterSymbol<OCTypeArgument>, OCTypeArgument>)map;
                    final OCTypeParameterSymbol<OCTypeArgument> ocTypeParameterSymbol2 = ocTypeParameterSymbol;
                    final boolean b = map2.containsKey(ocTypeParameterSymbol2);
                    if (!b) {
                        break Label_0055;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final Map<OCTypeParameterSymbol<OCTypeArgument>, OCTypeArgument> map2 = (Map<OCTypeParameterSymbol<OCTypeArgument>, OCTypeArgument>)map;
                    final OCTypeParameterSymbol<OCTypeArgument> ocTypeParameterSymbol2 = ocTypeParameterSymbol;
                    final boolean b = map2.containsKey(ocTypeParameterSymbol2);
                    if (b) {
                        continue;
                    }
                    map.put(ocTypeParameterSymbol, ocTypeParameterSymbol.getDefaultValue());
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
    }
    
    private static <T> boolean a(final Set<T> set, final Collection<T> collection) {
        for (final T next : collection) {
            try {
                if (set.contains(next)) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return false;
    }
    
    private static boolean a(final OCFunctionSymbol p0, final OCSimpleTypeSubstitution p1, final Set<OCTypeParameterSymbol> p2, @NotNull final OCResolveContext p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isPrunedBySFINAE"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_3        
        //    45: aload_1        
        //    46: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.substituteFirst:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    49: astore_3       
        //    50: aload_3        
        //    51: aload_0        
        //    52: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.useFor:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    55: astore_3       
        //    56: aload_3        
        //    57: iconst_1       
        //    58: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.setInSFINAEFlag:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //    61: istore          4
        //    63: aload_0        
        //    64: aload_3        
        //    65: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //    68: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    73: astore          5
        //    75: aload           5
        //    77: invokeinterface java/util/Iterator.hasNext:()Z
        //    82: ifeq            133
        //    85: aload           5
        //    87: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    92: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    95: astore          6
        //    97: aload           6
        //    99: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   102: aload_3        
        //   103: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   106: astore          7
        //   108: aload           7
        //   110: aload_3        
        //   111: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   114: ifeq            130
        //   117: iconst_1       
        //   118: istore          8
        //   120: aload_3        
        //   121: iload           4
        //   123: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.setInSFINAEFlag:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   126: pop            
        //   127: iload           8
        //   129: ireturn        
        //   130: goto            75
        //   133: aload_0        
        //   134: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getEffectiveType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   137: aload_3        
        //   138: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   141: astore          5
        //   143: aload           5
        //   145: aload_3        
        //   146: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   149: ifeq            165
        //   152: iconst_1       
        //   153: istore          6
        //   155: aload_3        
        //   156: iload           4
        //   158: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.setInSFINAEFlag:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   161: pop            
        //   162: iload           6
        //   164: ireturn        
        //   165: aload_0        
        //   166: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getTemplateParameters:()Ljava/util/List;
        //   169: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   174: astore          6
        //   176: aload           6
        //   178: invokeinterface java/util/Iterator.hasNext:()Z
        //   183: ifeq            403
        //   186: aload           6
        //   188: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   193: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   196: astore          7
        //   198: aload           7
        //   200: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.getDefaultValue:()Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   205: astore          8
        //   207: aload           8
        //   209: ifnonnull       295
        //   212: aload_2        
        //   213: aload           7
        //   215: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   220: ifne            295
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload           7
        //   232: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.isVariadic:()Z
        //   237: ifne            295
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: aload_0        
        //   248: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   251: aload           7
        //   253: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   256: ifnonnull       295
        //   259: goto            266
        //   262: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: aload_1        
        //   267: aload           7
        //   269: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   272: ifnonnull       295
        //   275: goto            282
        //   278: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   281: athrow         
        //   282: iconst_1       
        //   283: istore          9
        //   285: aload_3        
        //   286: iload           4
        //   288: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.setInSFINAEFlag:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   291: pop            
        //   292: iload           9
        //   294: ireturn        
        //   295: aload           7
        //   297: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //   300: ifeq            339
        //   303: aload           7
        //   305: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //   308: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   311: aload_3        
        //   312: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   315: astore          9
        //   317: aload           9
        //   319: aload_3        
        //   320: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   323: ifeq            339
        //   326: iconst_1       
        //   327: istore          10
        //   329: aload_3        
        //   330: iload           4
        //   332: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.setInSFINAEFlag:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   335: pop            
        //   336: iload           10
        //   338: ireturn        
        //   339: aload           8
        //   341: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   344: ifeq            400
        //   347: aload           8
        //   349: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   352: aload_3        
        //   353: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   356: astore          9
        //   358: aload           9
        //   360: aload_3        
        //   361: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   364: ifeq            400
        //   367: aload_2        
        //   368: aload_3        
        //   369: aload           9
        //   371: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getTypeDependencies:(Lcom/jetbrains/cidr/lang/types/OCType;)Ljava/util/Set;
        //   374: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/util/Set;Ljava/util/Collection;)Z
        //   377: ifne            400
        //   380: goto            387
        //   383: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   386: athrow         
        //   387: iconst_1       
        //   388: istore          10
        //   390: aload_3        
        //   391: iload           4
        //   393: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.setInSFINAEFlag:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   396: pop            
        //   397: iload           10
        //   399: ireturn        
        //   400: goto            176
        //   403: iconst_0       
        //   404: istore          6
        //   406: aload_3        
        //   407: iload           4
        //   409: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.setInSFINAEFlag:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   412: pop            
        //   413: iload           6
        //   415: ireturn        
        //   416: astore          11
        //   418: aload_3        
        //   419: iload           4
        //   421: invokestatic    com/jetbrains/cidr/lang/symbols/OCResolveContext.setInSFINAEFlag:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   424: pop            
        //   425: aload           11
        //   427: athrow         
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;Ljava/util/Set<Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  63     120    416    428    Any
        //  130    155    416    428    Any
        //  247    275    278    282    Ljava/lang/IllegalArgumentException;
        //  230    259    262    266    Ljava/lang/IllegalArgumentException;
        //  212    240    243    247    Ljava/lang/IllegalArgumentException;
        //  207    223    226    230    Ljava/lang/IllegalArgumentException;
        //  165    285    416    428    Any
        //  295    329    416    428    Any
        //  358    380    383    387    Ljava/lang/IllegalArgumentException;
        //  339    390    416    428    Any
        //  400    406    416    428    Any
        //  416    418    416    428    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0230:
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCResolveOverloadsUtil.class.desiredAssertionStatus()) {
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class OCFunctionGroupSymbol extends OCFunctionSymbol
    {
        private List<OCFunctionSymbol> myOverloads;
        private Cause myCause;
        
        public OCFunctionGroupSymbol(@NotNull final List<OCFunctionSymbol> myOverloads, @Nullable final OCType ocType, @NotNull final Cause myCause) {
            if (myOverloads == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "overloads", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol", "<init>"));
            }
            if (myCause == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cause", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol", "<init>"));
            }
            super(myOverloads.get(0).getProject(), myOverloads.get(0).getContainingFile(), 0L, myOverloads.get(0).getParent(), myOverloads.get(0).getQualifiedName(), (List<OCTypeParameterSymbol>)Collections.emptyList(), Collections.emptyList(), 0, OCSymbolAttribute.STATIC.getMask(), Collections.emptyList(), a(myOverloads, ocType), a(myOverloads), a(myOverloads, OCSymbolKind.FUNCTION_PREDECLARATION), null);
            this.myOverloads = myOverloads;
            this.myCause = myCause;
        }
        
        private static List<OCDeclaratorSymbol> a(final List<OCFunctionSymbol> list) {
            return list.get(0).getParameterSymbols();
        }
        
        private static OCFunctionType a(final List<OCFunctionSymbol> list, @Nullable OCType ocType) {
            if (ocType == null) {
                ocType = new OCMagicType(list.get(0).getType().getReturnType());
            }
            return new OCFunctionType(ocType, (List<OCType>)Collections.singletonList(OCEllipsisType.instance()));
        }
        
        private static OCSymbolKind a(final List<OCFunctionSymbol> list, final OCSymbolKind ocSymbolKind) {
            OCSymbolKind kind = null;
            for (final OCFunctionSymbol ocFunctionSymbol : list) {
                if (kind == null) {
                    kind = ocFunctionSymbol.getKind();
                }
                else {
                    try {
                        if (!ocFunctionSymbol.getKind().isSame(kind)) {
                            return ocSymbolKind;
                        }
                        continue;
                    }
                    catch (UnsupportedOperationException ex) {
                        throw b(ex);
                    }
                }
            }
            try {
                if (kind != null) {
                    return kind;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            return ocSymbolKind;
        }
        
        public List<OCFunctionSymbol> getOverloads() {
            return this.myOverloads;
        }
        
        public Cause getCause() {
            return this.myCause;
        }
        
        @Override
        public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
            try {
                if (comparator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol", "deepEqualStep"));
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                if (o == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol", "deepEqualStep"));
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            try {
                if (o2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol", "deepEqualStep"));
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
            throw new UnsupportedOperationException("this symbol is synthetic and should not be interned");
        }
        
        private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
            return ex;
        }
        
        public enum Cause
        {
            Ambiguous, 
            NoViable, 
            Magic;
        }
    }
    
    private static class Overload
    {
        OCSymbol mySymbol;
        List<ImplicitConversionSequence> myConversionSequences;
        boolean myHasMagic;
        
        public Overload(final OCSymbol mySymbol, final List<ImplicitConversionSequence> myConversionSequences) {
            this.mySymbol = mySymbol;
            this.myConversionSequences = myConversionSequences;
            this.myHasMagic = (mySymbol.getKind().isTemplateParameter() || myConversionSequences.stream().anyMatch(implicitConversionSequence -> implicitConversionSequence.hasMagic()) || a(mySymbol));
        }
    }
    
    private static class FuncDescriptor
    {
        @NotNull
        public final OCFunctionSymbol symbol;
        @NotNull
        private final OCFunctionType myResolvedType;
        @NotNull
        private final OCQualifiedName myName;
        @NotNull
        private final OCTypeEqualityAfterResolvingVisitor myVisitor;
        
        public FuncDescriptor(@NotNull final OCFunctionSymbol symbol, @NotNull final OCFunctionType myResolvedType, @NotNull final OCQualifiedName myName, @NotNull final OCResolveContext ocResolveContext) {
            if (symbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor", "<init>"));
            }
            if (myResolvedType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor", "<init>"));
            }
            if (myName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor", "<init>"));
            }
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor", "<init>"));
            }
            this.symbol = symbol;
            this.myResolvedType = myResolvedType;
            this.myName = myName;
            this.myVisitor = new OCTypeEqualityAfterResolvingVisitor(this.myResolvedType, false, ocResolveContext);
        }
        
        public boolean isUndistinguishable(@NotNull final FuncDescriptor p0) {
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
            //    18: ldc             "other"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "isUndistinguishable"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.symbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //    48: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriend:()Z
            //    51: ifne            92
            //    54: aload_1        
            //    55: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.symbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //    58: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriend:()Z
            //    61: ifne            92
            //    64: goto            71
            //    67: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    70: athrow         
            //    71: aload_0        
            //    72: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.myName:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //    75: aload_1        
            //    76: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.myName:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //    79: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.equals:(Ljava/lang/Object;)Z
            //    82: ifeq            121
            //    85: goto            92
            //    88: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    91: athrow         
            //    92: aload_0        
            //    93: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.myVisitor:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor;
            //    96: aload_1        
            //    97: getfield        com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.myResolvedType:Lcom/jetbrains/cidr/lang/types/OCFunctionType;
            //   100: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.isFunctionSignatureEqual:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
            //   103: ifeq            121
            //   106: goto            113
            //   109: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   112: athrow         
            //   113: iconst_1       
            //   114: goto            122
            //   117: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$FuncDescriptor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   120: athrow         
            //   121: iconst_0       
            //   122: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     64     67     71     Ljava/lang/IllegalArgumentException;
            //  54     85     88     92     Ljava/lang/IllegalArgumentException;
            //  71     106    109    113    Ljava/lang/IllegalArgumentException;
            //  92     117    117    121    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0071:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
}
