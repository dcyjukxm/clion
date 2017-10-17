// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCSynthesizePropertiesList;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;

public class OCImplementationImpl extends OCClassDeclarationBaseImpl<OCImplementationSymbol> implements OCImplementation
{
    public OCImplementationImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCImplementationImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    protected Class<? extends OCClassSymbol> getSymbolClass() {
        return OCImplementationSymbol.class;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCImplementationImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        ocVisitor.visitImplementation(this);
    }
    
    @NotNull
    @Override
    public List<OCSynthesizePropertiesList> getSynthesizePropertyLists() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.SYNTHESIZED_PROPERTIES_LIST);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCImplementationImpl", "getSynthesizePropertyLists"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return (List<OCSynthesizePropertiesList>)childrenByType;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
