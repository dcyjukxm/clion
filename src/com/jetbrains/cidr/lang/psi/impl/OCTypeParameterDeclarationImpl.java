// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCBuilderDriver;
import com.intellij.psi.impl.source.tree.ASTStructure;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;

public class OCTypeParameterDeclarationImpl extends OCElementBase implements OCTypeParameterDeclaration
{
    public OCTypeParameterDeclarationImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCTypeParameterDeclarationImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCTypeParameterDeclarationImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitTypeParameterDeclaration(this);
    }
    
    @Nullable
    public PsiElement getNameIdentifier() {
        return this.findChildByType(OCTokenTypes.IDENTIFIER);
    }
    
    @Nullable
    @Override
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCTypeParameterDeclarationImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    public OCTypeParameterTypeSymbol getSymbol() {
        return this.getLocalSymbol();
    }
    
    @Nullable
    public OCTypeParameterTypeSymbol getLocalSymbol() {
        final OCFile containingOCFile = this.getContainingOCFile();
        return new OCBuilderDriver<ASTNode>(containingOCFile, OCInclusionContext.empty(containingOCFile.getKind(), (PsiFile)containingOCFile), (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)new ASTStructure(this.getNode()), (BuilderDriverBase.NamedNodeStructure<Object>)OCBuilderDriver.AST_NAMED_NODE_STRUCTURE, (Processor<OCSymbol>)CommonProcessors.alwaysTrue()).processTypeParameter(this.getNode(), this.a());
    }
    
    @NotNull
    private BuilderDriverBase.DeclarationContext a() {
        final PsiElement contextOfType = PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCDeclaration.class });
        BuilderDriverBase.DeclarationContext declarationContext;
        try {
            declarationContext = new BuilderDriverBase.DeclarationContext(OCSymbolKind.TEMPLATE_TYPE_PARAMETER, contextOfType, null, null, (PsiElement)this, false);
            if (declarationContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCTypeParameterDeclarationImpl", "getDeclarationContext"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return declarationContext;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
