// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiReference;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCUnknownType;
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
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;

public class OCPrefixExpressionImpl extends OCExpressionWithReferenceBase<OCOperatorReference> implements OCPrefixExpression
{
    public OCPrefixExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCPrefixExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    protected OCOperatorReference createReference() {
        final String name = this.getOperationSign().getName();
        final OCExpression operand = this.getOperand();
        try {
            if (operand != null) {
                return new OCOperatorReference(this, name, OCOperatorReference.OperatorPlacement.PREFIX, this.getOperationSignNode().getPsi(), new OCExpression[] { operand });
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public OCExpression getOperand() {
        return this.findChildByType(OCElementTypes.EXPRESSIONS);
    }
    
    @NotNull
    @Override
    public OCElementType getOperationSign() {
        OCElementType ocElementType;
        try {
            ocElementType = (OCElementType)this.getOperationSignNode().getElementType();
            if (ocElementType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPrefixExpressionImpl", "getOperationSign"));
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
        ASTNode firstChildNode;
        try {
            firstChildNode = this.getNode().getFirstChildNode();
            if (firstChildNode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPrefixExpressionImpl", "getOperationSignNode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return firstChildNode;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCPrefixExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitPrefixExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCPrefixExpressionImpl", "getType"));
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
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPrefixExpressionImpl", "getType"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return ocType;
            }
        }
        final OCExpression operand = this.getOperand();
        OCType ocType2 = null;
        Label_0177: {
            try {
                if (operand != null) {
                    final OCType ocType3;
                    ocType2 = (ocType3 = operand.getType(ocResolveContext));
                    break Label_0177;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            OCType ocType3;
            ocType2 = (ocType3 = OCUnknownType.INSTANCE);
            try {
                if (ocType3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPrefixExpressionImpl", "getType"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return ocType2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
