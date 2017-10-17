// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCElement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;

public class OCTemplateParameterListImpl extends OCElementBase implements OCTemplateParameterList
{
    public OCTemplateParameterListImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCTemplateParameterListImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCTemplateParameterListImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitTemplateParameterList(this);
    }
    
    @NotNull
    @Override
    public List<OCElement> getParameters() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(TokenSet.create(new IElementType[] { OCElementTypes.PARAMETER_DECLARATION, OCElementTypes.CPP_TYPE_PARAMETER_DECLARATION }));
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCTemplateParameterListImpl", "getParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCElement>)childrenByType;
    }
    
    @NotNull
    @Override
    public List<OCParameterDeclaration> getParameterDeclarations() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.PARAMETER_DECLARATION);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCTemplateParameterListImpl", "getParameterDeclarations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCParameterDeclaration>)childrenByType;
    }
    
    @NotNull
    @Override
    public List<OCTypeParameterDeclaration> getTypeParameterDeclarations() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.CPP_TYPE_PARAMETER_DECLARATION);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCTemplateParameterListImpl", "getTypeParameterDeclarations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCTypeParameterDeclaration>)childrenByType;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
