// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.types.OCTypeArgument;

public class OCTypeUnificationVisitor implements OCTypeVisitor<UnificationResult>
{
    public static final int MAX_EXPANSION_PACK_SIZE = 25;
    private static final int HANDLE_ALL_ARGUMENTS = -1;
    public static final UnificationResult NOT_UNIFIED;
    public static final UnificationResult UNIFIED;
    public static final UnificationResult UNIFIED_CONST_VALUE;
    public static final UnificationResult UNKNOWN;
    private boolean myFunctionParametersMode;
    private boolean myCheckFunctionReturnTypes;
    private boolean myVariadicMode;
    private final boolean myMagicTypesEqual;
    private final boolean myPartialOrderingMode;
    private int myRelevantFunctionArgumentsCount;
    private OCTypeArgument myArgument;
    @Nullable
    private OCTypeOwner myArgumentExpr;
    private final Map<OCTypeParameterSymbol, OCTypeArgument> mySubstitutionMap;
    @Nullable
    private Set<OCTypeParameterSymbol> myDependentTypes;
    @NotNull
    private final OCResolveContext myContext;
    
    public OCTypeUnificationVisitor(final boolean myFunctionParametersMode, final boolean myCheckFunctionReturnTypes, final boolean myVariadicMode, final boolean myMagicTypesEqual, final boolean myPartialOrderingMode, @NotNull final OCTypeArgument myArgument, @Nullable final OCTypeOwner myArgumentExpr, @NotNull final Map<OCTypeParameterSymbol, OCTypeArgument> mySubstitutionMap, @Nullable final Set<OCTypeParameterSymbol> myDependentTypes, @NotNull final OCResolveContext myContext) {
        if (myArgument == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor", "<init>"));
        }
        if (mySubstitutionMap == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitutionMap", "com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor", "<init>"));
        }
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor", "<init>"));
        }
        this.myRelevantFunctionArgumentsCount = -1;
        this.myFunctionParametersMode = myFunctionParametersMode;
        this.myCheckFunctionReturnTypes = myCheckFunctionReturnTypes;
        this.myVariadicMode = myVariadicMode;
        this.myMagicTypesEqual = myMagicTypesEqual;
        this.myPartialOrderingMode = myPartialOrderingMode;
        this.myArgument = myArgument;
        this.myArgumentExpr = myArgumentExpr;
        this.mySubstitutionMap = mySubstitutionMap;
        this.myDependentTypes = myDependentTypes;
        this.myContext = myContext;
    }
    
    public UnificationResult unify(@NotNull final OCTypeArgument p0, @NotNull final OCTypeArgument p1) {
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
        //    18: ldc             "parameter"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "unify"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "argument"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "unify"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //    92: ifeq            370
        //    95: aload_0        
        //    96: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    99: aload_0        
        //   100: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.mySubstitutionMap:Ljava/util/Map;
        //   103: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.create:(Ljava/util/Map;)Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //   106: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.substitute:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   109: astore_3       
        //   110: aload_1        
        //   111: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   114: invokevirtual   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   117: astore          4
        //   119: aload           4
        //   121: aload_3        
        //   122: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Number;
        //   125: astore          5
        //   127: aconst_null    
        //   128: astore          6
        //   130: aload           5
        //   132: ifnonnull       279
        //   135: aload           4
        //   137: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol;
        //   140: ifeq            275
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: aload           4
        //   152: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol;
        //   155: aload_0        
        //   156: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   159: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.resolveToSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   162: astore          7
        //   164: aload           7
        //   166: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //   169: ifeq            275
        //   172: aload           7
        //   174: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   177: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.isVariadic:()Z
        //   182: ifeq            255
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: aload_2        
        //   193: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   196: ifne            255
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: aload_0        
        //   207: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.mySubstitutionMap:Ljava/util/Map;
        //   210: aload           7
        //   212: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   215: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   220: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   223: astore          8
        //   225: aload           8
        //   227: ifnull          243
        //   230: aload           8
        //   232: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   235: aload_2        
        //   236: invokevirtual   com/jetbrains/cidr/lang/types/OCExpansionPackType.appendTypeArgument:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   239: astore_2       
        //   240: goto            255
        //   243: new             Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   246: dup            
        //   247: aload_2        
        //   248: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   251: invokespecial   com/jetbrains/cidr/lang/types/OCExpansionPackType.<init>:(Ljava/util/List;)V
        //   254: astore_2       
        //   255: aload_0        
        //   256: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.mySubstitutionMap:Ljava/util/Map;
        //   259: aload           7
        //   261: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   264: aload_2        
        //   265: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   270: pop            
        //   271: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   274: areturn        
        //   275: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   278: areturn        
        //   279: aload_2        
        //   280: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   283: ifeq            302
        //   286: aload_2        
        //   287: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   290: invokevirtual   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   293: aload_3        
        //   294: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Number;
        //   297: astore          6
        //   299: goto            331
        //   302: aload_2        
        //   303: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   306: ifne            331
        //   309: aload_2        
        //   310: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   313: ifne            331
        //   316: goto            323
        //   319: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   326: areturn        
        //   327: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: aload           6
        //   333: ifnull          367
        //   336: aload           5
        //   338: aload           6
        //   340: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.isSameValue:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   343: ifeq            363
        //   346: goto            353
        //   349: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   352: athrow         
        //   353: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNIFIED_CONST_VALUE:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   356: goto            366
        //   359: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   362: athrow         
        //   363: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   366: areturn        
        //   367: goto            936
        //   370: aload_1        
        //   371: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   374: ifeq            936
        //   377: aload_2        
        //   378: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   381: ifeq            395
        //   384: goto            391
        //   387: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   394: astore_2       
        //   395: aload_1        
        //   396: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   399: ifeq            444
        //   402: aload_2        
        //   403: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   406: ifne            444
        //   409: goto            416
        //   412: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   415: athrow         
        //   416: aload_1        
        //   417: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   420: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   423: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   426: ifeq            444
        //   429: goto            436
        //   432: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   435: athrow         
        //   436: aload_1        
        //   437: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   440: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   443: astore_1       
        //   444: aload_1        
        //   445: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   448: ifeq            479
        //   451: aload_1        
        //   452: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   455: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   458: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   461: ifeq            479
        //   464: goto            471
        //   467: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   470: athrow         
        //   471: aload_1        
        //   472: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   475: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   478: astore_1       
        //   479: aload_0        
        //   480: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myFunctionParametersMode:Z
        //   483: ifne            500
        //   486: aload_0        
        //   487: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myPartialOrderingMode:Z
        //   490: ifeq            634
        //   493: goto            500
        //   496: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   499: athrow         
        //   500: aload_2        
        //   501: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   504: ifeq            522
        //   507: goto            514
        //   510: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   513: athrow         
        //   514: aload_2        
        //   515: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   518: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   521: astore_2       
        //   522: aload_1        
        //   523: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   526: ifeq            634
        //   529: aload_2        
        //   530: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   533: ifeq            626
        //   536: goto            543
        //   539: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   542: athrow         
        //   543: aload_1        
        //   544: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   547: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   550: ifeq            626
        //   553: goto            560
        //   556: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   559: athrow         
        //   560: aload_1        
        //   561: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   564: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   567: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   570: ifne            626
        //   573: goto            580
        //   576: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   579: athrow         
        //   580: aload_0        
        //   581: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgumentExpr:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   584: aload_0        
        //   585: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   588: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   591: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isLValue:()Z
        //   594: ifeq            626
        //   597: goto            604
        //   600: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   603: athrow         
        //   604: aload_2        
        //   605: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   608: ifne            626
        //   611: goto            618
        //   614: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   617: athrow         
        //   618: aload_2        
        //   619: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   622: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   625: astore_2       
        //   626: aload_1        
        //   627: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   630: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   633: astore_1       
        //   634: aload_2        
        //   635: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   638: ifeq            723
        //   641: aload_0        
        //   642: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myMagicTypesEqual:Z
        //   645: ifeq            723
        //   648: goto            655
        //   651: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   654: athrow         
        //   655: aload_0        
        //   656: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myDependentTypes:Ljava/util/Set;
        //   659: ifnull          723
        //   662: goto            669
        //   665: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   668: athrow         
        //   669: aload_1        
        //   670: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   673: ifeq            707
        //   676: goto            683
        //   679: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   682: athrow         
        //   683: aload_0        
        //   684: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myDependentTypes:Ljava/util/Set;
        //   687: aload_2        
        //   688: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   691: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   694: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   699: pop            
        //   700: goto            723
        //   703: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   706: athrow         
        //   707: aload_1        
        //   708: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   711: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$1;
        //   714: dup            
        //   715: aload_0        
        //   716: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$1.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor;)V
        //   719: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //   722: pop            
        //   723: aload_0        
        //   724: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   727: astore_3       
        //   728: aload_0        
        //   729: aload_2        
        //   730: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   733: aload_0        
        //   734: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myFunctionParametersMode:Z
        //   737: ifne            915
        //   740: aload_2        
        //   741: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   744: ifeq            915
        //   747: goto            754
        //   750: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   753: athrow         
        //   754: aload_2        
        //   755: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   758: ifne            915
        //   761: goto            768
        //   764: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   767: athrow         
        //   768: aload_1        
        //   769: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   772: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   775: aload_2        
        //   776: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   779: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   782: if_icmpeq       837
        //   785: goto            792
        //   788: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   791: athrow         
        //   792: aload_1        
        //   793: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   796: ifeq            906
        //   799: goto            806
        //   802: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   805: athrow         
        //   806: aload_2        
        //   807: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   810: ifeq            837
        //   813: goto            820
        //   816: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   819: athrow         
        //   820: aload_1        
        //   821: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   824: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.isConst:()Z
        //   827: ifne            906
        //   830: goto            837
        //   833: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   836: athrow         
        //   837: aload_1        
        //   838: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   841: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   844: aload_2        
        //   845: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   848: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   851: if_icmpeq       915
        //   854: goto            861
        //   857: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   860: athrow         
        //   861: aload_1        
        //   862: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   865: ifeq            906
        //   868: goto            875
        //   871: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   874: athrow         
        //   875: aload_2        
        //   876: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   879: ifeq            915
        //   882: goto            889
        //   885: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   888: athrow         
        //   889: aload_1        
        //   890: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   893: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.isVolatile:()Z
        //   896: ifeq            915
        //   899: goto            906
        //   902: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   905: athrow         
        //   906: aload_0        
        //   907: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.b:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   910: astore          4
        //   912: goto            928
        //   915: aload_1        
        //   916: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   919: aload_0        
        //   920: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //   923: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   926: astore          4
        //   928: aload_0        
        //   929: aload_3        
        //   930: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   933: aload           4
        //   935: areturn        
        //   936: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   939: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  130    143    146    150    Ljava/lang/IllegalArgumentException;
        //  164    185    188    192    Ljava/lang/IllegalArgumentException;
        //  172    199    202    206    Ljava/lang/IllegalArgumentException;
        //  302    316    319    323    Ljava/lang/IllegalArgumentException;
        //  309    327    327    331    Ljava/lang/IllegalArgumentException;
        //  331    346    349    353    Ljava/lang/IllegalArgumentException;
        //  336    359    359    363    Ljava/lang/IllegalArgumentException;
        //  370    384    387    391    Ljava/lang/IllegalArgumentException;
        //  395    409    412    416    Ljava/lang/IllegalArgumentException;
        //  402    429    432    436    Ljava/lang/IllegalArgumentException;
        //  444    464    467    471    Ljava/lang/IllegalArgumentException;
        //  479    493    496    500    Ljava/lang/IllegalArgumentException;
        //  486    507    510    514    Ljava/lang/IllegalArgumentException;
        //  522    536    539    543    Ljava/lang/IllegalArgumentException;
        //  529    553    556    560    Ljava/lang/IllegalArgumentException;
        //  543    573    576    580    Ljava/lang/IllegalArgumentException;
        //  560    597    600    604    Ljava/lang/IllegalArgumentException;
        //  580    611    614    618    Ljava/lang/IllegalArgumentException;
        //  634    648    651    655    Ljava/lang/IllegalArgumentException;
        //  641    662    665    669    Ljava/lang/IllegalArgumentException;
        //  655    676    679    683    Ljava/lang/IllegalArgumentException;
        //  669    703    703    707    Ljava/lang/IllegalArgumentException;
        //  728    747    750    754    Ljava/lang/IllegalArgumentException;
        //  740    761    764    768    Ljava/lang/IllegalArgumentException;
        //  754    785    788    792    Ljava/lang/IllegalArgumentException;
        //  768    799    802    806    Ljava/lang/IllegalArgumentException;
        //  792    813    816    820    Ljava/lang/IllegalArgumentException;
        //  806    830    833    837    Ljava/lang/IllegalArgumentException;
        //  820    854    857    861    Ljava/lang/IllegalArgumentException;
        //  837    868    871    875    Ljava/lang/IllegalArgumentException;
        //  861    882    885    889    Ljava/lang/IllegalArgumentException;
        //  875    899    902    906    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0543:
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
    
    public static boolean isSameValue(@Nullable final Object p0, @Nullable final Object p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Ljava/lang/Boolean;
        //     4: ifeq            64
        //     7: aload_1        
        //     8: instanceof      Ljava/lang/Number;
        //    11: ifeq            64
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: checkcast       Ljava/lang/Boolean;
        //    25: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    28: aload_1        
        //    29: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //    32: ifeq            50
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: iconst_1       
        //    43: goto            51
        //    46: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: iconst_0       
        //    51: if_icmpne       62
        //    54: iconst_1       
        //    55: goto            63
        //    58: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: iconst_0       
        //    63: ireturn        
        //    64: aload_0        
        //    65: instanceof      Ljava/lang/Number;
        //    68: ifeq            128
        //    71: aload_1        
        //    72: instanceof      Ljava/lang/Boolean;
        //    75: ifeq            128
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: aload_0        
        //    86: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.singAsInC:(Ljava/lang/Object;)I
        //    89: ifeq            107
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iconst_1       
        //   100: goto            108
        //   103: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: iconst_0       
        //   108: aload_1        
        //   109: checkcast       Ljava/lang/Boolean;
        //   112: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   115: if_icmpne       126
        //   118: iconst_1       
        //   119: goto            127
        //   122: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_0       
        //   127: ireturn        
        //   128: aload_0        
        //   129: instanceof      Ljava/lang/Number;
        //   132: ifeq            180
        //   135: aload_1        
        //   136: instanceof      Ljava/lang/Number;
        //   139: ifeq            180
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   148: athrow         
        //   149: aload_0        
        //   150: invokestatic    com/jetbrains/cidr/lang/util/OCNumber.valueOf:(Ljava/lang/Object;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //   153: aload_1        
        //   154: invokestatic    com/jetbrains/cidr/lang/util/OCNumber.valueOf:(Ljava/lang/Object;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //   157: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.compareTo:(Ljava/math/BigInteger;)I
        //   160: ifne            178
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: iconst_1       
        //   171: goto            179
        //   174: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: iconst_0       
        //   179: ireturn        
        //   180: aload_0        
        //   181: aload_1        
        //   182: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   185: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      35     38     42     Ljava/lang/IllegalArgumentException;
        //  21     46     46     50     Ljava/lang/IllegalArgumentException;
        //  51     58     58     62     Ljava/lang/IllegalArgumentException;
        //  64     78     81     85     Ljava/lang/IllegalArgumentException;
        //  71     92     95     99     Ljava/lang/IllegalArgumentException;
        //  85     103    103    107    Ljava/lang/IllegalArgumentException;
        //  108    122    122    126    Ljava/lang/IllegalArgumentException;
        //  128    142    145    149    Ljava/lang/IllegalArgumentException;
        //  135    163    166    170    Ljava/lang/IllegalArgumentException;
        //  149    174    174    178    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    private boolean a() {
        Label_0027: {
            try {
                if (!(this.myArgument instanceof OCType)) {
                    break Label_0027;
                }
                final OCTypeUnificationVisitor ocTypeUnificationVisitor = this;
                final OCTypeArgument ocTypeArgument = ocTypeUnificationVisitor.myArgument;
                final boolean b = ocTypeArgument instanceof OCMagicType;
                if (b) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCTypeUnificationVisitor ocTypeUnificationVisitor = this;
                final OCTypeArgument ocTypeArgument = ocTypeUnificationVisitor.myArgument;
                final boolean b = ocTypeArgument instanceof OCMagicType;
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private UnificationResult b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myFunctionParametersMode:Z
        //     4: ifeq            56
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //    11: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //    14: ifeq            56
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //    28: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //    31: aload_0        
        //    32: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    35: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    38: ifeq            56
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //    51: areturn        
        //    52: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_0        
        //    57: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:()Z
        //    60: ifeq            91
        //    63: aload_0        
        //    64: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myMagicTypesEqual:Z
        //    67: ifeq            87
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //    80: goto            90
        //    83: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //    90: areturn        
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myFunctionParametersMode:Z
        //    95: ifeq            122
        //    98: aload_0        
        //    99: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myVariadicMode:Z
        //   102: ifne            122
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   115: goto            125
        //   118: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   125: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  7      41     44     48     Ljava/lang/IllegalArgumentException;
        //  24     52     52     56     Ljava/lang/IllegalArgumentException;
        //  56     70     73     77     Ljava/lang/IllegalArgumentException;
        //  63     83     83     87     Ljava/lang/IllegalArgumentException;
        //  91     105    108    112    Ljava/lang/IllegalArgumentException;
        //  98     118    118    122    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    public void setRelevantFunctionArgumentsCount(final int myRelevantFunctionArgumentsCount) {
        this.myRelevantFunctionArgumentsCount = myRelevantFunctionArgumentsCount;
    }
    
    @Override
    public UnificationResult visitFunctionType(final OCFunctionType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //     7: ifeq            329
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //    14: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    17: astore_2       
        //    18: aload_1        
        //    19: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //    22: astore_3       
        //    23: aload_2        
        //    24: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //    27: astore          4
        //    29: aload_1        
        //    30: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isLValueRef:()Z
        //    33: aload_2        
        //    34: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isLValueRef:()Z
        //    37: if_icmpne       58
        //    40: aload_1        
        //    41: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isRValueRef:()Z
        //    44: aload_2        
        //    45: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isRValueRef:()Z
        //    48: if_icmpeq       67
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload_0        
        //    59: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.b:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //    62: areturn        
        //    63: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_0        
        //    68: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myRelevantFunctionArgumentsCount:I
        //    71: iconst_m1      
        //    72: if_icmpeq       179
        //    75: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.$assertionsDisabled:Z
        //    78: ifne            114
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myPartialOrderingMode:Z
        //    92: ifne            114
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: new             Ljava/lang/AssertionError;
        //   105: dup            
        //   106: invokespecial   java/lang/AssertionError.<init>:()V
        //   109: athrow         
        //   110: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload           4
        //   116: invokeinterface java/util/List.size:()I
        //   121: aload_0        
        //   122: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myRelevantFunctionArgumentsCount:I
        //   125: if_icmplt       174
        //   128: aload_3        
        //   129: invokeinterface java/util/List.size:()I
        //   134: aload_0        
        //   135: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myRelevantFunctionArgumentsCount:I
        //   138: if_icmplt       174
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: aload_3        
        //   149: iconst_0       
        //   150: aload_0        
        //   151: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myRelevantFunctionArgumentsCount:I
        //   154: invokeinterface java/util/List.subList:(II)Ljava/util/List;
        //   159: astore_3       
        //   160: aload           4
        //   162: iconst_0       
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myRelevantFunctionArgumentsCount:I
        //   167: invokeinterface java/util/List.subList:(II)Ljava/util/List;
        //   172: astore          4
        //   174: aload_0        
        //   175: iconst_m1      
        //   176: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myRelevantFunctionArgumentsCount:I
        //   179: aload_3        
        //   180: invokeinterface java/util/List.size:()I
        //   185: istore          5
        //   187: aload           4
        //   189: invokeinterface java/util/List.size:()I
        //   194: istore          6
        //   196: iload           5
        //   198: iload           6
        //   200: if_icmpeq       233
        //   203: aload_1        
        //   204: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isVararg:()Z
        //   207: ifeq            329
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: iload           6
        //   219: iconst_1       
        //   220: iadd           
        //   221: iload           5
        //   223: if_icmplt       329
        //   226: goto            233
        //   229: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   236: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //   239: astore          7
        //   241: aload_0        
        //   242: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myCheckFunctionReturnTypes:Z
        //   245: ifeq            295
        //   248: aload_0        
        //   249: aload_1        
        //   250: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   253: aload_2        
        //   254: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   257: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   260: astore          8
        //   262: aload           8
        //   264: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   267: if_acmpne       277
        //   270: aload           8
        //   272: areturn        
        //   273: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: aload           7
        //   279: aload           7
        //   281: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   284: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   287: aload           8
        //   289: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.add:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   292: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   295: aload_3        
        //   296: aload           4
        //   298: aload_0        
        //   299: aload           7
        //   301: invokedynamic   process:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor;Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor$TypeArgumentsProcessor;
        //   306: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.processArguments:(Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor$TypeArgumentsProcessor;)Z
        //   309: ifne            320
        //   312: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   315: areturn        
        //   316: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   319: athrow         
        //   320: aload           7
        //   322: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   325: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   328: areturn        
        //   329: aload_0        
        //   330: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   333: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   336: ifeq            424
        //   339: aload_0        
        //   340: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   343: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   346: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.isUnresolvedLambdaAutoType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   349: ifeq            424
        //   352: goto            359
        //   355: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   358: athrow         
        //   359: aload_1        
        //   360: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //   363: astore_2       
        //   364: aload_0        
        //   365: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   368: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   371: aload_0        
        //   372: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   375: new             Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;
        //   378: dup            
        //   379: aload_2        
        //   380: aconst_null    
        //   381: invokespecial   com/jetbrains/cidr/lang/resolve/OCArgumentsList.<init>:(Ljava/util/List;Ljava/util/List;)V
        //   384: iconst_1       
        //   385: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.resolveLambdaAutoType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   388: astore_3       
        //   389: aload_3        
        //   390: ifnull          424
        //   393: aload_3        
        //   394: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   397: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   400: ifeq            424
        //   403: goto            410
        //   406: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   409: athrow         
        //   410: aload_0        
        //   411: aload_1        
        //   412: aload_3        
        //   413: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   416: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   419: areturn        
        //   420: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   423: athrow         
        //   424: aload_0        
        //   425: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.b:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   428: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  29     51     54     58     Ljava/lang/IllegalArgumentException;
        //  40     63     63     67     Ljava/lang/IllegalArgumentException;
        //  67     81     84     88     Ljava/lang/IllegalArgumentException;
        //  75     95     98     102    Ljava/lang/IllegalArgumentException;
        //  88     110    110    114    Ljava/lang/IllegalArgumentException;
        //  114    141    144    148    Ljava/lang/IllegalArgumentException;
        //  196    210    213    217    Ljava/lang/IllegalArgumentException;
        //  203    226    229    233    Ljava/lang/IllegalArgumentException;
        //  262    273    273    277    Ljava/lang/IllegalArgumentException;
        //  295    316    316    320    Ljava/lang/IllegalArgumentException;
        //  329    352    355    359    Ljava/lang/IllegalArgumentException;
        //  389    403    406    410    Ljava/lang/IllegalArgumentException;
        //  393    420    420    424    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
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
    public UnificationResult visitMagicType(final OCMagicType ocMagicType) {
        return OCTypeUnificationVisitor.UNKNOWN;
    }
    
    @Override
    public UnificationResult visitObjectType(final OCObjectType ocObjectType) {
        return OCTypeUnificationVisitor.UNKNOWN;
    }
    
    @Override
    public UnificationResult visitArrayType(final OCArrayType ocArrayType) {
        try {
            if (this.myArgument instanceof OCArrayType) {
                return this.unify(ocArrayType.getRefType(), ((OCArrayType)this.myArgument).getRefType()).add(OCTypeUnificationVisitor.UNIFIED);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.b();
    }
    
    @Override
    public UnificationResult visitPointerType(final OCPointerType ocPointerType) {
        if (this.myArgument instanceof OCPointerType) {
            final UnificationResult unify = this.unify(ocPointerType.getRefType(), ((OCPointerType)this.myArgument).getRefType());
            final OCType classQualifier = ocPointerType.getClassQualifier();
            final OCType classQualifier2 = ((OCPointerType)this.myArgument).getClassQualifier();
            try {
                if (unify == OCTypeUnificationVisitor.NOT_UNIFIED) {
                    return unify;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            UnificationResult unificationResult = unify.add(OCTypeUnificationVisitor.UNIFIED);
            Label_0094: {
                boolean b = false;
                Label_0080: {
                    try {
                        if (classQualifier == null) {
                            b = true;
                            break Label_0080;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    b = false;
                    try {
                        if (classQualifier2 == null) {
                            final boolean b2 = true;
                            break Label_0094;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                final boolean b2 = false;
                try {
                    if (b != b2) {
                        return OCTypeUnificationVisitor.NOT_UNIFIED;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            if (classQualifier != null) {
                unificationResult = unificationResult.add(this.unify(classQualifier, classQualifier2));
            }
            return unificationResult;
        }
        return this.b();
    }
    
    @Override
    public UnificationResult visitBlockPointerType(final OCBlockPointerType ocBlockPointerType) {
        try {
            if (this.myArgument instanceof OCBlockPointerType) {
                return this.unify(ocBlockPointerType.getRefType(), ((OCBlockPointerType)this.myArgument).getRefType()).add(OCTypeUnificationVisitor.UNIFIED);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.b();
    }
    
    @Override
    public UnificationResult visitCppReferenceType(final OCCppReferenceType ocCppReferenceType) {
        if (this.myArgument instanceof OCCppReferenceType) {
            final OCCppReferenceType ocCppReferenceType2 = (OCCppReferenceType)this.myArgument;
            try {
                if (ocCppReferenceType.isRvalueRef() == ocCppReferenceType2.isRvalueRef()) {
                    return this.unify(ocCppReferenceType.getRefType(), ocCppReferenceType2.getRefType()).add(OCTypeUnificationVisitor.UNIFIED);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return this.b();
    }
    
    @Override
    public UnificationResult visitIdType(final OCIdType ocIdType) {
        return OCTypeUnificationVisitor.UNKNOWN;
    }
    
    @Override
    public UnificationResult visitReferenceType(final OCReferenceType ocReferenceType) {
        return OCTypeUnificationVisitor.UNKNOWN;
    }
    
    @Override
    public UnificationResult visitUnknownType(final OCUnknownType ocUnknownType) {
        return OCTypeUnificationVisitor.NOT_UNIFIED;
    }
    
    @Override
    public UnificationResult visitAutoType(final OCAutoType ocAutoType) {
        return OCTypeUnificationVisitor.UNKNOWN;
    }
    
    @Override
    public UnificationResult visitEllipsisReferenceType(final OCEllipsisType ocEllipsisType) {
        try {
            if (this.myArgument instanceof OCEllipsisType) {
                return OCTypeUnificationVisitor.UNIFIED;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.b();
    }
    
    @Override
    public UnificationResult visitIntType(final OCIntType ocIntType) {
        try {
            if (this.myArgument.equals(ocIntType, this.myContext)) {
                return OCTypeUnificationVisitor.UNIFIED;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.b();
    }
    
    @Override
    public UnificationResult visitRealType(final OCRealType ocRealType) {
        try {
            if (this.myArgument.equals(ocRealType, this.myContext)) {
                return OCTypeUnificationVisitor.UNIFIED;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.b();
    }
    
    @Override
    public UnificationResult visitVoidType(final OCVoidType ocVoidType) {
        try {
            if (this.myArgument.equals(ocVoidType, this.myContext)) {
                return OCTypeUnificationVisitor.UNIFIED;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.b();
    }
    
    @Nullable
    private OCType a(final OCType p0, final OCStructType p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //     4: ifne            45
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    11: ifeq            51
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_1        
        //    22: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    25: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    28: aload_2        
        //    29: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    32: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.resolvedNamesEqual:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Z
        //    35: ifeq            51
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_1        
        //    46: areturn        
        //    47: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myFunctionParametersMode:Z
        //    55: ifeq            194
        //    58: aload_1        
        //    59: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    62: ifeq            194
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: aload_2        
        //    73: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //    76: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    81: astore_3       
        //    82: aload_3        
        //    83: invokeinterface java/util/Iterator.hasNext:()Z
        //    88: ifeq            194
        //    91: aload_3        
        //    92: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    97: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   100: astore          4
        //   102: aload_1        
        //   103: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   106: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   109: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   114: astore          5
        //   116: aload           5
        //   118: invokeinterface java/util/Iterator.hasNext:()Z
        //   123: ifeq            191
        //   126: aload           5
        //   128: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   133: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   136: astore          6
        //   138: new             Lcom/intellij/openapi/util/Ref;
        //   141: dup            
        //   142: invokespecial   com/intellij/openapi/util/Ref.<init>:()V
        //   145: astore          7
        //   147: aload           6
        //   149: aload_0        
        //   150: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   153: aload           4
        //   155: aload           7
        //   157: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   162: iconst_0       
        //   163: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processAllBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;Z)Z
        //   166: pop            
        //   167: aload           7
        //   169: invokevirtual   com/intellij/openapi/util/Ref.isNull:()Z
        //   172: ifne            188
        //   175: aload           7
        //   177: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   180: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   183: areturn        
        //   184: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: goto            116
        //   191: goto            82
        //   194: aconst_null    
        //   195: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      38     41     45     Ljava/lang/IllegalArgumentException;
        //  21     47     47     51     Ljava/lang/IllegalArgumentException;
        //  51     65     68     72     Ljava/lang/IllegalArgumentException;
        //  147    184    184    188    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    public UnificationResult visitStructType(final OCStructType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //     7: ifeq            29
        //    10: aload_0        
        //    11: aload_0        
        //    12: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //    15: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    18: aload_1        
        //    19: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCStructType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    22: goto            30
        //    25: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: aconst_null    
        //    30: astore_2       
        //    31: aload_2        
        //    32: ifnonnull       44
        //    35: aload_0        
        //    36: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.b:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //    39: areturn        
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //    47: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //    50: astore_3       
        //    51: aload_2        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    55: ifeq            386
        //    58: aload_0        
        //    59: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    62: iconst_1       
        //    63: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.setDontExpandVariadics:(Z)V
        //    66: aload_1        
        //    67: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    70: astore          4
        //    72: aload           4
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    78: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getTemplateArguments:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //    81: astore          5
        //    83: iconst_0       
        //    84: istore          6
        //    86: aload           4
        //    88: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isSpecialization:()Z
        //    91: ifne            197
        //    94: aload           4
        //    96: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getTemplateParameters:()Ljava/util/List;
        //    99: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   104: astore          7
        //   106: aload           7
        //   108: invokeinterface java/util/Iterator.hasNext:()Z
        //   113: ifeq            197
        //   116: aload           7
        //   118: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   123: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   126: astore          8
        //   128: aload           4
        //   130: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   133: aload           8
        //   135: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   138: ifnonnull       194
        //   141: aload           8
        //   143: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.isVariadic:()Z
        //   148: ifne            194
        //   151: goto            158
        //   154: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: aload_0        
        //   159: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myDependentTypes:Ljava/util/Set;
        //   162: ifnull          191
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload_0        
        //   173: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myDependentTypes:Ljava/util/Set;
        //   176: aload           8
        //   178: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   183: pop            
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: iconst_1       
        //   192: istore          6
        //   194: goto            106
        //   197: iload           6
        //   199: ifeq            233
        //   202: aload_1        
        //   203: aload_0        
        //   204: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   207: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.clearSubstitution:()Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   210: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getResolvedArguments:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   213: astore          7
        //   215: aload           7
        //   217: ifnull          229
        //   220: aload           7
        //   222: goto            231
        //   225: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: aload           5
        //   231: astore          5
        //   233: aload_0        
        //   234: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   237: iconst_0       
        //   238: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.setDontExpandVariadics:(Z)V
        //   241: aload_2        
        //   242: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   245: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   248: astore          7
        //   250: aload           7
        //   252: aload_0        
        //   253: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   256: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getTemplateArguments:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   259: astore          8
        //   261: iconst_0       
        //   262: istore          9
        //   264: iload           9
        //   266: aload           8
        //   268: invokeinterface java/util/List.size:()I
        //   273: if_icmpge       361
        //   276: aload           8
        //   278: iload           9
        //   280: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   285: ifnonnull       355
        //   288: goto            295
        //   291: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   294: athrow         
        //   295: aload           7
        //   297: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getTemplateParameters:()Ljava/util/List;
        //   300: iload           9
        //   302: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   307: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   310: astore          10
        //   312: aload_0        
        //   313: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   316: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getTypeDependencies:()Ljava/util/Set;
        //   319: aload           10
        //   321: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   326: ifeq            355
        //   329: aload           8
        //   331: iload           9
        //   333: new             Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   336: dup            
        //   337: aload           10
        //   339: invokespecial   com/jetbrains/cidr/lang/types/OCTypeParameterType.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)V
        //   342: invokeinterface java/util/List.set:(ILjava/lang/Object;)Ljava/lang/Object;
        //   347: pop            
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: iinc            9, 1
        //   358: goto            264
        //   361: aload           5
        //   363: aload           8
        //   365: aload_0        
        //   366: aload_3        
        //   367: invokedynamic   process:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor;Lcom/intellij/openapi/util/Ref;)Lcom/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor$TypeArgumentsProcessor;
        //   372: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.processArguments:(Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor$TypeArgumentsProcessor;)Z
        //   375: ifne            386
        //   378: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   381: areturn        
        //   382: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   385: athrow         
        //   386: aload_3        
        //   387: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   390: checkcast       Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   393: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      25     25     29     Ljava/lang/IllegalArgumentException;
        //  31     40     40     44     Ljava/lang/IllegalArgumentException;
        //  128    151    154    158    Ljava/lang/IllegalArgumentException;
        //  141    165    168    172    Ljava/lang/IllegalArgumentException;
        //  158    184    187    191    Ljava/lang/IllegalArgumentException;
        //  215    225    225    229    Ljava/lang/IllegalArgumentException;
        //  264    288    291    295    Ljava/lang/IllegalArgumentException;
        //  312    348    351    355    Ljava/lang/IllegalArgumentException;
        //  361    382    382    386    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0158:
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
    public UnificationResult visitTypeParameterType(final OCTypeParameterType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //     4: astore_2       
        //     5: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //     8: astore_3       
        //     9: aload_0        
        //    10: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //    13: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //    16: ifeq            70
        //    19: aload_0        
        //    20: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //    23: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //    26: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //    29: aload_2        
        //    30: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    33: ifeq            70
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aload_2        
        //    44: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    47: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isSynthetic:()Z
        //    52: ifne            70
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //    65: areturn        
        //    66: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_2        
        //    71: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //    74: ifeq            159
        //    77: aload_0        
        //    78: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //    81: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //    84: ifne            226
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_0        
        //    95: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //    98: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   101: ifeq            134
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_0        
        //   112: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   115: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   118: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   121: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol;
        //   124: ifne            226
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload_0        
        //   135: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   138: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   141: ifne            226
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   154: areturn        
        //   155: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_0        
        //   160: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:()Z
        //   163: ifeq            226
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   170: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   173: ifne            226
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: aload_0        
        //   184: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   187: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   190: ifeq            222
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: aload_0        
        //   201: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.mySubstitutionMap:Ljava/util/Map;
        //   204: aload_2        
        //   205: aload_0        
        //   206: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   209: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   214: pop            
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNKNOWN:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   225: areturn        
        //   226: aload_0        
        //   227: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.mySubstitutionMap:Ljava/util/Map;
        //   230: aload_2        
        //   231: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   236: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   239: astore          4
        //   241: aload_0        
        //   242: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   245: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   248: ifeq            445
        //   251: aload_0        
        //   252: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   255: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   258: ifne            445
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: aload_0        
        //   269: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   272: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   275: astore          5
        //   277: aload_1        
        //   278: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.isConst:()Z
        //   281: ifeq            358
        //   284: aload           5
        //   286: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   289: ifeq            329
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   298: athrow         
        //   299: aload_0        
        //   300: aload           5
        //   302: aload_0        
        //   303: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   306: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   309: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutConstModifier:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   312: dup            
        //   313: astore          5
        //   315: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   318: aload_3        
        //   319: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   322: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.add:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   325: astore_3       
        //   326: goto            358
        //   329: aload_0        
        //   330: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myFunctionParametersMode:Z
        //   333: ifne            358
        //   336: aload_0        
        //   337: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myPartialOrderingMode:Z
        //   340: ifne            358
        //   343: goto            350
        //   346: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   349: athrow         
        //   350: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   353: areturn        
        //   354: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   357: athrow         
        //   358: aload_1        
        //   359: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.isVolatile:()Z
        //   362: ifeq            445
        //   365: aload           5
        //   367: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   370: ifeq            416
        //   373: goto            380
        //   376: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   379: athrow         
        //   380: aload_0        
        //   381: aload           5
        //   383: aload           5
        //   385: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   388: iconst_0       
        //   389: invokestatic    com/jetbrains/cidr/lang/types/CVQualifiers.get:(ZZ)Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   392: aload_0        
        //   393: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   396: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   399: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   402: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   405: aload_3        
        //   406: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   409: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.add:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   412: astore_3       
        //   413: goto            445
        //   416: aload_0        
        //   417: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myFunctionParametersMode:Z
        //   420: ifne            445
        //   423: aload_0        
        //   424: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myPartialOrderingMode:Z
        //   427: ifne            445
        //   430: goto            437
        //   433: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   436: athrow         
        //   437: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   440: areturn        
        //   441: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   444: athrow         
        //   445: aload_2        
        //   446: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.isVariadic:()Z
        //   451: ifeq            589
        //   454: aload_0        
        //   455: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   458: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   461: ifeq            502
        //   464: goto            471
        //   467: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   470: athrow         
        //   471: aload_0        
        //   472: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   475: checkcast       Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   478: invokevirtual   com/jetbrains/cidr/lang/types/OCVariadicType.getUnderlyingType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   481: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   484: ifeq            502
        //   487: goto            494
        //   490: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   493: athrow         
        //   494: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   497: areturn        
        //   498: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   501: athrow         
        //   502: aload_0        
        //   503: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   506: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   509: ifne            758
        //   512: aload           4
        //   514: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   517: ifeq            568
        //   520: goto            527
        //   523: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   526: athrow         
        //   527: aload           4
        //   529: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   532: astore          5
        //   534: aload           5
        //   536: invokevirtual   com/jetbrains/cidr/lang/types/OCExpansionPackType.getExpansionsCnt:()I
        //   539: bipush          25
        //   541: if_icmple       552
        //   544: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   547: areturn        
        //   548: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   551: athrow         
        //   552: aload_0        
        //   553: aload           5
        //   555: aload_0        
        //   556: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   559: invokevirtual   com/jetbrains/cidr/lang/types/OCExpansionPackType.appendTypeArgument:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   562: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   565: goto            758
        //   568: aload_0        
        //   569: new             Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   572: dup            
        //   573: aload_0        
        //   574: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   577: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   580: invokespecial   com/jetbrains/cidr/lang/types/OCExpansionPackType.<init>:(Ljava/util/List;)V
        //   583: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   586: goto            758
        //   589: aload_0        
        //   590: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   593: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   596: ifne            616
        //   599: aload_0        
        //   600: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   603: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   606: ifeq            648
        //   609: goto            616
        //   612: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   615: athrow         
        //   616: aload_0        
        //   617: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   620: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   623: aload_0        
        //   624: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   627: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   630: ifne            758
        //   633: goto            640
        //   636: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   639: athrow         
        //   640: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   643: areturn        
        //   644: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   647: athrow         
        //   648: aload           4
        //   650: ifnull          758
        //   653: aload           4
        //   655: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   658: ifeq            728
        //   661: goto            668
        //   664: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   667: athrow         
        //   668: aload_0        
        //   669: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   672: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   675: ifeq            728
        //   678: goto            685
        //   681: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   684: athrow         
        //   685: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor;
        //   688: dup            
        //   689: aload           4
        //   691: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   694: aload_0        
        //   695: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myMagicTypesEqual:Z
        //   698: iconst_0       
        //   699: iconst_0       
        //   700: iconst_0       
        //   701: iconst_0       
        //   702: iconst_1       
        //   703: iconst_1       
        //   704: aload_0        
        //   705: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   708: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   711: aload_0        
        //   712: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   715: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   718: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.equal:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   721: goto            743
        //   724: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   727: athrow         
        //   728: aload           4
        //   730: aload_0        
        //   731: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   734: aload_0        
        //   735: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   738: invokeinterface com/jetbrains/cidr/lang/types/OCTypeArgument.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   743: istore          5
        //   745: iload           5
        //   747: ifne            758
        //   750: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   753: areturn        
        //   754: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   757: athrow         
        //   758: aload_0        
        //   759: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.mySubstitutionMap:Ljava/util/Map;
        //   762: aload_2        
        //   763: aload_0        
        //   764: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.myArgument:Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   767: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   772: pop            
        //   773: aload_3        
        //   774: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  9      36     39     43     Ljava/lang/IllegalArgumentException;
        //  19     55     58     62     Ljava/lang/IllegalArgumentException;
        //  43     66     66     70     Ljava/lang/IllegalArgumentException;
        //  70     87     90     94     Ljava/lang/IllegalArgumentException;
        //  77     104    107    111    Ljava/lang/IllegalArgumentException;
        //  94     127    130    134    Ljava/lang/IllegalArgumentException;
        //  111    144    147    151    Ljava/lang/IllegalArgumentException;
        //  134    155    155    159    Ljava/lang/IllegalArgumentException;
        //  159    176    179    183    Ljava/lang/IllegalArgumentException;
        //  166    193    196    200    Ljava/lang/IllegalArgumentException;
        //  183    215    218    222    Ljava/lang/IllegalArgumentException;
        //  241    261    264    268    Ljava/lang/IllegalArgumentException;
        //  277    292    295    299    Ljava/lang/IllegalArgumentException;
        //  329    343    346    350    Ljava/lang/IllegalArgumentException;
        //  336    354    354    358    Ljava/lang/IllegalArgumentException;
        //  358    373    376    380    Ljava/lang/IllegalArgumentException;
        //  416    430    433    437    Ljava/lang/IllegalArgumentException;
        //  423    441    441    445    Ljava/lang/IllegalArgumentException;
        //  445    464    467    471    Ljava/lang/IllegalArgumentException;
        //  454    487    490    494    Ljava/lang/IllegalArgumentException;
        //  471    498    498    502    Ljava/lang/IllegalArgumentException;
        //  502    520    523    527    Ljava/lang/IllegalArgumentException;
        //  534    548    548    552    Ljava/lang/IllegalArgumentException;
        //  589    609    612    616    Ljava/lang/IllegalArgumentException;
        //  599    633    636    640    Ljava/lang/IllegalArgumentException;
        //  616    644    644    648    Ljava/lang/IllegalArgumentException;
        //  648    661    664    668    Ljava/lang/IllegalArgumentException;
        //  653    678    681    685    Ljava/lang/IllegalArgumentException;
        //  668    724    724    728    Ljava/lang/IllegalArgumentException;
        //  745    754    754    758    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
    public UnificationResult visitVariadicType(final OCVariadicType ocVariadicType) {
        return OCTypeUnificationVisitor.UNKNOWN;
    }
    
    @Override
    public UnificationResult visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        return OCTypeUnificationVisitor.UNKNOWN;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCTypeUnificationVisitor.class.desiredAssertionStatus()) {
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
        NOT_UNIFIED = new UnificationResult(-1);
        UNIFIED = new UnificationResult(1);
        UNIFIED_CONST_VALUE = new UnificationResult(1, 0, 1);
        UNKNOWN = new UnificationResult(0);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class UnificationResult
    {
        private final int numOfUnified;
        private int numOfNonSpecializedArgs;
        private int numOfConstantValueArgs;
        
        UnificationResult(final int numOfUnified) {
            this.numOfUnified = numOfUnified;
        }
        
        public UnificationResult(final int numOfUnified, final int numOfNonSpecializedArgs, final int numOfConstantValueArgs) {
            this.numOfUnified = numOfUnified;
            this.numOfNonSpecializedArgs = numOfNonSpecializedArgs;
            this.numOfConstantValueArgs = numOfConstantValueArgs;
        }
        
        UnificationResult add(final UnificationResult unificationResult) {
            if (this == OCTypeUnificationVisitor.NOT_UNIFIED || unificationResult == OCTypeUnificationVisitor.NOT_UNIFIED) {
                return OCTypeUnificationVisitor.NOT_UNIFIED;
            }
            return new UnificationResult(this.numOfUnified + unificationResult.numOfUnified, this.numOfNonSpecializedArgs + unificationResult.numOfNonSpecializedArgs, this.numOfConstantValueArgs + unificationResult.numOfConstantValueArgs);
        }
        
        void incNumOfNonSpecializedArgs() {
            ++this.numOfNonSpecializedArgs;
        }
        
        public boolean isUnified() {
            return this.numOfUnified > 0;
        }
        
        boolean isBetter(final UnificationResult unificationResult) {
            return this.numOfUnified > unificationResult.numOfUnified || (this.numOfUnified >= unificationResult.numOfUnified && (this.numOfNonSpecializedArgs < unificationResult.numOfNonSpecializedArgs || (this.numOfNonSpecializedArgs <= unificationResult.numOfNonSpecializedArgs && this.numOfConstantValueArgs > unificationResult.numOfConstantValueArgs)));
        }
        
        @Override
        public String toString() {
            if (this == OCTypeUnificationVisitor.UNIFIED) {
                return "UNIFIED";
            }
            if (this == OCTypeUnificationVisitor.UNIFIED_CONST_VALUE) {
                return "UNIFIED_CONST_VALUE";
            }
            if (this == OCTypeUnificationVisitor.NOT_UNIFIED) {
                return "NOT_UNIFIED";
            }
            if (this == OCTypeUnificationVisitor.UNKNOWN) {
                return "UNKNOWN";
            }
            return "UnificationResult(unified=" + this.numOfUnified + ", non-spec=" + this.numOfNonSpecializedArgs + ", const-value=" + this.numOfConstantValueArgs + ")";
        }
    }
}
