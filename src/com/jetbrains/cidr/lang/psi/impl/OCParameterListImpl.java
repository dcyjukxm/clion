// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.editor.smartEnter.OCFixer;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.Iterator;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCParameterList;

public class OCParameterListImpl extends OCElementBase implements OCParameterList
{
    public OCParameterListImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCParameterListImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public List<OCParameterDeclaration> getParameterDeclarations() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.PARAMETER_DECLARATION);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCParameterListImpl", "getParameterDeclarations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCParameterDeclaration>)childrenByType;
    }
    
    @NotNull
    @Override
    public List<OCDeclarator> getParameters() {
        final ArrayList<OCDeclarator> list = new ArrayList<OCDeclarator>();
        final Iterator<OCParameterDeclaration> iterator = this.getParameterDeclarations().iterator();
        while (iterator.hasNext()) {
            final OCDeclarator declarator = iterator.next().getDeclarator();
            try {
                if (declarator == null) {
                    continue;
                }
                list.add(declarator);
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        ArrayList<OCDeclarator> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCParameterListImpl", "getParameters"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return list2;
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
    
    @Nullable
    @Override
    public PsiElement getLastElement() {
        final ASTNode lParenth = this.getLParenth();
        try {
            if (lParenth == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Object o = null;
        final Iterator<OCDeclarator> iterator = this.getParameters().iterator();
        while (iterator.hasNext()) {
            final PsiElement nextSibling = ((OCDeclarator)(o = iterator.next())).getParent().getNextSibling();
            if (OCElementUtil.getElementType(nextSibling) != OCTokenTypes.COMMA) {
                try {
                    if (!OCElementUtil.isMissingCommaError(nextSibling)) {
                        if (!OCElementUtil.isMissingRParError(nextSibling)) {
                            continue;
                        }
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final ASTNode nextEssentialLeaf = OCFixer.getNextEssentialLeaf(nextSibling.getNode());
                try {
                    if (OCElementUtil.getElementType(nextEssentialLeaf) == OCTokenTypes.SEMICOLON) {
                        final Object a = o;
                        return (PsiElement)a;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                final Object a = a(nextSibling);
                return (PsiElement)a;
            }
            o = nextSibling;
        }
        try {
            if (o != null) {
                return (PsiElement)o;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    @Nullable
    private static PsiElement a(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "maybeComma", "com/jetbrains/cidr/lang/psi/impl/OCParameterListImpl", "getPrevEssentialLeaf"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode prevEssentialLeaf = OCFixer.getPrevEssentialLeaf(psiElement.getNode());
        try {
            if (prevEssentialLeaf == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return prevEssentialLeaf.getPsi();
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCParameterListImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitParameterList(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
