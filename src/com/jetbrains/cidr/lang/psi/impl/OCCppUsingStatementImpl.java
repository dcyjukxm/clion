// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.Processor;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.jetbrains.cidr.lang.symbols.OCBuilderDriver;
import com.intellij.psi.impl.source.tree.ASTStructure;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCAliasUsingSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCLocalScopeable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;

public class OCCppUsingStatementImpl extends OCElementBase implements OCCppUsingStatement
{
    public OCCppUsingStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCppUsingStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public boolean isNamespaceUsing() {
        try {
            if (this.findChildByType(OCTokenTypes.NAMESPACE_CPP_KEYWORD) != null) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return false;
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
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCppUsingStatementImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitUsingStatement(this);
    }
    
    public OCSymbol getLocalSymbol() {
        final OCFile containingOCFile = this.getContainingOCFile();
        final PsiElement contextOfType = PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCLocalScopeable.class });
        final OCSymbolWithQualifiedName globalContextFromLocal = OCSymbolReferenceResolver.getGlobalContextFromLocal((PsiElement)this);
        OCSymbolKind ocSymbolKind = null;
        PsiElement psiElement = null;
        OCSymbolWithQualifiedName ocSymbolWithQualifiedName = null;
        OCVisibility ocVisibility = null;
        Object o = null;
        Label_0045: {
            try {
                ocSymbolKind = null;
                psiElement = contextOfType;
                ocSymbolWithQualifiedName = globalContextFromLocal;
                ocVisibility = null;
                if (contextOfType != null) {
                    o = this;
                    break Label_0045;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            o = null;
        }
        final BuilderDriverBase.DeclarationContext declarationContext = new BuilderDriverBase.DeclarationContext<ASTNode>(ocSymbolKind, psiElement, ocSymbolWithQualifiedName, ocVisibility, (PsiElement)o, false);
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return ocSymbol instanceof OCUsingSymbol || ocSymbol instanceof OCAliasUsingSymbol;
            }
        };
        new OCBuilderDriver<ASTNode>(containingOCFile, OCInclusionContext.empty(containingOCFile.getKind(), (PsiFile)containingOCFile), (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)new ASTStructure(this.getNode()), (BuilderDriverBase.NamedNodeStructure<Object>)OCBuilderDriver.AST_NAMED_NODE_STRUCTURE, (Processor<OCSymbol>)findFirstProcessor).processUsingStatement(this.getNode(), (Processor<OCSymbol>)findFirstProcessor, (BuilderDriverBase.DeclarationContext<ASTNode>)declarationContext);
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    public OCSymbol getSymbol() {
        final OCSymbol symbol = this.getContainingOCFile().findSymbol((OCElement)this, OCSymbol.class);
        try {
            if (symbol != null) {
                return symbol;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return this.getLocalSymbol();
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCCppUsingStatementImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
