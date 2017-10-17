// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.symbols.expression.OCSizeofExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCReferenceExpressionSymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCallExpressionSymbol;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;

class OCTypeSubstitution$3 extends OCExpressionEvaluator.CachingEvaluator<Boolean> {
    final /* synthetic */ OCResolveContext val$context;
    
    @Nullable
    @Override
    public Boolean evalInteger(final Number n) {
        return false;
    }
    
    @Nullable
    @Override
    public Boolean evalBool(final Boolean b) {
        return false;
    }
    
    @Nullable
    @Override
    public Boolean evalDefault(final OCExpression ocExpression) {
        return false;
    }
    
    @Nullable
    @Override
    public Boolean evalBinary(final OCElementType ocElementType, @Nullable final Boolean b, @Nullable final Boolean b2) {
        return b == Boolean.TRUE || b2 == Boolean.TRUE;
    }
    
    @Nullable
    @Override
    public Boolean evalUnary(final OCElementType ocElementType, @Nullable final Boolean b) {
        return b;
    }
    
    @Nullable
    @Override
    public Boolean evalConditional(final Boolean b, @Nullable final Producer<Boolean> producer, @Nullable final Producer<Boolean> producer2) {
        return b == Boolean.TRUE || producer.produce() == Boolean.TRUE || producer2.produce() == Boolean.TRUE;
    }
    
    @Nullable
    @Override
    public Boolean evalCast(final OCType ocType, final Boolean b) {
        return b == Boolean.TRUE || OCTypeSubstitution.this.dependsOn(ocType, this.val$context);
    }
    
    @Nullable
    @Override
    public Boolean evalCall(final OCCallExpressionSymbol ocCallExpressionSymbol) {
        if (ocCallExpressionSymbol.getCalleeSymbol().evaluate((OCExpressionEvaluator.CachingEvaluator<Object>)this) == Boolean.TRUE) {
            return true;
        }
        final Iterator<OCExpressionSymbol> iterator = ocCallExpressionSymbol.getArguments().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().evaluate((OCExpressionEvaluator.CachingEvaluator<Object>)this) == Boolean.TRUE) {
                return true;
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public Boolean evalReference(final OCReferenceExpressionSymbol ocReferenceExpressionSymbol) {
        return OCTypeSubstitution.this.dependsOn(ocReferenceExpressionSymbol.getReference(), this.val$context);
    }
    
    @Nullable
    @Override
    public Boolean evalSizeof(final OCSizeofExpressionSymbol ocSizeofExpressionSymbol) {
        final OCExpressionSymbol expressionOperand = ocSizeofExpressionSymbol.getExpressionOperand();
        return (expressionOperand != null && expressionOperand.evaluate((OCExpressionEvaluator.CachingEvaluator<Object>)this) == Boolean.TRUE) || OCTypeSubstitution.this.dependsOn(ocSizeofExpressionSymbol.getTypeOperand(), this.val$context);
    }
}