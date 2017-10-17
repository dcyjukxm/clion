// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;

static class ChainOperations
{
    private static TokenSet ADDITIVE;
    private static TokenSet MULTIPLICATIVE;
    private static TokenSet BINARY_SHIFT;
    private static TokenSet LOGICAL;
    private static TokenSet QUALIFYING;
    
    private static boolean a(@Nullable final ASTNode astNode, @Nullable final ASTNode astNode2) {
        if (astNode == null || astNode2 == null) {
            return false;
        }
        final PsiElement psi = astNode.getPsi();
        final PsiElement psi2 = astNode2.getPsi();
        if (psi == null || psi2 == null || !(psi instanceof OCBinaryExpression) || !(psi2 instanceof OCBinaryExpression)) {
            return false;
        }
        final OCElementType operationSign = ((OCBinaryExpression)psi).getOperationSign();
        final OCElementType operationSign2 = ((OCBinaryExpression)psi2).getOperationSign();
        return operationSign == operationSign2 || (ChainOperations.ADDITIVE.contains((IElementType)operationSign) && ChainOperations.ADDITIVE.contains((IElementType)operationSign2)) || (ChainOperations.MULTIPLICATIVE.contains((IElementType)operationSign) && ChainOperations.MULTIPLICATIVE.contains((IElementType)operationSign2)) || (ChainOperations.BINARY_SHIFT.contains((IElementType)operationSign) && ChainOperations.BINARY_SHIFT.contains((IElementType)operationSign2)) || (ChainOperations.LOGICAL.contains((IElementType)operationSign) && ChainOperations.LOGICAL.contains((IElementType)operationSign2)) || (ChainOperations.QUALIFYING.contains((IElementType)operationSign) && ChainOperations.QUALIFYING.contains((IElementType)operationSign2));
    }
    
    static {
        ChainOperations.ADDITIVE = TokenSet.create(new IElementType[] { OCTokenTypes.PLUS, OCTokenTypes.MINUS });
        ChainOperations.MULTIPLICATIVE = TokenSet.create(new IElementType[] { OCTokenTypes.MUL, OCTokenTypes.DIV, OCTokenTypes.PERC });
        ChainOperations.BINARY_SHIFT = TokenSet.create(new IElementType[] { OCTokenTypes.LTLT, OCTokenTypes.GTGT });
        ChainOperations.LOGICAL = TokenSet.create(new IElementType[] { OCTokenTypes.LT, OCTokenTypes.GT, OCTokenTypes.LTEQ, OCTokenTypes.GTEQ, OCTokenTypes.EQEQ, OCTokenTypes.EXCLEQ });
        ChainOperations.QUALIFYING = TokenSet.create(new IElementType[] { OCTokenTypes.DOT_MUL, OCTokenTypes.DEREF_MUL });
    }
}
