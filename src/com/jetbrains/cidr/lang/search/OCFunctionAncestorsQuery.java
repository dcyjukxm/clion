// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityAfterResolvingVisitor;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.containers.HashMap;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.Query;
import com.intellij.util.FilteredQuery;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.AbstractQuery;

public class OCFunctionAncestorsQuery extends AbstractQuery<OCFunctionSymbol>
{
    private final OCFunctionSymbol myFunction;
    private final boolean myProcessSameSymbols;
    private final boolean myGoTransitive;
    private final boolean myProcessConstructors;
    
    public OCFunctionAncestorsQuery(final OCFunctionSymbol ocFunctionSymbol, final boolean b, final boolean b2) {
        this(ocFunctionSymbol, b, b2, false);
    }
    
    public OCFunctionAncestorsQuery(final OCFunctionSymbol myFunction, final boolean myGoTransitive, final boolean myProcessSameSymbols, final boolean myProcessConstructors) {
        this.myFunction = myFunction;
        this.myGoTransitive = myGoTransitive;
        this.myProcessSameSymbols = myProcessSameSymbols;
        this.myProcessConstructors = myProcessConstructors;
    }
    
    @Nullable
    public static OCFunctionSymbol findFirstVirtual(final OCFunctionSymbol ocFunctionSymbol, final boolean b) {
        return (OCFunctionSymbol)new FilteredQuery((Query)new OCFunctionAncestorsQuery(ocFunctionSymbol, false, b), p0 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isVirtual:()Z
            //     4: ifne            35
            //     7: aload_0        
            //     8: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isOverride:()Z
            //    11: ifne            35
            //    14: goto            21
            //    17: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    20: athrow         
            //    21: aload_0        
            //    22: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFinal:()Z
            //    25: ifeq            43
            //    28: goto            35
            //    31: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    34: athrow         
            //    35: iconst_1       
            //    36: goto            44
            //    39: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    42: athrow         
            //    43: iconst_0       
            //    44: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      14     17     21     Ljava/lang/IllegalArgumentException;
            //  7      28     31     35     Ljava/lang/IllegalArgumentException;
            //  21     39     39     43     Ljava/lang/IllegalArgumentException;
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
        }).findFirst();
    }
    
    protected boolean processResults(@NotNull final Processor<OCFunctionSymbol> p0) {
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
        //    18: ldc             "consumer"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processResults"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    48: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    51: astore_2       
        //    52: aload_2        
        //    53: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    56: ifne            65
        //    59: iconst_1       
        //    60: ireturn        
        //    61: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_2        
        //    66: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isPredeclaration:()Z
        //    69: ifeq            89
        //    72: aload_2        
        //    73: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getDefinitionSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    76: astore_3       
        //    77: aload_3        
        //    78: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    81: ifeq            89
        //    84: aload_3        
        //    85: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    88: astore_2       
        //    89: new             Lcom/intellij/util/containers/HashMap;
        //    92: dup            
        //    93: invokespecial   com/intellij/util/containers/HashMap.<init>:()V
        //    96: astore_3       
        //    97: aload_0        
        //    98: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   101: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   104: astore          4
        //   106: aload_0        
        //   107: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   110: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   113: astore          5
        //   115: aload_0        
        //   116: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   119: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppDestructor:()Z
        //   122: istore          6
        //   124: aload_0        
        //   125: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   128: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //   131: istore          7
        //   133: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   136: dup            
        //   137: aload           4
        //   139: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   142: astore          8
        //   144: aload           5
        //   146: aload           8
        //   148: aload           4
        //   150: invokedynamic   value:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Lcom/jetbrains/cidr/lang/psi/OCFile;)Lcom/intellij/openapi/util/Condition;
        //   155: astore          9
        //   157: aload_2        
        //   158: astore          10
        //   160: aload_0        
        //   161: iload           6
        //   163: iload           7
        //   165: aload           10
        //   167: aload_3        
        //   168: invokedynamic   process:(Lcom/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery;ZZLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/intellij/util/containers/HashMap;)Lcom/intellij/util/Processor;
        //   173: astore          11
        //   175: aload_0        
        //   176: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   179: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriendOrStatic:()Z
        //   182: ifne            261
        //   185: iload           6
        //   187: ifne            223
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload_0        
        //   198: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myProcessConstructors:Z
        //   201: ifeq            231
        //   204: goto            211
        //   207: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: iload           7
        //   213: ifeq            231
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aconst_null    
        //   224: goto            238
        //   227: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload_0        
        //   232: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   235: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getName:()Ljava/lang/String;
        //   238: astore          12
        //   240: aload_2        
        //   241: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   244: aload           12
        //   246: iconst_0       
        //   247: aload_0        
        //   248: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myGoTransitive:Z
        //   251: aload           9
        //   253: aload           11
        //   255: aload           8
        //   257: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.processMembersInBaseTypes:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;ZZLcom/intellij/openapi/util/Condition;Lcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   260: pop            
        //   261: aload_0        
        //   262: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myProcessSameSymbols:Z
        //   265: ifeq            294
        //   268: aload_0        
        //   269: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myFunction:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   272: new             Lcom/intellij/util/FilteringProcessor;
        //   275: dup            
        //   276: aload           9
        //   278: aload           11
        //   280: invokespecial   com/intellij/util/FilteringProcessor.<init>:(Lcom/intellij/openapi/util/Condition;Lcom/intellij/util/Processor;)V
        //   283: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //   286: pop            
        //   287: goto            294
        //   290: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   293: athrow         
        //   294: aload_3        
        //   295: invokevirtual   com/intellij/util/containers/HashMap.values:()Ljava/util/Collection;
        //   298: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   303: astore          12
        //   305: aload           12
        //   307: invokeinterface java/util/Iterator.hasNext:()Z
        //   312: ifeq            385
        //   315: aload           12
        //   317: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   322: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   325: astore          13
        //   327: aload_0        
        //   328: getfield        com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.myProcessSameSymbols:Z
        //   331: ifeq            365
        //   334: aload           13
        //   336: new             Lcom/jetbrains/cidr/lang/util/OCCommonProcessors$TypeFilteredProcessor;
        //   339: dup            
        //   340: aload_1        
        //   341: ldc             Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;.class
        //   343: invokespecial   com/jetbrains/cidr/lang/util/OCCommonProcessors$TypeFilteredProcessor.<init>:(Lcom/intellij/util/Processor;Ljava/lang/Class;)V
        //   346: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //   349: ifne            382
        //   352: goto            359
        //   355: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   358: athrow         
        //   359: iconst_0       
        //   360: ireturn        
        //   361: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   364: athrow         
        //   365: aload_1        
        //   366: aload           13
        //   368: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   373: ifne            382
        //   376: iconst_0       
        //   377: ireturn        
        //   378: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   381: athrow         
        //   382: goto            305
        //   385: iconst_1       
        //   386: ireturn        
        //    Signature:
        //  (Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  52     61     61     65     Ljava/lang/IllegalArgumentException;
        //  175    190    193    197    Ljava/lang/IllegalArgumentException;
        //  185    204    207    211    Ljava/lang/IllegalArgumentException;
        //  197    216    219    223    Ljava/lang/IllegalArgumentException;
        //  211    227    227    231    Ljava/lang/IllegalArgumentException;
        //  261    287    290    294    Ljava/lang/IllegalArgumentException;
        //  327    352    355    359    Ljava/lang/IllegalArgumentException;
        //  334    361    361    365    Ljava/lang/IllegalArgumentException;
        //  365    378    378    382    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0197:
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
}
