// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;

public class OCCaseStatementImpl extends OCElementBase implements OCCaseStatement
{
    public OCCaseStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCaseStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCExpression getExpression() {
        try {
            if (this.isRange()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.findChildByType(OCElementTypes.EXPRESSIONS);
    }
    
    @Override
    public boolean isRange() {
        try {
            if (this.getNode().findChildByType((IElementType)OCTokenTypes.ELLIPSIS) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public boolean isDefault() {
        final ASTNode firstChildNode = this.getNode().getFirstChildNode();
        Label_0031: {
            try {
                if (firstChildNode == null) {
                    return false;
                }
                final ASTNode astNode = firstChildNode;
                final IElementType elementType = OCElementUtil.getElementType(astNode);
                final OCElementType ocElementType = OCTokenTypes.DEFAULT_KEYWORD;
                if (elementType == ocElementType) {
                    break Label_0031;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final ASTNode astNode = firstChildNode;
                final IElementType elementType = OCElementUtil.getElementType(astNode);
                final OCElementType ocElementType = OCTokenTypes.DEFAULT_KEYWORD;
                if (elementType == ocElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public OCExpression getRangeFirst() {
        final ASTNode childByType = this.getNode().findChildByType((IElementType)OCTokenTypes.ELLIPSIS);
        try {
            if (childByType == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ASTNode astNode = this.getNode().getFirstChildNode();
        while (true) {
            Label_0055: {
                Label_0049: {
                    try {
                        if (astNode == null) {
                            break;
                        }
                        final ASTNode astNode2 = astNode;
                        final ASTNode astNode3 = childByType;
                        if (astNode2 == astNode3) {
                            break Label_0049;
                        }
                        break Label_0055;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final ASTNode astNode2 = astNode;
                        final ASTNode astNode3 = childByType;
                        if (astNode2 == astNode3) {
                            return null;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    if (OCElementTypes.EXPRESSIONS.contains(astNode.getElementType())) {
                        return (OCExpression)astNode.getPsi();
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            astNode = astNode.getTreeNext();
        }
        return null;
    }
    
    @Override
    public OCExpression getRangeSecond() {
        final ASTNode childByType = this.getNode().findChildByType((IElementType)OCTokenTypes.ELLIPSIS);
        try {
            if (childByType == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        boolean b = false;
        ASTNode astNode = this.getNode().getFirstChildNode();
        while (true) {
            Label_0079: {
                Label_0053: {
                    try {
                        if (astNode == null) {
                            break;
                        }
                        if (astNode != childByType) {
                            break Label_0053;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    b = true;
                    try {
                        if (!b) {
                            break Label_0079;
                        }
                        final TokenSet set = OCElementTypes.EXPRESSIONS;
                        final ASTNode astNode2 = astNode;
                        final IElementType elementType = astNode2.getElementType();
                        final boolean b2 = set.contains(elementType);
                        if (b2) {
                            break Label_0079;
                        }
                        break Label_0079;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final TokenSet set = OCElementTypes.EXPRESSIONS;
                    final ASTNode astNode2 = astNode;
                    final IElementType elementType = astNode2.getElementType();
                    final boolean b2 = set.contains(elementType);
                    if (b2) {
                        return (OCExpression)astNode.getPsi();
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            astNode = astNode.getTreeNext();
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCStatement getStatement() {
        return this.findChildByType(OCElementTypes.STATEMENTS);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCaseStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitCaseStatement(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
