// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCPropertyAttributesList;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCProperty;

public class OCPropertyImpl extends OCElementBase implements OCProperty
{
    public OCPropertyImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCPropertyImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCClassDeclaration getContainingClass() {
        OCClassDeclaration ocClassDeclaration;
        try {
            ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCClassDeclaration.class });
            if (ocClassDeclaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPropertyImpl", "getContainingClass"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocClassDeclaration;
    }
    
    @Nullable
    @Override
    public OCDeclaration getDeclaration() {
        final OCDeclaration ocDeclaration = this.findChildByType(OCElementTypes.DECLARATION);
        try {
            if (ocDeclaration != null) {
                return ocDeclaration;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.findChildByType(OCElementTypes.FUNCTION_DECLARATION);
    }
    
    @Override
    public OCPropertyAttributesList getPropertyAttributesList() {
        return this.findChildByType(OCElementTypes.PROPERTY_ATTRIBUTES_LIST);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCPropertyImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitProperty(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
