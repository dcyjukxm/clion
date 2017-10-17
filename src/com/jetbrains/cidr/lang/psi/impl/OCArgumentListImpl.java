// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.Iterator;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCArgumentList;

public class OCArgumentListImpl extends OCElementBase implements OCArgumentList
{
    public OCArgumentListImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCArgumentListImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public List<OCExpression> getArguments() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.EXPRESSIONS);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArgumentListImpl", "getArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCExpression>)childrenByType;
    }
    
    @NotNull
    @Override
    public List<OCType> getArgumentTypes(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCArgumentListImpl", "getArgumentTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList<OCType> list = new ArrayList<OCType>();
        final Iterator<OCExpression> iterator = this.getArguments().iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getResolvedType(ocResolveContext));
        }
        ArrayList<OCType> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArgumentListImpl", "getArgumentTypes"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return list2;
    }
    
    @Nullable
    @Override
    public PsiElement getLeftPar() {
        return this.findChildByType(OCTokenTypes.LPAR);
    }
    
    @Nullable
    @Override
    public PsiElement getRightPar() {
        return this.findChildByType(OCTokenTypes.RPAR);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCArgumentListImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitArgumentList(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
