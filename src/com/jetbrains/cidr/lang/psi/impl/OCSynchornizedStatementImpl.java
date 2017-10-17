// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCSynchronizedStatement;

public class OCSynchornizedStatementImpl extends OCElementBase implements OCSynchronizedStatement
{
    public OCSynchornizedStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCSynchornizedStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCExpression getLockExpression() {
        return this.findChildByType(OCElementTypes.EXPRESSIONS);
    }
    
    @NotNull
    @Override
    public OCElementType getKeywordType() {
        OCElementType synchronized_KEYWORD;
        try {
            synchronized_KEYWORD = OCTokenTypes.SYNCHRONIZED_KEYWORD;
            if (synchronized_KEYWORD == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSynchornizedStatementImpl", "getKeywordType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return synchronized_KEYWORD;
    }
    
    @Override
    public OCStatement getBody() {
        return this.findChildByType(OCElementTypes.STATEMENTS);
    }
    
    @Nullable
    @Override
    public PsiElement getExpression() {
        return (PsiElement)this.getLockExpression();
    }
    
    @Nullable
    @Override
    public ASTNode getLParenth() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.LPAR);
    }
    
    @Nullable
    @Override
    public ASTNode getRParenth() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.RPAR);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCSynchornizedStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitSynchronizedStatement(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
