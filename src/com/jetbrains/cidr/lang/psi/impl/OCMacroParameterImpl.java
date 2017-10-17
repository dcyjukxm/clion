// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.intellij.psi.search.SearchScope;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroParameterSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCMacroParameter;

public class OCMacroParameterImpl extends OCElementBase implements OCMacroParameter
{
    public OCMacroParameterImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCMacroParameterImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    public PsiElement getNameIdentifier() {
        PsiElement notNullChildByType;
        try {
            notNullChildByType = this.findNotNullChildByType(OCTokenTypes.IDENTIFIER);
            if (notNullChildByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMacroParameterImpl", "getNameIdentifier"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return notNullChildByType;
    }
    
    @NotNull
    public String getName() {
        String text;
        try {
            text = this.getNameIdentifier().getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMacroParameterImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCMacroParameterImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    public OCMacroParameterSymbol getSymbol() {
        return new OCMacroParameterSymbol(this.getProject(), this.getContainingFile().getVirtualFile(), this.getTextOffset(), this.getNameIdentifier().getText());
    }
    
    @NotNull
    @Override
    public SearchScope getUseScope() {
        final PsiElement contextOfType = PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCDefineDirective.class });
        SearchScope useScope = null;
        Label_0083: {
            Label_0033: {
                try {
                    if (contextOfType == null) {
                        break Label_0083;
                    }
                    final PsiElement psiElement = contextOfType;
                    final OCMacroParameterImpl ocMacroParameterImpl = this;
                    final PsiElement psiElement2 = ocMacroParameterImpl.getContext();
                    if (psiElement != psiElement2) {
                        break Label_0033;
                    }
                    break Label_0083;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement = contextOfType;
                    final OCMacroParameterImpl ocMacroParameterImpl = this;
                    final PsiElement psiElement2 = ocMacroParameterImpl.getContext();
                    if (psiElement == psiElement2) {
                        break Label_0083;
                    }
                    final LocalSearchScope localSearchScope = new LocalSearchScope(contextOfType);
                    if (localSearchScope != null) {
                        return (SearchScope)localSearchScope;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMacroParameterImpl", "getUseScope"));
            try {
                useScope = super.getUseScope();
                if (useScope == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMacroParameterImpl", "getUseScope"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return useScope;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCMacroParameterImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitMacroParameter(this);
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
