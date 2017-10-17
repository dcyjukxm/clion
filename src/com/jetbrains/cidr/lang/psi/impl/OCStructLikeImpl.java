// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCTypeArgumentList;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.openapi.util.Comparing;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.types.OCTypeBuilder;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.jetbrains.cidr.lang.symbols.OCBuilderDriver;
import com.intellij.psi.impl.source.tree.ASTStructure;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.symbols.OCDelegatingSymbol;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCLocalScopeable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.intellij.navigation.DelegatingItemPresentation;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentList;
import com.intellij.navigation.ItemPresentation;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCCppBaseClauseList;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCStructLike;

public abstract class OCStructLikeImpl extends OCElementBase implements OCStructLike
{
    public OCStructLikeImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCStructLikeImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public List<OCDeclaration> getFields() {
        return this.findChildrenByType(OCElementTypes.DECLARATION);
    }
    
    @Nullable
    @Override
    public OCCppBaseClauseList getBaseClausesList() {
        return this.findChildByType(OCElementTypes.CPP_BASE_CLAUSE_LIST);
    }
    
    public String getName() {
        final PsiElement nameIdentifier = this.getNameIdentifier();
        try {
            if (nameIdentifier != null) {
                return nameIdentifier.getText();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public OCVisibility getDefaultVisibility() {
        try {
            if (this.findChildByType(OCTokenTypes.CLASS_KEYWORD) != null) {
                return OCVisibility.PRIVATE;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return OCVisibility.PUBLIC;
    }
    
    @Nullable
    public PsiElement getNameIdentifier() {
        final PsiElement childByType = this.findChildByType(OCTokenTypes.IDENTIFIER);
        if (childByType == null) {
            final OCReferenceElement ocReferenceElement = this.findChildByType(OCElementTypes.REFERENCE_ELEMENT);
            try {
                if (ocReferenceElement != null) {
                    return ocReferenceElement.getLastChild();
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return null;
        }
        return childByType;
    }
    
    public OCCppNamespaceQualifier getNamespaceQualifier() {
        return this.findChildByType(OCElementTypes.CPP_NAMESPACE_QUALIFIER);
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCStructLikeImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    @Nullable
    public String getSymbolName() {
        return this.getName();
    }
    
    @Override
    public ItemPresentation getPresentation() {
        final ItemPresentation presentation = super.getPresentation();
        if (presentation != null) {
            final PsiElement psiElement = this.findChildByClass((Class<PsiElement>)OCTemplateArgumentList.class);
            try {
                if (psiElement != null) {
                    return (ItemPresentation)new DelegatingItemPresentation(presentation).withPresentableText(this.getName() + psiElement.getText());
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
        return presentation;
    }
    
    @Override
    public int getTextOffset() {
        return OCSymbolOffsetUtil.getTextOffset(this.getComplexOffset());
    }
    
    @Override
    public long getComplexOffset() {
        final PsiElement nameIdentifier = this.getNameIdentifier();
        try {
            if (nameIdentifier == null) {
                return super.getComplexOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return OCSymbolOffsetUtil.getComplexOffset(nameIdentifier);
    }
    
    @Override
    public boolean isDeclaration() {
        return OCElementUtil.isStructTypeDeclaration(this.getNode());
    }
    
    @NotNull
    @Override
    public TextRange getHeaderRange() {
        final PsiElement childByType = this.findChildByType(OCTokenTypes.LBRACE);
        Label_0085: {
            TextRange textRange3 = null;
            Label_0050: {
                try {
                    if (childByType == null) {
                        break Label_0085;
                    }
                    final OCStructLikeImpl ocStructLikeImpl = this;
                    final TextRange textRange = ocStructLikeImpl.getTextRange();
                    final int n = textRange.getStartOffset();
                    final PsiElement psiElement = childByType;
                    final int n2 = psiElement.getTextOffset();
                    final TextRange textRange2 = new TextRange(n, n2);
                    final OCStructLikeImpl ocStructLikeImpl2 = this;
                    final PsiFile psiFile = ocStructLikeImpl2.getContainingFile();
                    textRange3 = OCElementUtil.getTrimmedRange(textRange2, psiFile);
                    if (textRange3 == null) {
                        break Label_0050;
                    }
                    return textRange3;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final OCStructLikeImpl ocStructLikeImpl = this;
                    final TextRange textRange = ocStructLikeImpl.getTextRange();
                    final int n = textRange.getStartOffset();
                    final PsiElement psiElement = childByType;
                    final int n2 = psiElement.getTextOffset();
                    final TextRange textRange2 = new TextRange(n, n2);
                    final OCStructLikeImpl ocStructLikeImpl2 = this;
                    final PsiFile psiFile = ocStructLikeImpl2.getContainingFile();
                    textRange3 = OCElementUtil.getTrimmedRange(textRange2, psiFile);
                    if (textRange3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCStructLikeImpl", "getHeaderRange"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return textRange3;
        }
        final PsiElement nameIdentifier = this.getNameIdentifier();
        TextRange textRange4 = null;
        Label_0111: {
            try {
                if (nameIdentifier != null) {
                    final TextRange textRange5;
                    textRange4 = (textRange5 = nameIdentifier.getTextRange());
                    break Label_0111;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            TextRange textRange5;
            textRange4 = (textRange5 = this.getTextRange());
            try {
                if (textRange5 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCStructLikeImpl", "getHeaderRange"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return textRange4;
    }
    
    @Override
    public int getFunctionsStartOffset() {
        PsiElement psiElement = this.getFirstChild();
        PsiElement lastChild = null;
        while (true) {
            Label_0025: {
                Label_0074: {
                    Label_0048: {
                        Label_0030: {
                            try {
                                if (psiElement == null) {
                                    break;
                                }
                                if (!(psiElement instanceof OCFunctionDeclaration)) {
                                    break Label_0030;
                                }
                            }
                            catch (IncorrectOperationException ex) {
                                throw a(ex);
                            }
                            break Label_0025;
                            try {
                                if (lastChild != null) {
                                    break Label_0074;
                                }
                                final PsiElement psiElement2 = psiElement;
                                final boolean b = psiElement2 instanceof OCDeclaration;
                                if (!b) {
                                    break Label_0048;
                                }
                                break Label_0048;
                            }
                            catch (IncorrectOperationException ex2) {
                                throw a(ex2);
                            }
                        }
                        try {
                            final PsiElement psiElement2 = psiElement;
                            final boolean b = psiElement2 instanceof OCDeclaration;
                            if (!b) {
                                if (psiElement.getNode().getElementType() != OCTokenTypes.RBRACE) {
                                    break Label_0074;
                                }
                            }
                        }
                        catch (IncorrectOperationException ex3) {
                            throw a(ex3);
                        }
                    }
                    lastChild = psiElement;
                }
                psiElement = psiElement.getNextSibling();
                continue;
            }
            lastChild = psiElement;
            break;
        }
        if (lastChild == null) {
            lastChild = this.getLastChild();
        }
        return lastChild.getTextRange().getStartOffset();
    }
    
    protected void processSymbols(final Processor<OCSymbol> processor) {
        final OCFile containingOCFile = this.getContainingOCFile();
        final PsiElement contextOfType = PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCLocalScopeable.class });
        final OCSymbolWithQualifiedName globalContextFromLocal = OCSymbolReferenceResolver.getGlobalContextFromLocal((PsiElement)this);
        OCSymbolKind kind = null;
        PsiElement psiElement = null;
        OCSymbolWithQualifiedName ocSymbolWithQualifiedName = null;
        OCVisibility ocVisibility = null;
        Object o = null;
        Label_0050: {
            try {
                kind = this.getKind();
                psiElement = contextOfType;
                ocSymbolWithQualifiedName = globalContextFromLocal;
                ocVisibility = null;
                if (contextOfType != null) {
                    o = this;
                    break Label_0050;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            o = null;
        }
        final BuilderDriverBase.DeclarationContext declarationContext = new BuilderDriverBase.DeclarationContext<ASTNode>(kind, psiElement, ocSymbolWithQualifiedName, ocVisibility, (PsiElement)o, false);
        final PsiElement parent = this.getParent().getParent();
        if (parent instanceof OCDeclaration) {
            final OCTemplateParameterList templateParameterList = ((OCDeclaration)parent).getTemplateParameterList();
            try {
                if (templateParameterList != null) {
                    declarationContext.getTemplateParameters().add(templateParameterList.getNode());
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        final OCLanguageKind kind2 = containingOCFile.getKind();
        final Processor<OCSymbol> delegateProcessor = OCDelegatingSymbol.getDelegateProcessor(processor);
        new OCBuilderDriver<ASTNode>(containingOCFile, OCInclusionContext.empty(kind2, (PsiFile)containingOCFile), (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)new ASTStructure(this.getNode()), (BuilderDriverBase.NamedNodeStructure<Object>)OCBuilderDriver.AST_NAMED_NODE_STRUCTURE, delegateProcessor).processStruct(this.getNode(), new OCTypeBuilder(kind2, containingOCFile.getProject(), declarationContext), delegateProcessor, (BuilderDriverBase.DeclarationContext<ASTNode>)declarationContext);
    }
    
    public OCStructSymbol getLocalSymbol() {
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            final /* synthetic */ String val$symbolName = OCStructLikeImpl.this.getSymbolName();
            
            public boolean process(final OCSymbol ocSymbol) {
                return !(ocSymbol instanceof OCStructSymbol) || ocSymbol.getOffset() != OCStructLikeImpl.this.getTextOffset() || !Comparing.equal(this.val$symbolName, ocSymbol.getName()) || super.process((Object)ocSymbol);
            }
        };
        this.processSymbols((Processor<OCSymbol>)findFirstProcessor);
        return (OCStructSymbol)findFirstProcessor.getFoundValue();
    }
    
    public OCStructSymbol getSymbol() {
        final OCStructSymbol ocStructSymbol = this.getContainingOCFile().findSymbol(this, OCStructSymbol.class);
        try {
            if (ocStructSymbol != null) {
                return ocStructSymbol;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return this.getLocalSymbol();
    }
    
    @Nullable
    @Override
    public PsiElement getOpeningBrace() {
        return this.findChildByType(OCTokenTypes.LBRACE);
    }
    
    @Nullable
    @Override
    public PsiElement getClosingBrace() {
        return this.findChildByType(OCTokenTypes.RBRACE);
    }
    
    @Nullable
    public OCTypeArgumentList getTemplateArgumentList() {
        return this.findChildByType(OCElementTypes.TEMPLATE_ARGUMENT_LIST);
    }
    
    @NotNull
    public Collection<OCSymbol> resolveTemplateDeclarations() {
        Collection<OCSymbol> resolveTemplateDeclarations;
        try {
            resolveTemplateDeclarations = OCResolveUtil.resolveTemplateDeclarations(this);
            if (resolveTemplateDeclarations == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCStructLikeImpl", "resolveTemplateDeclarations"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return resolveTemplateDeclarations;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
