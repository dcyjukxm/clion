// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCFinallySection;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCCatchSection;
import java.util.List;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCStatement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCTryStatement;

public class OCTryStatementImpl extends OCElementBase implements OCTryStatement
{
    public OCTryStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCTryStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCStatement getBody() {
        return this.findChildByType(OCElementTypes.STATEMENTS);
    }
    
    @NotNull
    @Override
    public List<OCCatchSection> getCatchSections() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.CATCH_SECTION);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCTryStatementImpl", "getCatchSections"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCCatchSection>)childrenByType;
    }
    
    @Override
    public OCFinallySection getFinallySection() {
        return this.findChildByType(OCElementTypes.FINALLY_SECTION);
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCTryStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitTryStatement(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
