// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCStatement;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCIfStatement;

public class OCIfStatementImpl extends OCElementBase implements OCIfStatement
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCIfStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCIfStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCDeclarationOrExpression getCondition() {
        return this.findChildByType(OCElementTypes.DECLARATION_OR_EXPRESSION);
    }
    
    @Nullable
    @Override
    public OCStatement getThenBranch() {
        for (PsiElement psiElement = this.getFirstChild(); psiElement != null; psiElement = psiElement.getNextSibling()) {
            final ASTNode node = psiElement.getNode();
            Label_0033: {
                try {
                    if (OCIfStatementImpl.$assertionsDisabled) {
                        break Label_0033;
                    }
                    final ASTNode astNode = node;
                    if (astNode == null) {
                        break Label_0033;
                    }
                    break Label_0033;
                }
                catch (UnsupportedOperationException ex) {
                    throw b(ex);
                }
                try {
                    final ASTNode astNode = node;
                    if (astNode == null) {
                        throw new AssertionError();
                    }
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
            }
            final IElementType elementType = node.getElementType();
            try {
                if (elementType == OCTokenTypes.ELSE_KEYWORD) {
                    return null;
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
            try {
                if (psiElement instanceof OCStatement) {
                    return (OCStatement)psiElement;
                }
            }
            catch (UnsupportedOperationException ex4) {
                throw b(ex4);
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCStatement getElseBranch() {
        PsiElement psiElement = this.getFirstChild();
        boolean b = false;
        while (psiElement != null) {
            final ASTNode node = psiElement.getNode();
            Label_0035: {
                try {
                    if (OCIfStatementImpl.$assertionsDisabled) {
                        break Label_0035;
                    }
                    final ASTNode astNode = node;
                    if (astNode == null) {
                        break Label_0035;
                    }
                    break Label_0035;
                }
                catch (UnsupportedOperationException ex) {
                    throw b(ex);
                }
                try {
                    final ASTNode astNode = node;
                    if (astNode == null) {
                        throw new AssertionError();
                    }
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
            }
            if (node.getElementType() == OCTokenTypes.ELSE_KEYWORD) {
                b = true;
            }
            Label_0083: {
                try {
                    if (!b) {
                        break Label_0083;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final boolean b2 = psiElement2 instanceof OCStatement;
                    if (b2) {
                        break Label_0083;
                    }
                    break Label_0083;
                }
                catch (UnsupportedOperationException ex3) {
                    throw b(ex3);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final boolean b2 = psiElement2 instanceof OCStatement;
                    if (b2) {
                        return (OCStatement)psiElement;
                    }
                }
                catch (UnsupportedOperationException ex4) {
                    throw b(ex4);
                }
            }
            psiElement = psiElement.getNextSibling();
        }
        return null;
    }
    
    @Override
    public void setCondition(final OCDeclarationOrExpression ocDeclarationOrExpression) {
        throw new UnsupportedOperationException("setCondition is not implemented");
    }
    
    @Override
    public void setElse(final OCStatement ocStatement) {
        throw new UnsupportedOperationException("setElse is not implemented");
    }
    
    @Override
    public void setThen(final OCStatement ocStatement) {
        throw new UnsupportedOperationException("setThen is not implemented");
    }
    
    @Override
    public ASTNode getLParenth() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.LPAR);
    }
    
    @Override
    public ASTNode getRParenth() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.RPAR);
    }
    
    @Override
    public ASTNode getElseKeyword() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.ELSE_KEYWORD);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCIfStatementImpl", "accept"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        ocVisitor.visitIfStatement(this);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCIfStatementImpl.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
}
