// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCAddSuperProtocolIntentionAction;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.openapi.util.RecursionManager;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.intellij.util.Producer;
import java.util.List;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import java.util.Collections;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.quickfixes.OCCreateNewDefinitionIntentionAction;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import com.intellij.openapi.util.Computable;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.types.OCNumericType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;

public abstract class OCTypeCompatibilityVisitor<T extends OCType> implements OCTypeVisitor<OCType.TypeCheckResult>
{
    public static final OCType.TypeCheckResult OK_RESULT;
    @NotNull
    protected final T mySourceType;
    @Nullable
    protected final OCTypeOwner mySource;
    @Nullable
    protected final PsiElement myContext;
    protected final boolean myAllowImplicitConversions;
    protected final boolean myAssumeNullSubstitutionsEquals;
    @NotNull
    protected final OCResolveContext myResolveContext;
    
    public static OCType.TypeCheckResult checkConvertible(final OCType p0, final OCType p1, @Nullable final OCTypeOwner p2, @Nullable final PsiElement p3, final boolean p4, final boolean p5, final boolean p6, @NotNull final OCResolveContext p7) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           7
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "resolveContext"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "checkConvertible"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: iconst_0       
        //    46: istore          8
        //    48: aload_1        
        //    49: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    52: ifeq            237
        //    55: aload_2        
        //    56: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //    59: ifne            83
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_2        
        //    70: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCLiteralExpressionSymbol;
        //    73: ifeq            92
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_2        
        //    84: aload           7
        //    86: invokeinterface com/jetbrains/cidr/lang/types/OCTypeOwner.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    91: astore_1       
        //    92: aconst_null    
        //    93: astore          9
        //    95: aload_0        
        //    96: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    99: ifeq            111
        //   102: aload_0        
        //   103: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   106: astore          9
        //   108: goto            150
        //   111: aload_0        
        //   112: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   115: ifeq            150
        //   118: aload_0        
        //   119: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   122: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   125: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   128: ifeq            150
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload_0        
        //   139: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   142: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   145: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   148: astore          9
        //   150: aload           9
        //   152: ifnull          234
        //   155: aload_2        
        //   156: aload           7
        //   158: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.getPointerType:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   161: astore          10
        //   163: aload           10
        //   165: instanceof      Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   168: ifeq            226
        //   171: aload           9
        //   173: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToPointerToObjectCompatible:()Z
        //   176: ifeq            192
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: iconst_1       
        //   187: istore          8
        //   189: goto            234
        //   192: aload           9
        //   194: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   197: ifeq            218
        //   200: aload           9
        //   202: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   205: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //   208: ifne            234
        //   211: goto            218
        //   214: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   221: areturn        
        //   222: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: aload           10
        //   228: ifnull          234
        //   231: aload           10
        //   233: astore_1       
        //   234: goto            262
        //   237: aload_1        
        //   238: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToVoid:()Z
        //   241: ifeq            262
        //   244: aload_2        
        //   245: aload           7
        //   247: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.isLikeNil:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   250: ifeq            262
        //   253: goto            260
        //   256: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   259: athrow         
        //   260: aload_0        
        //   261: astore_1       
        //   262: aload_2        
        //   263: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   266: ifeq            313
        //   269: aload_2        
        //   270: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   273: aload_0        
        //   274: iload           4
        //   276: ifeq            306
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   285: athrow         
        //   286: iload           5
        //   288: ifeq            306
        //   291: goto            298
        //   294: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   297: athrow         
        //   298: iconst_1       
        //   299: goto            307
        //   302: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   305: athrow         
        //   306: iconst_0       
        //   307: aload           7
        //   309: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.checkCompoundInitializer:(Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   312: areturn        
        //   313: aload_1        
        //   314: aload_2        
        //   315: aload_3        
        //   316: iload           4
        //   318: ifeq            341
        //   321: iload           5
        //   323: ifeq            341
        //   326: goto            333
        //   329: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   332: athrow         
        //   333: iconst_1       
        //   334: goto            342
        //   337: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   340: athrow         
        //   341: iconst_0       
        //   342: iload           6
        //   344: aload           7
        //   346: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.createOCTypeCompatibilityVisitor:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;ZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;
        //   349: astore          9
        //   351: aload_0        
        //   352: aload           9
        //   354: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //   357: checkcast       Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   360: astore          10
        //   362: aload           10
        //   364: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   367: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.OK:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   370: if_acmpne       380
        //   373: aload           10
        //   375: areturn        
        //   376: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   379: athrow         
        //   380: iload           8
        //   382: ifeq            509
        //   385: aload_2        
        //   386: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   389: ifeq            509
        //   392: goto            399
        //   395: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   398: athrow         
        //   399: aload_2        
        //   400: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   403: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextWithMacros:()Ljava/lang/String;
        //   408: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isLikeNull:(Ljava/lang/String;)Z
        //   411: ifeq            429
        //   414: goto            421
        //   417: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   420: athrow         
        //   421: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   424: areturn        
        //   425: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   428: athrow         
        //   429: new             Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   432: dup            
        //   433: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   436: ldc             "Using zero as nil"
        //   438: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$UsingZeroAsNil;.class
        //   440: ldc             "CIDR"
        //   442: iconst_0       
        //   443: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   446: invokespecial   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.<init>:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   449: astore          11
        //   451: aload_2        
        //   452: checkcast       Lcom/intellij/psi/PsiElement;
        //   455: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   458: astore          12
        //   460: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction;
        //   463: dup            
        //   464: aload_2        
        //   465: checkcast       Lcom/intellij/psi/PsiElement;
        //   468: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   473: aload           12
        //   475: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   478: aload           12
        //   480: invokevirtual   com/intellij/openapi/util/TextRange.getLength:()I
        //   483: ldc             "nil"
        //   485: ldc             "Change to 'nil'"
        //   487: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction.<init>:(Lcom/intellij/psi/PsiFile;IILjava/lang/String;Ljava/lang/String;)V
        //   490: astore          13
        //   492: aload           11
        //   494: iconst_1       
        //   495: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   498: dup            
        //   499: iconst_0       
        //   500: aload           13
        //   502: aastore        
        //   503: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.setQuickFixes:([Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   506: aload           11
        //   508: areturn        
        //   509: iload           4
        //   511: ifeq            576
        //   514: aload           9
        //   516: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   519: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   522: ifeq            576
        //   525: goto            532
        //   528: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   531: athrow         
        //   532: aload_0        
        //   533: aload           9
        //   535: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   538: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   541: aload           9
        //   543: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   546: aload           9
        //   548: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   551: aload           10
        //   553: aload_3        
        //   554: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //   557: aload           7
        //   559: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.checkConversionOperators:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCStructType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   562: astore          11
        //   564: aload           11
        //   566: ifnull          576
        //   569: aload           11
        //   571: areturn        
        //   572: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   575: athrow         
        //   576: aload           10
        //   578: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  48     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     76     79     83     Ljava/lang/IllegalArgumentException;
        //  111    131    134    138    Ljava/lang/IllegalArgumentException;
        //  163    179    182    186    Ljava/lang/IllegalArgumentException;
        //  192    211    214    218    Ljava/lang/IllegalArgumentException;
        //  200    222    222    226    Ljava/lang/IllegalArgumentException;
        //  237    253    256    260    Ljava/lang/IllegalArgumentException;
        //  262    279    282    286    Ljava/lang/IllegalArgumentException;
        //  269    291    294    298    Ljava/lang/IllegalArgumentException;
        //  286    302    302    306    Ljava/lang/IllegalArgumentException;
        //  313    326    329    333    Ljava/lang/IllegalArgumentException;
        //  321    337    337    341    Ljava/lang/IllegalArgumentException;
        //  362    376    376    380    Ljava/lang/IllegalArgumentException;
        //  380    392    395    399    Ljava/lang/IllegalArgumentException;
        //  385    414    417    421    Ljava/lang/IllegalArgumentException;
        //  399    425    425    429    Ljava/lang/IllegalArgumentException;
        //  509    525    528    532    Ljava/lang/IllegalArgumentException;
        //  564    572    572    576    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0286:
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
    
    public static OCType.TypeCheckResult checkConversionOperators(final OCType ocType, final OCStructType ocStructType, @Nullable final OCTypeOwner ocTypeOwner, final PsiElement psiElement, @Nullable final OCType.TypeCheckResult typeCheckResult, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "checkConversionOperators"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Ref create = Ref.create((Object)typeCheckResult);
        try {
            if (ocStructType.isSubclassOfMagic(ocResolveContext)) {
                return OCTypeCompatibilityVisitor.OK_RESULT;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ocStructType.processMembers(null, (Processor<OCSymbol>)(p6 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload           4
            //     2: ifnonnull       45
            //     5: new             Ljava/lang/IllegalArgumentException;
            //     8: dup            
            //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    11: ldc             3
            //    13: anewarray       Ljava/lang/Object;
            //    16: dup            
            //    17: ldc             0
            //    19: ldc             "resolveContext"
            //    21: aastore        
            //    22: dup            
            //    23: ldc             1
            //    25: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
            //    27: aastore        
            //    28: dup            
            //    29: ldc             2
            //    31: ldc             "lambda$checkConversionOperators$0"
            //    33: aastore        
            //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    40: athrow         
            //    41: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    44: athrow         
            //    45: aload           6
            //    47: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //    50: ifeq            100
            //    53: aload           6
            //    55: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //    58: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConversionOperator:()Z
            //    61: ifeq            100
            //    64: goto            71
            //    67: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    70: athrow         
            //    71: iload_0        
            //    72: ifne            106
            //    75: goto            82
            //    78: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    81: athrow         
            //    82: aload           6
            //    84: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //    87: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isExplicit:()Z
            //    90: ifeq            106
            //    93: goto            100
            //    96: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    99: athrow         
            //   100: iconst_1       
            //   101: ireturn        
            //   102: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   105: athrow         
            //   106: aload           6
            //   108: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getEffectiveResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   113: astore          7
            //   115: aload_1        
            //   116: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   119: ifeq            163
            //   122: aload_1        
            //   123: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   126: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   129: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalar:()Z
            //   132: ifeq            163
            //   135: goto            142
            //   138: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   141: athrow         
            //   142: aload           7
            //   144: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
            //   147: ifne            163
            //   150: goto            157
            //   153: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   156: athrow         
            //   157: iconst_1       
            //   158: ireturn        
            //   159: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   162: athrow         
            //   163: aload           7
            //   165: aload_2        
            //   166: aload_3        
            //   167: iconst_0       
            //   168: iconst_1       
            //   169: aload           4
            //   171: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.createOCTypeCompatibilityVisitor:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;ZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;
            //   174: astore          8
            //   176: aload_1        
            //   177: aload           8
            //   179: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
            //   182: checkcast       Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
            //   185: astore          9
            //   187: aload           5
            //   189: invokevirtual   com/intellij/openapi/util/Ref.isNull:()Z
            //   192: ifne            285
            //   195: aload           9
            //   197: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //   200: aload           5
            //   202: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
            //   205: checkcast       Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
            //   208: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //   211: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.compareTo:(Ljava/lang/Enum;)I
            //   214: iflt            285
            //   217: goto            224
            //   220: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   223: athrow         
            //   224: aload           9
            //   226: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //   229: aload           5
            //   231: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
            //   234: checkcast       Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
            //   237: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //   240: if_acmpne       379
            //   243: goto            250
            //   246: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   249: athrow         
            //   250: aload           7
            //   252: aload_1        
            //   253: aload           4
            //   255: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.getTypesDifference:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)I
            //   258: aload           5
            //   260: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
            //   263: checkcast       Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
            //   266: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getTypeAfterConversion:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   269: aload_1        
            //   270: aload           4
            //   272: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.getTypesDifference:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)I
            //   275: if_icmpge       379
            //   278: goto            285
            //   281: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   284: athrow         
            //   285: aload           9
            //   287: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //   290: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.OK:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //   293: if_acmpne       354
            //   296: goto            303
            //   299: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   302: athrow         
            //   303: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$1;
            //   306: dup            
            //   307: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.OK:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
            //   310: aload           9
            //   312: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getInspectionClass:()Ljava/lang/Class;
            //   315: aload           9
            //   317: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getClangID:()Ljava/lang/String;
            //   320: aconst_null    
            //   321: aload           7
            //   323: iconst_0       
            //   324: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
            //   327: aload           9
            //   329: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$1.<init>:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;)V
            //   332: astore          10
            //   334: aload           10
            //   336: aload           6
            //   338: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //   341: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.setImplicitConstructor:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)V
            //   344: aload           5
            //   346: aload           10
            //   348: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
            //   351: goto            379
            //   354: aload           9
            //   356: aload           6
            //   358: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //   361: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.setImplicitConstructor:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)V
            //   364: aload           9
            //   366: aconst_null    
            //   367: aload           7
            //   369: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.setConversion:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
            //   372: aload           5
            //   374: aload           9
            //   376: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
            //   379: iconst_1       
            //   380: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      41     41     45     Ljava/lang/IllegalArgumentException;
            //  45     64     67     71     Ljava/lang/IllegalArgumentException;
            //  53     75     78     82     Ljava/lang/IllegalArgumentException;
            //  71     93     96     100    Ljava/lang/IllegalArgumentException;
            //  82     102    102    106    Ljava/lang/IllegalArgumentException;
            //  115    135    138    142    Ljava/lang/IllegalArgumentException;
            //  122    150    153    157    Ljava/lang/IllegalArgumentException;
            //  142    159    159    163    Ljava/lang/IllegalArgumentException;
            //  187    217    220    224    Ljava/lang/IllegalArgumentException;
            //  195    243    246    250    Ljava/lang/IllegalArgumentException;
            //  224    278    281    285    Ljava/lang/IllegalArgumentException;
            //  250    296    299    303    Ljava/lang/IllegalArgumentException;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }), ocResolveContext);
        return (OCType.TypeCheckResult)create.get();
    }
    
    public static OCType.TypeCheckResult checkConstructors(final OCStructType ocStructType, final OCType ocType, final OCTypeOwner ocTypeOwner, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "checkConstructors"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCTypeCompatibilityVisitor<? extends OCType> ocTypeCompatibilityVisitor = createOCTypeCompatibilityVisitor(ocType, ocTypeOwner, ocResolveContext.getElement(), false, true, ocResolveContext);
        final Iterator<OCStructSymbol> iterator = ocStructType.getStructs().iterator();
        while (iterator.hasNext()) {
            final OCType.TypeCheckResult processConstructors = ocTypeCompatibilityVisitor.processConstructors(iterator.next());
            try {
                if (processConstructors != null) {
                    return processConstructors;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return new OCType.TypeCheckResult(OCType.TypeCheckState.ERROR);
    }
    
    protected OCTypeCompatibilityVisitor(@NotNull final T mySourceType, @Nullable final OCTypeOwner mySource, @Nullable final PsiElement myContext, final boolean myAllowImplicitConversions, final boolean myAssumeNullSubstitutionsEquals, @NotNull final OCResolveContext myResolveContext) {
        if (mySourceType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "<init>"));
        }
        if (myResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "<init>"));
        }
        this.mySourceType = mySourceType;
        this.myResolveContext = myResolveContext;
        this.mySource = mySource;
        this.myContext = myContext;
        this.myAllowImplicitConversions = myAllowImplicitConversions;
        this.myAssumeNullSubstitutionsEquals = myAssumeNullSubstitutionsEquals;
    }
    
    public static OCTypeCompatibilityVisitor<? extends OCType> createOCTypeCompatibilityVisitor(@Nullable OCType instance, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "createOCTypeCompatibilityVisitor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (instance == null) {
            instance = OCUnknownType.INSTANCE;
        }
        return instance.accept((OCTypeVisitor<OCTypeCompatibilityVisitor<? extends OCType>>)new OCTypeCompatibilityVisitorCreator(ocTypeOwner, psiElement, b, b2, ocResolveContext));
    }
    
    @Override
    public OCType.TypeCheckResult visitCppReferenceType(final OCCppReferenceType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //     4: ifnull          222
        //     7: aload_1        
        //     8: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
        //    11: ifne            98
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_1        
        //    22: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //    25: ifne            98
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_1        
        //    36: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isUnknown:()Z
        //    39: ifne            98
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    57: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    60: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isLValue:()Z
        //    63: ifne            98
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: new             Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    76: dup            
        //    77: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //    80: ldc             "Expression must be lvalue"
        //    82: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotAssignable;.class
        //    84: ldc             "err_typecheck_convert_incompatible"
        //    86: iconst_0       
        //    87: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //    90: invokespecial   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.<init>:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //    93: areturn        
        //    94: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: aload_1        
        //    99: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   102: ifeq            222
        //   105: aload_1        
        //   106: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   109: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   112: ifne            222
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload_1        
        //   123: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   126: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   129: ifne            222
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: aload_0        
        //   140: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   143: aload_0        
        //   144: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   147: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   150: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isLValue:()Z
        //   153: ifeq            222
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   167: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;)Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   170: astore_2       
        //   171: aload_2        
        //   172: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   175: ifeq            197
        //   178: aload_2        
        //   179: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   182: invokeinterface com/jetbrains/cidr/lang/psi/OCLiteralExpression.isStringLiteral:()Z
        //   187: ifne            222
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: new             Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   200: dup            
        //   201: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   204: ldc             "Expression must be rvalue"
        //   206: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotAssignable;.class
        //   208: ldc             "err_typecheck_convert_incompatible"
        //   210: iconst_0       
        //   211: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   214: invokespecial   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.<init>:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   217: areturn        
        //   218: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload_1        
        //   223: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   226: astore_2       
        //   227: aload_0        
        //   228: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   231: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   234: ifeq            289
        //   237: aload_2        
        //   238: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   241: ifne            289
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload_2        
        //   252: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   255: ifne            289
        //   258: goto            265
        //   261: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$2;
        //   268: dup            
        //   269: aload_0        
        //   270: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   273: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatibleTypes;.class
        //   275: ldc             "err_typecheck_convert_incompatible"
        //   277: iconst_0       
        //   278: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   281: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$2.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   284: areturn        
        //   285: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: aload_2        
        //   290: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalar:()Z
        //   293: ifne            313
        //   296: aload_0        
        //   297: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   300: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalar:()Z
        //   303: ifeq            437
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: aload_2        
        //   314: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   317: ifne            437
        //   320: goto            327
        //   323: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   326: athrow         
        //   327: aload_1        
        //   328: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   331: ifne            437
        //   334: goto            341
        //   337: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   340: athrow         
        //   341: aload_2        
        //   342: instanceof      Lcom/jetbrains/cidr/lang/types/OCEllipsisType;
        //   345: ifne            437
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: aload_0        
        //   356: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   359: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //   362: ifne            437
        //   365: goto            372
        //   368: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor;
        //   375: dup            
        //   376: aload_0        
        //   377: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   380: iconst_1       
        //   381: iconst_0       
        //   382: iconst_0       
        //   383: iconst_0       
        //   384: iconst_0       
        //   385: iconst_1       
        //   386: iconst_1       
        //   387: aload_0        
        //   388: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   391: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   394: aload_2        
        //   395: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.equal:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   398: ifeq            416
        //   401: goto            408
        //   404: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   407: athrow         
        //   408: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   411: areturn        
        //   412: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   415: athrow         
        //   416: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$3;
        //   419: dup            
        //   420: aload_0        
        //   421: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   424: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatibleTypes;.class
        //   426: ldc             "err_typecheck_convert_incompatible"
        //   428: iconst_0       
        //   429: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   432: aload_1        
        //   433: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$3.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;)V
        //   436: areturn        
        //   437: aload_0        
        //   438: aload_1        
        //   439: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.checkRefType:(Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   442: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     66     69     73     Ljava/lang/IllegalArgumentException;
        //  49     94     94     98     Ljava/lang/IllegalArgumentException;
        //  98     115    118    122    Ljava/lang/IllegalArgumentException;
        //  105    132    135    139    Ljava/lang/IllegalArgumentException;
        //  122    156    159    163    Ljava/lang/IllegalArgumentException;
        //  171    190    193    197    Ljava/lang/IllegalArgumentException;
        //  178    218    218    222    Ljava/lang/IllegalArgumentException;
        //  227    244    247    251    Ljava/lang/IllegalArgumentException;
        //  237    258    261    265    Ljava/lang/IllegalArgumentException;
        //  251    285    285    289    Ljava/lang/IllegalArgumentException;
        //  289    306    309    313    Ljava/lang/IllegalArgumentException;
        //  296    320    323    327    Ljava/lang/IllegalArgumentException;
        //  313    334    337    341    Ljava/lang/IllegalArgumentException;
        //  327    348    351    355    Ljava/lang/IllegalArgumentException;
        //  341    365    368    372    Ljava/lang/IllegalArgumentException;
        //  355    401    404    408    Ljava/lang/IllegalArgumentException;
        //  372    412    412    416    Ljava/lang/IllegalArgumentException;
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
    
    protected OCType.TypeCheckResult checkRefType(final OCCppReferenceType ocCppReferenceType) {
        return ocCppReferenceType.getRefType().accept(this);
    }
    
    public static int getTypesDifference(@Nullable final OCType p0, @Nullable final OCType p1, @NotNull final OCResolveContext p2) {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getTypesDifference"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: ifnull          59
        //    48: aload_1        
        //    49: ifnonnull       65
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: iconst_m1      
        //    60: ireturn        
        //    61: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    69: ifeq            86
        //    72: aload_1        
        //    73: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    76: ifne            114
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: aload_1        
        //    87: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    90: ifeq            140
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: aload_0        
        //   101: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   104: ifeq            140
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: bipush          100
        //   116: aload_0        
        //   117: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   120: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   123: aload_1        
        //   124: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   127: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   130: aload_2        
        //   131: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.getTypesDifference:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)I
        //   134: iadd           
        //   135: ireturn        
        //   136: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: aload_1        
        //   141: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   144: aload_0        
        //   145: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   148: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   151: ifne            260
        //   154: aload_0        
        //   155: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   158: aload_2        
        //   159: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   162: ifeq            203
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload_1        
        //   173: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   176: ifeq            203
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload_1        
        //   187: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   190: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnum:()Z
        //   193: ifne            260
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: aload_1        
        //   204: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   207: aload_2        
        //   208: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   211: ifeq            252
        //   214: goto            221
        //   217: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: aload_0        
        //   222: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   225: ifeq            252
        //   228: goto            235
        //   231: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   234: athrow         
        //   235: aload_0        
        //   236: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   239: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnum:()Z
        //   242: ifne            260
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   251: athrow         
        //   252: sipush          1000
        //   255: ireturn        
        //   256: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   259: athrow         
        //   260: aload_0        
        //   261: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   264: ifeq            359
        //   267: aload_1        
        //   268: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   271: ifeq            359
        //   274: goto            281
        //   277: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: aload_0        
        //   282: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   285: astore_3       
        //   286: aload_1        
        //   287: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   290: astore          4
        //   292: aload_3        
        //   293: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   296: invokevirtual   com/jetbrains/cidr/lang/types/CTypeId.ordinal:()I
        //   299: iconst_2       
        //   300: imul           
        //   301: aload_3        
        //   302: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.isSigned:()Z
        //   305: ifeq            316
        //   308: iconst_0       
        //   309: goto            317
        //   312: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   315: athrow         
        //   316: iconst_1       
        //   317: iadd           
        //   318: istore          5
        //   320: aload           4
        //   322: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   325: invokevirtual   com/jetbrains/cidr/lang/types/CTypeId.ordinal:()I
        //   328: iconst_2       
        //   329: imul           
        //   330: aload           4
        //   332: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.isSigned:()Z
        //   335: ifeq            346
        //   338: iconst_0       
        //   339: goto            347
        //   342: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   345: athrow         
        //   346: iconst_1       
        //   347: iadd           
        //   348: istore          6
        //   350: iload           5
        //   352: iload           6
        //   354: isub           
        //   355: invokestatic    java/lang/Math.abs:(I)I
        //   358: ireturn        
        //   359: aload_1        
        //   360: aload_0        
        //   361: aload_2        
        //   362: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   365: ifne            457
        //   368: aload_0        
        //   369: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   372: ifeq            390
        //   375: goto            382
        //   378: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   381: athrow         
        //   382: aload_0        
        //   383: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   386: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   389: astore_0       
        //   390: aload_1        
        //   391: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   394: ifeq            405
        //   397: aload_1        
        //   398: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   401: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   404: astore_1       
        //   405: aload_1        
        //   406: aload_0        
        //   407: iconst_0       
        //   408: aload_2        
        //   409: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   412: ifeq            422
        //   415: bipush          50
        //   417: ireturn        
        //   418: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   421: athrow         
        //   422: aload_0        
        //   423: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   426: ifne            444
        //   429: aload_0        
        //   430: aload_2        
        //   431: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   434: ifeq            453
        //   437: goto            444
        //   440: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   443: athrow         
        //   444: bipush          100
        //   446: goto            456
        //   449: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: sipush          200
        //   456: ireturn        
        //   457: iconst_0       
        //   458: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     52     55     59     Ljava/lang/IllegalArgumentException;
        //  48     61     61     65     Ljava/lang/IllegalArgumentException;
        //  65     79     82     86     Ljava/lang/IllegalArgumentException;
        //  72     93     96     100    Ljava/lang/IllegalArgumentException;
        //  86     107    110    114    Ljava/lang/IllegalArgumentException;
        //  100    136    136    140    Ljava/lang/IllegalArgumentException;
        //  140    165    168    172    Ljava/lang/IllegalArgumentException;
        //  154    179    182    186    Ljava/lang/IllegalArgumentException;
        //  172    196    199    203    Ljava/lang/IllegalArgumentException;
        //  186    214    217    221    Ljava/lang/IllegalArgumentException;
        //  203    228    231    235    Ljava/lang/IllegalArgumentException;
        //  221    245    248    252    Ljava/lang/IllegalArgumentException;
        //  235    256    256    260    Ljava/lang/IllegalArgumentException;
        //  260    274    277    281    Ljava/lang/IllegalArgumentException;
        //  292    312    312    316    Ljava/lang/IllegalArgumentException;
        //  320    342    342    346    Ljava/lang/IllegalArgumentException;
        //  359    375    378    382    Ljava/lang/IllegalArgumentException;
        //  405    418    418    422    Ljava/lang/IllegalArgumentException;
        //  422    437    440    444    Ljava/lang/IllegalArgumentException;
        //  429    449    449    453    Ljava/lang/IllegalArgumentException;
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
    
    protected boolean bothTypesEquals(final OCType ocType, final OCType ocType2, final OCType ocType3) {
        Label_0033: {
            try {
                if (!ocType.equals(ocType2, false, this.myResolveContext)) {
                    return false;
                }
                final OCType ocType4 = ocType;
                final OCType ocType5 = ocType3;
                final boolean b = false;
                final OCTypeCompatibilityVisitor ocTypeCompatibilityVisitor = this;
                final OCResolveContext ocResolveContext = ocTypeCompatibilityVisitor.myResolveContext;
                final boolean b2 = ocType4.equals(ocType5, b, ocResolveContext);
                if (b2) {
                    break Label_0033;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCType ocType4 = ocType;
                final OCType ocType5 = ocType3;
                final boolean b = false;
                final OCTypeCompatibilityVisitor ocTypeCompatibilityVisitor = this;
                final OCResolveContext ocResolveContext = ocTypeCompatibilityVisitor.myResolveContext;
                final boolean b2 = ocType4.equals(ocType5, b, ocResolveContext);
                if (b2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    protected OCType.TypeCheckResult visitType(final OCType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //     7: ifne            41
        //    10: aload_1        
        //    11: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //    14: ifne            41
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    28: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //    31: ifeq            49
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    44: areturn        
        //    45: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    53: ifne            73
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    60: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    63: ifeq            81
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: iconst_1       
        //    74: goto            82
        //    77: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: iconst_0       
        //    82: istore_2       
        //    83: iload_2        
        //    84: ifeq            127
        //    87: aload_1        
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //    92: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    95: aload_0        
        //    96: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    99: aload_0        
        //   100: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   103: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   106: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   109: ifeq            127
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   122: areturn        
        //   123: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   126: athrow         
        //   127: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$4;
        //   130: dup            
        //   131: aload_0        
        //   132: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   135: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatibleTypes;.class
        //   137: ldc             "CIDR"
        //   139: iconst_0       
        //   140: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   143: aload_1        
        //   144: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$4.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   147: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  10     34     37     41     Ljava/lang/IllegalArgumentException;
        //  24     45     45     49     Ljava/lang/IllegalArgumentException;
        //  49     66     69     73     Ljava/lang/IllegalArgumentException;
        //  56     77     77     81     Ljava/lang/IllegalArgumentException;
        //  83     112    115    119    Ljava/lang/IllegalArgumentException;
        //  87     123    123    127    Ljava/lang/IllegalArgumentException;
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
    
    protected String getSourceTypeName() {
        try {
            if (this.mySource instanceof OCExpression) {
                return ((OCExpression)this.mySource).findBestTypeName(this.mySourceType);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.mySourceType.getName(this.myResolveContext);
    }
    
    @NotNull
    protected OCType.TypeCheckResult visitNumericType(final OCNumericType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //     7: ifeq            59
        //    10: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    13: dup            
        //    14: ifnonnull       58
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: new             Ljava/lang/IllegalStateException;
        //    27: dup            
        //    28: ldc             "@NotNull method %s.%s must not return null"
        //    30: ldc             2
        //    32: anewarray       Ljava/lang/Object;
        //    35: dup            
        //    36: ldc             0
        //    38: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //    40: aastore        
        //    41: dup            
        //    42: ldc             1
        //    44: ldc             "visitNumericType"
        //    46: aastore        
        //    47: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    50: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    53: athrow         
        //    54: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: areturn        
        //    59: aload_0        
        //    60: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    63: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //    66: ifeq            923
        //    69: aload_0        
        //    70: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    73: checkcast       Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //    76: astore_2       
        //    77: aload_1        
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //    82: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    85: ifeq            158
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    92: aload_0        
        //    93: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //    96: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    99: ifeq            158
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   112: dup            
        //   113: ifnonnull       157
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: new             Ljava/lang/IllegalStateException;
        //   126: dup            
        //   127: ldc             "@NotNull method %s.%s must not return null"
        //   129: ldc             2
        //   131: anewarray       Ljava/lang/Object;
        //   134: dup            
        //   135: ldc             0
        //   137: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   139: aastore        
        //   140: dup            
        //   141: ldc             1
        //   143: ldc             "visitNumericType"
        //   145: aastore        
        //   146: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   149: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   152: athrow         
        //   153: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: areturn        
        //   158: aload_1        
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   163: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getRank:(Lcom/intellij/psi/PsiElement;)I
        //   166: aload_2        
        //   167: aload_0        
        //   168: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   171: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getRank:(Lcom/intellij/psi/PsiElement;)I
        //   174: if_icmpge       655
        //   177: aload_0        
        //   178: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   181: ifnull          457
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: aload_0        
        //   192: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   195: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   198: ifeq            457
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload_1        
        //   209: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   212: ifeq            457
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload_0        
        //   223: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   226: aload_0        
        //   227: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   230: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Number;
        //   233: astore_3       
        //   234: aload_3        
        //   235: ifnull          312
        //   238: aload_1        
        //   239: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   242: aload_3        
        //   243: invokestatic    com/jetbrains/cidr/lang/util/OCNumber.valueOf:(Ljava/lang/Object;)Lcom/jetbrains/cidr/lang/util/OCNumber;
        //   246: aload_0        
        //   247: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   250: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.canRepresent:(Lcom/jetbrains/cidr/lang/util/OCNumber;Lcom/intellij/psi/PsiElement;)Z
        //   253: ifeq            312
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   266: dup            
        //   267: ifnonnull       311
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: new             Ljava/lang/IllegalStateException;
        //   280: dup            
        //   281: ldc             "@NotNull method %s.%s must not return null"
        //   283: ldc             2
        //   285: anewarray       Ljava/lang/Object;
        //   288: dup            
        //   289: ldc             0
        //   291: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   293: aastore        
        //   294: dup            
        //   295: ldc             1
        //   297: ldc             "visitNumericType"
        //   299: aastore        
        //   300: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   303: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   306: athrow         
        //   307: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   310: athrow         
        //   311: areturn        
        //   312: aload_3        
        //   313: ifnonnull       392
        //   316: aload_1        
        //   317: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   320: aload_0        
        //   321: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   324: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   327: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   330: invokevirtual   com/jetbrains/cidr/lang/types/CTypeId.equals:(Ljava/lang/Object;)Z
        //   333: ifeq            392
        //   336: goto            343
        //   339: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   342: athrow         
        //   343: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   346: dup            
        //   347: ifnonnull       391
        //   350: goto            357
        //   353: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   356: athrow         
        //   357: new             Ljava/lang/IllegalStateException;
        //   360: dup            
        //   361: ldc             "@NotNull method %s.%s must not return null"
        //   363: ldc             2
        //   365: anewarray       Ljava/lang/Object;
        //   368: dup            
        //   369: ldc             0
        //   371: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   373: aastore        
        //   374: dup            
        //   375: ldc             1
        //   377: ldc             "visitNumericType"
        //   379: aastore        
        //   380: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   383: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   386: athrow         
        //   387: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   390: athrow         
        //   391: areturn        
        //   392: aload_0        
        //   393: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   396: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   399: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   402: getstatic       com/jetbrains/cidr/lang/types/CTypeId.SIZE_T:Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   405: if_acmpne       457
        //   408: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   411: dup            
        //   412: ifnonnull       456
        //   415: goto            422
        //   418: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   421: athrow         
        //   422: new             Ljava/lang/IllegalStateException;
        //   425: dup            
        //   426: ldc             "@NotNull method %s.%s must not return null"
        //   428: ldc             2
        //   430: anewarray       Ljava/lang/Object;
        //   433: dup            
        //   434: ldc             0
        //   436: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   438: aastore        
        //   439: dup            
        //   440: ldc             1
        //   442: ldc             "visitNumericType"
        //   444: aastore        
        //   445: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   448: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   451: athrow         
        //   452: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   455: athrow         
        //   456: areturn        
        //   457: aload_0        
        //   458: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   461: ifnull          596
        //   464: aload_0        
        //   465: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   468: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   471: ifeq            596
        //   474: goto            481
        //   477: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   480: athrow         
        //   481: aload_1        
        //   482: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   485: ifeq            596
        //   488: goto            495
        //   491: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   494: athrow         
        //   495: aload_0        
        //   496: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   499: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;)Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   502: astore_3       
        //   503: aload_3        
        //   504: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   507: ifeq            596
        //   510: aload_3        
        //   511: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   514: invokeinterface com/jetbrains/cidr/lang/psi/OCLiteralExpression.getUnescapedLiteralText:()Ljava/lang/String;
        //   519: invokestatic    com/jetbrains/cidr/lang/types/OCRealType.narrowestLiteralType:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   522: aload_0        
        //   523: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   526: invokevirtual   com/jetbrains/cidr/lang/types/OCRealType.getRank:(Lcom/intellij/psi/PsiElement;)I
        //   529: aload_1        
        //   530: aload_0        
        //   531: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   534: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getRank:(Lcom/intellij/psi/PsiElement;)I
        //   537: if_icmpne       596
        //   540: goto            547
        //   543: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   546: athrow         
        //   547: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   550: dup            
        //   551: ifnonnull       595
        //   554: goto            561
        //   557: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   560: athrow         
        //   561: new             Ljava/lang/IllegalStateException;
        //   564: dup            
        //   565: ldc             "@NotNull method %s.%s must not return null"
        //   567: ldc             2
        //   569: anewarray       Ljava/lang/Object;
        //   572: dup            
        //   573: ldc             0
        //   575: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   577: aastore        
        //   578: dup            
        //   579: ldc             1
        //   581: ldc             "visitNumericType"
        //   583: aastore        
        //   584: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   587: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   590: athrow         
        //   591: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   594: athrow         
        //   595: areturn        
        //   596: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$5;
        //   599: dup            
        //   600: aload_0        
        //   601: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   604: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ValueMayNotFitIntoReceiver;.class
        //   606: ldc             "warn_impcast_integer_precision"
        //   608: iconst_0       
        //   609: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   612: aload_1        
        //   613: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$5.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCNumericType;)V
        //   616: dup            
        //   617: ifnonnull       654
        //   620: new             Ljava/lang/IllegalStateException;
        //   623: dup            
        //   624: ldc             "@NotNull method %s.%s must not return null"
        //   626: ldc             2
        //   628: anewarray       Ljava/lang/Object;
        //   631: dup            
        //   632: ldc             0
        //   634: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   636: aastore        
        //   637: dup            
        //   638: ldc             1
        //   640: ldc             "visitNumericType"
        //   642: aastore        
        //   643: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   646: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   649: athrow         
        //   650: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   653: athrow         
        //   654: areturn        
        //   655: aload_1        
        //   656: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.isComplex:()Z
        //   659: ifne            742
        //   662: aload_2        
        //   663: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.isComplex:()Z
        //   666: ifeq            742
        //   669: goto            676
        //   672: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   675: athrow         
        //   676: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$6;
        //   679: dup            
        //   680: aload_0        
        //   681: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   684: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ValueMayNotFitIntoReceiver;.class
        //   686: ldc             "warn_impcast_complex_scalar"
        //   688: iconst_0       
        //   689: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   692: aload_1        
        //   693: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$6.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCNumericType;)V
        //   696: dup            
        //   697: ifnonnull       741
        //   700: goto            707
        //   703: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   706: athrow         
        //   707: new             Ljava/lang/IllegalStateException;
        //   710: dup            
        //   711: ldc             "@NotNull method %s.%s must not return null"
        //   713: ldc             2
        //   715: anewarray       Ljava/lang/Object;
        //   718: dup            
        //   719: ldc             0
        //   721: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   723: aastore        
        //   724: dup            
        //   725: ldc             1
        //   727: ldc             "visitNumericType"
        //   729: aastore        
        //   730: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   733: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   736: athrow         
        //   737: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   740: athrow         
        //   741: areturn        
        //   742: aload_1        
        //   743: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.isSigned:()Z
        //   746: ifne            881
        //   749: aload_2        
        //   750: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.isSigned:()Z
        //   753: ifeq            881
        //   756: goto            763
        //   759: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   762: athrow         
        //   763: aload_0        
        //   764: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   767: ifnull          815
        //   770: goto            777
        //   773: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   776: athrow         
        //   777: aload_0        
        //   778: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   781: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   784: ifeq            815
        //   787: goto            794
        //   790: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   793: athrow         
        //   794: aload_0        
        //   795: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   798: aload_0        
        //   799: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   802: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.isPositive:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   805: ifne            881
        //   808: goto            815
        //   811: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   814: athrow         
        //   815: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$7;
        //   818: dup            
        //   819: aload_0        
        //   820: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   823: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$SignednessMismatch;.class
        //   825: ldc             "warn_impcast_integer_sign"
        //   827: iconst_0       
        //   828: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   831: aload_1        
        //   832: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$7.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCNumericType;)V
        //   835: dup            
        //   836: ifnonnull       880
        //   839: goto            846
        //   842: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   845: athrow         
        //   846: new             Ljava/lang/IllegalStateException;
        //   849: dup            
        //   850: ldc             "@NotNull method %s.%s must not return null"
        //   852: ldc             2
        //   854: anewarray       Ljava/lang/Object;
        //   857: dup            
        //   858: ldc             0
        //   860: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   862: aastore        
        //   863: dup            
        //   864: ldc             1
        //   866: ldc             "visitNumericType"
        //   868: aastore        
        //   869: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   872: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   875: athrow         
        //   876: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   879: athrow         
        //   880: areturn        
        //   881: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   884: dup            
        //   885: ifnonnull       922
        //   888: new             Ljava/lang/IllegalStateException;
        //   891: dup            
        //   892: ldc             "@NotNull method %s.%s must not return null"
        //   894: ldc             2
        //   896: anewarray       Ljava/lang/Object;
        //   899: dup            
        //   900: ldc             0
        //   902: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   904: aastore        
        //   905: dup            
        //   906: ldc             1
        //   908: ldc             "visitNumericType"
        //   910: aastore        
        //   911: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   914: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   917: athrow         
        //   918: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   921: athrow         
        //   922: areturn        
        //   923: aload_0        
        //   924: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   927: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   930: ifeq            1083
        //   933: aload_0        
        //   934: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   937: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   940: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   943: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   946: if_acmpne       1083
        //   949: goto            956
        //   952: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   955: athrow         
        //   956: aload_0        
        //   957: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   960: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   963: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //   966: ifeq            1041
        //   969: goto            976
        //   972: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   975: athrow         
        //   976: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$8;
        //   979: dup            
        //   980: aload_0        
        //   981: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   984: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ImplicitIntegerAndEnumConversion;.class
        //   986: ldc             "CIDR"
        //   988: iconst_0       
        //   989: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   992: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$8.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   995: dup            
        //   996: ifnonnull       1040
        //   999: goto            1006
        //  1002: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1005: athrow         
        //  1006: new             Ljava/lang/IllegalStateException;
        //  1009: dup            
        //  1010: ldc             "@NotNull method %s.%s must not return null"
        //  1012: ldc             2
        //  1014: anewarray       Ljava/lang/Object;
        //  1017: dup            
        //  1018: ldc             0
        //  1020: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //  1022: aastore        
        //  1023: dup            
        //  1024: ldc             1
        //  1026: ldc             "visitNumericType"
        //  1028: aastore        
        //  1029: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1032: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1035: athrow         
        //  1036: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1039: athrow         
        //  1040: areturn        
        //  1041: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //  1044: dup            
        //  1045: ifnonnull       1082
        //  1048: new             Ljava/lang/IllegalStateException;
        //  1051: dup            
        //  1052: ldc             "@NotNull method %s.%s must not return null"
        //  1054: ldc             2
        //  1056: anewarray       Ljava/lang/Object;
        //  1059: dup            
        //  1060: ldc             0
        //  1062: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //  1064: aastore        
        //  1065: dup            
        //  1066: ldc             1
        //  1068: ldc             "visitNumericType"
        //  1070: aastore        
        //  1071: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1074: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1077: athrow         
        //  1078: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1081: athrow         
        //  1082: areturn        
        //  1083: aload_0        
        //  1084: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //  1087: aload_0        
        //  1088: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //  1091: iconst_0       
        //  1092: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;Z)Z
        //  1095: ifeq            1297
        //  1098: aload_1        
        //  1099: aload_0        
        //  1100: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //  1103: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //  1106: ifeq            1181
        //  1109: goto            1116
        //  1112: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1115: athrow         
        //  1116: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$9;
        //  1119: dup            
        //  1120: aload_0        
        //  1121: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.WARNING:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //  1124: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ImplicitPointerAndIntegerConversion;.class
        //  1126: ldc             "ext_typecheck_convert_pointer_int"
        //  1128: iconst_0       
        //  1129: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  1132: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$9.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  1135: dup            
        //  1136: ifnonnull       1180
        //  1139: goto            1146
        //  1142: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1145: athrow         
        //  1146: new             Ljava/lang/IllegalStateException;
        //  1149: dup            
        //  1150: ldc             "@NotNull method %s.%s must not return null"
        //  1152: ldc             2
        //  1154: anewarray       Ljava/lang/Object;
        //  1157: dup            
        //  1158: ldc             0
        //  1160: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //  1162: aastore        
        //  1163: dup            
        //  1164: ldc             1
        //  1166: ldc             "visitNumericType"
        //  1168: aastore        
        //  1169: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1172: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1175: athrow         
        //  1176: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1179: athrow         
        //  1180: areturn        
        //  1181: aload_1        
        //  1182: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //  1185: ifeq            1253
        //  1188: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$10;
        //  1191: dup            
        //  1192: aload_0        
        //  1193: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //  1196: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ImplicitPointerAndIntegerConversion;.class
        //  1198: ldc             "ext_typecheck_convert_pointer_int"
        //  1200: iconst_0       
        //  1201: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //  1204: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$10.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //  1207: dup            
        //  1208: ifnonnull       1252
        //  1211: goto            1218
        //  1214: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1217: athrow         
        //  1218: new             Ljava/lang/IllegalStateException;
        //  1221: dup            
        //  1222: ldc             "@NotNull method %s.%s must not return null"
        //  1224: ldc             2
        //  1226: anewarray       Ljava/lang/Object;
        //  1229: dup            
        //  1230: ldc             0
        //  1232: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //  1234: aastore        
        //  1235: dup            
        //  1236: ldc             1
        //  1238: ldc             "visitNumericType"
        //  1240: aastore        
        //  1241: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1244: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1247: athrow         
        //  1248: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1251: athrow         
        //  1252: areturn        
        //  1253: aload_0        
        //  1254: aload_1        
        //  1255: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.visitType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //  1258: dup            
        //  1259: ifnonnull       1296
        //  1262: new             Ljava/lang/IllegalStateException;
        //  1265: dup            
        //  1266: ldc             "@NotNull method %s.%s must not return null"
        //  1268: ldc             2
        //  1270: anewarray       Ljava/lang/Object;
        //  1273: dup            
        //  1274: ldc             0
        //  1276: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //  1278: aastore        
        //  1279: dup            
        //  1280: ldc             1
        //  1282: ldc             "visitNumericType"
        //  1284: aastore        
        //  1285: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1288: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1291: athrow         
        //  1292: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1295: athrow         
        //  1296: areturn        
        //  1297: aload_0        
        //  1298: aload_1        
        //  1299: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.visitType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //  1302: dup            
        //  1303: ifnonnull       1340
        //  1306: new             Ljava/lang/IllegalStateException;
        //  1309: dup            
        //  1310: ldc             "@NotNull method %s.%s must not return null"
        //  1312: ldc             2
        //  1314: anewarray       Ljava/lang/Object;
        //  1317: dup            
        //  1318: ldc             0
        //  1320: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //  1322: aastore        
        //  1323: dup            
        //  1324: ldc             1
        //  1326: ldc             "visitNumericType"
        //  1328: aastore        
        //  1329: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //  1332: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //  1335: athrow         
        //  1336: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1339: athrow         
        //  1340: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  10     54     54     58     Ljava/lang/IllegalArgumentException;
        //  77     102    105    109    Ljava/lang/IllegalArgumentException;
        //  88     116    119    123    Ljava/lang/IllegalArgumentException;
        //  109    153    153    157    Ljava/lang/IllegalArgumentException;
        //  158    184    187    191    Ljava/lang/IllegalArgumentException;
        //  177    201    204    208    Ljava/lang/IllegalArgumentException;
        //  191    215    218    222    Ljava/lang/IllegalArgumentException;
        //  234    256    259    263    Ljava/lang/IllegalArgumentException;
        //  238    270    273    277    Ljava/lang/IllegalArgumentException;
        //  263    307    307    311    Ljava/lang/IllegalArgumentException;
        //  312    336    339    343    Ljava/lang/IllegalArgumentException;
        //  316    350    353    357    Ljava/lang/IllegalArgumentException;
        //  343    387    387    391    Ljava/lang/IllegalArgumentException;
        //  392    415    418    422    Ljava/lang/IllegalArgumentException;
        //  408    452    452    456    Ljava/lang/IllegalArgumentException;
        //  457    474    477    481    Ljava/lang/IllegalArgumentException;
        //  464    488    491    495    Ljava/lang/IllegalArgumentException;
        //  503    540    543    547    Ljava/lang/IllegalArgumentException;
        //  510    554    557    561    Ljava/lang/IllegalArgumentException;
        //  547    591    591    595    Ljava/lang/IllegalArgumentException;
        //  596    650    650    654    Ljava/lang/IllegalArgumentException;
        //  655    669    672    676    Ljava/lang/IllegalArgumentException;
        //  662    700    703    707    Ljava/lang/IllegalArgumentException;
        //  676    737    737    741    Ljava/lang/IllegalArgumentException;
        //  742    756    759    763    Ljava/lang/IllegalArgumentException;
        //  749    770    773    777    Ljava/lang/IllegalArgumentException;
        //  763    787    790    794    Ljava/lang/IllegalArgumentException;
        //  777    808    811    815    Ljava/lang/IllegalArgumentException;
        //  794    839    842    846    Ljava/lang/IllegalArgumentException;
        //  815    876    876    880    Ljava/lang/IllegalArgumentException;
        //  881    918    918    922    Ljava/lang/IllegalArgumentException;
        //  923    949    952    956    Ljava/lang/IllegalArgumentException;
        //  933    969    972    976    Ljava/lang/IllegalArgumentException;
        //  956    999    1002   1006   Ljava/lang/IllegalArgumentException;
        //  976    1036   1036   1040   Ljava/lang/IllegalArgumentException;
        //  1041   1078   1078   1082   Ljava/lang/IllegalArgumentException;
        //  1083   1109   1112   1116   Ljava/lang/IllegalArgumentException;
        //  1098   1139   1142   1146   Ljava/lang/IllegalArgumentException;
        //  1116   1176   1176   1180   Ljava/lang/IllegalArgumentException;
        //  1181   1211   1214   1218   Ljava/lang/IllegalArgumentException;
        //  1188   1248   1248   1252   Ljava/lang/IllegalArgumentException;
        //  1253   1292   1292   1296   Ljava/lang/IllegalArgumentException;
        //  1297   1336   1336   1340   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
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
    
    @NotNull
    protected OCType.TypeCheckResult checkStructCompatibleCtor(final OCStructType ocStructType) {
        for (final OCStructSymbol ocStructSymbol : ocStructType.getStructs()) {
            OCType.TypeCheckResult typeCheckResult = this.processTransparentUnion(ocStructSymbol, ocStructType);
            Label_0090: {
                OCType.TypeCheckResult typeCheckResult2 = null;
                Label_0055: {
                    try {
                        if (typeCheckResult == null) {
                            break Label_0090;
                        }
                        typeCheckResult2 = typeCheckResult;
                        if (typeCheckResult2 == null) {
                            break Label_0055;
                        }
                        return typeCheckResult2;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        typeCheckResult2 = typeCheckResult;
                        if (typeCheckResult2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "checkStructCompatibleCtor"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return typeCheckResult2;
            }
            if (this.myAllowImplicitConversions) {
                typeCheckResult = this.processConstructors(ocStructSymbol);
            }
            OCType.TypeCheckResult typeCheckResult3 = null;
            Label_0122: {
                try {
                    if (typeCheckResult == null) {
                        continue;
                    }
                    typeCheckResult3 = typeCheckResult;
                    if (typeCheckResult3 == null) {
                        break Label_0122;
                    }
                    return typeCheckResult3;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    typeCheckResult3 = typeCheckResult;
                    if (typeCheckResult3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "checkStructCompatibleCtor"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return typeCheckResult3;
        }
        final OCType.TypeCheckResult visitType = this.visitType(ocStructType);
        OCType.TypeCheckResult typeCheckResult4 = null;
        Label_0214: {
            Label_0188: {
                try {
                    if (visitType == OCTypeCompatibilityVisitor.OK_RESULT) {
                        break Label_0214;
                    }
                    final OCTypeCompatibilityVisitor ocTypeCompatibilityVisitor = this;
                    final OCStructType ocStructType2 = ocStructType;
                    final boolean b = ocTypeCompatibilityVisitor.isCppClassType(ocStructType2);
                    if (b) {
                        break Label_0188;
                    }
                    break Label_0214;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final OCTypeCompatibilityVisitor ocTypeCompatibilityVisitor = this;
                    final OCStructType ocStructType2 = ocStructType;
                    final boolean b = ocTypeCompatibilityVisitor.isCppClassType(ocStructType2);
                    if (b) {
                        visitType.setQuickFixes(new IntentionAction[] { this.getNewConstructorFix(ocStructType.getSymbol()) });
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            try {
                typeCheckResult4 = visitType;
                if (typeCheckResult4 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "checkStructCompatibleCtor"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return typeCheckResult4;
    }
    
    @NotNull
    protected OCType.TypeCheckResult checkAssignToEnum(final OCStructType p0, final Computable<String> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //     3: astore_3       
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    12: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Number;
        //    15: astore          4
        //    17: aload           4
        //    19: ifnull          297
        //    22: aload_0        
        //    23: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //    26: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    29: ifeq            297
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aload_1        
        //    40: aload           4
        //    42: invokevirtual   java/lang/Number.intValue:()I
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    49: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.findMatchingEnumConst:(Lcom/jetbrains/cidr/lang/types/OCStructType;ILcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    52: astore          5
        //    54: aload           5
        //    56: ifnull          203
        //    59: aload           5
        //    61: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    66: aload_0        
        //    67: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //    70: checkcast       Lcom/intellij/psi/PsiElement;
        //    73: iconst_0       
        //    74: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    77: astore          6
        //    79: aload           6
        //    81: ifnull          200
        //    84: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeElementIntentionAction;
        //    87: dup            
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //    92: checkcast       Lcom/intellij/psi/PsiElement;
        //    95: aload           6
        //    97: new             Ljava/lang/StringBuilder;
        //   100: dup            
        //   101: invokespecial   java/lang/StringBuilder.<init>:()V
        //   104: ldc             "Use constant '"
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   109: aload           5
        //   111: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: ldc             "'"
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   127: ldc             "Use enum constant"
        //   129: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeElementIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)V
        //   132: astore          7
        //   134: aload_2        
        //   135: astore          8
        //   137: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$11;
        //   140: dup            
        //   141: aload_0        
        //   142: aload_3        
        //   143: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ImplicitIntegerAndEnumConversion;.class
        //   145: ldc             "CIDR"
        //   147: iconst_1       
        //   148: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   151: dup            
        //   152: iconst_0       
        //   153: aload           7
        //   155: aastore        
        //   156: aload           8
        //   158: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$11.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/intellij/openapi/util/Computable;)V
        //   161: dup            
        //   162: ifnonnull       199
        //   165: new             Ljava/lang/IllegalStateException;
        //   168: dup            
        //   169: ldc             "@NotNull method %s.%s must not return null"
        //   171: ldc             2
        //   173: anewarray       Ljava/lang/Object;
        //   176: dup            
        //   177: ldc             0
        //   179: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   181: aastore        
        //   182: dup            
        //   183: ldc             1
        //   185: ldc             "checkAssignToEnum"
        //   187: aastore        
        //   188: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   191: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   194: athrow         
        //   195: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: areturn        
        //   200: goto            297
        //   203: aload_1        
        //   204: aload_0        
        //   205: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   208: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   211: astore          6
        //   213: aload           4
        //   215: invokevirtual   java/lang/Number.intValue:()I
        //   218: ifne            287
        //   221: aload           6
        //   223: ldc             "Options"
        //   225: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   228: ifeq            287
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   241: dup            
        //   242: ifnonnull       286
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   251: athrow         
        //   252: new             Ljava/lang/IllegalStateException;
        //   255: dup            
        //   256: ldc             "@NotNull method %s.%s must not return null"
        //   258: ldc             2
        //   260: anewarray       Ljava/lang/Object;
        //   263: dup            
        //   264: ldc             0
        //   266: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   268: aastore        
        //   269: dup            
        //   270: ldc             1
        //   272: ldc             "checkAssignToEnum"
        //   274: aastore        
        //   275: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   278: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   281: athrow         
        //   282: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   285: athrow         
        //   286: areturn        
        //   287: aload           6
        //   289: aload           4
        //   291: invokedynamic   compute:(Ljava/lang/String;Ljava/lang/Number;)Lcom/intellij/openapi/util/Computable;
        //   296: astore_2       
        //   297: aload_2        
        //   298: astore          5
        //   300: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$12;
        //   303: dup            
        //   304: aload_0        
        //   305: aload_3        
        //   306: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$ImplicitIntegerAndEnumConversion;.class
        //   308: ldc             "CIDR"
        //   310: iconst_0       
        //   311: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   314: aload           5
        //   316: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$12.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/intellij/openapi/util/Computable;)V
        //   319: dup            
        //   320: ifnonnull       357
        //   323: new             Ljava/lang/IllegalStateException;
        //   326: dup            
        //   327: ldc             "@NotNull method %s.%s must not return null"
        //   329: ldc             2
        //   331: anewarray       Ljava/lang/Object;
        //   334: dup            
        //   335: ldc             0
        //   337: ldc             "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor"
        //   339: aastore        
        //   340: dup            
        //   341: ldc             1
        //   343: ldc             "checkAssignToEnum"
        //   345: aastore        
        //   346: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   349: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   352: athrow         
        //   353: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   356: athrow         
        //   357: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/types/OCStructType;Lcom/intellij/openapi/util/Computable<Ljava/lang/String;>;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  17     32     35     39     Ljava/lang/IllegalArgumentException;
        //  137    195    195    199    Ljava/lang/IllegalArgumentException;
        //  213    231    234    238    Ljava/lang/IllegalArgumentException;
        //  221    245    248    252    Ljava/lang/IllegalArgumentException;
        //  238    282    282    286    Ljava/lang/IllegalArgumentException;
        //  300    353    353    357    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0238:
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
    public OCType.TypeCheckResult visitEllipsisReferenceType(final OCEllipsisType ocEllipsisType) {
        return OCTypeCompatibilityVisitor.OK_RESULT;
    }
    
    protected boolean isSuperTypeForFunctionChecks(final OCType p0, final OCType p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //     4: ifeq            35
        //     7: aload_2        
        //     8: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    11: ifeq            35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_1        
        //    22: aload_2        
        //    23: aload_0        
        //    24: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //    27: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    30: ireturn        
        //    31: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_1        
        //    36: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    39: ifeq            99
        //    42: aload_2        
        //    43: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    46: ifeq            99
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_2        
        //    57: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    60: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    63: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    66: if_acmpne       99
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_2        
        //    77: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    80: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //    83: ifne            99
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: iconst_1       
        //    94: ireturn        
        //    95: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: aload_2        
        //   100: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   103: ifeq            163
        //   106: aload_1        
        //   107: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   110: ifeq            163
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   119: athrow         
        //   120: aload_1        
        //   121: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   124: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   127: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   130: if_acmpne       163
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: aload_1        
        //   141: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   144: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isEnumClass:()Z
        //   147: ifne            163
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: iconst_1       
        //   158: ireturn        
        //   159: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   167: ifnull          186
        //   170: aload_0        
        //   171: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //   174: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   179: goto            187
        //   182: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aconst_null    
        //   187: astore_3       
        //   188: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor;
        //   191: dup            
        //   192: aload_2        
        //   193: iconst_0       
        //   194: iconst_1       
        //   195: iconst_0       
        //   196: iconst_0       
        //   197: iconst_1       
        //   198: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   201: dup            
        //   202: aload_3        
        //   203: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   206: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   209: aload_1        
        //   210: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.equal:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   213: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      31     31     35     Ljava/lang/IllegalArgumentException;
        //  35     49     52     56     Ljava/lang/IllegalArgumentException;
        //  42     69     72     76     Ljava/lang/IllegalArgumentException;
        //  56     86     89     93     Ljava/lang/IllegalArgumentException;
        //  76     95     95     99     Ljava/lang/IllegalArgumentException;
        //  99     113    116    120    Ljava/lang/IllegalArgumentException;
        //  106    133    136    140    Ljava/lang/IllegalArgumentException;
        //  120    150    153    157    Ljava/lang/IllegalArgumentException;
        //  140    159    159    163    Ljava/lang/IllegalArgumentException;
        //  163    182    182    186    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0056:
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
    public OCType.TypeCheckResult visitArrayType(final OCArrayType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myAssumeNullSubstitutionsEquals:Z
        //     4: ifne            15
        //     7: iconst_1       
        //     8: goto            16
        //    11: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    14: athrow         
        //    15: iconst_0       
        //    16: istore_2       
        //    17: iload_2        
        //    18: ifeq            70
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    25: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    28: ifeq            70
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_0        
        //    39: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    42: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    45: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getLength:()I
        //    48: aload_1        
        //    49: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getLength:()I
        //    52: if_icmpne       70
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    65: areturn        
        //    66: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_1        
        //    71: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //    74: ifne            90
        //    77: aload_0        
        //    78: aload_1        
        //    79: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.visitPointerType:(Lcom/jetbrains/cidr/lang/types/OCPointerType;)Ljava/lang/Object;
        //    82: checkcast       Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    85: areturn        
        //    86: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$13;
        //    93: dup            
        //    94: aload_0        
        //    95: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //    98: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$NotAssignable;.class
        //   100: ldc             "err_typecheck_array_not_modifiable_lvalue"
        //   102: iconst_0       
        //   103: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   106: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$13.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   109: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     11     15     Ljava/lang/IllegalArgumentException;
        //  17     31     34     38     Ljava/lang/IllegalArgumentException;
        //  21     55     58     62     Ljava/lang/IllegalArgumentException;
        //  38     66     66     70     Ljava/lang/IllegalArgumentException;
        //  70     86     86     90     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    
    protected boolean isCppClassType(final OCType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //     4: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //     9: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    17: ifeq            62
        //    20: aload_1        
        //    21: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    24: ifeq            62
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    33: athrow         
        //    34: aload_1        
        //    35: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    38: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    41: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    44: if_acmpne       62
        //    47: goto            54
        //    50: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: iconst_1       
        //    55: goto            63
        //    58: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: iconst_0       
        //    63: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      27     30     34     Ljava/lang/IllegalArgumentException;
        //  20     47     50     54     Ljava/lang/IllegalArgumentException;
        //  34     58     58     62     Ljava/lang/IllegalArgumentException;
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
    protected OCType.TypeCheckResult checkArcBridgeCast(final OCPointerType p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     7: ifeq            43
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    22: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //    25: ifeq            43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: istore_3       
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //    49: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //    52: ifeq            78
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    59: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToString:()Z
        //    62: ifeq            78
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: aconst_null    
        //    73: areturn        
        //    74: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload_0        
        //    79: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //    82: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    85: ifeq            111
        //    88: aload_0        
        //    89: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    92: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToVoid:()Z
        //    95: ifeq            111
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aconst_null    
        //   106: areturn        
        //   107: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_1        
        //   112: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   115: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   118: ifeq            161
        //   121: aload_0        
        //   122: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   125: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   128: ifne            161
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload_0        
        //   139: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   142: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToPointerToObjectCompatible:()Z
        //   145: ifeq            161
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: aconst_null    
        //   156: areturn        
        //   157: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: iload_3        
        //   162: ifeq            216
        //   165: aload_0        
        //   166: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   169: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToVoid:()Z
        //   172: ifeq            216
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload_1        
        //   183: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToObjectCompatible:()Z
        //   186: ifne            216
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: aload_1        
        //   197: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToPointerToObjectCompatible:()Z
        //   200: ifeq            216
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: aconst_null    
        //   211: areturn        
        //   212: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: iload_3        
        //   217: ifeq            623
        //   220: aload_1        
        //   221: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToObjectCompatible:()Z
        //   224: ifne            251
        //   227: goto            234
        //   230: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   233: athrow         
        //   234: aload_0        
        //   235: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   238: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   241: ifeq            623
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload_1        
        //   252: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToObject:()Z
        //   255: ifeq            457
        //   258: goto            265
        //   261: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: aload_0        
        //   266: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   269: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   272: ifeq            302
        //   275: goto            282
        //   278: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   281: athrow         
        //   282: aload_0        
        //   283: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   286: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   289: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getFunctionReferenceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   294: invokestatic    com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getSymbol:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   297: astore          4
        //   299: goto            361
        //   302: aload_0        
        //   303: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   306: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   309: ifeq            349
        //   312: aload_0        
        //   313: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   316: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   319: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
        //   324: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getKnownResponder:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   327: astore          4
        //   329: aload           4
        //   331: ifnull          361
        //   334: aload           4
        //   336: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   339: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   344: astore          4
        //   346: goto            361
        //   349: aload_0        
        //   350: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   353: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   356: invokestatic    com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getSymbol:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   359: astore          4
        //   361: aload           4
        //   363: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   366: ifeq            394
        //   369: aload           4
        //   371: ldc             "ImplicitBridging"
        //   373: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.hasAttribute:(Ljava/lang/String;)Z
        //   378: ifeq            394
        //   381: goto            388
        //   384: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   387: athrow         
        //   388: aconst_null    
        //   389: areturn        
        //   390: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   393: athrow         
        //   394: aload           4
        //   396: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   399: ifeq            408
        //   402: aconst_null    
        //   403: areturn        
        //   404: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   407: athrow         
        //   408: aload           4
        //   410: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   413: ifeq            457
        //   416: aload           4
        //   418: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   421: ifne            457
        //   424: goto            431
        //   427: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   430: athrow         
        //   431: aload           4
        //   433: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   438: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   441: if_acmpne       457
        //   444: goto            451
        //   447: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   450: athrow         
        //   451: aconst_null    
        //   452: areturn        
        //   453: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   456: athrow         
        //   457: aload_0        
        //   458: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   461: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   464: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   469: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //   472: invokeinterface com/jetbrains/cidr/lang/psi/OCCastExpression.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   477: astore          4
        //   479: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.BRIDGE_CAST_KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   482: invokevirtual   com/intellij/psi/tree/TokenSet.getTypes:()[Lcom/intellij/psi/tree/IElementType;
        //   485: astore          5
        //   487: aload           5
        //   489: arraylength    
        //   490: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   493: astore          6
        //   495: iconst_0       
        //   496: istore          7
        //   498: iload           7
        //   500: aload           6
        //   502: arraylength    
        //   503: if_icmpge       604
        //   506: aload           5
        //   508: iload           7
        //   510: aaload         
        //   511: checkcast       Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   514: invokevirtual   com/jetbrains/cidr/lang/parser/OCElementType.getName:()Ljava/lang/String;
        //   517: astore          8
        //   519: aload           6
        //   521: iload           7
        //   523: new             Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction;
        //   526: dup            
        //   527: aload_0        
        //   528: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySource:Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   531: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   534: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   539: aload           4
        //   541: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getTextOffset:()I
        //   546: iconst_0       
        //   547: new             Ljava/lang/StringBuilder;
        //   550: dup            
        //   551: invokespecial   java/lang/StringBuilder.<init>:()V
        //   554: aload           8
        //   556: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   559: ldc             " "
        //   561: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   564: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   567: new             Ljava/lang/StringBuilder;
        //   570: dup            
        //   571: invokespecial   java/lang/StringBuilder.<init>:()V
        //   574: ldc             "Add \""
        //   576: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   579: aload           8
        //   581: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   584: ldc             "\""
        //   586: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   589: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   592: ldc             "Add the bridge cast"
        //   594: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTextIntentionAction.<init>:(Lcom/intellij/psi/PsiFile;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   597: aastore        
        //   598: iinc            7, 1
        //   601: goto            498
        //   604: new             Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   607: dup            
        //   608: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   611: ldc             "ARC bridge cast is required"
        //   613: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$BridgeCastIssues;.class
        //   615: ldc             "CIDR"
        //   617: aload           6
        //   619: invokespecial   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.<init>:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   622: areturn        
        //   623: iload_2        
        //   624: ifeq            652
        //   627: ldc             "ARC bridge cast is required for toll free bridge"
        //   629: astore          4
        //   631: new             Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   634: dup            
        //   635: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   638: aload           4
        //   640: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$BridgeCastIssues;.class
        //   642: ldc             "CIDR"
        //   644: iconst_0       
        //   645: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   648: invokespecial   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.<init>:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   651: areturn        
        //   652: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$14;
        //   655: dup            
        //   656: aload_0        
        //   657: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   660: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatibleTypes;.class
        //   662: ldc             "err_typecheck_convert_incompatible"
        //   664: iconst_0       
        //   665: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   668: aload_1        
        //   669: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$14.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCPointerType;)V
        //   672: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      28     31     35     Ljava/lang/IllegalArgumentException;
        //  10     39     39     43     Ljava/lang/IllegalArgumentException;
        //  45     65     68     72     Ljava/lang/IllegalArgumentException;
        //  55     74     74     78     Ljava/lang/IllegalArgumentException;
        //  78     98     101    105    Ljava/lang/IllegalArgumentException;
        //  88     107    107    111    Ljava/lang/IllegalArgumentException;
        //  111    131    134    138    Ljava/lang/IllegalArgumentException;
        //  121    148    151    155    Ljava/lang/IllegalArgumentException;
        //  138    157    157    161    Ljava/lang/IllegalArgumentException;
        //  161    175    178    182    Ljava/lang/IllegalArgumentException;
        //  165    189    192    196    Ljava/lang/IllegalArgumentException;
        //  182    203    206    210    Ljava/lang/IllegalArgumentException;
        //  196    212    212    216    Ljava/lang/IllegalArgumentException;
        //  216    227    230    234    Ljava/lang/IllegalArgumentException;
        //  220    244    247    251    Ljava/lang/IllegalArgumentException;
        //  234    258    261    265    Ljava/lang/IllegalArgumentException;
        //  251    275    278    282    Ljava/lang/IllegalArgumentException;
        //  361    381    384    388    Ljava/lang/IllegalArgumentException;
        //  369    390    390    394    Ljava/lang/IllegalArgumentException;
        //  394    404    404    408    Ljava/lang/IllegalArgumentException;
        //  408    424    427    431    Ljava/lang/IllegalArgumentException;
        //  416    444    447    451    Ljava/lang/IllegalArgumentException;
        //  431    453    453    457    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0138:
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
    public OCType.TypeCheckResult visitBlockPointerType(final OCBlockPointerType ocBlockPointerType) {
        return this.visitPointerType(ocBlockPointerType);
    }
    
    @Override
    public OCType.TypeCheckResult visitIdType(final OCIdType ocIdType) {
        return this.visitObjectType(ocIdType);
    }
    
    @Override
    public OCType.TypeCheckResult visitIntType(final OCIntType ocIntType) {
        return this.visitNumericType(ocIntType);
    }
    
    @Override
    public OCType.TypeCheckResult visitRealType(final OCRealType ocRealType) {
        return this.visitNumericType(ocRealType);
    }
    
    @Override
    public OCType.TypeCheckResult visitReferenceType(final OCReferenceType ocReferenceType) {
        return this.visitType(ocReferenceType);
    }
    
    @Override
    public OCType.TypeCheckResult visitAutoType(final OCAutoType ocAutoType) {
        return OCTypeCompatibilityVisitor.OK_RESULT;
    }
    
    protected OCCreateNewDefinitionIntentionAction getNewConstructorFix(final OCStructSymbol ocStructSymbol) {
        try {
            if (this.mySource instanceof OCExpression) {
                final OCExpression ocExpression = (OCExpression)this.mySource;
                return new OCCreateNewDefinitionIntentionAction(OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION, this.myContext, ocStructSymbol, ocStructSymbol.getName(), new OCFunctionType(OCVoidType.instance(), Collections.singletonList(OCExpectedTypeUtil.getExpressionType(ocExpression, this.mySourceType, true))));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCExpression ocExpression = null;
        return new OCCreateNewDefinitionIntentionAction(OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION, this.myContext, ocStructSymbol, ocStructSymbol.getName(), new OCFunctionType(OCVoidType.instance(), Collections.singletonList(OCExpectedTypeUtil.getExpressionType(ocExpression, this.mySourceType, true))));
    }
    
    @Nullable
    protected OCType.TypeCheckResult processConstructors(final OCStructSymbol ocStructSymbol) {
        List<T> singletonList = null;
        List<OCTypeOwner> singletonList2 = null;
        Label_0033: {
            try {
                singletonList = Collections.singletonList(this.mySourceType);
                if (this.mySource != null) {
                    singletonList2 = Collections.singletonList(this.mySource);
                    break Label_0033;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            singletonList2 = null;
        }
        final OCSymbol constructor = ocStructSymbol.getType().findConstructor(new OCArgumentsList<Object>((List<OCType>)singletonList, singletonList2), this.myResolveContext, false, true, (Producer<Boolean>)(() -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
            //     4: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
            //     7: ifeq            76
            //    10: aload_0        
            //    11: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
            //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
            //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getFunctionReferenceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //    22: invokestatic    com/jetbrains/cidr/lang/daemon/OCGetSymbolVisitor.getSymbol:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    25: astore_1       
            //    26: aload_1        
            //    27: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //    30: ifne            63
            //    33: aload_1        
            //    34: ifnull          71
            //    37: goto            44
            //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_1        
            //    45: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //    50: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //    53: if_acmpne       71
            //    56: goto            63
            //    59: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    62: athrow         
            //    63: iconst_1       
            //    64: goto            72
            //    67: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    70: athrow         
            //    71: iconst_0       
            //    72: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //    75: areturn        
            //    76: iconst_0       
            //    77: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //    80: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  26     37     40     44     Ljava/lang/IllegalArgumentException;
            //  33     56     59     63     Ljava/lang/IllegalArgumentException;
            //  44     67     67     71     Ljava/lang/IllegalArgumentException;
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
        }));
        Label_0085: {
            try {
                if (!(constructor instanceof OCResolveOverloadsUtil.OCFunctionGroupSymbol)) {
                    break Label_0085;
                }
                final OCSymbol ocSymbol = constructor;
                final OCResolveOverloadsUtil.OCFunctionGroupSymbol ocFunctionGroupSymbol = (OCResolveOverloadsUtil.OCFunctionGroupSymbol)ocSymbol;
                final OCResolveOverloadsUtil.OCFunctionGroupSymbol.Cause cause = ocFunctionGroupSymbol.getCause();
                final OCResolveOverloadsUtil.OCFunctionGroupSymbol.Cause cause2 = OCResolveOverloadsUtil.OCFunctionGroupSymbol.Cause.Magic;
                if (cause != cause2) {
                    break Label_0085;
                }
                break Label_0085;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbol ocSymbol = constructor;
                final OCResolveOverloadsUtil.OCFunctionGroupSymbol ocFunctionGroupSymbol = (OCResolveOverloadsUtil.OCFunctionGroupSymbol)ocSymbol;
                final OCResolveOverloadsUtil.OCFunctionGroupSymbol.Cause cause = ocFunctionGroupSymbol.getCause();
                final OCResolveOverloadsUtil.OCFunctionGroupSymbol.Cause cause2 = OCResolveOverloadsUtil.OCFunctionGroupSymbol.Cause.Magic;
                if (cause != cause2) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        if (constructor instanceof OCFunctionSymbol) {
            final OCType resolve = ((OCResolveOverloadsUtil.OCFunctionGroupSymbol)constructor).getType().getParameterTypes().get(0).resolve(this.myResolveContext);
            OCType.TypeCheckResult checkConvertible = checkConvertible(resolve, this.mySourceType, this.mySource, this.myContext, false, false, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
            if (checkConvertible == OCTypeCompatibilityVisitor.OK_RESULT) {
                checkConvertible = new OCType.TypeCheckResult(OCType.TypeCheckState.OK, null, null, resolve, ocStructSymbol.getType(), new IntentionAction[0]);
            }
            else {
                checkConvertible.setConversion(resolve, ocStructSymbol.getType());
            }
            checkConvertible.setImplicitConstructor((OCFunctionSymbol)constructor);
            return checkConvertible;
        }
        return null;
    }
    
    @Nullable
    protected OCType.TypeCheckResult processTransparentUnion(final OCStructSymbol ocStructSymbol, final OCStructType ocStructType) {
        Label_0024: {
            try {
                if (ocStructType.getKind() != OCSymbolKind.UNION) {
                    break Label_0024;
                }
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final boolean b = ocStructSymbol2.isTransparentUnion();
                if (!b) {
                    break Label_0024;
                }
                break Label_0024;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final boolean b = ocStructSymbol2.isTransparentUnion();
                if (!b) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final Ref create = Ref.create((Object)null);
        ocStructSymbol.processFields((Processor<OCDeclaratorSymbol>)new CommonProcessors.FindFirstProcessor<OCDeclaratorSymbol>() {
            protected boolean accept(final OCDeclaratorSymbol ocDeclaratorSymbol) {
                final OCType.TypeCheckResult checkCompatible = ocDeclaratorSymbol.getType().resolve((PsiFile)ocStructSymbol.getContainingOCFile()).checkCompatible(OCTypeCompatibilityVisitor.this.mySourceType, OCTypeCompatibilityVisitor.this.mySource, OCTypeCompatibilityVisitor.this.myContext);
                if (checkCompatible.getState() == OCType.TypeCheckState.OK) {
                    create.set((Object)checkCompatible);
                    return true;
                }
                if (!checkCompatible.getState().isError(OCTypeCompatibilityVisitor.this.myContext) && create.isNull()) {
                    create.set((Object)checkCompatible);
                }
                return false;
            }
        });
        return (OCType.TypeCheckResult)create.get();
    }
    
    @Override
    public OCType.TypeCheckResult visitVariadicType(final OCVariadicType ocVariadicType) {
        return OCTypeCompatibilityVisitor.OK_RESULT;
    }
    
    @Override
    public OCType.TypeCheckResult visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        return OCTypeCompatibilityVisitor.OK_RESULT;
    }
    
    @Override
    public OCType.TypeCheckResult visitUnknownType(final OCUnknownType ocUnknownType) {
        return this.visitMagicType((OCMagicType)ocUnknownType);
    }
    
    @Override
    public OCType.TypeCheckResult visitMagicType(final OCMagicType ocMagicType) {
        return OCTypeCompatibilityVisitor.OK_RESULT;
    }
    
    @Override
    public OCType.TypeCheckResult visitVoidType(final OCVoidType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //     7: ifne            44
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    14: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    17: ifne            44
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myContext:Lcom/intellij/psi/PsiElement;
        //    31: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCastExpression;
        //    34: ifeq            52
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    47: areturn        
        //    48: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$16;
        //    55: dup            
        //    56: aload_0        
        //    57: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //    60: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatibleTypes;.class
        //    62: ldc             "err_typecheck_convert_incompatible"
        //    64: iconst_0       
        //    65: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //    68: aload_1        
        //    69: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$16.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Lcom/jetbrains/cidr/lang/types/OCVoidType;)V
        //    72: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      20     23     27     Ljava/lang/IllegalArgumentException;
        //  10     37     40     44     Ljava/lang/IllegalArgumentException;
        //  27     48     48     52     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    public OCType.TypeCheckResult visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        return this.visitMagicType((OCMagicType)ocTypeParameterType);
    }
    
    public static OCType.TypeCheckResult checkCompoundInitializer(final OCCompoundInitializer ocCompoundInitializer, final OCType ocType, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "checkCompoundInitializer"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCTypeCheckArgumentsChecker ocTypeCheckArgumentsChecker = new OCTypeCheckArgumentsChecker(ocResolveContext);
        RecursionManager.doPreventingRecursion((Object)new Pair((Object)ocCompoundInitializer, (Object)b), false, () -> {
            ocTypeCheckArgumentsChecker.checkCompoundInitializer(ocCompoundInitializer, ocType, false, b);
            return null;
        });
        OCType.TypeCheckResult ok_RESULT = OCTypeCompatibilityVisitor.OK_RESULT;
        for (final OCType.TypeCheckResult typeCheckResult : ocTypeCheckArgumentsChecker.getResults()) {
            if (typeCheckResult.getState().ordinal() > ok_RESULT.getState().ordinal()) {
                ok_RESULT = typeCheckResult;
            }
        }
        return ok_RESULT;
    }
    
    @Nullable
    protected OCType.TypeCheckResult checkAssignPointerToArray(final OCArrayType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //     7: ifeq            178
        //    10: aload_1        
        //    11: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    14: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    17: astore_2       
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.mySourceType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    22: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    25: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    28: astore_3       
        //    29: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    32: aload_2        
        //    33: iconst_0       
        //    34: aload_0        
        //    35: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    38: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    41: ifne            88
        //    44: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    47: aload_2        
        //    48: iconst_0       
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    53: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    56: ifne            88
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    69: aload_2        
        //    70: iconst_0       
        //    71: aload_0        
        //    72: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    75: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    78: ifeq            118
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    91: aload_3        
        //    92: iconst_0       
        //    93: aload_0        
        //    94: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.myResolveContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    97: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   100: ifeq            118
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   113: areturn        
        //   114: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: aload_0        
        //   119: getstatic       com/jetbrains/cidr/lang/types/OCIntType.WCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   122: aload_2        
        //   123: aload_3        
        //   124: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.bothTypesEquals:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   127: ifeq            138
        //   130: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   133: areturn        
        //   134: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload_0        
        //   139: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR16:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   142: aload_2        
        //   143: aload_3        
        //   144: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.bothTypesEquals:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   147: ifeq            158
        //   150: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   153: areturn        
        //   154: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: aload_0        
        //   159: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR32:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   162: aload_2        
        //   163: aload_3        
        //   164: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.bothTypesEquals:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   167: ifeq            178
        //   170: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   173: areturn        
        //   174: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aconst_null    
        //   179: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  29     59     62     66     Ljava/lang/IllegalArgumentException;
        //  44     81     84     88     Ljava/lang/IllegalArgumentException;
        //  66     103    106    110    Ljava/lang/IllegalArgumentException;
        //  88     114    114    118    Ljava/lang/IllegalArgumentException;
        //  118    134    134    138    Ljava/lang/IllegalArgumentException;
        //  138    154    154    158    Ljava/lang/IllegalArgumentException;
        //  158    174    174    178    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
    
    @NotNull
    protected OCType.TypeCheckResult getProtocolCompatibilityCheckResult(@NotNull final OCObjectType ocObjectType, @NotNull final OCObjectType ocObjectType2) {
        try {
            if (ocObjectType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "getProtocolCompatibilityCheckResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocObjectType2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "getProtocolCompatibilityCheckResult"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final OCProtocolSymbol ocProtocolSymbol : ocObjectType2.getAllProtocols()) {
            if (!ocObjectType.implementsProtocol(ocProtocolSymbol)) {
                final OCImplementation ocImplementation = (OCImplementation)PsiTreeUtil.getParentOfType(this.myContext, (Class)OCImplementation.class);
                final OCClassSymbol classSymbol = ocObjectType.getClassSymbol();
                OCType.TypeCheckResult typeCheckResult;
                try {
                    typeCheckResult = new OCType.TypeCheckResult(OCType.TypeCheckState.WARNING, OCInspections.NotImplementsProtocol.class, "CIDR", new IntentionAction[] { new OCAddSuperProtocolIntentionAction(ocObjectType.getInterface(), ocProtocolSymbol.getName(), false), new OCAddSuperProtocolIntentionAction(ocObjectType.getImplementation(), ocProtocolSymbol.getName(), true) {
                            @Override
                            protected boolean isAvailable(final OCClassSymbol ocClassSymbol) {
                                return ocImplementation != null && classSymbol != null && classSymbol.getName().equals(ocImplementation.getName()) && super.isAvailable(ocClassSymbol);
                            }
                        } }) {
                        @Override
                        public String getMessage() {
                            return "Interface '" + OCTypeCompatibilityVisitor.this.getSourceTypeName() + "' doesn't implement " + ocProtocolSymbol.getNameWithKindLowercase();
                        }
                    };
                    if (typeCheckResult == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "getProtocolCompatibilityCheckResult"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                return typeCheckResult;
            }
        }
        OCType.TypeCheckResult ok_RESULT;
        try {
            ok_RESULT = OCTypeCompatibilityVisitor.OK_RESULT;
            if (ok_RESULT == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor", "getProtocolCompatibilityCheckResult"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return ok_RESULT;
    }
    
    static {
        OK_RESULT = new OCType.TypeCheckResult(OCType.TypeCheckState.OK);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class OCTypeCompatibilityVisitorCreator implements OCTypeVisitor<OCTypeCompatibilityVisitor<? extends OCType>>
    {
        private final OCTypeOwner mySource;
        @Nullable
        private final PsiElement myContext;
        private final boolean myAllowImplicitConversions;
        private final boolean myAssumeNullSubstitutionsEquals;
        @NotNull
        private final OCResolveContext myResolveContext;
        
        public OCTypeCompatibilityVisitorCreator(@Nullable final OCTypeOwner mySource, @Nullable final PsiElement myContext, final boolean myAllowImplicitConversions, final boolean myAssumeNullSubstitutionsEquals, @NotNull final OCResolveContext myResolveContext) {
            if (myResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$OCTypeCompatibilityVisitorCreator", "<init>"));
            }
            this.mySource = mySource;
            this.myContext = myContext;
            this.myAllowImplicitConversions = myAllowImplicitConversions;
            this.myAssumeNullSubstitutionsEquals = myAssumeNullSubstitutionsEquals;
            this.myResolveContext = myResolveContext;
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitEllipsisReferenceType(final OCEllipsisType ocEllipsisType) {
            return new OCTypeCompatibilityVisitor_OCEllipsisType(ocEllipsisType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitFunctionType(final OCFunctionType ocFunctionType) {
            return new OCTypeCompatibilityVisitor_OCFunctionType(ocFunctionType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitMagicType(final OCMagicType ocMagicType) {
            return new OCTypeCompatibilityVisitor_AlwaysOk<OCType>((OCType)ocMagicType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitObjectType(final OCObjectType ocObjectType) {
            return new OCTypeCompatibilityVisitor_OCObjectType(ocObjectType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitArrayType(final OCArrayType ocArrayType) {
            return new OCTypeCompatibilityVisitor_OCArrayType(ocArrayType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitPointerType(final OCPointerType ocPointerType) {
            return new OCTypeCompatibilityVisitor_OCPointerType(ocPointerType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitBlockPointerType(final OCBlockPointerType ocBlockPointerType) {
            return new OCTypeCompatibilityVisitor_OCBlockPointerType(ocBlockPointerType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitCppReferenceType(final OCCppReferenceType ocCppReferenceType) {
            return ocCppReferenceType.getRefType(this.myContext).accept((OCTypeVisitor<OCTypeCompatibilityVisitor<? extends OCType>>)this);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitIdType(final OCIdType ocIdType) {
            return new OCTypeCompatibilityVisitor_OCIdType(ocIdType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitIntType(final OCIntType ocIntType) {
            return new OCTypeCompatibilityVisitor_OCIntType(ocIntType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitRealType(final OCRealType ocRealType) {
            return new OCTypeCompatibilityVisitor_OCRealType(ocRealType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitReferenceType(final OCReferenceType ocReferenceType) {
            return new OCTypeCompatibilityVisitor_OCReferenceType(ocReferenceType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitStructType(final OCStructType ocStructType) {
            return new OCTypeCompatibilityVisitor_OCStructType(ocStructType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitUnknownType(final OCUnknownType ocUnknownType) {
            return new OCTypeCompatibilityVisitor_AlwaysOk<OCType>((OCType)ocUnknownType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitVoidType(final OCVoidType ocVoidType) {
            return new OCTypeCompatibilityVisitor_OCVoidType(ocVoidType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
            return new OCTypeCompatibilityVisitor_AlwaysOk<OCType>((OCType)ocTypeParameterType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitAutoType(final OCAutoType ocAutoType) {
            return new OCTypeCompatibilityVisitor_OCAutoType(ocAutoType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitVariadicType(final OCVariadicType ocVariadicType) {
            return new OCTypeCompatibilityVisitor_AlwaysOk<OCType>((OCType)ocVariadicType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
        
        @Override
        public OCTypeCompatibilityVisitor<? extends OCType> visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
            return new OCTypeCompatibilityVisitor_AlwaysOk<OCType>((OCType)ocExpansionPackType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
        }
    }
}
