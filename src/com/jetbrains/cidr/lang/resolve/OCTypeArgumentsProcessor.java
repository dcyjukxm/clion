// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import org.jetbrains.annotations.Nullable;
import com.intellij.util.Function;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import java.util.List;

public class OCTypeArgumentsProcessor
{
    public static boolean processArguments(@NotNull final List<? extends OCTypeArgument> list, @NotNull final List<? extends OCTypeArgument> list2, @NotNull final TypeArgumentsProcessor typeArgumentsProcessor) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterTypes", "com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor", "processArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentTypes", "com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor", "processArguments"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (typeArgumentsProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor", "processArguments"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return processArguments(list, list2, null, true, typeArgumentsProcessor);
    }
    
    public static boolean processArguments(@NotNull final List<? extends OCTypeArgument> p0, @NotNull final List<? extends OCTypeArgument> p1, @Nullable final Function<OCTypeArgument, OCTypeArgument> p2, final boolean p3, @NotNull final TypeArgumentsProcessor p4) {
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
        //    18: ldc             "parameterTypes"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processArguments"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "argumentTypes"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processArguments"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload           4
        //    90: ifnonnull       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    99: ldc             3
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: ldc             0
        //   107: ldc             "processor"
        //   109: aastore        
        //   110: dup            
        //   111: ldc             1
        //   113: ldc             "com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             2
        //   119: ldc             "processArguments"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: iload_3        
        //   134: ifeq            261
        //   137: aload_0        
        //   138: invokeinterface java/util/List.size:()I
        //   143: aload_1        
        //   144: invokeinterface java/util/List.size:()I
        //   149: if_icmpeq       261
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: aload_0        
        //   160: invokeinterface java/util/List.size:()I
        //   165: ifle            255
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload_0        
        //   176: invokeinterface java/util/List.size:()I
        //   181: aload_1        
        //   182: invokeinterface java/util/List.size:()I
        //   187: iconst_1       
        //   188: iadd           
        //   189: if_icmpgt       255
        //   192: goto            199
        //   195: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: aload_0        
        //   200: aload_0        
        //   201: invokeinterface java/util/List.size:()I
        //   206: iconst_1       
        //   207: isub           
        //   208: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   213: ifnull          255
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aload_0        
        //   224: aload_0        
        //   225: invokeinterface java/util/List.size:()I
        //   230: iconst_1       
        //   231: isub           
        //   232: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   237: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   240: invokeinterface com/jetbrains/cidr/lang/types/OCTypeArgument.isVariadic:()Z
        //   245: ifne            261
        //   248: goto            255
        //   251: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   254: athrow         
        //   255: iconst_0       
        //   256: ireturn        
        //   257: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   260: athrow         
        //   261: iconst_0       
        //   262: istore          5
        //   264: iload           5
        //   266: aload_0        
        //   267: invokeinterface java/util/List.size:()I
        //   272: if_icmpge       594
        //   275: aload_0        
        //   276: iload           5
        //   278: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   283: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   286: astore          6
        //   288: aload           6
        //   290: ifnull          520
        //   293: aload           6
        //   295: invokeinterface com/jetbrains/cidr/lang/types/OCTypeArgument.isVariadic:()Z
        //   300: ifeq            520
        //   303: goto            310
        //   306: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   309: athrow         
        //   310: iload           5
        //   312: aload_0        
        //   313: invokeinterface java/util/List.size:()I
        //   318: iconst_1       
        //   319: isub           
        //   320: if_icmpne       518
        //   323: goto            330
        //   326: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   329: athrow         
        //   330: aload           6
        //   332: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   335: ifeq            358
        //   338: goto            345
        //   341: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: aload           6
        //   347: checkcast       Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   350: invokevirtual   com/jetbrains/cidr/lang/types/OCVariadicType.getUnderlyingType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   353: astore          6
        //   355: goto            381
        //   358: new             Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   361: dup            
        //   362: aload           6
        //   364: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   367: invokevirtual   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   370: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCVariadicPackExpressionSymbol;
        //   373: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCVariadicPackExpressionSymbol.getExpression:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   376: invokespecial   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.<init>:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;)V
        //   379: astore          6
        //   381: aload_2        
        //   382: ifnull          398
        //   385: aload_2        
        //   386: aload           6
        //   388: invokeinterface com/intellij/util/Function.fun:(Ljava/lang/Object;)Ljava/lang/Object;
        //   393: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   396: astore          6
        //   398: iload           5
        //   400: aload_1        
        //   401: invokeinterface java/util/List.size:()I
        //   406: if_icmplt       464
        //   409: aload           4
        //   411: aload           6
        //   413: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   416: ifeq            441
        //   419: goto            426
        //   422: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   425: athrow         
        //   426: aload           6
        //   428: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   431: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   434: goto            443
        //   437: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   440: athrow         
        //   441: aload           6
        //   443: new             Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
        //   446: dup            
        //   447: invokespecial   com/jetbrains/cidr/lang/types/OCExpansionPackType.<init>:()V
        //   450: invokeinterface com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor$TypeArgumentsProcessor.process:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Z
        //   455: ifne            464
        //   458: iconst_0       
        //   459: ireturn        
        //   460: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   463: athrow         
        //   464: iload           5
        //   466: istore          7
        //   468: iload           7
        //   470: aload_1        
        //   471: invokeinterface java/util/List.size:()I
        //   476: if_icmpge       518
        //   479: aload_1        
        //   480: iload           7
        //   482: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   487: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   490: astore          8
        //   492: aload           4
        //   494: aload           6
        //   496: aload           8
        //   498: invokeinterface com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor$TypeArgumentsProcessor.process:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Z
        //   503: ifne            512
        //   506: iconst_0       
        //   507: ireturn        
        //   508: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   511: athrow         
        //   512: iinc            7, 1
        //   515: goto            468
        //   518: iconst_1       
        //   519: ireturn        
        //   520: iload           5
        //   522: aload_1        
        //   523: invokeinterface java/util/List.size:()I
        //   528: if_icmplt       538
        //   531: goto            594
        //   534: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   537: athrow         
        //   538: aload_1        
        //   539: iload           5
        //   541: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   546: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   549: astore          7
        //   551: aload_2        
        //   552: ifnull          568
        //   555: aload_2        
        //   556: aload           6
        //   558: invokeinterface com/intellij/util/Function.fun:(Ljava/lang/Object;)Ljava/lang/Object;
        //   563: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   566: astore          6
        //   568: aload           4
        //   570: aload           6
        //   572: aload           7
        //   574: invokeinterface com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor$TypeArgumentsProcessor.process:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Z
        //   579: ifne            588
        //   582: iconst_0       
        //   583: ireturn        
        //   584: invokestatic    com/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   587: athrow         
        //   588: iinc            5, 1
        //   591: goto            264
        //   594: iconst_1       
        //   595: ireturn        
        //    Signature:
        //  (Ljava/util/List<+Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;Ljava/util/List<+Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;Lcom/intellij/util/Function<Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;ZLcom/jetbrains/cidr/lang/resolve/OCTypeArgumentsProcessor$TypeArgumentsProcessor;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     129    129    133    Ljava/lang/IllegalArgumentException;
        //  133    152    155    159    Ljava/lang/IllegalArgumentException;
        //  137    168    171    175    Ljava/lang/IllegalArgumentException;
        //  159    192    195    199    Ljava/lang/IllegalArgumentException;
        //  175    216    219    223    Ljava/lang/IllegalArgumentException;
        //  199    248    251    255    Ljava/lang/IllegalArgumentException;
        //  223    257    257    261    Ljava/lang/IllegalArgumentException;
        //  288    303    306    310    Ljava/lang/IllegalArgumentException;
        //  293    323    326    330    Ljava/lang/IllegalArgumentException;
        //  310    338    341    345    Ljava/lang/IllegalArgumentException;
        //  398    419    422    426    Ljava/lang/IllegalArgumentException;
        //  409    437    437    441    Ljava/lang/IllegalArgumentException;
        //  443    460    460    464    Ljava/lang/IllegalArgumentException;
        //  492    508    508    512    Ljava/lang/IllegalArgumentException;
        //  520    534    534    538    Ljava/lang/IllegalArgumentException;
        //  568    584    584    588    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0159:
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
    
    @FunctionalInterface
    public interface TypeArgumentsProcessor
    {
        boolean process(final OCTypeArgument p0, final OCTypeArgument p1);
    }
}
