// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.NavigatablePsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;

public interface OCFunctionDeclaration extends OCDeclaration, OCCallable<OCSymbolWithQualifiedName>, OCNamespaceQualifierOwner, NavigatablePsiElement, OCSymbolNameOwner
{
    @Nullable
    PsiElement getNameIdentifier();
    
    @Nullable
    OCDeclarator getDeclarator();
    
    @Nullable
    OCParameterList getParameterList();
    
    @Nullable
    List<OCDeclarator> getParameters();
    
    @Nullable
    OCTypeElement getTrailingReturnTypeElement();
    
    boolean isPossibleStructMember();
    
    @Nullable
    OCElementsRange getHeaderRange();
    
    @NotNull
    CVQualifiers getCVQualifiers();
    
    @NotNull
    List<PsiElement> getVirtualSpecifiers();
    
    boolean isOperator();
}
