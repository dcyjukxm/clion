// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class OCMacroReferenceElementImpl extends OCReferenceElementImpl
{
    public OCMacroReferenceElementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCMacroReferenceElementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCSymbol resolveToSymbol() {
        final OCSymbol resolveToSymbol = super.resolveToSymbol();
        try {
            if (resolveToSymbol != null) {
                return resolveToSymbol;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String canonicalText = this.getCanonicalText();
        final OCFile containingOCFile = this.getContainingOCFile();
        try {
            if (containingOCFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCMacroSymbol definition = OCInclusionContextUtil.headerContext((PsiFile)containingOCFile).getDefinition(canonicalText);
        try {
            if (definition != null) {
                return new OCMacroSymbol(containingOCFile.getProject(), OCInclusionContextUtil.getVirtualFile((PsiFile)containingOCFile), this.getTextOffset(), canonicalText, definition.getParameterNames(), definition.getSubstitution());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @Override
    public boolean isSoft() {
        return true;
    }
    
    @Override
    public PsiElement resolve() {
        final OCSymbol resolveToSymbol = super.resolveToSymbol();
        try {
            if (resolveToSymbol != null) {
                return resolveToSymbol.locateDefinition();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (PsiElement)this;
    }
    
    @Override
    public boolean isReferenceTo(final PsiElement psiElement) {
        Label_0033: {
            try {
                if (!(psiElement instanceof OCDefineDirective)) {
                    return false;
                }
                final OCMacroReferenceElementImpl ocMacroReferenceElementImpl = this;
                final String s = ocMacroReferenceElementImpl.getTextWithMacros();
                final PsiElement psiElement2 = psiElement;
                final OCDefineDirective ocDefineDirective = (OCDefineDirective)psiElement2;
                final String s2 = ocDefineDirective.getName();
                final boolean b = s.equals(s2);
                if (b) {
                    break Label_0033;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCMacroReferenceElementImpl ocMacroReferenceElementImpl = this;
                final String s = ocMacroReferenceElementImpl.getTextWithMacros();
                final PsiElement psiElement2 = psiElement;
                final OCDefineDirective ocDefineDirective = (OCDefineDirective)psiElement2;
                final String s2 = ocDefineDirective.getName();
                final boolean b = s.equals(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
