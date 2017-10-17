// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import java.util.List;
import java.util.Arrays;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.impl.OCBinaryExpressionImpl;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.parser.OCElementType;

public class OCBinaryExpressionSymbol extends OCExpressionSymbol
{
    private OCElementType myOperator;
    private OCExpressionSymbol myLeftOperand;
    private OCExpressionSymbol myRightOperand;
    
    public OCBinaryExpressionSymbol() {
    }
    
    public OCBinaryExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final OCElementType myOperator, @NotNull final OCExpressionSymbol myLeftOperand, @NotNull final OCExpressionSymbol myRightOperand) {
        if (myOperator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operator", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "<init>"));
        }
        if (myLeftOperand == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operand", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "<init>"));
        }
        if (myRightOperand == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operand1", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "<init>"));
        }
        super(project, virtualFile, n, s);
        this.myOperator = myOperator;
        this.myLeftOperand = myLeftOperand;
        this.myRightOperand = myRightOperand;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "deepEqualStep"));
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
        final OCBinaryExpressionSymbol ocBinaryExpressionSymbol = (OCBinaryExpressionSymbol)o;
        final OCBinaryExpressionSymbol ocBinaryExpressionSymbol2 = (OCBinaryExpressionSymbol)o2;
        try {
            if (ocBinaryExpressionSymbol.myOperator != ocBinaryExpressionSymbol2.myOperator) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocBinaryExpressionSymbol.myLeftOperand, (DeepEqual.Equality<Object>)ocBinaryExpressionSymbol2.myLeftOperand)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (!comparator.equalObjects(ocBinaryExpressionSymbol.myRightOperand, (DeepEqual.Equality<Object>)ocBinaryExpressionSymbol2.myRightOperand)) {
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
    public <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> cachingEvaluator) {
        try {
            if (cachingEvaluator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cachingEvaluator.evalBinary(this.myOperator, this.myLeftOperand.evaluate(cachingEvaluator), this.myRightOperand.evaluate(cachingEvaluator));
    }
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Info a = this.a(ocResolveContext);
        try {
            if (a.customReturnType != null) {
                return a.customReturnType;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        Label_0087: {
            try {
                if (a.leftType == null) {
                    return null;
                }
                final Info info = a;
                final OCType ocType = info.rightType;
                if (ocType != null) {
                    break Label_0087;
                }
                return null;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final Info info = a;
                final OCType ocType = info.rightType;
                if (ocType != null) {
                    return OCBinaryExpressionImpl.getBinaryExprType(this.myOperator, a.leftType, a.rightType, (PsiElement)ocResolveContext.getFile());
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return null;
    }
    
    @Nullable
    public OCType getCustomReturnType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "getCustomReturnType"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "getInfo"));
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
        OCType resolve = null;
        Info info = null;
        Label_0201: {
            try {
                if (resolvedType == null || resolvedType2 == null) {
                    break Label_0201;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            final OCFunctionSymbol resolveOperator = OCOperatorReference.resolveOperator(this.myOperator.getName(), OCOperatorReference.OperatorPlacement.INFIX, Arrays.asList(resolvedType, resolvedType2), Arrays.asList(this.myLeftOperand, this.myRightOperand), ocResolveContext);
            if (resolveOperator != null) {
                resolve = resolveOperator.getType().getReturnType().resolve(ocResolveContext);
            }
            try {
                info = new Info(resolvedType, resolvedType2, resolve);
                if (info == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "getInfo"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return info;
    }
    
    @NotNull
    public OCElementType getOperator() {
        OCElementType myOperator;
        try {
            myOperator = this.myOperator;
            if (myOperator == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "getOperator"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myOperator;
    }
    
    @NotNull
    public OCExpressionSymbol getLeftOperand() {
        OCExpressionSymbol myLeftOperand;
        try {
            myLeftOperand = this.myLeftOperand;
            if (myLeftOperand == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "getLeftOperand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myLeftOperand;
    }
    
    @NotNull
    public OCExpressionSymbol getRightOperand() {
        OCExpressionSymbol myRightOperand;
        try {
            myRightOperand = this.myRightOperand;
            if (myRightOperand == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCBinaryExpressionSymbol", "getRightOperand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myRightOperand;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class Info
    {
        @Nullable
        public final OCType leftType;
        @Nullable
        public final OCType rightType;
        @Nullable
        public final OCType customReturnType;
        
        private Info(@Nullable final OCType leftType, @Nullable final OCType rightType, @Nullable final OCType customReturnType) {
            this.leftType = leftType;
            this.rightType = rightType;
            this.customReturnType = customReturnType;
        }
    }
}
