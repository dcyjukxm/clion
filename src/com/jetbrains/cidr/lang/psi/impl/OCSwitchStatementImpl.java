// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;

public class OCSwitchStatementImpl extends OCElementBase implements OCSwitchStatement
{
    public OCSwitchStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCSwitchStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCDeclarationOrExpression getExpression() {
        return this.findChildByType(OCElementTypes.DECLARATION_OR_EXPRESSION);
    }
    
    @NotNull
    @Override
    public OCElementType getKeywordType() {
        OCElementType switch_KEYWORD;
        try {
            switch_KEYWORD = OCTokenTypes.SWITCH_KEYWORD;
            if (switch_KEYWORD == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSwitchStatementImpl", "getKeywordType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return switch_KEYWORD;
    }
    
    @Override
    public OCStatement getBody() {
        return this.findChildByType(OCElementTypes.STATEMENTS);
    }
    
    @NotNull
    @Override
    public PsiElement getSwitchToken() {
        PsiElement notNullChildByType;
        try {
            notNullChildByType = this.findNotNullChildByType(OCTokenTypes.SWITCH_KEYWORD);
            if (notNullChildByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSwitchStatementImpl", "getSwitchToken"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return notNullChildByType;
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
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCSwitchStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitSwitchStatement(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
