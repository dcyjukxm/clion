// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import java.util.HashMap;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.parser.OCElementType;
import java.util.Map;

public class OCComparisonUtils
{
    private static final Map<OCElementType, String> NEGATED_COMPARISONS;
    private static final Map<OCElementType, String> INVERSED_OPERATORS;
    
    public static boolean isComparison(@Nullable final OCExpression ocExpression) {
        return ocExpression instanceof OCBinaryExpression && OCTokenTypes.COMPARISON_OPERATIONS.contains((IElementType)((OCBinaryExpression)ocExpression).getOperationSign());
    }
    
    public static String getNegatedComparison(final OCElementType ocElementType) {
        return OCComparisonUtils.NEGATED_COMPARISONS.get(ocElementType);
    }
    
    public static String getInversedOperator(final OCElementType ocElementType) {
        return OCComparisonUtils.INVERSED_OPERATORS.get(ocElementType);
    }
    
    static {
        NEGATED_COMPARISONS = new HashMap<OCElementType, String>(6);
        INVERSED_OPERATORS = new HashMap<OCElementType, String>(2);
        OCComparisonUtils.NEGATED_COMPARISONS.put(OCTokenTypes.EQEQ, "!=");
        OCComparisonUtils.NEGATED_COMPARISONS.put(OCTokenTypes.EXCLEQ, "==");
        OCComparisonUtils.NEGATED_COMPARISONS.put(OCTokenTypes.GT, "<=");
        OCComparisonUtils.NEGATED_COMPARISONS.put(OCTokenTypes.LT, ">=");
        OCComparisonUtils.NEGATED_COMPARISONS.put(OCTokenTypes.GTEQ, "<");
        OCComparisonUtils.NEGATED_COMPARISONS.put(OCTokenTypes.LTEQ, ">");
        OCComparisonUtils.INVERSED_OPERATORS.put(OCTokenTypes.ANDAND, "||");
        OCComparisonUtils.INVERSED_OPERATORS.put(OCTokenTypes.OROR, "&&");
        OCComparisonUtils.INVERSED_OPERATORS.put(OCTokenTypes.AND, "|");
        OCComparisonUtils.INVERSED_OPERATORS.put(OCTokenTypes.OR, "&");
    }
}
