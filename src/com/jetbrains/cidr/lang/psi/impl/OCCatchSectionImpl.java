// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCStatement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCatchSection;

public class OCCatchSectionImpl extends OCElementBase implements OCCatchSection
{
    public OCCatchSectionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCatchSectionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCBlockStatement getBody() {
        return this.findChildByType(OCElementTypes.BLOCK_STATEMENTS);
    }
    
    @Override
    public OCParameterList getParameters() {
        return this.findChildByType(OCElementTypes.PARAMETER_LIST);
    }
    
    @Override
    public boolean isCppStatement() {
        try {
            if (this.findChildByType(OCElementTypes.OBJC_KEYWORD) == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCatchSectionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitCatchSection(this);
    }
    
    @NotNull
    @Override
    public OCElementType getKeywordType() {
        OCElementType catch_KEYWORD;
        try {
            catch_KEYWORD = OCTokenTypes.CATCH_KEYWORD;
            if (catch_KEYWORD == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCatchSectionImpl", "getKeywordType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return catch_KEYWORD;
    }
    
    @Nullable
    @Override
    public PsiElement getExpression() {
        PsiElement lastElement = null;
        Label_0024: {
            try {
                if (this.getParameters() == null) {
                    lastElement = null;
                    break Label_0024;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            lastElement = this.getParameters().getLastElement();
        }
        PsiElement parent = lastElement;
        try {
            if (!(parent instanceof OCDeclarator) || !parent.getTextRange().isEmpty()) {
                return parent;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        parent = parent.getParent();
        return parent;
    }
    
    @Nullable
    @Override
    public ASTNode getRParenth() {
        try {
            if (this.getParameters() == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.getParameters().getRParenth();
    }
    
    @Nullable
    @Override
    public ASTNode getLParenth() {
        try {
            if (this.getParameters() == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.getParameters().getLParenth();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
