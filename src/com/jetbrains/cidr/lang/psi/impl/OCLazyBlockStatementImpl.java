// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import java.util.Arrays;
import com.jetbrains.cidr.lang.psi.OCStatement;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;

public class OCLazyBlockStatementImpl extends OCLazyElementBase implements OCBlockStatement
{
    public OCLazyBlockStatementImpl(@NotNull final IElementType elementType, final CharSequence charSequence) {
        if (elementType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/psi/impl/OCLazyBlockStatementImpl", "<init>"));
        }
        super(elementType, charSequence);
    }
    
    @NotNull
    @Override
    public List<OCStatement> getStatements() {
        List<OCStatement> list;
        try {
            list = Arrays.asList((OCStatement[])this.findChildrenByClass((Class<T>)OCStatement.class));
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLazyBlockStatementImpl", "getStatements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return list;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCLazyBlockStatementImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        ocVisitor.visitBlockStatement(this);
    }
    
    @Override
    public PsiElement getOpeningBrace() {
        return this.findPsiChildByType(OCTokenTypes.LBRACE);
    }
    
    @Nullable
    @Override
    public PsiElement getClosingBrace() {
        return this.findPsiChildByType(OCTokenTypes.RBRACE);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
