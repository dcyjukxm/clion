// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.symbols.expression.OCSizeofExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCReferenceExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCallExpressionSymbol;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.parser.OCElementType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;

public static class PointerTypeEvaluator extends CachingEvaluator<OCType>
{
    protected PointerTypeEvaluator(@NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCExpressionEvaluator$PointerTypeEvaluator", "<init>"));
        }
        super(ocResolveContext);
    }
    
    @Override
    public OCType evalDefault(final OCExpression ocExpression) {
        final OCType resolvedType = ocExpression.getResolvedType(this.myContext);
        try {
            if (resolvedType instanceof OCPointerType) {
                return resolvedType;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCType evalInteger(final Number n) {
        try {
            if (OCExpressionEvaluator.isNullCompatible(n)) {
                return OCUnknownType.INSTANCE;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Override
    public OCType evalBool(final Boolean b) {
        try {
            if (!b) {
                return OCUnknownType.INSTANCE;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Override
    public OCType evalBinary(final OCElementType ocElementType, final OCType ocType, final OCType ocType2) {
        return null;
    }
    
    @Override
    public OCType evalUnary(final OCElementType ocElementType, final OCType ocType) {
        return null;
    }
    
    @Override
    public OCType evalConditional(final OCType ocType, final Producer<OCType> producer, final Producer<OCType> producer2) {
        try {
            if (producer == OCUnknownType.INSTANCE) {
                return (OCType)producer2.produce();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (producer2 == OCUnknownType.INSTANCE) {
                return (OCType)producer.produce();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCType evalCast(final OCType ocType, final OCType ocType2) {
        return null;
    }
    
    @Nullable
    @Override
    public OCType evalCall(final OCCallExpressionSymbol ocCallExpressionSymbol) {
        return null;
    }
    
    @Nullable
    @Override
    public OCType evalReference(final OCReferenceExpressionSymbol ocReferenceExpressionSymbol) {
        return null;
    }
    
    @Nullable
    @Override
    public OCType evalSizeof(final OCSizeofExpressionSymbol ocSizeofExpressionSymbol) {
        return null;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
