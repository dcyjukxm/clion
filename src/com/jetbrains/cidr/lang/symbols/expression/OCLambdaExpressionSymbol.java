// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import java.util.function.Function;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.types.visitors.OCTypeUnificationVisitor;
import java.util.Set;
import com.jetbrains.cidr.lang.types.visitors.OCSimpleTypeSubstitution;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.HashMap;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import java.util.Comparator;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCType;

public class OCLambdaExpressionSymbol extends OCExpressionSymbol
{
    private OCType myReturnType;
    private List<OCType> myParameterTypes;
    private OCFunctionSymbol myFunctionSymbol;
    private List<OCDeclaratorSymbol> myParameters;
    private List<OCDeclaratorSymbol> myLocalVarsAndParams;
    private List<OCExpressionSymbol> myReturnExpressions;
    private Map<OCType, OCType> myAutoParamsMapping;
    public static final java.util.Comparator<OCDeclaratorSymbol> variablesComparator;
    
    public OCLambdaExpressionSymbol() {
    }
    
    public OCLambdaExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final List<OCExpressionSymbol> myReturnExpressions, @NotNull final List<OCDeclaratorSymbol> myParameters, @NotNull final List<OCDeclaratorSymbol> myLocalVarsAndParams, @Nullable final List<OCType> myParameterTypes, @Nullable final OCType myReturnType) {
        if (myReturnExpressions == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "returnExpressions", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "<init>"));
        }
        if (myParameters == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "<init>"));
        }
        if (myLocalVarsAndParams == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "localVarsAndParams", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "<init>"));
        }
        super(project, virtualFile, n, s);
        this.myReturnExpressions = myReturnExpressions;
        this.myParameters = myParameters;
        this.myLocalVarsAndParams = myLocalVarsAndParams;
        this.myParameterTypes = myParameterTypes;
        this.myReturnType = myReturnType;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final OCLambdaExpressionSymbol ocLambdaExpressionSymbol = (OCLambdaExpressionSymbol)o;
        final OCLambdaExpressionSymbol ocLambdaExpressionSymbol2 = (OCLambdaExpressionSymbol)o2;
        try {
            if (!comparator.equalObjects(ocLambdaExpressionSymbol.myParameterTypes, ocLambdaExpressionSymbol2.myParameterTypes)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocLambdaExpressionSymbol.myParameters, ocLambdaExpressionSymbol2.myParameters)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (!comparator.equalObjects(ocLambdaExpressionSymbol.myLocalVarsAndParams, ocLambdaExpressionSymbol2.myLocalVarsAndParams)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        try {
            if (!comparator.equalObjects(ocLambdaExpressionSymbol.myReturnType, (DeepEqual.Equality<Object>)ocLambdaExpressionSymbol2.myReturnType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw b(ex8);
        }
        try {
            if (!comparator.equalObjects(ocLambdaExpressionSymbol.myReturnExpressions, ocLambdaExpressionSymbol2.myReturnExpressions)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw b(ex9);
        }
        try {
            if (!comparator.equalObjects(ocLambdaExpressionSymbol.myFunctionSymbol, (DeepEqual.Equality<Object>)ocLambdaExpressionSymbol2.myFunctionSymbol)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex10) {
            throw b(ex10);
        }
        return true;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind lambda;
        try {
            lambda = OCSymbolKind.LAMBDA;
            if (lambda == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return lambda;
    }
    
    public List<OCDeclaratorSymbol> getParameters() {
        return this.myParameters;
    }
    
    @NotNull
    public List<OCDeclaratorSymbol> getLocalVarsAndParams() {
        List<OCDeclaratorSymbol> myLocalVarsAndParams;
        try {
            myLocalVarsAndParams = this.myLocalVarsAndParams;
            if (myLocalVarsAndParams == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "getLocalVarsAndParams"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myLocalVarsAndParams;
    }
    
    public void setFunctionSymbol(@NotNull final OCFunctionSymbol myFunctionSymbol) {
        try {
            if (myFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "functionSymbol", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "setFunctionSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myFunctionSymbol = myFunctionSymbol;
    }
    
    @Nullable
    @Override
    public <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> cachingEvaluator) {
        try {
            if (cachingEvaluator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getResolvedType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myParameterTypes:Ljava/util/List;
        //    48: ifnull          207
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myParameterTypes:Ljava/util/List;
        //    55: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //    60: invokedynamic   test:()Ljava/util/function/Predicate;
        //    65: invokeinterface java/util/stream/Stream.anyMatch:(Ljava/util/function/Predicate;)Z
        //    70: ifeq            207
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aload_0        
        //    81: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myReturnExpressions:Ljava/util/List;
        //    84: invokeinterface java/util/List.isEmpty:()Z
        //    89: ifne            207
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.newTypesMap:()Ljava/util/Map;
        //   102: astore_2       
        //   103: aload_0        
        //   104: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myParameterTypes:Ljava/util/List;
        //   107: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   112: aload_2        
        //   113: invokedynamic   apply:(Ljava/util/Map;)Ljava/util/function/Function;
        //   118: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   123: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   126: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   131: checkcast       Ljava/util/List;
        //   134: astore_3       
        //   135: new             Lcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;
        //   138: dup            
        //   139: aload_0        
        //   140: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myProject:Lcom/intellij/openapi/project/Project;
        //   143: aload_0        
        //   144: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //   147: aload_0        
        //   148: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myComplexOffset:J
        //   151: aload_0        
        //   152: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myName:Ljava/lang/String;
        //   155: aload_0        
        //   156: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myReturnExpressions:Ljava/util/List;
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myParameters:Ljava/util/List;
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myLocalVarsAndParams:Ljava/util/List;
        //   167: aload_3        
        //   168: aload_0        
        //   169: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   172: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   175: astore          4
        //   177: aload           4
        //   179: aload_2        
        //   180: putfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myAutoParamsMapping:Ljava/util/Map;
        //   183: new             Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   186: dup            
        //   187: aload           4
        //   189: aconst_null    
        //   190: aconst_null    
        //   191: iconst_0       
        //   192: iconst_0       
        //   193: invokespecial   com/jetbrains/cidr/lang/types/OCAutoType.<init>:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;ZZ)V
        //   196: astore          5
        //   198: aload           5
        //   200: iconst_1       
        //   201: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.setNeedsAutoParamsResolving:(Z)V
        //   204: aload           5
        //   206: areturn        
        //   207: aload_0        
        //   208: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   211: astore_2       
        //   212: aload_2        
        //   213: ifnonnull       374
        //   216: aload_0        
        //   217: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myReturnExpressions:Ljava/util/List;
        //   220: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   225: astore_3       
        //   226: aload_3        
        //   227: invokeinterface java/util/Iterator.hasNext:()Z
        //   232: ifeq            366
        //   235: aload_3        
        //   236: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   241: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   244: astore          4
        //   246: aload_0        
        //   247: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myFunctionSymbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   250: ifnull          268
        //   253: aload_1        
        //   254: aload_0        
        //   255: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myFunctionSymbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   258: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.pushOuterFunction:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)V
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: aload           4
        //   270: aload_1        
        //   271: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   274: astore          5
        //   276: aload           5
        //   278: ifnull          292
        //   281: aload           5
        //   283: aload_0        
        //   284: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myProject:Lcom/intellij/openapi/project/Project;
        //   287: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.decayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   290: astore          5
        //   292: aload_0        
        //   293: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myFunctionSymbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   296: ifnull          310
        //   299: aload_1        
        //   300: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.popOuterFunction:()V
        //   303: goto            310
        //   306: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   309: athrow         
        //   310: aload_2        
        //   311: ifnonnull       320
        //   314: aload           5
        //   316: astore_2       
        //   317: goto            363
        //   320: aload           5
        //   322: ifnull          340
        //   325: aload           5
        //   327: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   330: ifne            363
        //   333: goto            340
        //   336: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   339: athrow         
        //   340: aload_2        
        //   341: aload           5
        //   343: aload_1        
        //   344: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   347: ifne            363
        //   350: goto            357
        //   353: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   356: athrow         
        //   357: aconst_null    
        //   358: areturn        
        //   359: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   362: athrow         
        //   363: goto            226
        //   366: aload_2        
        //   367: ifnonnull       374
        //   370: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   373: astore_2       
        //   374: aload_0        
        //   375: getfield        com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.myFunctionSymbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   378: ifnull          389
        //   381: aload_2        
        //   382: goto            413
        //   385: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   388: athrow         
        //   389: aload_1        
        //   390: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   393: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   396: dup            
        //   397: aload_2        
        //   398: aload_0        
        //   399: aload_1        
        //   400: invokespecial   com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.a:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   403: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;)V
        //   406: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   409: aload_1        
        //   410: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   413: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     73     76     80     Ljava/lang/IllegalArgumentException;
        //  51     92     95     99     Ljava/lang/IllegalArgumentException;
        //  246    261    264    268    Ljava/lang/IllegalArgumentException;
        //  292    303    306    310    Ljava/lang/IllegalArgumentException;
        //  320    333    336    340    Ljava/lang/IllegalArgumentException;
        //  325    350    353    357    Ljava/lang/IllegalArgumentException;
        //  340    359    359    363    Ljava/lang/IllegalArgumentException;
        //  374    385    385    389    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0340:
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
    
    private List<OCType> a(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "getResolvedParameterTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        return this.myParameterTypes.stream().map(ocType -> {
            try {
                if (ocResolveContext == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "lambda$getResolvedParameterTypes$1"));
                    throw ex2;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            return ocType.resolve(ocResolveContext);
        }).collect((Collector<? super Object, ?, List<OCType>>)Collectors.toList());
    }
    
    @Nullable
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext, @NotNull final OCArgumentsList<? extends OCTypeOwner> list) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return this.getResolvedType(ocResolveContext, list, false);
    }
    
    @Nullable
    public OCType getResolvedType(@NotNull OCResolveContext substitute, @NotNull final OCArgumentsList<? extends OCTypeOwner> list, final boolean b) {
        try {
            if (substitute == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        Label_0131: {
            Label_0115: {
                try {
                    if (this.myParameterTypes.size() == list.getCount()) {
                        break Label_0131;
                    }
                    final boolean b2 = b;
                    if (b2) {
                        break Label_0115;
                    }
                    return this.getResolvedType(substitute);
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                try {
                    final boolean b2 = b;
                    if (b2) {
                        return OCUnknownType.INSTANCE;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            return this.getResolvedType(substitute);
        }
        final HashMap<OCTypeParameterSymbol, OCTypeArgument> hashMap = new HashMap<OCTypeParameterSymbol, OCTypeArgument>();
        int i = 0;
        while (i < this.myParameterTypes.size()) {
            final OCType ocType2 = list.getTypes().get(i);
            OCTypeOwner ocTypeOwner = null;
            Label_0202: {
                try {
                    if (list.getExprs() != null) {
                        ocTypeOwner = (OCTypeOwner)list.getExprs().get(i);
                        break Label_0202;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                ocTypeOwner = null;
            }
            final OCTypeOwner ocTypeOwner2 = ocTypeOwner;
            Label_0264: {
                Label_0248: {
                    try {
                        if (OCSimpleTypeSubstitution.unify(this.myParameterTypes.get(i), ocType2, ocTypeOwner2, hashMap, null, true, false, substitute) != OCTypeUnificationVisitor.NOT_UNIFIED) {
                            break Label_0264;
                        }
                        final boolean b3 = b;
                        if (b3) {
                            break Label_0248;
                        }
                        return this.getResolvedType(substitute);
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                    try {
                        final boolean b3 = b;
                        if (b3) {
                            return OCUnknownType.INSTANCE;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                }
                return this.getResolvedType(substitute);
            }
            ++i;
            continue;
            return this.getResolvedType(substitute);
        }
        substitute = substitute.substitute(OCSimpleTypeSubstitution.create(hashMap), false, true);
        substitute.setAutoParamsTypeMapping(this.myAutoParamsMapping);
        return this.getResolvedType(substitute);
    }
    
    @NotNull
    public List<OCExpressionSymbol> getReturnExpressions() {
        List<OCExpressionSymbol> myReturnExpressions;
        try {
            myReturnExpressions = this.myReturnExpressions;
            if (myReturnExpressions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol", "getReturnExpressions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myReturnExpressions;
    }
    
    public OCFunctionSymbol getFunctionSymbol() {
        return this.myFunctionSymbol;
    }
    
    static {
        variablesComparator = java.util.Comparator.comparing((Function<? super OCDeclaratorSymbol, ? extends Comparable>)OCSymbolImpl::getName).thenComparingLong(OCSymbolBase::getComplexOffset);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
