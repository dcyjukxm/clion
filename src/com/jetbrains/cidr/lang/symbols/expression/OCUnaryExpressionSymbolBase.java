// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.parser.OCElementType;

public abstract class OCUnaryExpressionSymbolBase extends OCExpressionSymbol
{
    protected OCElementType myOperator;
    protected OCExpressionSymbol myOperand;
    
    public OCUnaryExpressionSymbolBase() {
    }
    
    public OCUnaryExpressionSymbolBase(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final OCElementType myOperator, @NotNull final OCExpressionSymbol myOperand) {
        if (myOperator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operator", "com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbolBase", "<init>"));
        }
        if (myOperand == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operand", "com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbolBase", "<init>"));
        }
        super(project, virtualFile, n, s);
        this.myOperator = myOperator;
        this.myOperand = myOperand;
    }
    
    @NotNull
    public abstract OCOperatorReference.OperatorPlacement getOperatorPlacement();
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbolBase", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbolBase", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbolBase", "deepEqualStep"));
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
        final OCUnaryExpressionSymbolBase ocUnaryExpressionSymbolBase = (OCUnaryExpressionSymbolBase)o;
        final OCUnaryExpressionSymbolBase ocUnaryExpressionSymbolBase2 = (OCUnaryExpressionSymbolBase)o2;
        try {
            if (ocUnaryExpressionSymbolBase.myOperator != ocUnaryExpressionSymbolBase2.myOperator) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocUnaryExpressionSymbolBase.myOperand, (DeepEqual.Equality<Object>)ocUnaryExpressionSymbolBase2.myOperand)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return true;
    }
    
    @Nullable
    @Override
    public <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> cachingEvaluator) {
        try {
            if (cachingEvaluator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbolBase", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cachingEvaluator.evalUnary(this.myOperator, this.myOperand.evaluate(cachingEvaluator));
    }
    
    protected abstract OCType getResolvedType(final OCType p0, @NotNull final OCResolveContext p1);
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbolBase", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCType resolvedType = this.myOperand.getResolvedType(ocResolveContext);
        try {
            if (resolvedType instanceof OCTypeParameterType) {
                ocResolveContext.addTypeDependency(((OCTypeParameterType)resolvedType).getSymbol());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (resolvedType == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final OCFunctionSymbol resolveOperator = OCOperatorReference.resolveOperator(this.myOperator.getName(), this.getOperatorPlacement(), (List<OCType>)Collections.singletonList(resolvedType), Collections.singletonList(this.myOperand), ocResolveContext);
        try {
            if (resolveOperator != null) {
                return resolveOperator.getType().getReturnType().resolve(ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return this.getResolvedType(resolvedType, ocResolveContext);
    }
    
    @Nullable
    public OCType getCustomReturnType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbolBase", "getCustomReturnType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.a(ocResolveContext).customReturnType;
    }
    
    @NotNull
    private Info a(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbolBase", "getInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCType resolvedType = this.myOperand.getResolvedType(ocResolveContext);
        try {
            if (resolvedType instanceof OCTypeParameterType) {
                ocResolveContext.addTypeDependency(((OCTypeParameterType)resolvedType).getSymbol());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        OCType resolve = null;
        if (resolvedType != null) {
            final OCFunctionSymbol resolveOperator = OCOperatorReference.resolveOperator(this.myOperator.getName(), this.getOperatorPlacement(), (List<OCType>)Collections.singletonList(resolvedType), Collections.singletonList(this.myOperand), ocResolveContext);
            if (resolveOperator != null) {
                resolve = resolveOperator.getType().getReturnType().resolve(ocResolveContext);
            }
        }
        Info info;
        try {
            info = new Info(resolvedType, resolve);
            if (info == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCUnaryExpressionSymbolBase", "getInfo"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return info;
    }
    
    public OCElementType getOperator() {
        return this.myOperator;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class Info
    {
        @Nullable
        public final OCType opType;
        @Nullable
        public final OCType customReturnType;
        
        private Info(@Nullable final OCType opType, @Nullable final OCType customReturnType) {
            this.opType = opType;
            this.customReturnType = customReturnType;
        }
    }
}
