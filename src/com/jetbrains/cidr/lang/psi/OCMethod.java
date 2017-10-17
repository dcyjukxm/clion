// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.NavigatablePsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;

public interface OCMethod extends OCElement, OCCallable<OCMethodSymbol>, NavigatablePsiElement, PsiNameIdentifierOwner, OCLocalScopeable
{
    @NotNull
    OCClassDeclaration getContainingClass();
    
    boolean isInstanceMethod();
    
    void setStatic(final boolean p0);
    
    @NotNull
    String getSelector();
    
    @NotNull
    OCType getRawReturnType();
    
    @NotNull
    List<OCMethodSelectorPart> getParameters();
    
    @NotNull
    List<PsiElement> getSelectors();
    
    TextRange getHeaderRange();
}
