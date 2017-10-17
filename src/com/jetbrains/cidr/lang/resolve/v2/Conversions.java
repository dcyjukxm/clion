// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.resolve.OCExprValueCategory;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.types.ARCAttribute;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;

public class Conversions
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public static ImplicitConversionSequence calculateConversion(@Nullable final OCTypeOwner p0, @NotNull final OCType p1, @NotNull final OCType p2, final boolean p3, final boolean p4, final boolean p5, final boolean p6, @NotNull final OCResolveContext p7) {
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
        //    18: ldc             "actualType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "calculateConversion"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "declType"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "calculateConversion"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           7
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
        //   113: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "calculateConversion"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_1        
        //   134: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   137: ifeq            148
        //   140: aload_1        
        //   141: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   144: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   147: astore_1       
        //   148: aload_2        
        //   149: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   152: ifeq            160
        //   155: aload_2        
        //   156: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   159: astore_2       
        //   160: aload_1        
        //   161: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   164: astore          8
        //   166: aload           8
        //   168: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   171: ifne            239
        //   174: aload           8
        //   176: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   179: ifeq            207
        //   182: goto            189
        //   185: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   188: athrow         
        //   189: aload           8
        //   191: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   194: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.hasSeveralSpecializations:()Z
        //   197: ifne            239
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: aload_2        
        //   208: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   211: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   214: ifne            239
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: aload           8
        //   226: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   229: ifeq            247
        //   232: goto            239
        //   235: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   238: athrow         
        //   239: invokestatic    com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.magic:()Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   242: areturn        
        //   243: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: aload_2        
        //   248: instanceof      Lcom/jetbrains/cidr/lang/types/OCEllipsisType;
        //   251: ifeq            271
        //   254: new             Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   257: dup            
        //   258: invokespecial   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.<init>:()V
        //   261: astore          9
        //   263: aload           9
        //   265: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setEllipsis:()V
        //   268: aload           9
        //   270: areturn        
        //   271: aload_2        
        //   272: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   275: astore          9
        //   277: aload_1        
        //   278: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   281: astore          10
        //   283: aload           9
        //   285: ifnull          325
        //   288: aload           10
        //   290: ifnull          325
        //   293: goto            300
        //   296: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   299: athrow         
        //   300: aload           10
        //   302: aload           7
        //   304: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   307: ifeq            325
        //   310: goto            317
        //   313: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: invokestatic    com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.magic:()Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   320: areturn        
        //   321: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   324: athrow         
        //   325: aload_0        
        //   326: aload_1        
        //   327: aload_2        
        //   328: iload_3        
        //   329: ifne            340
        //   332: iconst_1       
        //   333: goto            341
        //   336: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   339: athrow         
        //   340: iconst_0       
        //   341: iload           4
        //   343: iload           5
        //   345: iload           6
        //   347: aload           7
        //   349: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.TryCopyInitialization:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   352: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  166    182    185    189    Ljava/lang/IllegalArgumentException;
        //  174    200    203    207    Ljava/lang/IllegalArgumentException;
        //  189    217    220    224    Ljava/lang/IllegalArgumentException;
        //  207    232    235    239    Ljava/lang/IllegalArgumentException;
        //  224    243    243    247    Ljava/lang/IllegalArgumentException;
        //  283    293    296    300    Ljava/lang/IllegalArgumentException;
        //  288    310    313    317    Ljava/lang/IllegalArgumentException;
        //  300    321    321    325    Ljava/lang/IllegalArgumentException;
        //  325    336    336    340    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0189:
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
    
    public static ImplicitConversionSequence TryCopyInitialization(@Nullable final OCTypeOwner ocTypeOwner, final OCType ocType, final OCType ocType2, final boolean b, final boolean b2, final boolean b3, final boolean b4, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/Conversions", "TryCopyInitialization"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocTypeOwner instanceof OCCompoundInitializer) {
                return a((OCCompoundInitializer)ocTypeOwner, ocType2, b, b2, b3, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocType2 instanceof OCCppReferenceType) {
                return a(ocTypeOwner, ocType, ocType2, b, b4, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return TryImplicitConversion(ocTypeOwner, ocType, ocType2, b, false, b2, false, b3, false, ocResolveContext);
    }
    
    private static ImplicitConversionSequence a(final OCCompoundInitializer p0, final OCType p1, final boolean p2, final boolean p3, final boolean p4, @NotNull final OCResolveContext p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           5
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "context"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "TryListConversion"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: new             Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //    48: dup            
        //    49: invokespecial   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.<init>:()V
        //    52: astore          6
        //    54: aload           6
        //    56: getstatic       com/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind.no_conversion:Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;
        //    59: aload_0        
        //    60: aconst_null    
        //    61: aload_1        
        //    62: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setBad:(Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //    65: aload_1        
        //    66: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //    69: ifeq            79
        //    72: aload           6
        //    74: areturn        
        //    75: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_0        
        //    80: invokeinterface com/jetbrains/cidr/lang/psi/OCCompoundInitializer.getInitializerExpressions:()Ljava/util/List;
        //    85: astore          7
        //    87: aload_1        
        //    88: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //    91: istore          8
        //    93: aload           7
        //    95: invokeinterface java/util/List.size:()I
        //   100: iconst_1       
        //   101: if_icmpne       197
        //   104: iload           8
        //   106: ifeq            197
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload           7
        //   118: iconst_0       
        //   119: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   124: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   127: aload           5
        //   129: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   134: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getCppReferencedType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   137: astore          9
        //   139: aload           9
        //   141: aload_1        
        //   142: aload           5
        //   144: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   147: ifne            168
        //   150: aload           9
        //   152: aload_1        
        //   153: aload           5
        //   155: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   158: ifeq            197
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: aload           7
        //   170: iconst_0       
        //   171: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   176: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeOwner;
        //   179: aload           9
        //   181: aload_1        
        //   182: iload_2        
        //   183: iload_3        
        //   184: iload           4
        //   186: iconst_0       
        //   187: aload           5
        //   189: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.TryCopyInitialization:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   192: areturn        
        //   193: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload_1        
        //   198: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   201: ifeq            301
        //   204: aload           7
        //   206: invokeinterface java/util/List.isEmpty:()Z
        //   211: ifeq            259
        //   214: goto            221
        //   217: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: aload           6
        //   223: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setStandard:()V
        //   226: aload           6
        //   228: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   231: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setAsIdentityConversion:()V
        //   234: aload           6
        //   236: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   239: aload_1        
        //   240: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setFromType:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   243: aload           6
        //   245: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   248: aload_1        
        //   249: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setAllToTypes:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   252: aload           6
        //   254: areturn        
        //   255: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   258: athrow         
        //   259: aload           7
        //   261: iconst_0       
        //   262: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   267: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   270: astore          9
        //   272: aload           9
        //   274: aload           9
        //   276: aload           5
        //   278: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   283: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getCppReferencedType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   286: aload_1        
        //   287: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getArrayElementType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   290: iload_2        
        //   291: iload_3        
        //   292: iload           4
        //   294: iconst_0       
        //   295: aload           5
        //   297: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.TryCopyInitialization:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   300: areturn        
        //   301: aload_1        
        //   302: aload           5
        //   304: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isAggregateType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   307: istore          9
        //   309: iload           8
        //   311: ifeq            346
        //   314: iload           9
        //   316: ifne            346
        //   319: goto            326
        //   322: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   325: athrow         
        //   326: aload_0        
        //   327: aconst_null    
        //   328: aload_1        
        //   329: iload_2        
        //   330: iconst_0       
        //   331: iload_3        
        //   332: iconst_0       
        //   333: iload           4
        //   335: iconst_0       
        //   336: aload           5
        //   338: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   341: areturn        
        //   342: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   345: athrow         
        //   346: iload           9
        //   348: ifeq            426
        //   351: aload_0        
        //   352: aload_1        
        //   353: iconst_0       
        //   354: aload           5
        //   356: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.checkCompoundInitializer:(Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   359: astore          10
        //   361: aload           10
        //   363: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   366: if_acmpne       423
        //   369: aload           6
        //   371: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setUserDefined:()V
        //   374: aload           6
        //   376: new             Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   379: dup            
        //   380: invokespecial   com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.<init>:()V
        //   383: putfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   386: aload           6
        //   388: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   391: new             Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   394: dup            
        //   395: invokespecial   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.<init>:()V
        //   398: putfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.Before:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   401: aload           6
        //   403: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   406: new             Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   409: dup            
        //   410: invokespecial   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.<init>:()V
        //   413: putfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   416: goto            423
        //   419: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   422: athrow         
        //   423: aload           6
        //   425: areturn        
        //   426: aload_1        
        //   427: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   430: ifeq            702
        //   433: aload_1        
        //   434: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   437: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   440: aload           5
        //   442: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   445: astore          10
        //   447: aload           7
        //   449: invokeinterface java/util/List.size:()I
        //   454: iconst_1       
        //   455: if_icmpne       538
        //   458: aload           7
        //   460: iconst_0       
        //   461: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   466: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   469: astore          11
        //   471: aload           11
        //   473: aload           5
        //   475: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   480: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.getCppReferencedType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   483: astore          12
        //   485: invokestatic    com/intellij/openapi/util/Ref.create:()Lcom/intellij/openapi/util/Ref;
        //   488: astore          13
        //   490: aload           10
        //   492: aload           12
        //   494: aload           13
        //   496: aload           13
        //   498: aload           13
        //   500: aload           5
        //   502: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //   505: astore          14
        //   507: aload           14
        //   509: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.ordinal:()I
        //   512: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Related:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //   515: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.ordinal:()I
        //   518: if_icmplt       538
        //   521: aload           11
        //   523: aload           12
        //   525: aload_1        
        //   526: iload_2        
        //   527: iconst_0       
        //   528: aload           5
        //   530: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   533: areturn        
        //   534: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   537: athrow         
        //   538: aload_0        
        //   539: aload           10
        //   541: iload_2        
        //   542: iload_3        
        //   543: iload           4
        //   545: aload           5
        //   547: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;Lcom/jetbrains/cidr/lang/types/OCType;ZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   550: astore          6
        //   552: aload           6
        //   554: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isFailure:()Z
        //   557: ifeq            567
        //   560: aload           6
        //   562: areturn        
        //   563: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   566: athrow         
        //   567: aload_1        
        //   568: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   571: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   574: ifne            595
        //   577: aload           10
        //   579: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   582: getstatic       com/jetbrains/cidr/lang/types/CVQualifiers.CONST:Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   585: if_acmpne       689
        //   588: goto            595
        //   591: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   594: athrow         
        //   595: aload           6
        //   597: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isStandard:()Z
        //   600: ifeq            622
        //   603: goto            610
        //   606: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   609: athrow         
        //   610: aload           6
        //   612: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   615: goto            630
        //   618: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   621: athrow         
        //   622: aload           6
        //   624: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   627: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   630: astore          11
        //   632: aload           11
        //   634: iconst_1       
        //   635: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ReferenceBinding:Z
        //   638: aload           11
        //   640: aload_1        
        //   641: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   644: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   647: ifne            658
        //   650: iconst_1       
        //   651: goto            659
        //   654: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   657: athrow         
        //   658: iconst_0       
        //   659: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IsLvalueReference:Z
        //   662: aload           11
        //   664: iconst_1       
        //   665: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToRvalue:Z
        //   668: aload           11
        //   670: iconst_0       
        //   671: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToFunctionLvalue:Z
        //   674: aload           11
        //   676: iconst_0       
        //   677: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsImplicitObjectArgumentWithoutRefQualifier:Z
        //   680: aload           11
        //   682: iconst_0       
        //   683: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ObjCLifetimeConversionBinding:Z
        //   686: goto            699
        //   689: aload           6
        //   691: getstatic       com/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind.lvalue_ref_to_rvalue:Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;
        //   694: aconst_null    
        //   695: aload_1        
        //   696: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setBad:(Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   699: aload           6
        //   701: areturn        
        //   702: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions.$assertionsDisabled:Z
        //   705: ifne            732
        //   708: iload           8
        //   710: ifeq            732
        //   713: goto            720
        //   716: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   719: athrow         
        //   720: new             Ljava/lang/AssertionError;
        //   723: dup            
        //   724: invokespecial   java/lang/AssertionError.<init>:()V
        //   727: athrow         
        //   728: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   731: athrow         
        //   732: aload           7
        //   734: invokeinterface java/util/List.size:()I
        //   739: iconst_1       
        //   740: if_icmpne       804
        //   743: aload           7
        //   745: iconst_0       
        //   746: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   751: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   754: ifne            804
        //   757: goto            764
        //   760: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   763: athrow         
        //   764: aload           7
        //   766: iconst_0       
        //   767: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   772: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   775: astore          10
        //   777: aload           10
        //   779: aload           10
        //   781: aload           5
        //   783: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   788: aload_1        
        //   789: iload_2        
        //   790: iload_3        
        //   791: iload           4
        //   793: iconst_0       
        //   794: aload           5
        //   796: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.TryCopyInitialization:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   799: astore          6
        //   801: goto            852
        //   804: aload           7
        //   806: invokeinterface java/util/List.isEmpty:()Z
        //   811: ifeq            852
        //   814: aload           6
        //   816: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setStandard:()V
        //   819: aload           6
        //   821: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   824: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setAsIdentityConversion:()V
        //   827: aload           6
        //   829: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   832: aload_1        
        //   833: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setFromType:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   836: aload           6
        //   838: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   841: aload_1        
        //   842: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setAllToTypes:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   845: goto            852
        //   848: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   851: athrow         
        //   852: aload           6
        //   854: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  54     75     75     79     Ljava/lang/IllegalArgumentException;
        //  93     109    112    116    Ljava/lang/IllegalArgumentException;
        //  139    161    164    168    Ljava/lang/IllegalArgumentException;
        //  150    193    193    197    Ljava/lang/IllegalArgumentException;
        //  197    214    217    221    Ljava/lang/IllegalArgumentException;
        //  204    255    255    259    Ljava/lang/IllegalArgumentException;
        //  309    319    322    326    Ljava/lang/IllegalArgumentException;
        //  314    342    342    346    Ljava/lang/IllegalArgumentException;
        //  361    416    419    423    Ljava/lang/IllegalArgumentException;
        //  507    534    534    538    Ljava/lang/IllegalArgumentException;
        //  552    563    563    567    Ljava/lang/IllegalArgumentException;
        //  567    588    591    595    Ljava/lang/IllegalArgumentException;
        //  577    603    606    610    Ljava/lang/IllegalArgumentException;
        //  595    618    618    622    Ljava/lang/IllegalArgumentException;
        //  632    654    654    658    Ljava/lang/IllegalArgumentException;
        //  702    713    716    720    Ljava/lang/IllegalArgumentException;
        //  708    728    728    732    Ljava/lang/IllegalArgumentException;
        //  732    757    760    764    Ljava/lang/IllegalArgumentException;
        //  804    845    848    852    Ljava/lang/IllegalArgumentException;
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
    
    private static ImplicitConversionSequence a(@Nullable final OCTypeOwner p0, final OCType p1, final OCType p2, final boolean p3, final boolean p4, @NotNull final OCResolveContext p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           5
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "context"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "TryReferenceInit"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions.$assertionsDisabled:Z
        //    48: ifne            79
        //    51: aload_2        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    55: ifne            79
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: new             Ljava/lang/AssertionError;
        //    68: dup            
        //    69: ldc             "Reference init needs a reference"
        //    71: invokespecial   java/lang/AssertionError.<init>:(Ljava/lang/Object;)V
        //    74: athrow         
        //    75: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: new             Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //    82: dup            
        //    83: invokespecial   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.<init>:()V
        //    86: astore          6
        //    88: aload           6
        //    90: getstatic       com/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind.no_conversion:Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;
        //    93: aload_1        
        //    94: aload_2        
        //    95: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setBad:(Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //    98: aload_2        
        //    99: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   102: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   105: astore          7
        //   107: aload_1        
        //   108: astore          8
        //   110: aload_2        
        //   111: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   114: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   117: istore          9
        //   119: iconst_0       
        //   120: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   123: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //   126: astore          10
        //   128: iconst_0       
        //   129: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   132: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //   135: astore          11
        //   137: iconst_0       
        //   138: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   141: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //   144: astore          12
        //   146: aload_0        
        //   147: aload           5
        //   149: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   152: astore          13
        //   154: aload           7
        //   156: aload           8
        //   158: aload           10
        //   160: aload           11
        //   162: aload           12
        //   164: aload           5
        //   166: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //   169: astore          14
        //   171: iload           9
        //   173: ifne            525
        //   176: aload           13
        //   178: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   181: if_acmpne       459
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: aload           14
        //   193: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.ordinal:()I
        //   196: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Compatible_With_Added_Qualification:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //   199: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.ordinal:()I
        //   202: if_icmplt       459
        //   205: goto            212
        //   208: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   211: athrow         
        //   212: aload           6
        //   214: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setStandard:()V
        //   217: aload           6
        //   219: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   222: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   225: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   228: aload           6
        //   230: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   233: aload           10
        //   235: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   238: checkcast       Ljava/lang/Boolean;
        //   241: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   244: ifeq            264
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Derived_To_Base:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   257: goto            291
        //   260: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: aload           11
        //   266: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   269: checkcast       Ljava/lang/Boolean;
        //   272: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   275: ifeq            288
        //   278: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Compatible_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   281: goto            291
        //   284: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   287: athrow         
        //   288: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   291: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   294: aload           6
        //   296: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   299: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   302: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Third:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   305: aload           6
        //   307: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   310: aload           8
        //   312: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.FromTypePtr:Lcom/jetbrains/cidr/lang/types/OCType;
        //   315: aload           6
        //   317: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   320: iconst_0       
        //   321: aload           8
        //   323: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setToType:(ILcom/jetbrains/cidr/lang/types/OCType;)V
        //   326: aload           6
        //   328: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   331: iconst_1       
        //   332: aload           7
        //   334: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setToType:(ILcom/jetbrains/cidr/lang/types/OCType;)V
        //   337: aload           6
        //   339: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   342: iconst_2       
        //   343: aload           7
        //   345: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setToType:(ILcom/jetbrains/cidr/lang/types/OCType;)V
        //   348: aload           6
        //   350: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   353: iconst_1       
        //   354: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ReferenceBinding:Z
        //   357: aload           6
        //   359: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   362: iconst_1       
        //   363: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.DirectBinding:Z
        //   366: aload           6
        //   368: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   371: iload           9
        //   373: ifne            384
        //   376: iconst_1       
        //   377: goto            385
        //   380: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   383: athrow         
        //   384: iconst_0       
        //   385: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IsLvalueReference:Z
        //   388: aload           6
        //   390: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   393: aload           8
        //   395: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   398: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToFunctionLvalue:Z
        //   401: aload           6
        //   403: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   406: iconst_0       
        //   407: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToRvalue:Z
        //   410: aload           6
        //   412: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   415: iconst_0       
        //   416: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsImplicitObjectArgumentWithoutRefQualifier:Z
        //   419: aload           6
        //   421: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   424: aload           12
        //   426: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   429: checkcast       Ljava/lang/Boolean;
        //   432: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   435: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ObjCLifetimeConversionBinding:Z
        //   438: aload           6
        //   440: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   443: aconst_null    
        //   444: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.CopyConstructor:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   447: aload           6
        //   449: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   452: iconst_0       
        //   453: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.DeprecatedStringLiteralToCharPtr:Z
        //   456: aload           6
        //   458: areturn        
        //   459: iload_3        
        //   460: ifne            525
        //   463: aload           8
        //   465: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   468: ifeq            525
        //   471: goto            478
        //   474: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   477: athrow         
        //   478: aload           14
        //   480: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Incompatible:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //   483: if_acmpne       525
        //   486: goto            493
        //   489: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   492: athrow         
        //   493: aload_0        
        //   494: aload_1        
        //   495: aload_2        
        //   496: iload_3        
        //   497: iload           4
        //   499: iconst_1       
        //   500: iconst_0       
        //   501: iconst_0       
        //   502: iconst_0       
        //   503: aload           5
        //   505: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   508: astore          15
        //   510: aload           15
        //   512: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isBad:()Z
        //   515: ifne            525
        //   518: aload           15
        //   520: areturn        
        //   521: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   524: athrow         
        //   525: iload           9
        //   527: ifne            567
        //   530: aload           7
        //   532: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   535: ifeq            560
        //   538: goto            545
        //   541: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   544: athrow         
        //   545: aload           7
        //   547: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   550: ifeq            567
        //   553: goto            560
        //   556: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   559: athrow         
        //   560: aload           6
        //   562: areturn        
        //   563: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   566: athrow         
        //   567: aload           14
        //   569: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.ordinal:()I
        //   572: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Compatible_With_Added_Qualification:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //   575: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.ordinal:()I
        //   578: if_icmplt       999
        //   581: aload           13
        //   583: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.XValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   586: if_acmpeq       699
        //   589: goto            596
        //   592: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   595: athrow         
        //   596: aload           13
        //   598: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   601: if_acmpne       641
        //   604: goto            611
        //   607: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   610: athrow         
        //   611: aload           8
        //   613: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   616: ifne            699
        //   619: goto            626
        //   622: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   625: athrow         
        //   626: aload           8
        //   628: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   631: ifne            699
        //   634: goto            641
        //   637: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   640: athrow         
        //   641: aload           13
        //   643: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   646: if_acmpne       999
        //   649: goto            656
        //   652: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   655: athrow         
        //   656: aload           8
        //   658: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   661: ifne            699
        //   664: goto            671
        //   667: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   670: athrow         
        //   671: aload_0        
        //   672: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol;
        //   675: ifne            699
        //   678: goto            685
        //   681: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   684: athrow         
        //   685: aload_0        
        //   686: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   689: ifeq            999
        //   692: goto            699
        //   695: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   698: athrow         
        //   699: aload           6
        //   701: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setStandard:()V
        //   704: aload           6
        //   706: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   709: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   712: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   715: aload           6
        //   717: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   720: aload           10
        //   722: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   725: checkcast       Ljava/lang/Boolean;
        //   728: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   731: ifeq            751
        //   734: goto            741
        //   737: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   740: athrow         
        //   741: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Derived_To_Base:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   744: goto            778
        //   747: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   750: athrow         
        //   751: aload           11
        //   753: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   756: checkcast       Ljava/lang/Boolean;
        //   759: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   762: ifeq            775
        //   765: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Compatible_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   768: goto            778
        //   771: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   774: athrow         
        //   775: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   778: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   781: aload           6
        //   783: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   786: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   789: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Third:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   792: aload           6
        //   794: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   797: aload           8
        //   799: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.FromTypePtr:Lcom/jetbrains/cidr/lang/types/OCType;
        //   802: aload           6
        //   804: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   807: iconst_0       
        //   808: aload           8
        //   810: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setToType:(ILcom/jetbrains/cidr/lang/types/OCType;)V
        //   813: aload           6
        //   815: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   818: iconst_1       
        //   819: aload           7
        //   821: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setToType:(ILcom/jetbrains/cidr/lang/types/OCType;)V
        //   824: aload           6
        //   826: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   829: iconst_2       
        //   830: aload           7
        //   832: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setToType:(ILcom/jetbrains/cidr/lang/types/OCType;)V
        //   835: aload           6
        //   837: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   840: iconst_1       
        //   841: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ReferenceBinding:Z
        //   844: aload           6
        //   846: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   849: aload           5
        //   851: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   854: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsInitializerLists:(Lcom/intellij/psi/PsiFile;)Z
        //   857: ifne            890
        //   860: aload           13
        //   862: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   865: if_acmpeq       898
        //   868: goto            875
        //   871: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   874: athrow         
        //   875: aload           8
        //   877: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   880: ifne            898
        //   883: goto            890
        //   886: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   889: athrow         
        //   890: iconst_1       
        //   891: goto            899
        //   894: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   897: athrow         
        //   898: iconst_0       
        //   899: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.DirectBinding:Z
        //   902: aload           6
        //   904: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   907: iload           9
        //   909: ifne            920
        //   912: iconst_1       
        //   913: goto            921
        //   916: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   919: athrow         
        //   920: iconst_0       
        //   921: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IsLvalueReference:Z
        //   924: aload           6
        //   926: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   929: aload           8
        //   931: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   934: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToFunctionLvalue:Z
        //   937: aload           6
        //   939: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   942: aload           13
        //   944: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isRValue:()Z
        //   947: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToRvalue:Z
        //   950: aload           6
        //   952: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   955: iconst_0       
        //   956: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsImplicitObjectArgumentWithoutRefQualifier:Z
        //   959: aload           6
        //   961: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   964: aload           12
        //   966: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   969: checkcast       Ljava/lang/Boolean;
        //   972: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   975: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ObjCLifetimeConversionBinding:Z
        //   978: aload           6
        //   980: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   983: aconst_null    
        //   984: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.CopyConstructor:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   987: aload           6
        //   989: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   992: iconst_0       
        //   993: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.DeprecatedStringLiteralToCharPtr:Z
        //   996: aload           6
        //   998: areturn        
        //   999: aload           7
        //  1001: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  1004: ifeq            1014
        //  1007: aload           6
        //  1009: areturn        
        //  1010: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1013: athrow         
        //  1014: aload           14
        //  1016: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Related:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //  1019: if_acmpne       1053
        //  1022: aload           7
        //  1024: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //  1027: astore          15
        //  1029: aload           8
        //  1031: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //  1034: astore          16
        //  1036: aload           15
        //  1038: aload           16
        //  1040: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isSuperset:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Z
        //  1043: ifne            1053
        //  1046: aload           6
        //  1048: areturn        
        //  1049: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1052: athrow         
        //  1053: iload_3        
        //  1054: ifeq            1109
        //  1057: aload           14
        //  1059: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Incompatible:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //  1062: if_acmpne       1109
        //  1065: goto            1072
        //  1068: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1071: athrow         
        //  1072: aload           7
        //  1074: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //  1077: ifne            1102
        //  1080: goto            1087
        //  1083: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1086: athrow         
        //  1087: aload           8
        //  1089: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //  1092: ifeq            1109
        //  1095: goto            1102
        //  1098: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1101: athrow         
        //  1102: aload           6
        //  1104: areturn        
        //  1105: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1108: athrow         
        //  1109: aload           14
        //  1111: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.ordinal:()I
        //  1114: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Related:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //  1117: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.ordinal:()I
        //  1120: if_icmplt       1161
        //  1123: iload           9
        //  1125: ifeq            1161
        //  1128: goto            1135
        //  1131: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1134: athrow         
        //  1135: aload_0        
        //  1136: aload           5
        //  1138: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //  1141: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //  1144: if_acmpne       1161
        //  1147: goto            1154
        //  1150: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1153: athrow         
        //  1154: aload           6
        //  1156: areturn        
        //  1157: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1160: athrow         
        //  1161: aload_0        
        //  1162: aload_1        
        //  1163: aload           7
        //  1165: iload_3        
        //  1166: iconst_0       
        //  1167: iconst_0       
        //  1168: iconst_0       
        //  1169: iconst_0       
        //  1170: iconst_0       
        //  1171: aload           5
        //  1173: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.TryImplicitConversion:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //  1176: astore          6
        //  1178: aload           6
        //  1180: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isStandard:()Z
        //  1183: ifeq            1263
        //  1186: aload           6
        //  1188: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1191: iconst_1       
        //  1192: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ReferenceBinding:Z
        //  1195: aload           6
        //  1197: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1200: iload           9
        //  1202: ifne            1220
        //  1205: goto            1212
        //  1208: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1211: athrow         
        //  1212: iconst_1       
        //  1213: goto            1221
        //  1216: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1219: athrow         
        //  1220: iconst_0       
        //  1221: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IsLvalueReference:Z
        //  1224: aload           6
        //  1226: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1229: iconst_0       
        //  1230: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToFunctionLvalue:Z
        //  1233: aload           6
        //  1235: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1238: iconst_1       
        //  1239: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToRvalue:Z
        //  1242: aload           6
        //  1244: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1247: iconst_0       
        //  1248: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsImplicitObjectArgumentWithoutRefQualifier:Z
        //  1251: aload           6
        //  1253: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1256: iconst_0       
        //  1257: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ObjCLifetimeConversionBinding:Z
        //  1260: goto            1438
        //  1263: aload           6
        //  1265: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isUserDefined:()Z
        //  1268: ifeq            1438
        //  1271: aload           6
        //  1273: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //  1276: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.ConversionFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1279: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getEffectiveType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1282: astore          15
        //  1284: aload           15
        //  1286: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //  1289: ifeq            1326
        //  1292: aload_2        
        //  1293: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //  1296: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //  1299: ifeq            1326
        //  1302: goto            1309
        //  1305: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1308: athrow         
        //  1309: aload           6
        //  1311: getstatic       com/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind.lvalue_ref_to_rvalue:Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;
        //  1314: aload_1        
        //  1315: aload_2        
        //  1316: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setBad:(Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //  1319: aload           6
        //  1321: areturn        
        //  1322: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1325: athrow         
        //  1326: aload           6
        //  1328: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //  1331: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.Before:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1334: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setAsIdentityConversion:()V
        //  1337: aload           6
        //  1339: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //  1342: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1345: iconst_1       
        //  1346: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ReferenceBinding:Z
        //  1349: aload           6
        //  1351: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //  1354: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1357: iload           9
        //  1359: ifne            1370
        //  1362: iconst_1       
        //  1363: goto            1371
        //  1366: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1369: athrow         
        //  1370: iconst_0       
        //  1371: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IsLvalueReference:Z
        //  1374: aload           6
        //  1376: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //  1379: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1382: iconst_0       
        //  1383: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToFunctionLvalue:Z
        //  1386: aload           6
        //  1388: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //  1391: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1394: aload           15
        //  1396: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //  1399: ifne            1410
        //  1402: iconst_1       
        //  1403: goto            1411
        //  1406: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1409: athrow         
        //  1410: iconst_0       
        //  1411: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToRvalue:Z
        //  1414: aload           6
        //  1416: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //  1419: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1422: iconst_0       
        //  1423: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsImplicitObjectArgumentWithoutRefQualifier:Z
        //  1426: aload           6
        //  1428: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //  1431: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //  1434: iconst_0       
        //  1435: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ObjCLifetimeConversionBinding:Z
        //  1438: aload           6
        //  1440: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     75     75     79     Ljava/lang/IllegalArgumentException;
        //  171    184    187    191    Ljava/lang/IllegalArgumentException;
        //  176    205    208    212    Ljava/lang/IllegalArgumentException;
        //  191    247    250    254    Ljava/lang/IllegalArgumentException;
        //  212    260    260    264    Ljava/lang/IllegalArgumentException;
        //  264    284    284    288    Ljava/lang/IllegalArgumentException;
        //  291    380    380    384    Ljava/lang/IllegalArgumentException;
        //  459    471    474    478    Ljava/lang/IllegalArgumentException;
        //  463    486    489    493    Ljava/lang/IllegalArgumentException;
        //  510    521    521    525    Ljava/lang/IllegalArgumentException;
        //  525    538    541    545    Ljava/lang/IllegalArgumentException;
        //  530    553    556    560    Ljava/lang/IllegalArgumentException;
        //  545    563    563    567    Ljava/lang/IllegalArgumentException;
        //  567    589    592    596    Ljava/lang/IllegalArgumentException;
        //  581    604    607    611    Ljava/lang/IllegalArgumentException;
        //  596    619    622    626    Ljava/lang/IllegalArgumentException;
        //  611    634    637    641    Ljava/lang/IllegalArgumentException;
        //  626    649    652    656    Ljava/lang/IllegalArgumentException;
        //  641    664    667    671    Ljava/lang/IllegalArgumentException;
        //  656    678    681    685    Ljava/lang/IllegalArgumentException;
        //  671    692    695    699    Ljava/lang/IllegalArgumentException;
        //  685    734    737    741    Ljava/lang/IllegalArgumentException;
        //  699    747    747    751    Ljava/lang/IllegalArgumentException;
        //  751    771    771    775    Ljava/lang/IllegalArgumentException;
        //  778    868    871    875    Ljava/lang/IllegalArgumentException;
        //  860    883    886    890    Ljava/lang/IllegalArgumentException;
        //  875    894    894    898    Ljava/lang/IllegalArgumentException;
        //  899    916    916    920    Ljava/lang/IllegalArgumentException;
        //  999    1010   1010   1014   Ljava/lang/IllegalArgumentException;
        //  1036   1049   1049   1053   Ljava/lang/IllegalArgumentException;
        //  1053   1065   1068   1072   Ljava/lang/IllegalArgumentException;
        //  1057   1080   1083   1087   Ljava/lang/IllegalArgumentException;
        //  1072   1095   1098   1102   Ljava/lang/IllegalArgumentException;
        //  1087   1105   1105   1109   Ljava/lang/IllegalArgumentException;
        //  1109   1128   1131   1135   Ljava/lang/IllegalArgumentException;
        //  1123   1147   1150   1154   Ljava/lang/IllegalArgumentException;
        //  1135   1157   1157   1161   Ljava/lang/IllegalArgumentException;
        //  1178   1205   1208   1212   Ljava/lang/IllegalArgumentException;
        //  1186   1216   1216   1220   Ljava/lang/IllegalArgumentException;
        //  1284   1302   1305   1309   Ljava/lang/IllegalArgumentException;
        //  1292   1322   1322   1326   Ljava/lang/IllegalArgumentException;
        //  1326   1366   1366   1370   Ljava/lang/IllegalArgumentException;
        //  1371   1406   1406   1410   Ljava/lang/IllegalArgumentException;
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
    
    private static ReferenceCompareResult a(final OCType p0, final OCType p1, final Ref<Boolean> p2, final Ref<Boolean> p3, final Ref<Boolean> p4, @NotNull final OCResolveContext p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           5
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "context"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "CompareReferenceRelationship"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions.$assertionsDisabled:Z
        //    48: ifne            79
        //    51: aload_0        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    55: ifeq            79
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: new             Ljava/lang/AssertionError;
        //    68: dup            
        //    69: ldc             "T1 must be the pointee type of the reference type"
        //    71: invokespecial   java/lang/AssertionError.<init>:(Ljava/lang/Object;)V
        //    74: athrow         
        //    75: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions.$assertionsDisabled:Z
        //    82: ifne            113
        //    85: aload_1        
        //    86: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    89: ifeq            113
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: new             Ljava/lang/AssertionError;
        //   102: dup            
        //   103: ldc             "T2 cannot be a reference type"
        //   105: invokespecial   java/lang/AssertionError.<init>:(Ljava/lang/Object;)V
        //   108: athrow         
        //   109: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_0        
        //   114: aload           5
        //   116: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   119: astore          6
        //   121: aload_1        
        //   122: aload           5
        //   124: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   127: astore          7
        //   129: aload           6
        //   131: aload           5
        //   133: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   136: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getUnqualifiedArrayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/intellij/openapi/util/Pair;
        //   139: astore          8
        //   141: aload           7
        //   143: aload           5
        //   145: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   148: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getUnqualifiedArrayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/intellij/openapi/util/Pair;
        //   151: astore          9
        //   153: aload           8
        //   155: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   158: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   161: astore          10
        //   163: aload           9
        //   165: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   168: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   171: astore          11
        //   173: aload           8
        //   175: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   178: checkcast       Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   181: astore          12
        //   183: aload           9
        //   185: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   188: checkcast       Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   191: astore          13
        //   193: aload_2        
        //   194: iconst_0       
        //   195: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   198: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   201: aload_3        
        //   202: iconst_0       
        //   203: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   206: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   209: aload           4
        //   211: iconst_0       
        //   212: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   215: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   218: aload           10
        //   220: aload           11
        //   222: aload           5
        //   224: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   227: ifeq            237
        //   230: goto            328
        //   233: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: aload           11
        //   239: aload           10
        //   241: aload           5
        //   243: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   246: ifeq            264
        //   249: aload_2        
        //   250: iconst_1       
        //   251: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   254: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   257: goto            328
        //   260: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: aload           10
        //   266: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   269: ifeq            324
        //   272: aload           11
        //   274: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   277: ifeq            324
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: aload           10
        //   289: aload           11
        //   291: aload           5
        //   293: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   296: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   299: ifeq            324
        //   302: goto            309
        //   305: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   308: athrow         
        //   309: aload_3        
        //   310: iconst_1       
        //   311: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   314: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   317: goto            328
        //   320: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   323: athrow         
        //   324: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Incompatible:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //   327: areturn        
        //   328: aload           12
        //   330: aload           13
        //   332: if_acmpne       343
        //   335: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Compatible:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //   338: areturn        
        //   339: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   342: athrow         
        //   343: aload           12
        //   345: aload           13
        //   347: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isSuperset:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Z
        //   350: ifeq            361
        //   353: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Compatible_With_Added_Qualification:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //   356: areturn        
        //   357: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   360: athrow         
        //   361: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult.Ref_Related:Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //   364: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/util/Ref<Ljava/lang/Boolean;>;Lcom/intellij/openapi/util/Ref<Ljava/lang/Boolean;>;Lcom/intellij/openapi/util/Ref<Ljava/lang/Boolean;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/Conversions$ReferenceCompareResult;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     75     75     79     Ljava/lang/IllegalArgumentException;
        //  79     92     95     99     Ljava/lang/IllegalArgumentException;
        //  85     109    109    113    Ljava/lang/IllegalArgumentException;
        //  193    233    233    237    Ljava/lang/IllegalArgumentException;
        //  237    260    260    264    Ljava/lang/IllegalArgumentException;
        //  264    280    283    287    Ljava/lang/IllegalArgumentException;
        //  272    302    305    309    Ljava/lang/IllegalArgumentException;
        //  287    320    320    324    Ljava/lang/IllegalArgumentException;
        //  328    339    339    343    Ljava/lang/IllegalArgumentException;
        //  343    357    357    361    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0287:
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
    
    public static ImplicitConversionSequence TryImplicitConversion(@Nullable final OCTypeOwner p0, final OCType p1, final OCType p2, final boolean p3, final boolean p4, final boolean p5, final boolean p6, final boolean p7, final boolean p8, @NotNull final OCResolveContext p9) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           9
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "context"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "TryImplicitConversion"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: new             Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //    48: dup            
        //    49: invokespecial   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.<init>:()V
        //    52: astore          10
        //    54: aload           10
        //    56: aload_0        
        //    57: aload_1        
        //    58: aload_2        
        //    59: iload           5
        //    61: iload           6
        //    63: iload           7
        //    65: aload           9
        //    67: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.IsStandardConversion:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //    70: dup_x1         
        //    71: putfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //    74: ifnull          89
        //    77: aload           10
        //    79: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setStandard:()V
        //    82: aload           10
        //    84: areturn        
        //    85: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload           9
        //    91: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //    94: ifne            115
        //    97: aload           10
        //    99: getstatic       com/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind.no_conversion:Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;
        //   102: aload_0        
        //   103: aload_1        
        //   104: aload_2        
        //   105: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setBad:(Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   108: aload           10
        //   110: areturn        
        //   111: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload_2        
        //   116: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   119: ifeq            252
        //   122: aload_1        
        //   123: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   126: ifeq            252
        //   129: goto            136
        //   132: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   135: athrow         
        //   136: aload_1        
        //   137: aload_2        
        //   138: aload           9
        //   140: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   143: ifne            170
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_1        
        //   154: aload_2        
        //   155: aload           9
        //   157: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   160: ifeq            252
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: aload           10
        //   172: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setStandard:()V
        //   175: aload           10
        //   177: new             Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   180: dup            
        //   181: invokespecial   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.<init>:()V
        //   184: putfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   187: aload           10
        //   189: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   192: aload_1        
        //   193: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setFromType:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   196: aload           10
        //   198: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   201: aload_2        
        //   202: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setAllToTypes:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   205: aload           10
        //   207: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   210: aconst_null    
        //   211: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.CopyConstructor:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   214: aload_1        
        //   215: aload_2        
        //   216: aload           9
        //   218: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   221: ifne            249
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload           10
        //   233: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   236: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Derived_To_Base:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   239: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aload           10
        //   251: areturn        
        //   252: aload_0        
        //   253: aload_1        
        //   254: aload_2        
        //   255: iload_3        
        //   256: iload           4
        //   258: iload           5
        //   260: iload           6
        //   262: iload           7
        //   264: iload           8
        //   266: aload           9
        //   268: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   271: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  54     85     85     89     Ljava/lang/IllegalArgumentException;
        //  89     111    111    115    Ljava/lang/IllegalArgumentException;
        //  115    129    132    136    Ljava/lang/IllegalArgumentException;
        //  122    146    149    153    Ljava/lang/IllegalArgumentException;
        //  136    163    166    170    Ljava/lang/IllegalArgumentException;
        //  153    224    227    231    Ljava/lang/IllegalArgumentException;
        //  170    242    245    249    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0136:
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
    static StandardConversionSequence IsStandardConversion(@Nullable final OCTypeOwner p0, final OCType p1, final OCType p2, final boolean p3, final boolean p4, final boolean p5, @NotNull final OCResolveContext p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           6
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "context"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "IsStandardConversion"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: new             Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //    48: dup            
        //    49: invokespecial   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.<init>:()V
        //    52: astore          7
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload           6
        //    58: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    61: ifeq            71
        //    64: aload           7
        //    66: areturn        
        //    67: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload           7
        //    73: iconst_0       
        //    74: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IncompatibleObjC:Z
        //    77: aload           7
        //    79: aload_1        
        //    80: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setFromType:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //    83: aload           7
        //    85: aconst_null    
        //    86: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.CopyConstructor:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    89: aload           6
        //    91: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //    94: ifeq            131
        //    97: aload_1        
        //    98: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   101: ifne            125
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_2        
        //   112: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   115: ifeq            131
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: aconst_null    
        //   126: areturn        
        //   127: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload_0        
        //   132: aload           6
        //   134: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   137: invokevirtual   com/jetbrains/cidr/lang/resolve/OCExprValueCategory.isGLValue:()Z
        //   140: istore          8
        //   142: iload           8
        //   144: ifeq            196
        //   147: aload_1        
        //   148: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   151: ifne            196
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: aload_1        
        //   162: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   165: ifne            196
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload           7
        //   177: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Lvalue_To_Rvalue:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   180: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   183: aload_1        
        //   184: aload           6
        //   186: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   189: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   192: astore_1       
        //   193: goto            313
        //   196: aload_1        
        //   197: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   200: ifeq            270
        //   203: aload           7
        //   205: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Array_To_Pointer:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   208: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   211: aload_1        
        //   212: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   215: aload           6
        //   217: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getArrayDecayedType:(Lcom/jetbrains/cidr/lang/types/OCArrayType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   220: astore_1       
        //   221: aload_0        
        //   222: aload_2        
        //   223: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   226: ifeq            313
        //   229: aload           7
        //   231: iconst_1       
        //   232: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.DeprecatedStringLiteralToCharPtr:Z
        //   235: aload           7
        //   237: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   240: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   243: aload           7
        //   245: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Qualification:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   248: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Third:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   251: aload           7
        //   253: iconst_0       
        //   254: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.QualificationIncludesObjCLifetime:Z
        //   257: aload           7
        //   259: aload_1        
        //   260: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setAllToTypes:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   263: aload           7
        //   265: areturn        
        //   266: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   269: athrow         
        //   270: aload_1        
        //   271: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   274: ifeq            305
        //   277: iload           8
        //   279: ifeq            305
        //   282: goto            289
        //   285: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: aload           7
        //   291: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Function_To_Pointer:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   294: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   297: aload_1        
        //   298: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   301: astore_1       
        //   302: goto            313
        //   305: aload           7
        //   307: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   310: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   313: aload           7
        //   315: iconst_0       
        //   316: aload_1        
        //   317: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setToType:(ILcom/jetbrains/cidr/lang/types/OCType;)V
        //   320: iconst_0       
        //   321: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   324: invokestatic    com/intellij/openapi/util/Ref.create:(Ljava/lang/Object;)Lcom/intellij/openapi/util/Ref;
        //   327: astore          9
        //   329: invokestatic    com/intellij/openapi/util/Ref.create:()Lcom/intellij/openapi/util/Ref;
        //   332: astore          10
        //   334: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   337: astore          11
        //   339: aload_1        
        //   340: aload_2        
        //   341: aload           6
        //   343: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   346: ifeq            364
        //   349: aload           7
        //   351: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   354: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   357: goto            823
        //   360: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   363: athrow         
        //   364: aload_0        
        //   365: aload_1        
        //   366: aload_2        
        //   367: aload           6
        //   369: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   372: ifeq            396
        //   375: aload           7
        //   377: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Integral_Promotion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   380: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   383: aload_2        
        //   384: aload           6
        //   386: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   389: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   392: astore_1       
        //   393: goto            823
        //   396: aload_1        
        //   397: aload_2        
        //   398: aload           6
        //   400: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   403: ifeq            427
        //   406: aload           7
        //   408: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Floating_Promotion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   411: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   414: aload_2        
        //   415: aload           6
        //   417: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   420: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   423: astore_1       
        //   424: goto            823
        //   427: aload_2        
        //   428: aload           6
        //   430: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   433: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   436: ifeq            517
        //   439: aload_1        
        //   440: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isArithmeticType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   443: ifne            467
        //   446: goto            453
        //   449: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: aload_1        
        //   454: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   457: ifeq            517
        //   460: goto            467
        //   463: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   466: athrow         
        //   467: aload           7
        //   469: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Boolean_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   472: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   475: aload_2        
        //   476: aload           6
        //   478: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   481: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   484: ifeq            502
        //   487: goto            494
        //   490: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   493: athrow         
        //   494: aload_2        
        //   495: goto            513
        //   498: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   501: athrow         
        //   502: aload           6
        //   504: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   507: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   510: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.getAppropriateBool:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   513: astore_1       
        //   514: goto            823
        //   517: aload_1        
        //   518: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isIntegerLikeType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   521: ifeq            561
        //   524: aload_2        
        //   525: aload           6
        //   527: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isIntegralType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   530: ifeq            561
        //   533: goto            540
        //   536: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   539: athrow         
        //   540: aload           7
        //   542: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Integral_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   545: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   548: aload_2        
        //   549: aload           6
        //   551: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   554: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   557: astore_1       
        //   558: goto            823
        //   561: aload_1        
        //   562: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   565: ifeq            603
        //   568: aload_2        
        //   569: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   572: ifeq            603
        //   575: goto            582
        //   578: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   581: athrow         
        //   582: aload           7
        //   584: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Floating_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   587: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   590: aload_2        
        //   591: aload           6
        //   593: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   596: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   599: astore_1       
        //   600: goto            823
        //   603: aload_1        
        //   604: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   607: ifeq            626
        //   610: aload_2        
        //   611: aload           6
        //   613: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isIntegralType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   616: ifne            654
        //   619: goto            626
        //   622: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   625: athrow         
        //   626: aload_1        
        //   627: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isIntegerLikeType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   630: ifeq            675
        //   633: goto            640
        //   636: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   639: athrow         
        //   640: aload_2        
        //   641: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   644: ifeq            675
        //   647: goto            654
        //   650: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   653: athrow         
        //   654: aload           7
        //   656: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Floating_Integral:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   659: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   662: aload_2        
        //   663: aload           6
        //   665: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   668: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   671: astore_1       
        //   672: goto            823
        //   675: aload_0        
        //   676: aload_1        
        //   677: aload_2        
        //   678: iload_3        
        //   679: aload           10
        //   681: aload           9
        //   683: aload           6
        //   685: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/intellij/openapi/util/Ref;Lcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   688: ifeq            735
        //   691: aload           7
        //   693: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Pointer_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   696: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   699: aload           7
        //   701: aload           9
        //   703: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   706: checkcast       Ljava/lang/Boolean;
        //   709: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   712: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IncompatibleObjC:Z
        //   715: aload           10
        //   717: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   720: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   723: aload           6
        //   725: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   728: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   731: astore_1       
        //   732: goto            823
        //   735: aload_0        
        //   736: aload_1        
        //   737: aload_2        
        //   738: iload_3        
        //   739: aload           10
        //   741: aload           6
        //   743: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/intellij/openapi/util/Ref;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   746: ifeq            769
        //   749: aload           7
        //   751: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Pointer_Member:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   754: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   757: aload           10
        //   759: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   762: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   765: astore_1       
        //   766: goto            823
        //   769: aload           6
        //   771: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //   774: ifne            815
        //   777: aload_2        
        //   778: aload_1        
        //   779: aload           6
        //   781: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   784: ifeq            815
        //   787: goto            794
        //   790: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   793: athrow         
        //   794: aload           7
        //   796: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Compatible_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   799: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   802: aload_2        
        //   803: aload           6
        //   805: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   808: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   811: astore_1       
        //   812: goto            823
        //   815: aload           7
        //   817: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   820: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   823: aload           7
        //   825: iconst_1       
        //   826: aload_1        
        //   827: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setToType:(ILcom/jetbrains/cidr/lang/types/OCType;)V
        //   830: iconst_0       
        //   831: istore          14
        //   833: aload_1        
        //   834: aload_2        
        //   835: iload           4
        //   837: aload           6
        //   839: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   842: ifeq            881
        //   845: aload           7
        //   847: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Qualification:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   850: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Third:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   853: aload           7
        //   855: iload           14
        //   857: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.QualificationIncludesObjCLifetime:Z
        //   860: aload_2        
        //   861: astore_1       
        //   862: aload_1        
        //   863: aload           6
        //   865: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   868: astore          12
        //   870: aload_2        
        //   871: aload           6
        //   873: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   876: astore          13
        //   878: goto            939
        //   881: aload           7
        //   883: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Identity:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   886: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Third:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   889: aload_1        
        //   890: aload           6
        //   892: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   895: astore          12
        //   897: aload_2        
        //   898: aload           6
        //   900: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   903: astore          13
        //   905: aload_1        
        //   906: aload_2        
        //   907: aload           6
        //   909: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   912: ifeq            939
        //   915: aload_1        
        //   916: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   919: aload_2        
        //   920: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   923: if_acmpeq       939
        //   926: goto            933
        //   929: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   932: athrow         
        //   933: aload_2        
        //   934: astore_1       
        //   935: aload           13
        //   937: astore          12
        //   939: aload           7
        //   941: iconst_2       
        //   942: aload_1        
        //   943: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setToType:(ILcom/jetbrains/cidr/lang/types/OCType;)V
        //   946: aload           12
        //   948: aload           13
        //   950: aload           6
        //   952: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   955: ifeq            965
        //   958: aload           7
        //   960: areturn        
        //   961: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   964: athrow         
        //   965: aload           6
        //   967: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //   970: ifne            984
        //   973: iload_3        
        //   974: ifne            990
        //   977: goto            984
        //   980: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   983: athrow         
        //   984: aconst_null    
        //   985: areturn        
        //   986: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   989: athrow         
        //   990: aload_2        
        //   991: aload_1        
        //   992: aload_0        
        //   993: aload           6
        //   995: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   998: ifne            1007
        //  1001: aconst_null    
        //  1002: areturn        
        //  1003: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1006: athrow         
        //  1007: aload           7
        //  1009: aload_2        
        //  1010: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.setAllToTypes:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //  1013: aload           7
        //  1015: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_C_Only_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1018: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1021: aload           7
        //  1023: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_C_Only_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1026: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1029: aload           7
        //  1031: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_C_Only_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1034: putfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Third:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1037: aload           7
        //  1039: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  54     67     67     71     Ljava/lang/IllegalArgumentException;
        //  71     104    107    111    Ljava/lang/IllegalArgumentException;
        //  97     118    121    125    Ljava/lang/IllegalArgumentException;
        //  111    127    127    131    Ljava/lang/IllegalArgumentException;
        //  142    154    157    161    Ljava/lang/IllegalArgumentException;
        //  147    168    171    175    Ljava/lang/IllegalArgumentException;
        //  221    266    266    270    Ljava/lang/IllegalArgumentException;
        //  270    282    285    289    Ljava/lang/IllegalArgumentException;
        //  339    360    360    364    Ljava/lang/IllegalArgumentException;
        //  427    446    449    453    Ljava/lang/IllegalArgumentException;
        //  439    460    463    467    Ljava/lang/IllegalArgumentException;
        //  453    487    490    494    Ljava/lang/IllegalArgumentException;
        //  467    498    498    502    Ljava/lang/IllegalArgumentException;
        //  517    533    536    540    Ljava/lang/IllegalArgumentException;
        //  561    575    578    582    Ljava/lang/IllegalArgumentException;
        //  603    619    622    626    Ljava/lang/IllegalArgumentException;
        //  610    633    636    640    Ljava/lang/IllegalArgumentException;
        //  626    647    650    654    Ljava/lang/IllegalArgumentException;
        //  769    787    790    794    Ljava/lang/IllegalArgumentException;
        //  905    926    929    933    Ljava/lang/IllegalArgumentException;
        //  939    961    961    965    Ljava/lang/IllegalArgumentException;
        //  965    977    980    984    Ljava/lang/IllegalArgumentException;
        //  973    986    986    990    Ljava/lang/IllegalArgumentException;
        //  990    1003   1003   1007   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0111:
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
    
    private static boolean a(@Nullable final OCTypeOwner p0, final OCType p1, final OCType p2, @NotNull final OCResolveContext p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "IsIntegralPromotion"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //    48: ifne            57
        //    51: iconst_0       
        //    52: ireturn        
        //    53: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: aload_2        
        //    58: checkcast       Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //    61: astore          4
        //    63: aload_1        
        //    64: aload_3        
        //    65: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isPromotableIntegerType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    68: ifeq            216
        //    71: aload_1        
        //    72: aload_3        
        //    73: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //    76: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    79: ifne            216
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload_1        
        //    90: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isEnum:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //    93: ifne            216
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload_1        
        //   104: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isSignedIntegerType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   107: ifne            137
        //   110: goto            117
        //   113: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: aload_1        
        //   118: aload_3        
        //   119: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getTypeSize:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)I
        //   122: aload_2        
        //   123: aload_3        
        //   124: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getTypeSize:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)I
        //   127: if_icmpge       180
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: aload           4
        //   139: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   142: getstatic       com/jetbrains/cidr/lang/types/CTypeId.INT:Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   145: if_acmpne       178
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: aload           4
        //   157: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.isSigned:()Z
        //   160: ifeq            178
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   169: athrow         
        //   170: iconst_1       
        //   171: goto            179
        //   174: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: iconst_0       
        //   179: ireturn        
        //   180: aload           4
        //   182: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   185: getstatic       com/jetbrains/cidr/lang/types/CTypeId.INT:Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   188: if_acmpne       214
        //   191: aload           4
        //   193: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.isSigned:()Z
        //   196: ifne            214
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: iconst_1       
        //   207: goto            215
        //   210: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   213: athrow         
        //   214: iconst_0       
        //   215: ireturn        
        //   216: aload_1        
        //   217: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isEnum:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   220: ifeq            270
        //   223: aload_1        
        //   224: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   227: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   230: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isEnumClass:()Z
        //   233: ifeq            249
        //   236: goto            243
        //   239: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   242: athrow         
        //   243: iconst_0       
        //   244: ireturn        
        //   245: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aload_2        
        //   250: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isIntegerLikeType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   253: ifeq            270
        //   256: aload_2        
        //   257: aload_1        
        //   258: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getEnumPromotionType:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   261: aload_3        
        //   262: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   265: ireturn        
        //   266: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   269: athrow         
        //   270: aload_1        
        //   271: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isAnyCharacterType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   274: ifeq            445
        //   277: aload_1        
        //   278: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isCharacterType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   281: ifne            445
        //   284: goto            291
        //   287: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   290: athrow         
        //   291: aload_2        
        //   292: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isIntegerLikeType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   295: ifeq            445
        //   298: goto            305
        //   301: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   304: athrow         
        //   305: aload_1        
        //   306: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isSignedIntegerType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   309: istore          5
        //   311: aload_1        
        //   312: aload_3        
        //   313: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getTypeSize:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)I
        //   316: istore          6
        //   318: bipush          6
        //   320: anewarray       Lcom/jetbrains/cidr/lang/types/OCType;
        //   323: dup            
        //   324: iconst_0       
        //   325: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   328: aastore        
        //   329: dup            
        //   330: iconst_1       
        //   331: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UINT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   334: aastore        
        //   335: dup            
        //   336: iconst_2       
        //   337: getstatic       com/jetbrains/cidr/lang/types/OCIntType.LONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   340: aastore        
        //   341: dup            
        //   342: iconst_3       
        //   343: getstatic       com/jetbrains/cidr/lang/types/OCIntType.ULONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   346: aastore        
        //   347: dup            
        //   348: iconst_4       
        //   349: getstatic       com/jetbrains/cidr/lang/types/OCIntType.LONGLONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   352: aastore        
        //   353: dup            
        //   354: iconst_5       
        //   355: getstatic       com/jetbrains/cidr/lang/types/OCIntType.ULONGLONG:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   358: aastore        
        //   359: astore          7
        //   361: iconst_0       
        //   362: istore          8
        //   364: iload           8
        //   366: aload           7
        //   368: arraylength    
        //   369: if_icmpge       445
        //   372: aload           7
        //   374: iload           8
        //   376: aaload         
        //   377: aload_3        
        //   378: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getTypeSize:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)I
        //   381: istore          9
        //   383: iload           6
        //   385: iload           9
        //   387: if_icmplt       424
        //   390: iload           6
        //   392: iload           9
        //   394: if_icmpne       439
        //   397: goto            404
        //   400: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   403: athrow         
        //   404: iload           5
        //   406: aload           7
        //   408: iload           8
        //   410: aaload         
        //   411: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isSignedIntegerType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   414: if_icmpne       439
        //   417: goto            424
        //   420: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   423: athrow         
        //   424: aload_2        
        //   425: aload           7
        //   427: iload           8
        //   429: aaload         
        //   430: aload_3        
        //   431: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   434: ireturn        
        //   435: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   438: athrow         
        //   439: iinc            8, 1
        //   442: goto            364
        //   445: aload_1        
        //   446: aload_3        
        //   447: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   450: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   453: ifeq            509
        //   456: aload_2        
        //   457: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   460: ifeq            509
        //   463: goto            470
        //   466: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   469: athrow         
        //   470: aload           4
        //   472: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   475: getstatic       com/jetbrains/cidr/lang/types/CTypeId.INT:Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   478: if_acmpne       509
        //   481: goto            488
        //   484: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   487: athrow         
        //   488: aload           4
        //   490: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.isSigned:()Z
        //   493: ifeq            509
        //   496: goto            503
        //   499: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: iconst_1       
        //   504: ireturn        
        //   505: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   508: athrow         
        //   509: iconst_0       
        //   510: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     53     53     57     Ljava/lang/IllegalArgumentException;
        //  63     82     85     89     Ljava/lang/IllegalArgumentException;
        //  71     96     99     103    Ljava/lang/IllegalArgumentException;
        //  89     110    113    117    Ljava/lang/IllegalArgumentException;
        //  103    130    133    137    Ljava/lang/IllegalArgumentException;
        //  117    148    151    155    Ljava/lang/IllegalArgumentException;
        //  137    163    166    170    Ljava/lang/IllegalArgumentException;
        //  155    174    174    178    Ljava/lang/IllegalArgumentException;
        //  180    199    202    206    Ljava/lang/IllegalArgumentException;
        //  191    210    210    214    Ljava/lang/IllegalArgumentException;
        //  216    236    239    243    Ljava/lang/IllegalArgumentException;
        //  223    245    245    249    Ljava/lang/IllegalArgumentException;
        //  249    266    266    270    Ljava/lang/IllegalArgumentException;
        //  270    284    287    291    Ljava/lang/IllegalArgumentException;
        //  277    298    301    305    Ljava/lang/IllegalArgumentException;
        //  383    397    400    404    Ljava/lang/IllegalArgumentException;
        //  390    417    420    424    Ljava/lang/IllegalArgumentException;
        //  404    435    435    439    Ljava/lang/IllegalArgumentException;
        //  445    463    466    470    Ljava/lang/IllegalArgumentException;
        //  456    481    484    488    Ljava/lang/IllegalArgumentException;
        //  470    496    499    503    Ljava/lang/IllegalArgumentException;
        //  488    505    505    509    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0089:
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
    
    private static boolean a(final OCType p0, final OCType p1, @NotNull final OCResolveContext p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "IsFloatingPointPromotion"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //    48: ifeq            166
        //    51: aload_1        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //    55: ifeq            166
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: checkcast       Lcom/jetbrains/cidr/lang/types/OCRealType;
        //    69: invokevirtual   com/jetbrains/cidr/lang/types/OCRealType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //    72: astore_3       
        //    73: aload_1        
        //    74: checkcast       Lcom/jetbrains/cidr/lang/types/OCRealType;
        //    77: invokevirtual   com/jetbrains/cidr/lang/types/OCRealType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //    80: astore          4
        //    82: aload_3        
        //    83: getstatic       com/jetbrains/cidr/lang/types/CTypeId.FLOAT:Lcom/jetbrains/cidr/lang/types/CTypeId;
        //    86: if_acmpne       110
        //    89: aload           4
        //    91: getstatic       com/jetbrains/cidr/lang/types/CTypeId.DOUBLE:Lcom/jetbrains/cidr/lang/types/CTypeId;
        //    94: if_acmpne       110
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: iconst_1       
        //   105: ireturn        
        //   106: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_2        
        //   111: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //   114: ifne            166
        //   117: aload_3        
        //   118: getstatic       com/jetbrains/cidr/lang/types/CTypeId.FLOAT:Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   121: if_acmpeq       145
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload_3        
        //   132: getstatic       com/jetbrains/cidr/lang/types/CTypeId.DOUBLE:Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   135: if_acmpne       166
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload           4
        //   147: getstatic       com/jetbrains/cidr/lang/types/CTypeId.LONG_DOUBLE:Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   150: if_acmpne       166
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: iconst_1       
        //   161: ireturn        
        //   162: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: iconst_0       
        //   167: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  82     97     100    104    Ljava/lang/IllegalArgumentException;
        //  89     106    106    110    Ljava/lang/IllegalArgumentException;
        //  110    124    127    131    Ljava/lang/IllegalArgumentException;
        //  117    138    141    145    Ljava/lang/IllegalArgumentException;
        //  131    153    156    160    Ljava/lang/IllegalArgumentException;
        //  145    162    162    166    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
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
    
    private static boolean a(@Nullable final OCTypeOwner p0, final OCType p1, final OCType p2, final boolean p3, final Ref<OCType> p4, final Ref<Boolean> p5, @NotNull final OCResolveContext p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           6
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "Context"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "IsPointerConversion"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload           5
        //    47: iconst_0       
        //    48: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    51: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //    54: aload_2        
        //    55: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    58: ifeq            90
        //    61: aload_0        
        //    62: iload_3        
        //    63: aload           6
        //    65: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    68: ifeq            90
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    77: athrow         
        //    78: aload           4
        //    80: aload_2        
        //    81: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //    84: iconst_1       
        //    85: ireturn        
        //    86: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload_1        
        //    91: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    94: ifeq            123
        //    97: aload_2        
        //    98: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToVoid:()Z
        //   101: ifeq            123
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload           4
        //   113: aload_2        
        //   114: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   117: iconst_1       
        //   118: ireturn        
        //   119: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload_2        
        //   124: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   127: ifeq            159
        //   130: aload_0        
        //   131: iload_3        
        //   132: aload           6
        //   134: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   137: ifeq            159
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload           4
        //   149: aload_2        
        //   150: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   153: iconst_1       
        //   154: ireturn        
        //   155: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_2        
        //   160: getstatic       com/jetbrains/cidr/lang/types/OCPointerType.NULLPTR_T:Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   163: if_acmpne       195
        //   166: aload_0        
        //   167: iload_3        
        //   168: aload           6
        //   170: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   173: ifeq            195
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: aload           4
        //   185: aload_2        
        //   186: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   189: iconst_1       
        //   190: ireturn        
        //   191: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload_2        
        //   196: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   199: ifne            208
        //   202: iconst_0       
        //   203: ireturn        
        //   204: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload_2        
        //   209: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   212: astore          7
        //   214: aload_0        
        //   215: iload_3        
        //   216: aload           6
        //   218: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   221: ifeq            236
        //   224: aload           4
        //   226: aload_2        
        //   227: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   230: iconst_1       
        //   231: ireturn        
        //   232: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: aload           7
        //   238: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   241: astore          8
        //   243: aload_1        
        //   244: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   247: ifeq            306
        //   250: aload           8
        //   252: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   255: ifeq            306
        //   258: goto            265
        //   261: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: aload           6
        //   267: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   270: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   273: ifne            306
        //   276: goto            283
        //   279: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   282: athrow         
        //   283: aload           4
        //   285: aload_1        
        //   286: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   289: aload           8
        //   291: aload_2        
        //   292: aload           6
        //   294: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   297: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   300: iconst_1       
        //   301: ireturn        
        //   302: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   305: athrow         
        //   306: aload_1        
        //   307: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   310: ifne            319
        //   313: iconst_0       
        //   314: ireturn        
        //   315: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   318: athrow         
        //   319: aload_1        
        //   320: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   323: astore          9
        //   325: aload           9
        //   327: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   330: astore          10
        //   332: aload           10
        //   334: aload           8
        //   336: aload           6
        //   338: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   341: ifeq            350
        //   344: iconst_0       
        //   345: ireturn        
        //   346: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   349: athrow         
        //   350: aload           10
        //   352: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isIncompleteOrObjectType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   355: ifeq            395
        //   358: aload           8
        //   360: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   363: ifeq            395
        //   366: goto            373
        //   369: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   372: athrow         
        //   373: aload           4
        //   375: aload           9
        //   377: aload           8
        //   379: aload_2        
        //   380: aload           6
        //   382: iconst_1       
        //   383: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //   386: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   389: iconst_1       
        //   390: ireturn        
        //   391: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   394: athrow         
        //   395: aload           6
        //   397: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //   400: ifne            443
        //   403: aload           10
        //   405: aload           8
        //   407: aload           6
        //   409: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   412: ifeq            443
        //   415: goto            422
        //   418: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   421: athrow         
        //   422: aload           4
        //   424: aload           9
        //   426: aload           8
        //   428: aload_2        
        //   429: aload           6
        //   431: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   434: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   437: iconst_1       
        //   438: ireturn        
        //   439: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   442: athrow         
        //   443: aload           6
        //   445: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //   448: ifeq            540
        //   451: aload           10
        //   453: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   456: ifeq            540
        //   459: goto            466
        //   462: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   465: athrow         
        //   466: aload           8
        //   468: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isRecordType:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   471: ifeq            540
        //   474: goto            481
        //   477: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   480: athrow         
        //   481: aload           10
        //   483: aload           8
        //   485: aload           6
        //   487: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   490: ifne            540
        //   493: goto            500
        //   496: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   499: athrow         
        //   500: aload           10
        //   502: aload           8
        //   504: aload           6
        //   506: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   509: ifeq            540
        //   512: goto            519
        //   515: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   518: athrow         
        //   519: aload           4
        //   521: aload           9
        //   523: aload           8
        //   525: aload_2        
        //   526: aload           6
        //   528: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   531: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   534: iconst_1       
        //   535: ireturn        
        //   536: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   539: athrow         
        //   540: iconst_0       
        //   541: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/intellij/openapi/util/Ref<Lcom/jetbrains/cidr/lang/types/OCType;>;Lcom/intellij/openapi/util/Ref<Ljava/lang/Boolean;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     71     74     78     Ljava/lang/IllegalArgumentException;
        //  61     86     86     90     Ljava/lang/IllegalArgumentException;
        //  90     104    107    111    Ljava/lang/IllegalArgumentException;
        //  97     119    119    123    Ljava/lang/IllegalArgumentException;
        //  123    140    143    147    Ljava/lang/IllegalArgumentException;
        //  130    155    155    159    Ljava/lang/IllegalArgumentException;
        //  159    176    179    183    Ljava/lang/IllegalArgumentException;
        //  166    191    191    195    Ljava/lang/IllegalArgumentException;
        //  195    204    204    208    Ljava/lang/IllegalArgumentException;
        //  214    232    232    236    Ljava/lang/IllegalArgumentException;
        //  243    258    261    265    Ljava/lang/IllegalArgumentException;
        //  250    276    279    283    Ljava/lang/IllegalArgumentException;
        //  265    302    302    306    Ljava/lang/IllegalArgumentException;
        //  306    315    315    319    Ljava/lang/IllegalArgumentException;
        //  332    346    346    350    Ljava/lang/IllegalArgumentException;
        //  350    366    369    373    Ljava/lang/IllegalArgumentException;
        //  358    391    391    395    Ljava/lang/IllegalArgumentException;
        //  395    415    418    422    Ljava/lang/IllegalArgumentException;
        //  403    439    439    443    Ljava/lang/IllegalArgumentException;
        //  443    459    462    466    Ljava/lang/IllegalArgumentException;
        //  451    474    477    481    Ljava/lang/IllegalArgumentException;
        //  466    493    496    500    Ljava/lang/IllegalArgumentException;
        //  481    512    515    519    Ljava/lang/IllegalArgumentException;
        //  500    536    536    540    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0265:
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
    
    private static boolean a(@Nullable final OCTypeOwner ocTypeOwner, final OCType ocType, final OCType ocType2, final boolean b, final Ref<OCType> ref, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/Conversions", "IsMemberPointerConversion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0075: {
            try {
                if (!(ocType2 instanceof OCPointerType)) {
                    return false;
                }
                final OCType ocType3 = ocType2;
                final OCPointerType ocPointerType = (OCPointerType)ocType3;
                final OCType ocType4 = ocPointerType.getClassQualifier();
                if (ocType4 == null) {
                    return false;
                }
                break Label_0075;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCType ocType3 = ocType2;
                final OCPointerType ocPointerType = (OCPointerType)ocType3;
                final OCType ocType4 = ocPointerType.getClassQualifier();
                if (ocType4 == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (a(ocTypeOwner, b, ocResolveContext)) {
                    ref.set((Object)ocType2);
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        Label_0127: {
            try {
                if (!(ocType instanceof OCPointerType)) {
                    return false;
                }
                final OCType ocType5 = ocType;
                final OCPointerType ocPointerType2 = (OCPointerType)ocType5;
                final OCType ocType6 = ocPointerType2.getClassQualifier();
                if (ocType6 == null) {
                    return false;
                }
                break Label_0127;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                final OCType ocType5 = ocType;
                final OCPointerType ocPointerType2 = (OCPointerType)ocType5;
                final OCType ocType6 = ocPointerType2.getClassQualifier();
                if (ocType6 == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        final OCType classQualifier = ((OCPointerType)ocType).getClassQualifier();
        final OCType classQualifier2 = ((OCPointerType)ocType2).getClassQualifier();
        Label_0176: {
            try {
                if (TypeProperties.hasSameUnqualifiedType(classQualifier, classQualifier2, ocResolveContext)) {
                    return false;
                }
                final OCType ocType7 = classQualifier2;
                final OCType ocType8 = classQualifier;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b2 = TypeProperties.IsDerivedFrom(ocType7, ocType8, ocResolveContext2);
                if (b2) {
                    break Label_0176;
                }
                return false;
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            try {
                final OCType ocType7 = classQualifier2;
                final OCType ocType8 = classQualifier;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b2 = TypeProperties.IsDerivedFrom(ocType7, ocType8, ocResolveContext2);
                if (b2) {
                    ref.set((Object)OCPointerType.to(((OCPointerType)ocType).getRefType(), null, classQualifier2));
                    return true;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        return false;
    }
    
    private static boolean a(@Nullable final OCTypeOwner ocTypeOwner, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/Conversions", "isNullPointerConstantForConversion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCExpressionEvaluator.isLikeNil(ocTypeOwner, ocResolveContext);
    }
    
    private static OCType a(final OCPointerType ocPointerType, final OCType ocType, final OCType ocType2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/Conversions", "BuildSimilarlyQualifiedPointerType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a(ocPointerType, ocType, ocType2, ocResolveContext, false);
    }
    
    private static OCType a(final OCPointerType ocPointerType, final OCType ocType, final OCType ocType2, @NotNull final OCResolveContext ocResolveContext, final boolean b) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/Conversions", "BuildSimilarlyQualifiedPointerType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocType2.isPointerToID()) {
                return ocType2.cloneWithoutCVQualifiers(ocResolveContext.getProject());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final CVQualifiers cvQualifiers = ocPointerType.getRefType().getCVQualifiers();
        try {
            if (ocType.getCVQualifiers() == cvQualifiers) {
                return ocType2.cloneWithoutCVQualifiers(ocResolveContext.getProject());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return OCPointerType.to(ocType.cloneWithCVQualifiers(cvQualifiers, ocResolveContext.getProject()));
    }
    
    private static boolean a(final OCType p0, final OCType p1, final boolean p2, @NotNull final OCResolveContext p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "IsQualificationConversion"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: aload_3        
        //    47: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    50: ifeq            59
        //    53: iconst_0       
        //    54: ireturn        
        //    55: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: iconst_1       
        //    60: istore          4
        //    62: iconst_0       
        //    63: istore          5
        //    65: aload_0        
        //    66: aload_1        
        //    67: aload_3        
        //    68: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.AreSimilarPointerTypes:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    71: ifeq            202
        //    74: aload_0        
        //    75: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    78: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    81: astore_0       
        //    82: aload_1        
        //    83: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    86: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    89: astore_1       
        //    90: iconst_1       
        //    91: istore          5
        //    93: aload_0        
        //    94: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //    97: astore          6
        //    99: aload_1        
        //   100: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   103: astore          7
        //   105: iload_2        
        //   106: ifne            132
        //   109: aload           7
        //   111: aload           6
        //   113: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isSuperset:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Z
        //   116: ifne            132
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_0       
        //   127: ireturn        
        //   128: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: iload_2        
        //   133: ifne            168
        //   136: aload           6
        //   138: aload           7
        //   140: if_acmpeq       168
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: iload           4
        //   152: ifne            168
        //   155: goto            162
        //   158: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: iconst_0       
        //   163: ireturn        
        //   164: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: iload           4
        //   170: ifeq            196
        //   173: aload           7
        //   175: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.isConst:()Z
        //   178: ifeq            196
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: iconst_1       
        //   189: goto            197
        //   192: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: iconst_0       
        //   197: istore          4
        //   199: goto            65
        //   202: iload           5
        //   204: ifeq            231
        //   207: aload_0        
        //   208: aload_1        
        //   209: aload_3        
        //   210: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   213: ifeq            231
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: iconst_1       
        //   224: goto            232
        //   227: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: iconst_0       
        //   232: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     55     55     59     Ljava/lang/IllegalArgumentException;
        //  105    119    122    126    Ljava/lang/IllegalArgumentException;
        //  109    128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    143    146    150    Ljava/lang/IllegalArgumentException;
        //  136    155    158    162    Ljava/lang/IllegalArgumentException;
        //  150    164    164    168    Ljava/lang/IllegalArgumentException;
        //  168    181    184    188    Ljava/lang/IllegalArgumentException;
        //  173    192    192    196    Ljava/lang/IllegalArgumentException;
        //  202    216    219    223    Ljava/lang/IllegalArgumentException;
        //  207    227    227    231    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0150:
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
    
    private static boolean a(@Nullable final OCTypeOwner p0, final OCType p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     4: ifeq            21
        //     7: aload_0        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    11: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParenthesesAndCasts:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    14: goto            22
        //    17: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: astore_0       
        //    23: aload_0        
        //    24: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //    27: ifeq            92
        //    30: aload_0        
        //    31: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //    34: invokeinterface com/jetbrains/cidr/lang/psi/OCLiteralExpression.isStringLiteral:()Z
        //    39: ifeq            92
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToChar:()Z
        //    53: ifeq            92
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_1        
        //    64: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    67: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    70: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //    73: getstatic       com/jetbrains/cidr/lang/types/CVQualifiers.EMPTY:Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //    76: if_acmpne       92
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: iconst_1       
        //    87: ireturn        
        //    88: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: iconst_0       
        //    93: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     17     21     Ljava/lang/IllegalArgumentException;
        //  23     42     45     49     Ljava/lang/IllegalArgumentException;
        //  30     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     79     82     86     Ljava/lang/IllegalArgumentException;
        //  63     88     88     92     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0049:
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
    
    public static ImplicitConversionSequence.CompareKind CompareImplicitConversionSequences(final ImplicitConversionSequence p0, final ImplicitConversionSequence p1, @NotNull final OCResolveContext p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "CompareImplicitConversionSequences"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //    48: ifeq            97
        //    51: aload_0        
        //    52: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasDeprecatedStringLiteralToCharPtrConversion:(Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;)Z
        //    55: aload_1        
        //    56: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasDeprecatedStringLiteralToCharPtrConversion:(Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;)Z
        //    59: if_icmpeq       97
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_0        
        //    70: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasDeprecatedStringLiteralToCharPtrConversion:(Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;)Z
        //    73: ifeq            93
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //    86: goto            96
        //    89: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //    96: areturn        
        //    97: aload_0        
        //    98: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.getKindRank:()I
        //   101: aload_1        
        //   102: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.getKindRank:()I
        //   105: if_icmpge       116
        //   108: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   111: areturn        
        //   112: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload_1        
        //   117: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.getKindRank:()I
        //   120: aload_0        
        //   121: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.getKindRank:()I
        //   124: if_icmpge       135
        //   127: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   130: areturn        
        //   131: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: aload_0        
        //   136: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.getKind:()Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$Kind;
        //   139: aload_1        
        //   140: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.getKind:()Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$Kind;
        //   143: if_acmpeq       154
        //   146: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   149: areturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   157: astore_3       
        //   158: aload_0        
        //   159: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isBad:()Z
        //   162: ifne            230
        //   165: aload_0        
        //   166: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isStdInitializerListElement:()Z
        //   169: ifeq            201
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: aload_1        
        //   180: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isStdInitializerListElement:()Z
        //   183: ifne            201
        //   186: goto            193
        //   189: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   196: areturn        
        //   197: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: aload_0        
        //   202: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isStdInitializerListElement:()Z
        //   205: ifne            230
        //   208: aload_1        
        //   209: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isStdInitializerListElement:()Z
        //   212: ifeq            230
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   225: areturn        
        //   226: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload_0        
        //   231: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isStandard:()Z
        //   234: ifeq            253
        //   237: aload_0        
        //   238: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   241: aload_1        
        //   242: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   245: aload_2        
        //   246: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.d:(Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   249: astore_3       
        //   250: goto            328
        //   253: aload_0        
        //   254: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isUserDefined:()Z
        //   257: ifeq            328
        //   260: aload_0        
        //   261: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   264: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.ConversionFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   267: aload_1        
        //   268: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   271: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.ConversionFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   274: invokestatic    java/util/Objects.equals:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   277: ifeq            309
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: aload_0        
        //   288: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   291: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   294: aload_1        
        //   295: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   298: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   301: aload_2        
        //   302: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.d:(Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   305: astore_3       
        //   306: goto            328
        //   309: aload_0        
        //   310: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   313: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.ConversionFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   316: aload_1        
        //   317: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   320: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.ConversionFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   323: aload_2        
        //   324: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   327: astore_3       
        //   328: aload_3        
        //   329: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     62     65     69     Ljava/lang/IllegalArgumentException;
        //  51     76     79     83     Ljava/lang/IllegalArgumentException;
        //  69     89     89     93     Ljava/lang/IllegalArgumentException;
        //  97     112    112    116    Ljava/lang/IllegalArgumentException;
        //  116    131    131    135    Ljava/lang/IllegalArgumentException;
        //  135    150    150    154    Ljava/lang/IllegalArgumentException;
        //  158    172    175    179    Ljava/lang/IllegalArgumentException;
        //  165    186    189    193    Ljava/lang/IllegalArgumentException;
        //  179    197    197    201    Ljava/lang/IllegalArgumentException;
        //  201    215    218    222    Ljava/lang/IllegalArgumentException;
        //  208    226    226    230    Ljava/lang/IllegalArgumentException;
        //  253    280    283    287    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    
    private static ImplicitConversionSequence.CompareKind d(final StandardConversionSequence p0, final StandardConversionSequence p1, @NotNull final OCResolveContext p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "CompareStandardConversionSequences"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: aload_2        
        //    47: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //    50: dup            
        //    51: astore_3       
        //    52: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //    55: if_acmpeq       64
        //    58: aload_3        
        //    59: areturn        
        //    60: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload_0        
        //    65: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getRank:()Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionRank;
        //    68: astore          4
        //    70: aload_1        
        //    71: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getRank:()Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionRank;
        //    74: astore          5
        //    76: aload           4
        //    78: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionRank.ordinal:()I
        //    81: aload           5
        //    83: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionRank.ordinal:()I
        //    86: if_icmpge       97
        //    89: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //    92: areturn        
        //    93: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: aload           5
        //    99: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionRank.ordinal:()I
        //   102: aload           4
        //   104: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionRank.ordinal:()I
        //   107: if_icmpge       118
        //   110: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   113: areturn        
        //   114: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: aload_0        
        //   119: aload_2        
        //   120: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.isPointerConversionToBool:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   123: aload_1        
        //   124: aload_2        
        //   125: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.isPointerConversionToBool:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   128: if_icmpeq       160
        //   131: aload_1        
        //   132: aload_2        
        //   133: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.isPointerConversionToBool:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   136: ifeq            156
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   149: goto            159
        //   152: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   159: areturn        
        //   160: aload_0        
        //   161: aload_2        
        //   162: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.isPointerConversionToVoidPointer:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   165: istore          6
        //   167: aload_1        
        //   168: aload_2        
        //   169: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.isPointerConversionToVoidPointer:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   172: istore          7
        //   174: iload           6
        //   176: iload           7
        //   178: if_icmpeq       207
        //   181: iload           7
        //   183: ifeq            203
        //   186: goto            193
        //   189: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   196: goto            206
        //   199: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   206: areturn        
        //   207: iload           6
        //   209: ifne            249
        //   212: iload           7
        //   214: ifne            249
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: aload_0        
        //   225: aload_1        
        //   226: aload_2        
        //   227: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.c:(Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   230: dup            
        //   231: astore          8
        //   233: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   236: if_acmpeq       246
        //   239: aload           8
        //   241: areturn        
        //   242: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: goto            490
        //   249: iload           6
        //   251: ifeq            490
        //   254: iload           7
        //   256: ifeq            490
        //   259: goto            266
        //   262: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: aload_0        
        //   267: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getFromType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   270: aload_1        
        //   271: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getFromType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   274: aload_2        
        //   275: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equalsAfterResolving:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   278: ifne            490
        //   281: goto            288
        //   284: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   287: athrow         
        //   288: aload_0        
        //   289: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getFromType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   292: astore          8
        //   294: aload_1        
        //   295: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getFromType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   298: astore          9
        //   300: aload_0        
        //   301: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   304: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Array_To_Pointer:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   307: if_acmpne       321
        //   310: aload           8
        //   312: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   315: aload_2        
        //   316: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getArrayDecayedType:(Lcom/jetbrains/cidr/lang/types/OCArrayType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   319: astore          8
        //   321: aload_1        
        //   322: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   325: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Array_To_Pointer:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   328: if_acmpne       342
        //   331: aload           9
        //   333: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   336: aload_2        
        //   337: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getArrayDecayedType:(Lcom/jetbrains/cidr/lang/types/OCArrayType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   340: astore          9
        //   342: aload           8
        //   344: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   347: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   350: aload_2        
        //   351: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   354: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   357: astore          10
        //   359: aload           9
        //   361: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   364: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   367: aload_2        
        //   368: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   371: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   374: astore          11
        //   376: aload           11
        //   378: aload           10
        //   380: aload_2        
        //   381: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   384: ifeq            395
        //   387: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   390: areturn        
        //   391: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   394: athrow         
        //   395: aload           10
        //   397: aload           11
        //   399: aload_2        
        //   400: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   403: ifeq            414
        //   406: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   409: areturn        
        //   410: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   413: athrow         
        //   414: aload           8
        //   416: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   419: ifeq            490
        //   422: aload           9
        //   424: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   427: ifeq            490
        //   430: goto            437
        //   433: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   436: athrow         
        //   437: aload           8
        //   439: aload           9
        //   441: aload_2        
        //   442: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.canAssignObjCInterfaces:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   445: istore          12
        //   447: aload           9
        //   449: aload           8
        //   451: aload_2        
        //   452: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.canAssignObjCInterfaces:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   455: istore          13
        //   457: iload           12
        //   459: iload           13
        //   461: if_icmpeq       490
        //   464: iload           12
        //   466: ifeq            486
        //   469: goto            476
        //   472: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   475: athrow         
        //   476: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   479: goto            489
        //   482: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   485: athrow         
        //   486: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   489: areturn        
        //   490: aload_0        
        //   491: aload_1        
        //   492: aload_2        
        //   493: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.b:(Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   496: dup            
        //   497: astore          8
        //   499: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   502: if_acmpeq       512
        //   505: aload           8
        //   507: areturn        
        //   508: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   511: athrow         
        //   512: aload_0        
        //   513: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ReferenceBinding:Z
        //   516: ifeq            757
        //   519: aload_1        
        //   520: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ReferenceBinding:Z
        //   523: ifeq            757
        //   526: goto            533
        //   529: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: aload_0        
        //   534: aload_1        
        //   535: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.isBetterReferenceBindingKind:(Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;)Z
        //   538: ifeq            556
        //   541: goto            548
        //   544: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   547: athrow         
        //   548: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   551: areturn        
        //   552: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   555: athrow         
        //   556: aload_1        
        //   557: aload_0        
        //   558: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.isBetterReferenceBindingKind:(Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;)Z
        //   561: ifeq            572
        //   564: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   567: areturn        
        //   568: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   571: athrow         
        //   572: aload_0        
        //   573: iconst_2       
        //   574: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getToType:(I)Lcom/jetbrains/cidr/lang/types/OCType;
        //   577: astore          9
        //   579: aload_1        
        //   580: iconst_2       
        //   581: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getToType:(I)Lcom/jetbrains/cidr/lang/types/OCType;
        //   584: astore          10
        //   586: aload           9
        //   588: aload_2        
        //   589: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   592: astore          9
        //   594: aload           10
        //   596: aload_2        
        //   597: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   600: astore          10
        //   602: aload           9
        //   604: aload_2        
        //   605: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   608: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getUnqualifiedArrayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/intellij/openapi/util/Pair;
        //   611: astore          11
        //   613: aload           10
        //   615: aload_2        
        //   616: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   619: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getUnqualifiedArrayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/intellij/openapi/util/Pair;
        //   622: astore          12
        //   624: aload           11
        //   626: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   629: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   632: astore          13
        //   634: aload           12
        //   636: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   639: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   642: astore          14
        //   644: aload           11
        //   646: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   649: checkcast       Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   652: astore          15
        //   654: aload           12
        //   656: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   659: checkcast       Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   662: astore          16
        //   664: aload           13
        //   666: aload           14
        //   668: aload_2        
        //   669: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   672: ifeq            757
        //   675: aload_0        
        //   676: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ObjCLifetimeConversionBinding:Z
        //   679: aload_1        
        //   680: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ObjCLifetimeConversionBinding:Z
        //   683: if_icmpeq       721
        //   686: goto            693
        //   689: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   692: athrow         
        //   693: aload_0        
        //   694: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.ObjCLifetimeConversionBinding:Z
        //   697: ifeq            717
        //   700: goto            707
        //   703: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   706: athrow         
        //   707: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   710: goto            720
        //   713: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   716: athrow         
        //   717: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   720: areturn        
        //   721: aload           16
        //   723: aload           15
        //   725: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isMoreQualifiedThan:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Z
        //   728: ifeq            739
        //   731: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   734: areturn        
        //   735: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   738: athrow         
        //   739: aload           15
        //   741: aload           16
        //   743: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isMoreQualifiedThan:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/jetbrains/cidr/lang/types/CVQualifiers;)Z
        //   746: ifeq            757
        //   749: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   752: areturn        
        //   753: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   756: athrow         
        //   757: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   760: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  52     60     60     64     Ljava/lang/IllegalArgumentException;
        //  76     93     93     97     Ljava/lang/IllegalArgumentException;
        //  97     114    114    118    Ljava/lang/IllegalArgumentException;
        //  118    139    142    146    Ljava/lang/IllegalArgumentException;
        //  131    152    152    156    Ljava/lang/IllegalArgumentException;
        //  174    186    189    193    Ljava/lang/IllegalArgumentException;
        //  181    199    199    203    Ljava/lang/IllegalArgumentException;
        //  207    217    220    224    Ljava/lang/IllegalArgumentException;
        //  233    242    242    246    Ljava/lang/IllegalArgumentException;
        //  249    259    262    266    Ljava/lang/IllegalArgumentException;
        //  254    281    284    288    Ljava/lang/IllegalArgumentException;
        //  376    391    391    395    Ljava/lang/IllegalArgumentException;
        //  395    410    410    414    Ljava/lang/IllegalArgumentException;
        //  414    430    433    437    Ljava/lang/IllegalArgumentException;
        //  457    469    472    476    Ljava/lang/IllegalArgumentException;
        //  464    482    482    486    Ljava/lang/IllegalArgumentException;
        //  499    508    508    512    Ljava/lang/IllegalArgumentException;
        //  512    526    529    533    Ljava/lang/IllegalArgumentException;
        //  519    541    544    548    Ljava/lang/IllegalArgumentException;
        //  533    552    552    556    Ljava/lang/IllegalArgumentException;
        //  556    568    568    572    Ljava/lang/IllegalArgumentException;
        //  664    686    689    693    Ljava/lang/IllegalArgumentException;
        //  675    700    703    707    Ljava/lang/IllegalArgumentException;
        //  693    713    713    717    Ljava/lang/IllegalArgumentException;
        //  721    735    735    739    Ljava/lang/IllegalArgumentException;
        //  739    753    753    757    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0533:
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
    
    private static ImplicitConversionSequence.CompareKind a(final StandardConversionSequence standardConversionSequence, final StandardConversionSequence standardConversionSequence2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/Conversions", "compareStandardConversionSubsets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ImplicitConversionSequence.CompareKind compareKind = ImplicitConversionSequence.CompareKind.Indistinguishable;
        Label_0296: {
            Label_0272: {
                Label_0258: {
                    Label_0234: {
                        Label_0222: {
                            Label_0187: {
                                Label_0162: {
                                    Label_0141: {
                                        Label_0106: {
                                            Label_0098: {
                                                Label_0077: {
                                                    Label_0069: {
                                                        try {
                                                            if (!standardConversionSequence.isIdentityConversion()) {
                                                                break Label_0077;
                                                            }
                                                            final StandardConversionSequence standardConversionSequence3 = standardConversionSequence2;
                                                            final boolean b = standardConversionSequence3.isIdentityConversion();
                                                            if (!b) {
                                                                break Label_0069;
                                                            }
                                                            break Label_0077;
                                                        }
                                                        catch (IllegalArgumentException ex2) {
                                                            throw a(ex2);
                                                        }
                                                        try {
                                                            final StandardConversionSequence standardConversionSequence3 = standardConversionSequence2;
                                                            final boolean b = standardConversionSequence3.isIdentityConversion();
                                                            if (!b) {
                                                                return ImplicitConversionSequence.CompareKind.Better;
                                                            }
                                                        }
                                                        catch (IllegalArgumentException ex3) {
                                                            throw a(ex3);
                                                        }
                                                    }
                                                    try {
                                                        if (standardConversionSequence.isIdentityConversion()) {
                                                            break Label_0106;
                                                        }
                                                        final StandardConversionSequence standardConversionSequence4 = standardConversionSequence2;
                                                        final boolean b2 = standardConversionSequence4.isIdentityConversion();
                                                        if (b2) {
                                                            break Label_0098;
                                                        }
                                                        break Label_0106;
                                                    }
                                                    catch (IllegalArgumentException ex4) {
                                                        throw a(ex4);
                                                    }
                                                }
                                                try {
                                                    final StandardConversionSequence standardConversionSequence4 = standardConversionSequence2;
                                                    final boolean b2 = standardConversionSequence4.isIdentityConversion();
                                                    if (b2) {
                                                        return ImplicitConversionSequence.CompareKind.Worse;
                                                    }
                                                }
                                                catch (IllegalArgumentException ex5) {
                                                    throw a(ex5);
                                                }
                                            }
                                            try {
                                                if (standardConversionSequence.Second == standardConversionSequence2.Second) {
                                                    break Label_0162;
                                                }
                                                if (standardConversionSequence.Second != ImplicitConversionKind.ICK_Identity) {
                                                    break Label_0141;
                                                }
                                            }
                                            catch (IllegalArgumentException ex6) {
                                                throw a(ex6);
                                            }
                                        }
                                        compareKind = ImplicitConversionSequence.CompareKind.Better;
                                        break Label_0187;
                                    }
                                    if (standardConversionSequence2.Second == ImplicitConversionKind.ICK_Identity) {
                                        compareKind = ImplicitConversionSequence.CompareKind.Worse;
                                        break Label_0187;
                                    }
                                    return ImplicitConversionSequence.CompareKind.Indistinguishable;
                                    try {
                                        if (!TypeProperties.hasSimilarType(standardConversionSequence.getToType(1), standardConversionSequence2.getToType(1), ocResolveContext)) {
                                            return ImplicitConversionSequence.CompareKind.Indistinguishable;
                                        }
                                    }
                                    catch (IllegalArgumentException ex7) {
                                        throw a(ex7);
                                    }
                                }
                                try {
                                    if (standardConversionSequence.Third != standardConversionSequence2.Third) {
                                        break Label_0234;
                                    }
                                    final StandardConversionSequence standardConversionSequence5 = standardConversionSequence;
                                    final int n = 2;
                                    final OCType ocType = standardConversionSequence5.getToType(n);
                                    final StandardConversionSequence standardConversionSequence6 = standardConversionSequence2;
                                    final int n2 = 2;
                                    final OCType ocType2 = standardConversionSequence6.getToType(n2);
                                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                                    final boolean b3 = ocType.equals(ocType2, ocResolveContext2);
                                    if (b3) {
                                        break Label_0222;
                                    }
                                    return ImplicitConversionSequence.CompareKind.Indistinguishable;
                                }
                                catch (IllegalArgumentException ex8) {
                                    throw a(ex8);
                                }
                            }
                            try {
                                final StandardConversionSequence standardConversionSequence5 = standardConversionSequence;
                                final int n = 2;
                                final OCType ocType = standardConversionSequence5.getToType(n);
                                final StandardConversionSequence standardConversionSequence6 = standardConversionSequence2;
                                final int n2 = 2;
                                final OCType ocType2 = standardConversionSequence6.getToType(n2);
                                final OCResolveContext ocResolveContext2 = ocResolveContext;
                                final boolean b3 = ocType.equals(ocType2, ocResolveContext2);
                                if (b3) {
                                    return compareKind;
                                }
                            }
                            catch (IllegalArgumentException ex9) {
                                throw a(ex9);
                            }
                        }
                        return ImplicitConversionSequence.CompareKind.Indistinguishable;
                        try {
                            if (standardConversionSequence.Third != ImplicitConversionKind.ICK_Identity) {
                                break Label_0272;
                            }
                            final ImplicitConversionSequence.CompareKind compareKind2 = compareKind;
                            final ImplicitConversionSequence.CompareKind compareKind3 = ImplicitConversionSequence.CompareKind.Worse;
                            if (compareKind2 == compareKind3) {
                                break Label_0258;
                            }
                            return ImplicitConversionSequence.CompareKind.Better;
                        }
                        catch (IllegalArgumentException ex10) {
                            throw a(ex10);
                        }
                    }
                    try {
                        final ImplicitConversionSequence.CompareKind compareKind2 = compareKind;
                        final ImplicitConversionSequence.CompareKind compareKind3 = ImplicitConversionSequence.CompareKind.Worse;
                        if (compareKind2 == compareKind3) {
                            return ImplicitConversionSequence.CompareKind.Indistinguishable;
                        }
                    }
                    catch (IllegalArgumentException ex11) {
                        throw a(ex11);
                    }
                }
                return ImplicitConversionSequence.CompareKind.Better;
                try {
                    if (standardConversionSequence2.Third != ImplicitConversionKind.ICK_Identity) {
                        return ImplicitConversionSequence.CompareKind.Indistinguishable;
                    }
                    final ImplicitConversionSequence.CompareKind compareKind5 = compareKind;
                    final ImplicitConversionSequence.CompareKind compareKind6 = ImplicitConversionSequence.CompareKind.Better;
                    if (compareKind5 == compareKind6) {
                        break Label_0296;
                    }
                    return ImplicitConversionSequence.CompareKind.Worse;
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
            }
            try {
                final ImplicitConversionSequence.CompareKind compareKind5 = compareKind;
                final ImplicitConversionSequence.CompareKind compareKind6 = ImplicitConversionSequence.CompareKind.Better;
                if (compareKind5 == compareKind6) {
                    return ImplicitConversionSequence.CompareKind.Indistinguishable;
                }
            }
            catch (IllegalArgumentException ex13) {
                throw a(ex13);
            }
        }
        return ImplicitConversionSequence.CompareKind.Worse;
    }
    
    private static ImplicitConversionSequence.CompareKind a(final OCFunctionSymbol ocFunctionSymbol, final OCFunctionSymbol ocFunctionSymbol2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/Conversions", "compareConversionFunctions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ImplicitConversionSequence.CompareKind.Indistinguishable;
    }
    
    private static ImplicitConversionSequence.CompareKind c(final StandardConversionSequence p0, final StandardConversionSequence p1, @NotNull final OCResolveContext p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "CompareDerivedToBaseConversions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getFromType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    48: astore_3       
        //    49: aload_0        
        //    50: iconst_1       
        //    51: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getToType:(I)Lcom/jetbrains/cidr/lang/types/OCType;
        //    54: astore          4
        //    56: aload_1        
        //    57: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getFromType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    60: astore          5
        //    62: aload_1        
        //    63: iconst_1       
        //    64: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getToType:(I)Lcom/jetbrains/cidr/lang/types/OCType;
        //    67: astore          6
        //    69: aload_0        
        //    70: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    73: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Array_To_Pointer:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    76: if_acmpne       88
        //    79: aload_3        
        //    80: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    83: aload_2        
        //    84: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getArrayDecayedType:(Lcom/jetbrains/cidr/lang/types/OCArrayType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    87: astore_3       
        //    88: aload_1        
        //    89: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    92: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Array_To_Pointer:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    95: if_acmpne       109
        //    98: aload           5
        //   100: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   103: aload_2        
        //   104: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getArrayDecayedType:(Lcom/jetbrains/cidr/lang/types/OCArrayType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   107: astore          5
        //   109: aload_3        
        //   110: aload_2        
        //   111: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   114: astore_3       
        //   115: aload           4
        //   117: aload_2        
        //   118: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   121: astore          4
        //   123: aload           5
        //   125: aload_2        
        //   126: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   129: astore          5
        //   131: aload           6
        //   133: aload_2        
        //   134: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   137: astore          6
        //   139: aload_0        
        //   140: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   143: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Pointer_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   146: if_acmpne       427
        //   149: aload_1        
        //   150: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   153: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Pointer_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   156: if_acmpne       427
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_3        
        //   167: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   170: ifeq            427
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   179: athrow         
        //   180: aload           5
        //   182: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   185: ifeq            427
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload           4
        //   197: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   200: ifeq            427
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: aload           6
        //   212: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   215: ifeq            427
        //   218: goto            225
        //   221: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: aload_3        
        //   226: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   229: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   232: aload_2        
        //   233: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   236: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   239: astore          7
        //   241: aload           4
        //   243: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   246: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   249: aload_2        
        //   250: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   253: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   256: astore          8
        //   258: aload           5
        //   260: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   263: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   266: aload_2        
        //   267: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   270: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   273: astore          9
        //   275: aload           6
        //   277: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   280: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   283: aload_2        
        //   284: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   287: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   290: astore          10
        //   292: aload           7
        //   294: aload           9
        //   296: if_acmpne       358
        //   299: aload           8
        //   301: aload           10
        //   303: if_acmpeq       358
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: aload           8
        //   315: aload           10
        //   317: aload_2        
        //   318: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   321: ifeq            339
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   330: athrow         
        //   331: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   334: areturn        
        //   335: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   338: athrow         
        //   339: aload           10
        //   341: aload           8
        //   343: aload_2        
        //   344: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   347: ifeq            358
        //   350: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   353: areturn        
        //   354: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   357: athrow         
        //   358: aload           7
        //   360: aload           9
        //   362: if_acmpeq       424
        //   365: aload           8
        //   367: aload           10
        //   369: if_acmpne       424
        //   372: goto            379
        //   375: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   378: athrow         
        //   379: aload           9
        //   381: aload           7
        //   383: aload_2        
        //   384: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   387: ifeq            405
        //   390: goto            397
        //   393: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   396: athrow         
        //   397: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   400: areturn        
        //   401: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   404: athrow         
        //   405: aload           7
        //   407: aload           9
        //   409: aload_2        
        //   410: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   413: ifeq            424
        //   416: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   419: areturn        
        //   420: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   423: athrow         
        //   424: goto            995
        //   427: aload_0        
        //   428: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   431: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Pointer_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   434: if_acmpne       995
        //   437: aload_1        
        //   438: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   441: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Pointer_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   444: if_acmpne       995
        //   447: goto            454
        //   450: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   453: athrow         
        //   454: aload_3        
        //   455: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   458: ifeq            995
        //   461: goto            468
        //   464: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   467: athrow         
        //   468: aload           5
        //   470: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   473: ifeq            995
        //   476: goto            483
        //   479: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   482: athrow         
        //   483: aload           4
        //   485: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   488: ifeq            995
        //   491: goto            498
        //   494: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   497: athrow         
        //   498: aload           6
        //   500: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   503: ifeq            995
        //   506: goto            513
        //   509: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   512: athrow         
        //   513: aload_3        
        //   514: aload           5
        //   516: aload_2        
        //   517: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.canAssignObjCInterfaces:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   520: istore          7
        //   522: aload           5
        //   524: aload_3        
        //   525: aload_2        
        //   526: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.canAssignObjCInterfaces:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   529: istore          8
        //   531: aload           4
        //   533: aload           6
        //   535: aload_2        
        //   536: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.canAssignObjCInterfaces:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   539: istore          9
        //   541: aload           6
        //   543: aload           4
        //   545: aload_2        
        //   546: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.canAssignObjCInterfaces:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   549: istore          10
        //   551: aload           4
        //   553: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   556: ifeq            598
        //   559: aload           6
        //   561: iconst_1       
        //   562: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:(Z)Z
        //   565: ifne            590
        //   568: goto            575
        //   571: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   574: athrow         
        //   575: aload           6
        //   577: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   580: ifne            598
        //   583: goto            590
        //   586: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   589: athrow         
        //   590: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   593: areturn        
        //   594: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   597: athrow         
        //   598: aload           6
        //   600: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   603: ifeq            645
        //   606: aload           4
        //   608: iconst_1       
        //   609: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:(Z)Z
        //   612: ifne            637
        //   615: goto            622
        //   618: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   621: athrow         
        //   622: aload           4
        //   624: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   627: ifne            645
        //   630: goto            637
        //   633: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   636: athrow         
        //   637: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   640: areturn        
        //   641: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   644: athrow         
        //   645: aload           4
        //   647: iconst_1       
        //   648: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:(Z)Z
        //   651: ifeq            677
        //   654: aload           6
        //   656: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   659: ifne            677
        //   662: goto            669
        //   665: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   668: athrow         
        //   669: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   672: areturn        
        //   673: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   676: athrow         
        //   677: aload           6
        //   679: iconst_1       
        //   680: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:(Z)Z
        //   683: ifeq            709
        //   686: aload           4
        //   688: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   691: ifne            709
        //   694: goto            701
        //   697: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   700: athrow         
        //   701: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   704: areturn        
        //   705: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   708: athrow         
        //   709: aload           4
        //   711: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   714: ifeq            756
        //   717: aload           6
        //   719: iconst_1       
        //   720: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:(Z)Z
        //   723: ifne            748
        //   726: goto            733
        //   729: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   732: athrow         
        //   733: aload           6
        //   735: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   738: ifne            756
        //   741: goto            748
        //   744: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   747: athrow         
        //   748: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   751: areturn        
        //   752: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   755: athrow         
        //   756: aload           6
        //   758: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   761: ifeq            803
        //   764: aload           4
        //   766: iconst_1       
        //   767: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:(Z)Z
        //   770: ifne            795
        //   773: goto            780
        //   776: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   779: athrow         
        //   780: aload           4
        //   782: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   785: ifne            803
        //   788: goto            795
        //   791: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   794: athrow         
        //   795: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   798: areturn        
        //   799: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   802: athrow         
        //   803: aload           4
        //   805: iconst_1       
        //   806: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:(Z)Z
        //   809: ifeq            835
        //   812: aload           6
        //   814: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   817: ifne            835
        //   820: goto            827
        //   823: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   826: athrow         
        //   827: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   830: areturn        
        //   831: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   834: athrow         
        //   835: aload           6
        //   837: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   840: ifeq            866
        //   843: aload           4
        //   845: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   848: ifne            866
        //   851: goto            858
        //   854: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   857: athrow         
        //   858: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   861: areturn        
        //   862: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   865: athrow         
        //   866: aload_3        
        //   867: aload           5
        //   869: aload_2        
        //   870: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   873: ifeq            944
        //   876: aload_3        
        //   877: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   880: ifne            944
        //   883: goto            890
        //   886: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   889: athrow         
        //   890: aload_3        
        //   891: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isClassType:()Z
        //   894: ifne            944
        //   897: goto            904
        //   900: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   903: athrow         
        //   904: iload           9
        //   906: iload           10
        //   908: if_icmpeq       944
        //   911: goto            918
        //   914: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   917: athrow         
        //   918: iload           9
        //   920: ifeq            940
        //   923: goto            930
        //   926: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   929: athrow         
        //   930: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   933: goto            943
        //   936: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   939: athrow         
        //   940: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   943: areturn        
        //   944: aload           4
        //   946: aload           6
        //   948: aload_2        
        //   949: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   952: ifeq            995
        //   955: iload           7
        //   957: iload           8
        //   959: if_icmpeq       995
        //   962: goto            969
        //   965: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   968: athrow         
        //   969: iload           7
        //   971: ifeq            991
        //   974: goto            981
        //   977: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   980: athrow         
        //   981: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   984: goto            994
        //   987: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   990: athrow         
        //   991: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   994: areturn        
        //   995: aload_0        
        //   996: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   999: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Pointer_Member:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1002: if_acmpne       1337
        //  1005: aload_1        
        //  1006: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1009: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Pointer_Member:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1012: if_acmpne       1337
        //  1015: goto            1022
        //  1018: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1021: athrow         
        //  1022: aload_3        
        //  1023: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1026: ifeq            1337
        //  1029: goto            1036
        //  1032: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1035: athrow         
        //  1036: aload           5
        //  1038: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1041: ifeq            1337
        //  1044: goto            1051
        //  1047: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1050: athrow         
        //  1051: aload           4
        //  1053: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1056: ifeq            1337
        //  1059: goto            1066
        //  1062: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1065: athrow         
        //  1066: aload           6
        //  1068: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1071: ifeq            1337
        //  1074: goto            1081
        //  1077: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1080: athrow         
        //  1081: aload_3        
        //  1082: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1085: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1088: astore          7
        //  1090: aload           4
        //  1092: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1095: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1098: astore          8
        //  1100: aload           5
        //  1102: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1105: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1108: astore          9
        //  1110: aload           6
        //  1112: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //  1115: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1118: astore          10
        //  1120: aload           7
        //  1122: ifnull          1337
        //  1125: aload           8
        //  1127: ifnull          1337
        //  1130: goto            1137
        //  1133: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1136: athrow         
        //  1137: aload           9
        //  1139: ifnull          1337
        //  1142: goto            1149
        //  1145: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1148: athrow         
        //  1149: aload           10
        //  1151: ifnull          1337
        //  1154: goto            1161
        //  1157: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1160: athrow         
        //  1161: aload           7
        //  1163: aload_2        
        //  1164: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //  1167: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1170: astore          11
        //  1172: aload           8
        //  1174: aload_2        
        //  1175: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //  1178: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1181: astore          12
        //  1183: aload           9
        //  1185: aload_2        
        //  1186: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //  1189: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1192: astore          13
        //  1194: aload           10
        //  1196: aload_2        
        //  1197: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //  1200: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithoutCVQualifiers:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1203: astore          14
        //  1205: aload           11
        //  1207: aload           13
        //  1209: if_acmpne       1271
        //  1212: aload           12
        //  1214: aload           14
        //  1216: if_acmpeq       1271
        //  1219: goto            1226
        //  1222: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1225: athrow         
        //  1226: aload           12
        //  1228: aload           14
        //  1230: aload_2        
        //  1231: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1234: ifeq            1252
        //  1237: goto            1244
        //  1240: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1243: athrow         
        //  1244: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //  1247: areturn        
        //  1248: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1251: athrow         
        //  1252: aload           14
        //  1254: aload           12
        //  1256: aload_2        
        //  1257: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1260: ifeq            1271
        //  1263: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //  1266: areturn        
        //  1267: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1270: athrow         
        //  1271: aload           12
        //  1273: aload           14
        //  1275: if_acmpne       1337
        //  1278: aload           11
        //  1280: aload           13
        //  1282: if_acmpeq       1337
        //  1285: goto            1292
        //  1288: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1291: athrow         
        //  1292: aload           11
        //  1294: aload           13
        //  1296: aload_2        
        //  1297: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1300: ifeq            1318
        //  1303: goto            1310
        //  1306: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1309: athrow         
        //  1310: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //  1313: areturn        
        //  1314: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1317: athrow         
        //  1318: aload           13
        //  1320: aload           11
        //  1322: aload_2        
        //  1323: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1326: ifeq            1337
        //  1329: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //  1332: areturn        
        //  1333: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1336: athrow         
        //  1337: aload_0        
        //  1338: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1341: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Derived_To_Base:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //  1344: if_acmpne       1498
        //  1347: aload_3        
        //  1348: aload           5
        //  1350: aload_2        
        //  1351: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1354: ifeq            1427
        //  1357: goto            1364
        //  1360: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1363: athrow         
        //  1364: aload           4
        //  1366: aload           6
        //  1368: aload_2        
        //  1369: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1372: ifne            1427
        //  1375: goto            1382
        //  1378: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1381: athrow         
        //  1382: aload           4
        //  1384: aload           6
        //  1386: aload_2        
        //  1387: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1390: ifeq            1408
        //  1393: goto            1400
        //  1396: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1399: athrow         
        //  1400: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //  1403: areturn        
        //  1404: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1407: athrow         
        //  1408: aload           6
        //  1410: aload           4
        //  1412: aload_2        
        //  1413: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1416: ifeq            1427
        //  1419: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //  1422: areturn        
        //  1423: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1426: athrow         
        //  1427: aload_3        
        //  1428: aload           5
        //  1430: aload_2        
        //  1431: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1434: ifne            1498
        //  1437: aload           4
        //  1439: aload           6
        //  1441: aload_2        
        //  1442: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1445: ifeq            1498
        //  1448: goto            1455
        //  1451: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1454: athrow         
        //  1455: aload           5
        //  1457: aload_3        
        //  1458: aload_2        
        //  1459: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1462: ifeq            1480
        //  1465: goto            1472
        //  1468: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1471: athrow         
        //  1472: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //  1475: areturn        
        //  1476: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1479: athrow         
        //  1480: aload_3        
        //  1481: aload           5
        //  1483: aload_2        
        //  1484: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.IsDerivedFrom:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1487: ifeq            1498
        //  1490: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //  1493: areturn        
        //  1494: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1497: athrow         
        //  1498: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //  1501: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  139    159    162    166    Ljava/lang/IllegalArgumentException;
        //  149    173    176    180    Ljava/lang/IllegalArgumentException;
        //  166    188    191    195    Ljava/lang/IllegalArgumentException;
        //  180    203    206    210    Ljava/lang/IllegalArgumentException;
        //  195    218    221    225    Ljava/lang/IllegalArgumentException;
        //  292    306    309    313    Ljava/lang/IllegalArgumentException;
        //  299    324    327    331    Ljava/lang/IllegalArgumentException;
        //  313    335    335    339    Ljava/lang/IllegalArgumentException;
        //  339    354    354    358    Ljava/lang/IllegalArgumentException;
        //  358    372    375    379    Ljava/lang/IllegalArgumentException;
        //  365    390    393    397    Ljava/lang/IllegalArgumentException;
        //  379    401    401    405    Ljava/lang/IllegalArgumentException;
        //  405    420    420    424    Ljava/lang/IllegalArgumentException;
        //  427    447    450    454    Ljava/lang/IllegalArgumentException;
        //  437    461    464    468    Ljava/lang/IllegalArgumentException;
        //  454    476    479    483    Ljava/lang/IllegalArgumentException;
        //  468    491    494    498    Ljava/lang/IllegalArgumentException;
        //  483    506    509    513    Ljava/lang/IllegalArgumentException;
        //  551    568    571    575    Ljava/lang/IllegalArgumentException;
        //  559    583    586    590    Ljava/lang/IllegalArgumentException;
        //  575    594    594    598    Ljava/lang/IllegalArgumentException;
        //  598    615    618    622    Ljava/lang/IllegalArgumentException;
        //  606    630    633    637    Ljava/lang/IllegalArgumentException;
        //  622    641    641    645    Ljava/lang/IllegalArgumentException;
        //  645    662    665    669    Ljava/lang/IllegalArgumentException;
        //  654    673    673    677    Ljava/lang/IllegalArgumentException;
        //  677    694    697    701    Ljava/lang/IllegalArgumentException;
        //  686    705    705    709    Ljava/lang/IllegalArgumentException;
        //  709    726    729    733    Ljava/lang/IllegalArgumentException;
        //  717    741    744    748    Ljava/lang/IllegalArgumentException;
        //  733    752    752    756    Ljava/lang/IllegalArgumentException;
        //  756    773    776    780    Ljava/lang/IllegalArgumentException;
        //  764    788    791    795    Ljava/lang/IllegalArgumentException;
        //  780    799    799    803    Ljava/lang/IllegalArgumentException;
        //  803    820    823    827    Ljava/lang/IllegalArgumentException;
        //  812    831    831    835    Ljava/lang/IllegalArgumentException;
        //  835    851    854    858    Ljava/lang/IllegalArgumentException;
        //  843    862    862    866    Ljava/lang/IllegalArgumentException;
        //  866    883    886    890    Ljava/lang/IllegalArgumentException;
        //  876    897    900    904    Ljava/lang/IllegalArgumentException;
        //  890    911    914    918    Ljava/lang/IllegalArgumentException;
        //  904    923    926    930    Ljava/lang/IllegalArgumentException;
        //  918    936    936    940    Ljava/lang/IllegalArgumentException;
        //  944    962    965    969    Ljava/lang/IllegalArgumentException;
        //  955    974    977    981    Ljava/lang/IllegalArgumentException;
        //  969    987    987    991    Ljava/lang/IllegalArgumentException;
        //  995    1015   1018   1022   Ljava/lang/IllegalArgumentException;
        //  1005   1029   1032   1036   Ljava/lang/IllegalArgumentException;
        //  1022   1044   1047   1051   Ljava/lang/IllegalArgumentException;
        //  1036   1059   1062   1066   Ljava/lang/IllegalArgumentException;
        //  1051   1074   1077   1081   Ljava/lang/IllegalArgumentException;
        //  1120   1130   1133   1137   Ljava/lang/IllegalArgumentException;
        //  1125   1142   1145   1149   Ljava/lang/IllegalArgumentException;
        //  1137   1154   1157   1161   Ljava/lang/IllegalArgumentException;
        //  1205   1219   1222   1226   Ljava/lang/IllegalArgumentException;
        //  1212   1237   1240   1244   Ljava/lang/IllegalArgumentException;
        //  1226   1248   1248   1252   Ljava/lang/IllegalArgumentException;
        //  1252   1267   1267   1271   Ljava/lang/IllegalArgumentException;
        //  1271   1285   1288   1292   Ljava/lang/IllegalArgumentException;
        //  1278   1303   1306   1310   Ljava/lang/IllegalArgumentException;
        //  1292   1314   1314   1318   Ljava/lang/IllegalArgumentException;
        //  1318   1333   1333   1337   Ljava/lang/IllegalArgumentException;
        //  1337   1357   1360   1364   Ljava/lang/IllegalArgumentException;
        //  1347   1375   1378   1382   Ljava/lang/IllegalArgumentException;
        //  1364   1393   1396   1400   Ljava/lang/IllegalArgumentException;
        //  1382   1404   1404   1408   Ljava/lang/IllegalArgumentException;
        //  1408   1423   1423   1427   Ljava/lang/IllegalArgumentException;
        //  1427   1448   1451   1455   Ljava/lang/IllegalArgumentException;
        //  1437   1465   1468   1472   Ljava/lang/IllegalArgumentException;
        //  1455   1476   1476   1480   Ljava/lang/IllegalArgumentException;
        //  1480   1494   1494   1498   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0166:
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
    
    private static ImplicitConversionSequence.CompareKind b(final StandardConversionSequence p0, final StandardConversionSequence p1, @NotNull final OCResolveContext p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "CompareQualificationConversions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    48: aload_1        
        //    49: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    52: if_acmpne       108
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    59: aload_1        
        //    60: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    63: if_acmpne       108
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_0        
        //    74: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Third:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    77: aload_1        
        //    78: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Third:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    81: if_acmpne       108
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Third:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    95: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Qualification:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    98: if_acmpeq       116
        //   101: goto            108
        //   104: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   111: areturn        
        //   112: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload_0        
        //   117: iconst_2       
        //   118: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getToType:(I)Lcom/jetbrains/cidr/lang/types/OCType;
        //   121: astore_3       
        //   122: aload_1        
        //   123: iconst_2       
        //   124: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getToType:(I)Lcom/jetbrains/cidr/lang/types/OCType;
        //   127: astore          4
        //   129: aload_3        
        //   130: aload_2        
        //   131: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   134: astore_3       
        //   135: aload           4
        //   137: aload_2        
        //   138: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   141: astore          4
        //   143: aload_3        
        //   144: aload_2        
        //   145: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   148: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getUnqualifiedArrayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/intellij/openapi/util/Pair;
        //   151: astore          5
        //   153: aload           4
        //   155: aload_2        
        //   156: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   159: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getUnqualifiedArrayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/intellij/openapi/util/Pair;
        //   162: astore          6
        //   164: aload           5
        //   166: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   169: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   172: astore          7
        //   174: aload           6
        //   176: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   179: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   182: astore          8
        //   184: aload           5
        //   186: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   189: checkcast       Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   192: astore          9
        //   194: aload           6
        //   196: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //   199: checkcast       Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   202: astore          10
        //   204: aload           7
        //   206: aload           8
        //   208: aload_2        
        //   209: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   212: ifeq            223
        //   215: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   218: areturn        
        //   219: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aload_3        
        //   224: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   227: ifeq            257
        //   230: aload           9
        //   232: getstatic       com/jetbrains/cidr/lang/types/CVQualifiers.EMPTY:Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   235: if_acmpeq       257
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   244: athrow         
        //   245: aload           7
        //   247: aload           9
        //   249: aload_2        
        //   250: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   253: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   256: astore_3       
        //   257: aload           4
        //   259: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   262: ifeq            293
        //   265: aload           10
        //   267: getstatic       com/jetbrains/cidr/lang/types/CVQualifiers.EMPTY:Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   270: if_acmpeq       293
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload           8
        //   282: aload           10
        //   284: aload_2        
        //   285: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   288: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   291: astore          4
        //   293: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   296: astore          11
        //   298: aload_0        
        //   299: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.QualificationIncludesObjCLifetime:Z
        //   302: aload_1        
        //   303: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.QualificationIncludesObjCLifetime:Z
        //   306: if_icmpeq       338
        //   309: aload_0        
        //   310: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.QualificationIncludesObjCLifetime:Z
        //   313: ifeq            333
        //   316: goto            323
        //   319: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   326: goto            336
        //   329: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   332: athrow         
        //   333: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   336: astore          11
        //   338: aload_3        
        //   339: aload           4
        //   341: aload_2        
        //   342: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.AreSimilarPointerTypes:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   345: ifeq            482
        //   348: aload_3        
        //   349: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   352: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   355: astore_3       
        //   356: aload           4
        //   358: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   361: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   364: astore          4
        //   366: aload_3        
        //   367: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   370: aload           4
        //   372: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   375: if_acmpne       385
        //   378: goto            469
        //   381: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   384: athrow         
        //   385: aload           4
        //   387: aload_3        
        //   388: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isMoreQualifiedThan:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   391: ifeq            425
        //   394: aload           11
        //   396: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   399: if_acmpne       417
        //   402: goto            409
        //   405: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   408: athrow         
        //   409: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   412: areturn        
        //   413: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   416: athrow         
        //   417: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   420: astore          11
        //   422: goto            469
        //   425: aload_3        
        //   426: aload           4
        //   428: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isMoreQualifiedThan:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   431: ifeq            465
        //   434: aload           11
        //   436: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Better:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   439: if_acmpne       457
        //   442: goto            449
        //   445: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   448: athrow         
        //   449: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   452: areturn        
        //   453: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   456: athrow         
        //   457: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Worse:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   460: astore          11
        //   462: goto            469
        //   465: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   468: areturn        
        //   469: aload_3        
        //   470: aload           4
        //   472: aload_2        
        //   473: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   476: ifeq            338
        //   479: goto            482
        //   482: getstatic       com/jetbrains/cidr/lang/resolve/v2/Conversions$1.$SwitchMap$com$jetbrains$cidr$lang$resolve$v2$ImplicitConversionSequence$CompareKind:[I
        //   485: aload           11
        //   487: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.ordinal:()I
        //   490: iaload         
        //   491: tableswitch {
        //                2: 516
        //                3: 538
        //                4: 541
        //          default: 553
        //        }
        //   516: aload_0        
        //   517: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.DeprecatedStringLiteralToCharPtr:Z
        //   520: ifeq            553
        //   523: goto            530
        //   526: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   529: athrow         
        //   530: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   533: astore          11
        //   535: goto            553
        //   538: goto            553
        //   541: aload_1        
        //   542: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.DeprecatedStringLiteralToCharPtr:Z
        //   545: ifeq            553
        //   548: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind.Indistinguishable:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence$CompareKind;
        //   551: astore          11
        //   553: aload           11
        //   555: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     66     69     73     Ljava/lang/IllegalArgumentException;
        //  55     84     87     91     Ljava/lang/IllegalArgumentException;
        //  73     101    104    108    Ljava/lang/IllegalArgumentException;
        //  91     112    112    116    Ljava/lang/IllegalArgumentException;
        //  204    219    219    223    Ljava/lang/IllegalArgumentException;
        //  223    238    241    245    Ljava/lang/IllegalArgumentException;
        //  257    273    276    280    Ljava/lang/IllegalArgumentException;
        //  298    316    319    323    Ljava/lang/IllegalArgumentException;
        //  309    329    329    333    Ljava/lang/IllegalArgumentException;
        //  366    381    381    385    Ljava/lang/IllegalArgumentException;
        //  385    402    405    409    Ljava/lang/IllegalArgumentException;
        //  394    413    413    417    Ljava/lang/IllegalArgumentException;
        //  425    442    445    449    Ljava/lang/IllegalArgumentException;
        //  434    453    453    457    Ljava/lang/IllegalArgumentException;
        //  482    523    526    530    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    
    static boolean isBetterReferenceBindingKind(final StandardConversionSequence p0, final StandardConversionSequence p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsImplicitObjectArgumentWithoutRefQualifier:Z
        //     4: ifne            21
        //     7: aload_1        
        //     8: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsImplicitObjectArgumentWithoutRefQualifier:Z
        //    11: ifeq            27
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: iconst_0       
        //    22: ireturn        
        //    23: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IsLvalueReference:Z
        //    31: ifne            62
        //    34: aload_0        
        //    35: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToRvalue:Z
        //    38: ifeq            62
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: aload_1        
        //    49: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IsLvalueReference:Z
        //    52: ifne            118
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_0        
        //    63: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IsLvalueReference:Z
        //    66: ifeq            126
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_0        
        //    77: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToFunctionLvalue:Z
        //    80: ifeq            126
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload_1        
        //    91: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.IsLvalueReference:Z
        //    94: ifne            126
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload_1        
        //   105: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.BindsToFunctionLvalue:Z
        //   108: ifeq            126
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: iconst_1       
        //   119: goto            127
        //   122: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_0       
        //   127: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      23     23     27     Ljava/lang/IllegalArgumentException;
        //  27     41     44     48     Ljava/lang/IllegalArgumentException;
        //  34     55     58     62     Ljava/lang/IllegalArgumentException;
        //  48     69     72     76     Ljava/lang/IllegalArgumentException;
        //  62     83     86     90     Ljava/lang/IllegalArgumentException;
        //  76     97     100    104    Ljava/lang/IllegalArgumentException;
        //  90     111    114    118    Ljava/lang/IllegalArgumentException;
        //  104    122    122    126    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    
    public static ImplicitConversionSequence TryObjectArgumentInitialization(final OCType ocType, OCType refType, @NotNull final OCFunctionSymbol ocFunctionSymbol, final OCExprValueCategory ocExprValueCategory, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "Method", "com/jetbrains/cidr/lang/resolve/v2/Conversions", "TryObjectArgumentInitialization"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/Conversions", "TryObjectArgumentInitialization"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        CVQualifiers cvQualifiers = null;
        Label_0113: {
            try {
                if (ocFunctionSymbol.isCppDestructor()) {
                    cvQualifiers = CVQualifiers.CONST_VOLATILE;
                    break Label_0113;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            cvQualifiers = ocFunctionSymbol.getType().getCVQualifiers();
        }
        final CVQualifiers cvQualifiers2 = cvQualifiers;
        final OCType cloneWithCVQualifiers = ocType.cloneWithCVQualifiers(cvQualifiers2, ocResolveContext.getProject());
        final ImplicitConversionSequence implicitConversionSequence = new ImplicitConversionSequence();
        if (refType instanceof OCPointerType) {
            refType = ((OCPointerType)refType).getRefType();
        }
        Label_0172: {
            try {
                if (Conversions.$assertionsDisabled) {
                    break Label_0172;
                }
                final OCType ocType2 = refType;
                final boolean b = TypeProperties.isRecordType(ocType2);
                if (!b) {
                    break Label_0172;
                }
                break Label_0172;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final OCType ocType2 = refType;
                final boolean b = TypeProperties.isRecordType(ocType2);
                if (!b) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        final OCType resolve = refType.resolve(ocResolveContext);
        Label_0228: {
            try {
                if (cloneWithCVQualifiers.getCVQualifiers() == resolve.getCVQualifiers()) {
                    break Label_0228;
                }
                final OCType ocType3 = cloneWithCVQualifiers;
                final CVQualifiers cvQualifiers3 = ocType3.getCVQualifiers();
                final OCType ocType4 = resolve;
                final CVQualifiers cvQualifiers4 = ocType4.getCVQualifiers();
                final boolean b2 = cvQualifiers3.isSuperset(cvQualifiers4);
                if (!b2) {
                    break Label_0228;
                }
                break Label_0228;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                final OCType ocType3 = cloneWithCVQualifiers;
                final CVQualifiers cvQualifiers3 = ocType3.getCVQualifiers();
                final OCType ocType4 = resolve;
                final CVQualifiers cvQualifiers4 = ocType4.getCVQualifiers();
                final boolean b2 = cvQualifiers3.isSuperset(cvQualifiers4);
                if (!b2) {
                    implicitConversionSequence.setBad(BadConversionSequence.FailureKind.bad_qualifiers, refType, cloneWithCVQualifiers);
                    return implicitConversionSequence;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        ImplicitConversionKind second;
        if (ocType.resolve(ocResolveContext).equals(resolve, false, ocResolveContext)) {
            second = ImplicitConversionKind.ICK_Identity;
        }
        else {
            if (!TypeProperties.IsDerivedFrom(refType, ocType, ocResolveContext)) {
                implicitConversionSequence.setBad(BadConversionSequence.FailureKind.unrelated_class, refType, cloneWithCVQualifiers);
                return implicitConversionSequence;
            }
            second = ImplicitConversionKind.ICK_Derived_To_Base;
        }
        final TypeProperties.RefQualifier refQualifier = TypeProperties.getRefQualifier(ocFunctionSymbol.getType());
        StandardConversionSequence standard2 = null;
        boolean bindsImplicitObjectArgumentWithoutRefQualifier = false;
        Label_0552: {
            Label_0506: {
                StandardConversionSequence standard = null;
                Label_0424: {
                    Label_0399: {
                        try {
                            switch (refQualifier) {
                                case RQ_LValue: {
                                    break;
                                }
                                case RQ_RValue: {
                                    break Label_0399;
                                }
                                default: {}
                                case RQ_None: {
                                    break Label_0424;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex8) {
                            throw a(ex8);
                        }
                        Label_0381: {
                            try {
                                if (ocExprValueCategory == OCExprValueCategory.LValue) {
                                    break Label_0424;
                                }
                                final CVQualifiers cvQualifiers5 = cvQualifiers2;
                                final CVQualifiers cvQualifiers6 = CVQualifiers.CONST;
                                if (cvQualifiers5 != cvQualifiers6) {
                                    break Label_0381;
                                }
                                break Label_0424;
                            }
                            catch (IllegalArgumentException ex9) {
                                throw a(ex9);
                            }
                            try {
                                final CVQualifiers cvQualifiers5 = cvQualifiers2;
                                final CVQualifiers cvQualifiers6 = CVQualifiers.CONST;
                                if (cvQualifiers5 != cvQualifiers6) {
                                    implicitConversionSequence.setBad(BadConversionSequence.FailureKind.lvalue_ref_to_rvalue, refType, cloneWithCVQualifiers);
                                    return implicitConversionSequence;
                                }
                                break Label_0424;
                            }
                            catch (IllegalArgumentException ex10) {
                                throw a(ex10);
                            }
                        }
                        try {
                            if (!ocExprValueCategory.isRValue()) {
                                implicitConversionSequence.setBad(BadConversionSequence.FailureKind.rvalue_ref_to_lvalue, refType, cloneWithCVQualifiers);
                                return implicitConversionSequence;
                            }
                        }
                        catch (IllegalArgumentException ex11) {
                            throw a(ex11);
                        }
                    }
                    try {
                        implicitConversionSequence.setStandard();
                        implicitConversionSequence.Standard.setAsIdentityConversion();
                        implicitConversionSequence.Standard.Second = second;
                        implicitConversionSequence.Standard.setFromType(refType);
                        implicitConversionSequence.Standard.setAllToTypes(cloneWithCVQualifiers);
                        implicitConversionSequence.Standard.ReferenceBinding = true;
                        implicitConversionSequence.Standard.DirectBinding = true;
                        standard = implicitConversionSequence.Standard;
                        if (refQualifier != TypeProperties.RefQualifier.RQ_RValue) {
                            final boolean isLvalueReference = true;
                            break Label_0506;
                        }
                    }
                    catch (IllegalArgumentException ex12) {
                        throw a(ex12);
                    }
                }
                final boolean isLvalueReference = false;
                try {
                    standard.IsLvalueReference = isLvalueReference;
                    implicitConversionSequence.Standard.BindsToFunctionLvalue = false;
                    implicitConversionSequence.Standard.BindsToRvalue = ocExprValueCategory.isRValue();
                    standard2 = implicitConversionSequence.Standard;
                    if (refQualifier == TypeProperties.RefQualifier.RQ_None) {
                        bindsImplicitObjectArgumentWithoutRefQualifier = true;
                        break Label_0552;
                    }
                }
                catch (IllegalArgumentException ex13) {
                    throw a(ex13);
                }
            }
            bindsImplicitObjectArgumentWithoutRefQualifier = false;
        }
        standard2.BindsImplicitObjectArgumentWithoutRefQualifier = bindsImplicitObjectArgumentWithoutRefQualifier;
        return implicitConversionSequence;
    }
    
    private static ImplicitConversionSequence a(@Nullable final OCTypeOwner p0, final OCType p1, final OCType p2, final boolean p3, final boolean p4, final boolean p5, final boolean p6, final boolean p7, final boolean p8, @NotNull final OCResolveContext p9) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           9
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "context"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "TryUserDefinedConversion"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: new             Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //    48: dup            
        //    49: invokespecial   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.<init>:()V
        //    52: astore          10
        //    54: iload_3        
        //    55: ifeq            76
        //    58: aload           10
        //    60: getstatic       com/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind.no_conversion:Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;
        //    63: aload_0        
        //    64: aload_1        
        //    65: aload_2        
        //    66: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setBad:(Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //    69: aload           10
        //    71: areturn        
        //    72: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_0        
        //    77: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //    80: ifeq            166
        //    83: aload_0        
        //    84: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //    87: aload_2        
        //    88: iconst_1       
        //    89: aload           9
        //    91: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.checkCompoundInitializer:(Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    94: astore          11
        //    96: aload           11
        //    98: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   101: aload           9
        //   103: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   106: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //   109: ifne            166
        //   112: aload           10
        //   114: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setUserDefined:()V
        //   117: aload           10
        //   119: new             Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   122: dup            
        //   123: invokespecial   com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.<init>:()V
        //   126: putfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   129: aload           10
        //   131: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   134: new             Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   137: dup            
        //   138: invokespecial   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.<init>:()V
        //   141: putfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.Before:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   144: aload           10
        //   146: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   149: new             Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   152: dup            
        //   153: invokespecial   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.<init>:()V
        //   156: putfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   159: aload           10
        //   161: areturn        
        //   162: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_1        
        //   167: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   170: ifeq            350
        //   173: aload_2        
        //   174: aload_1        
        //   175: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   178: aload_0        
        //   179: aload           9
        //   181: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   184: aconst_null    
        //   185: iconst_0       
        //   186: aload           9
        //   188: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.checkConversionOperators:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCStructType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   191: astore          11
        //   193: aload           11
        //   195: ifnull          350
        //   198: aload           11
        //   200: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   203: aload           9
        //   205: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   208: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //   211: ifne            350
        //   214: goto            221
        //   217: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: aload           11
        //   223: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.isWithConversion:()Z
        //   226: ifeq            350
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: aload           11
        //   238: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getTypeAfterConversion:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   241: ifnull          350
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol;
        //   254: dup            
        //   255: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol.<init>:()V
        //   258: aload           11
        //   260: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getTypeAfterConversion:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   263: aload_2        
        //   264: iconst_0       
        //   265: iconst_1       
        //   266: iload           7
        //   268: iload           4
        //   270: aload           9
        //   272: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.calculateConversion:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   275: astore          12
        //   277: aload           12
        //   279: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isStandard:()Z
        //   282: ifeq            350
        //   285: aload           10
        //   287: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setUserDefined:()V
        //   290: aload           10
        //   292: new             Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   295: dup            
        //   296: invokespecial   com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.<init>:()V
        //   299: putfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   302: aload           10
        //   304: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   307: new             Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   310: dup            
        //   311: invokespecial   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.<init>:()V
        //   314: putfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.Before:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   317: aload           10
        //   319: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   322: aload           12
        //   324: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   327: putfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   330: aload           10
        //   332: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   335: aload           11
        //   337: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getImplicitConstructor:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   340: putfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.ConversionFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   343: aload           10
        //   345: areturn        
        //   346: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   349: athrow         
        //   350: aload_2        
        //   351: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   354: ifeq            570
        //   357: aload_2        
        //   358: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   361: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   364: aload_1        
        //   365: aload_0        
        //   366: aload           9
        //   368: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.checkConstructors:(Lcom/jetbrains/cidr/lang/types/OCStructType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   371: astore          11
        //   373: aload           11
        //   375: ifnull          570
        //   378: aload           11
        //   380: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   383: aload           9
        //   385: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   388: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isError:(Lcom/intellij/psi/PsiElement;)Z
        //   391: ifne            570
        //   394: goto            401
        //   397: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   400: athrow         
        //   401: aload           11
        //   403: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.isWithConversion:()Z
        //   406: ifeq            570
        //   409: goto            416
        //   412: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   415: athrow         
        //   416: aload           11
        //   418: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getTypeBeforeConversion:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   421: ifnull          570
        //   424: goto            431
        //   427: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   430: athrow         
        //   431: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol;
        //   434: dup            
        //   435: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol.<init>:()V
        //   438: aload_1        
        //   439: aload           11
        //   441: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getTypeBeforeConversion:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   444: iconst_0       
        //   445: iconst_1       
        //   446: iload           7
        //   448: iload           4
        //   450: aload           9
        //   452: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.calculateConversion:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;ZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence;
        //   455: astore          12
        //   457: aload           12
        //   459: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isStandard:()Z
        //   462: ifne            480
        //   465: aload           12
        //   467: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isEllipsis:()Z
        //   470: ifeq            570
        //   473: goto            480
        //   476: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   479: athrow         
        //   480: aload           10
        //   482: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setUserDefined:()V
        //   485: aload           10
        //   487: new             Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   490: dup            
        //   491: invokespecial   com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.<init>:()V
        //   494: putfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   497: aload           10
        //   499: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   502: aload           12
        //   504: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   507: ifnull          529
        //   510: goto            517
        //   513: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   516: athrow         
        //   517: aload           12
        //   519: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   522: goto            536
        //   525: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   528: athrow         
        //   529: new             Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   532: dup            
        //   533: invokespecial   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.<init>:()V
        //   536: putfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.Before:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   539: aload           10
        //   541: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   544: new             Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   547: dup            
        //   548: invokespecial   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.<init>:()V
        //   551: putfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.After:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //   554: aload           10
        //   556: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //   559: aload           11
        //   561: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getImplicitConstructor:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   564: putfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.ConversionFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   567: aload           10
        //   569: areturn        
        //   570: aload           10
        //   572: getstatic       com/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind.no_conversion:Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;
        //   575: aload_0        
        //   576: aload_1        
        //   577: aload_2        
        //   578: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.setBad:(Lcom/jetbrains/cidr/lang/resolve/v2/BadConversionSequence$FailureKind;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   581: aload           10
        //   583: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  54     72     72     76     Ljava/lang/IllegalArgumentException;
        //  96     162    162    166    Ljava/lang/IllegalArgumentException;
        //  193    214    217    221    Ljava/lang/IllegalArgumentException;
        //  198    229    232    236    Ljava/lang/IllegalArgumentException;
        //  221    244    247    251    Ljava/lang/IllegalArgumentException;
        //  277    346    346    350    Ljava/lang/IllegalArgumentException;
        //  373    394    397    401    Ljava/lang/IllegalArgumentException;
        //  378    409    412    416    Ljava/lang/IllegalArgumentException;
        //  401    424    427    431    Ljava/lang/IllegalArgumentException;
        //  457    473    476    480    Ljava/lang/IllegalArgumentException;
        //  465    510    513    517    Ljava/lang/IllegalArgumentException;
        //  480    525    525    529    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0221:
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
    
    private static boolean a(final OCType p0, final OCType p1, final OCTypeOwner p2, @NotNull final OCResolveContext p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/Conversions"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "CheckSingleAssignmentConstraints"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    48: ifne            65
        //    51: aload_1        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //    55: ifeq            109
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: aload_1        
        //    67: aconst_null    
        //    68: aload_2        
        //    69: ldc             Lcom/jetbrains/cidr/lang/psi/OCExpression;.class
        //    71: invokestatic    com/intellij/util/ObjectUtils.tryCast:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    74: checkcast       Lcom/intellij/psi/PsiElement;
        //    77: iconst_1       
        //    78: iconst_1       
        //    79: aload_3        
        //    80: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;ZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //    83: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //    86: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.OK:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //    89: if_acmpne       107
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iconst_1       
        //   100: goto            108
        //   103: invokestatic    com/jetbrains/cidr/lang/resolve/v2/Conversions.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: iconst_0       
        //   108: ireturn        
        //   109: iconst_1       
        //   110: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     92     95     99     Ljava/lang/IllegalArgumentException;
        //  65     103    103    107    Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    private static OCFunctionType a(@Nullable final OCType ocType) {
        try {
            if (ocType instanceof OCFunctionType) {
                return (OCFunctionType)ocType;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0043: {
            try {
                if (!(ocType instanceof OCPointerType)) {
                    return null;
                }
                final OCType ocType2 = ocType;
                final OCPointerType ocPointerType = (OCPointerType)ocType2;
                final OCType ocType3 = ocPointerType.getRefType();
                final boolean b = ocType3 instanceof OCFunctionType;
                if (b) {
                    break Label_0043;
                }
                return null;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCType ocType2 = ocType;
                final OCPointerType ocPointerType = (OCPointerType)ocType2;
                final OCType ocType3 = ocPointerType.getRefType();
                final boolean b = ocType3 instanceof OCFunctionType;
                if (b) {
                    return (OCFunctionType)((OCPointerType)ocType).getRefType();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!Conversions.class.desiredAssertionStatus()) {
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
    
    enum ReferenceCompareResult
    {
        Ref_Incompatible, 
        Ref_Related, 
        Ref_Compatible_With_Added_Qualification, 
        Ref_Compatible;
    }
}
