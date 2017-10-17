// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;

public interface OCMethodSelectorPart extends OCElement, OCLocalSymbolDeclarator<OCDeclaratorSymbol>, PsiNameIdentifierOwner
{
    @Nullable
    String getSelectorPart();
    
    @Nullable
    String getParameterName();
    
    @Nullable
    PsiElement getSelectorIdentifier();
    
    @NotNull
    OCType getType();
    
    @NotNull
    OCType getRawType();
    
    @Nullable
    PsiElement getParameter();
    
    @Nullable
    ASTNode findParameterNode();
    
    @Nullable
    OCTypeElement getTypeElement();
}
