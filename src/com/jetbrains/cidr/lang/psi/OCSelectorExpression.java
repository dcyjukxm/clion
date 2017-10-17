// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;

public interface OCSelectorExpression extends OCExpression
{
    @NotNull
    String getSelector();
    
    @NotNull
    TextRange getSelectorRange();
    
    List<PsiElement> getSelectorParts();
    
    @NotNull
    OCType getExpectedReturnType();
    
    String getExpectedMethodSignature();
    
    @NotNull
    OCPolyVariantReference<OCMethodSymbol> getReference();
}
