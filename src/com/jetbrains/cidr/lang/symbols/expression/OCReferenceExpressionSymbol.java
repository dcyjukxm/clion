// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.psi.impl.OCReferenceExpressionImpl;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterValueSymbol;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;

public class OCReferenceExpressionSymbol extends OCExpressionSymbol
{
    private OCSymbolReference myReference;
    private OCExpressionSymbol myParent;
    
    public OCReferenceExpressionSymbol() {
    }
    
    public OCReferenceExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, final OCSymbolReference myReference) {
        super(project, virtualFile, n, s);
        this.myReference = myReference;
    }
    
    public void setParent(final OCExpressionSymbol myParent) {
        this.myParent = myParent;
    }
    
    public OCSymbolReference getReference() {
        return this.myReference;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol", "deepEqualStep"));
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
        final OCReferenceExpressionSymbol ocReferenceExpressionSymbol = (OCReferenceExpressionSymbol)o;
        final OCReferenceExpressionSymbol ocReferenceExpressionSymbol2 = (OCReferenceExpressionSymbol)o2;
        try {
            if (!comparator.equalObjects((DeepEqual.Equality<Object>)ocReferenceExpressionSymbol.myReference, (DeepEqual.Equality<Object>)ocReferenceExpressionSymbol2.myReference)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocReferenceExpressionSymbol.myParent, (DeepEqual.Equality<Object>)ocReferenceExpressionSymbol2.myParent)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return true;
    }
    
    @Nullable
    public OCSymbol resolveToSymbol(@NotNull final OCResolveContext p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "resolveToSymbol"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.myReference:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //    49: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)Ljava/util/List;
        //    52: astore_2       
        //    53: aload_1        
        //    54: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getFile:()Lcom/intellij/psi/PsiFile;
        //    57: astore_3       
        //    58: aload_0        
        //    59: getfield        com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.myParent:Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    62: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol;
        //    65: ifeq            115
        //    68: aload_3        
        //    69: ifnull          115
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_3        
        //    80: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    83: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    88: ifeq            115
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: aload_0        
        //    99: getfield        com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.myParent:Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   102: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol;
        //   105: aload_2        
        //   106: aload_1        
        //   107: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol.resolveOverloads:(Ljava/util/Collection;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   110: areturn        
        //   111: invokestatic    com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload_2        
        //   116: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.findPredeclaration:(Ljava/util/Collection;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   119: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  58     72     75     79     Ljava/lang/IllegalArgumentException;
        //  68     91     94     98     Ljava/lang/IllegalArgumentException;
        //  79     111    111    115    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0079:
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
    @Override
    public <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> cachingEvaluator) {
        try {
            if (cachingEvaluator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cachingEvaluator.evalReference(this);
    }
    
    @Nullable
    @Override
    public OCTypeArgument evaluateToTypeArgument(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolver", "com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol", "evaluateToTypeArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCTypeArgument evaluateToTypeArgument = super.evaluateToTypeArgument(ocResolveContext);
        if (evaluateToTypeArgument == null) {
            final OCSymbol resolveToSymbol = this.resolveToSymbol(ocResolveContext);
            try {
                if (resolveToSymbol instanceof OCTypeParameterValueSymbol) {
                    return new OCTypeParameterType((OCTypeParameterSymbol)resolveToSymbol);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return evaluateToTypeArgument;
    }
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCReferenceExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCSymbol resolveToSymbol = this.resolveToSymbol(ocResolveContext);
        try {
            if (resolveToSymbol instanceof OCTypeParameterSymbol) {
                ocResolveContext.addTypeDependency((OCTypeParameterSymbol)resolveToSymbol);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (resolveToSymbol != null) {
                return OCReferenceExpressionImpl.getReferenceExpressionType(resolveToSymbol).resolve(ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return OCUnknownType.INSTANCE;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
