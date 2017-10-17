// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.symbols.expression.OCSizeofExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCReferenceExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCCallExpressionSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.Nullable;

public interface Evaluator<T>
{
    @Nullable
    T evalInteger(final Number p0);
    
    @Nullable
    T evalBool(final Boolean p0);
    
    @Nullable
    T evalDefault(final OCExpression p0);
    
    @Nullable
    T evalBinary(final OCElementType p0, @Nullable final T p1, @Nullable final T p2);
    
    @Nullable
    T evalUnary(final OCElementType p0, @Nullable final T p1);
    
    @Nullable
    T evalConditional(final T p0, final Producer<T> p1, final Producer<T> p2);
    
    @Nullable
    T evalCast(final OCType p0, final T p1);
    
    @Nullable
    T evalCall(final OCCallExpressionSymbol p0);
    
    @Nullable
    T evalReference(final OCReferenceExpressionSymbol p0);
    
    @Nullable
    T evalSizeof(final OCSizeofExpressionSymbol p0);
}
