// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.util.ArrayUtil;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.NonNls;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCQualifiedDesignator;

public class OCQualifiedDesignatorImpl extends OCElementWithReferenceBase implements OCQualifiedDesignator
{
    public OCQualifiedDesignatorImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    public OCQualifiedDesignatorImpl getQualifier() {
        return this.findChildByType(OCElementTypes.QUALIFIED_DESIGNATOR);
    }
    
    @Nullable
    public PsiElement getNameIdentifier() {
        return this.findNameStartTokenInCall();
    }
    
    @NotNull
    @Override
    public String getName() {
        String identifierName;
        try {
            identifierName = OCElementUtil.getIdentifierName(this.getNameIdentifier());
            if (identifierName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return identifierName;
    }
    
    @NotNull
    @Override
    public String getSymbolName() {
        String identifierName;
        try {
            identifierName = OCElementUtil.getIdentifierName(this.findReferenceTokenInCall());
            if (identifierName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl", "getSymbolName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return identifierName;
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    @Nullable
    @Override
    public OCExpression getArrayStartIndexer() {
        final ASTNode childByType = this.getNode().findChildByType(OCElementTypes.EXPRESSIONS);
        try {
            if (childByType != null) {
                return (OCExpression)childByType.getPsi();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public OCExpression getArrayStopIndexer() {
        final OCExpression arrayStartIndexer = this.getArrayStartIndexer();
        if (arrayStartIndexer != null) {
            final PsiElement nextSibling = arrayStartIndexer.getNextSibling();
            if (nextSibling != null) {
                final ASTNode childByType = this.getNode().findChildByType(OCElementTypes.EXPRESSIONS, nextSibling.getNode());
                try {
                    if (childByType != null) {
                        return (OCExpression)childByType.getPsi();
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return null;
            }
        }
        return null;
    }
    
    @Nullable
    public PsiElement resolve() {
        final OCSymbol resolveToSymbol = this.resolveToSymbol();
        try {
            if (resolveToSymbol != null) {
                return resolveToSymbol.locateDefinition();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCSymbol resolveToSymbol() {
        final OCType parentType = this.getParentType();
        try {
            if (!(parentType instanceof OCStructType) || this.getNameIdentifier() == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        ((OCStructType)parentType).processFields(this.getSymbolName(), (Processor<? super OCDeclaratorSymbol>)findFirstProcessor, new OCResolveContext((PsiElement)this.getContainingOCFile()));
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    @Nullable
    @Override
    public OCType getParentType() {
        final OCQualifiedDesignatorImpl qualifier = this.getQualifier();
        OCType ocType = null;
        if (qualifier != null) {
            final OCType parentType = qualifier.getParentType();
            Label_0129: {
                Label_0100: {
                    try {
                        if (!(parentType instanceof OCStructType) || qualifier.getNameIdentifier() == null) {
                            break Label_0100;
                        }
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                    ((OCStructType)parentType).processFields(qualifier.getSymbolName(), (Processor<? super OCDeclaratorSymbol>)findFirstProcessor, new OCResolveContext((PsiElement)this.getContainingOCFile()));
                    if (findFirstProcessor.isFound()) {
                        ocType = ((OCSymbol)findFirstProcessor.getFoundValue()).getType();
                    }
                    break Label_0129;
                    try {
                        if (!(parentType instanceof OCArrayType) || qualifier.getArrayStartIndexer() == null) {
                            break Label_0129;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                ocType = ((OCArrayType)parentType).getRefType();
            }
        }
        else {
            ocType = ((OCCompoundInitializer)PsiTreeUtil.getParentOfType((PsiElement)this, (Class)OCCompoundInitializer.class)).inferType();
        }
        try {
            if (ocType != null) {
                return ocType.resolve(this.getContainingFile());
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @Nullable
    @Override
    protected PsiReference createReference() {
        return (PsiReference)new OCReference() {
            public PsiElement getElement() {
                return (PsiElement)OCQualifiedDesignatorImpl.this;
            }
            
            public TextRange getRangeInElement() {
                return OCElementUtil.getRangeInParent(OCQualifiedDesignatorImpl.this.getNode().getLastChildNode());
            }
            
            public PsiElement resolve() {
                return OCQualifiedDesignatorImpl.this.resolve();
            }
            
            @NotNull
            public String getCanonicalText() {
                String symbolName;
                try {
                    symbolName = OCQualifiedDesignatorImpl.this.getSymbolName();
                    if (symbolName == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl$1", "getCanonicalText"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return symbolName;
            }
            
            public PsiElement handleElementRename(final String name) throws IncorrectOperationException {
                return OCQualifiedDesignatorImpl.this.setName(name);
            }
            
            @Override
            public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
                try {
                    if (ocSymbol == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl$1", "bindToSymbol"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return this.getElement();
            }
            
            public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
                try {
                    if (psiElement == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl$1", "bindToElement"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
                try {
                    if (symbol != null) {
                        return this.bindToSymbol(symbol);
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                return psiElement;
            }
            
            public boolean isReferenceTo(final PsiElement psiElement) {
                try {
                    if (!(psiElement instanceof OCSymbolDeclarator)) {
                        return false;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return Comparing.equal((Object)this.resolveToSymbol(), ((OCSymbolDeclarator)psiElement).getSymbol());
            }
            
            @NotNull
            public Object[] getVariants() {
                Object[] empty_OBJECT_ARRAY;
                try {
                    empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
                    if (empty_OBJECT_ARRAY == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl$1", "getVariants"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return empty_OBJECT_ARRAY;
            }
            
            public boolean isSoft() {
                return false;
            }
            
            public OCSymbol resolveToSymbol() {
                return OCQualifiedDesignatorImpl.this.resolveToSymbol();
            }
            
            private static IncorrectOperationException a(final IncorrectOperationException ex) {
                return ex;
            }
        };
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedDesignatorImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitQualifiedDesignator(this);
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
