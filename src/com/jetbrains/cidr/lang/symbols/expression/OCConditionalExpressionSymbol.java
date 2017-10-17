// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.impl.OCConditionalExpressionImpl;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;

public class OCConditionalExpressionSymbol extends OCExpressionSymbol
{
    private OCExpressionSymbol myCondition;
    private OCExpressionSymbol myLeftOperand;
    private OCExpressionSymbol myRightOperand;
    
    public OCConditionalExpressionSymbol() {
    }
    
    public OCConditionalExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, final OCExpressionSymbol myCondition, final OCExpressionSymbol myLeftOperand, final OCExpressionSymbol myRightOperand) {
        super(project, virtualFile, n, s);
        this.myCondition = myCondition;
        this.myLeftOperand = myLeftOperand;
        this.myRightOperand = myRightOperand;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol", "deepEqualStep"));
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
        final OCConditionalExpressionSymbol ocConditionalExpressionSymbol = (OCConditionalExpressionSymbol)o;
        final OCConditionalExpressionSymbol ocConditionalExpressionSymbol2 = (OCConditionalExpressionSymbol)o2;
        try {
            if (!comparator.equalObjects(ocConditionalExpressionSymbol.myCondition, (DeepEqual.Equality<Object>)ocConditionalExpressionSymbol2.myCondition)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocConditionalExpressionSymbol.myLeftOperand, (DeepEqual.Equality<Object>)ocConditionalExpressionSymbol2.myLeftOperand)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (!comparator.equalObjects(ocConditionalExpressionSymbol.myRightOperand, (DeepEqual.Equality<Object>)ocConditionalExpressionSymbol2.myRightOperand)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        return true;
    }
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCType resolvedType = this.myLeftOperand.getResolvedType(ocResolveContext);
        final OCType resolvedType2 = this.myRightOperand.getResolvedType(ocResolveContext);
        try {
            if (resolvedType instanceof OCTypeParameterType) {
                ocResolveContext.addTypeDependency(((OCTypeParameterType)resolvedType).getSymbol());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (resolvedType2 instanceof OCTypeParameterType) {
                ocResolveContext.addTypeDependency(((OCTypeParameterType)resolvedType2).getSymbol());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        Label_0127: {
            try {
                if (resolvedType == null) {
                    return null;
                }
                final OCTypeParameterType ocTypeParameterType = (OCTypeParameterType)resolvedType2;
                if (ocTypeParameterType != null) {
                    break Label_0127;
                }
                return null;
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            try {
                final OCTypeParameterType ocTypeParameterType = (OCTypeParameterType)resolvedType2;
                if (ocTypeParameterType != null) {
                    return OCConditionalExpressionImpl.getConditionalExprType(resolvedType, resolvedType2, false, false, (PsiElement)ocResolveContext.getFile());
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> cachingEvaluator) {
        try {
            if (cachingEvaluator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cachingEvaluator.evalConditional(this.myCondition.evaluate(cachingEvaluator), (com.intellij.util.Producer<T>)(() -> {
            try {
                if (cachingEvaluator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol", "lambda$evaluate$0"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return this.myLeftOperand.evaluate((OCExpressionEvaluator.CachingEvaluator<Object>)cachingEvaluator);
        }), (com.intellij.util.Producer<T>)(() -> {
            try {
                if (cachingEvaluator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCConditionalExpressionSymbol", "lambda$evaluate$1"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return this.myRightOperand.evaluate((OCExpressionEvaluator.CachingEvaluator<Object>)cachingEvaluator);
        }));
    }
    
    public OCExpressionSymbol getLeftOperandOrCondition() {
        try {
            if (this.myLeftOperand == null) {
                return this.myCondition;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.myLeftOperand;
    }
    
    public OCExpressionSymbol getRightOperand() {
        return this.myRightOperand;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
