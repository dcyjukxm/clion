// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.parameterInfo;

import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCStructType;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public abstract class OCFunctionCallPlace<T extends PsiElement>
{
    @NotNull
    private final T myElement;
    private static final Pattern LINE_BREAKS;
    
    public OCFunctionCallPlace(@NotNull final T myElement) {
        if (myElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace", "<init>"));
        }
        this.myElement = myElement;
    }
    
    protected static void collectInitializationOptions(@NotNull final OCType ocType, @NotNull final OCElement ocElement, @NotNull final Collection<OCFunctionCallOption> collection) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace", "collectInitializationOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "place", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace", "collectInitializationOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace", "collectInitializationOptions"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        Label_0170: {
            try {
                if (!ocElement.getContainingOCFile().getKind().isCpp()) {
                    return;
                }
                final OCType ocType2 = ocType;
                final boolean b = ocType2.isInstanceable();
                if (!b) {
                    return;
                }
                break Label_0170;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final OCType ocType2 = ocType;
                final boolean b = ocType2.isInstanceable();
                if (!b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                if (ocType instanceof OCStructType) {
                    collectConstructorCallOptions(ocType, (PsiElement)ocElement, collection);
                    return;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        if (!(ocType instanceof OCCppReferenceType)) {
            collection.add(new OCFunctionCallOption(new OCFunctionParameterInfo(new OCFunctionType(OCVoidType.instance(), Collections.emptyList()), null), -2));
        }
        collection.add(new OCFunctionCallOption(new OCFunctionParameterInfo(new OCFunctionType(OCVoidType.instance(), Collections.singletonList(ocType), Collections.singletonList("<unnamed>")), null), -1));
    }
    
    protected static void collectConstructorCallOptions(@NotNull final OCType ocType, @NotNull final PsiElement psiElement, @NotNull final Collection<OCFunctionCallOption> collection) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rt", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace", "collectConstructorCallOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "place", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace", "collectConstructorCallOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace", "collectConstructorCallOptions"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        Collection<OCSymbol> results;
        if (ocType instanceof OCStructType) {
            final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
            ((OCStructType)ocType).getSymbol().processConstructors((Processor<? super OCFunctionSymbol>)collectProcessor);
            results = (Collection<OCSymbol>)collectProcessor.getResults();
        }
        else {
            results = null;
        }
        collectCallOptions(collection, psiElement, results, null);
    }
    
    protected static void collectCallOptions(@NotNull final Collection<OCFunctionCallOption> p0, @NotNull final PsiElement p1, @Nullable final Collection<OCSymbol> p2, @Nullable final OCType p3) {
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
        //    18: ldc             "result"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "collectCallOptions"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "place"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "collectCallOptions"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnull          359
        //    92: aload_2        
        //    93: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //    98: astore          4
        //   100: aload           4
        //   102: invokeinterface java/util/Iterator.hasNext:()Z
        //   107: ifeq            359
        //   110: aload           4
        //   112: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   117: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   120: astore          5
        //   122: aload           5
        //   124: ifnonnull       134
        //   127: goto            100
        //   130: invokestatic    com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload_1        
        //   135: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //   137: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   140: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   143: astore          6
        //   145: aload           6
        //   147: ifnull          210
        //   150: aload           6
        //   152: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   157: astore          7
        //   159: aload           7
        //   161: ifnull          210
        //   164: aload           7
        //   166: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   171: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   174: if_acmpne       210
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   183: athrow         
        //   184: aload           5
        //   186: aload           7
        //   188: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   193: ifeq            210
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: goto            100
        //   206: invokestatic    com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: aload           5
        //   212: aload_1        
        //   213: aload_3        
        //   214: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.isVisible:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   217: ifne            227
        //   220: goto            100
        //   223: invokestatic    com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: aload           5
        //   229: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   234: aload_1        
        //   235: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   240: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   243: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   246: astore          7
        //   248: aload           7
        //   250: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   253: ifeq            356
        //   256: aload           7
        //   258: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   261: astore          8
        //   263: aconst_null    
        //   264: astore          9
        //   266: aload           5
        //   268: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   271: ifeq            320
        //   274: aload           8
        //   276: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.hasNoParameters:()Z
        //   279: ifne            320
        //   282: goto            289
        //   285: invokestatic    com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: aload           5
        //   291: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   294: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   297: dup            
        //   298: aload           6
        //   300: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   303: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   306: astore          10
        //   308: aload           10
        //   310: invokedynamic   fun:()Lcom/intellij/util/Function;
        //   315: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   318: astore          9
        //   320: new             Lcom/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionParameterInfo;
        //   323: dup            
        //   324: aload           8
        //   326: aload           9
        //   328: invokespecial   com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionParameterInfo.<init>:(Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;)V
        //   331: astore          10
        //   333: aload_0        
        //   334: new             Lcom/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallOption;
        //   337: dup            
        //   338: aload           10
        //   340: aload           5
        //   342: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //   347: invokespecial   com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallOption.<init>:(Lcom/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionParameterInfo;I)V
        //   350: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
        //   355: pop            
        //   356: goto            100
        //   359: return         
        //    Signature:
        //  (Ljava/util/Collection<Lcom/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallOption;>;Lcom/intellij/psi/PsiElement;Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  122    130    130    134    Ljava/lang/IllegalArgumentException;
        //  159    177    180    184    Ljava/lang/IllegalArgumentException;
        //  164    196    199    203    Ljava/lang/IllegalArgumentException;
        //  184    206    206    210    Ljava/lang/IllegalArgumentException;
        //  210    223    223    227    Ljava/lang/IllegalArgumentException;
        //  266    282    285    289    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0184:
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
    public T getElement() {
        PsiElement myElement;
        try {
            myElement = this.myElement;
            if (myElement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionCallPlace", "getElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (T)myElement;
    }
    
    public abstract void collectCallOptions(@NotNull final Collection<OCFunctionCallOption> p0);
    
    @NotNull
    public abstract List<OCExpression> getArgumentExpressions();
    
    static {
        LINE_BREAKS = Pattern.compile("\\s*\n+\\s*");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
