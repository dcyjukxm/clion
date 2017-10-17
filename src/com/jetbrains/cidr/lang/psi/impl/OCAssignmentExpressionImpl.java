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
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;

public class OCAssignmentExpressionImpl extends OCExpressionWithReferenceBase<OCOperatorReference> implements OCAssignmentExpression
{
    private static final Logger LOG;
    
    public OCAssignmentExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCAssignmentExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    protected OCOperatorReference createReference() {
        final String name = this.getOperationSign().getName();
        final OCExpression receiverExpression = this.getReceiverExpression();
        final OCExpression sourceExpression = this.getSourceExpression();
        try {
            if (sourceExpression != null) {
                return new OCOperatorReference(this, name, OCOperatorReference.OperatorPlacement.INFIX, this.getOperationSignNode().getPsi(), new OCExpression[] { receiverExpression, sourceExpression });
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    @Override
    public OCExpression getReceiverExpression() {
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            OCExpression ocExpression2 = null;
            Label_0116: {
                Label_0086: {
                    OCExpression ocExpression = null;
                    Label_0051: {
                        try {
                            if (!OCTokenTypes.ASSIGNMENT_OPERATIONS.contains(elementType)) {
                                break Label_0086;
                            }
                            final Logger logger = OCAssignmentExpressionImpl.LOG;
                            final String s = "Cannot find receiver expression in assignment";
                            logger.error(s);
                            ocExpression = null;
                            if (ocExpression == null) {
                                break Label_0051;
                            }
                            return ocExpression;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final Logger logger = OCAssignmentExpressionImpl.LOG;
                            final String s = "Cannot find receiver expression in assignment";
                            logger.error(s);
                            ocExpression = null;
                            if (ocExpression == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCAssignmentExpressionImpl", "getReceiverExpression"));
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    return ocExpression;
                    try {
                        if (!OCElementTypes.EXPRESSIONS.contains(elementType)) {
                            continue;
                        }
                        final ASTNode astNode2 = astNode;
                        final PsiElement psiElement = astNode2.getPsi();
                        ocExpression2 = (OCExpression)psiElement;
                        if (ocExpression2 == null) {
                            break Label_0116;
                        }
                        return ocExpression2;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final ASTNode astNode2 = astNode;
                    final PsiElement psiElement = astNode2.getPsi();
                    ocExpression2 = (OCExpression)psiElement;
                    if (ocExpression2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCAssignmentExpressionImpl", "getReceiverExpression"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return ocExpression2;
        }
        OCExpression ocExpression3;
        try {
            OCAssignmentExpressionImpl.LOG.error("Cannot find receiver expression in assignment");
            ocExpression3 = null;
            if (ocExpression3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCAssignmentExpressionImpl", "getReceiverExpression"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return ocExpression3;
    }
    
    @Override
    public OCExpression getSourceExpression() {
        boolean b = false;
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            if (OCTokenTypes.ASSIGNMENT_OPERATIONS.contains(elementType)) {
                b = true;
            }
            else {
                Label_0059: {
                    try {
                        if (!b) {
                            continue;
                        }
                        final TokenSet set = OCElementTypes.EXPRESSIONS;
                        final IElementType elementType2 = elementType;
                        final boolean b2 = set.contains(elementType2);
                        if (b2) {
                            break Label_0059;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final TokenSet set = OCElementTypes.EXPRESSIONS;
                        final IElementType elementType2 = elementType;
                        final boolean b2 = set.contains(elementType2);
                        if (b2) {
                            return (OCExpression)astNode.getPsi();
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    public ASTNode getOperationSignNode() {
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            ASTNode astNode2 = null;
            Label_0043: {
                try {
                    if (!OCTokenTypes.ASSIGNMENT_OPERATIONS.contains(elementType)) {
                        continue;
                    }
                    astNode2 = astNode;
                    if (astNode2 == null) {
                        break Label_0043;
                    }
                    return astNode2;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    astNode2 = astNode;
                    if (astNode2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCAssignmentExpressionImpl", "getOperationSignNode"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return astNode2;
        }
        throw new AssertionError((Object)"Cannot find operation sign in assignment expression");
    }
    
    @NotNull
    @Override
    public OCElementType getOperationSign() {
        OCElementType ocElementType;
        try {
            ocElementType = (OCElementType)this.getOperationSignNode().getElementType();
            if (ocElementType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCAssignmentExpressionImpl", "getOperationSign"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocElementType;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCAssignmentExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitAssignmentExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCAssignmentExpressionImpl", "getType"));
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
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCAssignmentExpressionImpl", "getType"));
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
            type = this.getReceiverExpression().getType(ocResolveContext);
            if (type == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCAssignmentExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return type;
    }
    
    static {
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.psi.impl.OCAssignmentExpressionImpl");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
