// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.lang.ASTNode;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.PsiReference;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Iconable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.impl.source.tree.LazyParseablePsiElement;

public class OCLazyElementBase extends LazyParseablePsiElement implements OCElement
{
    public OCLazyElementBase(@NotNull final IElementType type, final CharSequence buffer) {
        if (type == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/psi/impl/OCLazyElementBase", "<init>"));
        }
        super(type, buffer);
    }
    
    @NotNull
    @Override
    public Language getLanguage() {
        OCLanguage instance;
        try {
            instance = OCLanguage.getInstance();
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLazyElementBase", "getLanguage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return instance;
    }
    
    @Override
    public String toString() {
        return OCElementUtil.getElementDebugName((PsiElement)this);
    }
    
    @Override
    public OCFile getContainingOCFile() {
        return (OCFile)super.getContainingFile();
    }
    
    protected Icon getBaseIcon() {
        final OCSymbol a = this.a();
        try {
            if (a != null) {
                return a.getBaseIcon();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return super.getBaseIcon();
    }
    
    @Nullable
    protected Icon getElementIcon(@Iconable.IconFlags final int n) {
        final OCSymbol a = this.a();
        try {
            if (a != null) {
                return a.computeFullIconNow((PsiElement)this);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return super.getElementIcon(n);
    }
    
    @Override
    public ItemPresentation getPresentation() {
        final OCSymbol a = this.a();
        try {
            if (a != null) {
                return a.getPresentation();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return super.getPresentation();
    }
    
    @Nullable
    private OCSymbol a() {
        try {
            if (this instanceof OCSymbolDeclarator) {
                return ((OCSymbolDeclarator)this).getSymbol();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public void accept(@NotNull final PsiElementVisitor psiElementVisitor) {
        try {
            if (psiElementVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCLazyElementBase", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElementVisitor instanceof OCVisitor) {
                this.accept((OCVisitor)psiElementVisitor);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        psiElementVisitor.visitElement((PsiElement)this);
    }
    
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCLazyElementBase", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitOCElement(this);
    }
    
    @Override
    public String getTextWithMacros() {
        return OCElementUtil.getTextWithMacros((PsiElement)this);
    }
    
    @NotNull
    @Override
    public TextRange getRangeWithMacros() {
        TextRange rangeWithMacros;
        try {
            rangeWithMacros = OCElementUtil.getRangeWithMacros((PsiElement)this);
            if (rangeWithMacros == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLazyElementBase", "getRangeWithMacros"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return rangeWithMacros;
    }
    
    @Override
    public boolean isEmpty() {
        return OCElementUtil.isElementEmpty((PsiElement)this);
    }
    
    @Override
    public PsiReference findReferenceAt(final int n) {
        final PsiReference referenceInMacro = OCElementUtil.findReferenceInMacro(this.findElementAt(n));
        try {
            if (referenceInMacro != null) {
                return referenceInMacro;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return super.findReferenceAt(n);
    }
    
    @NotNull
    @Override
    public SearchScope getUseScope() {
        SearchScope useScope;
        try {
            useScope = OCElementUtil.getUseScope((PsiElement)this);
            if (useScope == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLazyElementBase", "getUseScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return useScope;
    }
    
    public void deleteChildInternal(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/lang/psi/impl/OCLazyElementBase", "deleteChildInternal"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCChangeUtil.delete(astNode.getPsi());
    }
    
    @Override
    public long getComplexOffset() {
        return OCSymbolOffsetUtil.getComplexOffset((PsiElement)this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
