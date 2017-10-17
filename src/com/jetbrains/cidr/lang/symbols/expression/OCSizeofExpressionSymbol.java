// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCType;

public class OCSizeofExpressionSymbol extends OCExpressionSymbol
{
    private OCExpressionSymbol myExpressionOperand;
    private OCType myTypeOperand;
    private boolean myVariadic;
    
    public OCSizeofExpressionSymbol() {
    }
    
    public OCSizeofExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final OCExpressionSymbol myExpressionOperand, final boolean myVariadic) {
        if (myExpressionOperand == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expressionOperand", "com/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol", "<init>"));
        }
        super(project, virtualFile, n, s);
        this.myExpressionOperand = myExpressionOperand;
        this.myVariadic = myVariadic;
    }
    
    public OCSizeofExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final OCType myTypeOperand, final boolean myVariadic) {
        if (myTypeOperand == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeOperand", "com/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol", "<init>"));
        }
        super(project, virtualFile, n, s);
        this.myTypeOperand = myTypeOperand;
        this.myVariadic = myVariadic;
    }
    
    @Nullable
    public OCExpressionSymbol getExpressionOperand() {
        return this.myExpressionOperand;
    }
    
    @Nullable
    public OCType getTypeOperand() {
        return this.myTypeOperand;
    }
    
    public boolean isVariadic() {
        return this.myVariadic;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol", "deepEqualStep"));
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
        final OCSizeofExpressionSymbol ocSizeofExpressionSymbol = (OCSizeofExpressionSymbol)o;
        final OCSizeofExpressionSymbol ocSizeofExpressionSymbol2 = (OCSizeofExpressionSymbol)o2;
        try {
            if (!comparator.equalObjects(ocSizeofExpressionSymbol.myExpressionOperand, (DeepEqual.Equality<Object>)ocSizeofExpressionSymbol2.myExpressionOperand)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocSizeofExpressionSymbol.myTypeOperand, (DeepEqual.Equality<Object>)ocSizeofExpressionSymbol2.myTypeOperand)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (ocSizeofExpressionSymbol.myVariadic != ocSizeofExpressionSymbol2.myVariadic) {
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cachingEvaluator.evalSizeof(this);
    }
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCSizeofExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return OCIntType.SIZE_T;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
