// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCUDLiteralExpression;

public class OCUDLiteralExpressionImpl extends OCUnaryExpressionImpl implements OCUDLiteralExpression
{
    public OCUDLiteralExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCUDLiteralExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCLiteralExpression getOperand() {
        return (OCLiteralExpression)this.getFirstChild();
    }
    
    @NotNull
    @Override
    public OCElementType getOperationSign() {
        OCElementType udl_SUFFIX;
        try {
            udl_SUFFIX = OCTokenTypes.UDL_SUFFIX;
            if (udl_SUFFIX == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUDLiteralExpressionImpl", "getOperationSign"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return udl_SUFFIX;
    }
    
    @NotNull
    @Override
    public ASTNode getOperationSignNode() {
        ASTNode node;
        try {
            node = this.getNameIdentifier().getNode();
            if (node == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUDLiteralExpressionImpl", "getOperationSignNode"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return node;
    }
    
    @NotNull
    @Override
    public String getOperationName() {
        String string;
        try {
            string = "\"\"" + this.getOperationSignNode().getText();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUDLiteralExpressionImpl", "getOperationName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @Override
    public boolean isGetAddress() {
        return false;
    }
    
    @NotNull
    public PsiElement getNameIdentifier() {
        PsiElement notNullChildByType;
        try {
            notNullChildByType = this.findNotNullChildByType(OCTokenTypes.UDL_SUFFIX);
            if (notNullChildByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUDLiteralExpressionImpl", "getNameIdentifier"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return notNullChildByType;
    }
    
    @NonNls
    @NotNull
    public String getName() {
        String text;
        try {
            text = this.getNameIdentifier().getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUDLiteralExpressionImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    @NotNull
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCUDLiteralExpressionImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCUDLiteralExpressionImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return (PsiElement)this;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
