// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;

public class OCCommaExpressionSymbol extends OCExpressionSymbol
{
    private OCExpressionSymbol myHeadExpression;
    private OCExpressionSymbol myTailExpression;
    
    public OCCommaExpressionSymbol() {
    }
    
    public OCCommaExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final OCExpressionSymbol myHeadExpression, @NotNull final OCExpressionSymbol myTailExpression) {
        if (myHeadExpression == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headExpression", "com/jetbrains/cidr/lang/symbols/expression/OCCommaExpressionSymbol", "<init>"));
        }
        if (myTailExpression == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tailExpression", "com/jetbrains/cidr/lang/symbols/expression/OCCommaExpressionSymbol", "<init>"));
        }
        super(project, virtualFile, n, s);
        this.myHeadExpression = myHeadExpression;
        this.myTailExpression = myTailExpression;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCCommaExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCCommaExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCCommaExpressionSymbol", "deepEqualStep"));
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
        final OCCommaExpressionSymbol ocCommaExpressionSymbol = (OCCommaExpressionSymbol)o;
        final OCCommaExpressionSymbol ocCommaExpressionSymbol2 = (OCCommaExpressionSymbol)o2;
        try {
            if (!comparator.equalObjects(ocCommaExpressionSymbol.myHeadExpression, (DeepEqual.Equality<Object>)ocCommaExpressionSymbol2.myHeadExpression)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocCommaExpressionSymbol.myTailExpression, (DeepEqual.Equality<Object>)ocCommaExpressionSymbol2.myTailExpression)) {
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCCommaExpressionSymbol", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.myTailExpression.evaluate(cachingEvaluator);
    }
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCCommaExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (ocResolveContext.isInSFINAE()) {
            final OCType resolvedType = this.myHeadExpression.getResolvedType(ocResolveContext);
            Label_0079: {
                try {
                    if (resolvedType == null) {
                        break Label_0079;
                    }
                    final OCType ocType = resolvedType;
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final boolean b = ocType.isUnresolved(ocResolveContext2);
                    if (b) {
                        break Label_0079;
                    }
                    return this.myTailExpression.getResolvedType(ocResolveContext);
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    final OCType ocType = resolvedType;
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final boolean b = ocType.isUnresolved(ocResolveContext2);
                    if (b) {
                        return OCUnknownType.INSTANCE;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
        }
        return this.myTailExpression.getResolvedType(ocResolveContext);
    }
    
    @NotNull
    public OCExpressionSymbol getTailExpression() {
        OCExpressionSymbol myTailExpression;
        try {
            myTailExpression = this.myTailExpression;
            if (myTailExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCCommaExpressionSymbol", "getTailExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myTailExpression;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
