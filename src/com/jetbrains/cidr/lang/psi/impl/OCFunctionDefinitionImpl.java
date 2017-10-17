// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiCodeFragment;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCLocalScopeable;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;

public class OCFunctionDefinitionImpl extends OCFunctionDeclarationImpl implements OCFunctionDefinition
{
    public OCFunctionDefinitionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDefinitionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCCallableKind getKind() {
        return OCCallableKind.FUNCTION;
    }
    
    @Override
    public OCFunctionSymbol getSymbol() {
        OCFunctionSymbol ocFunctionSymbol = this.getContainingOCFile().findSymbol(this, OCFunctionSymbol.class);
        if (ocFunctionSymbol == null) {
            final OCLocalScopeable ocLocalScopeable = (OCLocalScopeable)PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCLocalScopeable.class });
            if (ocLocalScopeable instanceof OCStructLike) {
                final OCStructSymbol ocStructSymbol = ((OCStructLike)ocLocalScopeable).getSymbol();
                if (ocStructSymbol != null) {
                    final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
                        protected boolean accept(final OCSymbol ocSymbol) {
                            return ocSymbol instanceof OCFunctionSymbol && ocSymbol.getOffset() == OCFunctionDefinitionImpl.this.getTextOffset();
                        }
                    };
                    ocStructSymbol.processMembers(this.getSymbolName(), (Processor<OCSymbol>)findFirstProcessor);
                    return (OCFunctionSymbol)findFirstProcessor.getFoundValue();
                }
            }
        }
        try {
            if (ocFunctionSymbol != null || !(this.getContainingOCFile() instanceof PsiCodeFragment)) {
                return ocFunctionSymbol;
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        final OCFunctionSymbol localSymbol = this.getDeclarators().get(0).getLocalSymbol();
        if (localSymbol instanceof OCFunctionSymbol) {
            ocFunctionSymbol = localSymbol;
        }
        return ocFunctionSymbol;
    }
    
    @Override
    public OCConstructorInitializationList getConstructorInitializationList() {
        return (OCConstructorInitializationList)PsiTreeUtil.getChildOfType((PsiElement)this.getDeclarator(), (Class)OCConstructorInitializationList.class);
    }
    
    @NotNull
    @Override
    public OCBlockStatement getBody() {
        OCBlockStatement ocBlockStatement;
        try {
            ocBlockStatement = this.findNotNullChildByType(OCElementTypes.BLOCK_STATEMENTS);
            if (ocBlockStatement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDefinitionImpl", "getBody"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return ocBlockStatement;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDefinitionImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        ocVisitor.visitFunctionDefinition(this);
    }
    
    @Nullable
    @Override
    public OCConstructorInitializationList setConstructorInitializationList(@NotNull final OCConstructorInitializationList list) throws IncorrectOperationException {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCFunctionDefinitionImpl", "setConstructorInitializationList"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        final OCConstructorInitializationList constructorInitializationList = this.getConstructorInitializationList();
        try {
            if (constructorInitializationList != null) {
                return (OCConstructorInitializationList)OCChangeUtil.replaceHandlingMacros((PsiElement)constructorInitializationList, (PsiElement)list);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw c(ex2);
        }
        final OCDeclarator declarator = this.getDeclarator();
        declarator.add(OCElementFactory.create(OCTokenTypes.COLON, (PsiElement)this));
        return (OCConstructorInitializationList)declarator.add((PsiElement)list);
    }
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
