// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
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
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCLocalScopeable;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceAliasSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceAlias;

public class OCCppNamespaceAliasImpl extends OCElementBase implements OCCppNamespaceAlias
{
    public OCCppNamespaceAliasImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceAliasImpl", "<init>"));
        }
        super(astNode);
    }
    
    public OCNamespaceAliasSymbol getLocalSymbol() {
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
                return ocSymbol instanceof OCNamespaceAliasSymbol;
            }
        };
        new OCBuilderDriver<ASTNode>(containingOCFile, OCInclusionContext.empty(containingOCFile.getKind(), (PsiFile)containingOCFile), (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)new ASTStructure(this.getNode()), (BuilderDriverBase.NamedNodeStructure<Object>)OCBuilderDriver.AST_NAMED_NODE_STRUCTURE, (Processor<OCSymbol>)findFirstProcessor).processNamespaceAlias(this.getNode(), (Processor<OCSymbol>)findFirstProcessor, (BuilderDriverBase.DeclarationContext<ASTNode>)declarationContext);
        return (OCNamespaceAliasSymbol)findFirstProcessor.getFoundValue();
    }
    
    public OCNamespaceAliasSymbol getSymbol() {
        final OCNamespaceAliasSymbol ocNamespaceAliasSymbol = this.getContainingOCFile().findSymbol(this, OCNamespaceAliasSymbol.class);
        try {
            if (ocNamespaceAliasSymbol != null) {
                return ocNamespaceAliasSymbol;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return this.getLocalSymbol();
    }
    
    public PsiElement getNameIdentifier() {
        return this.findChildByType(OCTokenTypes.IDENTIFIER);
    }
    
    @Nullable
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
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceAliasImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceAliasImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitNamespaceAlias(this);
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
