// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

import com.intellij.openapi.util.Pair;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.types.CTypeId;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCNumericType;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;

public class TypeProperties
{
    public static boolean isAggregateType(@NotNull final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "isAggregateType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "isAggregateType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0115: {
            try {
                if (!(ocType instanceof OCStructType)) {
                    return false;
                }
                final OCType ocType2 = ocType;
                final boolean b = ocType2.isCppStructType();
                if (!b) {
                    return false;
                }
                break Label_0115;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final OCType ocType2 = ocType;
                final boolean b = ocType2.isCppStructType();
                if (!b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final OCStructSymbol symbol = ((OCStructType)ocType).getSymbol();
        try {
            if (symbol.hasDeclaredConstructor()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!symbol.hasBaseClasses()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return false;
    }
    
    public static boolean isEnum(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "isEnum"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0068: {
            try {
                if (!(ocType instanceof OCStructType)) {
                    return false;
                }
                final OCType ocType2 = ocType;
                final OCStructType ocStructType = (OCStructType)ocType2;
                final boolean b = ocStructType.isEnum();
                if (b) {
                    break Label_0068;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCType ocType2 = ocType;
                final OCStructType ocStructType = (OCStructType)ocType2;
                final boolean b = ocStructType.isEnum();
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
    
    public static boolean isRecordType(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "isRecordType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0068: {
            try {
                if (!(ocType instanceof OCStructType)) {
                    return false;
                }
                final OCType ocType2 = ocType;
                final OCStructType ocStructType = (OCStructType)ocType2;
                final boolean b = ocStructType.isEnum();
                if (!b) {
                    break Label_0068;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCType ocType2 = ocType;
                final OCStructType ocStructType = (OCStructType)ocType2;
                final boolean b = ocStructType.isEnum();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean isPromotableIntegerType(@NotNull final OCType p0, @NotNull final OCResolveContext p1) {
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
        //    18: ldc             "type"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/TypeProperties"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isPromotableIntegerType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "context"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/resolve/v2/TypeProperties"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isPromotableIntegerType"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    92: ifeq            164
        //    95: getstatic       com/jetbrains/cidr/lang/resolve/v2/TypeProperties$1.$SwitchMap$com$jetbrains$cidr$lang$types$CTypeId:[I
        //    98: aload_0        
        //    99: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   102: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   105: invokevirtual   com/jetbrains/cidr/lang/types/CTypeId.ordinal:()I
        //   108: iaload         
        //   109: tableswitch {
        //                2: 156
        //                3: 156
        //                4: 156
        //                5: 156
        //                6: 156
        //                7: 156
        //                8: 156
        //          default: 162
        //        }
        //   152: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: iconst_1       
        //   157: ireturn        
        //   158: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: iconst_0       
        //   163: ireturn        
        //   164: aload_0        
        //   165: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isEnum:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   168: ifeq            216
        //   171: aload_0        
        //   172: aload_1        
        //   173: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   176: ifne            214
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload_0        
        //   187: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   190: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   193: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isEnumClass:()Z
        //   196: ifne            214
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: iconst_1       
        //   207: goto            215
        //   210: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   213: athrow         
        //   214: iconst_0       
        //   215: ireturn        
        //   216: iconst_0       
        //   217: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     152    152    156    Ljava/lang/IllegalArgumentException;
        //  95     158    158    162    Ljava/lang/IllegalArgumentException;
        //  164    179    182    186    Ljava/lang/IllegalArgumentException;
        //  171    199    202    206    Ljava/lang/IllegalArgumentException;
        //  186    210    210    214    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0186:
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
    
    public static boolean isIntegerLikeType(@NotNull final OCType p0) {
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
        //    18: ldc             "type"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/TypeProperties"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isIntegerLikeType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    48: ifne            85
        //    51: aload_0        
        //    52: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isEnum:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //    55: ifeq            93
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    69: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    72: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isEnumClass:()Z
        //    75: ifne            93
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: iconst_1       
        //    86: goto            94
        //    89: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: iconst_0       
        //    94: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     78     81     85     Ljava/lang/IllegalArgumentException;
        //  65     89     89     93     Ljava/lang/IllegalArgumentException;
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
    
    public static boolean isIntegralType(@NotNull final OCType p0, @NotNull final OCResolveContext p1) {
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
        //    18: ldc             "type"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/TypeProperties"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isIntegralType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "context"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/resolve/v2/TypeProperties"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isIntegralType"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    92: ifne            123
        //    95: aload_1        
        //    96: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //    99: ifne            131
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: aload_0        
        //   110: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isEnum:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   113: ifeq            131
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: iconst_1       
        //   124: goto            132
        //   127: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: iconst_0       
        //   132: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     102    105    109    Ljava/lang/IllegalArgumentException;
        //  95     116    119    123    Ljava/lang/IllegalArgumentException;
        //  109    127    127    131    Ljava/lang/IllegalArgumentException;
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
    
    public static boolean isArithmeticType(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "isArithmeticType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0065: {
            try {
                if (ocType instanceof OCNumericType) {
                    break Label_0065;
                }
                final OCType ocType2 = ocType;
                final boolean b = isEnum(ocType2);
                if (b) {
                    break Label_0065;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCType ocType2 = ocType;
                final boolean b = isEnum(ocType2);
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
    
    public static boolean isSignedIntegerType(@NotNull final OCType p0) {
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
        //    18: ldc             "type"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/TypeProperties"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isSignedIntegerType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    48: ifeq            68
        //    51: aload_0        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    55: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.isSigned:()Z
        //    58: ifne            82
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: aload_0        
        //    69: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.isEnum:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //    72: ifeq            90
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: iconst_1       
        //    83: goto            91
        //    86: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: iconst_0       
        //    91: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     61     64     68     Ljava/lang/IllegalArgumentException;
        //  51     75     78     82     Ljava/lang/IllegalArgumentException;
        //  68     86     86     90     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
    
    public static boolean isAnyCharacterType(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "isAnyCharacterType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(ocType instanceof OCIntType)) {
                return false;
            }
            final int[] array = TypeProperties.TypeProperties$1.$SwitchMap$com$jetbrains$cidr$lang$types$CTypeId;
            final OCType ocType2 = ocType;
            final OCIntType ocIntType = (OCIntType)ocType2;
            final CTypeId cTypeId = ocIntType.getCTypeId();
            final int n = cTypeId.ordinal();
            final int n2 = array[n];
            switch (n2) {
                case 2:
                case 3:
                case 4:
                case 5:
                case 6: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            final int[] array = TypeProperties.TypeProperties$1.$SwitchMap$com$jetbrains$cidr$lang$types$CTypeId;
            final OCType ocType2 = ocType;
            final OCIntType ocIntType = (OCIntType)ocType2;
            final CTypeId cTypeId = ocIntType.getCTypeId();
            final int n = cTypeId.ordinal();
            final int n2 = array[n];
            switch (n2) {
                case 2:
                case 3:
                case 4:
                case 5:
                case 6: {
                    return true;
                }
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    public static boolean isCharacterType(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "isCharacterType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(ocType instanceof OCIntType)) {
                return false;
            }
            final int[] array = TypeProperties.TypeProperties$1.$SwitchMap$com$jetbrains$cidr$lang$types$CTypeId;
            final OCType ocType2 = ocType;
            final OCIntType ocIntType = (OCIntType)ocType2;
            final CTypeId cTypeId = ocIntType.getCTypeId();
            final int n = cTypeId.ordinal();
            final int n2 = array[n];
            switch (n2) {
                case 2:
                case 3: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            final int[] array = TypeProperties.TypeProperties$1.$SwitchMap$com$jetbrains$cidr$lang$types$CTypeId;
            final OCType ocType2 = ocType;
            final OCIntType ocIntType = (OCIntType)ocType2;
            final CTypeId cTypeId = ocIntType.getCTypeId();
            final int n = cTypeId.ordinal();
            final int n2 = array[n];
            switch (n2) {
                case 2:
                case 3: {
                    return true;
                }
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    public static OCType getEnumPromotionType(final OCType ocType) {
        return OCIntType.INT;
    }
    
    public static int getTypeSize(@NotNull final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "getTypeSize"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "getTypeSize"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocType instanceof OCNumericType) {
                return ocType.getSizeInBytes(ocResolveContext.getFile(), null);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (isEnum(ocType)) {
                return getEnumPromotionType(ocType).getSizeInBytes(ocResolveContext.getFile(), null);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        throw new IllegalArgumentException(ocType.getName());
    }
    
    public static boolean hasSameUnqualifiedType(final OCType ocType, final OCType ocType2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "hasSameUnqualifiedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocType2 == null || !new OCTypeEqualityVisitor(ocType2, true, false, false, false, false, true, true, ocResolveContext).equal(ocType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return true;
        b = false;
        return b;
    }
    
    public static boolean isIncompleteOrObjectType(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "isIncompleteOrObjectType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(ocType instanceof OCFunctionType)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    public static boolean IsDerivedFrom(final OCType ocType, final OCType ocType2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "IsDerivedFrom"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0071: {
            try {
                if (!(ocType instanceof OCStructType)) {
                    return false;
                }
                final OCType ocType3 = ocType2;
                final boolean b = ocType3 instanceof OCStructType;
                if (!b) {
                    return false;
                }
                break Label_0071;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCType ocType3 = ocType2;
                final boolean b = ocType3 instanceof OCStructType;
                if (!b) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        for (final OCStructSymbol ocStructSymbol : ((OCStructType)ocType).getStructs()) {
            try {
                if (((OCStructType)ocType2).getSymbol().isAncestor(ocStructSymbol, ocResolveContext)) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    public static boolean canAssignObjCInterfaces(final OCType ocType, final OCType ocType2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "canAssignObjCInterfaces"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocType.isCompatible(ocType2, ocResolveContext.getElement());
    }
    
    public static boolean AreSimilarPointerTypes(final OCType p0, final OCType p1, @NotNull final OCResolveContext p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/TypeProperties"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "AreSimilarPointerTypes"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    48: ifeq            65
        //    51: aload_1        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    55: ifne            71
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: iconst_0       
        //    66: ireturn        
        //    67: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload_0        
        //    72: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    75: astore_3       
        //    76: aload_1        
        //    77: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    80: astore          4
        //    82: aload_0        
        //    83: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    86: ifne            103
        //    89: aload_1        
        //    90: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    93: ifeq            141
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload_0        
        //   104: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   107: ifeq            139
        //   110: goto            117
        //   113: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: aload_1        
        //   118: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   121: ifeq            139
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: iconst_1       
        //   132: goto            140
        //   135: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: iconst_0       
        //   140: ireturn        
        //   141: aload_3        
        //   142: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   145: ifnonnull       163
        //   148: aload           4
        //   150: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   153: ifnull          225
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_3        
        //   164: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   167: ifnull          223
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: aload           4
        //   179: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   182: ifnull          223
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: aload_3        
        //   193: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   196: aload           4
        //   198: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getClassQualifier:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   201: aload_2        
        //   202: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.hasSameUnqualifiedType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   205: ifeq            223
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: iconst_1       
        //   216: goto            224
        //   219: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: iconst_0       
        //   224: ireturn        
        //   225: iconst_1       
        //   226: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     58     61     65     Ljava/lang/IllegalArgumentException;
        //  51     67     67     71     Ljava/lang/IllegalArgumentException;
        //  82     96     99     103    Ljava/lang/IllegalArgumentException;
        //  89     110    113    117    Ljava/lang/IllegalArgumentException;
        //  103    124    127    131    Ljava/lang/IllegalArgumentException;
        //  117    135    135    139    Ljava/lang/IllegalArgumentException;
        //  141    156    159    163    Ljava/lang/IllegalArgumentException;
        //  148    170    173    177    Ljava/lang/IllegalArgumentException;
        //  163    185    188    192    Ljava/lang/IllegalArgumentException;
        //  177    208    211    215    Ljava/lang/IllegalArgumentException;
        //  192    219    219    223    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0103:
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
    
    public static OCType getArrayDecayedType(final OCArrayType ocArrayType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "getArrayDecayedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCPointerType.to(ocArrayType.getRefType()).cloneWithCVQualifiers(ocArrayType.getArrayElementType().getCVQualifiers(), ocResolveContext.getProject());
    }
    
    public static boolean hasDeprecatedStringLiteralToCharPtrConversion(final ImplicitConversionSequence p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isStandard:()Z
        //     4: ifeq            24
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.Standard:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //    11: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.DeprecatedStringLiteralToCharPtr:Z
        //    14: ifne            58
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.isUserDefined:()Z
        //    28: ifeq            66
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_0        
        //    39: getfield        com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionSequence.UserDefined:Lcom/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence;
        //    42: getfield        com/jetbrains/cidr/lang/resolve/v2/UserDefinedConversionSequence.Before:Lcom/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence;
        //    45: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.DeprecatedStringLiteralToCharPtr:Z
        //    48: ifeq            66
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: iconst_1       
        //    59: goto            67
        //    62: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: iconst_0       
        //    67: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  7      31     34     38     Ljava/lang/IllegalArgumentException;
        //  24     51     54     58     Ljava/lang/IllegalArgumentException;
        //  38     62     62     66     Ljava/lang/IllegalArgumentException;
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
    
    public static boolean isMoreQualifiedThan(final OCType ocType, final OCType ocType2) {
        return isMoreQualifiedThan(ocType.getCVQualifiers(), ocType2.getCVQualifiers());
    }
    
    public static boolean isMoreQualifiedThan(final CVQualifiers cvQualifiers, final CVQualifiers cvQualifiers2) {
        Label_0020: {
            try {
                if (cvQualifiers == cvQualifiers2) {
                    return false;
                }
                final CVQualifiers cvQualifiers3 = cvQualifiers;
                final CVQualifiers cvQualifiers4 = cvQualifiers2;
                final boolean b = cvQualifiers3.isSuperset(cvQualifiers4);
                if (b) {
                    break Label_0020;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CVQualifiers cvQualifiers3 = cvQualifiers;
                final CVQualifiers cvQualifiers4 = cvQualifiers2;
                final boolean b = cvQualifiers3.isSuperset(cvQualifiers4);
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
    
    public static boolean hasSimilarType(OCType ocType, OCType ocType2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "hasSimilarType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        while (AreSimilarPointerTypes(ocType, ocType2, ocResolveContext)) {
            ocType = ((OCPointerType)ocType).getRefType();
            ocType2 = ((OCPointerType)ocType2).getRefType();
            ocType = ocType.cloneWithoutCVQualifiers(ocResolveContext.getProject());
            ocType2 = ocType2.cloneWithoutCVQualifiers(ocResolveContext.getProject());
        }
        return ocType.equals(ocType2, false, ocResolveContext);
    }
    
    public static Pair<OCType, CVQualifiers> getUnqualifiedArrayType(@NotNull final OCType ocType, @NotNull final Project project) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "getUnqualifiedArrayType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "getUnqualifiedArrayType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!(ocType instanceof OCArrayType)) {
                return (Pair<OCType, CVQualifiers>)Pair.create((Object)ocType.cloneWithoutCVQualifiers(project), (Object)ocType.getCVQualifiers());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Pair<OCType, CVQualifiers> unqualifiedArrayType = getUnqualifiedArrayType(((OCArrayType)ocType).getRefType(), project);
        return (Pair<OCType, CVQualifiers>)Pair.create((Object)OCArrayType.to((OCType)unqualifiedArrayType.first, ((OCArrayType)ocType).getLength()), (Object)ocType.getCVQualifiers().or((CVQualifiers)unqualifiedArrayType.second));
    }
    
    public static RefQualifier getRefQualifier(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/v2/TypeProperties", "getRefQualifier"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0076: {
            Label_0068: {
                try {
                    if (!(ocType instanceof OCFunctionType)) {
                        return RefQualifier.RQ_None;
                    }
                    final OCType ocType2 = ocType;
                    final OCFunctionType ocFunctionType = (OCFunctionType)ocType2;
                    final boolean b = ocFunctionType.isLValueRef();
                    if (b) {
                        break Label_0068;
                    }
                    break Label_0076;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCType ocType2 = ocType;
                    final OCFunctionType ocFunctionType = (OCFunctionType)ocType2;
                    final boolean b = ocFunctionType.isLValueRef();
                    if (b) {
                        return RefQualifier.RQ_LValue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                if (((OCFunctionType)ocType).isRValueRef()) {
                    return RefQualifier.RQ_RValue;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return RefQualifier.RQ_None;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum RefQualifier
    {
        RQ_None, 
        RQ_LValue, 
        RQ_RValue;
    }
}
