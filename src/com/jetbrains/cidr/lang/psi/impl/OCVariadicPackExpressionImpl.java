// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCVariadicPackExpression;

public class OCVariadicPackExpressionImpl extends OCExpressionBase implements OCVariadicPackExpression
{
    public OCVariadicPackExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCVariadicPackExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCExpression getOperand() {
        OCExpression ocExpression;
        try {
            ocExpression = this.findNotNullChildByType(OCElementTypes.EXPRESSIONS);
            if (ocExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCVariadicPackExpressionImpl", "getOperand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocExpression;
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCVariadicPackExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCVariadicType ocVariadicType;
        try {
            ocVariadicType = new OCVariadicType(this.getOperand().getType(ocResolveContext));
            if (ocVariadicType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCVariadicPackExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocVariadicType;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCVariadicPackExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitVariadicPackExpression(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
