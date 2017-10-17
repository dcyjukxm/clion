// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.constructors;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public abstract class OCBaseConstructorReference implements PsiReference
{
    @NotNull
    protected final PsiElement myElement;
    @NotNull
    protected final OCSymbolDeclarator myField;
    @NotNull
    protected final OCFunctionSymbol myTargetConstructor;
    
    public OCBaseConstructorReference(@NotNull final PsiElement myElement, @NotNull final OCSymbolDeclarator myField, @NotNull final OCFunctionSymbol myTargetConstructor) {
        if (myElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/constructors/OCBaseConstructorReference", "<init>"));
        }
        if (myField == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "field", "com/jetbrains/cidr/lang/search/constructors/OCBaseConstructorReference", "<init>"));
        }
        if (myTargetConstructor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetConstructor", "com/jetbrains/cidr/lang/search/constructors/OCBaseConstructorReference", "<init>"));
        }
        this.myElement = myElement;
        this.myField = myField;
        this.myTargetConstructor = myTargetConstructor;
    }
    
    public PsiElement getElement() {
        return this.myElement;
    }
    
    public PsiElement resolve() {
        return ((OCSymbolBase<PsiElement>)this.myTargetConstructor).locateDefinition();
    }
    
    @NotNull
    public OCFunctionSymbol getTargetConstructor() {
        OCFunctionSymbol myTargetConstructor;
        try {
            myTargetConstructor = this.myTargetConstructor;
            if (myTargetConstructor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/constructors/OCBaseConstructorReference", "getTargetConstructor"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return myTargetConstructor;
    }
    
    @NotNull
    public OCSymbolDeclarator getField() {
        OCSymbolDeclarator myField;
        try {
            myField = this.myField;
            if (myField == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/constructors/OCBaseConstructorReference", "getField"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return myField;
    }
    
    @NotNull
    public String getCanonicalText() {
        String text;
        try {
            text = this.myElement.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/constructors/OCBaseConstructorReference", "getCanonicalText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        return null;
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/constructors/OCBaseConstructorReference", "bindToElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public boolean isReferenceTo(final PsiElement psiElement) {
        try {
            if (!(psiElement instanceof OCFunctionDeclaration)) {
                return false;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCSymbolWithQualifiedName symbol = ((OCFunctionDeclaration)psiElement).getSymbol();
        try {
            if (Comparing.equal((Object)this.myTargetConstructor, (Object)symbol)) {
                return true;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (symbol == null || !symbol.isGlobal()) {
                return false;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final OCQualifiedName resolvedQualifiedName = this.myTargetConstructor.getResolvedQualifiedName();
        final OCQualifiedName resolvedQualifiedName2 = symbol.getResolvedQualifiedName();
        try {
            if (!Comparing.equal((Object)resolvedQualifiedName, (Object)resolvedQualifiedName2)) {
                return false;
            }
            if (!(symbol instanceof OCFunctionSymbol)) {
                return true;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final OCType resolvedType = this.myTargetConstructor.getResolvedType();
        final OCType resolvedType2 = symbol.getResolvedType();
        try {
            if (!resolvedType.equals(resolvedType2, psiElement)) {
                return false;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @NotNull
    public Object[] getVariants() {
        Object[] array;
        try {
            array = new Object[0];
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/constructors/OCBaseConstructorReference", "getVariants"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return array;
    }
    
    public boolean isSoft() {
        return false;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
