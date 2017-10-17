// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import java.util.List;

public class OCQualifiedExpressionSymbol extends OCExpressionSymbol
{
    private OCExpressionSymbol myQualifier;
    private String myMemberName;
    private boolean isDeref;
    private List<OCTypeArgument> myTypeArguments;
    private OCExpressionSymbol myParent;
    
    public OCQualifiedExpressionSymbol() {
    }
    
    public OCQualifiedExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final OCExpressionSymbol myQualifier, @NotNull final String myMemberName, final boolean isDeref, @NotNull final List<OCTypeArgument> myTypeArguments) {
        if (myQualifier == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifier", "com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol", "<init>"));
        }
        if (myMemberName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberName", "com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol", "<init>"));
        }
        if (myTypeArguments == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeArguments", "com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol", "<init>"));
        }
        super(project, virtualFile, n, s);
        this.myQualifier = myQualifier;
        this.myMemberName = myMemberName;
        this.isDeref = isDeref;
        this.myTypeArguments = myTypeArguments;
    }
    
    public void setParent(final OCExpressionSymbol myParent) {
        this.myParent = myParent;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol", "deepEqualStep"));
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
        final OCQualifiedExpressionSymbol ocQualifiedExpressionSymbol = (OCQualifiedExpressionSymbol)o;
        final OCQualifiedExpressionSymbol ocQualifiedExpressionSymbol2 = (OCQualifiedExpressionSymbol)o2;
        try {
            if (!comparator.equalObjects(ocQualifiedExpressionSymbol.myQualifier, (DeepEqual.Equality<Object>)ocQualifiedExpressionSymbol2.myQualifier)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocQualifiedExpressionSymbol.myMemberName, ocQualifiedExpressionSymbol2.myMemberName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (!comparator.equalObjects(ocQualifiedExpressionSymbol.myTypeArguments, ocQualifiedExpressionSymbol2.myTypeArguments)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        try {
            if (!comparator.equalObjects(ocQualifiedExpressionSymbol.isDeref, ocQualifiedExpressionSymbol2.isDeref)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw b(ex8);
        }
        try {
            if (!comparator.equalObjects(ocQualifiedExpressionSymbol.myParent, (DeepEqual.Equality<Object>)ocQualifiedExpressionSymbol2.myParent)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw b(ex9);
        }
        return true;
    }
    
    @Nullable
    @Override
    public <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> cachingEvaluator) {
        try {
            if (cachingEvaluator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol", "evaluate"));
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
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getResolvedType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.myQualifier:Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    48: aload_1        
        //    49: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    52: astore_2       
        //    53: aload_1        
        //    54: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getFile:()Lcom/intellij/psi/PsiFile;
        //    57: astore_3       
        //    58: aload_2        
        //    59: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    62: ifeq            73
        //    65: aload_2        
        //    66: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    69: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    72: astore_2       
        //    73: aload_0        
        //    74: getfield        com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.isDeref:Z
        //    77: aload_2        
        //    78: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    81: if_icmpeq       92
        //    84: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //    87: areturn        
        //    88: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_2        
        //    93: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    96: ifeq            107
        //    99: aload_2        
        //   100: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   103: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   106: astore_2       
        //   107: aload_2        
        //   108: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   111: ifeq            328
        //   114: aload_2        
        //   115: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   118: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   121: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   124: if_acmpeq       328
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: aload_0        
        //   135: getfield        com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.myMemberName:Ljava/lang/String;
        //   138: ldc             "~"
        //   140: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   143: ifeq            197
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_2        
        //   154: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   157: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   160: invokedynamic   process:()Lcom/intellij/util/Processor;
        //   165: iconst_0       
        //   166: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processDestructors:(Lcom/intellij/util/Processor;Z)Z
        //   169: ifeq            197
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: new             Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   182: dup            
        //   183: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   186: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   189: invokespecial   com/jetbrains/cidr/lang/types/OCFunctionType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;)V
        //   192: areturn        
        //   193: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: aload_2        
        //   198: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   201: aload_0        
        //   202: getfield        com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.myMemberName:Ljava/lang/String;
        //   205: aload_0        
        //   206: getfield        com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.myTypeArguments:Ljava/util/List;
        //   209: aload_1        
        //   210: iconst_1       
        //   211: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getResolvedMembers:(Lcom/jetbrains/cidr/lang/types/OCStructType;Ljava/lang/String;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Ljava/util/List;
        //   214: astore          4
        //   216: aload_0        
        //   217: getfield        com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.myParent:Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   220: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol;
        //   223: ifeq            296
        //   226: aload_3        
        //   227: ifnull          296
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   236: athrow         
        //   237: aload_3        
        //   238: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   241: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   246: ifeq            296
        //   249: goto            256
        //   252: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   255: athrow         
        //   256: aload_0        
        //   257: getfield        com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.myParent:Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   260: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol;
        //   263: aload           4
        //   265: aload_1        
        //   266: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol.resolveOverloads:(Ljava/util/Collection;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   269: astore          5
        //   271: aload           5
        //   273: ifnull          294
        //   276: aload           5
        //   278: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   283: aload_1        
        //   284: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   287: goto            295
        //   290: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   293: athrow         
        //   294: aconst_null    
        //   295: areturn        
        //   296: aload           4
        //   298: invokeinterface java/util/List.isEmpty:()Z
        //   303: ifne            328
        //   306: aload           4
        //   308: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   311: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   314: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   319: aload_1        
        //   320: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   323: areturn        
        //   324: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   327: athrow         
        //   328: aload_2        
        //   329: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   332: ifne            349
        //   335: aload_2        
        //   336: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   339: ifeq            361
        //   342: goto            349
        //   345: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   348: athrow         
        //   349: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   352: dup            
        //   353: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:()V
        //   356: areturn        
        //   357: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   360: athrow         
        //   361: aconst_null    
        //   362: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  73     88     88     92     Ljava/lang/IllegalArgumentException;
        //  107    127    130    134    Ljava/lang/IllegalArgumentException;
        //  114    146    149    153    Ljava/lang/IllegalArgumentException;
        //  134    172    175    179    Ljava/lang/IllegalArgumentException;
        //  153    193    193    197    Ljava/lang/IllegalArgumentException;
        //  216    230    233    237    Ljava/lang/IllegalArgumentException;
        //  226    249    252    256    Ljava/lang/IllegalArgumentException;
        //  271    290    290    294    Ljava/lang/IllegalArgumentException;
        //  296    324    324    328    Ljava/lang/IllegalArgumentException;
        //  328    342    345    349    Ljava/lang/IllegalArgumentException;
        //  335    357    357    361    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0134:
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
    public OCExpressionSymbol getQualifier() {
        OCExpressionSymbol myQualifier;
        try {
            myQualifier = this.myQualifier;
            if (myQualifier == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol", "getQualifier"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myQualifier;
    }
    
    public boolean isDeref() {
        return this.isDeref;
    }
    
    @NotNull
    public OCExpressionSymbol getParent() {
        OCExpressionSymbol myParent;
        try {
            myParent = this.myParent;
            if (myParent == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCQualifiedExpressionSymbol", "getParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myParent;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
