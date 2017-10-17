// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import java.util.Collections;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;

public class OCParameterDeclarationImpl extends OCDeclarationImpl implements OCParameterDeclaration
{
    public OCParameterDeclarationImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCParameterDeclarationImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public boolean isEllipsis() {
        try {
            if (this.findChildByType(OCTokenTypes.ELLIPSIS) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @Nullable
    @Override
    public OCTypeElement getTypeElement() {
        return this.findChildByType(OCElementTypes.TYPE_ELEMENT);
    }
    
    @Override
    public OCDeclarator getDeclarator() {
        return this.findChildByType(OCElementTypes.DECLARATOR);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCParameterDeclarationImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        ocVisitor.visitParameterDeclaration(this);
    }
    
    @NotNull
    @Override
    public List<OCDeclarator> getDeclarators() {
        final OCDeclarator declarator = this.getDeclarator();
        List<OCDeclarator> list = null;
        Label_0023: {
            try {
                if (declarator != null) {
                    final List<OCDeclarator> list2;
                    list = (list2 = Collections.singletonList(declarator));
                    break Label_0023;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            List<OCDeclarator> list2;
            list = (list2 = Collections.emptyList());
            try {
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCParameterDeclarationImpl", "getDeclarators"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return list;
    }
    
    @Override
    public boolean isTypedef() {
        return false;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
