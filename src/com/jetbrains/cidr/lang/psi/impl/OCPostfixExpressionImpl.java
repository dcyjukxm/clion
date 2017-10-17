// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiReference;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;

public class OCPostfixExpressionImpl extends OCExpressionWithReferenceBase<OCOperatorReference> implements OCPostfixExpression
{
    public OCPostfixExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCPostfixExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    protected OCOperatorReference createReference() {
        return new OCOperatorReference(this, this.getOperationSign().getName(), OCOperatorReference.OperatorPlacement.POSTFIX, this.getOperationSignNode().getPsi(), new OCExpression[] { this.getOperand() });
    }
    
    @NotNull
    @Override
    public OCExpression getOperand() {
        OCExpression ocExpression;
        try {
            ocExpression = this.findChildByType(OCElementTypes.EXPRESSIONS);
            if (ocExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPostfixExpressionImpl", "getOperand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocExpression;
    }
    
    @NotNull
    @Override
    public OCElementType getOperationSign() {
        OCElementType ocElementType;
        try {
            ocElementType = (OCElementType)this.getOperationSignNode().getElementType();
            if (ocElementType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPostfixExpressionImpl", "getOperationSign"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocElementType;
    }
    
    @NotNull
    @Override
    public ASTNode getOperationSignNode() {
        ASTNode lastChildNode;
        try {
            lastChildNode = this.getNode().getLastChildNode();
            if (lastChildNode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPostfixExpressionImpl", "getOperationSignNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return lastChildNode;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCPostfixExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitPostfixExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCPostfixExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCOperatorReference ocOperatorReference = this.getReference();
        if (ocOperatorReference != null) {
            for (final OCSymbol ocSymbol : ocOperatorReference.resolveToSymbols(ocResolveContext)) {
                OCType ocType = null;
                Label_0113: {
                    try {
                        if (!(ocSymbol instanceof OCFunctionSymbol)) {
                            continue;
                        }
                        final OCSymbol ocSymbol2 = ocSymbol;
                        ocType = ocSymbol2.getEffectiveType();
                        if (ocType == null) {
                            break Label_0113;
                        }
                        return ocType;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCSymbol ocSymbol2 = ocSymbol;
                        ocType = ocSymbol2.getEffectiveType();
                        if (ocType == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPostfixExpressionImpl", "getType"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return ocType;
            }
        }
        OCType type;
        try {
            type = this.getOperand().getType(ocResolveContext);
            if (type == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPostfixExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return type;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
