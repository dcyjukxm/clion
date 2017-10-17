// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public interface OCCallable<T extends OCSymbol> extends PsiNamedElement, OCSymbolDeclarator<T>, OCElement
{
    OCCallableKind getKind();
    
    @NotNull
    OCType getReturnType();
    
    @Nullable
    OCTypeElement getReturnTypeElement();
    
    @Nullable
    OCParameterList getParameterList();
    
    @Nullable
    List<? extends PsiNamedElement> getParameters();
    
    @Nullable
    OCNoexceptSpecifier getNoexceptSpecifier();
    
    @Nullable
    OCBlockStatement getBody();
}
