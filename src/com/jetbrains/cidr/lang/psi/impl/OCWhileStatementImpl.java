// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCWhileStatement;

public class OCWhileStatementImpl extends OCElementBase implements OCWhileStatement
{
    public OCWhileStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCWhileStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCDeclarationOrExpression getCondition() {
        return this.findChildByType(OCElementTypes.DECLARATION_OR_EXPRESSION);
    }
    
    @Nullable
    public OCDeclarationOrExpression getExpression() {
        return this.getCondition();
    }
    
    @NotNull
    public OCElementType getKeywordType() {
        OCElementType while_KEYWORD;
        try {
            while_KEYWORD = OCTokenTypes.WHILE_KEYWORD;
            if (while_KEYWORD == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCWhileStatementImpl", "getKeywordType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return while_KEYWORD;
    }
    
    @Nullable
    public OCStatement getBody() {
        return this.findChildByType(OCElementTypes.STATEMENTS);
    }
    
    public ASTNode getLParenth() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.LPAR);
    }
    
    public ASTNode getRParenth() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.RPAR);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCWhileStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitWhileStatement(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
