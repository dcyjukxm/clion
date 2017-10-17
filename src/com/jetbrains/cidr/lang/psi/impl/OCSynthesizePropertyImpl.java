// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.Icon;
import com.intellij.navigation.ItemPresentation;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.jetbrains.cidr.lang.psi.OCPropertyAttributesList;
import com.jetbrains.cidr.lang.psi.OCSynthesizePropertiesList;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;

public class OCSynthesizePropertyImpl extends OCElementBase implements OCSynthesizeProperty, PsiNamedElement
{
    public OCSynthesizePropertyImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCSynthesizePropertyImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCSynthesizePropertyImpl", "accept"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        ocVisitor.visitSynthesizeProperty(this);
    }
    
    @Override
    public OCReferenceElement getPropertyRef() {
        return this.findChildByType(OCElementTypes.REFERENCE_ELEMENT);
    }
    
    @Override
    public OCReferenceElement getInstanceVariableRef() {
        final List<PsiElement> childrenByType = this.findChildrenByType(OCElementTypes.REFERENCE_ELEMENT);
        try {
            if (childrenByType.size() == 2) {
                return (OCReferenceElement)childrenByType.get(1);
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Override
    public boolean isSynthesize() {
        final OCSynthesizePropertiesList list = (OCSynthesizePropertiesList)this.getContext();
        Label_0028: {
            try {
                if (list == null) {
                    return false;
                }
                final OCSynthesizePropertiesList list2 = list;
                final boolean b = list2.isSynthesize();
                if (b) {
                    break Label_0028;
                }
                return false;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                final OCSynthesizePropertiesList list2 = list;
                final boolean b = list2.isSynthesize();
                if (b) {
                    return true;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Override
    public OCPropertyAttributesList getPropertyAttributesList() {
        return this.findChildByType(OCElementTypes.PROPERTY_ATTRIBUTES_LIST);
    }
    
    public OCSynthesizeSymbol getSymbol() {
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCClassDeclaration.class });
        final OCReferenceElement propertyRef = this.getPropertyRef();
        Label_0037: {
            try {
                if (ocClassDeclaration == null) {
                    break Label_0037;
                }
                final OCReferenceElement ocReferenceElement = propertyRef;
                if (ocReferenceElement == null) {
                    break Label_0037;
                }
                break Label_0037;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                final OCReferenceElement ocReferenceElement = propertyRef;
                if (ocReferenceElement == null) {
                    return null;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        final OCClassSymbol symbol = ocClassDeclaration.getSymbol();
        if (symbol != null) {
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            symbol.processMembers(propertyRef.getCanonicalText(), (Class<? extends OCMemberSymbol>)OCSynthesizeSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
            return (OCSynthesizeSymbol)findFirstProcessor.getFoundValue();
        }
        return null;
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "@synthesize";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSynthesizePropertyImpl", "getName"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return s;
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCSynthesizePropertyImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw b((RuntimeException)ex);
        }
        throw new UnsupportedOperationException("setName is not implemented");
    }
    
    @Override
    public ItemPresentation getPresentation() {
        return (ItemPresentation)new ItemPresentation() {
            public String getPresentableText() {
                return OCSynthesizePropertyImpl.this.getName();
            }
            
            public String getLocationString() {
                final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getContextOfType((PsiElement)OCSynthesizePropertyImpl.this, new Class[] { OCClassDeclaration.class });
                assert ocClassDeclaration != null;
                final ItemPresentation presentation = ocClassDeclaration.getPresentation();
                return (presentation != null) ? presentation.getPresentableText() : "";
            }
            
            public Icon getIcon(final boolean b) {
                return null;
            }
        };
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
